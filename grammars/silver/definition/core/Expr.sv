grammar silver:definition:core;

--import silver:analysis:typechecking:core;

nonterminal Expr with
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, typerep, monadRewritten<Expr>;
nonterminal Exprs with
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, exprs, rawExprs;

nonterminal ExprInhs with
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, decoratingnt, suppliedInhs;
nonterminal ExprInh with
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, decoratingnt, suppliedInhs;
nonterminal ExprLHSExpr with
  config, grammarName, env, location, unparse, errors, name, typerep, decoratingnt, suppliedInhs;

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
synthesized attribute exprs :: [Decorated Expr];
{--
 - Get each individual Expr, without decorating them.
 -}
synthesized attribute rawExprs :: [Expr];


aspect default production
top::Expr ::=
{
  top.monadRewritten = error("Attribute monadRewritten must be defined on all productions");
}


abstract production errorExpr
top::Expr ::= e::[Message]
{
  top.unparse = s"{- Errors:\n${messagesToString(e)} -}";
  top.errors := e;
  top.typerep = errorType();

  top.monadRewritten = top;
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
  
  forwards to if null(q.lookupValue.dcls)
              then errorReference(q.lookupValue.errors, q, location=top.location)
              else q.lookupValue.dcl.refDispatcher(q, top.location);
}

abstract production errorReference
top::Expr ::= msg::[Message]  q::Decorated QName
{
  top.unparse = q.unparse;
  
  top.errors := msg;
  top.typerep = errorType();
}

-- TODO: We should separate this out, even, to be "nonterminal/decorable" and "as-is"
abstract production childReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  
  top.errors := [];
  top.typerep = if q.lookupValue.typerep.isDecorable
                then ntOrDecType(q.lookupValue.typerep, freshType())
                else q.lookupValue.typerep;
}

abstract production lhsReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  
  top.errors := [];
  -- An LHS is *always* a decorable (nonterminal) type.
  top.typerep = ntOrDecType(q.lookupValue.typerep, freshType());
}

abstract production localReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  
  top.errors := [];
  top.typerep = if q.lookupValue.typerep.isDecorable
                then ntOrDecType(q.lookupValue.typerep, freshType())
                else q.lookupValue.typerep;
}

abstract production forwardReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;
  
  top.errors := [];
  -- An LHS (and thus, forward) is *always* a decorable (nonterminal) type.
  top.typerep = ntOrDecType(q.lookupValue.typerep, freshType());
}

-- Note here that production and function *references* are distinguished.
-- Later on, we do *not* distinguish for application.

abstract production productionReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;

  top.errors := [];

  -- TODO: the freshening should probably be the responsibility of the thing in the environment, not here?
  top.typerep = freshenCompletely(q.lookupValue.typerep);
}

abstract production functionReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;

  top.errors := [];

  top.typerep = freshenCompletely(q.lookupValue.typerep); -- TODO see above
}

