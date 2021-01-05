package uk.ac.ucl.jsh.applications;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Echo implements Application {

    @Override
    public void exec(ArrayList<String> appArgs, BufferedReader input, OutputStreamWriter writer) throws IOException {
        boolean atLeastOnePrinted = false;
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
