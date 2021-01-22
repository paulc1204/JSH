package uk.ac.ucl.jsh.applications;

import uk.ac.ucl.jsh.Eval;
import uk.ac.ucl.jsh.Jsh;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CdTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();
    
    @Before
    public void setDirectory() {
        Jsh.setCurrentDir(Paths.get("src").toAbsolutePath().toString());
    }
    
    @Test
    public void testCd() throws Exception {
        Eval.eval("cd main", null);
        assertEquals(Paths.get("src", "main").toAbsolutePath().toString(), 
                     Paths.get(Jsh.getCurrentDir()).toAbsolutePath().toString());
    }

    @Test
    public void testWrongDir() throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        exception.expect(RuntimeException.class);
        Eval.eval("cd wrong", out);
    }
}
