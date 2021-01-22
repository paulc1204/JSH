package uk.ac.ucl.jsh.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import uk.ac.ucl.jsh.visitors.Visitor;

public class Pipe extends AbstractCommands implements Command {

    public Pipe(){
        super();
    }
    
    public Pipe(ArrayList<Command> pipeCommands){
        super(pipeCommands);
    }

    
    @Override
    public void accept(Visitor visitor, InputStream input, OutputStream output) throws Exception{
        visitor.visit(this, input, output);

    }

    
    
}
