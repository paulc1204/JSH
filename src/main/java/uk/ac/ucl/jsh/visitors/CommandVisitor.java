package uk.ac.ucl.jsh.visitors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import uk.ac.ucl.jsh.applications.ApplicationFactory;
import uk.ac.ucl.jsh.commands.Call;
import uk.ac.ucl.jsh.commands.Pipe;
import uk.ac.ucl.jsh.commands.Sequence;

public class CommandVisitor implements Visitor {

    @Override
    public void visit(Call call) {
        try {
            ApplicationFactory.getApp(call.appName).exec(call.appArgs, call.input, call.output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Pipe pipe) {
       

    }

    @Override
    public void visit(Sequence seq) {
        

    }
    
}
