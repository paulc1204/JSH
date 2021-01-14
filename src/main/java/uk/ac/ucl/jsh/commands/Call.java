package uk.ac.ucl.jsh.commands;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import uk.ac.ucl.jsh.applications.*;
import uk.ac.ucl.jsh.visitors.Visitor;

public class Call implements Command {
	
	public ArrayList<String> appArgs;
    public String appName;
    public InputStream input;
	public final OutputStream output; //i think output becomes null for some reason as it gets passed around???
	
	public Call(ArrayList<String> tokens, InputStream input, OutputStream output){
		appArgs = new ArrayList<String>(tokens.subList(1, tokens.size()));
        appName = tokens.get(0);
		this.input = input;
		this.output = output;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}   
        


    
}
