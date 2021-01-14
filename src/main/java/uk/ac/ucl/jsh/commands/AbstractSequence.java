package uk.ac.ucl.jsh.commands;

import java.util.ArrayList;
import java.util.Iterator;

public class AbstractSequence implements Iterable<Command> {
    public ArrayList<Command> commands;

    public AbstractSequence() {
    }

    public AbstractSequence(ArrayList<Command> commands) {
        this.commands = commands;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    @Override
    public Iterator<Command> iterator() {
        return commands.listIterator();
    }

}
