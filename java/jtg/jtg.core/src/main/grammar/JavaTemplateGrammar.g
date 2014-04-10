header
{

package org.edgo.jtg.core.grammar;

import org.edgo.jtg.core.model.ParsedUnit;
import antlr.TokenStreamSelector;

}

options {
}

class JavaTemplateLexer extends Lexer;

options {
    k=5; // Name vs. Namespace
    charVocabulary = '\u0003'..'\uFFFF';
    importVocab = Macrocode;
    exportVocab = JavaTemplate;
}

{
    private TokenStreamSelector selector;
    
    public TokenStreamSelector getSelector() {
        return selector;
    }
    
    public void setSelector(TokenStreamSelector selector) {
        this.selector = selector;
    }
}

RUNAT
	: "runat"
	;

ASSIGN
	: '='
	;

MACROCODE_END
    options {
        generateAmbigWarnings=false;
    }
    : "#>" 
    {
		selector.push("targetcode");
	}
    ;

DIRECTIVE_CMD_TEMPLATE
    :   "codeTemplate"
    ;

DIRECTIVE_CMD_PROPERTY
    :   "property"
    ;

DIRECTIVE_CMD_JAR
    :   "jar"
    ;

DIRECTIVE_CMD_IMPORT
    :   "import"
    ;

DIRECTIVE_CMD_EXTENDS
    :   "extends"
    ;

DIRECTIVE_ATTR_LANGUAGE
    :   "language"
    ;

DIRECTIVE_ATTR_TARGET_LANGUAGE
    :   "targetLanguage"
    ;

DIRECTIVE_ATTR_DESCRIPTION
    :   "description"
    ;

DIRECTIVE_ATTR_NAME
    :   "name"
    ;

DIRECTIVE_ATTR_PARENT
    :   "parent"
    ;

DIRECTIVE_ATTR_TYPE
    :   "type"
    ;

// CodeSmith compatibility. Ignoring this token.
DIRECTIVE_ATTR_CATEGORY
    :   "category"
    ;

STRING_VALUE
	: '"' (~('"'))* '"'
	;

WS  
    :   (' '
    |   '\t'
    |   '\n'  { newline(); }
    |   '\r')
        { $setType(Token.SKIP); }
    ;
    
class JavaTemplateParser extends Parser;

options {
    k = 4;
    importVocab = JavaTemplate;
}

{
		private ParsedUnit unit;
}

template [ParsedUnit unit]
	{
		this.unit = unit;
	}
    : ( directive_template )
      (
        ( directive_jar )
        | ( directive_property )
        | ( directive_import )
        | ( directive_extends )
      )*
      ( script
		| placeholder
        | macrocode
        | comment
        | { LA(1) != EOF }? targetcode
       )*
    ;

directive_template
	{
		String language;
		String targetlanguage;
	}
    : DIRECTIVE_BEGIN v:DIRECTIVE_CMD_TEMPLATE 
	  (
		(language=directive_attr_language targetlanguage=directive_attr_target_language (directive_attr_description)? )
		| (targetlanguage=directive_attr_target_language language=directive_attr_language (directive_attr_description)? )
	  )
	  MACROCODE_END (WS)?
	  {
		unit.setMacroLang(language);
	  }
    ;

directive_import
	{
		String ns;
	}
    : DIRECTIVE_BEGIN v:DIRECTIVE_CMD_IMPORT ns=directive_attr_name MACROCODE_END (WS)? 
    {
		unit.directiveImport(ns, v.getLine()); 
	}
    ;

directive_extends
	{
		String parent;
	}
    : DIRECTIVE_BEGIN v:DIRECTIVE_CMD_EXTENDS parent=directive_attr_parent MACROCODE_END (WS)? 
    {
		unit.directiveExtends(parent, v.getLine()); 
	}
    ;

directive_jar
	{
		String name;
	}
    : DIRECTIVE_BEGIN v:DIRECTIVE_CMD_JAR name=directive_attr_name MACROCODE_END (WS)? 
    {
		unit.directiveJar(name);
    }
    ;

directive_property
	{
		String name;
		String type;
	}
    : DIRECTIVE_BEGIN v:DIRECTIVE_CMD_PROPERTY
   	  (
		options {
			generateAmbigWarnings=false;
		}
		:
		  (name=directive_attr_name type=directive_attr_type (directive_attr_category)? (directive_attr_description)? )
		| (name=directive_attr_name type=directive_attr_type (directive_attr_description)? (directive_attr_category)? )
		| (type=directive_attr_type name=directive_attr_name (directive_attr_category)? (directive_attr_description)? )
		| (type=directive_attr_type name=directive_attr_name (directive_attr_description)? (directive_attr_category)? )
	  )
	  MACROCODE_END (WS)?
	  {
		unit.directiveArguments(name, type, v.getLine());
	  }
    ;

directive_attr_language returns[String language]
	{
		language = "";
	}
	: DIRECTIVE_ATTR_LANGUAGE language=directive_attr
	;

directive_attr_target_language returns[String language]
	{
		language = "";
	}
	: DIRECTIVE_ATTR_TARGET_LANGUAGE language=directive_attr
	;

directive_attr_description
	{
		String attr;
	}
	: DIRECTIVE_ATTR_DESCRIPTION attr=directive_attr
	;

directive_attr_name returns[String name]
	{
		name = "";
	}
	: DIRECTIVE_ATTR_NAME name=directive_attr
	;

directive_attr_parent returns[String parent]
	{
		parent = "";
	}
	: DIRECTIVE_ATTR_PARENT parent=directive_attr
	;

directive_attr_type returns[String type]
	{
		type = "";
	}
	: DIRECTIVE_ATTR_TYPE type=directive_attr
	;

directive_attr_category
	{
		String attr;
	}
	: DIRECTIVE_ATTR_CATEGORY attr=directive_attr
	;

directive_attr returns[String value]
	{
		value = "";
	}
    : ASSIGN v:STRING_VALUE 
     { 
		value = v.getText();
        if (value.startsWith("\""))
            value = value.substring(1);
        if (value.endsWith("\""))
            value = value.substring(0, value.length() - 1);
     }
    ;

script
    : SCRIPT_BEGIN 
      ( RUNAT ASSIGN
		(
		  t:STRING_VALUE
		  {
			if (t.getText() != "\"template\"")
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
		  }
		  | "'template'"
		  | "template"
		)
	  )?
	  MACROCODE_END (WS)? ( v:TARGETCODE 
	    { 
		  unit.addScript(v); 
	    }
	  )? SCRIPT_END MACROCODE_END (WS)?
	  {
	  }
    ;

placeholder
    : PLACEHOLDER_BEGIN v:MACROCODE MACROCODE_END
	{
		unit.addPlaceholder(v);
	}
    ;

macrocode
    : MACROCODE_BEGIN ( v:MACROCODE { unit.addMacrocode(v); } )? MACROCODE_END
	{
	}
    ;

comment
    : COMMENT_BEGIN (v:MACROCODE)? MACROCODE_END
    {
	}
    ;

targetcode
    : ( w:WS )? ( v:TARGETCODE )?
		{
			if (null != v)
			{
				if (null != w)
				{
					v.setText(w.getText() + v.getText());
					v.setLine(w.getLine());
				}
				unit.addTargetcode(v); 
			}
			else
			{
				if (null != w)
				{
					unit.addTargetcode(w); 
				}
			}
		} 
    ;
