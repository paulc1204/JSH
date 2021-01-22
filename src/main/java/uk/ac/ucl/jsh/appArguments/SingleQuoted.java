package uk.ac.ucl.jsh.appArguments;

public class SingleQuoted extends AppArg{

    public SingleQuoted(String argument) {
        super(argument);
    }
    
    @Override
    public Boolean trimmed() {
        return false;
    }
}
