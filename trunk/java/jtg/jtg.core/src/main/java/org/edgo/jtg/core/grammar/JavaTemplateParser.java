// $ANTLR 2.7.7 (20060906): "JavaTemplateGrammar.g" -> "JavaTemplateParser.java"$


package org.edgo.jtg.core.grammar;

import org.edgo.jtg.core.model.ParsedUnit;
import antlr.TokenStreamSelector;


import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class JavaTemplateParser extends antlr.LLkParser       implements JavaTemplateParserTokenTypes
 {

		private ParsedUnit unit;

protected JavaTemplateParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public JavaTemplateParser(TokenBuffer tokenBuf) {
  this(tokenBuf,4);
}

protected JavaTemplateParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public JavaTemplateParser(TokenStream lexer) {
  this(lexer,4);
}

public JavaTemplateParser(ParserSharedInputState state) {
  super(state,4);
  tokenNames = _tokenNames;
}

	public final void template(
		ParsedUnit unit
	) throws RecognitionException, TokenStreamException {
		
		
				this.unit = unit;
			
		
		try {      // for error handling
			{
			directive_template();
			}
			{
			_loop29:
			do {
				if ((LA(1)==DIRECTIVE_BEGIN) && (LA(2)==DIRECTIVE_CMD_JAR)) {
					{
					directive_jar();
					}
				}
				else if ((LA(1)==DIRECTIVE_BEGIN) && (LA(2)==DIRECTIVE_CMD_PROPERTY)) {
					{
					directive_property();
					}
				}
				else if ((LA(1)==DIRECTIVE_BEGIN) && (LA(2)==DIRECTIVE_CMD_IMPORT)) {
					{
					directive_import();
					}
				}
				else if ((LA(1)==DIRECTIVE_BEGIN) && (LA(2)==DIRECTIVE_CMD_EXTENDS)) {
					{
					directive_extends();
					}
				}
				else {
					break _loop29;
				}
				
			} while (true);
			}
			{
			_loop31:
			do {
				if ((LA(1)==SCRIPT_BEGIN) && (LA(2)==MACROCODE_END||LA(2)==RUNAT) && (_tokenSet_0.member(LA(3))) && (_tokenSet_1.member(LA(4)))) {
					script();
				}
				else if ((LA(1)==PLACEHOLDER_BEGIN) && (LA(2)==MACROCODE) && (LA(3)==MACROCODE_END) && (_tokenSet_2.member(LA(4)))) {
					placeholder();
				}
				else if ((LA(1)==MACROCODE_BEGIN) && (LA(2)==MACROCODE||LA(2)==MACROCODE_END) && (_tokenSet_3.member(LA(3))) && (_tokenSet_4.member(LA(4)))) {
					macrocode();
				}
				else if ((LA(1)==COMMENT_BEGIN) && (LA(2)==MACROCODE||LA(2)==MACROCODE_END) && (_tokenSet_3.member(LA(3))) && (_tokenSet_4.member(LA(4)))) {
					comment();
				}
				else if (((_tokenSet_2.member(LA(1))) && (_tokenSet_4.member(LA(2))) && (_tokenSet_5.member(LA(3))) && (_tokenSet_6.member(LA(4))))&&( LA(1) != EOF )) {
					targetcode();
				}
				else {
					break _loop31;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void directive_template() throws RecognitionException, TokenStreamException {
		
		Token  v = null;
		
				String language;
				String targetlanguage;
			
		
		try {      // for error handling
			match(DIRECTIVE_BEGIN);
			v = LT(1);
			match(DIRECTIVE_CMD_TEMPLATE);
			{
			switch ( LA(1)) {
			case DIRECTIVE_ATTR_LANGUAGE:
			{
				{
				language=directive_attr_language();
				targetlanguage=directive_attr_target_language();
				{
				switch ( LA(1)) {
				case DIRECTIVE_ATTR_DESCRIPTION:
				{
					directive_attr_description();
					break;
				}
				case MACROCODE_END:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				break;
			}
			case DIRECTIVE_ATTR_TARGET_LANGUAGE:
			{
				{
				targetlanguage=directive_attr_target_language();
				language=directive_attr_language();
				{
				switch ( LA(1)) {
				case DIRECTIVE_ATTR_DESCRIPTION:
				{
					directive_attr_description();
					break;
				}
				case MACROCODE_END:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(MACROCODE_END);
			{
			if ((LA(1)==WS) && (_tokenSet_8.member(LA(2))) && (_tokenSet_9.member(LA(3))) && (_tokenSet_10.member(LA(4)))) {
				match(WS);
			}
			else if ((_tokenSet_8.member(LA(1))) && (_tokenSet_9.member(LA(2))) && (_tokenSet_10.member(LA(3))) && (_tokenSet_6.member(LA(4)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			
					unit.setMacroLang(language);
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	public final void directive_jar() throws RecognitionException, TokenStreamException {
		
		Token  v = null;
		
				String name;
			
		
		try {      // for error handling
			match(DIRECTIVE_BEGIN);
			v = LT(1);
			match(DIRECTIVE_CMD_JAR);
			name=directive_attr_name();
			match(MACROCODE_END);
			{
			if ((LA(1)==WS) && (_tokenSet_8.member(LA(2))) && (_tokenSet_9.member(LA(3))) && (_tokenSet_10.member(LA(4)))) {
				match(WS);
			}
			else if ((_tokenSet_8.member(LA(1))) && (_tokenSet_9.member(LA(2))) && (_tokenSet_10.member(LA(3))) && (_tokenSet_6.member(LA(4)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			
					unit.directiveJar(name);
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	public final void directive_property() throws RecognitionException, TokenStreamException {
		
		Token  v = null;
		
				String name;
				String type;
			
		
		try {      // for error handling
			match(DIRECTIVE_BEGIN);
			v = LT(1);
			match(DIRECTIVE_CMD_PROPERTY);
			{
			if ((LA(1)==DIRECTIVE_ATTR_NAME) && (LA(2)==ASSIGN) && (LA(3)==STRING_VALUE) && (LA(4)==DIRECTIVE_ATTR_TYPE)) {
				{
				name=directive_attr_name();
				type=directive_attr_type();
				{
				switch ( LA(1)) {
				case DIRECTIVE_ATTR_CATEGORY:
				{
					directive_attr_category();
					break;
				}
				case MACROCODE_END:
				case DIRECTIVE_ATTR_DESCRIPTION:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case DIRECTIVE_ATTR_DESCRIPTION:
				{
					directive_attr_description();
					break;
				}
				case MACROCODE_END:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
			}
			else if ((LA(1)==DIRECTIVE_ATTR_NAME) && (LA(2)==ASSIGN) && (LA(3)==STRING_VALUE) && (LA(4)==DIRECTIVE_ATTR_TYPE)) {
				{
				name=directive_attr_name();
				type=directive_attr_type();
				{
				switch ( LA(1)) {
				case DIRECTIVE_ATTR_DESCRIPTION:
				{
					directive_attr_description();
					break;
				}
				case MACROCODE_END:
				case DIRECTIVE_ATTR_CATEGORY:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case DIRECTIVE_ATTR_CATEGORY:
				{
					directive_attr_category();
					break;
				}
				case MACROCODE_END:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
			}
			else if ((LA(1)==DIRECTIVE_ATTR_TYPE) && (LA(2)==ASSIGN) && (LA(3)==STRING_VALUE) && (LA(4)==DIRECTIVE_ATTR_NAME)) {
				{
				type=directive_attr_type();
				name=directive_attr_name();
				{
				switch ( LA(1)) {
				case DIRECTIVE_ATTR_CATEGORY:
				{
					directive_attr_category();
					break;
				}
				case MACROCODE_END:
				case DIRECTIVE_ATTR_DESCRIPTION:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case DIRECTIVE_ATTR_DESCRIPTION:
				{
					directive_attr_description();
					break;
				}
				case MACROCODE_END:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
			}
			else if ((LA(1)==DIRECTIVE_ATTR_TYPE) && (LA(2)==ASSIGN) && (LA(3)==STRING_VALUE) && (LA(4)==DIRECTIVE_ATTR_NAME)) {
				{
				type=directive_attr_type();
				name=directive_attr_name();
				{
				switch ( LA(1)) {
				case DIRECTIVE_ATTR_DESCRIPTION:
				{
					directive_attr_description();
					break;
				}
				case MACROCODE_END:
				case DIRECTIVE_ATTR_CATEGORY:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case DIRECTIVE_ATTR_CATEGORY:
				{
					directive_attr_category();
					break;
				}
				case MACROCODE_END:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			match(MACROCODE_END);
			{
			if ((LA(1)==WS) && (_tokenSet_8.member(LA(2))) && (_tokenSet_9.member(LA(3))) && (_tokenSet_10.member(LA(4)))) {
				match(WS);
			}
			else if ((_tokenSet_8.member(LA(1))) && (_tokenSet_9.member(LA(2))) && (_tokenSet_10.member(LA(3))) && (_tokenSet_6.member(LA(4)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			
					unit.directiveArguments(name, type, v.getLine());
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	public final void directive_import() throws RecognitionException, TokenStreamException {
		
		Token  v = null;
		
				String ns;
			
		
		try {      // for error handling
			match(DIRECTIVE_BEGIN);
			v = LT(1);
			match(DIRECTIVE_CMD_IMPORT);
			ns=directive_attr_name();
			match(MACROCODE_END);
			{
			if ((LA(1)==WS) && (_tokenSet_8.member(LA(2))) && (_tokenSet_9.member(LA(3))) && (_tokenSet_10.member(LA(4)))) {
				match(WS);
			}
			else if ((_tokenSet_8.member(LA(1))) && (_tokenSet_9.member(LA(2))) && (_tokenSet_10.member(LA(3))) && (_tokenSet_6.member(LA(4)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			
					unit.directiveImport(ns, v.getLine()); 
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	public final void directive_extends() throws RecognitionException, TokenStreamException {
		
		Token  v = null;
		
				String parent;
			
		
		try {      // for error handling
			match(DIRECTIVE_BEGIN);
			v = LT(1);
			match(DIRECTIVE_CMD_EXTENDS);
			parent=directive_attr_parent();
			match(MACROCODE_END);
			{
			if ((LA(1)==WS) && (_tokenSet_8.member(LA(2))) && (_tokenSet_9.member(LA(3))) && (_tokenSet_10.member(LA(4)))) {
				match(WS);
			}
			else if ((_tokenSet_8.member(LA(1))) && (_tokenSet_9.member(LA(2))) && (_tokenSet_10.member(LA(3))) && (_tokenSet_6.member(LA(4)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			
					unit.directiveExtends(parent, v.getLine()); 
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	public final void script() throws RecognitionException, TokenStreamException {
		
		Token  t = null;
		Token  v = null;
		
		try {      // for error handling
			match(SCRIPT_BEGIN);
			{
			switch ( LA(1)) {
			case RUNAT:
			{
				match(RUNAT);
				match(ASSIGN);
				{
				switch ( LA(1)) {
				case STRING_VALUE:
				{
					t = LT(1);
					match(STRING_VALUE);
					
								if (t.getText() != "\"template\"")
								{
									throw new NoViableAltException(LT(1), getFilename());
								}
							
					break;
				}
				case 29:
				{
					match(29);
					break;
				}
				case LITERAL_template:
				{
					match(LITERAL_template);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case MACROCODE_END:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(MACROCODE_END);
			{
			switch ( LA(1)) {
			case WS:
			{
				match(WS);
				break;
			}
			case SCRIPT_END:
			case TARGETCODE:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case TARGETCODE:
			{
				v = LT(1);
				match(TARGETCODE);
				
						  unit.addScript(v); 
					
				break;
			}
			case SCRIPT_END:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(SCRIPT_END);
			match(MACROCODE_END);
			{
			if ((LA(1)==WS) && (_tokenSet_2.member(LA(2))) && (_tokenSet_4.member(LA(3))) && (_tokenSet_5.member(LA(4)))) {
				match(WS);
			}
			else if ((_tokenSet_2.member(LA(1))) && (_tokenSet_4.member(LA(2))) && (_tokenSet_5.member(LA(3))) && (_tokenSet_6.member(LA(4)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void placeholder() throws RecognitionException, TokenStreamException {
		
		Token  v = null;
		
		try {      // for error handling
			match(PLACEHOLDER_BEGIN);
			v = LT(1);
			match(MACROCODE);
			match(MACROCODE_END);
			
					unit.addPlaceholder(v);
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void macrocode() throws RecognitionException, TokenStreamException {
		
		Token  v = null;
		
		try {      // for error handling
			match(MACROCODE_BEGIN);
			{
			switch ( LA(1)) {
			case MACROCODE:
			{
				v = LT(1);
				match(MACROCODE);
				unit.addMacrocode(v);
				break;
			}
			case MACROCODE_END:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(MACROCODE_END);
			
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void comment() throws RecognitionException, TokenStreamException {
		
		Token  v = null;
		
		try {      // for error handling
			match(COMMENT_BEGIN);
			{
			switch ( LA(1)) {
			case MACROCODE:
			{
				v = LT(1);
				match(MACROCODE);
				break;
			}
			case MACROCODE_END:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(MACROCODE_END);
			
				
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void targetcode() throws RecognitionException, TokenStreamException {
		
		Token  w = null;
		Token  v = null;
		
		try {      // for error handling
			{
			if ((LA(1)==WS) && (_tokenSet_2.member(LA(2))) && (_tokenSet_4.member(LA(3))) && (_tokenSet_5.member(LA(4)))) {
				w = LT(1);
				match(WS);
			}
			else if ((_tokenSet_2.member(LA(1))) && (_tokenSet_4.member(LA(2))) && (_tokenSet_5.member(LA(3))) && (_tokenSet_6.member(LA(4)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			{
			if ((LA(1)==TARGETCODE) && (_tokenSet_2.member(LA(2))) && (_tokenSet_4.member(LA(3))) && (_tokenSet_5.member(LA(4)))) {
				v = LT(1);
				match(TARGETCODE);
			}
			else if ((_tokenSet_2.member(LA(1))) && (_tokenSet_4.member(LA(2))) && (_tokenSet_5.member(LA(3))) && (_tokenSet_6.member(LA(4)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			
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
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final String  directive_attr_language() throws RecognitionException, TokenStreamException {
		String language;
		
		
				language = "";
			
		
		try {      // for error handling
			match(DIRECTIVE_ATTR_LANGUAGE);
			language=directive_attr();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_11);
		}
		return language;
	}
	
	public final String  directive_attr_target_language() throws RecognitionException, TokenStreamException {
		String language;
		
		
				language = "";
			
		
		try {      // for error handling
			match(DIRECTIVE_ATTR_TARGET_LANGUAGE);
			language=directive_attr();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_12);
		}
		return language;
	}
	
	public final void directive_attr_description() throws RecognitionException, TokenStreamException {
		
		
				String attr;
			
		
		try {      // for error handling
			match(DIRECTIVE_ATTR_DESCRIPTION);
			attr=directive_attr();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_13);
		}
	}
	
	public final String  directive_attr_name() throws RecognitionException, TokenStreamException {
		String name;
		
		
				name = "";
			
		
		try {      // for error handling
			match(DIRECTIVE_ATTR_NAME);
			name=directive_attr();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_14);
		}
		return name;
	}
	
	public final String  directive_attr_parent() throws RecognitionException, TokenStreamException {
		String parent;
		
		
				parent = "";
			
		
		try {      // for error handling
			match(DIRECTIVE_ATTR_PARENT);
			parent=directive_attr();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_15);
		}
		return parent;
	}
	
	public final String  directive_attr_type() throws RecognitionException, TokenStreamException {
		String type;
		
		
				type = "";
			
		
		try {      // for error handling
			match(DIRECTIVE_ATTR_TYPE);
			type=directive_attr();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_16);
		}
		return type;
	}
	
	public final void directive_attr_category() throws RecognitionException, TokenStreamException {
		
		
				String attr;
			
		
		try {      // for error handling
			match(DIRECTIVE_ATTR_CATEGORY);
			attr=directive_attr();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_17);
		}
	}
	
	public final String  directive_attr() throws RecognitionException, TokenStreamException {
		String value;
		
		Token  v = null;
		
				value = "";
			
		
		try {      // for error handling
			match(ASSIGN);
			v = LT(1);
			match(STRING_VALUE);
			
					value = v.getText();
			if (value.startsWith("\""))
			value = value.substring(1);
			if (value.endsWith("\""))
			value = value.substring(0, value.length() - 1);
			
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_18);
		}
		return value;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"PLACEHOLDER_BEGIN",
		"DIRECTIVE_BEGIN",
		"COMMENT_BEGIN",
		"SCRIPT_BEGIN",
		"SCRIPT_END",
		"MACROCODE_BEGIN",
		"WS",
		"TARGETCODE",
		"MACROCODE",
		"MACROCODE_END",
		"RUNAT",
		"ASSIGN",
		"DIRECTIVE_CMD_TEMPLATE",
		"DIRECTIVE_CMD_PROPERTY",
		"DIRECTIVE_CMD_JAR",
		"DIRECTIVE_CMD_IMPORT",
		"DIRECTIVE_CMD_EXTENDS",
		"DIRECTIVE_ATTR_LANGUAGE",
		"DIRECTIVE_ATTR_TARGET_LANGUAGE",
		"DIRECTIVE_ATTR_DESCRIPTION",
		"DIRECTIVE_ATTR_NAME",
		"DIRECTIVE_ATTR_PARENT",
		"DIRECTIVE_ATTR_TYPE",
		"DIRECTIVE_ATTR_CATEGORY",
		"STRING_VALUE",
		"\"'template'\"",
		"\"template\""
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 36096L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 1879058688L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 3794L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 11986L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 32466L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 65490L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 1879113682L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 3826L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 1998546L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 117506002L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 12591104L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 10493952L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = { 134225920L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = { 209723392L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = { 8192L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = { 159391744L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = { 8396800L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = { 232792064L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	
	}
