grammar silver:definition:core;

import silver:analysis:typechecking:core;

concrete production nestedExpr
top::Expr ::= '(' e::Expr ')'
{
  top.pp = "(" ++ e.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);
  top.typerep = e.typerep;
  top.errors := e.errors;
}

concrete production baseExpr
top::Expr ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;
  top.errors <- q.lookupValue.errors;

  forwards to if null(q.lookupValue.dcls)
              then errorReference(q)
              else q.lookupValue.dcl.refDispatcher(q);
}

abstract production errorReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  top.errors := []; -- The reason we don't error here: we only forward here
                    -- if the lookup failed, which already produced an error.
  top.typerep = errorType();
}

abstract production childReference
top::Expr ::= q::Decorated QName
{
  -- Undecorate iff it's a ("undecced") NT and we're explicitly expecting undecorated
  production attribute shouldUnDec ::Boolean;
  shouldUnDec = case top.expected of
                  expected_undecorated() -> true
                | expected_type(t)     -> !performSubstitution(new(t), top.upSubst).isDecorated
                | _                    -> false
                end;

  top.pp = q.pp;
  top.location = q.location;
  top.errors := [];
  top.typerep = if shouldUnDec || !q.lookupValue.typerep.doDecorate
                then q.lookupValue.typerep
                else decoratedTypeExp(q.lookupValue.typerep);
}

abstract production lhsReference
top::Expr ::= q::Decorated QName
{
  -- should always be a NT
  production attribute shouldUnDec ::Boolean;
  shouldUnDec = case top.expected of
                  expected_undecorated() -> true
                | expected_type(t)     -> !performSubstitution(new(t), top.upSubst).isDecorated
                | _                    -> false
                end;

  top.pp = q.pp;
  top.location = q.location;
  top.errors := [];
  top.typerep = if shouldUnDec || !q.lookupValue.typerep.doDecorate -- TODO: isn't this always known?
                then q.lookupValue.typerep
                else decoratedTypeExp(q.lookupValue.typerep);
}

abstract production localReference
top::Expr ::= q::Decorated QName
{
  production attribute shouldUnDec ::Boolean;
  shouldUnDec = case top.expected of
                  expected_undecorated() -> true
                | expected_type(t)     -> !performSubstitution(new(t), top.upSubst).isDecorated
                | _                    -> false
                end;

  top.pp = q.pp;
  top.location = q.location;
  top.errors := [];
  top.typerep = if shouldUnDec || !q.lookupValue.typerep.doDecorate
                then q.lookupValue.typerep
                else decoratedTypeExp(q.lookupValue.typerep);
}

abstract production forwardReference
top::Expr ::= q::Decorated QName
{
  -- should always be a NT
  production attribute shouldUnDec ::Boolean;
  shouldUnDec = case top.expected of
                  expected_undecorated() -> true
                | expected_type(t)     -> !performSubstitution(new(t), top.upSubst).isDecorated
                | _                    -> false
                end;

  top.pp = q.pp;
  top.location = q.location;
  top.errors := [];
  top.typerep = if shouldUnDec || !q.lookupValue.typerep.doDecorate -- TODO: isn't this always known?
                then q.lookupValue.typerep
                else decoratedTypeExp(q.lookupValue.typerep);
}

abstract production productionReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;

  top.errors := [];

  -- TODO: the freshening should probably be the responsibility of the thing in the environment, not here?
  top.typerep = freshenCompletely(q.lookupValue.typerep);
}

abstract production functionReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;

  top.errors := [];

  top.typerep = freshenCompletely(q.lookupValue.typerep); -- TODO see above
}

abstract production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;

  top.errors := [];

  top.typerep = freshenCompletely(q.lookupValue.typerep); -- TODO see above
}

concrete production concreteDecorateExpr
top::Expr ::= q::NameTick
{
  top.pp = q.pp;
  top.location = q.location;

  forwards to baseExpr(qNameId(nameIdLower(terminal(IdLower_t, q.name, q.location.line, q.location.column)))) with {
    expected = expected_decorated();
  };
}

concrete production concreteDontDecorateExpr
top::Expr ::= q::NameTickTick
{
  top.pp = q.pp;
  top.location = q.location;

  forwards to baseExpr(qNameId(nameIdLower(terminal(IdLower_t, q.name, q.location.line, q.location.column)))) with {
    expected = expected_undecorated();
  };
}

concrete production concreteForwardExpr
top::Expr ::= q::'forward'
{
  forwards to baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "forward", q))));
}


concrete production productionApp
top::Expr ::= e::Expr '(' es::Exprs ')'
{
  top.pp = e.pp ++ "(" ++ es.pp ++ ")";
  top.location = e.location;
  
  e.expected = expected_default();
  forwards to performSubstitution(e.typerep, e.upSubst).applicationDispatcher(e, es);
}

concrete production emptyProductionApp
top::Expr ::= e::Expr '(' ')'
{
  forwards to productionApp(e, $2, exprsEmpty(), $3);
}

