grammar silver:compiler:definition:core;

--import silver:compiler:analysis:typechecking:core;
import silver:util:treeset as ts;

nonterminal Expr with
  config, grammarName, env, location, unparse, errors, freeVars, frame, compiledGrammars, typerep, isRoot, originRules;
nonterminal Exprs with
  config, grammarName, env, location, unparse, errors, freeVars, frame, compiledGrammars, exprs, rawExprs, isRoot, originRules;

nonterminal ExprInhs with
  config, grammarName, env, location, unparse, errors, freeVars, frame, compiledGrammars, decoratingnt, suppliedInhs, isRoot, originRules;
nonterminal ExprInh with
  config, grammarName, env, location, unparse, errors, freeVars, frame, compiledGrammars, decoratingnt, suppliedInhs, isRoot, originRules;
nonterminal ExprLHSExpr with
  config, grammarName, env, location, unparse, errors, freeVars, name, typerep, decoratingnt, suppliedInhs, isRoot, originRules;

flowtype freeVars {} on Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr;

propagate errors, freeVars on Expr, Exprs, ExprInhs, ExprInh, ExprLHSExpr;

{--
 - The nonterminal being decorated. (Used for 'decorate with {}')
 -}
autocopy attribute decoratingnt :: Type;
{--
 - The inherited attributes being supplied in a decorate expression
 -}
synthesized attribute suppliedInhs :: [String];
{--
 - A list of decorated expressions from an Exprs.
 -}
monoid attribute exprs :: [Decorated Expr];
{--
 - Get each individual Expr, without decorating them.
 -}
monoid attribute rawExprs :: [Expr];
{--
 - Compute the expression's free (unbound) variables
 -}
monoid attribute freeVars :: ts:Set<String>;

-- Is this Expr the logical "root" of the expression? That is, will it's value be the value computed
--  for the attribute/return value/etc that it is part of?
autocopy attribute isRoot :: Boolean;

autocopy attribute originRules :: [Decorated Expr];


abstract production errorExpr
top::Expr ::= e::[Message]
{
  top.unparse = s"{- Errors:\n${messagesToString(e)} -}";
  top.errors <- e;
  top.typerep = errorType();
}

concrete production nestedExpr
top::Expr ::= '(' e::Expr ')'
{
  top.unparse = "(" ++ e.unparse ++ ")";
  
  forwards to e;
}

concrete production baseExpr
top::Expr ::= q::QName
{
  top.unparse = q.unparse;
  top.freeVars := ts:fromList([q.name]);
  
  forwards to if null(q.lookupValue.dcls)
              then errorReference(q.lookupValue.errors, q, location=top.location)
              else q.lookupValue.dcl.refDispatcher(q, top.location);
}

abstract production errorReference
top::Expr ::= msg::[Message]  q::Decorated QName
{
  top.unparse = q.unparse;
  top.freeVars <- ts:fromList([q.name]);
  
  top.errors <- msg;
  top.typerep = errorType();
}

-- TODO: We should separate this out, even, to be "nonterminal/decorable" and "as-is"
abstract production childReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  top.freeVars <- ts:fromList([q.name]);
  
  top.typerep = if q.lookupValue.typeScheme.isDecorable
                then q.lookupValue.typeScheme.asNtOrDecType
                else q.lookupValue.typeScheme.monoType;
}

abstract production lhsReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  top.freeVars <- ts:fromList([q.name]);
  
  -- An LHS is *always* a decorable (nonterminal) type.
  top.typerep = q.lookupValue.typeScheme.asNtOrDecType;
}

abstract production localReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  top.freeVars <- ts:fromList([q.name]);
  
  top.typerep = if q.lookupValue.typeScheme.isDecorable
                then q.lookupValue.typeScheme.asNtOrDecType
                else q.lookupValue.typeScheme.monoType;
}

abstract production forwardReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  top.freeVars <- ts:fromList([q.name]);
  
  -- An LHS (and thus, forward) is *always* a decorable (nonterminal) type.
  top.typerep = q.lookupValue.typeScheme.asNtOrDecType;
}

-- Note here that production and function *references* are distinguished.
-- Later on, we do *not* distinguish for application.

abstract production productionReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  top.freeVars <- ts:fromList([q.name]);

  production typeScheme::PolyType = q.lookupValue.typeScheme;
  top.typerep = typeScheme.typerep;

  production contexts::Contexts =
    foldContexts(map(performContextSubstitution(_, top.finalSubst), typeScheme.contexts));
  contexts.env = top.env;
}

