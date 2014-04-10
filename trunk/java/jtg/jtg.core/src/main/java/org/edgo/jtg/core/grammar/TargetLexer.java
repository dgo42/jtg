// $ANTLR 2.7.7 (20060906): "TargetLexer.g" -> "TargetLexer.java"$


package org.edgo.jtg.core.grammar;

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

public class TargetLexer extends antlr.CharScanner implements TargetcodeTokenTypes, TokenStream
 {

    private TokenStreamSelector selector;
    
    public TokenStreamSelector getSelector() {
        return selector;
    }
    
    public void setSelector(TokenStreamSelector selector) {
        this.selector = selector;
    }
public TargetLexer(InputStream in) {
	this(new ByteBuffer(in));
}
public TargetLexer(Reader in) {
	this(new CharBuffer(in));
}
public TargetLexer(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public TargetLexer(LexerSharedInputState state) {
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
				if ((LA(1)=='<') && (LA(2)=='#') && (LA(3)==' '||LA(3)=='s') && (LA(4)=='c'||LA(4)=='s') && (LA(5)=='c'||LA(5)=='r')) {
					mSCRIPT_BEGIN(true);
					theRetToken=_returnToken;
				}
				else if ((LA(1)=='<') && (LA(2)=='#') && (LA(3)=='/') && (LA(4)==' '||LA(4)=='s') && (LA(5)=='c'||LA(5)=='s')) {
					mSCRIPT_END(true);
					theRetToken=_returnToken;
				}
				else if ((LA(1)=='<') && (LA(2)=='#') && (LA(3)=='-') && (LA(4)=='-') && (true)) {
					mCOMMENT_BEGIN(true);
					theRetToken=_returnToken;
				}
				else if ((LA(1)=='<') && (LA(2)=='#') && (LA(3)=='=') && (true) && (true)) {
					mPLACEHOLDER_BEGIN(true);
					theRetToken=_returnToken;
				}
				else if ((LA(1)=='<') && (LA(2)=='#') && (LA(3)=='@') && (true) && (true)) {
					mDIRECTIVE_BEGIN(true);
					theRetToken=_returnToken;
				}
				else if ((LA(1)=='<') && (LA(2)=='#') && (true) && (true) && (true)) {
					mMACROCODE_BEGIN(true);
					theRetToken=_returnToken;
				}
				else if ((_tokenSet_0.member(LA(1))) && (true) && (true) && (true) && (true)) {
					mWS(true);
					theRetToken=_returnToken;
				}
				else if (((LA(1) >= '\u0003' && LA(1) <= '\ufffe')) && (true) && (true) && (true) && (true)) {
					mTARGETCODE(true);
					theRetToken=_returnToken;
				}
				else {
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
				else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
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

	public final void mPLACEHOLDER_BEGIN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = PLACEHOLDER_BEGIN;
		int _saveIndex;
		
		{
		match("<#=");
		
					selector.push("macrocode");
				
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIRECTIVE_BEGIN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIRECTIVE_BEGIN;
		int _saveIndex;
		
		{
		match("<#@");
		
					selector.push("main");
				
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCOMMENT_BEGIN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COMMENT_BEGIN;
		int _saveIndex;
		
		{
		match("<#--");
		
					selector.push("macrocode");
				
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSCRIPT_BEGIN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SCRIPT_BEGIN;
		int _saveIndex;
		
		{
		match("<#");
		{
		switch ( LA(1)) {
		case ' ':
		{
			match(' ');
			break;
		}
		case 's':
		{
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		match("script");
		
					selector.push("main");
			
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSCRIPT_END(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SCRIPT_END;
		int _saveIndex;
		
		{
		match("<#/");
		{
		switch ( LA(1)) {
		case ' ':
		{
			match(' ');
			break;
		}
		case 's':
		{
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		match("script");
		
					selector.push("main");
			
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMACROCODE_BEGIN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MACROCODE_BEGIN;
		int _saveIndex;
		
		{
		if (!( !(LA(3) == '=' || LA(3) == '-' || LA(3) == '@' || LA(3) == 's' || (LA(3) == '/' && LA(4) != '/')) ))
		  throw new SemanticException(" !(LA(3) == '=' || LA(3) == '-' || LA(3) == '@' || LA(3) == 's' || (LA(3) == '/' && LA(4) != '/')) ");
		match("<#");
		
					selector.push("macrocode");
				
		}
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
		int _cnt18=0;
		_loop18:
		do {
			if ((_tokenSet_0.member(LA(1))) && (true) && (true) && (true) && (true)) {
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
			}
			else {
				if ( _cnt18>=1 ) { break _loop18; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
			}
			
			_cnt18++;
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mTARGETCODE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = TARGETCODE;
		int _saveIndex;
		
		{
		{
		int _cnt23=0;
		_loop23:
		do {
			if (((LA(1)=='<'))&&( LA(2) != '#' )) {
				match('<');
			}
			else if ((_tokenSet_0.member(LA(1)))) {
				mWS(false);
			}
			else if ((_tokenSet_1.member(LA(1)))) {
				{
				match(_tokenSet_1);
				}
			}
			else {
				if ( _cnt23>=1 ) { break _loop23; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
			}
			
			_cnt23++;
		} while (true);
		}
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[1025];
		data[0]=4294977024L;
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[2048];
		data[0]=-1152921508901824008L;
		for (int i = 1; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	
	}
