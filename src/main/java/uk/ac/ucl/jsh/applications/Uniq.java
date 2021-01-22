package uk.ac.ucl.jsh.applications;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import uk.ac.ucl.jsh.Jsh;

public class Uniq implements Application {
    
    @Override
    public void exec(ArrayList<String> appArgs, InputStream input, OutputStream output) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(output);
        boolean caseInsensitive = false;
        String fileName;
        
        if (appArgs.isEmpty()) {
            throw new RuntimeException("uniq: missing arguments");
        }
        if (appArgs.size() > 2) {
            throw new RuntimeException("uniq: wrong arguments");
        }
        if(appArgs.size() == 1){
            fileName = Jsh.getCurrentDir() + File.separator + appArgs.get(0);
        }else{
            caseInsensitive = appArgs.get(0).equals("-i");
            if(!caseInsensitive){
                throw new RuntimeException("uniq: wrong option input, only allowed [-i]");
            }
            fileName = Jsh.getCurrentDir() + File.separator + appArgs.get(1);
        }

        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Set<String> lines = new LinkedHashSet<String>();
        Set<String> insensitiveLines = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        String line;
        
        if(caseInsensitive){
            while ((line = reader.readLine()) != null) {
                insensitiveLines.add(line);
            }
        }else{
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
            
        }
        reader.close();

        for(String unique : caseInsensitive? insensitiveLines: lines){
            writer.write(unique);
            writer.write(System.lineSeparator());
            writer.flush();
        }
    }
    
}
