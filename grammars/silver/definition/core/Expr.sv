grammar silver:definition:core;

--import silver:analysis:typechecking:core;

nonterminal Expr with
  config, grammarName, file, env, location, pp, errors, blockContext, compiledGrammars, signature, typerep;
nonterminal Exprs with
  config, grammarName, file, env, location, pp, errors, blockContext, compiledGrammars, signature, exprs, rawExprs;

nonterminal ExprInhs with
  config, grammarName, file, env, location, pp, errors, blockContext, compiledGrammars, signature, decoratingnt, suppliedInhs;
nonterminal ExprInh with
  config, grammarName, file, env, location, pp, errors, blockContext, compiledGrammars, signature, decoratingnt, suppliedInhs;
nonterminal ExprLHSExpr with
  config, grammarName, file, env, location, pp, errors, typerep, decoratingnt, suppliedInhs;

{--
 - Exprs with optional underscores omitting parameters. Used exclusively for
 - (partial) function application.
 -}
nonterminal AppExprs with 
  config, grammarName, file, env, location, pp, errors, blockContext, compiledGrammars, signature, exprs, rawExprs,
  isPartial, missingTypereps, appExprIndicies, appExprIndex, appExprTypereps, normalExprs, appExprApplied;

nonterminal AppExpr with
  config, grammarName, file, env, location, pp, errors, blockContext, compiledGrammars, signature, exprs, rawExprs,
  isPartial, missingTypereps, appExprIndicies, appExprIndex, appExprTyperep, appExprApplied;

synthesized attribute normalExprs :: Exprs;
synthesized attribute isPartial :: Boolean;
synthesized attribute missingTypereps :: [TypeExp];
synthesized attribute appExprIndicies :: [Integer];
inherited attribute appExprIndex :: Integer;
inherited attribute appExprTypereps :: [TypeExp];
inherited attribute appExprTyperep :: TypeExp;
autocopy attribute appExprApplied :: String;


{--
 - The nonterminal being decorated. (Used for 'decorate with {}')
 -}
autocopy attribute decoratingnt :: TypeExp;
{--
 - The inherited attributes being supplied in a decorate expression
 -}
synthesized attribute suppliedInhs :: [String];
{--
 - A list of decorated expressions from an Exprs.
 -}
synthesized attribute exprs :: [Decorated Expr];
{--
 - Get each individual Expr, without decorating them.
 -}
synthesized attribute rawExprs :: [Expr];



concrete production nestedExpr
top::Expr ::= '(' e::Expr ')'
{
  top.pp = "(" ++ e.pp ++ ")";
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
}

-- TODO: We should separate this out, even, to be "nonterminal/decorable" and "as-is"
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

  top.errors <- [wrn(top.location, "Tick suffixes no longer do ANYTHING. Remove it!")];

  forwards to baseExpr(qNameId(nameIdLower(terminal(IdLower_t, q.name, q.location.line, q.location.column))));
}

concrete production concreteDontDecorateExpr
top::Expr ::= q::NameTickTick
{
  top.pp = q.pp;
  top.location = q.location;

  top.errors <- [wrn(top.location, "Double tick suffixes no longer do ANYTHING. Remove it!")];

  forwards to baseExpr(qNameId(nameIdLower(terminal(IdLower_t, q.name, q.location.line, q.location.column))));
}

concrete production concreteForwardExpr
top::Expr ::= q::'forward'
{
  top.pp = "forward";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "forward", q))));
}


concrete production productionApp
top::Expr ::= e::Expr '(' es::AppExprs ')'
{
  top.pp = e.pp ++ "(" ++ es.pp ++ ")";
  top.location = e.location;
  
  -- TODO: You know, since the rule is we can't access .typerep without "first" supplying
  -- .downSubst, perhaps we should just... report .typerep after substitution in the first place!
  forwards to performSubstitution(e.typerep, e.upSubst).applicationDispatcher(e, es);
}

concrete production emptyProductionApp
top::Expr ::= e::Expr '(' ')'
{
  top.pp = e.pp ++ "()";
  top.location = e.location;
  
  forwards to productionApp(e, $2, emptyAppExprs(forward.location), $3);
}

