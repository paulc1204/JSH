package uk.ac.ucl.jsh.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import uk.ac.ucl.jsh.visitors.Visitor;

public class Pipe implements Command {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);

    }
    
}
