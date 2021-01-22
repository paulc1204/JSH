package uk.ac.ucl.jsh.appArguments;

public class DoubleQuoted extends AppArg {

    public DoubleQuoted(String argument) {
        super(argument);
    }
    
    @Override
    public Boolean trimmed() {
        return false;
    }
}
