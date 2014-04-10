header
{

package org.edgo.jtg.core.grammar;

import antlr.TokenStreamSelector;

}

options {
}

class TargetLexer extends Lexer;

options {
	k=5;
    charVocabulary = '\u0003'..'\uFFFF';
    exportVocab = Targetcode;
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

PLACEHOLDER_BEGIN
    : (
		options {
			generateAmbigWarnings=false;
		}
		: "<#="
		{ 
			selector.push("macrocode");
		}
	  )
    ;

DIRECTIVE_BEGIN
    : (
		options {
			generateAmbigWarnings=false;
		}
		: "<#@"
		{ 
			selector.push("main");
		}
	  )
    ;

COMMENT_BEGIN
    : (
		options {
			generateAmbigWarnings=false;
		}
		: "<#--"
		{ 
			selector.push("macrocode");
		}
	  )
    ;

SCRIPT_BEGIN
	: (
		options {
			generateAmbigWarnings=false;
		}
		: "<#" (' ')? "script"
		{ 
			selector.push("main");
	    }
	  )
	;

SCRIPT_END
	: (
		options {
			generateAmbigWarnings=false;
		}
		: "<#/" (' ')? "script"
		{ 
			selector.push("main");
	    }
	  )
	;

MACROCODE_BEGIN
    : (
		options {
			generateAmbigWarnings=false;
		}
		:
		// not <#= & not <#- & not <#@ & not <#s & not <#/ - only macrocode - switch to macrocode
		{ !(LA(3) == '=' || LA(3) == '-' || LA(3) == '@' || LA(3) == 's' || (LA(3) == '/' && LA(4) != '/')) }? "<#" 
		{
			selector.push("macrocode");
		}
	  )
    ;

WS  
    : (
		(' '
		|   '\t'
		|   '\n'  { newline(); }
		|   '\r')
	  )+
    ;

TARGETCODE
    : (
        options {
            generateAmbigWarnings=false;
        }
        :
         (
			{ LA(2) != '#' }? '<'
			| WS
			| ~('<'|' '|'\t'|'\n'|'\r'|'\uFFFF')
         )+ 
      )
    ;