abstract production globalValueReference
top::Expr ::= q::Decorated QName
{
  top.unparse = q.unparse;

  top.errors := [];

  top.typerep = freshenCompletely(q.lookupValue.typerep); -- TODO see above
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
  
  -- TODO: You know, since the rule is we can't access .typerep without "first" supplying
  -- .downSubst, perhaps we should just... report .typerep after substitution in the first place!
  forwards to performSubstitution(e.typerep, e.upSubst).applicationDispatcher(e, es, anns, top.location);
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
top::Expr ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs
{
  top.unparse = e.unparse ++ "(" ++ es.unparse ++ "," ++ anns.unparse ++ ")";
  
  top.errors := e.errors ++
    (if e.typerep.isError then [] else  
    [err(top.location, e.unparse ++ " has type " ++ prettyType(performSubstitution(e.typerep, e.upSubst)) ++
      " and cannot be invoked as a function.")]) ++
    es.errors ++ anns.errors;
        -- TODO This error message is cumbersomely generated...

  top.typerep = errorType();
  
  es.appExprTypereps = [];
  es.appExprApplied = e.unparse;
  anns.appExprApplied = e.unparse;
  anns.remainingFuncAnnotations = [];
  anns.funcAnnotations = [];
}

-- Note that this applies to both function and productions.
-- We don't distinguish anymore at this point. A production reference
-- becomes a function, effectively.
abstract production functionApplication
top::Expr ::= e::Decorated Expr es::AppExprs anns::AnnoAppExprs
{
  top.unparse = e.unparse ++ "(" ++ es.unparse ++ "," ++ anns.unparse ++ ")";
  
  -- NOTE: REVERSED ORDER
  -- We may need to resolve e's type to get at the actual 'function type'
  local t :: Type = performSubstitution(e.typerep, e.upSubst);
  es.appExprTypereps = reverse(t.inputTypes);
  es.appExprApplied = e.unparse;
  anns.appExprApplied = e.unparse;
  anns.remainingFuncAnnotations = t.namedTypes;
  anns.funcAnnotations = anns.remainingFuncAnnotations;
  
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
  
  top.errors := e.errors ++ es.errors ++ anns.errors;

  local ety :: Type = performSubstitution(e.typerep, e.upSubst);

  top.typerep = ety.outputType;
}

abstract production partialApplication
top::Expr ::= e::Decorated Expr es::Decorated AppExprs anns::Decorated AnnoAppExprs
{
  top.unparse = e.unparse ++ "(" ++ es.unparse ++ "," ++ anns.unparse ++ ")";
  
  top.errors := e.errors ++ es.errors ++ anns.errors;

  local ety :: Type = performSubstitution(e.typerep, e.upSubst);

  top.typerep = functionType(ety.outputType, es.missingTypereps ++ anns.partialAnnoTypereps, anns.missingAnnotations);
}

concrete production attributeSection
top::Expr ::= '(' '.' q::QName ')'
{
  top.unparse = "(." ++ q.unparse ++ ")";
  
  -- Fresh variable for the input type, and we'll come back later and check that it occurs on that type.
  
  -- Also, freshen the attribute type, because even though there currently should NOT be any type variables
  -- there, there could be if the code will raise an error.
  local rawInputType :: Type = freshType();
  top.typerep = functionType(freshenCompletely(q.lookupAttribute.typerep), [rawInputType], []);
  
  top.errors := q.lookupAttribute.errors;
  
  top.errors <- if null(q.lookupAttribute.dclBoundVars) then []
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
  top.errors := e.errors;
  top.typerep = e.typerep;
}

concrete production access
top::Expr ::= e::Expr '.' q::QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;
  
  -- We don't include 'q' here because this might be a terminal, where
  -- 'q' shouldn't actually resolve to a name!
  top.errors := e.errors ++ forward.errors;
  
  q.attrFor = performSubstitution(e.typerep, e.upSubst);
  
  -- Note: we're first consulting the TYPE of the LHS.
  forwards to q.attrFor.accessHandler(e, q, top.location);
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
  top.errors := accessError ++ q.errors;
  
  local accessError :: [Message] =
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
  
  top.errors := q.errors;
}

abstract production terminalAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;
  
  -- NO use of q.errors, as that become nonsensical here.
  
  top.errors :=
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
    then nonterminalType("core:Location", [])
    else errorType();
}

abstract production undecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;

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
  top.errors := []; -- already included?
}

abstract production inhDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;
  
  top.typerep = q.typerep;
  top.errors := []; -- already included?
}

-- TODO: change name. really "unknownDclAccessHandler"
abstract production errorDecoratedAccessHandler
top::Expr ::= e::Decorated Expr  q::Decorated QNameAttrOccur
{
  top.unparse = e.unparse ++ "." ++ q.unparse;

  top.errors := []; -- empty because we only ever get here if lookup failed. see above.

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
  top.errors := e.errors ++ inh.errors;
  
  inh.decoratingnt = performSubstitution(e.typerep, e.upSubst);
}

abstract production exprInhsEmpty
top::ExprInhs ::= 
{
  top.unparse = "";
  
  top.errors := [];
  top.suppliedInhs = [];
}

concrete production exprInhsOne
top::ExprInhs ::= lhs::ExprInh
{
  top.unparse = lhs.unparse;
  
  top.errors := lhs.errors;
  top.suppliedInhs = lhs.suppliedInhs;
}

