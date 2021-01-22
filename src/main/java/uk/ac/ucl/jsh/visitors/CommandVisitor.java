package uk.ac.ucl.jsh.visitors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import uk.ac.ucl.jsh.applications.ApplicationFactory;
import uk.ac.ucl.jsh.commands.Call;
import uk.ac.ucl.jsh.commands.Command;
import uk.ac.ucl.jsh.commands.Pipe;
import uk.ac.ucl.jsh.commands.Sequence;

/**
     * Visits the {@code ParseTree} to execute the appropriate subclass of {@code Command}
     */
public class CommandVisitor implements Visitor {

    @Override
    public void visit(Call call, InputStream input, OutputStream output) throws Exception{
        ApplicationFactory.getApp(call.getAppName()).exec(call.getAppArgs(), input, output);
      
    }

    @Override
    public void visit(Pipe pipe, InputStream input, OutputStream output) throws Exception { 
        ArrayList<Command> pipeCommands = pipe.getCommands();
        if(pipeCommands.size()==0) return;

        ByteArrayInputStream byteIn;
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();

        try {
            pipeCommands.get(0).accept(this, input, byteOut);
            for(int i=0; i<(pipeCommands.size()-1); i++){
                byteIn = new ByteArrayInputStream(byteOut.toByteArray()); 
                byteOut.reset();
                pipeCommands.get(i).accept(this, byteIn, byteOut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            pipeCommands.get(pipeCommands.size()-1).accept(this, byteIn, output);
        }
        

    }

    @Override
    public void visit(Sequence seq, InputStream input, OutputStream output) throws Exception{
        for(Command command : seq){
            command.accept(this, input, output);
        }

    }
    
}
