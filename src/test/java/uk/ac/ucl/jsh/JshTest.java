package uk.ac.ucl.jsh;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Scanner;

public class JshTest {

    @Test
    public void testJsh() throws Exception {
        PipedInputStream in = new PipedInputStream();
        PipedOutputStream out;
        out = new PipedOutputStream(in);
        Eval.eval("echo foo", out);
        Scanner scn = new Scanner(in);
        assertEquals(scn.next(),"foo");
        scn.close();
    }

}
