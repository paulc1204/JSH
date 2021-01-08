package uk.ac.ucl.jsh;

import java.io.BufferedReader;
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
import uk.ac.ucl.jsh.visitors.CommandVisitor;

//this should be implemented in ANTLR
public class Eval {

    public static void eval(String cmdline, OutputStream output) throws IOException{

        OutputStreamWriter writer = new OutputStreamWriter(output);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        CharStream parserInput = CharStreams.fromString(cmdline); 
        JshGrammarLexer lexer = new JshGrammarLexer(parserInput);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);        
        JshGrammarParser parser = new JshGrammarParser(tokenStream);
        ParseTree tree = parser.command();
        ArrayList<String> rawCommands = new ArrayList<String>();
        String lastSubcommand = "";
        for (int i=0; i<tree.getChildCount(); i++) {
            if (!tree.getChild(i).getText().equals(";")) {
                //No sequencing
                lastSubcommand += tree.getChild(i).getText();
            } else {
                //Sequence of commands 
                rawCommands.add(lastSubcommand);
                lastSubcommand = "";
            }
        }   
        rawCommands.add(lastSubcommand);
        for (String rawCommand : rawCommands) {
            String spaceRegex = "[^\\s\"']+|\"([^\"]*)\"|'([^']*)'";
            ArrayList<String> tokens = new ArrayList<String>();
            Pattern regex = Pattern.compile(spaceRegex);
            Matcher regexMatcher = regex.matcher(rawCommand);
            String nonQuote;
            while (regexMatcher.find()) {
                if (regexMatcher.group(1) != null || regexMatcher.group(2) != null) {
                    String quoted = regexMatcher.group(0).trim();
                    tokens.add(quoted.substring(1,quoted.length()-1));
                } else {
                    nonQuote = regexMatcher.group().trim();
                    ArrayList<String> globbingResult = new ArrayList<String>();
                    Path dir = Paths.get(Jsh.currentDirectory);
                    DirectoryStream<Path> stream = Files.newDirectoryStream(dir, nonQuote);
                    for (Path entry : stream) {
                        globbingResult.add(entry.getFileName().toString());
                    }
                    if (globbingResult.isEmpty()) {
                        globbingResult.add(nonQuote);
                    }
                    tokens.addAll(globbingResult);
                }
            }

            CommandVisitor visitor = new CommandVisitor(tokens, input, writer);
            Command command = new Call();  
            command.accept(visitor);
        }


    }
}