abstract production productionApplicationDispatcher
top::Expr ::= e::Decorated Expr es::Exprs
{
  top.pp = e.pp ++ "(" ++ es.pp ++ ")";
  top.location = e.location;
  top.errors := e.errors ++ es.errors; 

  top.typerep = performSubstitution(e.typerep, e.upSubst).outputType;

  es.expectedInputTypes = performSubstitution(e.typerep, e.upSubst).inputTypes;
}

abstract production functionApplicationDispatcher
top::Expr ::= e::Decorated Expr es::Exprs
{
  top.pp = e.pp ++ "(" ++ es.pp ++ ")";
  top.location = e.location;
  top.errors := e.errors ++ es.errors; 

  top.typerep = performSubstitution(e.typerep, e.upSubst).outputType;

  es.expectedInputTypes = performSubstitution(e.typerep, e.upSubst).inputTypes;
}

abstract production errorApplicationDispatcher
top::Expr ::= e::Decorated Expr es::Exprs
{
  top.pp = e.pp ++ "(" ++ es.pp ++ ")";
  top.location = e.location;
  top.errors := [err(top.location, e.pp ++ " has type " ++ prettyType(performSubstitution(e.typerep, e.upSubst)) ++ " and cannot be invoked as a function.")] ++ e.errors ++ es.errors; -- TODO fix this

  top.typerep = errorType();

  es.expectedInputTypes = [];
}

concrete production attributeAccess
top::Expr ::= e::Expr '.' q::QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  -- expected here mean "if it exists in a decorated form, use that"
  -- this way we get the decorated children, but a direct call to
  -- a constructor will still be undecorated.
  
  e.expected = expected_decorated();
  
  top.errors <- e.errors;
  
  forwards to performSubstitution(e.typerep, e.upSubst).accessDispatcher(e, $2, q);
}

abstract production errorAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  top.typerep = q.lookupAttribute.typerep;
  top.errors := [err(top.location, "LHS of '.' is type " ++ prettyType(performSubstitution(e.typerep, e.upSubst)) ++ " and cannot have attributes.")] ++ q.lookupAttribute.errors; -- TODO fix this
}

abstract production undecoratedAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  -- TODO BUG: It's expecting something decorated here. We want to give all inherited attributes of 'e' to 'decorateExprWithEmpty...'

  -- and this is a positively UGLY way of getting around this... *evil grin*
  
  forwards to CHEAT_HACK_DISPATCHER( decorateExprWithEmpty('decorate', new(e), 'with', '{', '}'), $2, q);
}
abstract production CHEAT_HACK_DISPATCHER -- muahaahahahahaha
top::Expr ::= e::Expr '.' q::Decorated QName
{
  forwards to decoratedAccessDispatcher( e {- it gets decorated :) -} , $2, q);
}

abstract production decoratedAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors <- q.lookupAttribute.errors;
  
  -- We dispatch again, based on the kind of attribute
  forwards to if null(q.lookupAttribute.dcls)
              then errorDNTAccessDispatcher(e, $2, q)
              else q.lookupAttribute.dcl.attrAccessDispatcher(e, $2, q);
}

abstract production synDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(q, performSubstitution(e.typerep, e.upSubst));

  top.typerep = occursCheck.typerep;
  
  top.errors := occursCheck.errors;
}

abstract production inhDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(q, performSubstitution(e.typerep, e.upSubst));

  top.typerep = occursCheck.typerep;
  
  top.errors := occursCheck.errors;
}

abstract production errorDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  top.typerep = errorType();
  
  top.errors := []; -- empty because we only ever get here if lookup failed. see above.
}


abstract production terminalAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  -- TODO: this is a hacky way of dealing with terminal attributes
  top.typerep = if q.name == "lexeme" || q.name == "filename"
                then stringTypeExp()
                else if q.name == "line" || q.name == "column"
                then intTypeExp()
                else errorType();
  top.errors :=
        if q.name == "lexeme" || q.name == "filename" || q.name == "line" || q.name == "column"
        then []
        else [err(q.location, q.name ++ " is not a terminal attribute")];
}

concrete production decorateExprWithEmpty
top::Expr ::= 'decorate' e::Expr 'with' '{' '}'
{
  forwards to decorateExprWith($1, e, $3, $4, exprInhsEmpty(), $5);
}

concrete production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.pp = "decorate " ++ e.pp ++ " with {" ++ inh.pp ++ "}";
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = decoratedTypeExp(performSubstitution(e.typerep, e.upSubst));
  top.errors := e.errors ++ inh.errors;
  
  inh.decoratingnt = performSubstitution(e.typerep, e.upSubst);

  e.expected = expected_undecorated();
}

concrete production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  top.pp = lhs.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);
  top.errors := lhs.errors ++ e.errors;

  e.expected = expected_type(lhs.typerep);
}

abstract production exprInhsEmpty
top::ExprInhs ::= 
{
  top.pp = "";
  top.location = loc(top.file, -1, -1);
  top.errors := [];
}

concrete production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  top.pp = lhs.pp;
  top.location = lhs.location;
  top.errors := lhs.errors;
}

