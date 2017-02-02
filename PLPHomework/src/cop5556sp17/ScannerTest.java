package cop5556sp17;

import static cop5556sp17.Scanner.Kind.*;
import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import cop5556sp17.Scanner.IllegalCharException;
import cop5556sp17.Scanner.IllegalNumberException;

public class ScannerTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testEmpty() throws IllegalCharException, IllegalNumberException {
		String input = "";
		Scanner scanner = new Scanner(input);
		scanner.scan();
	}

	@Test
	public void testSemiConcat() throws IllegalCharException, IllegalNumberException {
		// input string
		String input = ";;;";
		// create and initialize the scanner
		Scanner scanner = new Scanner(input);
		scanner.scan();

		// get the first token and check its kind, position, and contents
		Scanner.Token token = scanner.nextToken();
		assertEquals(SEMI, token.kind);
		assertEquals(0, token.pos);
		String text = SEMI.getText();
		assertEquals(text.length(), token.length);
		assertEquals(text, token.getText());

		// get the next token and check its kind, position, and contents
		Scanner.Token token1 = scanner.nextToken();
		assertEquals(SEMI, token1.kind);
		assertEquals(1, token1.pos);
		assertEquals(text.length(), token1.length);
		assertEquals(text, token1.getText());

		Scanner.Token token2 = scanner.nextToken();
		assertEquals(SEMI, token2.kind);
		assertEquals(2, token2.pos);
		assertEquals(text.length(), token2.length);
		assertEquals(text, token2.getText());

		// check that the scanner has inserted an EOF token at the end
		Scanner.Token token3 = scanner.nextToken();
		assertEquals(Scanner.Kind.EOF, token3.kind);
	}

	/**
	 * This test illustrates how to check that the Scanner detects errors
	 * properly. In this test, the input contains an int literal with a value
	 * that exceeds the range of an int. The scanner should detect this and
	 * throw and IllegalNumberException.
	 * 
	 * @throws IllegalCharException
	 * @throws IllegalNumberException
	 */
	@Test
	public void testIntOverflowError() throws IllegalCharException, IllegalNumberException {
		String input = "99999999999999999";
		Scanner scanner = new Scanner(input);
		thrown.expect(IllegalNumberException.class);
		scanner.scan();
	}

	// TODO more tests
	@Test
	public void myTest1() throws IllegalCharException, IllegalNumberException {

		// input string
		String input = "00100 ac";
		// create and initialize the scanner
		Scanner scanner = new Scanner(input);
		scanner.scan();
		
		// get the first token and check its kind, position, and contents
		Scanner.Token token = scanner.nextToken();
		assertEquals(INT_LIT, token.kind);
		
		token = scanner.nextToken();
		assertEquals(INT_LIT, token.kind);
		
		token = scanner.nextToken();
		assertEquals(INT_LIT, token.kind);
		
		token = scanner.nextToken();
		assertEquals(IDENT, token.kind);
		
	}

	@Test
	public void myTest2() throws IllegalCharException, IllegalNumberException {
		// input string
		String input = "screenheight move convolve \ntrue";
		// create and initialize the scanner
		Scanner scanner = new Scanner(input);
		scanner.scan();
		String text = "";
		// get the first token and check its kind, position, and contents
		Scanner.Token token = scanner.nextToken();
		assertEquals(KW_SCREENHEIGHT, token.kind);
		assertEquals(0, token.pos);
		text = KW_SCREENHEIGHT.getText();
		assertEquals(text.length(), token.length);
		assertEquals(text, token.getText());

		// get the next token and check its kind, position, and contents
		Scanner.Token token1 = scanner.nextToken();
		assertEquals(KW_MOVE, token1.kind);
		assertEquals(13, token1.pos);
		text = KW_MOVE.getText();
		assertEquals(text.length(), token1.length);
		assertEquals(text, token1.getText());
		
		Scanner.Token token2 = scanner.nextToken();
		assertEquals(OP_CONVOLVE, token2.kind);
		assertEquals(18, token2.pos);
		assertEquals(OP_CONVOLVE.text.length(), token2.length);
		assertEquals(OP_CONVOLVE.text, token2.getText());

		// check that the scanner has inserted an EOF token at the end
		Scanner.Token token3 = scanner.nextToken();
		assertEquals(KW_TRUE, token3.kind);
		assertEquals(0, token3.getLinePos().posInLine);
	}

	@Test
	public void myTest3() throws IllegalCharException, IllegalNumberException {
		// input string
		String input = "(123) + y== true_abc";
		// create and initialize the scanner
		Scanner scanner = new Scanner(input);
		scanner.scan();
		// get the first token and check its kind, position, and contents
		Scanner.Token token = scanner.nextToken();
		String text = token.getText();
		assertEquals(LPAREN, token.kind);
		assertEquals(text, token.getText());
		// get the next token and check its kind, position, and contents
		Scanner.Token token1 = scanner.nextToken();
		assertEquals(INT_LIT, token1.kind);
		Scanner.Token token2 = scanner.nextToken();
		assertEquals(RPAREN, token2.kind);
		Scanner.Token token3 = scanner.nextToken();
		assertEquals(PLUS, token3.kind);
		Scanner.Token token4 = scanner.nextToken();
		assertEquals(IDENT, token4.kind);
		Scanner.Token token5 = scanner.nextToken();
		assertEquals(EQUAL, token5.kind);
		Scanner.Token token6 = scanner.nextToken();
		assertEquals(IDENT, token6.kind);
		
	}


	@Test
	public void myTest4() throws IllegalCharException, IllegalNumberException {
		String input = "abc #";
		Scanner scanner = new Scanner(input);
		thrown.expect(IllegalCharException.class);
		scanner.scan();
	}

	@Test
	public void myTest5() throws IllegalCharException, IllegalNumberException {
		String input = "abc/*this is comment";
		Scanner scanner = new Scanner(input);
		thrown.expect(IllegalCharException.class);
		scanner.scan();
	}

}