abstract production functionReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  top.freeVars <- ts:fromList([q.name]);

  production typeScheme::PolyType = q.lookupValue.typeScheme;
  top.typerep = typeScheme.typerep;

  production contexts::Contexts =
    foldContexts(map(performContextSubstitution(_, top.finalSubst), typeScheme.contexts));
  contexts.env = top.env;
}

abstract production classMemberReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  top.freeVars <- ts:fromList([q.name]);

  production typeScheme::PolyType = q.lookupValue.typeScheme;
  top.typerep = typeScheme.typerep;

  production instHead::Context =
    case typeScheme.contexts of
    | c :: _ -> performContextSubstitution(c, top.finalSubst)
    | _ -> error("Class member should have at least one context!")
    end;
  instHead.env = top.env;
  production contexts::Contexts =
    case typeScheme.contexts of
    | _ :: cs -> foldContexts(map(performContextSubstitution(_, top.finalSubst), cs))
    | _ -> error("Class member should have at least one context!")
    end;
  contexts.env = top.env;
}

abstract production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  top.freeVars <- ts:fromList([q.name]);

  top.typerep = q.lookupValue.typeScheme.monoType; -- These aren't generalized, for now.
}

concrete production concreteForwardExpr
top::Expr ::= q::'forward'
{
  top.unparse = "forward";

  -- TODO: we're forwarding to baseExpr just to decorate the tree we create.
  -- That's a bit weird.
  forwards to baseExpr(qName(q.location, "forward"), location=top.location);
}

concrete production application
top::Expr ::= e::Expr '(' es::AppExprs ',' anns::AnnoAppExprs ')'
{
  -- TODO: fix comma when one or the other is empty
  top.unparse = e.unparse ++ "(" ++ es.unparse ++ "," ++ anns.unparse ++ ")";
  propagate freeVars;
  
  local correctNumTypes :: [Type] =
    if length(t.inputTypes) > es.appExprSize
    then take(es.appExprSize, t.inputTypes)
    else if length(t.inputTypes) < es.appExprSize
    then t.inputTypes ++ repeat(errorType(), es.appExprSize - length(t.inputTypes))
    else t.inputTypes;
  
  -- NOTE: REVERSED ORDER
  -- We may need to resolve e's type to get at the actual 'function type'
  local t :: Type = performSubstitution(e.typerep, e.upSubst);
  es.appExprTypereps = reverse(correctNumTypes);
  es.appExprApplied = e.unparse;
  anns.appExprApplied = e.unparse;
  anns.remainingFuncAnnotations = t.namedTypes;
  anns.funcAnnotations = anns.remainingFuncAnnotations;
  
  top.errors <- 
    if !t.isApplicable
    then []
    else if length(t.inputTypes) > es.appExprSize
    then [err(top.location, "Too few arguments provided to function '" ++ e.unparse ++ "'")]
    else if length(t.inputTypes) < es.appExprSize
    then [err(top.location, "Too many arguments provided to function '" ++ e.unparse ++ "'")]
    else [];

  -- TODO: You know, since the rule is we can't access .typerep without "first" supplying
  -- .downSubst, perhaps we should just... report .typerep after substitution in the first place!
  forwards to t.applicationDispatcher(e, es, anns, top.location);
}

concrete production applicationAnno
top::Expr ::= e::Expr '(' anns::AnnoAppExprs ')'
{
  forwards to application(e, $2, emptyAppExprs(location=$2.location), ',', anns, $4, location=top.location);
}
concrete production applicationExpr
top::Expr ::= e::Expr '(' es::AppExprs ')'
{
  forwards to application(e, $2, es, ',', emptyAnnoAppExprs(location=$4.location), $4, location=top.location);
}
concrete production applicationEmpty
top::Expr ::= e::Expr '(' ')'
{
  forwards to application(e, $2, emptyAppExprs(location=$2.location), ',', emptyAnnoAppExprs(location=$3.location), $3, location=top.location);
}

abstract production errorApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.unparse = e.unparse ++ "(" ++ es.unparse ++ "," ++ anns.unparse ++ ")";
  
  top.errors <- e.errors;
  top.errors <-
    if e.typerep.isError then [] else  
      [err(top.location, e.unparse ++ " has type " ++ prettyType(performSubstitution(e.typerep, e.upSubst)) ++
        " and cannot be invoked as a function.")];
        -- TODO This error message is cumbersomely generated...

  top.typerep = errorType();
}

-- Note that this applies to both function and productions.
-- We don't distinguish anymore at this point. A production reference
-- becomes a function, effectively.
abstract production functionApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.unparse = e.unparse ++ "(" ++ es.unparse ++ "," ++ anns.unparse ++ ")";
  propagate freeVars;
  
  -- TODO: we have an ambiguity here in the longer term.
  -- How to distinguish between
  -- foo(x) where there is an annotation 'a'?
  -- Is this partial application, give (Foo ::= ;a::Something) or (Foo) + error.
  -- Possibly this can be solved by having somehting like "foo(x,a=?)"
  forwards to if es.isPartial || anns.isPartial
              then partialApplication(e, es, anns, location=top.location)
              else functionInvocation(e, es, anns, location=top.location);
}

