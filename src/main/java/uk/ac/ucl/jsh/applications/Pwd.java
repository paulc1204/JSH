package uk.ac.ucl.jsh.applications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import uk.ac.ucl.jsh.Jsh;

public class Pwd implements Application {

    @Override
    public void exec(ArrayList<String> appArgs, BufferedReader input, OutputStreamWriter writer) throws IOException {
        writer.write(Jsh.currentDirectory);
        writer.write(System.getProperty("line.separator"));
        writer.flush();
    }
    
}
