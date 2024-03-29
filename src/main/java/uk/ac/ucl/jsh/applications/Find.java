package uk.ac.ucl.jsh.applications;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import java.util.*;

import uk.ac.ucl.jsh.Jsh;

public class Find extends SimpleFileVisitor<Path> implements Application {

    private PathMatcher matcher;
    private OutputStreamWriter writer;
    private String pattern;
    private Path rootPath;

    private void find(Path file) throws IOException {
        Path name = file.getFileName();
        if (name != null && matcher.matches(name)) {
            writer.write(file.toString());
            writer.write(System.getProperty("line.separator"));
            writer.flush();
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        find(file);
        return CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        find(dir);
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        System.err.println(exc);
        return CONTINUE;
    }

	@Override
	public void exec(ArrayList<String> appArgs, InputStream input, OutputStream output) {
        if (appArgs.isEmpty()) {
            throw new RuntimeException("find: missing arguments");
        }
        if (appArgs.size() > 3 || appArgs.size() < 2) {
            throw new RuntimeException("find: wrong arguments");
        }  
        if (!appArgs.contains("-name")){
            throw new RuntimeException("find: must contain \'-name\'");
        }

        writer = new OutputStreamWriter(output);
        rootPath = Paths.get(Jsh.getCurrentDir());
        pattern = appArgs.get(1);
        if (appArgs.size() == 3) { 
            rootPath = Paths.get(Jsh.getCurrentDir() + File.separator + appArgs.get(0)); 
            pattern = appArgs.get(2);
        }

        matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
        
		try {
            Files.walkFileTree(this.rootPath, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

