
import calculator.values.NumericValue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.ExpressionParser;
import parser.MyInfixParser;
import parser.MyPrefixParser;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for {@link CLI} class: tests capitalize(), print_init(), print_menu(), and a minimal runCLI().
 */
public class TestCLI {
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }

    @Test
    void testCapitalize() {
        assertNull(CLI.capitalize(null));
        assertEquals("", CLI.capitalize(""));
        assertEquals("Hello", CLI.capitalize("hello"));
        assertEquals("World", CLI.capitalize("WORLD"));
        assertEquals("A", CLI.capitalize("a"));
    }

    @Test
    void testPrintInit() {
        CLI.print_init();
        String out = outContent.toString();
        assertTrue(out.contains("Welcome to the CLI Calculator!"));
        assertTrue(out.contains("prefix"));
        assertTrue(out.contains("postfix"));
        // check that instructions mention 'help'
        assertTrue(out.contains("help"));
    }

    @Test
    void testPrintMenuInfix() {
        // ensure in infix mode
        ExpressionParser.setNotation(ExpressionParser.Notation.INFIX);
        CLI.print_menu();
        String err = errContent.toString();
        MyInfixParser p=new MyInfixParser();
        assertThrows(RuntimeException.class, () ->  p.evaluate(new MyInfixParser.Node("+ 1 2")));
    }

    @Test
    void testPrintMenuPrefix() {
        ExpressionParser.setNotation(ExpressionParser.Notation.PREFIX);
        CLI.print_menu();
        MyPrefixParser p=new MyPrefixParser();
        assertThrows(RuntimeException.class, () ->  p.evaluate("1 * 2"));
    }

    @Test
    void testRunCLIExitImmediately() {
        // simulate user typing "exit" then newline
        String input = "exit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        // skip initial delay
        CLI.first_use = false;
        CLI.runCLI();
        String out = outContent.toString();
        String err = errContent.toString();
        // welcome printed to out
        assertTrue(out.contains("Welcome to the CLI Calculator!"));
        // prompt printed to err
        assertTrue(err.contains(">>>"));
        // goodbye message
        assertTrue(out.contains("Goodbye!"));
    }

    @Test
    void testRunCLIHelpCommand() {
        // simulate user: help, exit
        String input = "help\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        CLI.first_use = false;
        CLI.runCLI();
        String err = errContent.toString();
        // help prints 'Instructions' header
        assertTrue(err.contains("Instructions :"));
        // after help, exit prints prompt then terminates
        assertTrue(err.contains(">>>"));
    }
}