abstract production functionInvocation
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.unparse = e.unparse ++ "(" ++ es.unparse ++ "," ++ anns.unparse ++ ")";
  
  top.errors <- e.errors ++ es.errors ++ anns.errors;

  local ety :: Type = performSubstitution(e.typerep, e.upSubst);

  top.typerep = ety.outputType;
}

abstract production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.unparse = e.unparse ++ "(" ++ es.unparse ++ "," ++ anns.unparse ++ ")";
  
  top.errors <- e.errors ++ es.errors ++ anns.errors;

  local ety :: Type = performSubstitution(e.typerep, e.upSubst);

  top.typerep =
    appTypes(
      functionType(length(es.missingTypereps) + length(anns.partialAnnoTypereps), map(fst, anns.missingAnnotations)),
      es.missingTypereps ++ anns.partialAnnoTypereps ++ map(snd, anns.missingAnnotations) ++ [ety.outputType]);
}

concrete production noteAttachment
top::Expr ::= 'attachNote' note::Expr 'on' e::Expr 'end'
{
  top.unparse = "attachNote" ++ note.unparse ++ " on " ++ e.unparse ++ " end";

  top.typerep = e.typerep;

  note.isRoot = false;
  e.isRoot = false;
  e.originRules = top.originRules ++ [note];
}

concrete production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.unparse = "(." ++ q.unparse ++ ")";
  
  -- Fresh variable for the input type, and we'll come back later and check that it occurs on that type.
  
  local rawInputType :: Type = freshType();
  top.typerep = appTypes(functionType(1, []), [rawInputType, q.lookupAttribute.typeScheme.typerep]);
  
  top.errors <- q.lookupAttribute.errors;
  
  top.errors <- if null(q.lookupAttribute.typeScheme.boundVars) then []
                else [err(q.location, "Attribute " ++ q.name ++ " is parameterized, and attribute sections currently do not work with parameterized attributes, yet.")]; -- TODO The type inference system is too weak, currently.
  
  top.errors <- if !q.lookupAttribute.found || q.lookupAttribute.dcl.isSynthesized then []
                else [err(q.location, "Only synthesized attributes are currently supported in attribute sections.")];
  
  -- Only known after the inference pass (uses final subst)
  production attribute inputType :: Type;
  inputType = performSubstitution(rawInputType, top.finalSubst);
  
  -- We're not using QNameAttrOccur right now because that assumes we know the nonterminal
  -- so we can filter down to just attributes occurring on that. We could probably fix this
  -- to make it work either way.
  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(q, if inputType.isDecorated then inputType.decoratedType else inputType);

  top.errors <- occursCheck.errors;
}

-- NOTE: this is not intended to be used normally.
-- Its purpose is for test cases. Essentially all other situations should never care what the forward tree is.
concrete production forwardAccess
top::Expr ::= e::Expr '.' 'forward'
{
  top.unparse = e.unparse ++ ".forward";
  top.typerep = e.typerep;
}

concrete production access
top::Expr ::= e::Expr '.' q::QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;
  propagate freeVars;
  
  -- We don't include 'q' here because this might be a terminal, where
  -- 'q' shouldn't actually resolve to a name!
  top.errors := e.errors ++ forward.errors;
  
  local eTy::Type = performSubstitution(e.typerep, e.upSubst);
  q.attrFor = if eTy.isDecorated then eTy.decoratedType else eTy;
  
  -- Note: we're first consulting the TYPE of the LHS.
  forwards to eTy.accessHandler(e, q, top.location);
  -- This jumps to:
  -- errorAccessHandler  (e.g. 1.unparse)
  -- undecoratedAccessHandler
  -- decoratedAccessHandler  (see that production, for how normal attribute access proceeds!)
  -- terminalAccessHandler
}

abstract production errorAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;
  
  top.typerep = errorType();
  top.errors <- q.errors;
  
  top.errors <-
    if e.typerep.isError then [] else
      let ref :: String =
            if length(e.unparse) < 12 then "'" ++ e.unparse ++ "' has" else "LHS of '.' is"
       in [err(top.location, ref ++ " type " ++ prettyType(q.attrFor) ++ " and cannot have attributes.")]
      end;
}

abstract production annoAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;
  
  production index :: Integer =
    findNamedSigElem(q.name, annotationsForNonterminal(q.attrFor, top.env), 0);

  top.typerep = q.typerep;
  
  top.errors <- q.errors;
}