concrete production exprInhsCons
top::ExprInhs ::= lhs::ExprInh inh::ExprInhs
{
  top.unparse = lhs.unparse ++ " " ++ inh.unparse;
  
  top.errors := lhs.errors ++ inh.errors;
  top.suppliedInhs = lhs.suppliedInhs ++ inh.suppliedInhs;
}

concrete production exprInh
top::ExprInh ::= lhs::ExprLHSExpr '=' e::Expr ';'
{
  top.unparse = lhs.unparse ++ " = " ++ e.unparse ++ ";";
  
  top.errors := lhs.errors ++ e.errors;
  top.suppliedInhs = lhs.suppliedInhs;
}

concrete production exprLhsExpr
top::ExprLHSExpr ::= q::QNameAttrOccur
{
  top.name = q.name;
  top.unparse = q.unparse;

  top.errors := q.errors;
  top.typerep = q.typerep;
  top.suppliedInhs = [q.dcl.attrOccurring];
  
  q.attrFor = top.decoratingnt;
}

concrete production trueConst
top::Expr ::= 'true'
{
  top.unparse = "true";
  
  top.errors := [];
  top.typerep = boolType();

  top.monadRewritten = top;
}

concrete production falseConst
top::Expr ::= 'false'
{
  top.unparse = "false";
  
  top.errors := [];
  top.typerep = boolType();

  top.monadRewritten = top;
}

concrete production and
top::Expr ::= e1::Expr '&&' e2::Expr
{
  top.unparse = e1.unparse ++ " && " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  {-
  if isMonad(e1.typerep)
  then if isMonad(e2.typerep)
       then if monadsMatch(e1.typerep, e2.typerep, top.downSubst)
            then type is e1.typerep, no errors if inner is bool
            else reject it to avoid nested monads
       else check if e2 is bool
  else if isMonad(e2.typerep)
       then check if e1 is bool and e2 inner is bool
  -}
  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep, boolType());
  local errCheck2::TypeCheck = check(if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep, boolType());

  top.errors <-
       if errCheck1.typeerror
       then [err(e1.location, "First operand to && must be type bool or Monad(bool).  Got instead type " ++ errCheck1.leftpp)]
       else [];
  top.errors <-
       if errCheck2.typeerror
       then [err(e2.location, "First operand to && must be type bool or Monad(bool).  Got instead type " ++ errCheck2.leftpp)]
       else [];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to && must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck2.leftpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck2.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  errCheck2.downSubst = errCheck1.upSubst;
  top.upSubst = errCheck2.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then e1.typerep --assume it will be well-typed
                else if isMonad(e2.typerep)
                     then e2.typerep
                     else boolType();
}

concrete production or
top::Expr ::= e1::Expr '||' e2::Expr
{
  top.unparse = e1.unparse ++ " || " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep, boolType());
  local errCheck2::TypeCheck = check(if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep, boolType());

  top.errors <-
       if errCheck1.typeerror
       then [err(e1.location, "First operand to || must be type bool or Monad(bool).  Got instead type " ++ errCheck1.leftpp)]
       else [];
  top.errors <-
       if errCheck2.typeerror
       then [err(e2.location, "First operand to || must be type bool or Monad(bool).  Got instead type " ++ errCheck2.leftpp)]
       else [];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to || must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck2.leftpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck2.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  errCheck2.downSubst = errCheck1.upSubst;
  top.upSubst = errCheck2.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then e1.typerep --assume it will be well-typed
                else if isMonad(e2.typerep)
                     then e2.typerep
                     else boolType();
}

concrete production not
top::Expr ::= '!' e::Expr
{
  top.unparse = "! " ++ e.unparse;

  top.errors := e.errors;

  local errCheck::TypeCheck = check(if isMonad(e.typerep)
                                    then monadInnerType(e.typerep)
                                    else e.typerep, boolType());

  top.errors <-
       if errCheck.typeerror
       then [err(e.location, "Operand to ! must be type bool or Monad(bool).  Got instead type " ++ errCheck.leftpp)]
       else [];

  errCheck.finalSubst = top.finalSubst;
  errCheck.downSubst = e.upSubst;
  top.upSubst = errCheck.upSubst;

  top.typerep = if isMonad(e.typerep)
                then e.typerep --assume it will be well-typed
                else boolType();
}

