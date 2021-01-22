package uk.ac.ucl.jsh.appArguments;

public class NonSpecial extends AppArg{
    
    public NonSpecial(String argument) {
        super(argument);
    }

    @Override
    public Boolean trimmed() {
        return true;
    }

}