abstract production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;
  
  -- NO use of q.errors, as that become nonsensical here.
  
  top.errors <-
    if q.name == "lexeme" || q.name == "location" || 
       q.name == "filename" || q.name == "line" || q.name == "column"
    then []
    else [err(q.location, q.name ++ " is not a terminal attribute")];

  top.typerep =
    if q.name == "lexeme" || q.name == "filename"
    then stringType()
    else if q.name == "line" || q.name == "column"
    then intType()
    else if q.name == "location"
    then nonterminalType("silver:core:Location", [], false)
    else errorType();
}

abstract production undecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;
  propagate freeVars;

  top.errors := q.errors ++ forward.errors; -- so that these errors appear first.
  
  -- TODO: We should consider disambiguating based on what dcls *actually*
  -- occur on the LHS here.
  
  -- Note: LHS is UNdecorated, here we dispatch based on the kind of attribute.
  forwards to if !q.found then errorDecoratedAccessHandler(e, q, location=top.location)
              else q.attrDcl.undecoratedAccessHandler(e, q, top.location);
  -- annoAccessHandler
  -- accessBouncer
}

{--
 - Accessing an attribute occasionally demands manipulating the left-hand side.
 - This production is intended to permit that.
 -}
abstract production accessBouncer
top::Expr ::= target::(Expr ::= Decorated Expr  Decorated QNameAttrOccur  Location) e::Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;

  -- Basically the only purpose here is to decorate 'e'.
  forwards to target(e, q, top.location);
}
function accessBounceDecorate
Expr ::= target::(Expr ::= Decorated Expr  Decorated QNameAttrOccur  Location) e::Decorated Expr  q::Decorated QNameAttrOccur  l::Location
{
  return accessBouncer(target, decorateExprWithEmpty('decorate', exprRef(e, location=l), 'with', '{', '}', location=l), q, location=l);
}
function accessBounceUndecorate
Expr ::= target::(Expr ::= Decorated Expr  Decorated QNameAttrOccur  Location) e::Decorated Expr  q::Decorated QNameAttrOccur  l::Location
{
  return accessBouncer(target, newFunction('new', '(', exprRef(e, location=l), ')', location=l), q, location=l);
}

abstract production decoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;
  propagate freeVars;

  top.errors := q.errors ++ forward.errors; -- so that these errors appear first.
  
  -- TODO: We should consider disambiguating based on what dcls *actually*
  -- occur on the LHS here.
  
  -- Note: LHS is decorated, here we dispatch based on the kind of attribute.
  forwards to if !q.found then errorDecoratedAccessHandler(e, q, location=top.location)
              else q.attrDcl.decoratedAccessHandler(e, q, top.location);
  -- From here we go to:
  -- synDecoratedAccessHandler
  -- inhDecoratedAccessHandler
  -- errorDecoratedAccessHandler  -- unknown attribute error raised already.
}

abstract production synDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;
  
  top.typerep = q.typerep;
}

abstract production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;
  
  top.typerep = q.typerep;
}

-- TODO: change name. really "unknownDclAccessHandler"
abstract production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;

  top.typerep = errorType();
}


concrete production decorateExprWithEmpty
top::Expr ::= 'decorate' e::Expr 'with' '{' '}'
{
  top.unparse = "decorate " ++ e.unparse ++ " with {}";

  forwards to decorateExprWith($1, e, $3, $4, exprInhsEmpty(location=top.location), $5, location=top.location);
}

concrete production decorateExprWith
top::Expr ::= 'decorate' e::Expr 'with' '{' inh::ExprInhs '}'
{
  top.unparse = "decorate " ++ e.unparse ++ " with {" ++ inh.unparse ++ "}";

  top.typerep = decoratedType(performSubstitution(e.typerep, e.upSubst)); -- .decoratedForm?
  e.isRoot = false;
  
  inh.decoratingnt = performSubstitution(e.typerep, e.upSubst);
}

abstract production exprInhsEmpty
top::ExprInhs ::= 
{
  top.unparse = "";
  
  top.suppliedInhs = [];
}

concrete production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  top.unparse = lhs.unparse;
  
  top.suppliedInhs = lhs.suppliedInhs;
}

concrete production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  top.unparse = lhs.unparse ++ " " ++ inh.unparse;
  
  top.suppliedInhs = lhs.suppliedInhs ++ inh.suppliedInhs;
}

concrete production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  top.unparse = lhs.unparse ++ " = " ++ e.unparse ++ ";";
  
  top.suppliedInhs = lhs.suppliedInhs;
}

