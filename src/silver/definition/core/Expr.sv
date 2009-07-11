grammar silver:definition:core;
import silver:definition:env;

--Definition Expressions
concrete production nestedExpr
top::Expr ::= '(' e::Expr ')'
{
  top.pp = "(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);
  top.typerep = e.typerep;
  top.errors := e.errors;
  top.warnings := [::Decorated Message];
}

concrete production baseExpr
top::Expr ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else q.name;

  --whether it is bound to a value
  production attribute vals :: [Decorated EnvItem];
  vals = getValueDcl(fName, top.env);

  production attribute in_sig ::Boolean;
  in_sig = !null(getValueDcl(fName, top.signatureEnv));

  production attribute in_locals ::Boolean;
  in_locals = !null(getValueDcl(fName, top.localsEnv));

  local attribute shouldDec ::Boolean;
  shouldDec = case top.expected of
		expected_decorated() -> true |
		expected_default() -> true |
		expected_type(t) ->  t.isDecorated |
		_ -> false end;
 	      	

  --to define the type
  forwards to if (in_sig || in_locals) && !null(vals) && head(vals).typerep.doDecorate && shouldDec
	      then decorateExpr(q)
	      else if !in_sig && !in_locals && !null(vals) 
		      && head(vals).typerep.typeName != "TOP"
		      && head(vals).typerep.isProduction 
	      then productionReference(q)
	      else if !in_sig && !in_locals && !null(vals) 
		      && head(vals).typerep.typeName != "TOP"
		      && head(vals).typerep.isFunction
	      then functionReference(q) 
	      else dontDecorateExpr(q);
}

concrete production concreteDecorateExpr
top::Expr ::= q::NameTick
{
  top.pp = q.pp;
  top.location = q.location;

  forwards to decorateExpr(qNameId(nameId(terminal(Id_t, q.name, q.location.line, q.location.column))));
}

abstract production decorateExpr
top::Expr ::= q::QName
{
  top.pp = q.pp ++ "'"; 
  top.location = q.location;

  --the full name of the identifier
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else q.name;

  --whether it is bound to a value
  production attribute vals :: [Decorated EnvItem];
  vals = getValueDcl(fName, top.env);

  production attribute origTypeRep :: Decorated TypeRep;
  origTypeRep = if !null(fNames) && !null(vals) 
		then head(vals).typerep 
		else topTypeRep();

  production attribute in_sig ::Boolean;
  in_sig = !null(getValueDcl(fName, top.signatureEnv));

  production attribute in_locals ::Boolean;
  in_locals = !null(getValueDcl(fName, top.localsEnv));

  local attribute er1 :: [Decorated Message];
  er1 = if null(vals)
	then [err(top.location, "Value '" ++ q.name ++ "' is not declared.")] 
	else [::Decorated Message];

  top.errors := er1;
  top.warnings := [::Decorated Message];

  top.typerep = if !null(fNames) && !null(vals) 
		then refTypeRep(head(vals).typerep) 
		else topTypeRep();
}

concrete production concreteDontDecorateExpr
top::Expr ::= q::NameTickTick
{
  top.pp = q.pp;
  top.location = q.location;

  forwards to dontDecorateExpr(qNameId(nameId(terminal(Id_t, q.name, q.location.line, q.location.column))));
}

abstract production dontDecorateExpr
top::Expr ::= q::QName
{
  top.pp = q.pp; 
  top.location = q.location;

  --the full name of the identifier
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else q.name;

  --whether it is bound to a value
  production attribute vals :: [Decorated EnvItem];
  vals = getValueDcl(fName, top.env);

  production attribute in_sig ::Boolean;
  in_sig = !null(getValueDcl(fName, top.signatureEnv));

  production attribute in_locals ::Boolean;
  in_locals = !null(getValueDcl(fName, top.localsEnv));

  local attribute er1 :: [Decorated Message];
  er1 = if null(vals)
	then [err(top.location, "Value '" ++ q.name ++ "' is not declared.")]
	else [::Decorated Message];

  top.errors := er1;
  top.warnings := [::Decorated Message];
  top.typerep = if !null(fNames) && !null(vals) then head(vals).typerep else topTypeRep();
}



