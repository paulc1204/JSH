package uk.ac.ucl.jsh.applications;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.ucl.jsh.Eval;
import uk.ac.ucl.jsh.Jsh;
import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;

public class SortTest {

    @Before
    public void setDirectory() {
        Jsh.setCurrentDir(Paths.get("src", "test", "java", "uk", "ac", "ucl", "jsh", "applications", "sortTests").toAbsolutePath().toString());
    }

    @Test
    public void testSort() throws Exception {
        String ls = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("sort sorty.txt", out);
        assertEquals("a" + ls + "b" + ls + "c", out.toString().trim());
    }

    @Test
    public void testSortReverse() throws Exception {
        String ls = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("sort -r sorty.txt", out);
        assertEquals("c" + ls + "b" + ls + "a", out.toString().trim());
    }

}