concrete production exprLhsExpr
top::ExprLHSExpr ::= q::QNameAttrOccur
{
  top.name = q.name;
  top.unparse = q.unparse;

  top.typerep = q.typerep;
  top.suppliedInhs = [q.dcl.attrOccurring];
  
  q.attrFor = top.decoratingnt;
}

concrete production trueConst
top::Expr ::= 'true'
{
  top.unparse = "true";
  
  top.typerep = boolType();
}

concrete production falseConst
top::Expr ::= 'false'
{
  top.unparse = "false";
  
  top.typerep = boolType();
}

concrete production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.unparse = e1.unparse ++ " && " ++ e2.unparse;

  top.typerep = boolType();

  e1.isRoot = false;
  e2.isRoot = false;
}

concrete production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.unparse = e1.unparse ++ " || " ++ e2.unparse;

  top.typerep = boolType();

  e1.isRoot = false;
  e2.isRoot = false;
}

concrete production not
top::Expr ::= '!' e::Expr
{
  top.unparse = "! " ++ e.unparse;

  top.typerep = boolType();

  e.isRoot = false;
}

concrete production gtOp
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.unparse = e1.unparse ++ " > " ++ e2.unparse;

  forwards to
    -- silver:core:gt(e1, e2)
    applicationExpr(
      baseExpr(qName(top.location, "silver:core:gt"), location=top.location), '(',
      snocAppExprs(
        oneAppExprs(presentAppExpr(e1, location=top.location), location=top.location), ',',
        presentAppExpr(e2, location=top.location),
        location=top.location),')',
      location=top.location);
}

concrete production ltOp
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.unparse = e1.unparse ++ " < " ++ e2.unparse;

  forwards to
    -- silver:core:lt(e1, e2)
    applicationExpr(
      baseExpr(qName(top.location, "silver:core:lt"), location=top.location), '(',
      snocAppExprs(
        oneAppExprs(presentAppExpr(e1, location=top.location), location=top.location), ',',
        presentAppExpr(e2, location=top.location),
        location=top.location),')',
      location=top.location);
}

concrete production gteOp
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.unparse = e1.unparse ++ " >= " ++ e2.unparse;

  forwards to
    -- silver:core:gte(e1, e2)
    applicationExpr(
      baseExpr(qName(top.location, "silver:core:gte"), location=top.location), '(',
      snocAppExprs(
        oneAppExprs(presentAppExpr(e1, location=top.location), location=top.location), ',',
        presentAppExpr(e2, location=top.location),
        location=top.location),')',
      location=top.location);
}

concrete production lteOp
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.unparse = e1.unparse ++ " <= " ++ e2.unparse;

  forwards to
    -- silver:core:lte(e1, e2)
    applicationExpr(
      baseExpr(qName(top.location, "silver:core:lte"), location=top.location), '(',
      snocAppExprs(
        oneAppExprs(presentAppExpr(e1, location=top.location), location=top.location), ',',
        presentAppExpr(e2, location=top.location),
        location=top.location),')',
      location=top.location);
}

concrete production eqOp
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.unparse = e1.unparse ++ " == " ++ e2.unparse;

  forwards to
    -- silver:core:eq(e1, e2)
    applicationExpr(
      baseExpr(qName(top.location, "silver:core:eq"), location=top.location), '(',
      snocAppExprs(
        oneAppExprs(presentAppExpr(e1, location=top.location), location=top.location), ',',
        presentAppExpr(e2, location=top.location),
        location=top.location),')',
      location=top.location);
}

concrete production neqOp
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.unparse = e1.unparse ++ " != " ++ e2.unparse;

  forwards to
    -- silver:core:neq(e1, e2)
    applicationExpr(
      baseExpr(qName(top.location, "silver:core:neq"), location=top.location), '(',
      snocAppExprs(
        oneAppExprs(presentAppExpr(e1, location=top.location), location=top.location), ',',
        presentAppExpr(e2, location=top.location),
        location=top.location),')',
      location=top.location);
}

concrete production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
precedence = 0
{
  top.unparse = "if " ++ e1.unparse ++ " then " ++ e2.unparse ++ " else " ++ e3.unparse;

  top.typerep = e2.typerep;
}

concrete production intConst
top::Expr ::= i::Int_t
{
  top.unparse = i.lexeme;

  top.typerep = intType();
}

concrete production floatConst
top::Expr ::= f::Float_t
{
  top.unparse = f.lexeme;

  top.typerep = floatType();
} 

concrete production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.unparse = e1.unparse ++ " + " ++ e2.unparse;

  top.typerep = e1.typerep;

  e1.isRoot=false;
  e2.isRoot=false;
}

concrete production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.unparse = e1.unparse ++ " - " ++ e2.unparse;

  top.typerep = e1.typerep;

  e1.isRoot=false;
  e2.isRoot=false;
}

