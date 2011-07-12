grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal BehaviorList
                with pp, 
                     htrans,
                     stack,
                     haskellStack,
                     frameIn,
                     nameIn, -- parent Name
                     tabs,
                     errors,
                     childBehaviors,
                     subBehaviorDecs;

abstract production behaviorlist_empty
bl::BehaviorList ::=
{
 bl.pp     = "Empty BehaviorList";
 bl.htrans = "";
 bl.errors = [];
 bl.childBehaviors = "";
 bl.subBehaviorDecs =declist_empty();
}		    
		    

abstract production behaviorlist_cons
bl::BehaviorList ::= b::Behavior blIn::BehaviorList
{
 bl.pp       = b.pp ++ "\n" ++ blIn.pp;
 bl.htrans   = b.htrans ++ blIn.htrans;
 bl.errors   = b.errors ++ blIn.errors;
 bl.childBehaviors 
             = if blIn.childBehaviors == ""
               then b.name.lexeme ++ "BR"
               else b.name.lexeme ++ "BR, " ++ blIn.childBehaviors;
 bl.subBehaviorDecs = declist_cons(declare_hn(b.type, b.name),
                                             blIn.subBehaviorDecs);
 
 b.stack     = bl.stack;
 blIn.stack  = bl.stack;

 b.tabs      = bl.tabs;
 blIn.tabs   = bl.tabs;

 b.nameIn   = bl.nameIn;
 blIn.nameIn = bl.nameIn;

 b.frameIn   = bl.frameIn;
 blIn.frameIn = bl.frameIn;

 b.haskellStack  = bl.haskellStack;
 blIn.haskellStack = bl.haskellStack;
}