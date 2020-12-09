package uk.ac.ucl.jsh.commands;

import java.io.OutputStream;

public interface Command{

    public void eval(String cmdline, OutputStream output);
}