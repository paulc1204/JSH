package uk.ac.ucl.jsh.commands;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class AbstractCommands implements Iterable<Command> {
    protected ArrayList<Command> commands;

    public AbstractCommands() {
        this.commands = new ArrayList<Command>();
    }

    public AbstractCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }

    public void setCommands(ArrayList<Command> commands){
        this.commands = commands;
    }

    public void addCommand(Command command){
        commands.add(command);
        
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    @Override
    public Iterator<Command> iterator() {
        return commands.listIterator();
    }

}
