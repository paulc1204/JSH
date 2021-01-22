package uk.ac.ucl.jsh.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import uk.ac.ucl.jsh.applications.*;
import uk.ac.ucl.jsh.visitors.Visitor;

public class Call implements Command {
	
	private ArrayList<String> appArgs;
	private String appName;
	private String inputRe;
	private String outputRe;
	
	public Call(String appName, ArrayList<String> appArgs){
		this.appArgs = appArgs;
        this.appName = appName;
	}

	public String getAppName(){
		return appName;
	}

	public ArrayList<String> getAppArgs(){
		return appArgs;
	}

	public void redirInput(String inputRe){
		this.inputRe = inputRe;
	}

	public void redirOutput(String outputRe){
		this.outputRe = outputRe;
	}

	@Override
	public void accept(Visitor visitor, InputStream input, OutputStream output) throws Exception {
		visitor.visit(this, input, output);
	}   
        


    
}
