/*

*/
grammar JavaTemplateGrammar;

@header
{

package org.edgo.jtg.core.grammar;

}

options {
	tokenVocab	= JavaTemplateLexer;
}

@parser::members {
    private org.edgo.jtg.core.model.ParsedUnit unit;

    public void setUnit(org.edgo.jtg.core.model.ParsedUnit unit) {
        this.unit = unit;
    }
}

template
    : directive_template
      (
        directive_jar
        | directive_property
        | directive_import
        | directive_extends
        | /*{ _input.LA(1) != EOF }? */targetcode
      )*
      ( script
        | placeholder
        | directive_include
        | macrocode
        | comment
        | targetcode
      )* 
      EOF
    ;

directive_template
    : MACROCODE_BEGIN DIRECTIVE_BEGIN (WS_DIR)* v=DIRECTIVE_CMD_TEMPLATE (WS_DIR)+  
	  lang=directive_attr_language (WS_DIR)+ targetlanguage=directive_attr_target_language ((WS_DIR)+ directive_attr_description)?
	  (WS_DIR)* DIRECTIVE_END (NEWLINE)*
	  {
		unit.setMacroLang($lang.lang);
	  }
    ;

directive_import
    : MACROCODE_BEGIN DIRECTIVE_BEGIN (WS_DIR)? v=DIRECTIVE_CMD_IMPORT (WS_DIR)+ ns=directive_attr_name (WS_DIR)? DIRECTIVE_END (NEWLINE)*
    {
		unit.directiveImport($ns.name, $v.getLine()); 
	}
    ;

directive_include
    : MACROCODE_BEGIN DIRECTIVE_BEGIN (WS_DIR)? v=DIRECTIVE_CMD_INCLUDE (WS_DIR)+
      file=directive_attr_file ((WS_DIR)+ params=directive_attr_params)? ((WS_DIR)+ arg=directive_attr_arg)? (WS_DIR)?
      DIRECTIVE_END (NEWLINE)*
      {
      	String params = null;
      	if (_localctx.params != null) {
      		params = _localctx.params.params;
      	}
      	String arg = null;
      	if (_localctx.arg != null) {
      		arg = _localctx.arg.arg;
      	}
		unit.addInclude($v, $file.file, params, arg); 
	  }
    ;

directive_extends
    : MACROCODE_BEGIN DIRECTIVE_BEGIN (WS_DIR)? v=DIRECTIVE_CMD_EXTENDS (WS_DIR)+ parent=directive_attr_parent (WS_DIR)? DIRECTIVE_END(NEWLINE)*
    {
		unit.directiveExtends($parent.parent, $v.getLine()); 
	}
    ;

directive_jar
    : MACROCODE_BEGIN DIRECTIVE_BEGIN (WS_DIR)? v=DIRECTIVE_CMD_JAR (WS_DIR)+ name=directive_attr_name (WS_DIR)? DIRECTIVE_END (NEWLINE)*
    {
		unit.directiveJar($name.name);
    }
    ;

directive_property
    : MACROCODE_BEGIN DIRECTIVE_BEGIN (WS_DIR)? v=DIRECTIVE_CMD_PROPERTY (WS_DIR)+
   	    name=directive_attr_name (WS_DIR)+ type=directive_attr_type ((WS_DIR)+ directive_attr_description)?
	  (WS_DIR)? DIRECTIVE_END (NEWLINE)*
	  {
		unit.directiveArguments($name.name, $type.type, $v.getLine());
	  }
    ;

directive_attr_language returns[String lang] : DIRECTIVE_ATTR_LANGUAGE l=directive_attr { _localctx.lang = _localctx.l.value; };

directive_attr_target_language returns[String lang] : DIRECTIVE_ATTR_TARGET_LANGUAGE l=directive_attr { _localctx.lang = _localctx.l.value; };

directive_attr_description : DIRECTIVE_ATTR_DESCRIPTION directive_attr;

directive_attr_name returns[String name] : DIRECTIVE_ATTR_NAME n=directive_attr { _localctx.name = _localctx.n.value; };

directive_attr_parent returns[String parent] : DIRECTIVE_ATTR_PARENT p=directive_attr { _localctx.parent = _localctx.p.value; };

directive_attr_type returns[String type] : DIRECTIVE_ATTR_TYPE t=directive_attr { _localctx.type = _localctx.t.value; };

directive_attr_file returns[String file] : DIRECTIVE_ATTR_FILE f=directive_attr { _localctx.file = _localctx.f.value; };

directive_attr_arg returns[String arg] : DIRECTIVE_ATTR_ARG a=directive_attr { _localctx.arg = _localctx.a.value; };

directive_attr_params returns[String params] : DIRECTIVE_ATTR_PARAMS p=directive_attr { _localctx.params = _localctx.p.value; };

directive_attr returns[String value] : ASSIGN v=STRING_VALUE 
	{
		String value = _localctx.v.getText();
        if (value.startsWith("\"")) {
            value = value.substring(1);
        }
        if (value.endsWith("\"")) {
            value = value.substring(0, value.length() - 1);
        }
		_localctx.value = value;
	};

script
    : MACROCODE_BEGIN SCRIPT_BEGIN ( v=SCRIPT_MACROCODE 
	    { 
		  unit.addScript($v); 
	    }
	  )? MACROCODE_BEGIN SCRIPT_END (NEWLINE)*;

placeholder
    : MACROCODE_BEGIN PLACEHOLDER_BEGIN v=MACROCODE MACRO_END
	{
		unit.addPlaceholder($v);
	}
    ;

macrocode 
    : MACROCODE_BEGIN (v1=MACRO_BLOCK_BEGIN (v2=MACROCODE)? 
	{
		unit.addMacrocode($v1, $v2);
	} )
	MACRO_END;

comment : MACROCODE_BEGIN COMMENT_BEGIN (v=COMMENTCODE)? COMMENT_END;

targetcode
    : NEWLINE* v=TARGETCODE
		{
			unit.addTargetcode($v); 
		} 
    ;