abstract production errorApplication
top::Expr ::= e::Decorated Expr es::AppExprs
{
  top.pp = e.pp ++ "(" ++ es.pp ++ ")";
  top.location = e.location;
  
  top.errors := e.errors ++ 
    [err(top.location, e.pp ++ " has type " ++ prettyType(performSubstitution(e.typerep, e.upSubst)) ++
      " and cannot be invoked as a function.")] ++ es.errors;
        -- TODO This error message is cumbersomely generated...

  top.typerep = errorType();
  
  es.appExprIndex = 0;
  es.appExprTypereps = [];
  es.appExprApplied = e.pp;
}

abstract production functionApplication
top::Expr ::= e::Decorated Expr es::AppExprs
{
  top.pp = e.pp ++ "(" ++ es.pp ++ ")";
  top.location = e.location;
  
  es.appExprIndex = 0;
  -- We may need to resolve e's type to get at the actual 'function type'
  es.appExprTypereps = performSubstitution(e.typerep, e.upSubst).inputTypes;
  es.appExprApplied = e.pp;
  
  forwards to if es.isPartial
              then partialApplication(e, es)
              else functionInvocation(e, es);
}

abstract production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs
{
  top.pp = e.pp ++ "(" ++ es.pp ++ ")";
  top.location = e.location;
  
  top.errors := e.errors ++ es.errors; 

  top.typerep = e.typerep.outputType;
}

abstract production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs
{
  top.pp = e.pp ++ "(" ++ es.pp ++ ")";
  top.location = e.location;
  
  top.errors := e.errors ++ es.errors; 

  top.typerep = functionTypeExp(e.typerep.outputType, es.missingTypereps);
}

concrete production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.pp = "(." ++ q.pp ++ ")";
  top.location = loc(top.file, $2.line, $2.column);
  
  -- Fresh variable for the input type, and we'll come back later and check that it occurs on that type.
  
  -- Also, freshen the attribute type, because even though there currently should NOT be any type variables
  -- there, there could be if the code will raise an error.
  local rawInputType :: TypeExp = freshType();
  top.typerep = functionTypeExp(freshenCompletely(q.lookupAttribute.typerep), [rawInputType]);
  
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
  inputType = performSubstitution(rawInputType, top.finalSubst);
  
  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(q, if inputType.isDecorated then inputType.decoratedType else inputType);

  top.errors <- occursCheck.errors;
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
}

abstract production undecoratedAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);

  -- TODO BUG: It's expecting something decorated here. We want to give all inherited attributes of 'e' to 'decorateExprWithEmpty...'

  -- and this is a positively UGLY way of getting around this... *evil grin*
  
  -- OKAY, and now we're compounding this by using a fancy little thing to make the flow type computation
  -- work a little bit better by using WithIntention...
  
  forwards to CHEAT_HACK_DISPATCHER( decorateExprWithIntention(e.location, new(e), exprInhsEmpty(), [q.lookupAttribute.fullName]), $2, q);
}
abstract production CHEAT_HACK_DISPATCHER -- muahaahahahahaha
top::Expr ::= e::Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);

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
}

abstract production errorDNTAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := []; -- empty because we only ever get here if lookup failed. see above.

  top.typerep = errorType();
}


abstract production terminalAccessDispatcher
top::Expr ::= e::Decorated Expr '.' q::Decorated QName
{
  top.pp = e.pp ++ "." ++ q.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  top.errors :=
    if q.name == "lexeme" || q.name == "filename" || q.name == "line" || q.name == "column" || q.name == "endLine" || q.name == "endColumn" || q.name == "index" || q.name == "endIndex"
    then []
    else [err(q.location, q.name ++ " is not a terminal attribute")];

  -- TODO: this is a hacky way of dealing with terminal attributes
  top.typerep =
    if q.name == "lexeme" || q.name == "filename"
    then stringTypeExp()
    else if q.name == "line" || q.name == "column" || q.name == "endLine" || q.name == "endColumn" || q.name == "index" || q.name == "endIndex"
    then intTypeExp()
    else errorType();
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

  top.typerep = decoratedTypeExp(performSubstitution(e.typerep, e.upSubst)); -- .decoratedForm?
  top.errors := e.errors ++ inh.errors;
  
  inh.decoratingnt = performSubstitution(e.typerep, e.upSubst);
}