concrete production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.unparse = e1.unparse ++ " * " ++ e2.unparse;

  top.typerep = e1.typerep;

  e1.isRoot=false;
  e2.isRoot=false;
}

concrete production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.unparse = e1.unparse ++ " / " ++ e2.unparse;

  top.typerep = e1.typerep;

  e1.isRoot=false;
  e2.isRoot=false;
}

concrete production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  top.unparse = e1.unparse ++ " % " ++ e2.unparse;

  top.typerep = e1.typerep;

  e1.isRoot=false;
  e2.isRoot=false;
}

concrete production neg
top::Expr ::= '-' e::Expr
precedence = 13
{
  top.unparse = "- " ++ e.unparse;

  top.typerep = e.typerep;

  e.isRoot=false;
}

concrete production stringConst
top::Expr ::= s::String_t
{
  top.unparse = s.lexeme;

  top.typerep = stringType();
}

concrete production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
  top.unparse = e1.unparse ++ " ++ " ++ e2.unparse;

  forwards to
    -- silver:core:append(e1, e2)
    applicationExpr(
      baseExpr(qName(top.location, "silver:core:append"), location=top.location), '(',
      snocAppExprs(
        oneAppExprs(presentAppExpr(e1, location=top.location), location=top.location), ',',
        presentAppExpr(e2, location=top.location),
        location=top.location),')',
      location=top.location);
}

-- These sorta seem obsolete, but there are some important differences from AppExprs.
-- For one, AppExprs expects a fixed, imposed list of types. Here we're flexible!
-- This is used by both pattern matching and list literals.
abstract production exprsEmpty
top::Exprs ::=
{
  top.unparse = "";
  
  top.exprs := [];
  top.rawExprs := [];
}
concrete production exprsSingle
top::Exprs ::= e::Expr
{
  top.unparse = e.unparse;

  top.exprs := [e];
  top.rawExprs := [e];
}
concrete production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.unparse = e1.unparse ++ ", " ++ e2.unparse;

  top.exprs := [e1] ++ e2.exprs;
  top.rawExprs := [e1] ++ e2.rawExprs;
}


{--
 - Exprs with optional underscores omitting parameters. Used exclusively for
 - (partial) function application.
 -}
nonterminal AppExprs with 
  config, grammarName, env, location, unparse, errors, freeVars, frame, compiledGrammars, exprs, rawExprs,
  isPartial, missingTypereps, appExprIndicies, appExprSize, appExprTypereps, appExprApplied, isRoot, originRules;

nonterminal AppExpr with
  config, grammarName, env, location, unparse, errors, freeVars, frame, compiledGrammars, exprs, rawExprs,
  isPartial, missingTypereps, appExprIndicies, appExprIndex, appExprTyperep, appExprApplied, isRoot, originRules;

propagate errors, freeVars on AppExprs, AppExpr;
propagate exprs, rawExprs on AppExprs;

synthesized attribute isPartial :: Boolean;
synthesized attribute missingTypereps :: [Type];
synthesized attribute appExprIndicies :: [Integer];
synthesized attribute appExprSize :: Integer;
inherited attribute appExprIndex :: Integer;
inherited attribute appExprTypereps :: [Type];
inherited attribute appExprTyperep :: Type;
autocopy attribute appExprApplied :: String;

-- These are the "new" Exprs syntax. This allows missing (_) arguments, to indicate partial application.
concrete production missingAppExpr
top::AppExpr ::= '_'
{
  top.unparse = "_";
  
  top.isPartial = true;
  top.missingTypereps = [top.appExprTyperep];
  
  top.rawExprs := [];
  top.exprs := [];
  top.appExprIndicies = [];
}
concrete production presentAppExpr
top::AppExpr ::= e::Expr
{
  top.unparse = e.unparse;
  
  top.isPartial = false;
  top.missingTypereps = [];
  
  top.rawExprs := [e];
  top.exprs := [e];
  top.appExprIndicies = [top.appExprIndex];
}