concrete production gt
top::Expr ::= e1::Expr '>' e2::Expr
{
  top.unparse = e1.unparse ++ " > " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to > must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to > must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to > must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to > must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then monadOfType(e1.typerep, boolType())
                else if isMonad(e2.typerep)
                     then monadOfType(e2.typerep, boolType())
                     else boolType();
}

concrete production lt
top::Expr ::= e1::Expr '<' e2::Expr
{
  top.unparse = e1.unparse ++ " < " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to < must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to < must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to < must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to < must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then monadOfType(e1.typerep, boolType())
                else if isMonad(e2.typerep)
                     then monadOfType(e2.typerep, boolType())
                     else boolType();
}

concrete production gteq
top::Expr ::= e1::Expr '>=' e2::Expr
{
  top.unparse = e1.unparse ++ " >= " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to >= must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to >= must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to >= must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to >= must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then monadOfType(e1.typerep, boolType())
                else if isMonad(e2.typerep)
                     then monadOfType(e2.typerep, boolType())
                     else boolType();
}

concrete production lteq
top::Expr ::= e1::Expr '<=' e2::Expr
{
  top.unparse = e1.unparse ++ " <= " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to <= must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to <= must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to <= must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to <= must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then monadOfType(e1.typerep, boolType())
                else if isMonad(e2.typerep)
                     then monadOfType(e2.typerep, boolType())
                     else boolType();
}

concrete production eqeq
top::Expr ::= e1::Expr '==' e2::Expr
{
  top.unparse = e1.unparse ++ " == " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to > must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to > must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to > must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to > must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then monadOfType(e1.typerep, boolType())
                else if isMonad(e2.typerep)
                     then monadOfType(e2.typerep, boolType())
                     else boolType();
}

concrete production neq
top::Expr ::= e1::Expr '!=' e2::Expr
{
  top.unparse = e1.unparse ++ " != " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to > must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to > must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceOrd
            then []
            else [err(top.location, "Operands to > must be concrete types Integer, Float, String, or TerminalId, or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to > must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then monadOfType(e1.typerep, boolType())
                else if isMonad(e2.typerep)
                     then monadOfType(e2.typerep, boolType())
                     else boolType();
}

concrete production ifThenElse
top::Expr ::= 'if' e1::Expr 'then' e2::Expr 'else' e3::Expr
precedence = 0
{
  top.unparse = "if " ++ e1.unparse ++ " then " ++ e2.unparse ++ " else " ++ e3.unparse;

  top.errors := e1.errors ++ e2.errors ++ e3.errors;
  top.typerep = e2.typerep;
}

concrete production intConst
top::Expr ::= i::Int_t
{
  top.unparse = i.lexeme;

  top.errors := [];
  top.typerep = intType();
}

concrete production floatConst
top::Expr ::= f::Float_t
{
  top.unparse = f.lexeme;

  top.errors := [];
  top.typerep = floatType();
} 

concrete production plus
top::Expr ::= e1::Expr '+' e2::Expr
{
  top.unparse = e1.unparse ++ " + " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to + must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to + must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to + must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to + must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then e1.typerep
                else e2.typerep;
}

concrete production minus
top::Expr ::= e1::Expr '-' e2::Expr
{
  top.unparse = e1.unparse ++ " - " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to - must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to - must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to - must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to - must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then e1.typerep
                else e2.typerep;
}

concrete production multiply
top::Expr ::= e1::Expr '*' e2::Expr
{
  top.unparse = e1.unparse ++ " * " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to * must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to * must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to * must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to * must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then e1.typerep
                else e2.typerep;
}

concrete production divide
top::Expr ::= e1::Expr '/' e2::Expr
{
  top.unparse = e1.unparse ++ " / " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to / must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to / must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to / must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to / must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then e1.typerep
                else e2.typerep;
}

concrete production modulus
top::Expr ::= e1::Expr '%' e2::Expr
{
  top.unparse = e1.unparse ++ " % " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;

  local matchAndSubst::Pair<Boolean Substitution> =
                  if isMonad(e1.typerep) && isMonad(e2.typerep)
                  then monadsMatch(e1.typerep, e2.typerep,
                                   e2.upSubst)
                  else pair(true, e2.upSubst);
  local errCheck1::TypeCheck = check(if isMonad(e1.typerep)
                                     then monadInnerType(e1.typerep)
                                     else e1.typerep,
                                     if isMonad(e2.typerep)
                                     then monadInnerType(e2.typerep)
                                     else e2.typerep);

  top.errors <-
       if errCheck1.typeerror
       then [err(top.location, "Operands to % must be either the same type or monads of the same type.  Got instead type " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)]
       else [];
  top.errors <-
       if isMonad(e1.typerep)
       then if performSubstitution(monadInnerType(e1.typerep), top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to % must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(monadInnerType(e1.typerep), top.finalSubst)))]
       else if performSubstitution(e1.typerep, top.finalSubst).instanceNum
            then []
            else [err(top.location, "Operands to % must be concrete types Integer or Float or monads of these.  Instead they are of type " ++ prettyType(performSubstitution(e1.typerep, top.finalSubst)))];
  top.errors <-
       if matchAndSubst.fst
       then []
       else [err(top.location, "Two monad operands to % must have the same monad.  Got instead " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)];

  errCheck1.finalSubst = top.finalSubst;
  errCheck1.downSubst = matchAndSubst.snd;
  top.upSubst = errCheck1.upSubst;

  top.typerep = if isMonad(e1.typerep)
                then e1.typerep
                else e2.typerep;
}

