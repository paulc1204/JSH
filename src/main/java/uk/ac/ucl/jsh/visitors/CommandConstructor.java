package uk.ac.ucl.jsh.visitors;

import java.util.ArrayList;
import java.util.List;

import uk.ac.ucl.jsh.JshGrammarBaseVisitor;
import uk.ac.ucl.jsh.JshGrammarParser;
import uk.ac.ucl.jsh.JshGrammarParser.*;
import uk.ac.ucl.jsh.appArguments.AppArg;
import uk.ac.ucl.jsh.commands.Command;
import uk.ac.ucl.jsh.commands.*;

public class CommandConstructor extends JshGrammarBaseVisitor<Command> {

	@Override public Command visitIORedir(JshGrammarParser.IORedirContext ctx) {   
        String appName = ctx.appName.getText();
        Boolean unsafe = appName.startsWith("_");
        ArrayList<String> appArgs = new ArrayList<String>();

        for(ArgContext arg : ctx.appArgs){
            if(arg.argument.trimmed()){
                appArgs.add(arg.getText());
            }else{
                appArgs.add(arg.getText().substring(1, arg.getText().length()-1));
            }
        }

        if(unsafe) appName = appName.substring(1);
        
        Call call = new Call(appName, appArgs);

        return unsafe? new UnsafeCall(call): call;
    }

	@Override public Command visitPipeSequence(JshGrammarParser.PipeSequenceContext ctx) {   
        Sequence seq = new Sequence();
        seq.addCommand(visit(ctx.left));
        for(CommandContext comCtx : ctx.right){
            seq.addCommand(visit(comCtx));
        }

        return seq;
    }

	@Override public Command visitCallSequence(JshGrammarParser.CallSequenceContext ctx) {   
        Sequence seq = new Sequence();
        seq.addCommand(visit(ctx.left));
        for(CommandContext comCtx : ctx.right){
            seq.addCommand(visit(comCtx));
        }

        return seq;
    }

	@Override public Command visitPipe(JshGrammarParser.PipeContext ctx) {   
        Pipe pipe = new Pipe();
        pipe.addCommand(visit(ctx.left));
        for(CallContext callCtx : ctx.right){
            pipe.addCommand(visit(callCtx));
        }

        return pipe;
    }

}
