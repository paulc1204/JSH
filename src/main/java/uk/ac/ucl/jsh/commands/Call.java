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
    public BufferedReader input;
	public OutputStreamWriter writer;
	
	public Call(ArrayList<String> tokens, BufferedReader input, OutputStreamWriter writer){
		appArgs = new ArrayList<String>(tokens.subList(1, tokens.size()));
        appName = tokens.get(0);
        this.input = input;
        this.writer = writer;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}   
        


    
}
