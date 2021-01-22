package uk.ac.ucl.jsh.applications;

import uk.ac.ucl.jsh.Eval;
import uk.ac.ucl.jsh.Jsh;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;


public class LsTest {
    @Before
    public void setDirectory() {
        Jsh.setCurrentDir(Paths.get("src", "test", "java", "uk", "ac", "ucl", "jsh", "applications", "findTests")
                .toAbsolutePath().toString());
    }

    @Test
    public void testLs() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("ls", out);
        assertEquals("sample.txt" + "\t" + "sample2.txt", out.toString().trim());
    }
}
