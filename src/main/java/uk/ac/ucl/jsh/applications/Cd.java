package uk.ac.ucl.jsh.applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import uk.ac.ucl.jsh.Jsh;

public class Cd implements Application {

    @Override
    public void exec(ArrayList<String> appArgs, BufferedReader input, OutputStreamWriter writer) throws IOException {
        if (appArgs.isEmpty()) {
            throw new RuntimeException("cd: missing argument");
        } else if (appArgs.size() > 1) {
            throw new RuntimeException("cd: too many arguments");
        }
        String dirString = appArgs.get(0);
        File dir = new File(Jsh.currentDirectory, dirString);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new RuntimeException("cd: " + dirString + " is not an existing directory");
        }
        Jsh.currentDirectory = dir.getCanonicalPath();

    }
    
}
