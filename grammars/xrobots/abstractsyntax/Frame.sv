grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal Frame with name, pp, errors, bindings;

function lookup_b
Type ::= binds::[Binding] n::Id_t
{
  return if null(binds)
         then  errorType()
         else if (head(binds)).name.lexeme == n.lexeme
              then (head(binds)).type
              else lookup_b(tail(binds), n);
}

  
function found_b
Boolean ::= binds::[Binding] n::Id_t
{
  return if null(binds)
         then false
         else if (head(binds)).name.lexeme == n.lexeme
              then true
              else found_b(tail(binds), n);
}


abstract production newFrame
f::Frame ::= n::Id_t binds::[Binding]
{
 f.pp       = "new Frame " ++ n.lexeme ++ 
              ":\n\tBinding:\n\t\t" ++ printBindings(binds) ++ "\n";
 f.name     = n;
 f.bindings = binds;
 f.errors   = [];
}

abstract production emptyFrame
f::Frame ::= 
{
 f.pp       = "EmptyFrame()\n";
 f.name     = terminal(Id_t, "EmptyFrame", 0, 0);
 f.bindings = [];
 f.errors   = [];
}

abstract production addBinding
fResult::Frame ::=  f1::Frame n::Id_t t::Type
{
 fResult.pp       = "Adding Dec(name: " ++ n.lexeme ++ ", type: " ++
                      t.pp ++         ") to frame " ++ f1.pp ++ "\n";
 fResult.name     = f1.name;
 fResult.bindings = f1.bindings ++ [newBinding(n, t)];
 fResult.errors   = f1.errors;
 }

abstract production combineFrameBindings
fResult::Frame ::=  f1::Frame f2::Frame
{
 fResult.pp       = "Combining " ++ f1.pp ++ " and " ++ f2.pp ++ "\n";
 fResult.name     = f1.name;
 fResult.bindings = f1.bindings ++ f2.bindings;
 fResult.errors   = f1.errors ++ f2.errors;
}
 
abstract production errorFrame
f::Frame ::= 
{
 f.pp       = "error Frame ";
 f.bindings = [];
 f.errors   = mk_error(
		  "Could not find the indexed frame in the stack.", 0, 0);
}

    
abstract production namedErrorFrame
f::Frame ::= n::Id_t 
{
 f.pp       = "error Frame ";
 f.name     = n;
 f.bindings = [];
 f.errors   = mk_error(
		  "Could not find the frame " ++ n.lexeme ++ " in the stack.",
		       n.line,  n.column);
}