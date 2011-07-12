grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal TransList
                with pp, 
                     htrans,
                     stack,
                     nameIn,
                     tabs,
                     errors;

abstract production translist_empty
tl:: TransList ::= 
{
 tl.pp            = "empty trans list" ;
 tl.htrans        ="end \n" ++
{--- tl.tabs ++ "trans" ++ tl.nameIn.lexeme ++ "Fnc :: " ++
                                "Stack Frame -> StackPointer -> " ++
                                 "(Bool, Stack Frame)\n" ++ 
                    tl.tabs ++ "trans" ++ tl.nameIn.lexeme ++ "Fnc "
                             ++ "stack sp = (True, stack)" ++---}
 "\n------------------------------------------------------------------------\n";
 tl.errors        = [];
}

abstract production translist_cons
tl:: TransList ::= t::Trans tlIn::TransList
{
 tl.pp            = t.pp ++ "\n" ++ tlIn.pp ;
 tl.htrans        = t.htrans  ++ ",\n" ++ tlIn.htrans;
 tl.errors        = t.errors  ++ tlIn.errors;

 t.nameIn         = tl.nameIn;
 tlIn.nameIn      = tl.nameIn;

 t.tabs           = tl.tabs;
 tlIn.tabs        = tl.tabs;

 t.stack          = tl.stack;
 tlIn.stack       = tl.stack;
}
  
function mk_ProcTransfnc
String ::= tl::TransList tabs::String
{
  return tabs ++ 
         "processTrans :: [Stack Frame -> StackPointer -> (bool,Stack Frame)]\n"
         ++ tabs ++ "\t\t\t -> (bool, Stack Frame)\n" ++
         tabs ++ "processTrans transFncList =\n" ++
         tabs ++ "\t if transFncList == []\n" ++
         tabs ++ "\t\t then (False, Stack)\n" ++
         tabs ++ "\t\t else if fst((head transFncList) stack si)\n" ++
         tabs ++ "\t\t\t then ((head transFncList) stack si\n" ++
         tabs ++ "\t\t\t else processTrans (tail transFncList)\n" ++
 "------------------------------------------------------------------------\n";
}
   
  