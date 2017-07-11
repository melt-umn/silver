grammar silver:modification:copper;

terminal Pluck_kwd 'pluck' lexer classes {KEYWORD,RESERVED};
terminal Print_kwd 'print' lexer classes {KEYWORD,RESERVED};
terminal PushToken_kwd 'pushToken' lexer classes {KEYWORD,RESERVED};

concrete production namePrint
top::Name ::= 'print'
{ forwards to name("print", top.location); }

concrete production namePluck
top::Name ::= 'pluck'
{ forwards to name("pluck", top.location); }


concrete production pluckDef
top::ProductionStmt ::= 'pluck' e::Expr ';'
{
  top.pp = "pluck " ++ e.pp ++ ";";

  -- Cast to integer is required, because that's secretly the real type of the
  -- result, but our type system only calls it an Object at the moment.
  -- Perhaps this problem can be resolved by using a proper type in this situation.
  top.translation = "return (Integer)" ++ e.translation ++ ";\n";

  top.errors := (if !top.blockContext.permitPluck
               then [err(top.location, "'pluck' allowed only in disambiguation-group parser actions.")]
               else [])
               ++ e.errors;

  -- TODO: figure out wtf is going on with type here! (needs to be a terminal, plus one of the ones in the disgroup)

  e.downSubst = top.downSubst;
  top.upSubst = e.upSubst;
}

concrete production printStmt
top::ProductionStmt ::= 'print' e::Expr ';'
{
  top.pp = "print " ++ e.pp ++ ";";

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
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  -- TODO see ugly hack in ActionCode.sv
}

abstract production parserAttributeValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";

  top.errors := e.errors ++
               (if !top.blockContext.permitActions
                then [err(top.location, "Assignment to parser attributes only permitted in parser action blocks")]
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
}

concrete production pushTokenStmt
top::ProductionStmt ::= 'pushToken' '(' val::QName ',' lexeme::Expr ')' ';'
{
   forwards to pushTokenIfStmt($1, $2, val, $4, lexeme, $6, 'if', trueConst('true', location=$7.location), $7, location=top.location );
}


concrete production pushTokenIfStmt
top::ProductionStmt ::= 'pushToken' '(' val::QName ',' lexeme::Expr ')' 'if' condition::Expr ';'
{
  top.pp = "\t" ++ "pushToken(" ++ val.pp ++ ", " ++ lexeme.pp ++ ") if " ++ condition.pp ++ ";";

  top.errors := lexeme.errors ++ condition.errors ++
               (if !top.blockContext.permitActions
                then [err(top.location, "Tokens may only be pushed in action blocks")]
                else []);

  top.translation = "if(" ++ condition.translation ++ "){" ++ " pushToken(Terminals." ++ makeCopperName(val.lookupType.fullName) ++ ", (" ++ lexeme.translation ++ ").toString()" ++ ");}";

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;

  lexeme.downSubst = top.downSubst;
  errCheck1.downSubst = lexeme.upSubst;
  condition.downSubst = errCheck1.upSubst;
  errCheck2.downSubst = condition.upSubst;
  top.upSubst = errCheck2.upSubst;

  errCheck1 = check(lexeme.typerep, stringTypeExp());
  top.errors <-
       if errCheck1.typeerror
       then [err(lexeme.location, "Lexeme parameter has type " ++ errCheck1.leftpp ++ " which is not a String")]
       else [];


  errCheck2 = check(condition.typerep, boolTypeExp());
  top.errors <-
       if errCheck2.typeerror
       then [err(condition.location, "pushToken condition has type " ++ errCheck1.leftpp ++ " which is not a Boolean")]
       else [];
}




abstract production parserAttributeDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  
  -- Note this is always erroring!
  top.errors := if !top.blockContext.permitActions
                then [err(q.location, "Parser attributes can only be used in action blocks")]
                else [err(q.location, "Parser action blocks are imperative, not declarative. You cannot modify the attributes of " ++ q.name ++ ". If you are trying to set inherited attributes, you should use 'decorate ... with { ... }' when you create it.")];

  top.translation = error("Internal compiler error: translation not defined in the presence of errors");

  top.typerep = q.lookupValue.typerep;
}

abstract production termAttrValueValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";

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
}

