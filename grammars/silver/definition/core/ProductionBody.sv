grammar silver:definition:core;

nonterminal ProductionBody with
  config, grammarName, env, location, unparse, errors, defs, frame, compiledGrammars,
  productionAttributes, uniqueSignificantExpression;
nonterminal ProductionStmts with 
  config, grammarName, env, location, unparse, errors, defs, frame, compiledGrammars,
  productionAttributes, uniqueSignificantExpression;
nonterminal ProductionStmt with
  config, grammarName, env, location, unparse, errors, defs, frame, compiledGrammars,
  productionAttributes, uniqueSignificantExpression;

flowtype decorate {frame, grammarName, compiledGrammars, config, env, flowEnv, downSubst}
  on ProductionBody, ProductionStmts;
flowtype decorate {frame, grammarName, compiledGrammars, config, env, flowEnv, downSubst, finalSubst}
  on ProductionStmt;

nonterminal DefLHS with 
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars, name, typerep, defLHSattr, found;

nonterminal ForwardInhs with 
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars;
nonterminal ForwardInh with 
  config, grammarName, env, location, unparse, errors, frame, compiledGrammars;
nonterminal ForwardLHSExpr with 
  config, grammarName, env, location, unparse, errors, frame, name, typerep;

{--
 - Context for ProductionStmt blocks. (Indicates function, production, aspect, etc)
 - Includes singature for those contexts with a signature.
 -}
autocopy attribute frame :: BlockContext;

{--
 - Defs of attributes that should be wrapped up as production attributes.
 -}
monoid attribute productionAttributes :: [Def] with [], ++;
{--
 - Either the 'forward' expression, or the 'return' expression.
 - I gave it an obtuse name so it could easily be renamed in the future.
 -}
monoid attribute uniqueSignificantExpression :: [Decorated Expr] with [], ++;

{--
 - The attribute we're defining on a DefLHS.
 -}
inherited attribute defLHSattr :: Decorated QNameAttrOccur;

propagate errors on ProductionBody, ProductionStmts, ProductionStmt, DefLHS, ForwardInhs, ForwardInh, ForwardLHSExpr;
propagate defs, productionAttributes, uniqueSignificantExpression on ProductionBody, ProductionStmts;

concrete production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  top.unparse = stmts.unparse;
}

concrete production productionStmtsNil
top::ProductionStmts ::= 
{
  top.unparse = "";
}

concrete production productionStmtsSnoc
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmt
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;
}

----------

abstract production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  top.unparse = h.unparse ++ "\n" ++ t.unparse;
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
}

concrete production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.unparse = "\treturn " ++ e.unparse ++ ";";
  
  top.uniqueSignificantExpression := [e];
  
  top.errors <- if !top.frame.permitReturn
                then [err(top.location, "Return is not valid in this context. (They are only permitted in function declarations.)")]
                else [];
}

concrete production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::TypeExpr ';'
{
  top.unparse = "\tlocal attribute " ++ a.unparse ++ "::" ++ te.unparse ++ ";";

  production attribute fName :: String;
  fName = top.frame.fullName ++ ":local:" ++ a.name;

  top.defs := [localDef(top.grammarName, a.location, fName, te.typerep)];

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1 
        then [err(a.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors <- if !top.frame.permitLocalAttributes
                then [err(top.location, "Local attributes are not valid in this context.")]
                else [];
}

concrete production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::TypeExpr ';'
{
  -- TODO: we should unparse the production keyword, not local here!!
  --top.unparse = "\tproduction attribute " ++ a.unparse ++ "::" ++ te.unparse ++ ";";

  top.productionAttributes := forward.defs;
  top.defs := [];

  top.errors <- if !top.frame.permitProductionAttributes
                then [err(top.location, "Production attributes are not valid in this context.")]
                else [];

  forwards to localAttributeDcl('local', $2, a, $4, te, $6, location=top.location);
}

concrete production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  top.unparse = "\tforwards to " ++ e.unparse;

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

  production attribute fwdDcls :: [DclInfo];
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
    if !dl.found || !attr.found || !null(problems)
    then errorAttributeDef(problems, dl, attr, e, location=top.location)
    else attr.attrDcl.attrDefDispatcher(dl, attr, e, top.location);
}

{- This is a helper that exist primarily to decorate 'e' and add its error messages to the list.
   Invariant: msg should not be null! -}
abstract production errorAttributeDef
top::ProductionStmt ::= msg::[Message] dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";

  forwards to errorProductionStmt(msg ++ e.errors, location=top.location);
}