concrete production neg
top::Expr ::= '-' e::Expr
precedence = 13
{
  top.unparse = "- " ++ e.unparse;

  top.errors := e.errors;

  top.errors <-
       if isMonad(e.typerep)
       then if performSubstitution(monadInnerType(e.typerep), top.finalSubst).instanceNum
            then [err(e.location, "Operand to unary - must be concrete types Integer or Float or a monad of these.  Got instead type " ++ prettyType(performSubstitution(monadInnerType(e.typerep), top.finalSubst)))]
            else []
       else if performSubstitution(e.typerep, top.finalSubst).instanceNum
            then [err(e.location, "Operand to unary - must be concrete types Integer or Float or a monad of these.  Got instead type " ++ prettyType(performSubstitution(e.typerep, top.finalSubst)))]
            else [];

  top.typerep = e.typerep;
}

concrete production stringConst
top::Expr ::= s::String_t
{
  top.unparse = s.lexeme;

  top.errors := [];
  top.typerep = stringType();
}

concrete production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
  top.unparse = e1.unparse ++ " ++ " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors ++ forward.errors;
  top.typerep = if errCheck1.typeerror then errorType() else result_type;

  local result_type :: Type = performSubstitution(e1.typerep, errCheck1.upSubst);

  -- Moved from 'analysis:typechecking' because we want to use this stuff here now
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;

  e1.downSubst = top.downSubst;
  e2.downSubst = e1.upSubst;
  errCheck1.downSubst = e2.upSubst;
  forward.downSubst = errCheck1.upSubst;
  -- upSubst defined via forward :D

  errCheck1 = check(e1.typerep, e2.typerep);

  forwards to
    -- if the types disagree, forward to an error production instead.
    if errCheck1.typeerror
    then errorExpr([err(top.location, "Operands to ++ must be the same concatenable type. Instead they are " ++ errCheck1.leftpp ++ " and " ++ errCheck1.rightpp)], location=top.location)
    else top.typerep.appendDispatcher(e1, e2, top.location);
}

abstract production stringPlusPlus
top::Expr ::= e1::Decorated Expr   e2::Decorated Expr
{
  top.unparse = e1.unparse ++ " ++ " ++ e2.unparse;

  top.errors := [];
  top.typerep = stringType();
}

abstract production errorPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  top.unparse = e1.unparse ++ " ++ " ++ e2.unparse;

  local result_type :: Type = performSubstitution(e1.typerep, top.downSubst);

  top.errors :=
    if result_type.isError then []
    else [err(e1.location, prettyType(result_type) ++ " is not a concatenable type.")];
  top.typerep = errorType();
}