abstract production decorateExprWithIntention
top::Expr ::= l::Location  e::Expr  inh::ExprInhs  intention::[String]
{
  -- TODO: this whole production is a hack to work around some problems computing
  -- flow types. The idea is the few places where we "auto decorate" in order to
  -- do something else, we can indicate that using this production, by specifying
  -- the "intention."
  
  -- This production should eventually be eliminated, somehow. It's pure duplication of the above.
  
  top.pp = "decorate " ++ e.pp ++ " with {" ++ inh.pp ++ "}";
  top.location = l;

  top.typerep = decoratedTypeExp(performSubstitution(e.typerep, e.upSubst)); -- .decoratedForm?
  top.errors := e.errors ++ inh.errors;
  
  inh.decoratingnt = performSubstitution(e.typerep, e.upSubst);
}

abstract production exprInhsEmpty
top::ExprInhs ::= 
{
  top.pp = "";
  top.location = loc(top.file, -1, -1);
  
  top.errors := [];
  top.suppliedInhs = [];
}

concrete production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  top.pp = lhs.pp;
  top.location = lhs.location;
  
  top.errors := lhs.errors;
  top.suppliedInhs = lhs.suppliedInhs;
}

concrete production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  top.pp = lhs.pp ++ " " ++ inh.pp;
  top.location = lhs.location;
  
  top.errors := lhs.errors ++ inh.errors;
  top.suppliedInhs = lhs.suppliedInhs ++ inh.suppliedInhs;
}

concrete production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  top.pp = lhs.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);
  
  top.errors := lhs.errors ++ e.errors;
  top.suppliedInhs = lhs.suppliedInhs;
}

concrete production exprLhsExpr
top::ExprLHSExpr ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(q, top.decoratingnt);

  top.errors := q.lookupAttribute.errors ++ occursCheck.errors;
  top.typerep = occursCheck.typerep;
  top.suppliedInhs = [q.lookupAttribute.fullName];
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
}

concrete production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.pp = e1.pp ++ " || " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
}

concrete production not
top::Expr ::= '!' e::Expr
{
  top.pp = "! " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = boolTypeExp();
  top.errors := e.errors;
}

concrete production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.pp = e1.pp ++ " > " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
}

concrete production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.pp = e1.pp ++ " < " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
}

concrete production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.pp = e1.pp ++ " >= " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
}

concrete production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.pp = e1.pp ++ " <= " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
}

concrete production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.pp = e1.pp ++ " == " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
}

concrete production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.pp = e1.pp ++ " != " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = boolTypeExp();
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
}

concrete production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.pp = e1.pp ++ " - " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
}

concrete production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.pp = e1.pp ++ " * " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
}

concrete production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.pp = e1.pp ++ " / " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
}

concrete production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  top.pp = e1.pp ++ " % " ++ e2.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := e1.errors ++ e2.errors;
  top.typerep = e1.typerep;
}

concrete production neg
top::Expr ::= '-' e::Expr
precedence = 13
{
  top.pp = "- " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := e.errors;
  top.typerep = e.typerep;
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
}

abstract production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.pp = e1.pp ++ " ++ " ++ e2.pp;
  top.location = e1.location;

  top.errors := [err(e1.location, prettyType(performSubstitution(e1.typerep, e1.upSubst)) ++ " is not a concatenable type.")] ++ e1.errors ++ e2.errors;
  top.typerep = errorType();
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

concrete production missingAppExpr
top::AppExpr ::= '_'
{
  top.pp = "_";
  top.location = loc(top.file, $1.line, $1.column);
  
  top.isPartial = true;
  top.missingTypereps = [top.appExprTyperep];
  
  top.rawExprs = [];
  top.exprs = [];
  top.appExprIndicies = [];
  
  top.errors := [];
}

concrete production presentAppExpr
top::AppExpr ::= e::Expr
{
  top.pp = e.pp;
  top.location = e.location;

  forwards to decoratedAppExpr(e);
}

abstract production decoratedAppExpr
top::AppExpr ::= e::Decorated Expr
{
  top.pp = e.pp;
  top.location = e.location;
  
  top.isPartial = false;
  top.missingTypereps = [];
  
  top.rawExprs = [new(e)];
  top.exprs = [e];
  top.appExprIndicies = [top.appExprIndex];
  
  top.errors := e.errors;
}


