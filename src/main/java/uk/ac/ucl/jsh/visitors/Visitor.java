package uk.ac.ucl.jsh.visitors;

import uk.ac.ucl.jsh.commands.*;


public interface Visitor {
    
    public void visit(Call call);
    public void visit(Pipe pipe);
    public void visit(Sequence seq);
}
