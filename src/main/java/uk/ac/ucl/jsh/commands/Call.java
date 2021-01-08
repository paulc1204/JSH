package uk.ac.ucl.jsh.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import uk.ac.ucl.jsh.applications.*;
import uk.ac.ucl.jsh.visitors.Visitor;

public class Call implements Command {
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
		
	}   
        


    
}
