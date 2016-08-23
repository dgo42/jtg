// Generated from ..\grammar\JavaTemplateLexer.g4 by ANTLR 4.5.3


package org.edgo.jtg.core.grammar;


import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JavaTemplateLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DIRECTIVE_CMD_TEMPLATE=1, DIRECTIVE_CMD_PROPERTY=2, DIRECTIVE_CMD_JAR=3, 
		DIRECTIVE_CMD_IMPORT=4, DIRECTIVE_CMD_INCLUDE=5, DIRECTIVE_CMD_EXTENDS=6, 
		DIRECTIVE_ATTR_LANGUAGE=7, DIRECTIVE_ATTR_TARGET_LANGUAGE=8, DIRECTIVE_ATTR_DESCRIPTION=9, 
		DIRECTIVE_ATTR_NAME=10, DIRECTIVE_ATTR_FILE=11, DIRECTIVE_ATTR_ARG=12, 
		DIRECTIVE_ATTR_PARAMS=13, DIRECTIVE_ATTR_PARENT=14, DIRECTIVE_ATTR_TYPE=15, 
		STRING_VALUE=16, MACROCODE_BEGIN=17, MACROCODE_END=18, PLACEHOLDER_BEGIN=19, 
		DIRECTIVE_BEGIN=20, COMMENT_BEGIN=21, SCRIPT_BEGIN=22, SCRIPT_END=23, 
		MACROCODE=24, TARGETCODE=25, WS_NEWLINE=26, MACRO_BLOCK_BEGIN=27, MACRO_END=28, 
		COMMENTCODE=29, COMMENT_END=30, SCRIPT_MACROCODE=31, ASSIGN=32, WS_DIR=33, 
		DIRECTIVE_END=34;
	public static final int TEMPLATE = 1;
	public static final int MACRO = 2;
	public static final int COMMENT = 3;
	public static final int SCRIPT_MACRO = 4;
	public static final int DIRECTIVE = 5;
	public static String[] modeNames = {
		"DEFAULT_MODE", "TEMPLATE", "MACRO", "COMMENT", "SCRIPT_MACRO", "DIRECTIVE"
	};

	public static final String[] ruleNames = {
		"MACROCODE_BEGIN", "WS_NEWLINE", "TARGETCODE", "MACROCODE_END", "PLACEHOLDER_BEGIN", 
		"DIRECTIVE_BEGIN", "COMMENT_BEGIN", "SCRIPT_BEGIN", "SCRIPT_END", "MACRO_BLOCK_BEGIN", 
		"MACROCODE", "MACRO_END", "COMMENTCODE", "COMMENT_END", "SCRIPT_MACROCODE", 
		"ASSIGN", "DIRECTIVE_CMD_TEMPLATE", "DIRECTIVE_CMD_PROPERTY", "DIRECTIVE_CMD_JAR", 
		"DIRECTIVE_CMD_IMPORT", "DIRECTIVE_CMD_INCLUDE", "DIRECTIVE_CMD_EXTENDS", 
		"DIRECTIVE_ATTR_LANGUAGE", "DIRECTIVE_ATTR_TARGET_LANGUAGE", "DIRECTIVE_ATTR_DESCRIPTION", 
		"DIRECTIVE_ATTR_NAME", "DIRECTIVE_ATTR_FILE", "DIRECTIVE_ATTR_ARG", "DIRECTIVE_ATTR_PARAMS", 
		"DIRECTIVE_ATTR_PARENT", "DIRECTIVE_ATTR_TYPE", "WS_DIR", "ESCAPED_QUOTE", 
		"STRING_VALUE", "DIRECTIVE_END"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'codeTemplate'", "'property'", "'jar'", "'import'", "'include'", 
		"'extends'", "'language'", "'targetLanguage'", "'description'", "'name'", 
		"'file'", "'arg'", "'params'", "'parent'", "'type'", null, "'<#'", null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "DIRECTIVE_CMD_TEMPLATE", "DIRECTIVE_CMD_PROPERTY", "DIRECTIVE_CMD_JAR", 
		"DIRECTIVE_CMD_IMPORT", "DIRECTIVE_CMD_INCLUDE", "DIRECTIVE_CMD_EXTENDS", 
		"DIRECTIVE_ATTR_LANGUAGE", "DIRECTIVE_ATTR_TARGET_LANGUAGE", "DIRECTIVE_ATTR_DESCRIPTION", 
		"DIRECTIVE_ATTR_NAME", "DIRECTIVE_ATTR_FILE", "DIRECTIVE_ATTR_ARG", "DIRECTIVE_ATTR_PARAMS", 
		"DIRECTIVE_ATTR_PARENT", "DIRECTIVE_ATTR_TYPE", "STRING_VALUE", "MACROCODE_BEGIN", 
		"MACROCODE_END", "PLACEHOLDER_BEGIN", "DIRECTIVE_BEGIN", "COMMENT_BEGIN", 
		"SCRIPT_BEGIN", "SCRIPT_END", "MACROCODE", "TARGETCODE", "WS_NEWLINE", 
		"MACRO_BLOCK_BEGIN", "MACRO_END", "COMMENTCODE", "COMMENT_END", "SCRIPT_MACROCODE", 
		"ASSIGN", "WS_DIR", "DIRECTIVE_END"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public JavaTemplateLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "JavaTemplateLexer.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return TARGETCODE_sempred((RuleContext)_localctx, predIndex);
		case 4:
			return PLACEHOLDER_BEGIN_sempred((RuleContext)_localctx, predIndex);
		case 5:
			return DIRECTIVE_BEGIN_sempred((RuleContext)_localctx, predIndex);
		case 6:
			return COMMENT_BEGIN_sempred((RuleContext)_localctx, predIndex);
		case 7:
			return SCRIPT_BEGIN_sempred((RuleContext)_localctx, predIndex);
		case 8:
			return SCRIPT_END_sempred((RuleContext)_localctx, predIndex);
		case 9:
			return MACRO_BLOCK_BEGIN_sempred((RuleContext)_localctx, predIndex);
		case 10:
			return MACROCODE_sempred((RuleContext)_localctx, predIndex);
		case 12:
			return COMMENTCODE_sempred((RuleContext)_localctx, predIndex);
		case 14:
			return SCRIPT_MACROCODE_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean TARGETCODE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return  _input.LA(2) != '#' ;
		}
		return true;
	}
	private boolean PLACEHOLDER_BEGIN_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return  (_input.LA(-2) == '<' && _input.LA(-1) == '#') ;
		}
		return true;
	}
	private boolean DIRECTIVE_BEGIN_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return  (_input.LA(-2) == '<' && _input.LA(-1) == '#') ;
		}
		return true;
	}
	private boolean COMMENT_BEGIN_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return  (_input.LA(-2) == '<' && _input.LA(-1) == '#') ;
		}
		return true;
	}
	private boolean SCRIPT_BEGIN_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return  (_input.LA(-2) == '<' && _input.LA(-1) == '#') ;
		}
		return true;
	}
	private boolean SCRIPT_END_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return  (_input.LA(-2) == '<' && _input.LA(-1) == '#') ;
		}
		return true;
	}
	private boolean MACRO_BLOCK_BEGIN_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 6:
			return  (_input.LA(-2) == '<' && _input.LA(-1) == '#' && _input.LA(1) != '=' && _input.LA(1) != '@' && _input.LA(1) != '-' && _input.LA(1) != 's' && _input.LA(1) != '/') ;
		}
		return true;
	}
	private boolean MACROCODE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 7:
			return  _input.LA(2) != '>' ;
		}
		return true;
	}
	private boolean COMMENTCODE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 8:
			return  _input.LA(2) != '>' ;
		}
		return true;
	}
	private boolean SCRIPT_MACROCODE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return  _input.LA(2) != '#' ;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2$\u013e\b\1\b\1\b"+
		"\1\b\1\b\1\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b"+
		"\4\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t"+
		"\20\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t"+
		"\27\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t"+
		"\36\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\5\3U\n\3\3\3\3\3\3\4\3\4\3\4\6\4\\\n\4\r\4\16\4]\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\6\f\u0095\n"+
		"\f\r\f\16\f\u0096\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\6\16\u00a1\n\16\r"+
		"\16\16\16\u00a2\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\6\20\u00ad\n\20"+
		"\r\20\16\20\u00ae\3\20\3\20\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3"+
		"\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3\"\3"+
		"\"\3\"\3#\3#\3#\7#\u0133\n#\f#\16#\u0136\13#\3#\3#\3$\3$\3$\3$\3$\3\u0134"+
		"\2%\b\23\n\34\f\33\16\24\20\25\22\26\24\27\26\30\30\31\32\35\34\32\36"+
		"\36 \37\" $!&\"(\3*\4,\5.\6\60\7\62\b\64\t\66\n8\13:\f<\r>\16@\17B\20"+
		"D\21F#H\2J\22L$\b\2\3\4\5\6\7\b\5\2\13\f\17\17\"\"\3\2>>\7\2//\61\61?"+
		"?BBuu\3\2%%\4\2\f\f\17\17\4\2\13\13\"\"\u0142\2\b\3\2\2\2\2\n\3\2\2\2"+
		"\2\f\3\2\2\2\3\16\3\2\2\2\3\20\3\2\2\2\3\22\3\2\2\2\3\24\3\2\2\2\3\26"+
		"\3\2\2\2\3\30\3\2\2\2\3\32\3\2\2\2\4\34\3\2\2\2\4\36\3\2\2\2\5 \3\2\2"+
		"\2\5\"\3\2\2\2\6$\3\2\2\2\7&\3\2\2\2\7(\3\2\2\2\7*\3\2\2\2\7,\3\2\2\2"+
		"\7.\3\2\2\2\7\60\3\2\2\2\7\62\3\2\2\2\7\64\3\2\2\2\7\66\3\2\2\2\78\3\2"+
		"\2\2\7:\3\2\2\2\7<\3\2\2\2\7>\3\2\2\2\7@\3\2\2\2\7B\3\2\2\2\7D\3\2\2\2"+
		"\7F\3\2\2\2\7J\3\2\2\2\7L\3\2\2\2\bN\3\2\2\2\nT\3\2\2\2\f[\3\2\2\2\16"+
		"_\3\2\2\2\20b\3\2\2\2\22g\3\2\2\2\24l\3\2\2\2\26r\3\2\2\2\30~\3\2\2\2"+
		"\32\u008b\3\2\2\2\34\u0094\3\2\2\2\36\u0098\3\2\2\2 \u00a0\3\2\2\2\"\u00a4"+
		"\3\2\2\2$\u00ac\3\2\2\2&\u00b2\3\2\2\2(\u00b4\3\2\2\2*\u00c1\3\2\2\2,"+
		"\u00ca\3\2\2\2.\u00ce\3\2\2\2\60\u00d5\3\2\2\2\62\u00dd\3\2\2\2\64\u00e5"+
		"\3\2\2\2\66\u00ee\3\2\2\28\u00fd\3\2\2\2:\u0109\3\2\2\2<\u010e\3\2\2\2"+
		">\u0113\3\2\2\2@\u0117\3\2\2\2B\u011e\3\2\2\2D\u0125\3\2\2\2F\u012a\3"+
		"\2\2\2H\u012c\3\2\2\2J\u012f\3\2\2\2L\u0139\3\2\2\2NO\7>\2\2OP\7%\2\2"+
		"PQ\3\2\2\2QR\b\2\2\2R\t\3\2\2\2SU\t\2\2\2TS\3\2\2\2UV\3\2\2\2VW\b\3\3"+
		"\2W\13\3\2\2\2XY\6\4\2\2Y\\\7>\2\2Z\\\n\3\2\2[X\3\2\2\2[Z\3\2\2\2\\]\3"+
		"\2\2\2][\3\2\2\2]^\3\2\2\2^\r\3\2\2\2_`\7%\2\2`a\7@\2\2a\17\3\2\2\2bc"+
		"\6\6\3\2cd\7?\2\2de\3\2\2\2ef\b\6\4\2f\21\3\2\2\2gh\6\7\4\2hi\7B\2\2i"+
		"j\3\2\2\2jk\b\7\5\2k\23\3\2\2\2lm\6\b\5\2mn\7/\2\2no\7/\2\2op\3\2\2\2"+
		"pq\b\b\6\2q\25\3\2\2\2rs\6\t\6\2st\7u\2\2tu\7e\2\2uv\7t\2\2vw\7k\2\2w"+
		"x\7r\2\2xy\7v\2\2yz\3\2\2\2z{\5\16\5\2{|\3\2\2\2|}\b\t\7\2}\27\3\2\2\2"+
		"~\177\6\n\7\2\177\u0080\7\61\2\2\u0080\u0081\7u\2\2\u0081\u0082\7e\2\2"+
		"\u0082\u0083\7t\2\2\u0083\u0084\7k\2\2\u0084\u0085\7r\2\2\u0085\u0086"+
		"\7v\2\2\u0086\u0087\3\2\2\2\u0087\u0088\5\16\5\2\u0088\u0089\3\2\2\2\u0089"+
		"\u008a\b\n\b\2\u008a\31\3\2\2\2\u008b\u008c\6\13\b\2\u008c\u008d\n\4\2"+
		"\2\u008d\u008e\3\2\2\2\u008e\u008f\b\13\4\2\u008f\33\3\2\2\2\u0090\u0091"+
		"\6\f\t\2\u0091\u0095\7%\2\2\u0092\u0095\n\5\2\2\u0093\u0095\t\6\2\2\u0094"+
		"\u0090\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095\u0096\3\2"+
		"\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\35\3\2\2\2\u0098\u0099"+
		"\7%\2\2\u0099\u009a\7@\2\2\u009a\u009b\3\2\2\2\u009b\u009c\b\r\b\2\u009c"+
		"\37\3\2\2\2\u009d\u009e\6\16\n\2\u009e\u00a1\7%\2\2\u009f\u00a1\n\5\2"+
		"\2\u00a0\u009d\3\2\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0"+
		"\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3!\3\2\2\2\u00a4\u00a5\7%\2\2\u00a5\u00a6"+
		"\7@\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\b\17\b\2\u00a8#\3\2\2\2\u00a9"+
		"\u00aa\6\20\13\2\u00aa\u00ad\7>\2\2\u00ab\u00ad\n\3\2\2\u00ac\u00a9\3"+
		"\2\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b1\b\20\b\2\u00b1%\3\2\2\2"+
		"\u00b2\u00b3\7?\2\2\u00b3\'\3\2\2\2\u00b4\u00b5\7e\2\2\u00b5\u00b6\7q"+
		"\2\2\u00b6\u00b7\7f\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9\7V\2\2\u00b9\u00ba"+
		"\7g\2\2\u00ba\u00bb\7o\2\2\u00bb\u00bc\7r\2\2\u00bc\u00bd\7n\2\2\u00bd"+
		"\u00be\7c\2\2\u00be\u00bf\7v\2\2\u00bf\u00c0\7g\2\2\u00c0)\3\2\2\2\u00c1"+
		"\u00c2\7r\2\2\u00c2\u00c3\7t\2\2\u00c3\u00c4\7q\2\2\u00c4\u00c5\7r\2\2"+
		"\u00c5\u00c6\7g\2\2\u00c6\u00c7\7t\2\2\u00c7\u00c8\7v\2\2\u00c8\u00c9"+
		"\7{\2\2\u00c9+\3\2\2\2\u00ca\u00cb\7l\2\2\u00cb\u00cc\7c\2\2\u00cc\u00cd"+
		"\7t\2\2\u00cd-\3\2\2\2\u00ce\u00cf\7k\2\2\u00cf\u00d0\7o\2\2\u00d0\u00d1"+
		"\7r\2\2\u00d1\u00d2\7q\2\2\u00d2\u00d3\7t\2\2\u00d3\u00d4\7v\2\2\u00d4"+
		"/\3\2\2\2\u00d5\u00d6\7k\2\2\u00d6\u00d7\7p\2\2\u00d7\u00d8\7e\2\2\u00d8"+
		"\u00d9\7n\2\2\u00d9\u00da\7w\2\2\u00da\u00db\7f\2\2\u00db\u00dc\7g\2\2"+
		"\u00dc\61\3\2\2\2\u00dd\u00de\7g\2\2\u00de\u00df\7z\2\2\u00df\u00e0\7"+
		"v\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2\7p\2\2\u00e2\u00e3\7f\2\2\u00e3\u00e4"+
		"\7u\2\2\u00e4\63\3\2\2\2\u00e5\u00e6\7n\2\2\u00e6\u00e7\7c\2\2\u00e7\u00e8"+
		"\7p\2\2\u00e8\u00e9\7i\2\2\u00e9\u00ea\7w\2\2\u00ea\u00eb\7c\2\2\u00eb"+
		"\u00ec\7i\2\2\u00ec\u00ed\7g\2\2\u00ed\65\3\2\2\2\u00ee\u00ef\7v\2\2\u00ef"+
		"\u00f0\7c\2\2\u00f0\u00f1\7t\2\2\u00f1\u00f2\7i\2\2\u00f2\u00f3\7g\2\2"+
		"\u00f3\u00f4\7v\2\2\u00f4\u00f5\7N\2\2\u00f5\u00f6\7c\2\2\u00f6\u00f7"+
		"\7p\2\2\u00f7\u00f8\7i\2\2\u00f8\u00f9\7w\2\2\u00f9\u00fa\7c\2\2\u00fa"+
		"\u00fb\7i\2\2\u00fb\u00fc\7g\2\2\u00fc\67\3\2\2\2\u00fd\u00fe\7f\2\2\u00fe"+
		"\u00ff\7g\2\2\u00ff\u0100\7u\2\2\u0100\u0101\7e\2\2\u0101\u0102\7t\2\2"+
		"\u0102\u0103\7k\2\2\u0103\u0104\7r\2\2\u0104\u0105\7v\2\2\u0105\u0106"+
		"\7k\2\2\u0106\u0107\7q\2\2\u0107\u0108\7p\2\2\u01089\3\2\2\2\u0109\u010a"+
		"\7p\2\2\u010a\u010b\7c\2\2\u010b\u010c\7o\2\2\u010c\u010d\7g\2\2\u010d"+
		";\3\2\2\2\u010e\u010f\7h\2\2\u010f\u0110\7k\2\2\u0110\u0111\7n\2\2\u0111"+
		"\u0112\7g\2\2\u0112=\3\2\2\2\u0113\u0114\7c\2\2\u0114\u0115\7t\2\2\u0115"+
		"\u0116\7i\2\2\u0116?\3\2\2\2\u0117\u0118\7r\2\2\u0118\u0119\7c\2\2\u0119"+
		"\u011a\7t\2\2\u011a\u011b\7c\2\2\u011b\u011c\7o\2\2\u011c\u011d\7u\2\2"+
		"\u011dA\3\2\2\2\u011e\u011f\7r\2\2\u011f\u0120\7c\2\2\u0120\u0121\7t\2"+
		"\2\u0121\u0122\7g\2\2\u0122\u0123\7p\2\2\u0123\u0124\7v\2\2\u0124C\3\2"+
		"\2\2\u0125\u0126\7v\2\2\u0126\u0127\7{\2\2\u0127\u0128\7r\2\2\u0128\u0129"+
		"\7g\2\2\u0129E\3\2\2\2\u012a\u012b\t\7\2\2\u012bG\3\2\2\2\u012c\u012d"+
		"\7^\2\2\u012d\u012e\7$\2\2\u012eI\3\2\2\2\u012f\u0134\7$\2\2\u0130\u0133"+
		"\5H\"\2\u0131\u0133\n\6\2\2\u0132\u0130\3\2\2\2\u0132\u0131\3\2\2\2\u0133"+
		"\u0136\3\2\2\2\u0134\u0135\3\2\2\2\u0134\u0132\3\2\2\2\u0135\u0137\3\2"+
		"\2\2\u0136\u0134\3\2\2\2\u0137\u0138\7$\2\2\u0138K\3\2\2\2\u0139\u013a"+
		"\7%\2\2\u013a\u013b\7@\2\2\u013b\u013c\3\2\2\2\u013c\u013d\b$\b\2\u013d"+
		"M\3\2\2\2\23\2\3\4\5\6\7T[]\u0094\u0096\u00a0\u00a2\u00ac\u00ae\u0132"+
		"\u0134\t\4\3\2\2\3\2\4\4\2\4\7\2\4\5\2\4\6\2\4\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}