grammar silver:definition:core;

import silver:analysis:typechecking:core;
import silver:analysis:typechecking;

nonterminal Expr with grammarName, file, env, location, pp, errors, defs, signature, typerep;
nonterminal Exprs with grammarName, file, env, location, pp, errors, defs, signature, exprs, rawExprs;

nonterminal ExprInhs with grammarName, file, env, location, pp, errors, defs, signature, decoratingnt;
nonterminal ExprInh with grammarName, file, env, location, pp, errors, defs, signature, decoratingnt;
nonterminal ExprLHSExpr with grammarName, file, env, location, pp, errors, defs, typerep, decoratingnt;

{--
 - The nonterminal being decorated. (Used for 'decorate with {}')
 -}
autocopy attribute decoratingnt :: TypeExp;
{--
 - A list of decorated expressions from an Exprs.
 -}
synthesized attribute exprs :: [Decorated Expr];
{--
 - Get each individual Expr, without decorating them.
 -}
synthesized attribute rawExprs :: [Expr];



abstract production defaultExpr
top::Expr ::=
{
}

concrete production nestedExpr
top::Expr ::= '(' e::Expr ')'
{
  top.pp = "(" ++ forward.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);
  
  forwards to e;
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
  
  forwards to defaultExpr();
}

abstract production childReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  top.errors := [];
  top.typerep = if q.lookupValue.typerep.isDecorable
                --then ntOrDecTypeExp(q.lookupValue.typerep, errorType(){-fresh tyvar-})
                then ntOrDecTypeExp(q.lookupValue.typerep, freshType(){-fresh tyvar-}) -- #HACK2012 Issue 4
                else q.lookupValue.typerep;
  
  forwards to defaultExpr();
}

abstract production lhsReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  top.errors := [];
  top.typerep = if q.lookupValue.typerep.isDecorable -- actually always decorable...
                --then ntOrDecTypeExp(q.lookupValue.typerep, errorType(){-fresh tyvar-})
                then ntOrDecTypeExp(q.lookupValue.typerep, freshType(){-fresh tyvar-}) -- #HACK2012 Issue 4
                else q.lookupValue.typerep;
  
  forwards to defaultExpr();
}

abstract production localReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  top.errors := [];
  top.typerep = if q.lookupValue.typerep.isDecorable
                --then ntOrDecTypeExp(q.lookupValue.typerep, errorType(){-fresh tyvar-})
                then ntOrDecTypeExp(q.lookupValue.typerep, freshType(){-fresh tyvar-}) -- #HACK2012 Issue 4
                else q.lookupValue.typerep;
  
  forwards to defaultExpr();
}

abstract production forwardReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  top.errors := [];
  top.typerep = if q.lookupValue.typerep.isDecorable -- actually always decorable...
                --then ntOrDecTypeExp(q.lookupValue.typerep, errorType(){-fresh tyvar-})
                then ntOrDecTypeExp(q.lookupValue.typerep, freshType(){-fresh tyvar-}) -- #HACK2012 Issue 4
                else q.lookupValue.typerep;
  
  forwards to defaultExpr();
}

{- Eventhough bug #16 removes the production type, we still need the
production reference for code generation purposes.  Type checking does
not need to distinguish between functions and productions, excpet for
the need to detect cases when, for example, a function aspect attempts
to aspect a production. --EVW
 -}

abstract production productionReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;

  top.errors := [];

  -- TODO: the freshening should probably be the responsibility of the thing in the environment, not here?
  top.typerep = freshenCompletely(q.lookupValue.typerep);
  
  forwards to defaultExpr();
}

abstract production functionReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;

  top.errors := [];

  top.typerep = freshenCompletely(q.lookupValue.typerep); -- TODO see above
  
  forwards to defaultExpr();
}

abstract production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;

  top.errors := [];

  top.typerep = freshenCompletely(q.lookupValue.typerep); -- TODO see above
  
  forwards to defaultExpr();
}

concrete production concreteDecorateExpr
top::Expr ::= q::NameTick
{
  top.pp = q.pp;
  top.location = q.location;

  -- TODO: warn obsolete. no longer does ANYTHING

  forwards to baseExpr(qNameId(nameIdLower(terminal(IdLower_t, q.name, q.location.line, q.location.column))));
}

concrete production concreteDontDecorateExpr
top::Expr ::= q::NameTickTick
{
  top.pp = q.pp;
  top.location = q.location;

  -- TODO: warn obsolete. no longer does ANYTHING

  forwards to baseExpr(qNameId(nameIdLower(terminal(IdLower_t, q.name, q.location.line, q.location.column))));
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
  
  forwards to performSubstitution(e.typerep, e.upSubst).applicationDispatcher(e, es);
}

