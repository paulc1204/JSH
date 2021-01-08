package uk.ac.ucl.jsh.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import uk.ac.ucl.jsh.visitors.Visitor;

public class Sequence implements Command {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);

    }
    
}
