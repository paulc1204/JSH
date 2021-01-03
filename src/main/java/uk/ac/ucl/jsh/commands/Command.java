package uk.ac.ucl.jsh.commands;

import java.io.InputStream;
import java.io.OutputStream;

public interface Command{

    public void eval(InputStream input, OutputStream output);
}