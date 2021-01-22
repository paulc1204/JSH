package uk.ac.ucl.jsh.applications;

import uk.ac.ucl.jsh.Eval;
import uk.ac.ucl.jsh.Jsh;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class CommandTest {

    final String ls = System.lineSeparator();
    @Before
    public void setDirectory() {
        Jsh.setCurrentDir(Paths.get("src", "test", "java", "uk", "ac", "ucl", "jsh", "applications", "findTests").toAbsolutePath().toString());
    }
    
    @Test
    public void testSequence() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("find -name sample.txt; echo foo", out);
        assertEquals(Jsh.getCurrentDir() + File.separator + "sample.txt" + ls + "foo", out.toString().trim());
    }

    @Test
    public void testSequence2() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("cd ..; cd findTests; find -name sample.txt; echo foo", out);
        assertEquals(Jsh.getCurrentDir() + File.separator + "sample.txt" + ls + "foo", out.toString().trim());
    }

    //test that Error resulting from unsafe call of app gets printed to standard output
    @Test
    public void testUnsafeCall() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("_find sample.txt", out);
        assertEquals("java.lang.RuntimeException: find: wrong arguments", out.toString().trim());
    }
}