concrete production emptyProductionApp
top::Expr ::= e::Expr '(' ')'
{
  forwards to productionApp(e, $2, exprsEmpty(), $3);
}

abstract production functionApplicationDispatcher
top::Expr ::= e::Decorated Expr es::Exprs
{
  top.pp = e.pp ++ "(" ++ es.pp ++ ")";
  top.location = e.location;
  top.errors := e.errors ++ es.errors; 

  top.typerep = performSubstitution(e.typerep, e.upSubst).outputType;
  
  forwards to defaultExpr();
}

abstract production errorApplicationDispatcher
top::Expr ::= e::Decorated Expr es::Exprs
{
  top.pp = e.pp ++ "(" ++ es.pp ++ ")";
  top.location = e.location;
  top.errors := e.errors ++ [err(top.location, e.pp ++ " has type " ++ prettyType(performSubstitution(e.typerep, e.upSubst)) ++ " and cannot be invoked as a function.")] ++ es.errors; -- TODO fix this.  Uhhh how? What's broken? My comments SUCK! Perhaps I didn't like doing the performsubst here

  top.typerep = errorType();
  
  forwards to defaultExpr();
}

concrete production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.pp = "(." ++ q.pp ++ ")";
  top.location = loc(top.file, $2.line, $2.column);
  
  -- Fresh variable for the input type, and we'll come back later and check that it occurs on that type.
  
  -- Also, freshen the attribute type, because even though there currently should NOT be any type variables
  -- there, there could be if the code will raise an error.
  --top.typerep = functionTypeExp(freshenCompletely(q.lookupAttribute.typerep), [errorType()]);
  top.typerep = functionTypeExp(freshenCompletely(q.lookupAttribute.typerep), [freshType()]); -- #HACK2012 Issue 4
  
  top.errors := q.lookupAttribute.errors;
  
  top.errors <- if null(q.lookupAttribute.dclBoundVars) then []
                else [err(q.location, "Attribute " ++ q.pp ++ " is parameterized, and attribute sections currently do not work with parameterized attributes, yet.")]; -- TODO The type inference system is too weak, currently.
  
  top.errors <- case q.lookupAttribute.dcls of -- TODO HORRIBLE. FIX. PLZ.
                | synDcl(_,_,_,_,_) :: _ -> []
                | [] -> [] -- ignore
                | _ -> [err(q.location, "Only synthesized attributes are currently supported in attribute sections.")]
                end;
  
  -- Only known after the inference pass (uses final subst)
  production attribute inputType :: TypeExp;
  inputType = performSubstitution(head(top.typerep.inputTypes), top.finalSubst);
  
  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(q, if inputType.isDecorated then inputType.decoratedType else inputType);

  top.errors <- occursCheck.errors;

  forwards to defaultExpr();
}

concrete production attributeAccess
top::Expr ::= e::Expr '.' q::QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  top.errors := e.errors ++ forward.errors; -- So that e.errors appears first!
  
  forwards to performSubstitution(e.typerep, e.upSubst).accessDispatcher(e, $2, q);
}

abstract production errorAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  top.typerep = q.lookupAttribute.typerep;
  top.errors := [err(top.location, "LHS of '.' is type " ++ prettyType(performSubstitution(e.typerep, e.upSubst)) ++ " and cannot have attributes.")] ++ q.lookupAttribute.errors; -- TODO fix this. How? Why? What's wrong? Perhaps I didn't like doing the performsubst here
  
  forwards to defaultExpr();
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

  top.errors := q.lookupAttribute.errors ++ forward.errors; -- so that these errors appear first.
  
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
  occursCheck = occursCheckQName(q, performSubstitution(e.typerep, e.upSubst).decoratedType);

  top.typerep = occursCheck.typerep;
  
  top.errors := occursCheck.errors;
  
  forwards to defaultExpr();
}

abstract production inhDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(q, performSubstitution(e.typerep, e.upSubst).decoratedType);

  top.typerep = occursCheck.typerep;
  
  top.errors := occursCheck.errors;
  
  forwards to defaultExpr();
}

abstract production errorDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  top.typerep = errorType();
  
  top.errors := []; -- empty because we only ever get here if lookup failed. see above.
  
  forwards to defaultExpr();
}