concrete production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  top.pp = lhs.pp ++ " " ++ inh.pp;
  top.location = lhs.location;
  top.errors := lhs.errors ++ inh.errors;
}

concrete production exprLhsExpr
top::ExprLHSExpr ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(q, top.decoratingnt);

  top.typerep = occursCheck.typerep;
  
  top.errors := occursCheck.errors;
}

concrete production trueConst
top::Expr ::= 'true'
{
  top.pp = "true";
  top.location = loc(top.file, $1.line, $1.column);
  top.errors := [];
  top.typerep = boolTypeExp();
}

concrete production falseConst
top::Expr ::= 'false'
{
  top.pp = "false";
  top.location = loc(top.file, $1.line, $1.column);
  top.errors := [];
  top.typerep = boolTypeExp();
}

concrete production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.pp = e1.pp ++ " && " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  e1.expected = expected_type(boolTypeExp());
  e2.expected = expected_type(boolTypeExp());
}

concrete production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.pp = e1.pp ++ " || " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  e1.expected = expected_type(boolTypeExp());
  e2.expected = expected_type(boolTypeExp());
}

concrete production not
top::Expr ::= '!' e::Expr
{
  top.pp = "! " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = boolTypeExp();
  top.errors := e.errors;
  
  e.expected = expected_type(boolTypeExp());
}

concrete production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.pp = e1.pp ++ " > " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.pp = e1.pp ++ " < " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.pp = e1.pp ++ " >= " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.pp = e1.pp ++ " <= " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.pp = e1.pp ++ " == " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.pp = e1.pp ++ " != " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
precedence = 0
{
  top.pp = "if " ++ e1.pp ++ " then " ++ e2.pp ++ " else " ++ e3.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors ++ e3.errors;
  top.typerep = e2.typerep;
  
  e1.expected = expected_type(boolTypeExp());
}

concrete production intConst
top::Expr ::= i::Int_t
{
  top.pp = i.lexeme;
  top.location = loc(top.file, i.line, i.column);

  top.errors := [];
  top.typerep = intTypeExp();
}

concrete production floatConst
top::Expr ::= f::Float_t
{
  top.pp = f.lexeme;
  top.location = loc(top.file, f.line, f.column);

  top.errors := [];
  top.typerep = floatTypeExp();
} 

concrete production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.pp = e1.pp ++ " + " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
  
  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.pp = e1.pp ++ " - " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
  
  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.pp = e1.pp ++ " * " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
  
  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.pp = e1.pp ++ " / " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
  
  e1.expected = expected_default();
  e2.expected = expected_default();
}

concrete production neg
top::Expr ::= '-' e::Expr
precedence = 13
{
  top.pp = "- " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e.errors;
  top.typerep = e.typerep;
  
  e.expected = expected_default();
}

concrete production stringConst
top::Expr ::= s::String_t
{
  top.pp = s.lexeme;
  top.location = loc(top.file, s.line, s.column);

  top.errors := [];
  top.typerep = stringTypeExp();
}

concrete production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
  top.pp = e1.pp ++ " ++ " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  production attribute handler :: [Expr] with ++;
  handler := [];
  -- TODO: THIS IS A COMPLETELY BUSTED WAY TO DO THIS, BUT WORKS FOR NOW. **FRAGILE**  probable BUGS
  handler <- if !(unify(e1.typerep, stringTypeExp()).failure || unify(e2.typerep, stringTypeExp()).failure)
             then [stringPlusPlus(e1, e2)]
             else [];

  forwards to if null(handler) then errorPlusPlus(e1, e2) else head(handler);
}

abstract production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.pp = e1.pp ++ " ++ " ++ e2.pp;
  top.location = e1.location;

  top.errors := e1.errors ++ e2.errors;
  top.typerep = stringTypeExp();
}

abstract production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.pp = e1.pp ++ " ++ " ++ e2.pp;
  top.location = e1.location;

  top.errors := e1.errors ++ e2.errors;
  top.typerep = errorType();
}

abstract production exprsEmpty
top::Exprs ::=
{
  top.pp = "";
  top.location = loc("exprsEmpty", -1, -1);
  top.errors := [];
  top.exprs = [];
}

concrete production exprsSingle
top::Exprs ::= e::Expr
{
  top.pp = e.pp;
  top.location = e.location;

  top.errors := e.errors;
  top.exprs = [e];

  e.expected = if null(top.expectedInputTypes) then expected_default() else expected_type(head(top.expectedInputTypes));
}

concrete production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.pp = e1.pp ++ ", " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.exprs = [e1] ++ e2.exprs;

  e1.expected = if null(top.expectedInputTypes) then expected_default() else expected_type(head(top.expectedInputTypes));

  e2.expectedInputTypes = if null(top.expectedInputTypes) then [] else tail(top.expectedInputTypes);
}


function getTypesExprs
[TypeExp] ::= es::[Decorated Expr]{
  return if null(es) then [] else [head(es).typerep] ++ getTypesExprs(tail(es));
}

