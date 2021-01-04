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
import java.util.stream.Stream;

import uk.ac.ucl.jsh.Jsh;

public class Sort implements Application<OutputStreamWriter> {

    private String fileName;
    
    @Override
    public void exec(ArrayList<String> args, BufferedReader input, OutputStreamWriter writer) throws IOException {
        if (args.isEmpty()) {
            throw new RuntimeException("sort: missing arguments");
        }
        if (args.size() != 1) {
            throw new RuntimeException("sort: wrong arguments");
        }

        fileName = Jsh.currentDirectory + File.separator + args.get(0);

        try(Stream<String> lines = Files.lines(Paths.get(fileName))){
            lines.sorted().forEach(s -> {
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
