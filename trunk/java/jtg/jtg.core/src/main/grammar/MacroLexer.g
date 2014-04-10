header
{

package org.edgo.jtg.core.grammar;

import antlr.TokenStreamSelector;

}

options {
}

class MacroLexer extends Lexer;

options {
	k=2;
    charVocabulary = '\u0003'..'\uFFFF';
    importVocab = Targetcode;
    exportVocab = Macrocode;
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

MACROCODE
    : (
         options {
		    generateAmbigWarnings=false;
		}
		:
		  (
			'\n' { newline(); }
				// all '#' but not "#>"
			| { LA(2) != '>' }? '#'
				// all but '#' and '\n'
			| ~('#'|'\n')
		  )+ 
      )
    ;

MACROCODE_END
    : (
        options {
		    generateAmbigWarnings=false;
	    }
		: "#>" 
		{
			selector.push("targetcode");
		}
	  )
    ;
