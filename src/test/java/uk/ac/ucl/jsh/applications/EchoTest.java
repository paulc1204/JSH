package uk.ac.ucl.jsh.applications;

import org.junit.Test;

import uk.ac.ucl.jsh.Eval;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;

public class EchoTest {

    @Test
    public void testEcho() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("echo foo bar", out);
        assertEquals("foo bar", out.toString().trim());
    }

}
