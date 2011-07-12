grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal Entry with pp, 
                     type,
                     htrans,
                     stack,
                     haskellStack,
                     haskellBindings,
                     nameIn,
                     tabs,
                     errors;

abstract production makeEntry
e:: Entry ::= s::Stmt
{
 e.pp            = s.pp ;
 e.type          = intType();
 s.haskellStack = "newStack";
 e.htrans       = e.tabs ++ "enter"++ e.nameIn.lexeme
                           ++ "Fnc :: Stack Frame -> [Value] -> Int\n" ++
                  e.tabs ++ "\t -> (Stack Frame, StackPointer)\n" ++
                  e.tabs ++ "enter" ++ e.nameIn.lexeme 
                         ++ "Fnc stack exprList si = (ts, sp)\n"++ 
                  e.tabs ++ "\t" ++ "where\n" ++
                  e.tabs ++ "\t\t" ++ "ts =  " ++ s.htrans ++ "\n" ++
                  e.tabs ++ "\t\t" ++"(newStack, sp) = \n" ++ 
                  e.tabs ++  "\t\t\t" ++ "push stack\n"++
      		  e.tabs ++ "\t\t\t" ++  "   Frame{\n" ++
		  e.tabs ++ "\t\t\t\t" ++ "dn             =\"" ++ e.nameIn.lexeme
                     ++ "\",\n"++
		  e.tabs ++ "\t\t\t\t" ++ "bindings       = "
                                           ++ e.haskellBindings ++ ",\n" ++
	 	  e.tabs ++ "\t\t\t\t" ++ "behaviorRecord = " ++
                  e.nameIn.lexeme ++ "BR,\n" ++
	 	  e.tabs ++ "\t\t\t\t" ++"staticLink     = si" ++ "\n" ++
                  e.tabs ++ "\t\t\t" ++"   }\n" ++
 "--------------------------------------------------------------------\n";       
 e.errors        = s.errors ;
 s.stack         = e.stack;
}


abstract production emptyEntry
e:: Entry ::= 
{
 e.pp            = "emptyEntry" ;
 e.type          = intType();
 e.htrans        =  e.tabs ++ "enter"++ e.nameIn.lexeme
                           ++ "Fnc :: Stack Frame -> [Value] -> Int\n" ++
                  e.tabs ++ "\t -> (Stack Frame, StackPointer)\n" ++
                  e.tabs ++ "enter" ++ e.nameIn.lexeme 
                         ++ "Fnc stack exprList si = (stack, si)\n";
 e.errors        = [];
}	      