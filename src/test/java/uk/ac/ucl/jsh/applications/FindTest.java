package uk.ac.ucl.jsh.applications;

import uk.ac.ucl.jsh.Eval;
import uk.ac.ucl.jsh.Jsh;
import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class FindTest {

    @Before
    public void setDirectory() {
        Jsh.setCurrentDir(Paths.get("src", "test", "java", "uk", "ac", "ucl", "jsh", "applications", "findTests")
                .toAbsolutePath().toString());
    }
    
    @Test
    public void testFind() throws IOException {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Eval.eval("find -name sample.txt", out);
        Scanner scn = new Scanner(in);
        assertEquals(Jsh.getCurrentDir() + File.separator + "sample.txt", scn.next());
        scn.close();
    }
}
