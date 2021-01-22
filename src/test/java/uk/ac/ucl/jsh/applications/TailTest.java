package uk.ac.ucl.jsh.applications;

import uk.ac.ucl.jsh.Eval;
import uk.ac.ucl.jsh.Jsh;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class TailTest {
    @Before
    public void setDirectory() {
        Jsh.setCurrentDir(Paths.get("src", "test", "java", "uk", "ac", "ucl", "jsh", "applications", "tailTests")
                .toAbsolutePath().toString());
    }
    @Test
    public void testTail() throws Exception {
        String ls = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Eval.eval("tail -n 3 sample.txt", out);
        assertEquals("c" + ls + "d" + ls + "e", out.toString().trim());
    }

    @Test
    public void testTailOver() throws Exception {
        String ls = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        Eval.eval("tail -n 100 sample.txt", out);
        assertEquals("a" + ls + "b" + ls + "c" + ls + "d" + ls + "e", out.toString().trim());
    }
}
