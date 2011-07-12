grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal Root with  pp, errors, htrans;

abstract production root
r::Root ::= b::Behavior
{
 r.pp              = b.pp;
 r.htrans          = "module Program where\n\n" ++
                     "import DataTypes\n" ++
                     "import Arithmatic\n" ++
                     "import Stack\n" ++
 "----------------------------------------------------------------------\n\n" ++
                     b.htrans;
 
 r.errors          = b.errors;
 
 b.frameIn         = newFrame(terminal(Id_t, "Root Frame", 0 ,0), []); 
 b.stack           = empty_stack();
 b.haskellStack    = "stack";
 b.tabs            = "";
 b.nameIn          =terminal(Id_t, "Root Frame", 0 ,0);
 
}
