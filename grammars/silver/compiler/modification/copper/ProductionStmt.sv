grammar silver:compiler:modification:copper;

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
  top.unparse = "pluck " ++ e.unparse ++ ";";

  -- Cast to integer is required, because that's secretly the real type of the
  -- result, but our type system only calls it an Object at the moment.
  -- Perhaps this problem can be resolved by using a proper type in this situation.
  top.translation = "return (Integer)(" ++ e.translation ++ ");\n";

  propagate errors;
  top.errors <-
    if !top.frame.permitPluck
    then [err(top.location, "'pluck' allowed only in disambiguation-group parser actions.")]
    else [];

  e.originRules = [];
  e.isRoot = true;

  local tyCk :: TypeCheck = check(e.typerep, terminalIdType());
  tyCk.finalSubst = top.finalSubst;
  top.errors <-
    if tyCk.typeerror
    then [err(top.location, "'pluck' expects one of the terminals it is disambiguating between. Instead it received "++tyCk.leftpp)]
    else [];

  thread downSubst, upSubst on top, e, tyCk, top;



  -- TODO: Enforce that the plucked terminal is one of those that are being disambiguated between.
  -- Currently all that is checked is that it is a terminal.
}

concrete production printStmt
top::ProductionStmt ::= 'print' e::Expr ';'
{
  top.unparse = "print " ++ e.unparse ++ ";";

  top.translation = "System.err.println(" ++ e.translation ++ ");\n";

  propagate errors;
  top.errors <-
    if !top.frame.permitActions
    then [err(top.location, "'print' statement allowed only in parser action blocks. You may be looking for print(String,IO) :: IO.")]
    else [];

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e.originRules = [];
  e.isRoot = true;

  thread downSubst, upSubst on top, e, errCheck1, top;
  
  errCheck1 = check(e.typerep, stringType());
  top.errors <-
       if errCheck1.typeerror
       then [err(e.location, "print expects a string, instead it recieved a " ++ errCheck1.leftpp)]
       else [];
}

aspect production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::TypeExpr ';'
{
  -- TODO see ugly hack in ActionCode.sv
}

abstract production parserAttributeValueDef
top::ProductionStmt ::= val::PartiallyDecorated QName  e::Expr
{
  top.unparse = "\t" ++ val.unparse ++ " = " ++ e.unparse ++ ";";

  propagate errors;
  top.errors <-
    if !top.frame.permitActions
    then [err(top.location, "Assignment to parser attributes only permitted in parser action blocks")]
    else [];

  top.translation = makeCopperName(val.lookupValue.fullName) ++ " = " ++ e.translation ++ ";\n";

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, e, errCheck1, top;

  e.originRules = [];
  e.isRoot = true;

  errCheck1 = check(e.typerep, val.lookupValue.typeScheme.monoType);
  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Parser attribute " ++ val.name ++ " has type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
       else [];
}

concrete production pushTokenStmt
top::ProductionStmt ::= 'pushToken' '(' val::QName ',' lexeme::Expr ')' ';'
{
  top.unparse = "\t" ++ "pushToken(" ++ val.unparse ++ ", " ++ lexeme.unparse ++ ");";

  propagate errors;
  top.errors <-
    if !top.frame.permitActions
    then [err(top.location, "Tokens may only be pushed in action blocks")]
    else [];

  top.translation = "pushToken(Terminals." ++ makeCopperName(val.lookupType.fullName) ++ ", (" ++ lexeme.translation ++ ").toString()" ++ ");";

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  lexeme.originRules = [];
  lexeme.isRoot = false;

  thread downSubst, upSubst on top, lexeme, errCheck1, top;

  errCheck1 = check(lexeme.typerep, stringType());
  top.errors <-
       if errCheck1.typeerror
       then [err(lexeme.location, "Lexeme parameter has type " ++ errCheck1.leftpp ++ " which is not a String")]
       else [];
}

