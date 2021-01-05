package uk.ac.ucl.jsh.applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

import uk.ac.ucl.jsh.Jsh;

public class Sort implements Application {

    @Override
    public void exec(ArrayList<String> appArgs, BufferedReader input, OutputStreamWriter writer) throws IOException {
        boolean reverse = false;
        String fileName;

        if (appArgs.isEmpty()) {
            throw new RuntimeException("sort: missing arguments");
        }else if (appArgs.size() > 2) {
            throw new RuntimeException("sort: wrong arguments");
        }

        if(appArgs.size() == 1){
            fileName = Jsh.currentDirectory + File.separator + appArgs.get(0);
        }else{
            reverse = appArgs.get(0).equals("-r");
            if(!reverse){
                throw new RuntimeException("sort: wrong arguments");
            }
            fileName = Jsh.currentDirectory + File.separator + appArgs.get(1);   
        }

        try(Stream<String> lines = Files.lines(Paths.get(fileName))){
            if(!reverse){
                lines.sorted().forEach(s -> {
                    try {
                        writer.write(s);
                        writer.write(System.getProperty("line.separator"));
                        writer.flush();
                    } catch (IOException e) {
                        // throw something here
                    }      
                });
            }else{
                lines.sorted(Comparator.reverseOrder()).forEach(s -> {
                    try {
                        writer.write(s);
                        writer.write(System.getProperty("line.separator"));
                        writer.flush();
                    } catch (IOException e) {
                        // throw something here
                    }      
                });
            }
            
        }
        
    }
    
}
