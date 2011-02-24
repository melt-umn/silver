grammar silver:modification:ffi;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type;

terminal Type_kwd 'type'; -- no keyword class...

-- TODO: this should provide some sort of translation type
-- but right now, we don't. Phooey.

-- TODO: uhhh 'nonterminal foreign type' is a dumb declaration. fix this

concrete production ffiTypeDcl
top::AGDcl ::= 'nonterminal' 'foreign' 'type' id::Name ';' -- '{' ffidefs::FFIDefs '}'
{
  top.pp = "nonterminal foreign type " ++ id.pp ++ ";"; -- "{\n" ++ ffidefs.pp ++ "}";
  top.location = loc(top.file, $1.line, $1.column);
  
  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  -- TODO: this is horrifying. ntDcls are actually generic "add a new named type"
  -- but, given they're name, that's not obvious.
  -- We should either rename them, or add a new 'ftDcl' or something.
  top.defs = addNtDcl(top.grammarName, id.location, fName, [], foreignTypeExp(fName), emptyDefs());

  top.errors := [];
  top.errors <- 
       if length(getTypeDcl(fName, top.env)) > 1 
       then [err(top.location, "Type '" ++ fName ++ "' is already bound.")]
       else [];

  top.errors <-
       if isLower(substring(0,1,id.name))
       then [err(id.location, "Types must be capitalized. Invalid nonterminal name " ++ id.name)]
       else [];
  
  forwards to agDclDefault();
}

