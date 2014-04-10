// $ANTLR 2.7.7 (20060906): "JavaTemplateGrammar.g" -> "JavaTemplateLexer.java"$


package org.edgo.jtg.core.grammar;

import org.edgo.jtg.core.model.ParsedUnit;
import antlr.TokenStreamSelector;


import java.io.InputStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.SemanticException;

public class JavaTemplateLexer extends antlr.CharScanner implements JavaTemplateTokenTypes, TokenStream
 {

    private TokenStreamSelector selector;
    
    public TokenStreamSelector getSelector() {
        return selector;
    }
    
    public void setSelector(TokenStreamSelector selector) {
        this.selector = selector;
    }
public JavaTemplateLexer(InputStream in) {
	this(new ByteBuffer(in));
}
public JavaTemplateLexer(Reader in) {
	this(new CharBuffer(in));
}
public JavaTemplateLexer(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public JavaTemplateLexer(LexerSharedInputState state) {
	super(state);
	caseSensitiveLiterals = true;
	setCaseSensitive(true);
	literals = new Hashtable();
}

public Token nextToken() throws TokenStreamException {
	Token theRetToken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		resetText();
		try {   // for char stream error handling
			try {   // for lexical error handling
				switch ( LA(1)) {
				case 'r':
				{
					mRUNAT(true);
					theRetToken=_returnToken;
					break;
				}
				case '=':
				{
					mASSIGN(true);
					theRetToken=_returnToken;
					break;
				}
				case '#':
				{
					mMACROCODE_END(true);
					theRetToken=_returnToken;
					break;
				}
				case 'j':
				{
					mDIRECTIVE_CMD_JAR(true);
					theRetToken=_returnToken;
					break;
				}
				case 'i':
				{
					mDIRECTIVE_CMD_IMPORT(true);
					theRetToken=_returnToken;
					break;
				}
				case 'e':
				{
					mDIRECTIVE_CMD_EXTENDS(true);
					theRetToken=_returnToken;
					break;
				}
				case 'l':
				{
					mDIRECTIVE_ATTR_LANGUAGE(true);
					theRetToken=_returnToken;
					break;
				}
				case 'd':
				{
					mDIRECTIVE_ATTR_DESCRIPTION(true);
					theRetToken=_returnToken;
					break;
				}
				case 'n':
				{
					mDIRECTIVE_ATTR_NAME(true);
					theRetToken=_returnToken;
					break;
				}
				case '"':
				{
					mSTRING_VALUE(true);
					theRetToken=_returnToken;
					break;
				}
				case '\t':  case '\n':  case '\r':  case ' ':
				{
					mWS(true);
					theRetToken=_returnToken;
					break;
				}
				default:
					if ((LA(1)=='c') && (LA(2)=='o')) {
						mDIRECTIVE_CMD_TEMPLATE(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='p') && (LA(2)=='r')) {
						mDIRECTIVE_CMD_PROPERTY(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='t') && (LA(2)=='a')) {
						mDIRECTIVE_ATTR_TARGET_LANGUAGE(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='p') && (LA(2)=='a')) {
						mDIRECTIVE_ATTR_PARENT(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='t') && (LA(2)=='y')) {
						mDIRECTIVE_ATTR_TYPE(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='c') && (LA(2)=='a')) {
						mDIRECTIVE_ATTR_CATEGORY(true);
						theRetToken=_returnToken;
					}
				else {
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
				else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				}
				if ( _returnToken==null ) continue tryAgain; // found SKIP token
				_ttype = _returnToken.getType();
				_ttype = testLiteralsTable(_ttype);
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (RecognitionException e) {
				throw new TokenStreamRecognitionException(e);
			}
		}
		catch (CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
			}
			else {
				throw new TokenStreamException(cse.getMessage());
			}
		}
	}
}

	public final void mRUNAT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = RUNAT;
		int _saveIndex;
		
		match("runat");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mASSIGN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ASSIGN;
		int _saveIndex;
		
		match('=');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMACROCODE_END(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MACROCODE_END;
		int _saveIndex;
		
		match("#>");
		
				selector.push("targetcode");
			
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_CMD_TEMPLATE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_CMD_TEMPLATE;
		int _saveIndex;
		
		match("codeTemplate");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_CMD_PROPERTY(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_CMD_PROPERTY;
		int _saveIndex;
		
		match("property");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_CMD_JAR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_CMD_JAR;
		int _saveIndex;
		
		match("jar");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_CMD_IMPORT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_CMD_IMPORT;
		int _saveIndex;
		
		match("import");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_CMD_EXTENDS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_CMD_EXTENDS;
		int _saveIndex;
		
		match("extends");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_ATTR_LANGUAGE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_ATTR_LANGUAGE;
		int _saveIndex;
		
		match("language");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_ATTR_TARGET_LANGUAGE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_ATTR_TARGET_LANGUAGE;
		int _saveIndex;
		
		match("targetLanguage");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_ATTR_DESCRIPTION(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_ATTR_DESCRIPTION;
		int _saveIndex;
		
		match("description");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_ATTR_NAME(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_ATTR_NAME;
		int _saveIndex;
		
		match("name");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_ATTR_PARENT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_ATTR_PARENT;
		int _saveIndex;
		
		match("parent");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_ATTR_TYPE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_ATTR_TYPE;
		int _saveIndex;
		
		match("type");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_ATTR_CATEGORY(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_ATTR_CATEGORY;
		int _saveIndex;
		
		match("category");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSTRING_VALUE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = STRING_VALUE;
		int _saveIndex;
		
		match('"');
		{
		_loop19:
		do {
			if ((_tokenSet_0.member(LA(1)))) {
				{
				match(_tokenSet_0);
				}
			}
			else {
				break _loop19;
			}
			
		} while (true);
		}
		match('"');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mWS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = WS;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case ' ':
		{
			match(' ');
			break;
		}
		case '\t':
		{
			match('\t');
			break;
		}
		case '\n':
		{
			match('\n');
			newline();
			break;
		}
		case '\r':
		{
			match('\r');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		_ttype = Token.SKIP;
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[2048];
		data[0]=-17179869192L;
		for (int i = 1; i<=1023; i++) { data[i]=-1L; }
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	
	}