abstract production terminalAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  -- TODO: this is a hacky way of dealing with terminal attributes
  top.typerep = if q.name == "lexeme" || q.name == "filename"
                then stringTypeExp()
                else if q.name == "line" || q.name == "column" || q.name == "endLine" || q.name == "endColumn" || q.name == "index" || q.name == "endIndex"
                then intTypeExp()
                else errorType();
  top.errors :=
        if q.name == "lexeme" || q.name == "filename" || q.name == "line" || q.name == "column" || q.name == "endLine" || q.name == "endColumn" || q.name == "index" || q.name == "endIndex"
        then []
        else [err(q.location, q.name ++ " is not a terminal attribute")];
  
  forwards to defaultExpr();
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

  top.typerep = decoratedTypeExp(performSubstitution(e.typerep, e.upSubst)); -- .decoratedForm?
  top.errors := e.errors ++ inh.errors;
  
  inh.decoratingnt = performSubstitution(e.typerep, e.upSubst);
  
  forwards to defaultExpr();
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

concrete production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  top.pp = lhs.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);
  top.errors := lhs.errors ++ e.errors;
}

concrete production exprLhsExpr
top::ExprLHSExpr ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(q, top.decoratingnt);

  top.typerep = occursCheck.typerep;
  
  top.errors := q.lookupAttribute.errors ++ occursCheck.errors;
}

concrete production trueConst
top::Expr ::= 'true'
{
  top.pp = "true";
  top.location = loc(top.file, $1.line, $1.column);
  top.errors := [];
  top.typerep = boolTypeExp();
  
  forwards to defaultExpr();
}

concrete production falseConst
top::Expr ::= 'false'
{
  top.pp = "false";
  top.location = loc(top.file, $1.line, $1.column);
  top.errors := [];
  top.typerep = boolTypeExp();
  
  forwards to defaultExpr();
}

concrete production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.pp = e1.pp ++ " && " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  forwards to defaultExpr();
}

concrete production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.pp = e1.pp ++ " || " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  forwards to defaultExpr();
}

concrete production not
top::Expr ::= '!' e::Expr
{
  top.pp = "! " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = boolTypeExp();
  top.errors := e.errors;
  
  forwards to defaultExpr();
}

concrete production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.pp = e1.pp ++ " > " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  forwards to defaultExpr();
}

concrete production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.pp = e1.pp ++ " < " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  forwards to defaultExpr();
}

concrete production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.pp = e1.pp ++ " >= " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  forwards to defaultExpr();
}

concrete production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.pp = e1.pp ++ " <= " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  forwards to defaultExpr();
}

concrete production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.pp = e1.pp ++ " == " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  forwards to defaultExpr();
}

concrete production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.pp = e1.pp ++ " != " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
  
  forwards to defaultExpr();
}

concrete production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
precedence = 0
{
  top.pp = "if " ++ e1.pp ++ " then " ++ e2.pp ++ " else " ++ e3.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e1.errors ++ e2.errors ++ e3.errors;
  top.typerep = e2.typerep;
  
  forwards to defaultExpr();
}

concrete production intConst
top::Expr ::= i::Int_t
{
  top.pp = i.lexeme;
  top.location = loc(top.file, i.line, i.column);

  top.errors := [];
  top.typerep = intTypeExp();
  
  forwards to defaultExpr();
}

concrete production floatConst
top::Expr ::= f::Float_t
{
  top.pp = f.lexeme;
  top.location = loc(top.file, f.line, f.column);

  top.errors := [];
  top.typerep = floatTypeExp();
  
  forwards to defaultExpr();
} 

concrete production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.pp = e1.pp ++ " + " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
  
  forwards to defaultExpr();
}

concrete production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.pp = e1.pp ++ " - " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
  
  forwards to defaultExpr();
}

concrete production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.pp = e1.pp ++ " * " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
  
  forwards to defaultExpr();
}

concrete production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.pp = e1.pp ++ " / " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
  
  forwards to defaultExpr();
}

concrete production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  top.pp = e1.pp ++ " % " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
  
  forwards to defaultExpr();
}

concrete production neg
top::Expr ::= '-' e::Expr
precedence = 13
{
  top.pp = "- " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e.errors;
  top.typerep = e.typerep;
  
  forwards to defaultExpr();
}

concrete production stringConst
top::Expr ::= s::String_t
{
  top.pp = s.lexeme;
  top.location = loc(top.file, s.line, s.column);

  top.errors := [];
  top.typerep = stringTypeExp();
  
  forwards to defaultExpr();
}

concrete production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
  top.pp = e1.pp ++ " ++ " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.typerep = performSubstitution(e1.typerep, errCheck1.upSubst); -- is it safe to report a typerep using substs? hope so!

  forwards to top.typerep.appendDispatcher(e1,e2);
}

abstract production stringPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.pp = e1.pp ++ " ++ " ++ e2.pp;
  top.location = e1.location;

  top.errors := e1.errors ++ e2.errors;
  top.typerep = stringTypeExp();
  
  forwards to defaultExpr();
}