concrete production blockStmt
top::ProductionStmt ::= '{' stmts::ProductionStmts '}'
{
  top.unparse = "\t{\n" ++ stmts.unparse ++ "\n\t}";
  
  propagate errors;
  top.errors <-
    if !top.frame.permitActions
    then [err(top.location, "Block statement is only permitted in action blocks")]
    else [];
  
  top.translation = stmts.translation;
  
  stmts.downSubst = top.downSubst;
  top.upSubst = error("Shouldn't ever be needed anywhere. (Should only ever be fed back here as top.finalSubst)");
  -- Of course, this means do not use top.finalSubst here!
}

concrete production ifElseStmt
top::ProductionStmt ::= 'if' '(' condition::Expr ')' th::ProductionStmt 'else' el::ProductionStmt
{
  top.unparse = "\t" ++ "if (" ++ condition.unparse ++ ") " ++ th.unparse ++ "\nelse " ++ el.unparse;

  propagate errors;
  top.errors <-
    if !top.frame.permitActions
    then [err(top.location, "If statement is only permitted in action blocks")]
    else [];

  top.translation = s"if(${condition.translation}) {${th.translation}} else {${el.translation}}";

  condition.originRules = [];
  condition.isRoot = false;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, condition, errCheck1, top;
  
  th.downSubst = top.downSubst;
  th.finalSubst = th.upSubst;
  
  el.downSubst = top.downSubst;
  el.finalSubst = el.upSubst;

  errCheck1 = check(condition.typerep, boolType());
  top.errors <-
       if errCheck1.typeerror
       then [err(condition.location, "if condition has type " ++ errCheck1.leftpp ++ " which is not a Boolean")]
       else [];
}

concrete production ifStmt
top::ProductionStmt ::= 'if' '(' condition::Expr ')' th::ProductionStmt
{
  top.unparse = "\t" ++ "if (" ++ condition.unparse ++ ") " ++ th.unparse;
  forwards to ifElseStmt($1, $2, condition, $4, th, 'else', blockStmt('{', productionStmtsNil(location=top.location), '}', location=top.location), location=top.location);
}


abstract production parserAttributeDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  top.found = false;
  
  -- Note this is always erroring!
  propagate errors;
  top.errors <-
    if !top.frame.permitActions
    then [err(q.location, "Parser attributes can only be used in action blocks")]
    else [err(q.location, "Parser action blocks are imperative, not declarative. You cannot modify the attributes of " ++ q.name ++ ". If you are trying to set inherited attributes, you should use 'decorate ... with { ... }' when you create it.")];

  top.translation = error("Internal compiler error: translation not defined in the presence of errors");

  top.typerep = q.lookupValue.typeScheme.monoType;
}

abstract production termAttrValueValueDef
top::ProductionStmt ::= val::PartiallyDecorated QName  e::Expr
{
  top.unparse = "\t" ++ val.unparse ++ " = " ++ e.unparse ++ ";";

  -- these values should only ever be in scope when it's valid to use them
  propagate errors;
  
  top.errors <-
    if val.name != "lexeme" then [] else
    [err(val.location, "lexeme is not reassignable.")];

  local memberfunc :: String =
    if val.name == "filename" then "setFileName" else
    if val.name == "line" then "setLine" else
    if val.name == "column" then "setColumn" else
    error("unknown assignment to terminal attribute: " ++ val.name);

  top.translation = "virtualLocation." ++ memberfunc ++ "(" ++ e.translation
                     ++ (if val.name == "filename" then ".toString()" else "") ++ ");\n";

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  thread downSubst, upSubst on top, e, errCheck1, top;

  e.originRules = [];
  e.isRoot = true;

  errCheck1 = check(e.typerep, val.lookupValue.typeScheme.monoType);
  top.errors <-
    if errCheck1.typeerror
    then [err(top.location, "Terminal attribute " ++ val.name ++ " has type " ++ errCheck1.rightpp ++ " but the expression being assigned to it has type " ++ errCheck1.leftpp)]
    else [];
}

