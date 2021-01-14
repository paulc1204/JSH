package uk.ac.ucl.jsh;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Jsh {

    public static final Path ROOT_DIRECTORY = Paths.get(System.getProperty("user.dir")); 
    private static String currentDirectory = ROOT_DIRECTORY.toString();

    public static void setCurrentDir(String dir){
        currentDirectory = Paths.get(dir).toAbsolutePath().toString();
    }

    public static String getCurrentDir(){
        return currentDirectory;
    }

    public static void main(String[] args) {

        if (args.length > 0) {
            if (args.length != 2) {
                System.out.println("jsh: wrong number of arguments");
                return;
            }
            if (!args[0].equals("-c")) {
                System.out.println("jsh: " + args[0] + ": unexpected argument");
            }
            try {
                Eval.eval(args[1], System.out);
            } catch (Exception e) {
                System.out.println("jsh: " + e.getMessage());
            }
        } else {
            Scanner input = new Scanner(System.in);
            try {
                while (true) {
                    String prompt = currentDirectory + "> ";
                    System.out.print(prompt);
                    try {
                        String cmdline = input.nextLine();
                        Eval.eval(cmdline, System.out);
                    } catch (Exception e) {
                        System.out.println("jsh: " + e.getMessage());
                    }
                }
            } finally {
                input.close();
            }
        }
    }

}
