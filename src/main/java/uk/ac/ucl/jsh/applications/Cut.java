package uk.ac.ucl.jsh.applications;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.regex.Pattern;

import uk.ac.ucl.jsh.Jsh;

public class Cut implements Application{

    String fileName;

    @Override
    public void exec(ArrayList<String> appArgs, InputStream input, OutputStream output) throws IOException {
        boolean option = false;

        OutputStreamWriter writer = new OutputStreamWriter(output);
        if (appArgs.isEmpty()) {
            throw new RuntimeException("cut: missing arguments");
        }
        if (appArgs.size() > 3) {
            throw new RuntimeException("cut: wrong arguments");
        }
        option = appArgs.get(0).equals("-b");
        if (!option){
            throw new RuntimeException("cut: wrong arguments");
        }
        fileName = Jsh.getCurrentDir() + File.separator + appArgs.get(2);

        try(Stream<String> lines = Files.lines(Paths.get(fileName))){
            String pattern1 = ".*,.*";
            String pattern2 = ".*-.*";
            String pattern3 = "-.*";
            String pattern4 = ".*-";

            if (Pattern.matches(pattern1, appArgs.get(0))){
                String[] split = pattern1.split(appArgs.get(0));
                for (String s: split){
                    if (Pattern.matches(pattern2, s)){
                            lines.forEach(l -> {
                                int start1 = Integer.valueOf(s.charAt(0));
                                int end1 = Integer.valueOf(s.charAt(2));
                                for (int i = start1; i < end1+1; i++){
                                try {
									writer.write(l.charAt(i));
	                                writer.write(System.getProperty("line.separator"));
	                                writer.flush();
								} catch (IOException e) {
									e.printStackTrace();
								}

                        }

                    });
                    if (Pattern.matches(pattern3, s)){
                            lines.forEach(l -> {
                                int end2 = Integer.valueOf(s.charAt(1));
                                for (int i = 0;i < end2 + 1; i++){
                                try {
									writer.write(l.charAt(i));
	                                writer.write(System.getProperty("line.separator"));
	                                writer.flush();
								} catch (IOException e) {
									e.printStackTrace();
								}

                        }
                        });
                    }
                    if (Pattern.matches(pattern4, s)){

                        lines.forEach(l -> {
                            int start3 = Integer.valueOf(s.charAt(0));
                            for (int i = start3;i < l.length(); i++){
                                try {
									writer.write(l.charAt(i));
	                                writer.write(System.getProperty("line.separator"));
	                                writer.flush();
								} catch (IOException e) {
									e.printStackTrace();
								}

                            }
                            
                        });
                    }
                    else {
                        int val = Integer.valueOf(s.charAt(0));
                        lines.forEach(l -> {
                            try {
                                writer.write(l.charAt(val));
								writer.write(System.getProperty("line.separator"));
	                            writer.flush();
							} catch (IOException e) {
								e.printStackTrace();
							}

                        });
                    }
                    }   
                }

            } 
            else{
            int val = Integer.valueOf(appArgs.get(0));
            lines.forEach(l -> {
                try {
					writer.write(l.charAt(val));
	                writer.write(System.getProperty("line.separator"));
	                writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
                }
                

                });
            }   
        }
    }
}