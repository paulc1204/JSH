package uk.ac.ucl.jsh.applications;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.ucl.jsh.Eval;
import uk.ac.ucl.jsh.Jsh;
import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;

public class CatTest {

    @Before
    public void setDirectory() {
        Jsh.setCurrentDir(Paths.get("src", "test", "java", "uk", "ac", "ucl", "jsh", "applications", "catTests").toAbsolutePath().toString());
    }

    @Test
    public void testCat() throws Exception {
        String ls = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("cat cat1.txt", out);
        assertEquals("aaa" + ls + "this is cat1", out.toString().trim());
    }

    @Test
    public void testCatMultiple() throws Exception {
        String ls = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("cat cat1.txt cat2.txt", out);
        assertEquals("aaa" + ls + "this is cat1" + ls + "bbb" + ls + "this is cat2", out.toString().trim());
    }

}
