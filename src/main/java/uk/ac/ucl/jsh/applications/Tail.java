package uk.ac.ucl.jsh.applications;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import uk.ac.ucl.jsh.Jsh;

public class Tail implements Application {

    @Override
    public void exec(ArrayList<String> appArgs, InputStream input, OutputStream output) throws IOException {
        if (appArgs.isEmpty()) {
            throw new RuntimeException("tail: missing arguments");
        }
        if (appArgs.size() != 1 && appArgs.size() != 3) {
            throw new RuntimeException("tail: wrong arguments");
        }
        if (appArgs.size() == 3 && !appArgs.get(0).equals("-n")) {
            throw new RuntimeException("tail: wrong argument " + appArgs.get(0));
        }
        int tailLines = 10;
        String tailArg;
        OutputStreamWriter writer = new OutputStreamWriter(output);

        if (appArgs.size() == 3) {
            try {
                tailLines = Integer.parseInt(appArgs.get(1));
            } catch (Exception e) {
                throw new RuntimeException("tail: wrong argument " + appArgs.get(1));
            }
            tailArg = appArgs.get(2);
        } else {
            tailArg = appArgs.get(0);
        }
        File tailFile = new File(Jsh.getCurrentDir() + File.separator + tailArg);
        if (tailFile.exists()) {
            Charset encoding = StandardCharsets.UTF_8;
            Path filePath = Paths.get((String) Jsh.getCurrentDir() + File.separator + tailArg);
            ArrayList<String> storage = new ArrayList<>();
            try (BufferedReader reader = Files.newBufferedReader(filePath, encoding)) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    storage.add(line);
                }
                int index = 0;
                if (tailLines > storage.size()) {
                    index = 0;
                } else {
                    index = storage.size() - tailLines;
                }
                for (int i = index; i < storage.size(); i++) {
                    writer.write(storage.get(i));
                    writer.write(System.getProperty("line.separator"));
                    writer.flush();
                }            
            } catch (IOException e) {
                throw new RuntimeException("tail: cannot open " + tailArg);
            }

        }
    }
}
