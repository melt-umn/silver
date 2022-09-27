grammar silver:compiler:definition:core;

nonterminal ProductionBody with
  config, grammarName, env, location, unparse, errors, defs, frame, compiledGrammars,
  productionAttributes, uniqueSignificantExpression;
nonterminal ProductionStmts with 
  config, grammarName, env, location, unparse, errors, defs, frame, compiledGrammars,
  productionAttributes, uniqueSignificantExpression, originRules;
nonterminal ProductionStmt with
  config, grammarName, env, location, unparse, errors, defs, frame, compiledGrammars,
  productionAttributes, uniqueSignificantExpression, originRules;

flowtype decorate {frame, grammarName, compiledGrammars, config, env, flowEnv, downSubst}
  on ProductionBody;
flowtype decorate {frame, grammarName, compiledGrammars, config, env, flowEnv, downSubst, originRules}
  on ProductionStmts;
flowtype decorate {frame, grammarName, compiledGrammars, config, env, flowEnv, downSubst, finalSubst, originRules}
  on ProductionStmt;
flowtype forward {decorate} on ProductionBody, ProductionStmts, ProductionStmt;

nonterminal DefLHS with 
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, name, typerep, defLHSattr, found, originRules;

flowtype decorate {frame, grammarName, compiledGrammars, config, env, flowEnv, defLHSattr, originRules}
  on DefLHS;

nonterminal ForwardInhs with 
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, originRules;
nonterminal ForwardInh with 
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, originRules;
nonterminal ForwardLHSExpr with 
  config, grammarName, env, location, unparse, errors, frame, name, typerep, originRules;

{--
 - Context for ProductionStmt blocks. (Indicates function, production, aspect, etc)
 - Includes singature for those contexts with a signature.
 -}
inherited attribute frame :: BlockContext;

{--
 - Defs of attributes that should be wrapped up as production attributes.
 -}
monoid attribute productionAttributes :: [Def];
{--
 - Either the 'forward' expression, or the 'return' expression.
 - I gave it an obtuse name so it could easily be renamed in the future.
 -}
monoid attribute uniqueSignificantExpression :: [Decorated Expr];

{--
 - The attribute we're defining on a DefLHS.
 -}
inherited attribute defLHSattr :: Decorated QNameAttrOccur;

-- Notes that should be attached to stuff constructed/modified in rules in this productionBody
-- Notes flow 'up' in this from statements and then back 'down' into the via originRules.
synthesized attribute originRuleDefs :: [Decorated Expr] occurs on ProductionStmt, ProductionStmts;

propagate config, grammarName, env, errors, frame, compiledGrammars on
  ProductionBody, ProductionStmts, ProductionStmt, DefLHS, ForwardInhs, ForwardInh, ForwardLHSExpr;
propagate defs, productionAttributes, uniqueSignificantExpression on ProductionBody, ProductionStmts;
propagate originRules on ProductionStmts, ProductionStmt, DefLHS, ForwardInhs, ForwardInh, ForwardLHSExpr
  excluding attachNoteStmt;


concrete production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  top.unparse = stmts.unparse;

  stmts.originRules = stmts.originRuleDefs;
}

concrete production productionStmtsNil
top::ProductionStmts ::= 
{
  top.unparse = "";

  top.originRuleDefs = [];
}

concrete production productionStmtsSnoc
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmt
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;

  top.originRuleDefs = h.originRuleDefs ++ t.originRuleDefs;
}

----------

abstract production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;

  top.originRuleDefs = h.originRuleDefs ++ t.originRuleDefs;
  propagate defs, productionAttributes, uniqueSignificantExpression;
}

abstract production errorProductionStmt
top::ProductionStmt ::= e::[Message]
{
  top.unparse = s"{- Errors:\n${messagesToString(e)} -}";
  top.errors <- e;
}

--------------------------------------------------------------------------------

aspect default production
top::ProductionStmt ::=
{
  -- as is usual for defaults ("base classes")
  -- can't provide unparse or location, errors should NOT be defined!
  top.productionAttributes := [];
  top.uniqueSignificantExpression := [];
  
  top.defs := [];

  top.originRuleDefs = [];
}

concrete production attachNoteStmt
top::ProductionStmt ::= 'attachNote' note::Expr ';'
{
  top.unparse = "attachNote " ++ note.unparse;
  note.isRoot = false; -- Irrelevant because OriginNotes aren't tracked
  note.originRules = []; --Prevents cyclical dependency when translating
  top.originRuleDefs = [note];
}

concrete production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.unparse = "\treturn " ++ e.unparse ++ ";";
  
  top.uniqueSignificantExpression := [e];
  
  top.errors <- if !top.frame.permitReturn
                then [err(top.location, "Return is not valid in this context. (They are only permitted in function declarations.)")]
                else [];

  e.isRoot = true;
}

