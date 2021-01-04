package uk.ac.ucl.jsh.applications;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public interface Application<T> {
    
    public void exec(ArrayList<String> args, BufferedReader input, T output);

}