abstract production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.pp = e1.pp ++ " ++ " ++ e2.pp;
  top.location = e1.location;

  top.errors := [err(e1.location, prettyType(performSubstitution(e1.typerep, e1.upSubst)) ++ " is not a concatenable type.")] ++ e1.errors ++ e2.errors;
  top.typerep = errorType();
  
  forwards to defaultExpr();
}


abstract production exprsEmpty
top::Exprs ::=
{
  top.pp = "";
  top.location = loc("exprsEmpty", -1, -1);
  top.errors := [];
  top.exprs = [];
  top.rawExprs = [];
}

concrete production exprsSingle
top::Exprs ::= e::Expr
{
  top.pp = e.pp;
  top.location = e.location;

  top.errors := e.errors;
  top.exprs = [e];
  top.rawExprs = [e];
}

concrete production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.pp = e1.pp ++ ", " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.exprs = [e1] ++ e2.exprs;
  top.rawExprs = [e1] ++ e2.rawExprs;
}

abstract production exprsDecorated
top::Exprs ::= es::[Decorated Expr]
{
  top.pp = implode(", ", getPPsExprs(es));
  top.location = if null(es) then loc("nowhere",-1,-1) else head(es).location;
  
  top.errors := foldr(append, [], getErrorsExprs(es));
  top.exprs = es;
  top.rawExprs = error("internal error: not yet implemented: rawExprs from exprsDecorated");
}


function getTypesExprs
[TypeExp] ::= es::[Decorated Expr]{
  return if null(es) then [] else [head(es).typerep] ++ getTypesExprs(tail(es));
}
function getPPsExprs
[String] ::= es::[Decorated Expr]{
  return if null(es) then [] else [head(es).pp] ++ getPPsExprs(tail(es));
}
function getErrorsExprs
[[Message]] ::= es::[Decorated Expr]{
  return if null(es) then [] else [head(es).errors] ++ getErrorsExprs(tail(es));
}


{--
 - Exprs with optional underscores omitting parameters. Used exclusively for
 - (partial) function application.
 -}
nonterminal AppExprs with rawExprs, isPartial, missingTypereps, appExprIndicies, appExprIndex, appExprTypereps, env, errors, location, file;

nonterminal AppExpr with rawExprs, isPartial, missingTypereps, appExprIndicies, appExprIndex, appExprTyperep, location, file;

synthesized attribute isPartial :: Boolean;
synthesized attribute missingTypereps :: [TypeExp];
synthesized attribute appExprIndicies :: [Integer];
inherited attribute appExprIndex :: Integer;
inherited attribute appExprTypereps :: [TypeExp];
inherited attribute appExprTyperep :: TypeExp;

concrete production appExprMissing
top::AppExpr ::= '_'
{
  top.location = loc(top.file, $1.line, $1.column);
  
  top.isPartial = true;
  top.missingTypereps = [top.appExprTyperep];
  
  top.rawExprs = [];
  top.appExprIndicies = [];
}

concrete production appExprPresent
top::AppExpr ::= e::Expr
{
  top.location = e.location;
  
  top.isPartial = false;
  top.missingTypereps = [];
  
  top.rawExprs = [e];
  top.appExprIndicies = [top.appExprIndex];
}

concrete production appExprCons
top::AppExprs ::= e::AppExpr ',' es::AppExprs
{
  top.location = e.location;
  top.rawExprs = e.rawExprs ++ es.rawExprs;
  top.isPartial = e.isPartial || es.isPartial;
  top.missingTypereps = e.missingTypereps ++ es.missingTypereps;
  top.appExprIndicies = e.appExprIndicies ++ es.appExprIndicies;
  top.errors := es.errors;
  e.appExprIndex = top.appExprIndex;
  e.appExprTyperep = if null(top.appExprTypereps)
                     then errorType()
                     else head(top.appExprTypereps);
  es.appExprIndex = top.appExprIndex + 1;
  es.appExprTypereps = if null(top.appExprTypereps) then [] else tail(top.appExprTypereps);
}

concrete production appExprSingle
top::AppExprs ::= e::AppExpr
{
  top.location = e.location;
  top.rawExprs = e.rawExprs;
  top.isPartial = e.isPartial;
  top.missingTypereps = e.missingTypereps;
  top.appExprIndicies = e.appExprIndicies;
  top.errors := if null(top.appExprTypereps)
                then [err(top.location, "Insufficient parameters provided to ???")] -- TODO ???
                else [];
  top.errors <- if length(top.appExprTypereps) > 1
                then [err(top.location, "Too many parameters provided to ???")] -- TODO ???
                else [];
  e.appExprIndex = top.appExprIndex;
  e.appExprTyperep = if null(top.appExprTypereps)
                     then errorType()
                     else head(top.appExprTypereps);
}

