/*

*/
lexer grammar JavaTemplateLexer;

tokens {
	DIRECTIVE_CMD_TEMPLATE,
	DIRECTIVE_CMD_PROPERTY,
	DIRECTIVE_CMD_JAR,
	DIRECTIVE_CMD_IMPORT,
	DIRECTIVE_CMD_INCLUDE,
	DIRECTIVE_CMD_EXTENDS,
	DIRECTIVE_ATTR_LANGUAGE,
	DIRECTIVE_ATTR_TARGET_LANGUAGE,
	DIRECTIVE_ATTR_DESCRIPTION,
	DIRECTIVE_ATTR_NAME,
	DIRECTIVE_ATTR_FILE,
	DIRECTIVE_ATTR_ARG,
	DIRECTIVE_ATTR_PARAMS,
	DIRECTIVE_ATTR_PARENT,
	DIRECTIVE_ATTR_TYPE,
	STRING_VALUE,
	MACROCODE_BEGIN,
	MACROCODE_END,
	PLACEHOLDER_BEGIN,
	DIRECTIVE_BEGIN,
	COMMENT_BEGIN,
	SCRIPT_BEGIN,
	SCRIPT_END,
	MACROCODE,
	TARGETCODE
}

@header
{

package org.edgo.jtg.core.grammar;

}

MACROCODE_BEGIN : 
	// not <#= & not <#- & not <#@ & not <#s & not <#/ - only macrocode - switch to macrocode
	//{ !(_input.LA(3) == '=' || _input.LA(3) == '-' || _input.LA(3) == '@' || _input.LA(3) == 's' || (_input.LA(3) == '/' && _input.LA(4) != 's')) }? 
	'<#'	-> mode(TEMPLATE);

WS_NEWLINE : ( ' ' | '\t' | ( '\n' | '\r')) -> channel(HIDDEN);

TARGETCODE
    : (
		/* '<' but not '<#' */
		{ _input.LA(2) != '#' }? '<'
		/*  */
		| ~[<]
      )+
    ;

mode TEMPLATE;

MACROCODE_END : '#>';

PLACEHOLDER_BEGIN : 
	{ (_input.LA(-2) == '<' && _input.LA(-1) == '#') }?
	'=' -> mode(MACRO);

DIRECTIVE_BEGIN : 
	{ (_input.LA(-2) == '<' && _input.LA(-1) == '#') }?
	'@' -> mode(DIRECTIVE);

COMMENT_BEGIN : 
	{ (_input.LA(-2) == '<' && _input.LA(-1) == '#') }?
	'--' -> mode(COMMENT);

SCRIPT_BEGIN : 
	{ (_input.LA(-2) == '<' && _input.LA(-1) == '#') }? 
	'script' MACROCODE_END -> mode(SCRIPT_MACRO);

SCRIPT_END : 
	{ (_input.LA(-2) == '<' && _input.LA(-1) == '#') }?
	'/script' MACROCODE_END -> mode(DEFAULT_MODE);

MACRO_BLOCK_BEGIN : 
	{ (_input.LA(-2) == '<' && _input.LA(-1) == '#' && _input.LA(1) != '=' && _input.LA(1) != '@' && _input.LA(1) != '-' && _input.LA(1) != 's' && _input.LA(1) != '/') }? 
	~[@=s/-] -> mode(MACRO);

mode MACRO;

MACROCODE
    :   (
			// all '#' but not "#>"
			{ _input.LA(2) != '>' }? '#'
			// all but '#' and '\n'
			| ~[#]
			| '\r'
			| '\n'
	  	)+ 
    ;

MACRO_END : '#>' -> mode(DEFAULT_MODE);

mode COMMENT;

COMMENTCODE
    :   (
			// all '#' but not "#>"
			{ _input.LA(2) != '>' }? '#'
			// all but '#' and '\n'
			| ~[#]
	  	)+ 
    ;

COMMENT_END : '#>' -> mode(DEFAULT_MODE);

mode SCRIPT_MACRO;

SCRIPT_MACROCODE
    :   (
			// all '#' but not "#>"
			{ _input.LA(2) != '#' }? '<'
			// all but '<'
			| ~[<]
	  	)+
		-> mode(DEFAULT_MODE)  	
    ;

mode DIRECTIVE;

ASSIGN : '=';

DIRECTIVE_CMD_TEMPLATE : 'codeTemplate';

DIRECTIVE_CMD_PROPERTY : 'property';

DIRECTIVE_CMD_JAR : 'jar';

DIRECTIVE_CMD_IMPORT : 'import';

DIRECTIVE_CMD_INCLUDE : 'include';

DIRECTIVE_CMD_EXTENDS : 'extends';

DIRECTIVE_ATTR_LANGUAGE : 'language';

DIRECTIVE_ATTR_TARGET_LANGUAGE : 'targetLanguage';

DIRECTIVE_ATTR_DESCRIPTION : 'description';

DIRECTIVE_ATTR_NAME : 'name';

DIRECTIVE_ATTR_FILE : 'file';

DIRECTIVE_ATTR_ARG : 'arg';

DIRECTIVE_ATTR_PARAMS : 'params';

DIRECTIVE_ATTR_PARENT : 'parent';

DIRECTIVE_ATTR_TYPE : 'type';

WS_DIR : (' ' | '\t');

fragment
ESCAPED_QUOTE : '\\"';

STRING_VALUE : '"' ( ESCAPED_QUOTE | ~('\n'|'\r') )*? '"';

DIRECTIVE_END : '#>' -> mode(DEFAULT_MODE);
