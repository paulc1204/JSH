package uk.ac.ucl.jsh.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import uk.ac.ucl.jsh.visitors.Visitor;

public class UnsafeCall implements Command {

    private Call call;

    public UnsafeCall(Call call){
        this.call = call;
    }

    @Override
    public void accept(Visitor visitor, InputStream input, OutputStream output) throws Exception {
        try {
            visitor.visit(call, input, output);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    
}
