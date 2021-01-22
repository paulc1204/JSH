package uk.ac.ucl.jsh.appArguments;

public abstract class AppArg {
    
    private String argument;

    public AppArg(String argument){
        this.argument = argument;
    }

    public abstract Boolean trimmed();
}
