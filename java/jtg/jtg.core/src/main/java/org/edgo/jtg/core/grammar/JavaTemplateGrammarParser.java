// Generated from ..\grammar\JavaTemplateGrammar.g4 by ANTLR 4.5.3


package org.edgo.jtg.core.grammar;

import org.edgo.jtg.core.model.ParsedUnit;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JavaTemplateGrammarParser extends Parser {
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
		MACROCODE=24, TARGETCODE=25, NEWLINE=26, MACRO_BLOCK_BEGIN=27, MACRO_END=28, 
		COMMENTCODE=29, COMMENT_END=30, SCRIPT_MACROCODE=31, ASSIGN=32, WS_DIR=33, 
		DIRECTIVE_END=34;
	public static final int
		RULE_template = 0, RULE_directive_template = 1, RULE_directive_import = 2, 
		RULE_directive_include = 3, RULE_directive_extends = 4, RULE_directive_jar = 5, 
		RULE_directive_property = 6, RULE_directive_attr_language = 7, RULE_directive_attr_target_language = 8, 
		RULE_directive_attr_description = 9, RULE_directive_attr_name = 10, RULE_directive_attr_parent = 11, 
		RULE_directive_attr_type = 12, RULE_directive_attr_file = 13, RULE_directive_attr_arg = 14, 
		RULE_directive_attr_params = 15, RULE_directive_attr = 16, RULE_script = 17, 
		RULE_placeholder = 18, RULE_macrocode = 19, RULE_comment = 20, RULE_targetcode = 21;
	public static final String[] ruleNames = {
		"template", "directive_template", "directive_import", "directive_include", 
		"directive_extends", "directive_jar", "directive_property", "directive_attr_language", 
		"directive_attr_target_language", "directive_attr_description", "directive_attr_name", 
		"directive_attr_parent", "directive_attr_type", "directive_attr_file", 
		"directive_attr_arg", "directive_attr_params", "directive_attr", "script", 
		"placeholder", "macrocode", "comment", "targetcode"
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
		"SCRIPT_BEGIN", "SCRIPT_END", "MACROCODE", "TARGETCODE", "NEWLINE", "MACRO_BLOCK_BEGIN", 
		"MACRO_END", "COMMENTCODE", "COMMENT_END", "SCRIPT_MACROCODE", "ASSIGN", 
		"WS_DIR", "DIRECTIVE_END"
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

	@Override
	public String getGrammarFileName() { return "JavaTemplateGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private ParsedUnit unit;

	    public void setUnit(ParsedUnit unit) {
	        this.unit = unit;
	    }

	public JavaTemplateGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class TemplateContext extends ParserRuleContext {
		public Directive_templateContext directive_template() {
			return getRuleContext(Directive_templateContext.class,0);
		}
		public TerminalNode EOF() { return getToken(JavaTemplateGrammarParser.EOF, 0); }
		public List<Directive_jarContext> directive_jar() {
			return getRuleContexts(Directive_jarContext.class);
		}
		public Directive_jarContext directive_jar(int i) {
			return getRuleContext(Directive_jarContext.class,i);
		}
		public List<Directive_propertyContext> directive_property() {
			return getRuleContexts(Directive_propertyContext.class);
		}
		public Directive_propertyContext directive_property(int i) {
			return getRuleContext(Directive_propertyContext.class,i);
		}
		public List<Directive_importContext> directive_import() {
			return getRuleContexts(Directive_importContext.class);
		}
		public Directive_importContext directive_import(int i) {
			return getRuleContext(Directive_importContext.class,i);
		}
		public List<Directive_extendsContext> directive_extends() {
			return getRuleContexts(Directive_extendsContext.class);
		}
		public Directive_extendsContext directive_extends(int i) {
			return getRuleContext(Directive_extendsContext.class,i);
		}
		public List<TargetcodeContext> targetcode() {
			return getRuleContexts(TargetcodeContext.class);
		}
		public TargetcodeContext targetcode(int i) {
			return getRuleContext(TargetcodeContext.class,i);
		}
		public List<ScriptContext> script() {
			return getRuleContexts(ScriptContext.class);
		}
		public ScriptContext script(int i) {
			return getRuleContext(ScriptContext.class,i);
		}
		public List<PlaceholderContext> placeholder() {
			return getRuleContexts(PlaceholderContext.class);
		}
		public PlaceholderContext placeholder(int i) {
			return getRuleContext(PlaceholderContext.class,i);
		}
		public List<Directive_includeContext> directive_include() {
			return getRuleContexts(Directive_includeContext.class);
		}
		public Directive_includeContext directive_include(int i) {
			return getRuleContext(Directive_includeContext.class,i);
		}
		public List<MacrocodeContext> macrocode() {
			return getRuleContexts(MacrocodeContext.class);
		}
		public MacrocodeContext macrocode(int i) {
			return getRuleContext(MacrocodeContext.class,i);
		}
		public List<CommentContext> comment() {
			return getRuleContexts(CommentContext.class);
		}
		public CommentContext comment(int i) {
			return getRuleContext(CommentContext.class,i);
		}
		public TemplateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_template; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterTemplate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitTemplate(this);
		}
	}

	public final TemplateContext template() throws RecognitionException {
		TemplateContext _localctx = new TemplateContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_template);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			directive_template();
			setState(52);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(50);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(45);
						directive_jar();
						}
						break;
					case 2:
						{
						setState(46);
						directive_property();
						}
						break;
					case 3:
						{
						setState(47);
						directive_import();
						}
						break;
					case 4:
						{
						setState(48);
						directive_extends();
						}
						break;
					case 5:
						{
						setState(49);
						targetcode();
						}
						break;
					}
					} 
				}
				setState(54);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MACROCODE_BEGIN) | (1L << TARGETCODE) | (1L << NEWLINE))) != 0)) {
				{
				setState(61);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(55);
					script();
					}
					break;
				case 2:
					{
					setState(56);
					placeholder();
					}
					break;
				case 3:
					{
					setState(57);
					directive_include();
					}
					break;
				case 4:
					{
					setState(58);
					macrocode();
					}
					break;
				case 5:
					{
					setState(59);
					comment();
					}
					break;
				case 6:
					{
					setState(60);
					targetcode();
					}
					break;
				}
				}
				setState(65);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(66);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_templateContext extends ParserRuleContext {
		public Token v;
		public Directive_attr_languageContext lang;
		public Directive_attr_target_languageContext targetlanguage;
		public TerminalNode MACROCODE_BEGIN() { return getToken(JavaTemplateGrammarParser.MACROCODE_BEGIN, 0); }
		public TerminalNode DIRECTIVE_BEGIN() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_BEGIN, 0); }
		public TerminalNode DIRECTIVE_END() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_END, 0); }
		public TerminalNode DIRECTIVE_CMD_TEMPLATE() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_CMD_TEMPLATE, 0); }
		public Directive_attr_languageContext directive_attr_language() {
			return getRuleContext(Directive_attr_languageContext.class,0);
		}
		public Directive_attr_target_languageContext directive_attr_target_language() {
			return getRuleContext(Directive_attr_target_languageContext.class,0);
		}
		public List<TerminalNode> WS_DIR() { return getTokens(JavaTemplateGrammarParser.WS_DIR); }
		public TerminalNode WS_DIR(int i) {
			return getToken(JavaTemplateGrammarParser.WS_DIR, i);
		}
		public Directive_attr_descriptionContext directive_attr_description() {
			return getRuleContext(Directive_attr_descriptionContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(JavaTemplateGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(JavaTemplateGrammarParser.NEWLINE, i);
		}
		public Directive_templateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_template; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_template(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_template(this);
		}
	}

	public final Directive_templateContext directive_template() throws RecognitionException {
		Directive_templateContext _localctx = new Directive_templateContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_directive_template);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(MACROCODE_BEGIN);
			setState(69);
			match(DIRECTIVE_BEGIN);
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS_DIR) {
				{
				{
				setState(70);
				match(WS_DIR);
				}
				}
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(76);
			((Directive_templateContext)_localctx).v = match(DIRECTIVE_CMD_TEMPLATE);
			setState(78); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(77);
				match(WS_DIR);
				}
				}
				setState(80); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS_DIR );
			setState(82);
			((Directive_templateContext)_localctx).lang = directive_attr_language();
			setState(84); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(83);
				match(WS_DIR);
				}
				}
				setState(86); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS_DIR );
			setState(88);
			((Directive_templateContext)_localctx).targetlanguage = directive_attr_target_language();
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(90); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(89);
					match(WS_DIR);
					}
					}
					setState(92); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS_DIR );
				setState(94);
				directive_attr_description();
				}
				break;
			}
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS_DIR) {
				{
				{
				setState(97);
				match(WS_DIR);
				}
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(103);
			match(DIRECTIVE_END);
			setState(107);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(104);
					match(NEWLINE);
					}
					} 
				}
				setState(109);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}

					unit.setMacroLang(((Directive_templateContext)_localctx).lang.lang);
				  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_importContext extends ParserRuleContext {
		public Token v;
		public Directive_attr_nameContext ns;
		public TerminalNode MACROCODE_BEGIN() { return getToken(JavaTemplateGrammarParser.MACROCODE_BEGIN, 0); }
		public TerminalNode DIRECTIVE_BEGIN() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_BEGIN, 0); }
		public TerminalNode DIRECTIVE_END() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_END, 0); }
		public TerminalNode DIRECTIVE_CMD_IMPORT() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_CMD_IMPORT, 0); }
		public Directive_attr_nameContext directive_attr_name() {
			return getRuleContext(Directive_attr_nameContext.class,0);
		}
		public List<TerminalNode> WS_DIR() { return getTokens(JavaTemplateGrammarParser.WS_DIR); }
		public TerminalNode WS_DIR(int i) {
			return getToken(JavaTemplateGrammarParser.WS_DIR, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(JavaTemplateGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(JavaTemplateGrammarParser.NEWLINE, i);
		}
		public Directive_importContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_import; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_import(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_import(this);
		}
	}

	public final Directive_importContext directive_import() throws RecognitionException {
		Directive_importContext _localctx = new Directive_importContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_directive_import);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(MACROCODE_BEGIN);
			setState(113);
			match(DIRECTIVE_BEGIN);
			setState(115);
			_la = _input.LA(1);
			if (_la==WS_DIR) {
				{
				setState(114);
				match(WS_DIR);
				}
			}

			setState(117);
			((Directive_importContext)_localctx).v = match(DIRECTIVE_CMD_IMPORT);
			setState(119); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(118);
				match(WS_DIR);
				}
				}
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS_DIR );
			setState(123);
			((Directive_importContext)_localctx).ns = directive_attr_name();
			setState(125);
			_la = _input.LA(1);
			if (_la==WS_DIR) {
				{
				setState(124);
				match(WS_DIR);
				}
			}

			setState(127);
			match(DIRECTIVE_END);
			setState(131);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(128);
					match(NEWLINE);
					}
					} 
				}
				setState(133);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}

					unit.directiveImport(((Directive_importContext)_localctx).ns.name, ((Directive_importContext)_localctx).v.getLine()); 
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_includeContext extends ParserRuleContext {
		public Token v;
		public Directive_attr_fileContext file;
		public Directive_attr_paramsContext params;
		public Directive_attr_argContext arg;
		public TerminalNode MACROCODE_BEGIN() { return getToken(JavaTemplateGrammarParser.MACROCODE_BEGIN, 0); }
		public TerminalNode DIRECTIVE_BEGIN() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_BEGIN, 0); }
		public TerminalNode DIRECTIVE_END() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_END, 0); }
		public TerminalNode DIRECTIVE_CMD_INCLUDE() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_CMD_INCLUDE, 0); }
		public Directive_attr_fileContext directive_attr_file() {
			return getRuleContext(Directive_attr_fileContext.class,0);
		}
		public List<TerminalNode> WS_DIR() { return getTokens(JavaTemplateGrammarParser.WS_DIR); }
		public TerminalNode WS_DIR(int i) {
			return getToken(JavaTemplateGrammarParser.WS_DIR, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(JavaTemplateGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(JavaTemplateGrammarParser.NEWLINE, i);
		}
		public Directive_attr_paramsContext directive_attr_params() {
			return getRuleContext(Directive_attr_paramsContext.class,0);
		}
		public Directive_attr_argContext directive_attr_arg() {
			return getRuleContext(Directive_attr_argContext.class,0);
		}
		public Directive_includeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_include; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_include(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_include(this);
		}
	}

	public final Directive_includeContext directive_include() throws RecognitionException {
		Directive_includeContext _localctx = new Directive_includeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_directive_include);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(MACROCODE_BEGIN);
			setState(137);
			match(DIRECTIVE_BEGIN);
			setState(139);
			_la = _input.LA(1);
			if (_la==WS_DIR) {
				{
				setState(138);
				match(WS_DIR);
				}
			}

			setState(141);
			((Directive_includeContext)_localctx).v = match(DIRECTIVE_CMD_INCLUDE);
			setState(143); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(142);
				match(WS_DIR);
				}
				}
				setState(145); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS_DIR );
			setState(147);
			((Directive_includeContext)_localctx).file = directive_attr_file();
			setState(154);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(149); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(148);
					match(WS_DIR);
					}
					}
					setState(151); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS_DIR );
				setState(153);
				((Directive_includeContext)_localctx).params = directive_attr_params();
				}
				break;
			}
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(157); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(156);
					match(WS_DIR);
					}
					}
					setState(159); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS_DIR );
				setState(161);
				((Directive_includeContext)_localctx).arg = directive_attr_arg();
				}
				break;
			}
			setState(165);
			_la = _input.LA(1);
			if (_la==WS_DIR) {
				{
				setState(164);
				match(WS_DIR);
				}
			}

			setState(167);
			match(DIRECTIVE_END);
			setState(171);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(168);
					match(NEWLINE);
					}
					} 
				}
				setState(173);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			}

			      	String params = null;
			      	if (_localctx.params != null) {
			      		params = _localctx.params.params;
			      	}
			      	String arg = null;
			      	if (_localctx.arg != null) {
			      		arg = _localctx.arg.arg;
			      	}
					unit.addInclude(((Directive_includeContext)_localctx).v, ((Directive_includeContext)_localctx).file.file, params, arg); 
				  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_extendsContext extends ParserRuleContext {
		public Token v;
		public Directive_attr_parentContext parent;
		public TerminalNode MACROCODE_BEGIN() { return getToken(JavaTemplateGrammarParser.MACROCODE_BEGIN, 0); }
		public TerminalNode DIRECTIVE_BEGIN() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_BEGIN, 0); }
		public TerminalNode DIRECTIVE_END() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_END, 0); }
		public TerminalNode DIRECTIVE_CMD_EXTENDS() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_CMD_EXTENDS, 0); }
		public Directive_attr_parentContext directive_attr_parent() {
			return getRuleContext(Directive_attr_parentContext.class,0);
		}
		public List<TerminalNode> WS_DIR() { return getTokens(JavaTemplateGrammarParser.WS_DIR); }
		public TerminalNode WS_DIR(int i) {
			return getToken(JavaTemplateGrammarParser.WS_DIR, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(JavaTemplateGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(JavaTemplateGrammarParser.NEWLINE, i);
		}
		public Directive_extendsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_extends; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_extends(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_extends(this);
		}
	}

	public final Directive_extendsContext directive_extends() throws RecognitionException {
		Directive_extendsContext _localctx = new Directive_extendsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_directive_extends);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(MACROCODE_BEGIN);
			setState(177);
			match(DIRECTIVE_BEGIN);
			setState(179);
			_la = _input.LA(1);
			if (_la==WS_DIR) {
				{
				setState(178);
				match(WS_DIR);
				}
			}

			setState(181);
			((Directive_extendsContext)_localctx).v = match(DIRECTIVE_CMD_EXTENDS);
			setState(183); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(182);
				match(WS_DIR);
				}
				}
				setState(185); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS_DIR );
			setState(187);
			((Directive_extendsContext)_localctx).parent = directive_attr_parent();
			setState(189);
			_la = _input.LA(1);
			if (_la==WS_DIR) {
				{
				setState(188);
				match(WS_DIR);
				}
			}

			setState(191);
			match(DIRECTIVE_END);
			setState(195);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(192);
					match(NEWLINE);
					}
					} 
				}
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			}

					unit.directiveExtends(((Directive_extendsContext)_localctx).parent.parent, ((Directive_extendsContext)_localctx).v.getLine()); 
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_jarContext extends ParserRuleContext {
		public Token v;
		public Directive_attr_nameContext name;
		public TerminalNode MACROCODE_BEGIN() { return getToken(JavaTemplateGrammarParser.MACROCODE_BEGIN, 0); }
		public TerminalNode DIRECTIVE_BEGIN() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_BEGIN, 0); }
		public TerminalNode DIRECTIVE_END() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_END, 0); }
		public TerminalNode DIRECTIVE_CMD_JAR() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_CMD_JAR, 0); }
		public Directive_attr_nameContext directive_attr_name() {
			return getRuleContext(Directive_attr_nameContext.class,0);
		}
		public List<TerminalNode> WS_DIR() { return getTokens(JavaTemplateGrammarParser.WS_DIR); }
		public TerminalNode WS_DIR(int i) {
			return getToken(JavaTemplateGrammarParser.WS_DIR, i);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(JavaTemplateGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(JavaTemplateGrammarParser.NEWLINE, i);
		}
		public Directive_jarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_jar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_jar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_jar(this);
		}
	}

	public final Directive_jarContext directive_jar() throws RecognitionException {
		Directive_jarContext _localctx = new Directive_jarContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_directive_jar);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(200);
			match(MACROCODE_BEGIN);
			setState(201);
			match(DIRECTIVE_BEGIN);
			setState(203);
			_la = _input.LA(1);
			if (_la==WS_DIR) {
				{
				setState(202);
				match(WS_DIR);
				}
			}

			setState(205);
			((Directive_jarContext)_localctx).v = match(DIRECTIVE_CMD_JAR);
			setState(207); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(206);
				match(WS_DIR);
				}
				}
				setState(209); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS_DIR );
			setState(211);
			((Directive_jarContext)_localctx).name = directive_attr_name();
			setState(213);
			_la = _input.LA(1);
			if (_la==WS_DIR) {
				{
				setState(212);
				match(WS_DIR);
				}
			}

			setState(215);
			match(DIRECTIVE_END);
			setState(219);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(216);
					match(NEWLINE);
					}
					} 
				}
				setState(221);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}

					unit.directiveJar(((Directive_jarContext)_localctx).name.name);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_propertyContext extends ParserRuleContext {
		public Token v;
		public Directive_attr_nameContext name;
		public Directive_attr_typeContext type;
		public TerminalNode MACROCODE_BEGIN() { return getToken(JavaTemplateGrammarParser.MACROCODE_BEGIN, 0); }
		public TerminalNode DIRECTIVE_BEGIN() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_BEGIN, 0); }
		public TerminalNode DIRECTIVE_END() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_END, 0); }
		public TerminalNode DIRECTIVE_CMD_PROPERTY() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_CMD_PROPERTY, 0); }
		public Directive_attr_nameContext directive_attr_name() {
			return getRuleContext(Directive_attr_nameContext.class,0);
		}
		public Directive_attr_typeContext directive_attr_type() {
			return getRuleContext(Directive_attr_typeContext.class,0);
		}
		public List<TerminalNode> WS_DIR() { return getTokens(JavaTemplateGrammarParser.WS_DIR); }
		public TerminalNode WS_DIR(int i) {
			return getToken(JavaTemplateGrammarParser.WS_DIR, i);
		}
		public Directive_attr_descriptionContext directive_attr_description() {
			return getRuleContext(Directive_attr_descriptionContext.class,0);
		}
		public List<TerminalNode> NEWLINE() { return getTokens(JavaTemplateGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(JavaTemplateGrammarParser.NEWLINE, i);
		}
		public Directive_propertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_property; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_property(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_property(this);
		}
	}

	public final Directive_propertyContext directive_property() throws RecognitionException {
		Directive_propertyContext _localctx = new Directive_propertyContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_directive_property);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(MACROCODE_BEGIN);
			setState(225);
			match(DIRECTIVE_BEGIN);
			setState(227);
			_la = _input.LA(1);
			if (_la==WS_DIR) {
				{
				setState(226);
				match(WS_DIR);
				}
			}

			setState(229);
			((Directive_propertyContext)_localctx).v = match(DIRECTIVE_CMD_PROPERTY);
			setState(231); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(230);
				match(WS_DIR);
				}
				}
				setState(233); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS_DIR );
			setState(235);
			((Directive_propertyContext)_localctx).name = directive_attr_name();
			setState(237); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(236);
				match(WS_DIR);
				}
				}
				setState(239); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS_DIR );
			setState(241);
			((Directive_propertyContext)_localctx).type = directive_attr_type();
			setState(248);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(243); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(242);
					match(WS_DIR);
					}
					}
					setState(245); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS_DIR );
				setState(247);
				directive_attr_description();
				}
				break;
			}
			setState(251);
			_la = _input.LA(1);
			if (_la==WS_DIR) {
				{
				setState(250);
				match(WS_DIR);
				}
			}

			setState(253);
			match(DIRECTIVE_END);
			setState(257);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(254);
					match(NEWLINE);
					}
					} 
				}
				setState(259);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}

					unit.directiveArguments(((Directive_propertyContext)_localctx).name.name, ((Directive_propertyContext)_localctx).type.type, ((Directive_propertyContext)_localctx).v.getLine());
				  
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_attr_languageContext extends ParserRuleContext {
		public String lang;
		public Directive_attrContext l;
		public TerminalNode DIRECTIVE_ATTR_LANGUAGE() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_ATTR_LANGUAGE, 0); }
		public Directive_attrContext directive_attr() {
			return getRuleContext(Directive_attrContext.class,0);
		}
		public Directive_attr_languageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_attr_language; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_attr_language(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_attr_language(this);
		}
	}

	public final Directive_attr_languageContext directive_attr_language() throws RecognitionException {
		Directive_attr_languageContext _localctx = new Directive_attr_languageContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_directive_attr_language);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(DIRECTIVE_ATTR_LANGUAGE);
			setState(263);
			((Directive_attr_languageContext)_localctx).l = directive_attr();
			 _localctx.lang = _localctx.l.value; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_attr_target_languageContext extends ParserRuleContext {
		public String lang;
		public Directive_attrContext l;
		public TerminalNode DIRECTIVE_ATTR_TARGET_LANGUAGE() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_ATTR_TARGET_LANGUAGE, 0); }
		public Directive_attrContext directive_attr() {
			return getRuleContext(Directive_attrContext.class,0);
		}
		public Directive_attr_target_languageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_attr_target_language; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_attr_target_language(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_attr_target_language(this);
		}
	}

	public final Directive_attr_target_languageContext directive_attr_target_language() throws RecognitionException {
		Directive_attr_target_languageContext _localctx = new Directive_attr_target_languageContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_directive_attr_target_language);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			match(DIRECTIVE_ATTR_TARGET_LANGUAGE);
			setState(267);
			((Directive_attr_target_languageContext)_localctx).l = directive_attr();
			 _localctx.lang = _localctx.l.value; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_attr_descriptionContext extends ParserRuleContext {
		public TerminalNode DIRECTIVE_ATTR_DESCRIPTION() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_ATTR_DESCRIPTION, 0); }
		public Directive_attrContext directive_attr() {
			return getRuleContext(Directive_attrContext.class,0);
		}
		public Directive_attr_descriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_attr_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_attr_description(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_attr_description(this);
		}
	}

	public final Directive_attr_descriptionContext directive_attr_description() throws RecognitionException {
		Directive_attr_descriptionContext _localctx = new Directive_attr_descriptionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_directive_attr_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			match(DIRECTIVE_ATTR_DESCRIPTION);
			setState(271);
			directive_attr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_attr_nameContext extends ParserRuleContext {
		public String name;
		public Directive_attrContext n;
		public TerminalNode DIRECTIVE_ATTR_NAME() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_ATTR_NAME, 0); }
		public Directive_attrContext directive_attr() {
			return getRuleContext(Directive_attrContext.class,0);
		}
		public Directive_attr_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_attr_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_attr_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_attr_name(this);
		}
	}

	public final Directive_attr_nameContext directive_attr_name() throws RecognitionException {
		Directive_attr_nameContext _localctx = new Directive_attr_nameContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_directive_attr_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(DIRECTIVE_ATTR_NAME);
			setState(274);
			((Directive_attr_nameContext)_localctx).n = directive_attr();
			 _localctx.name = _localctx.n.value; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_attr_parentContext extends ParserRuleContext {
		public String parent;
		public Directive_attrContext p;
		public TerminalNode DIRECTIVE_ATTR_PARENT() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_ATTR_PARENT, 0); }
		public Directive_attrContext directive_attr() {
			return getRuleContext(Directive_attrContext.class,0);
		}
		public Directive_attr_parentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_attr_parent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_attr_parent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_attr_parent(this);
		}
	}

	public final Directive_attr_parentContext directive_attr_parent() throws RecognitionException {
		Directive_attr_parentContext _localctx = new Directive_attr_parentContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_directive_attr_parent);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(DIRECTIVE_ATTR_PARENT);
			setState(278);
			((Directive_attr_parentContext)_localctx).p = directive_attr();
			 _localctx.parent = _localctx.p.value; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_attr_typeContext extends ParserRuleContext {
		public String type;
		public Directive_attrContext t;
		public TerminalNode DIRECTIVE_ATTR_TYPE() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_ATTR_TYPE, 0); }
		public Directive_attrContext directive_attr() {
			return getRuleContext(Directive_attrContext.class,0);
		}
		public Directive_attr_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_attr_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_attr_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_attr_type(this);
		}
	}

	public final Directive_attr_typeContext directive_attr_type() throws RecognitionException {
		Directive_attr_typeContext _localctx = new Directive_attr_typeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_directive_attr_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			match(DIRECTIVE_ATTR_TYPE);
			setState(282);
			((Directive_attr_typeContext)_localctx).t = directive_attr();
			 _localctx.type = _localctx.t.value; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_attr_fileContext extends ParserRuleContext {
		public String file;
		public Directive_attrContext f;
		public TerminalNode DIRECTIVE_ATTR_FILE() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_ATTR_FILE, 0); }
		public Directive_attrContext directive_attr() {
			return getRuleContext(Directive_attrContext.class,0);
		}
		public Directive_attr_fileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_attr_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_attr_file(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_attr_file(this);
		}
	}

	public final Directive_attr_fileContext directive_attr_file() throws RecognitionException {
		Directive_attr_fileContext _localctx = new Directive_attr_fileContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_directive_attr_file);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(DIRECTIVE_ATTR_FILE);
			setState(286);
			((Directive_attr_fileContext)_localctx).f = directive_attr();
			 _localctx.file = _localctx.f.value; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_attr_argContext extends ParserRuleContext {
		public String arg;
		public Directive_attrContext a;
		public TerminalNode DIRECTIVE_ATTR_ARG() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_ATTR_ARG, 0); }
		public Directive_attrContext directive_attr() {
			return getRuleContext(Directive_attrContext.class,0);
		}
		public Directive_attr_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_attr_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_attr_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_attr_arg(this);
		}
	}

	public final Directive_attr_argContext directive_attr_arg() throws RecognitionException {
		Directive_attr_argContext _localctx = new Directive_attr_argContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_directive_attr_arg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(289);
			match(DIRECTIVE_ATTR_ARG);
			setState(290);
			((Directive_attr_argContext)_localctx).a = directive_attr();
			 _localctx.arg = _localctx.a.value; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_attr_paramsContext extends ParserRuleContext {
		public String params;
		public Directive_attrContext p;
		public TerminalNode DIRECTIVE_ATTR_PARAMS() { return getToken(JavaTemplateGrammarParser.DIRECTIVE_ATTR_PARAMS, 0); }
		public Directive_attrContext directive_attr() {
			return getRuleContext(Directive_attrContext.class,0);
		}
		public Directive_attr_paramsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_attr_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_attr_params(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_attr_params(this);
		}
	}

	public final Directive_attr_paramsContext directive_attr_params() throws RecognitionException {
		Directive_attr_paramsContext _localctx = new Directive_attr_paramsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_directive_attr_params);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(DIRECTIVE_ATTR_PARAMS);
			setState(294);
			((Directive_attr_paramsContext)_localctx).p = directive_attr();
			 _localctx.params = _localctx.p.value; 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Directive_attrContext extends ParserRuleContext {
		public String value;
		public Token v;
		public TerminalNode ASSIGN() { return getToken(JavaTemplateGrammarParser.ASSIGN, 0); }
		public TerminalNode STRING_VALUE() { return getToken(JavaTemplateGrammarParser.STRING_VALUE, 0); }
		public Directive_attrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_directive_attr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterDirective_attr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitDirective_attr(this);
		}
	}

	public final Directive_attrContext directive_attr() throws RecognitionException {
		Directive_attrContext _localctx = new Directive_attrContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_directive_attr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297);
			match(ASSIGN);
			setState(298);
			((Directive_attrContext)_localctx).v = match(STRING_VALUE);

					String value = _localctx.v.getText();
			        if (value.startsWith("\"")) {
			            value = value.substring(1);
			        }
			        if (value.endsWith("\"")) {
			            value = value.substring(0, value.length() - 1);
			        }
					_localctx.value = value;
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScriptContext extends ParserRuleContext {
		public Token v;
		public List<TerminalNode> MACROCODE_BEGIN() { return getTokens(JavaTemplateGrammarParser.MACROCODE_BEGIN); }
		public TerminalNode MACROCODE_BEGIN(int i) {
			return getToken(JavaTemplateGrammarParser.MACROCODE_BEGIN, i);
		}
		public TerminalNode SCRIPT_BEGIN() { return getToken(JavaTemplateGrammarParser.SCRIPT_BEGIN, 0); }
		public TerminalNode SCRIPT_END() { return getToken(JavaTemplateGrammarParser.SCRIPT_END, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(JavaTemplateGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(JavaTemplateGrammarParser.NEWLINE, i);
		}
		public TerminalNode SCRIPT_MACROCODE() { return getToken(JavaTemplateGrammarParser.SCRIPT_MACROCODE, 0); }
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitScript(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_script);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
			match(MACROCODE_BEGIN);
			setState(302);
			match(SCRIPT_BEGIN);
			setState(305);
			_la = _input.LA(1);
			if (_la==SCRIPT_MACROCODE) {
				{
				setState(303);
				((ScriptContext)_localctx).v = match(SCRIPT_MACROCODE);
				 
						  unit.addScript(((ScriptContext)_localctx).v); 
					    
				}
			}

			setState(307);
			match(MACROCODE_BEGIN);
			setState(308);
			match(SCRIPT_END);
			setState(312);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(309);
					match(NEWLINE);
					}
					} 
				}
				setState(314);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PlaceholderContext extends ParserRuleContext {
		public Token v;
		public TerminalNode MACROCODE_BEGIN() { return getToken(JavaTemplateGrammarParser.MACROCODE_BEGIN, 0); }
		public TerminalNode PLACEHOLDER_BEGIN() { return getToken(JavaTemplateGrammarParser.PLACEHOLDER_BEGIN, 0); }
		public TerminalNode MACRO_END() { return getToken(JavaTemplateGrammarParser.MACRO_END, 0); }
		public TerminalNode MACROCODE() { return getToken(JavaTemplateGrammarParser.MACROCODE, 0); }
		public PlaceholderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_placeholder; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterPlaceholder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitPlaceholder(this);
		}
	}

	public final PlaceholderContext placeholder() throws RecognitionException {
		PlaceholderContext _localctx = new PlaceholderContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_placeholder);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(315);
			match(MACROCODE_BEGIN);
			setState(316);
			match(PLACEHOLDER_BEGIN);
			setState(317);
			((PlaceholderContext)_localctx).v = match(MACROCODE);
			setState(318);
			match(MACRO_END);

					unit.addPlaceholder(((PlaceholderContext)_localctx).v);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MacrocodeContext extends ParserRuleContext {
		public Token v1;
		public Token v2;
		public TerminalNode MACROCODE_BEGIN() { return getToken(JavaTemplateGrammarParser.MACROCODE_BEGIN, 0); }
		public TerminalNode MACRO_END() { return getToken(JavaTemplateGrammarParser.MACRO_END, 0); }
		public TerminalNode MACRO_BLOCK_BEGIN() { return getToken(JavaTemplateGrammarParser.MACRO_BLOCK_BEGIN, 0); }
		public TerminalNode MACROCODE() { return getToken(JavaTemplateGrammarParser.MACROCODE, 0); }
		public MacrocodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_macrocode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterMacrocode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitMacrocode(this);
		}
	}

	public final MacrocodeContext macrocode() throws RecognitionException {
		MacrocodeContext _localctx = new MacrocodeContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_macrocode);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(321);
			match(MACROCODE_BEGIN);
			{
			setState(322);
			((MacrocodeContext)_localctx).v1 = match(MACRO_BLOCK_BEGIN);
			setState(324);
			_la = _input.LA(1);
			if (_la==MACROCODE) {
				{
				setState(323);
				((MacrocodeContext)_localctx).v2 = match(MACROCODE);
				}
			}


					unit.addMacrocode(((MacrocodeContext)_localctx).v1, ((MacrocodeContext)_localctx).v2);
				
			}
			setState(328);
			match(MACRO_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public Token v;
		public TerminalNode MACROCODE_BEGIN() { return getToken(JavaTemplateGrammarParser.MACROCODE_BEGIN, 0); }
		public TerminalNode COMMENT_BEGIN() { return getToken(JavaTemplateGrammarParser.COMMENT_BEGIN, 0); }
		public TerminalNode COMMENT_END() { return getToken(JavaTemplateGrammarParser.COMMENT_END, 0); }
		public TerminalNode COMMENTCODE() { return getToken(JavaTemplateGrammarParser.COMMENTCODE, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterComment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitComment(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_comment);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			match(MACROCODE_BEGIN);
			setState(331);
			match(COMMENT_BEGIN);
			setState(333);
			_la = _input.LA(1);
			if (_la==COMMENTCODE) {
				{
				setState(332);
				((CommentContext)_localctx).v = match(COMMENTCODE);
				}
			}

			setState(335);
			match(COMMENT_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TargetcodeContext extends ParserRuleContext {
		public Token v;
		public TerminalNode TARGETCODE() { return getToken(JavaTemplateGrammarParser.TARGETCODE, 0); }
		public List<TerminalNode> NEWLINE() { return getTokens(JavaTemplateGrammarParser.NEWLINE); }
		public TerminalNode NEWLINE(int i) {
			return getToken(JavaTemplateGrammarParser.NEWLINE, i);
		}
		public TargetcodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_targetcode; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).enterTargetcode(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaTemplateGrammarListener ) ((JavaTemplateGrammarListener)listener).exitTargetcode(this);
		}
	}

	public final TargetcodeContext targetcode() throws RecognitionException {
		TargetcodeContext _localctx = new TargetcodeContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_targetcode);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEWLINE) {
				{
				{
				setState(337);
				match(NEWLINE);
				}
				}
				setState(342);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(343);
			((TargetcodeContext)_localctx).v = match(TARGETCODE);

						unit.addTargetcode(((TargetcodeContext)_localctx).v); 
					
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3$\u015d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\7\2\65\n\2\f\2\16\28\13\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2@\n\2\f\2\16"+
		"\2C\13\2\3\2\3\2\3\3\3\3\3\3\7\3J\n\3\f\3\16\3M\13\3\3\3\3\3\6\3Q\n\3"+
		"\r\3\16\3R\3\3\3\3\6\3W\n\3\r\3\16\3X\3\3\3\3\6\3]\n\3\r\3\16\3^\3\3\5"+
		"\3b\n\3\3\3\7\3e\n\3\f\3\16\3h\13\3\3\3\3\3\7\3l\n\3\f\3\16\3o\13\3\3"+
		"\3\3\3\3\4\3\4\3\4\5\4v\n\4\3\4\3\4\6\4z\n\4\r\4\16\4{\3\4\3\4\5\4\u0080"+
		"\n\4\3\4\3\4\7\4\u0084\n\4\f\4\16\4\u0087\13\4\3\4\3\4\3\5\3\5\3\5\5\5"+
		"\u008e\n\5\3\5\3\5\6\5\u0092\n\5\r\5\16\5\u0093\3\5\3\5\6\5\u0098\n\5"+
		"\r\5\16\5\u0099\3\5\5\5\u009d\n\5\3\5\6\5\u00a0\n\5\r\5\16\5\u00a1\3\5"+
		"\5\5\u00a5\n\5\3\5\5\5\u00a8\n\5\3\5\3\5\7\5\u00ac\n\5\f\5\16\5\u00af"+
		"\13\5\3\5\3\5\3\6\3\6\3\6\5\6\u00b6\n\6\3\6\3\6\6\6\u00ba\n\6\r\6\16\6"+
		"\u00bb\3\6\3\6\5\6\u00c0\n\6\3\6\3\6\7\6\u00c4\n\6\f\6\16\6\u00c7\13\6"+
		"\3\6\3\6\3\7\3\7\3\7\5\7\u00ce\n\7\3\7\3\7\6\7\u00d2\n\7\r\7\16\7\u00d3"+
		"\3\7\3\7\5\7\u00d8\n\7\3\7\3\7\7\7\u00dc\n\7\f\7\16\7\u00df\13\7\3\7\3"+
		"\7\3\b\3\b\3\b\5\b\u00e6\n\b\3\b\3\b\6\b\u00ea\n\b\r\b\16\b\u00eb\3\b"+
		"\3\b\6\b\u00f0\n\b\r\b\16\b\u00f1\3\b\3\b\6\b\u00f6\n\b\r\b\16\b\u00f7"+
		"\3\b\5\b\u00fb\n\b\3\b\5\b\u00fe\n\b\3\b\3\b\7\b\u0102\n\b\f\b\16\b\u0105"+
		"\13\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\5\23\u0134\n\23\3\23\3\23\3\23\7\23\u0139\n\23\f\23\16\23\u013c\13"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\5\25\u0147\n\25\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\5\26\u0150\n\26\3\26\3\26\3\27\7\27\u0155"+
		"\n\27\f\27\16\27\u0158\13\27\3\27\3\27\3\27\3\27\2\2\30\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"$&(*,\2\2\u0178\2.\3\2\2\2\4F\3\2\2\2\6r\3"+
		"\2\2\2\b\u008a\3\2\2\2\n\u00b2\3\2\2\2\f\u00ca\3\2\2\2\16\u00e2\3\2\2"+
		"\2\20\u0108\3\2\2\2\22\u010c\3\2\2\2\24\u0110\3\2\2\2\26\u0113\3\2\2\2"+
		"\30\u0117\3\2\2\2\32\u011b\3\2\2\2\34\u011f\3\2\2\2\36\u0123\3\2\2\2 "+
		"\u0127\3\2\2\2\"\u012b\3\2\2\2$\u012f\3\2\2\2&\u013d\3\2\2\2(\u0143\3"+
		"\2\2\2*\u014c\3\2\2\2,\u0156\3\2\2\2.\66\5\4\3\2/\65\5\f\7\2\60\65\5\16"+
		"\b\2\61\65\5\6\4\2\62\65\5\n\6\2\63\65\5,\27\2\64/\3\2\2\2\64\60\3\2\2"+
		"\2\64\61\3\2\2\2\64\62\3\2\2\2\64\63\3\2\2\2\658\3\2\2\2\66\64\3\2\2\2"+
		"\66\67\3\2\2\2\67A\3\2\2\28\66\3\2\2\29@\5$\23\2:@\5&\24\2;@\5\b\5\2<"+
		"@\5(\25\2=@\5*\26\2>@\5,\27\2?9\3\2\2\2?:\3\2\2\2?;\3\2\2\2?<\3\2\2\2"+
		"?=\3\2\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2"+
		"DE\7\2\2\3E\3\3\2\2\2FG\7\23\2\2GK\7\26\2\2HJ\7#\2\2IH\3\2\2\2JM\3\2\2"+
		"\2KI\3\2\2\2KL\3\2\2\2LN\3\2\2\2MK\3\2\2\2NP\7\3\2\2OQ\7#\2\2PO\3\2\2"+
		"\2QR\3\2\2\2RP\3\2\2\2RS\3\2\2\2ST\3\2\2\2TV\5\20\t\2UW\7#\2\2VU\3\2\2"+
		"\2WX\3\2\2\2XV\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Za\5\22\n\2[]\7#\2\2\\[\3\2"+
		"\2\2]^\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_`\3\2\2\2`b\5\24\13\2a\\\3\2\2\2a"+
		"b\3\2\2\2bf\3\2\2\2ce\7#\2\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2g"+
		"i\3\2\2\2hf\3\2\2\2im\7$\2\2jl\7\34\2\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2"+
		"mn\3\2\2\2np\3\2\2\2om\3\2\2\2pq\b\3\1\2q\5\3\2\2\2rs\7\23\2\2su\7\26"+
		"\2\2tv\7#\2\2ut\3\2\2\2uv\3\2\2\2vw\3\2\2\2wy\7\6\2\2xz\7#\2\2yx\3\2\2"+
		"\2z{\3\2\2\2{y\3\2\2\2{|\3\2\2\2|}\3\2\2\2}\177\5\26\f\2~\u0080\7#\2\2"+
		"\177~\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0085\7$\2\2"+
		"\u0082\u0084\7\34\2\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2\2\2\u0085\u0083"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0085\3\2\2\2\u0088"+
		"\u0089\b\4\1\2\u0089\7\3\2\2\2\u008a\u008b\7\23\2\2\u008b\u008d\7\26\2"+
		"\2\u008c\u008e\7#\2\2\u008d\u008c\3\2\2\2\u008d\u008e\3\2\2\2\u008e\u008f"+
		"\3\2\2\2\u008f\u0091\7\7\2\2\u0090\u0092\7#\2\2\u0091\u0090\3\2\2\2\u0092"+
		"\u0093\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0095\3\2"+
		"\2\2\u0095\u009c\5\34\17\2\u0096\u0098\7#\2\2\u0097\u0096\3\2\2\2\u0098"+
		"\u0099\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\3\2"+
		"\2\2\u009b\u009d\5 \21\2\u009c\u0097\3\2\2\2\u009c\u009d\3\2\2\2\u009d"+
		"\u00a4\3\2\2\2\u009e\u00a0\7#\2\2\u009f\u009e\3\2\2\2\u00a0\u00a1\3\2"+
		"\2\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3"+
		"\u00a5\5\36\20\2\u00a4\u009f\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3"+
		"\2\2\2\u00a6\u00a8\7#\2\2\u00a7\u00a6\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a9\u00ad\7$\2\2\u00aa\u00ac\7\34\2\2\u00ab\u00aa\3\2"+
		"\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00b0\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b1\b\5\1\2\u00b1\t\3\2\2\2"+
		"\u00b2\u00b3\7\23\2\2\u00b3\u00b5\7\26\2\2\u00b4\u00b6\7#\2\2\u00b5\u00b4"+
		"\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\7\b\2\2\u00b8"+
		"\u00ba\7#\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00b9\3\2"+
		"\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\5\30\r\2\u00be"+
		"\u00c0\7#\2\2\u00bf\u00be\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c1\3\2"+
		"\2\2\u00c1\u00c5\7$\2\2\u00c2\u00c4\7\34\2\2\u00c3\u00c2\3\2\2\2\u00c4"+
		"\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c8\3\2"+
		"\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00c9\b\6\1\2\u00c9\13\3\2\2\2\u00ca\u00cb"+
		"\7\23\2\2\u00cb\u00cd\7\26\2\2\u00cc\u00ce\7#\2\2\u00cd\u00cc\3\2\2\2"+
		"\u00cd\u00ce\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d1\7\5\2\2\u00d0\u00d2"+
		"\7#\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3"+
		"\u00d4\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d7\5\26\f\2\u00d6\u00d8\7"+
		"#\2\2\u00d7\u00d6\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00dd\7$\2\2\u00da\u00dc\7\34\2\2\u00db\u00da\3\2\2\2\u00dc\u00df\3\2"+
		"\2\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00e0\3\2\2\2\u00df"+
		"\u00dd\3\2\2\2\u00e0\u00e1\b\7\1\2\u00e1\r\3\2\2\2\u00e2\u00e3\7\23\2"+
		"\2\u00e3\u00e5\7\26\2\2\u00e4\u00e6\7#\2\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6"+
		"\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e9\7\4\2\2\u00e8\u00ea\7#\2\2\u00e9"+
		"\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00e9\3\2\2\2\u00eb\u00ec\3\2"+
		"\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef\5\26\f\2\u00ee\u00f0\7#\2\2\u00ef"+
		"\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2"+
		"\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00fa\5\32\16\2\u00f4\u00f6\7#\2\2\u00f5"+
		"\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2"+
		"\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fb\5\24\13\2\u00fa\u00f5\3\2\2\2\u00fa"+
		"\u00fb\3\2\2\2\u00fb\u00fd\3\2\2\2\u00fc\u00fe\7#\2\2\u00fd\u00fc\3\2"+
		"\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0103\7$\2\2\u0100"+
		"\u0102\7\34\2\2\u0101\u0100\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0101\3"+
		"\2\2\2\u0103\u0104\3\2\2\2\u0104\u0106\3\2\2\2\u0105\u0103\3\2\2\2\u0106"+
		"\u0107\b\b\1\2\u0107\17\3\2\2\2\u0108\u0109\7\t\2\2\u0109\u010a\5\"\22"+
		"\2\u010a\u010b\b\t\1\2\u010b\21\3\2\2\2\u010c\u010d\7\n\2\2\u010d\u010e"+
		"\5\"\22\2\u010e\u010f\b\n\1\2\u010f\23\3\2\2\2\u0110\u0111\7\13\2\2\u0111"+
		"\u0112\5\"\22\2\u0112\25\3\2\2\2\u0113\u0114\7\f\2\2\u0114\u0115\5\"\22"+
		"\2\u0115\u0116\b\f\1\2\u0116\27\3\2\2\2\u0117\u0118\7\20\2\2\u0118\u0119"+
		"\5\"\22\2\u0119\u011a\b\r\1\2\u011a\31\3\2\2\2\u011b\u011c\7\21\2\2\u011c"+
		"\u011d\5\"\22\2\u011d\u011e\b\16\1\2\u011e\33\3\2\2\2\u011f\u0120\7\r"+
		"\2\2\u0120\u0121\5\"\22\2\u0121\u0122\b\17\1\2\u0122\35\3\2\2\2\u0123"+
		"\u0124\7\16\2\2\u0124\u0125\5\"\22\2\u0125\u0126\b\20\1\2\u0126\37\3\2"+
		"\2\2\u0127\u0128\7\17\2\2\u0128\u0129\5\"\22\2\u0129\u012a\b\21\1\2\u012a"+
		"!\3\2\2\2\u012b\u012c\7\"\2\2\u012c\u012d\7\22\2\2\u012d\u012e\b\22\1"+
		"\2\u012e#\3\2\2\2\u012f\u0130\7\23\2\2\u0130\u0133\7\30\2\2\u0131\u0132"+
		"\7!\2\2\u0132\u0134\b\23\1\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134"+
		"\u0135\3\2\2\2\u0135\u0136\7\23\2\2\u0136\u013a\7\31\2\2\u0137\u0139\7"+
		"\34\2\2\u0138\u0137\3\2\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2\2\2\u013a"+
		"\u013b\3\2\2\2\u013b%\3\2\2\2\u013c\u013a\3\2\2\2\u013d\u013e\7\23\2\2"+
		"\u013e\u013f\7\25\2\2\u013f\u0140\7\32\2\2\u0140\u0141\7\36\2\2\u0141"+
		"\u0142\b\24\1\2\u0142\'\3\2\2\2\u0143\u0144\7\23\2\2\u0144\u0146\7\35"+
		"\2\2\u0145\u0147\7\32\2\2\u0146\u0145\3\2\2\2\u0146\u0147\3\2\2\2\u0147"+
		"\u0148\3\2\2\2\u0148\u0149\b\25\1\2\u0149\u014a\3\2\2\2\u014a\u014b\7"+
		"\36\2\2\u014b)\3\2\2\2\u014c\u014d\7\23\2\2\u014d\u014f\7\27\2\2\u014e"+
		"\u0150\7\37\2\2\u014f\u014e\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151\3"+
		"\2\2\2\u0151\u0152\7 \2\2\u0152+\3\2\2\2\u0153\u0155\7\34\2\2\u0154\u0153"+
		"\3\2\2\2\u0155\u0158\3\2\2\2\u0156\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157"+
		"\u0159\3\2\2\2\u0158\u0156\3\2\2\2\u0159\u015a\7\33\2\2\u015a\u015b\b"+
		"\27\1\2\u015b-\3\2\2\2-\64\66?AKRX^afmu{\177\u0085\u008d\u0093\u0099\u009c"+
		"\u00a1\u00a4\u00a7\u00ad\u00b5\u00bb\u00bf\u00c5\u00cd\u00d3\u00d7\u00dd"+
		"\u00e5\u00eb\u00f1\u00f7\u00fa\u00fd\u0103\u0133\u013a\u0146\u014f\u0156";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}