concrete production consAppExprs
top::AppExprs ::= e::AppExpr ',' es::AppExprs
{
  top.pp = e.pp ++ ", " ++ es.pp;
  top.location = e.location;

  top.isPartial = e.isPartial || es.isPartial;
  top.missingTypereps = e.missingTypereps ++ es.missingTypereps;

  top.rawExprs = e.rawExprs ++ es.rawExprs;
  top.exprs = e.exprs ++ es.exprs;
  top.appExprIndicies = e.appExprIndicies ++ es.appExprIndicies;
  top.normalExprs = exprsCons(head(e.rawExprs), $2, es.normalExprs);

  top.errors := e.errors ++ es.errors;

  e.appExprIndex = top.appExprIndex;
  e.appExprTyperep = if null(top.appExprTypereps)
                     then errorType()
                     else head(top.appExprTypereps);

  es.appExprIndex = top.appExprIndex + 1;
  es.appExprTypereps = if null(top.appExprTypereps) then [] else tail(top.appExprTypereps);
}

concrete production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  top.pp = e.pp;
  top.location = e.location;

  top.isPartial = e.isPartial;
  top.missingTypereps = e.missingTypereps;

  top.rawExprs = e.rawExprs;
  top.exprs = e.exprs;
  top.appExprIndicies = e.appExprIndicies;
  top.normalExprs = exprsSingle(head(e.rawExprs));
  
  top.errors := if null(top.appExprTypereps)
                then [err(top.location, "Too many arguments provided to function '" ++ top.appExprApplied ++ "'")]
                else if length(top.appExprTypereps) > 1
                then [err(top.location, "Too few arguments provided to function '" ++ top.appExprApplied ++ "'")]
                else [];
  top.errors <- e.errors;

  e.appExprIndex = top.appExprIndex;
  e.appExprTyperep = if null(top.appExprTypereps)
                     then errorType()
                     else head(top.appExprTypereps);
}

abstract production emptyAppExprs
top::AppExprs ::= l::Location
{
  top.pp = "";
  top.location = l;

  top.isPartial = false;
  top.missingTypereps = [];

  top.rawExprs = [];
  top.exprs = [];
  top.appExprIndicies = [];
  top.normalExprs = exprsEmpty();

  -- Assumption: We only get here when we're looking at ()
  -- i.e. we can't ever have 'too many' provided error
  top.errors := if null(top.appExprTypereps) then []
                else [err(top.location, "Too few arguments provided to function '" ++ top.appExprApplied ++ "'")];
}

{--
 - Utility for other modules to create function invocations.
 - This makes no assumptions, use it any way you wish!
 -}
function mkFunctionInvocation
Expr ::= e::Expr  es::[Expr]
{
  return productionApp(e, '(', foldAppExprs(es,e.location), ')');
}
function foldAppExprs
AppExprs ::= e::[Expr]  l::Location
{
  return if null(e) then emptyAppExprs(l)
         else if null(tail(e)) then oneAppExprs(presentAppExpr(head(e)))
         else consAppExprs(presentAppExpr(head(e)), ',', foldAppExprs(tail(e),l));
}

{--
 - Utility for other modules to create function invocations.
 -
 - Major assumption: The expressions are already decorated, and the 
 - typing substitution threaded through them will then be fed through
 - the expr created by this function.
 -
 - The purpose of this vs just mkFunctionInvocationDecorated
 - is to avoid exponential growth from forwarding. Type checking
 - an expr, then forwarding to a function call that again type
 - checks that expr well... just nest those and boom.
 -}
function mkFunctionInvocationDecorated
Expr ::= e::Expr  es::[Decorated Expr]
{
  return productionApp(e, '(', foldAppExprsDecorated(es, e.location), ')');
}
function foldAppExprsDecorated
AppExprs ::= e::[Decorated Expr]  l::Location
{
  return if null(e) then emptyAppExprs(l)
         else if null(tail(e)) then oneAppExprs(decoratedAppExpr(head(e)))
         else consAppExprs(decoratedAppExpr(head(e)), ',', foldAppExprsDecorated(tail(e),l));
}

