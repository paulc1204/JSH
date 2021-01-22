package uk.ac.ucl.jsh.applications;

import uk.ac.ucl.jsh.Eval;
import uk.ac.ucl.jsh.Jsh;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.file.Paths;

import org.junit.Test;

public class PwdTest {
    
    @Test
    public void testPwd() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Eval.eval("pwd", out);
        assertEquals(Paths.get(Jsh.getCurrentDir()).toAbsolutePath().toString(), 
                     out.toString().trim());
    }

}