abstract production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";
}

abstract production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS  attr::Decorated QNameAttrOccur  e::Expr
{
  top.unparse = "\t" ++ dl.unparse ++ "." ++ attr.unparse ++ " = " ++ e.unparse ++ ";";
}

concrete production concreteDefLHS
top::DefLHS ::= q::QName
{
  top.name = q.name;
  top.unparse = q.unparse;

  top.errors := q.lookupValue.errors ++ forward.errors;
  
  forwards to if null(q.lookupValue.dcls)
              then errorDefLHS(q, location=top.location)
              else q.lookupValue.dcl.defLHSDispatcher(q, top.location);
}

abstract production errorDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  top.found = false;
  
  top.errors <-
    if top.typerep.isError then [] else [err(q.location, "Cannot define attributes on " ++ q.name)];
  top.typerep = q.lookupValue.typerep;
}

concrete production concreteDefLHSfwd
top::DefLHS ::= q::'forward'
{
  forwards to concreteDefLHS(qName(q.location, "forward"), location=top.location);
}

abstract production childDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  top.found = !existingProblems && top.defLHSattr.attrDcl.isInherited;
  
  local existingProblems :: Boolean = !top.defLHSattr.found || top.typerep.isError;
  
  top.errors <-
    if existingProblems || top.found then []
    else [err(q.location, "Cannot define synthesized attribute '" ++ top.defLHSattr.name ++ "' on child '" ++ q.name ++ "'")];
                
  top.typerep = q.lookupValue.typerep;
}

abstract production lhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  top.found = !existingProblems && top.defLHSattr.attrDcl.isSynthesized;
  
  local existingProblems :: Boolean = !top.defLHSattr.found || top.typerep.isError;
  
  top.errors <-
    if existingProblems || top.found then []
    else [err(q.location, "Cannot define inherited attribute '" ++ top.defLHSattr.name ++ "' on the lhs '" ++ q.name ++ "'")];

  top.typerep = q.lookupValue.typerep;
}

abstract production localDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  top.found = !existingProblems && top.defLHSattr.attrDcl.isInherited;
  
  local existingProblems :: Boolean = !top.defLHSattr.found || top.typerep.isError;
  
  top.errors <-
    if existingProblems || top.found then []
    else [err(q.location, "Cannot define synthesized attribute '" ++ top.defLHSattr.name ++ "' on local '" ++ q.name ++ "'")];

  top.typerep = q.lookupValue.typerep;
}

abstract production forwardDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.name = q.name;
  top.unparse = q.unparse;
  top.found = !existingProblems && top.defLHSattr.attrDcl.isInherited;
  
  local existingProblems :: Boolean = !top.defLHSattr.found || top.typerep.isError;
  
  top.errors <-
    if existingProblems || top.found then []
    else [err(q.location, "Cannot define synthesized attribute '" ++ top.defLHSattr.name ++ "' on forward")];

  top.typerep = q.lookupValue.typerep;
}

----- done with DefLHS

concrete production valueEq
top::ProductionStmt ::= val::QName '=' e::Expr ';'
{
  top.unparse = "\t" ++ val.unparse ++ " = " ++ e.unparse ++ ";";

  top.errors <- val.lookupValue.errors;

  -- defs must stay here explicitly, because we dispatch on types in the forward here!
  top.productionAttributes := [];
  top.defs := [];
  
  forwards to if null(val.lookupValue.dcls)
              then errorValueDef(val, e, location=top.location)
              else val.lookupValue.dcl.defDispatcher(val, e, top.location);
}

abstract production errorValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.unparse = "\t" ++ val.unparse ++ " = " ++ e.unparse ++ ";";
  
  top.errors <-
    if val.lookupValue.typerep.isError then []
    else [err(val.location, val.name ++ " cannot be assigned to.")];
}

abstract production localValueDef
top::ProductionStmt ::= val::Decorated QName  e::Expr
{
  top.unparse = "\t" ++ val.unparse ++ " = " ++ e.unparse ++ ";";

  -- val is already valid here

  -- TODO: missing redefinition check
}

