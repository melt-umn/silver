grammar silver:modification:ffi;

terminal FFI_kwd 'foreign' lexer classes {KEYWORD,RESERVED};

nonterminal FFIDefs with config, location, grammarName, file, errors, signature, env, pp;
nonterminal FFIDef with config, location, grammarName, file, errors, signature, env, pp;

concrete production functionDclFFI
top::AGDcl ::= 'function' id::Name ns::FunctionSignature body::ProductionBody 'foreign' '{' ffidefs::FFIDefs '}'
{
  top.pp = "function " ++ id.pp ++ "\n" ++ ns.pp ++ "\n" ++ body.pp ++ " foreign {\n" ++ ffidefs.pp ++ "}"; 
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  production attribute namedSig :: NamedSignature;
  namedSig = namedSignature(fName, ns.inputElements, ns.outputElement);

  top.errors <- ffidefs.errors;

  forwards to functionDcl($1, id, ns, body);
}

concrete production ffidefsOne
top::FFIDefs ::= one::FFIDef
{
  top.pp = one.pp;
  top.location = one.location;
  
  top.errors := one.errors;
}

concrete production ffidefsMany
top::FFIDefs ::= one::FFIDef more::FFIDefs
{
  top.pp = one.pp ++ more.pp;
  top.location = one.location;
  
  top.errors := one.errors ++ more.errors;
}

concrete production ffidef
top::FFIDef ::= name::String_t ':' 'return' code::String_t ';'
{
  top.pp = name.lexeme ++ ": return " ++ code.lexeme ++ ";\n";
  top.location = loc(top.file, name.line, name.column);
  
  top.errors := [];
  -- Up to each translation to do something with this.
}