abstract production productionReference
top::Expr ::= q::QName
{
  top.pp = q.pp; 
  top.location = q.location;

  --the full name of the identifier
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else q.name;

  --whether it is bound to a value
  production attribute vals :: [Decorated EnvItem];
  vals = getValueDcl(fName, top.env);

  local attribute er1 :: [Decorated Message];
  er1 = if null(vals)
	then [err(top.location, "Value '" ++ q.name ++ "' is not declared.")] 
	else [::Decorated Message];

  top.errors := er1;
  top.warnings := [::Decorated Message];

  top.typerep = if !null(fNames) && !null(vals) then head(vals).typerep else topTypeRep();
}


abstract production functionReference
top::Expr ::= q::QName
{
  top.pp = q.pp; 
  top.location = q.location;

  --the full name of the identifier
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else q.name;

  --whether it is bound to a value
  production attribute vals :: [Decorated EnvItem];
  vals = getValueDcl(fName, top.env);

  local attribute er1 :: [Decorated Message];
  er1 = if null(vals)
	then [err(top.location, "Value '" ++ q.name ++ "' is not declared.")] 
	else [::Decorated Message];

  top.errors := er1;
  top.warnings := [::Decorated Message];

  top.typerep = if !null(fNames) && !null(vals) then head(vals).typerep else topTypeRep();
}


concrete production productionApp
top::Expr ::= e::Expr '(' es::Exprs ')'
{
  top.pp = e.pp ++ "(" ++ es.pp ++ ")";
  top.location = loc(top.file, $2.line, $2.column);

  e.userFriendly = 0;
  e.expected = expected_default();
  forwards to e.typerep.applicationDispatcher(e, es);
}

concrete production emptyProductionApp
top::Expr ::= e::Expr '(' ')'
{
  top.pp = e.pp ++ "()";
  top.location = loc(top.file, $2.line, $2.column);

  e.userFriendly = 0;
  e.expected = expected_default();
  forwards to e.typerep.applicationDispatcher(e, exprsEmpty());
}

concrete production attributeAccess
top::Expr ::= e::Expr '.' q::QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);

  e.userFriendly = 1;
  e.expected = expected_decorated();

  forwards to if e.typerep.isDecorated || e.typerep.isTerminal
       	      then atAccess(e, terminal(At_t, "@", $2.line, $2.column), q)
       	      else hashAccess(e, terminal(Hash_t, "#", $2.line, $2.column), q)
	with {userFriendly = 1;};
}

concrete production hashAccess
top::Expr ::= e::Expr '#' q::QName
{
  top.pp = e.pp ++ "#" ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);


  local attribute withPart :: Expr;
  withPart = decorateExprWith(terminal(Decorate_kwd, "decorate", $2.line, $2.column),
	     	      e, 
	     	      terminal(With_kwd, "with", $2.line, $2.column),
	     	      terminal(LCurly_t, "{", $2.line, $2.column),
	              exprInhsEmpty(),
		      terminal(RCurly_t, "}", $2.line, $2.column));

  forwards to atAccess(withPart, terminal(At_t, "@", $2.line, $2.column), q);
}

concrete production atAccess
top::Expr ::= e::Expr '@' q::QName
{
  top.pp = e.pp ++ "@" ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  top.errors := er ++ e.errors;
  top.warnings := [::Decorated Message];

  e.userFriendly = 1;
  e.expected = expected_decorated();

  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else q.name;

--  production attribute occursOn :: Boolean;
--  occursOn = !null(fNames) && doesOccurOn(fName, e.typerep.typeName, top.env);
--
  production attribute vals :: [Decorated EnvItem];
  vals = getAttributeDcl(fName, top.env);

  local attribute er :: [Decorated Message];
  er = if null(vals)
       then [err(top.location, "Attribute '" ++ q.name ++ "' is not declared.")] 
       else [::Decorated Message];
  
  top.typerep = if !null(vals) then head(vals).typerep else topTypeRep();
--  top.typerep = if occursOn && !null(vals) then head(vals).typerep else topTypeRep();
}