concrete production snocAppExprs
top::AppExprs ::= es::AppExprs ',' e::AppExpr
{
  top.unparse = es.unparse ++ ", " ++ e.unparse;

  top.isPartial = es.isPartial || e.isPartial;
  top.missingTypereps = es.missingTypereps ++ e.missingTypereps;

  top.appExprIndicies = es.appExprIndicies ++ e.appExprIndicies;

  top.appExprSize = es.appExprSize + 1;

  e.appExprIndex = es.appExprSize;
  e.appExprTyperep = head(top.appExprTypereps);
  
  es.appExprTypereps = tail(top.appExprTypereps);
}
concrete production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  top.unparse = e.unparse;

  top.isPartial = e.isPartial;
  top.missingTypereps = e.missingTypereps;

  top.appExprIndicies = e.appExprIndicies;
  
  top.appExprSize = 1;

  e.appExprIndex = 0;
  e.appExprTyperep = if null(top.appExprTypereps)
                     then errorType()
                     else head(top.appExprTypereps);
}
abstract production emptyAppExprs
top::AppExprs ::=
{
  top.unparse = "";

  top.isPartial = false;
  top.missingTypereps = [];

  top.appExprIndicies = [];
  top.appExprSize = 0;

  -- Assumption: We only get here when we're looking at ()
  -- i.e. we can't ever have 'too many' provided error
  top.errors <- if null(top.appExprTypereps) then []
                else [err(top.location, "Too few arguments provided to function '" ++ top.appExprApplied ++ "'")];
}


nonterminal AnnoAppExprs with
  config, grammarName, env, location, unparse, errors, freeVars, frame, compiledGrammars,
  isPartial, appExprApplied, exprs,
  remainingFuncAnnotations, funcAnnotations,
  missingAnnotations, partialAnnoTypereps, annoIndexConverted, annoIndexSupplied, isRoot, originRules;
nonterminal AnnoExpr with
  config, grammarName, env, location, unparse, errors, freeVars, frame, compiledGrammars,
  isPartial, appExprApplied, exprs,
  remainingFuncAnnotations, funcAnnotations,
  missingAnnotations, partialAnnoTypereps, annoIndexConverted, annoIndexSupplied, isRoot, originRules;
  
propagate errors, freeVars, exprs on AnnoAppExprs, AnnoExpr;

{--
 - Annotations that have not yet been supplied
 -}
inherited attribute remainingFuncAnnotations :: [Pair<String Type>];
{--
 - All annotations of this function
 -}
autocopy attribute funcAnnotations :: [Pair<String Type>];
{--
 - Annotations that have not been supplied (by subtracting from remainingFuncAnnotations)
 -}
synthesized attribute missingAnnotations :: [Pair<String Type>];
{--
 - Typereps of those annotations that are partial (_)
 -}
synthesized attribute partialAnnoTypereps :: [Type];

synthesized attribute annoIndexConverted :: [Integer];
synthesized attribute annoIndexSupplied :: [Integer];

concrete production annoExpr
top::AnnoExpr ::= qn::QName '=' e::AppExpr
{
  top.unparse = qn.unparse ++ "=" ++ e.unparse;
  
  local fq :: Pair<Maybe<Pair<String Type>> [Pair<String Type>]> =
    extractNamedArg(qn.name, top.remainingFuncAnnotations);
    
  e.appExprIndex =
    findNamedArgType(qn.name, top.funcAnnotations, 0);
  e.appExprTyperep =
    if fq.fst.isJust then fq.fst.fromJust.snd else errorType();
    
  top.missingAnnotations = fq.snd; -- minus qn, if it was there
  top.partialAnnoTypereps = e.missingTypereps;
  
  top.errors <-
    if fq.fst.isJust then []
    else [err(qn.location, "Named parameter '" ++ qn.name ++ "' is not appropriate for '" ++ top.appExprApplied ++ "'")];
  top.isPartial = e.isPartial;
  top.annoIndexConverted =
    if e.isPartial then [e.appExprIndex] else [];
  top.annoIndexSupplied =
    if e.isPartial then [] else [e.appExprIndex];
}

concrete production snocAnnoAppExprs
top::AnnoAppExprs ::= es::AnnoAppExprs ',' e::AnnoExpr
{
  top.unparse = es.unparse ++ ", " ++ e.unparse;

  top.isPartial = es.isPartial || e.isPartial;

  e.remainingFuncAnnotations = top.remainingFuncAnnotations;
  es.remainingFuncAnnotations = e.missingAnnotations;
  top.missingAnnotations = es.missingAnnotations;
  
  top.partialAnnoTypereps = es.partialAnnoTypereps ++ e.partialAnnoTypereps;
  top.annoIndexConverted = es.annoIndexConverted ++ e.annoIndexConverted;
  top.annoIndexSupplied = es.annoIndexSupplied ++ e.annoIndexSupplied;
}

concrete production oneAnnoAppExprs
top::AnnoAppExprs ::= e::AnnoExpr
{
  top.unparse = e.unparse;

  top.isPartial = e.isPartial;
  top.errors <-
    if null(top.missingAnnotations) then []
    else [err(top.location, "Missing named parameters for function '" ++ top.appExprApplied ++ "': "
      ++ implode(", ", map(fst, top.missingAnnotations)))];

  top.errors <- e.errors;

  e.remainingFuncAnnotations = top.remainingFuncAnnotations;
  top.missingAnnotations = e.missingAnnotations;

  top.partialAnnoTypereps = e.partialAnnoTypereps;
  top.annoIndexConverted = e.annoIndexConverted;
  top.annoIndexSupplied = e.annoIndexSupplied;
}

