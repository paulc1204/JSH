package uk.ac.ucl.jsh.applications;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import uk.ac.ucl.jsh.Eval;
import uk.ac.ucl.jsh.Jsh;
import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;

public class UniqTest {

    @Before
    public void setDirectory() {
        Jsh.setCurrentDir(Paths.get("src", "test", "java", "uk", "ac", "ucl", "jsh", "applications", "uniqTests").toAbsolutePath().toString());
    }

    @Test
    public void testUniqSensitive() throws Exception {
        String ls = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("uniq uniqTest.txt", out);
        assertEquals("aaa"+ls+"AAA"+ls+"bbb"+ls+"BBB"+ls+"ccc"+ls+"CCC", out.toString().trim());
    }

    @Test
    public void testUniqInsensitive() throws Exception {
        String ls = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("uniq -i uniqTest.txt", out);
        assertEquals("aaa"+ls+"bbb"+ls+"ccc", out.toString().trim());
    }

}