concrete production decorateExprWithEmpty
top::Expr ::= 'decorate' e::Expr 'with' '{' '}'
{
  top.pp = "decorate " ++ e.pp ++ " with {}";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to decorateExprWith($1, e, $3, $4, exprInhsEmpty(), $5);
}

concrete production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.pp = "decorate " ++ e.pp ++ " with {" ++ inh.pp ++ "}";
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = refTypeRep(e.typerep);
  top.errors := e.errors ++ inh.errors;
  top.warnings := [::Decorated Message];

  e.expected = expected_undecorated();
  e.userFriendly = -1;
}

concrete production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  top.pp = lhs.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);
  top.errors = lhs.errors ++ e.errors;
  top.warnings := [::Decorated Message];

  e.expected = expected_type(lhs.typerep);
  e.userFriendly = lhs.typerep.userFriendlyLHS;
}

abstract production exprInhsEmpty
top::ExprInhs ::= 
{
  top.pp = "";
  top.location = loc(top.file, -1, -1);
  top.errors := [::Decorated Message];
  top.warnings := [::Decorated Message];
}

concrete production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  top.pp = lhs.pp;
  top.location = lhs.location;
  top.errors := lhs.errors;
  top.warnings := [::Decorated Message];
}

concrete production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  top.pp = lhs.pp ++ " " ++ inh.pp;
  top.location = lhs.location;
  top.errors := lhs.errors ++ inh.errors;
  top.warnings := [::Decorated Message];
}

concrete production exprLhsExpr
top::ExprLHSExpr ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

  --the full name of the identifier
  production attribute fNames :: [Decorated EnvItem];
  fNames = getFullNameDcl(q.name, top.env);

  production attribute fName ::String;
  fName = if !null(fNames) then head(fNames).fullName else q.name;

  --whether it is bound to a value
  production attribute vals :: [Decorated EnvItem];
  vals = getAttributeDcl(fName, top.env);

  local attribute er1 :: [Decorated Message];
  er1 = if null(vals)
	then [err(top.location, "Value '" ++ q.name ++ "' is not declared.")] 
	else [::Decorated Message];

  top.errors := er1;
  top.warnings := [::Decorated Message];
  top.typerep = if !null(fNames) && !null(vals) then head(vals).typerep else topTypeRep();
}

concrete production trueConst
top::Expr ::= 'true'
{
  top.pp = "true";
  top.location = loc(top.file, $1.line, $1.column);
  top.errors := [::Decorated Message];
  top.warnings := [::Decorated Message];
  top.typerep = booleanTypeRep();
}

concrete production falseConst
top::Expr ::= 'false'
{
  top.pp = "false";
  top.location = loc(top.file, $1.line, $1.column);
  top.errors := [::Decorated Message];
  top.warnings := [::Decorated Message];
  top.typerep = booleanTypeRep();
}

concrete production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.pp = e1.pp ++ " && " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = booleanTypeRep();
}

concrete production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.pp = e1.pp ++ " || " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = booleanTypeRep();
}

concrete production not
top::Expr ::= '!' e::Expr
{
  top.pp = "! " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = booleanTypeRep();
  top.errors := e.errors;
  top.warnings := [::Decorated Message];
}

concrete production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.pp = e1.pp ++ " > " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = booleanTypeRep();
}

concrete production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.pp = e1.pp ++ " < " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = booleanTypeRep();
}

concrete production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.pp = e1.pp ++ " >= " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = booleanTypeRep();
}

concrete production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.pp = e1.pp ++ " <= " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = booleanTypeRep();
}

concrete production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.pp = e1.pp ++ " == " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = booleanTypeRep();
}

