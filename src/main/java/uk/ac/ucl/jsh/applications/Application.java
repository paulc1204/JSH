package uk.ac.ucl.jsh.applications;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public interface Application {
    
    public void exec(ArrayList<String> appArgs, InputStream input, OutputStream output) throws IOException;

}
