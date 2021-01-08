package uk.ac.ucl.jsh.visitors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import uk.ac.ucl.jsh.ApplicationFactory;
import uk.ac.ucl.jsh.commands.Call;
import uk.ac.ucl.jsh.commands.Pipe;
import uk.ac.ucl.jsh.commands.Sequence;

public class CommandVisitor implements Visitor {

    private ArrayList<String> appArgs;
    String appName;
    private BufferedReader input;
    private OutputStreamWriter writer;

    public CommandVisitor(ArrayList<String> tokens, BufferedReader input, OutputStreamWriter writer) {
        appArgs = new ArrayList<String>(tokens.subList(1, tokens.size()));
        appName = tokens.get(0);
        this.input = input;
        this.writer = writer;
    }

    @Override
    public void visit(Call call) {
        try {
            ApplicationFactory.getApp(appName).exec(appArgs, input, writer);
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
