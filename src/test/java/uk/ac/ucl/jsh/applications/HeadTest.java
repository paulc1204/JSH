package uk.ac.ucl.jsh.applications;

import uk.ac.ucl.jsh.Eval;
import uk.ac.ucl.jsh.Jsh;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;


public class HeadTest {
    @Before
    public void setDirectory() {
        Jsh.setCurrentDir(Paths.get("src", "test", "java", "uk", "ac", "ucl", "jsh", "applications", "headTests")
                .toAbsolutePath().toString());
    }
    
    @Test
    public void testHead() throws Exception {
        String ls = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Eval.eval("head -n 3 sample.txt", out);
        assertEquals("aaa" + ls + "bbb" + ls + "ccc", out.toString().trim());
    }

    @Test
    public void testHeadOver() throws Exception {
        String ls = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Eval.eval("head -n 100 sample.txt", out);
        assertEquals("aaa" + ls + "bbb" + ls + "ccc" + ls + "ddd" + ls + "eee", out.toString().trim());
    }
}
 