concrete production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::TypeExpr ';'
{
  top.unparse = "\tlocal attribute " ++ a.unparse ++ "::" ++ te.unparse ++ ";";

  production attribute fName :: String;
  fName = s"${top.frame.fullName}:local:${top.grammarName}:${implode("_", filter(isAlpha, explode(".", top.location.filename)))}:${toString(top.location.line)}:${toString(top.location.column)}:${a.name}";

  top.defs := [localDef(top.grammarName, a.location, fName, te.typerep)];

  top.errors <-
        if length(getValueDclInScope(a.name, top.env)) > 1 
        then [err(a.location, "Value '" ++ a.name ++ "' is already bound.")]
        else [];

  top.errors <- if !top.frame.permitLocalAttributes
                then [err(top.location, "Local attributes are not valid in this context.")]
                else [];
}

concrete production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::TypeExpr ';'
{
  top.unparse = "\tproduction attribute " ++ a.unparse ++ "::" ++ te.unparse ++ ";";

  production attribute fName :: String;
  fName = top.frame.fullName ++ ":local:" ++ top.grammarName ++ ":" ++ a.name;

  top.productionAttributes := [localDef(top.grammarName, a.location, fName, te.typerep)];

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1 
        then [err(a.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors <- if !top.frame.permitProductionAttributes
                then [err(top.location, "Production attributes are not valid in this context.")]
                else [];
}

concrete production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  top.unparse = "\tforwards to " ++ e.unparse;

  e.isRoot = true;

  top.productionAttributes := [forwardDef(top.grammarName, top.location, top.frame.signature.outputElement.typerep)];
  top.uniqueSignificantExpression := [e];

  top.errors <- if !top.frame.permitForward
                then [err(top.location, "Forwarding is not permitted in this context. (Only permitted in non-aspect productions.)")]
                else [];
}

concrete production forwardsToWith
top::ProductionStmt ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{
  top.unparse = "\tforwards to " ++ e.unparse ++ " with {" ++ inh.unparse ++ "};";

  forwards to productionStmtAppend(
    forwardsTo($1, $2, $3, $8, location=top.location),
    forwardingWith('forwarding', $4, $5, inh, $7, $8, location=top.location),
    location=top.location);
}

concrete production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.unparse = "\tforwarding with {" ++ inh.unparse ++ "};";

  production attribute fwdDcls :: [ValueDclInfo];
  fwdDcls = getValueDcl("forward", top.env);
  
  top.errors <- if null(fwdDcls)
                then [err(top.location, "'forwarding with' clause for a production that does not forward!")]
                else [];
}

-- TODO eliminate these (/ combine with the ones for decorate expression)
concrete production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  top.unparse = lhs.unparse ++ " = " ++ e.unparse ++ ";";

  e.isRoot = true;
}

concrete production forwardInhsOne
top::ForwardInhs ::= lhs::ForwardInh
{
  top.unparse = lhs.unparse;
}

concrete production forwardInhsCons
top::ForwardInhs ::= lhs::ForwardInh rhs::ForwardInhs
{
  top.unparse = lhs.unparse ++ " " ++ rhs.unparse;
}

concrete production forwardLhsExpr
top::ForwardLHSExpr ::= q::QNameAttrOccur
{
  top.name = q.name;
  top.unparse = q.unparse;

  top.typerep = q.typerep;
  
  q.attrFor = top.frame.signature.outputElement.typerep;
}

concrete production attributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";
  propagate grammarName, config, env, frame, compiledGrammars, originRules;

  -- defs must stay here explicitly, because we dispatch on types in the forward here!
  top.productionAttributes := [];
  top.defs := [];
  
  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;
  
  local problems :: [Message] =
    if attr.found && attr.attrDcl.isAnnotation
    then [err(attr.location, attr.name ++ " is an annotation, which are supplied to productions as arguments, not defined as equations.")]
    else dl.errors ++ attr.errors;

  forwards to
    -- oddly enough we may have no errors and need to forward to error production:
    -- consider "production foo  top::DoesNotExist ::= { top.errors = ...; }"
    -- where top is a valid reference to a type that is an error type
    -- so there is an error elsewhere
    (if !dl.found || !attr.found || !null(problems)
     then errorAttributeDef(problems, _,  _, _, location=_)
     else attr.attrDcl.attrDefDispatcher)(dl, attr, e, top.location);
}

{- This is a helper that exist primarily to decorate 'e' and add its error messages to the list.
   Invariant: msg should not be null! -}