concrete production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.pp = e1.pp ++ " != " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = booleanTypeRep();
}

concrete production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
precedence = 0
{
  top.pp = "if " ++ e1.pp ++ " then " ++ e2.pp ++ " else " ++ e3.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors ++ e3.errors;
  top.typerep = e2.typerep;
}

concrete production intConst
top::Expr ::= i::Int_t
{
  top.pp = i.lexeme;
  top.location = loc(top.file, i.line, i.column);

  top.errors = [::Decorated Message];
  top.warnings := [::Decorated Message];
  top.typerep = integerTypeRep();
}

concrete production floatConst
top::Expr ::= f::Float_t
{
  top.pp = f.lexeme;
  top.location = loc(top.file, f.line, f.column);

  top.errors := [::Decorated Message];
  top.warnings := [::Decorated Message];
  top.typerep = floatTypeRep();
} 

concrete production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.pp = e1.pp ++ " + " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = e1.typerep;
}

concrete production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.pp = e1.pp ++ " - " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = e1.typerep;
}

concrete production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.pp = e1.pp ++ " * " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = e1.typerep;
}

concrete production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.pp = e1.pp ++ " / " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = e1.typerep;
}

concrete production neg
top::Expr ::= '-' e::Expr
precedence = 13
{
  top.pp = "- " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e.errors;
  top.warnings := [::Decorated Message];
  top.typerep = e.typerep;
}

concrete production stringConst
top::Expr ::= s::String_t
{
  top.pp = s.lexeme;
  top.location = loc(top.file, s.line, s.column);

  top.errors := [::Decorated Message];
  top.warnings := [::Decorated Message];
  top.typerep = stringTypeRep();
}

concrete production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
  top.pp = e1.pp ++ " ++ " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  production attribute handler :: [Expr] with ++;
  handler := [::Expr];

  forwards to if null(handler) then defaultPlusPlus(e1, e2) else head(handler);
}

aspect production plusPlus
top::Expr ::= e1::Expr p::PlusPlus_t e2::Expr
{
  handler <- if e1.typerep.typeName == "String" && e2.typerep.typeName == "String"
	     then [stringPlusPlus(e1, e2)]
	     else [::Expr];
}

abstract production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.pp = e1.pp ++ " ++ " ++ e2.pp;
  top.location = e1.location;

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = stringTypeRep();
}

abstract production defaultPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.pp = e1.pp ++ " ++ " ++ e2.pp;
  top.location = e1.location;

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.typerep = stringTypeRep();
}

abstract production exprsEmpty
top::Exprs ::=
{
  top.pp = "";
  top.location = loc("exprsEmpty", -1, -1);
  top.errors := [::Decorated Message];
  top.warnings := [::Decorated Message];
  top.exprs = [];
}

concrete production exprsSingle
top::Exprs ::= e::Expr
{
  top.pp = e.pp;
  top.location = e.location;

  top.errors := e.errors;
  top.warnings := [::Decorated Message];
  top.exprs = [e];

  e.expected = if null(top.expectedInputTypes) then expected_default() else expected_type(head(top.expectedInputTypes));
  e.userFriendly = if null(top.expectedInputTypes) then 0 else head(top.expectedInputTypes).userFriendlyLHS;
}

concrete production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.pp = e1.pp ++ ", " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.warnings := [::Decorated Message];
  top.exprs = [e1] ++ e2.exprs;

  e1.userFriendly = if null(top.expectedInputTypes) then 0 else head(top.expectedInputTypes).userFriendlyLHS;
  e1.expected = if null(top.expectedInputTypes) then expected_default() else expected_type(head(top.expectedInputTypes));

  e2.expectedInputTypes = if null(top.expectedInputTypes) then [::Decorated TypeRep] else tail(top.expectedInputTypes);
}


function getTypesExprs
[Decorated TypeRep] ::= es::[Decorated Expr]{
  return if null(es) then [::Decorated TypeRep] else [head(es).typerep] ++ getTypesExprs(tail(es));
}