abstract production emptyAnnoAppExprs
top::AnnoAppExprs ::=
{
  top.unparse = "";

  top.isPartial = false;
  top.errors <-
    if null(top.missingAnnotations) then []
    else [err(top.location, "Missing named parameters for function '" ++ top.appExprApplied ++ "': "
      ++ implode(", ", map(fst, top.missingAnnotations)))];

  top.missingAnnotations = top.remainingFuncAnnotations;
  
  top.partialAnnoTypereps = [];
  top.annoIndexConverted = [];
  top.annoIndexSupplied = [];
}

function reorderedAnnoAppExprs
[Decorated Expr] ::= d::Decorated AnnoAppExprs
{
  -- This is an annoyingly poor quality implementation
  return map(snd, sortBy(reorderedLte, zipWith(pair, d.annoIndexSupplied, d.exprs)));
}
function reorderedLte
Boolean ::= l::Pair<Integer Decorated Expr>  r::Pair<Integer Decorated Expr> { return l.fst <= r.fst; }

function extractNamedArg
Pair<Maybe<Pair<String Type>> [Pair<String Type>]> ::= n::String  l::[Pair<String Type>]
{
  local recurse :: Pair<Maybe<Pair<String Type>> [Pair<String Type>]> =
    extractNamedArg(n, tail(l));

  return if null(l) then pair(nothing(), [])
  else if head(l).fst == n then pair(just(head(l)), tail(l))
  else pair(recurse.fst, head(l) :: recurse.snd);
}

function findNamedArgType
Integer ::= s::String l::[Pair<String Type>] z::Integer
{
  return if null(l) then -1
  else if s == head(l).fst then z
  else findNamedArgType(s, tail(l), z+1);
}


{--
 - Utility for other modules to create function invocations.
 - This makes no assumptions, use it any way you wish!
 -}
function mkStrFunctionInvocation
Expr ::= l::Location  e::String  es::[Expr]
{
  return mkFullFunctionInvocation(l, baseExpr(qName(l, e), location=l), es, []);
}
function mkFunctionInvocation
Expr ::= l::Location  e::Expr  es::[Expr]
{
  return mkFullFunctionInvocation(l, e, es, []);
}
function mkFullFunctionInvocation
Expr ::= l::Location  e::Expr  es::[Expr]  ans::[Pair<String Expr>]
{
  return application(e, '(',
    foldl(snocAppExprs(_, ',', _, location=l), emptyAppExprs(location=l),
      map(presentAppExpr(_, location=l), es)),
    ',',
    foldl(snocAnnoAppExprs(_, ',', _, location=l), emptyAnnoAppExprs(location=l),
      map(mkAnnoExpr, ans)),
    ')', location=l);
}
-- Internal helper function
function mkAnnoExpr
AnnoExpr ::= p::Pair<String Expr>
{
  return annoExpr(qName(p.snd.location, p.fst), '=', presentAppExpr(p.snd, location=p.snd.location), location=p.snd.location);
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
Expr ::= l::Location  e::Expr  es::[Decorated Expr]
{
  return mkFunctionInvocation(l, e, map(exprRef(_, location=l), es));
}
function mkStrFunctionInvocationDecorated
Expr ::= l::Location  e::String  es::[Decorated Expr]
{
  return mkFunctionInvocation(l, baseExpr(qName(l, e), location=l), map(exprRef(_, location=l), es));
}

{--
 - We allow references to existing subexpressions to appear arbitrarily in trees.
 - 
 - There is one MAJOR restriction on the use of this production:
 -   The referenced expression (e) MUST APPEAR in the same expression tree
 -   as it is referenced in.
 -
 - This is for type information reasons: the subtree referenced must have been
 - typechecked in the same 'typing context' as wherever this tree appears.
 -
 - This is trivially satisfied for the typical use case for this production,
 - where you're typechecking your children, then forwarding to some tree with
 - references to those children.
 -}
abstract production exprRef
top::Expr ::= e::Decorated Expr
{
  top.unparse = e.unparse;
  top.freeVars <- e.freeVars;

  -- See the major restriction. This should have been checked for error already!
  top.typerep = e.typerep;
  
  -- TODO: one of the little things we might want is to make this transparent to
  -- forwarding. e.g. e might be a 'childReference' and pattern matching would
  -- need to separately account for this!
  -- To accomplish this, we might want some notion of a decorated forward.
}