abstract production errorAttributeDef
top::ProductionStmt ::= msg::[Message] dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";
  propagate grammarName, config, env, frame, compiledGrammars, originRules;
  e.isRoot = true;

  forwards to errorProductionStmt(msg ++ e.errors, location=top.location);
}

abstract production synthesizedAttributeDef
top::ProductionStmt ::= dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";

  e.isRoot = true;
}

abstract production inheritedAttributeDef
top::ProductionStmt ::= dl::PartiallyDecorated DefLHS  attr::PartiallyDecorated QNameAttrOccur  e::Expr
{
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";

  e.isRoot = true;
}

concrete production concreteDefLHS
top::DefLHS ::= q::QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  propagate env;
  
  forwards to (if null(q.lookupValue.dcls)
               then errorDefLHS(_, location=_)
               else q.lookupValue.dcl.defLHSDispatcher)(q, top.location);
} action {
  if (contains(q.name, sigNames)) {
    insert semantic token IdSigName_t at q.baseNameLoc;
  }
}

abstract production errorDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  top.found = false;
  
  top.errors <- q.lookupValue.errors;
  top.errors <-
    if top.typerep.isError then [] else [err(q.location, "Cannot define attributes on " ++ q.name)];
  top.typerep = q.lookupValue.typeScheme.monoType;
}

concrete production concreteDefLHSfwd
top::DefLHS ::= q::'forward'
{
  forwards to concreteDefLHS(qName(q.location, "forward"), location=top.location);
}

abstract production childDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  top.found = !existingProblems && top.defLHSattr.attrDcl.isInherited;
  
  local existingProblems :: Boolean = !top.defLHSattr.found || top.typerep.isError;
  
  top.errors <-
    if existingProblems || top.found then []
    else [err(q.location, "Cannot define synthesized attribute '" ++ top.defLHSattr.name ++ "' on child '" ++ q.name ++ "'")];
                
  top.typerep = q.lookupValue.typeScheme.monoType;
}

abstract production lhsDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  top.found = !existingProblems && top.defLHSattr.attrDcl.isSynthesized;
  
  local existingProblems :: Boolean = !top.defLHSattr.found || top.typerep.isError;
  
  top.errors <-
    if existingProblems || top.found then []
    else [err(q.location, "Cannot define inherited attribute '" ++ top.defLHSattr.name ++ "' on the lhs '" ++ q.name ++ "'")];

  top.typerep = q.lookupValue.typeScheme.monoType;
}

abstract production localDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  top.found = !existingProblems && top.defLHSattr.attrDcl.isInherited;
  
  local existingProblems :: Boolean = !top.defLHSattr.found || top.typerep.isError;
  
  top.errors <-
    if existingProblems || top.found then []
    else [err(q.location, "Cannot define synthesized attribute '" ++ top.defLHSattr.name ++ "' on local '" ++ q.name ++ "'")];

  top.typerep = q.lookupValue.typeScheme.monoType;
}

abstract production forwardDefLHS
top::DefLHS ::= q::PartiallyDecorated QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  top.found = !existingProblems && top.defLHSattr.attrDcl.isInherited;
  
  local existingProblems :: Boolean = !top.defLHSattr.found || top.typerep.isError;
  
  top.errors <-
    if existingProblems || top.found then []
    else [err(q.location, "Cannot define synthesized attribute '" ++ top.defLHSattr.name ++ "' on forward")];

  top.typerep = q.lookupValue.typeScheme.monoType;
}

----- done with DefLHS

concrete production valueEq
top::ProductionStmt ::= val::QName '=' e::Expr ';'
{
  top.unparse = "\t" ++ val.unparse ++ " = " ++ e.unparse ++ ";";
  propagate env;

  top.errors <- val.lookupValue.errors;

  -- defs must stay here explicitly, because we dispatch on types in the forward here!
  top.productionAttributes := [];
  top.defs := [];
  
  forwards to (if null(val.lookupValue.dcls)
               then errorValueDef(_, _, location=_)
               else val.lookupValue.dcl.defDispatcher)(val, e, top.location);
}

abstract production errorValueDef
top::ProductionStmt ::= val::PartiallyDecorated QName  e::Expr
{
  top.unparse = "\t" ++ val.unparse ++ " = " ++ e.unparse ++ ";";

  e.isRoot = true;

  top.errors <-
    if val.lookupValue.typeScheme.isError then []
    else [err(val.location, val.name ++ " cannot be assigned to.")];
}

abstract production localValueDef
top::ProductionStmt ::= val::PartiallyDecorated QName  e::Expr
{
  top.unparse = "\t" ++ val.unparse ++ " = " ++ e.unparse ++ ";";

  -- val is already valid here

  e.isRoot = true;

  -- TODO: missing redefinition check
}

