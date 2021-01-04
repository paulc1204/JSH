package uk.ac.ucl.jsh.commands;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public interface Command{

    public void eval(String cmdline, OutputStreamWriter writer);
}