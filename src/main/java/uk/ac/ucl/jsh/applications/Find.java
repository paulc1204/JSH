package uk.ac.ucl.jsh.applications;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import static java.nio.file.FileVisitOption.*;
import java.util.*;

public class Find extends SimpleFileVisitor<Path> implements Application<OutputStreamWriter> {

    private final PathMatcher matcher;
    private OutputStreamWriter writer;
    private String pattern;
    private Path rootPath;

    public Find(Path rootPath, String pattern) {
        this.rootPath = rootPath;
        this.pattern = pattern;
        matcher = FileSystems.getDefault().getPathMatcher("glob:" + this.pattern);
        
    }

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

    // @Override
    // public void exec(String[] args, BufferedReader input, OutputStream output) {
        
    // }

	@Override
	public void exec(String[] args, BufferedReader input, OutputStreamWriter writer) {
        this.writer = writer;
		try {
            Files.walkFileTree(this.rootPath, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}

