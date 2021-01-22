package uk.ac.ucl.jsh.visitors;

import java.io.InputStream;
import java.io.OutputStream;

import uk.ac.ucl.jsh.commands.*;


public interface Visitor {
    
    public void visit(Call call, InputStream input, OutputStream output) throws Exception;
    public void visit(Pipe pipe, InputStream input, OutputStream output) throws Exception;
    public void visit(Sequence seq, InputStream input, OutputStream output) throws Exception;
}
