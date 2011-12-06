grammar silver:modification:copper;

terminal Pluck_kwd 'pluck' lexer classes {KEYWORD};
terminal Print_kwd 'print' lexer classes {KEYWORD};

concrete production namePrint
top::Name ::= 'print'
{ forwards to nameIdLower(terminal(IdLower_t, "print", $1)); }

concrete production namePluck
top::Name ::= 'pluck'
{ forwards to nameIdLower(terminal(IdLower_t, "pluck", $1)); }


concrete production pluckDef
top::ProductionStmt ::= 'pluck' e::Expr ';'
{
  top.pp = "pluck " ++ e.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.translation = "return " ++ e.translation ++ ";\n";

  top.errors := (if !top.blockContext.permitPluck
               then [err(top.location, "'pluck' allowed only in disambiguation-group parser actions.")]
               else [])
               ++ e.errors;

  -- TODO: figure out wtf is going on with type here! (needs to be a terminal, plus one of the ones in the disgroup)

  e.downSubst = top.downSubst;
  
  top.upSubst = e.upSubst;
  
  forwards to defaultProductionStmt();
}

concrete production printStmt
top::ProductionStmt ::= 'print' e::Expr ';'
{
  top.pp = "print " ++ e.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  top.translation = "System.err.println(" ++ e.translation ++ ");\n";

  top.errors := (if !top.blockContext.permitActions
               then [err(top.location, "'print' statement allowed only in parser action blocks. You may be looking for print(String,IO) :: IO.")]
               else [])
               ++ e.errors;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;
  
  errCheck1 = check(e.typerep, stringTypeExp());
  top.errors <-
       if errCheck1.typeerror
       then [err(e.location, "print expects a string, instead it recieved a " ++ errCheck1.leftpp)]
       else [];
  
  forwards to defaultProductionStmt();
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  -- TODO see ugly hack in ActionCode.sv
}

abstract production parserAttributeValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e.errors ++
               (if !top.blockContext.permitActions
                then [err(val.location, "Assignment to parser attributes only permitted in parser action blocks")]
                else []);

  top.translation = makeCopperName(val.lookupValue.fullName) ++ " = " ++ e.translation ++ ";\n";

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;

  errCheck1 = check(e.typerep, val.lookupValue.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Value " ++ val.name ++ " has type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
       else [];
  
  forwards to defaultProductionStmt();
}

abstract production parserAttributeDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if !top.blockContext.permitActions
                then [err(q.location, "Parser attributes can only be used in action blocks")]
                else [err(q.location, "Parser action blocks are imperative, not declarative. You cannot modify the attributes of " ++ q.name ++ ". If you are trying to set inherited attributes, you should use 'decorate ... with { ... }' when you create it.")];

  top.typerep = q.lookupValue.typerep;
}

abstract production termAttrValueValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  -- these values should only ever be in scope when it's valid to use them
  top.errors := e.errors;

  local attribute memberfunc :: String;
  memberfunc = if val.name == "filename" then "setFileName" else
               if val.name == "line" then "setLine" else
               if val.name == "column" then "setColumn" else
               error("unknown assignment to terminal attribute: " ++ val.name);

  top.translation = "virtualLocation." ++ memberfunc ++ "(" ++ e.translation
                     ++ (if val.name == "filename" then ".toString()" else "") ++ ");\n";

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.downSubst = top.downSubst;
  errCheck1.downSubst = e.upSubst;
  top.upSubst = errCheck1.upSubst;

  errCheck1 = check(e.typerep, val.lookupValue.typerep);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Value " ++ val.name ++ " has type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
       else [];
  
  forwards to defaultProductionStmt();
}