-- These sorta seem obsolete, but there are some important differences from AppExprs.
-- For one, AppExprs expects a fixed, imposed list of types. Here we're flexible!
-- This is used by both pattern matching and list literals.
abstract production exprsEmpty
top::Exprs ::=
{
  top.unparse = "";
  
  top.errors := [];
  top.exprs = [];
  top.rawExprs = [];
}
concrete production exprsSingle
top::Exprs ::= e::Expr
{
  top.unparse = e.unparse;

  top.errors := e.errors;
  top.exprs = [e];
  top.rawExprs = [e];
}
concrete production exprsCons
top::Exprs ::= e1::Expr ',' e2::Exprs
{
  top.unparse = e1.unparse ++ ", " ++ e2.unparse;

  top.errors := e1.errors ++ e2.errors;
  top.exprs = [e1] ++ e2.exprs;
  top.rawExprs = [e1] ++ e2.rawExprs;
}


{--
 - Exprs with optional underscores omitting parameters. Used exclusively for
 - (partial) function application.
 -}
nonterminal AppExprs with 
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, exprs, rawExprs,
  isPartial, missingTypereps, appExprIndicies, appExprSize, appExprTypereps, appExprApplied;

nonterminal AppExpr with
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, exprs, rawExprs,
  isPartial, missingTypereps, appExprIndicies, appExprIndex, appExprTyperep, appExprApplied;

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
  
  top.rawExprs = [];
  top.exprs = [];
  top.appExprIndicies = [];
  
  top.errors := [];
}
concrete production presentAppExpr
top::AppExpr ::= e::Expr
{
  top.unparse = e.unparse;
  
  top.isPartial = false;
  top.missingTypereps = [];
  
  top.rawExprs = [e];
  top.exprs = [e];
  top.appExprIndicies = [top.appExprIndex];
  
  top.errors := e.errors;
}

concrete production snocAppExprs
top::AppExprs ::= es::AppExprs ',' e::AppExpr
{
  top.unparse = es.unparse ++ ", " ++ e.unparse;

  top.isPartial = es.isPartial || e.isPartial;
  top.missingTypereps = es.missingTypereps ++ e.missingTypereps;

  top.rawExprs = es.rawExprs ++ e.rawExprs;
  top.exprs = es.exprs ++ e.exprs;
  top.appExprIndicies = es.appExprIndicies ++ e.appExprIndicies;

  top.errors := es.errors ++ e.errors;
  top.appExprSize = es.appExprSize + 1;

  e.appExprIndex = es.appExprSize;
  e.appExprTyperep = if null(top.appExprTypereps)
                     then errorType()
                     else head(top.appExprTypereps);

  es.appExprTypereps = if null(top.appExprTypereps) then [] else tail(top.appExprTypereps);
}
concrete production oneAppExprs
top::AppExprs ::= e::AppExpr
{
  top.unparse = e.unparse;

  top.isPartial = e.isPartial;
  top.missingTypereps = e.missingTypereps;

  top.rawExprs = e.rawExprs;
  top.exprs = e.exprs;
  top.appExprIndicies = e.appExprIndicies;
  
  top.errors := if null(top.appExprTypereps)
                then [err(top.location, "Too many arguments provided to function '" ++ top.appExprApplied ++ "'")]
                else if length(top.appExprTypereps) > 1
                then [err(top.location, "Too few arguments provided to function '" ++ top.appExprApplied ++ "'")]
                else [];
  top.errors <- e.errors;
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

  top.rawExprs = [];
  top.exprs = [];
  top.appExprIndicies = [];
  top.appExprSize = 0;

  -- Assumption: We only get here when we're looking at ()
  -- i.e. we can't ever have 'too many' provided error
  top.errors := if null(top.appExprTypereps) then []
                else [err(top.location, "Too few arguments provided to function '" ++ top.appExprApplied ++ "'")];
}


nonterminal AnnoAppExprs with
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars,
  isPartial, appExprApplied, exprs,
  remainingFuncAnnotations, funcAnnotations,
  missingAnnotations, partialAnnoTypereps, annoIndexConverted, annoIndexSupplied;
nonterminal AnnoExpr with
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars,
  isPartial, appExprApplied, exprs,
  remainingFuncAnnotations, funcAnnotations,
  missingAnnotations, partialAnnoTypereps, annoIndexConverted, annoIndexSupplied;

