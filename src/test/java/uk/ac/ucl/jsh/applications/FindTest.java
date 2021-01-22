package uk.ac.ucl.jsh.applications;

import uk.ac.ucl.jsh.Eval;
import uk.ac.ucl.jsh.Jsh;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class FindTest {

    @Before
    public void setDirectory() {
        Jsh.setCurrentDir(Paths.get("src", "test", "java", "uk", "ac", "ucl", "jsh", "applications", "findTests").toAbsolutePath().toString());
    }
    
    @Test
    public void testFind() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("find -name sample.txt", out);
        assertEquals(Jsh.getCurrentDir() + File.separator + "sample.txt", out.toString().trim());
    }

    @Test
    public void testFindGlobbing() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("find -name *.txt", out);
        assertEquals(Jsh.getCurrentDir() + File.separator + "sample.txt" + System.lineSeparator() +
                     Jsh.getCurrentDir() + File.separator + "sample2.txt", 
                     out.toString().trim());
    }
}
