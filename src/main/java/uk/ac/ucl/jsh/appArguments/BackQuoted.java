package uk.ac.ucl.jsh.appArguments;

public class BackQuoted extends AppArg{

    public BackQuoted(String argument) {
        super(argument);
    }

    @Override
    public Boolean trimmed() {
        return false;
    }
    
}
