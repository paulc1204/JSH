package uk.ac.ucl.jsh;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import uk.ac.ucl.jsh.commands.*;
import uk.ac.ucl.jsh.visitors.CommandConstructor;
import uk.ac.ucl.jsh.visitors.CommandVisitor;

public class Eval {

    /**
     * Evaluates the command line input from user according to grammar defined by JSH
     * Sorts the commands into appropriate Command types for execution
     * Constructed commands accept visitor for appropriate execution
     * 
     * @param cmdline String command line input from user
     * 
     * @param output OutputStream for execution results to be output to 
     */
    public static void eval(String cmdline, OutputStream output) throws Exception {

        CharStream parserInput = CharStreams.fromString(cmdline); 
        JshGrammarLexer lexer = new JshGrammarLexer(parserInput);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);        
        JshGrammarParser parser = new JshGrammarParser(tokenStream);
        ParseTree tree = parser.command();

        Command root = tree.accept(new CommandConstructor());
        root.accept(new CommandVisitor(), System.in, output);

    }
}
