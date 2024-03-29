package uk.ac.ucl.jsh.applications;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Echo implements Application {

    @Override
    public void exec(ArrayList<String> appArgs, InputStream input, OutputStream output) throws IOException {
        boolean atLeastOnePrinted = false;
        OutputStreamWriter writer = new OutputStreamWriter(output);
        for (String arg : appArgs) {
            writer.write(arg);
            writer.write(" ");
            writer.flush();
            atLeastOnePrinted = true;
        }
        if (atLeastOnePrinted) {
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        }
    }
    
}
