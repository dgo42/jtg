// $ANTLR 2.7.7 (20060906): "JavaTemplateGrammar.g" -> "JavaTemplateLexer.java"$


package org.edgo.jtg.core.grammar;

import org.edgo.jtg.core.model.ParsedUnit;
import antlr.TokenStreamSelector;


public interface JavaTemplateTokenTypes {
	int EOF = 1;
	int NULL_TREE_LOOKAHEAD = 3;
	int PLACEHOLDER_BEGIN = 4;
	int DIRECTIVE_BEGIN = 5;
	int COMMENT_BEGIN = 6;
	int SCRIPT_BEGIN = 7;
	int SCRIPT_END = 8;
	int MACROCODE_BEGIN = 9;
	int WS = 10;
	int TARGETCODE = 11;
	int MACROCODE = 12;
	int MACROCODE_END = 13;
	int RUNAT = 14;
	int ASSIGN = 15;
	int DIRECTIVE_CMD_TEMPLATE = 16;
	int DIRECTIVE_CMD_PROPERTY = 17;
	int DIRECTIVE_CMD_JAR = 18;
	int DIRECTIVE_CMD_IMPORT = 19;
	int DIRECTIVE_CMD_EXTENDS = 20;
	int DIRECTIVE_ATTR_LANGUAGE = 21;
	int DIRECTIVE_ATTR_TARGET_LANGUAGE = 22;
	int DIRECTIVE_ATTR_DESCRIPTION = 23;
	int DIRECTIVE_ATTR_NAME = 24;
	int DIRECTIVE_ATTR_PARENT = 25;
	int DIRECTIVE_ATTR_TYPE = 26;
	int DIRECTIVE_ATTR_CATEGORY = 27;
	int STRING_VALUE = 28;
}
