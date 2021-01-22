grammar JshGrammar;

@header {
	import uk.ac.ucl.jsh.appArguments.AppArg; 
	import uk.ac.ucl.jsh.appArguments.BackQuoted; 
    import uk.ac.ucl.jsh.appArguments.SingleQuoted;
    import uk.ac.ucl.jsh.appArguments.DoubleQuoted;
    import uk.ac.ucl.jsh.appArguments.NonSpecial;
}
/*
 * Parser Rules
 */

command : call | seq | pipe;

call
    : appName = NONSPECIAL (appArgs += arg)* ('<' inputArg = arg)? ('>' outputArg = arg)?   #iORedir
    | ('<' inputArg = arg)? appName = NONSPECIAL (appArgs += arg)* ('>' outputArg = arg)?   #iORedirFront
    | substitution = BACKQUOTED (appArgs += arg)*                                           #commandSubst
    ;

seq
    : left = call (';' right += command)+    #callSequence 
    | left = pipe (';' right += command)+    #pipeSequence
    ;

pipe
    : left = call ('|' right += call)+
    ;

arg 
    returns[AppArg argument]:
    nonSpecial = NONSPECIAL { $argument = new NonSpecial($nonSpecial.text); }
    | singleQuoted = SINGLEQUOTED { $argument = new SingleQuoted($singleQuoted.text); }
    | doubleQuoted = DOUBLEQUOTED { $argument = new DoubleQuoted($doubleQuoted.text); }
    | backQuoted = BACKQUOTED { $argument = new BackQuoted($backQuoted.text); }
    ;
   
/*
 * Lexer Rules
 */

NONSPECIAL : ~[ \r\n\t'";|<>`]+;
SINGLEQUOTED : '\'' (~[\n'])* '\'';
DOUBLEQUOTED : '"' (~[\n"])* '"';
BACKQUOTED: '`' (~[\n`])* '`';
WS: [ \r\n\t]+ -> skip;