{--
 - Annotations that have not yet been supplied
 -}
inherited attribute remainingFuncAnnotations :: [NamedArgType];
{--
 - All annotations of this function
 -}
autocopy attribute funcAnnotations :: [NamedArgType];
{--
 - Annotations that have not been supplied (by subtracting from remainingFuncAnnotations)
 -}
synthesized attribute missingAnnotations :: [NamedArgType];
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
  
  local fq :: Pair<Maybe<NamedArgType> [NamedArgType]> =
    extractNamedArg(qn.name, top.remainingFuncAnnotations);
    
  e.appExprIndex =
    findNamedArgType(qn.name, top.funcAnnotations, 0);
  e.appExprTyperep =
    if fq.fst.isJust then fq.fst.fromJust.argType else errorType();
    
  top.missingAnnotations = fq.snd; -- minus qn, if it was there
  top.partialAnnoTypereps = e.missingTypereps;
  
  top.errors :=
    (if fq.fst.isJust then []
     else [err(qn.location, "Named parameter '" ++ qn.name ++ "' is not appropriate for '" ++ top.appExprApplied ++ "'")]) ++
    e.errors;
  top.isPartial = e.isPartial;
  top.exprs = e.exprs;
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
  top.errors := es.errors ++ e.errors;

  e.remainingFuncAnnotations = top.remainingFuncAnnotations;
  es.remainingFuncAnnotations = e.missingAnnotations;
  top.missingAnnotations = es.missingAnnotations;
  
  top.partialAnnoTypereps = es.partialAnnoTypereps ++ e.partialAnnoTypereps;
  top.annoIndexConverted = es.annoIndexConverted ++ e.annoIndexConverted;
  top.annoIndexSupplied = es.annoIndexSupplied ++ e.annoIndexSupplied;
  top.exprs = es.exprs ++ e.exprs;
}

concrete production oneAnnoAppExprs
top::AnnoAppExprs ::= e::AnnoExpr
{
  top.unparse = e.unparse;

  top.isPartial = e.isPartial;
  top.errors :=
    if null(top.missingAnnotations) then []
    else [err(top.location, "Missing named parameters for function '" ++ top.appExprApplied ++ "': "
      ++ implode(", ", map((.argName), top.missingAnnotations)))];

  top.errors <- e.errors;

  e.remainingFuncAnnotations = top.remainingFuncAnnotations;
  top.missingAnnotations = e.missingAnnotations;

  top.partialAnnoTypereps = e.partialAnnoTypereps;
  top.annoIndexConverted = e.annoIndexConverted;
  top.annoIndexSupplied = e.annoIndexSupplied;
  top.exprs = e.exprs;
}

abstract production emptyAnnoAppExprs
top::AnnoAppExprs ::=
{
  top.unparse = "";

  top.isPartial = false;
  top.errors :=
    if null(top.missingAnnotations) then []
    else [err(top.location, "Missing named parameters for function '" ++ top.appExprApplied ++ "': "
      ++ implode(", ", map((.argName), top.missingAnnotations)))];

  top.missingAnnotations = top.remainingFuncAnnotations;
  
  top.partialAnnoTypereps = [];
  top.annoIndexConverted = [];
  top.annoIndexSupplied = [];
  top.exprs = [];
}

function reorderedAnnoAppExprs
[Decorated Expr] ::= d::Decorated AnnoAppExprs
{
  -- This is an annoyingly poor quality implementation
  return map(reorderedGetSnd, sortBy(reorderedLte, zipWith(pair, d.annoIndexSupplied, d.exprs)));
}
function reorderedGetSnd
b ::= p::Pair<a b> { return p.snd; }
function reorderedLte
Boolean ::= l::Pair<Integer Decorated Expr>  r::Pair<Integer Decorated Expr> { return l.fst <= r.fst; }






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

  -- See the major restriction. This should have been checked for error already!
  top.errors := [];
  top.typerep = e.typerep;
  
  -- TODO: one of the little things we might want is to make this transparent to
  -- forwarding. e.g. e might be a 'childReference' and pattern matching would
  -- need to separately account for this!
  -- To accomplish this, we might want some notion of a decorated forward.
}

