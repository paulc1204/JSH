package uk.ac.ucl.jsh.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import uk.ac.ucl.jsh.visitors.Visitor;

public class Sequence extends AbstractCommands implements Command {

    public Sequence(){
        super();
    }

    public Sequence(ArrayList<Command> commands){
        super(commands);
    }

    @Override
    public void accept(Visitor visitor, InputStream input, OutputStream output) throws Exception{
        visitor.visit(this, input, output);

    }
    
}
