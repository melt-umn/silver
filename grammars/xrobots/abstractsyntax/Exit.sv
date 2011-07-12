grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal Exit
                with pp, 
                     type,
                     htrans,
                     stack,
                     haskellStack,
                     nameIn,
                     tabs,
                     errors;

abstract production makeExit
e::Exit  ::= s::Stmt
{
 e.pp           = s.pp ;
 e.type         = intType();
 s.haskellStack = "stack";
 e.htrans       = e.tabs ++ "exit" ++ e.nameIn.lexeme
                  ++ "Fnc :: Stack Frame -> StackPointer -> Stack Frame\n" ++
                  e.tabs ++ "exit" ++ e.nameIn.lexeme
                            ++"Fnc stack si = ts\n"++ 
                  e.tabs ++ "\t" ++"where\n" ++
                  e.tabs ++ "\t\t" ++"ts  =  " ++ s.htrans ++ "\n" ++
 "------------------------------------------------------------------------\n";
 e.errors       = s.errors ;
 s.stack        = e.stack;
}


abstract production emptyExit
e:: Exit ::= 
{
 e.pp            = "emptyExit" ;
 e.type          = intType();
 e.htrans        =  e.tabs ++ "exit" ++ e.nameIn.lexeme
                           ++ "Fnc :: Stack Frame -> StackPointer " ++
                              " -> Stack Frame\n" ++
                    e.tabs ++ "exit" ++ e.nameIn.lexeme
                           ++ "Fnc stack si = stack\n"++ 
"------------------------------------------------------------------------\n";
 e.errors        = [];
}	      