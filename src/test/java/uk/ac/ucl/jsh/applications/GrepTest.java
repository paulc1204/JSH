package uk.ac.ucl.jsh.applications;
import uk.ac.ucl.jsh.Eval;
import uk.ac.ucl.jsh.Jsh;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;


public class GrepTest {
    @Before
    public void setDirectory() {
        Jsh.setCurrentDir(Paths.get("src", "test", "java", "uk", "ac", "ucl", "jsh", "applications", "grepTests")
                .toAbsolutePath().toString());
    }
    
    @Test
    public void testGrep() throws Exception {
        String ls = System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("grep hello sample.txt", out);
        assertEquals("helloPritika" + ls + "helloArib" + ls + "helloPaul", out.toString().trim());
    }
}
