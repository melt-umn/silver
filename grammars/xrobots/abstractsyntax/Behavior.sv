grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal Behavior
                with pp, 
                     type,
                     name,
                     htrans,
                     stack,
                     haskellStack,
                     frameIn,
                     nameIn, -- parent Name
                     tabs,
                     errors;

abstract production behavior
 b::Behavior ::= n::Id_t params::DecList decs::DecList
		 bl::BehaviorList entry::Entry trans::TransList ex::Exit
{
 b.pp           = "Behavior " ++  n.lexeme ++ "(" ++ params.pp ++ "){\n"
                  ++ bl.pp ++ decs.pp ++ entry.pp ++ "\n\n" ++ ex.pp ++ "\n}";
 b.type         = behaviorType(decsToTypes(params.frameSyn.bindings));
 b.name         = n;
 b.htrans       = b.tabs ++ n.lexeme ++ "BR = \n" ++
                  b.tabs ++ "  BehaviorRecord{\n" ++
                  b.tabs ++ "\t"++ "behaviorName = \"" ++ n.lexeme ++ "\",\n" ++
                  b.tabs ++ "\t"++ "parentName      = \"" ++ b.nameIn.lexeme
                  ++ "\",\n" ++
                  b.tabs ++ "\t"++ "params          = " ++ params.decHTrans 
                  ++ ",\n"  ++
                  b.tabs ++ "\t"++ "decs            = " ++ decs.decHTrans 
                  ++ ",\n" ++
                  b.tabs ++ "\t" ++ "entryFn        = " 
                                        ++ "enter" ++ n.lexeme ++ "Fnc,\n" ++
                  b.tabs ++ "\t" ++ "transFn        = " 
                                        ++ "trans" ++ n.lexeme ++ "Fnc,\n" ++
                  b.tabs ++ "\t" ++ "exitFn         = " 
                                        ++ "exit" ++ n.lexeme ++ "Fnc,\n" ++
                  b.tabs ++ "\t" ++ "childBehaviors = [" ++
                                      bl.childBehaviors ++ "]\n" ++
                  b.tabs ++ "  }\n" ++
                  b.tabs ++ "\t" ++ "where\n" ++
                   entry.htrans ++
                   ex.htrans ++
                   trans.htrans ++
                   bl.htrans ;
 entry.nameIn   = n;
 ex.nameIn      = n;
 bl.nameIn      = n;
 trans.nameIn   = n;
 ----------------------------------------------------------------
 entry.tabs     = b.tabs ++ "\t  ";
 ex.tabs        = b.tabs ++ "\t  ";
 bl.tabs        = b.tabs ++ "\t  ";
 trans.tabs     = b.tabs ++ "\t  ";
 -----------------------------------------------------------------------
 entry.haskellBindings
                = params.bindingHTrans ++ "++" ++ decs.bindingHTrans
                  ++ " ++ " ++ bl.subBehaviorDecs.bindingHTrans;
 -------------------------------------------------------------------------
 entry.stack    = push(b.stack, 
                       combineFrameBindings(decs.frameSyn, params.frameSyn));
 ex.stack       = push(b.stack, 
                       combineFrameBindings(decs.frameSyn, params.frameSyn));
 bl.stack       = push(b.stack, 
                       combineFrameBindings(decs.frameSyn, params.frameSyn));
 trans.stack    = push(b.stack, 
                       combineFrameBindings(decs.frameSyn, params.frameSyn));
 --------------------------------------------------------------------------
 b.errors       =     
          if found_b(b.frameIn.bindings, n)
              then  mk_error(n.lexeme ++  
                           " has alread been declared in this behavior.",
	 		   n.line, n.column)
               else params.errors ++ decs.errors ++ bl.errors
                           ++ entry.errors ++ trans.errors  ++ ex.errors ;

 -----------------------------------------------------------------------------
 params.frameIn = newFrame(n, []);
 decs.frameIn   = params.frameSyn; 
 bl.frameIn     = decs.frameSyn; 
}	

function decsToTypes
[Type] ::= bl::[Binding]
{
 return if null(bl)
        then []
        else [(head(bl)).type] ++ decsToTypes(tail(bl));
}	 