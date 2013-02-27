grammar silver:definition:core;

nonterminal ProductionBody with
  config, grammarName, file, env, location, pp, errors, defs, blockContext, compiledGrammars,
  productionAttributes, signature, uniqueSignificantExpression;
nonterminal ProductionStmts with 
  config, grammarName, file, env, location, pp, errors, defs, blockContext, compiledGrammars,
  productionAttributes, signature, uniqueSignificantExpression;
nonterminal ProductionStmt with
  config, grammarName, file, env, location, pp, errors, defs, blockContext, compiledGrammars,
  productionAttributes, signature, uniqueSignificantExpression;

nonterminal DefLHS with 
  config, grammarName, file, env, location, pp, errors, blockContext, compiledGrammars, signature, typerep, defLHSattr;

nonterminal ForwardInhs with 
  config, grammarName, file, env, location, pp, errors, blockContext, compiledGrammars, signature;
nonterminal ForwardInh with 
  config, grammarName, file, env, location, pp, errors, blockContext, compiledGrammars, signature;
nonterminal ForwardLHSExpr with 
  config, grammarName, file, env, location, pp, errors, signature, typerep;

{--
 - The signature of this fun/production, given to the production's body.
 - This is mostly for the use of ProductionStmts, almost exclusively.
 - It DOES however, validly occur on Expr. But, on Expr, all uses should be
 - guarded. (e.g. nearly all uses are in productions that can only be
 - forwarded to by a dispatcher using something in the environment
 - provided by the production. Concretely: child reference dcls are only
 - available when a production added them to the env for its children.)
 - It's decorated because why not? We're always looking at a physical signature
 - anyway, so let's just reuse it, to avoid any recomputation.
 -}
autocopy attribute signature :: NamedSignature;
{--
 - Context for ProductionStmt blocks. (Function, production, other...)
 - At some future time, we should consider the possibility of eliminating
 - signature, and just using this. Or not.
 -}
autocopy attribute blockContext :: BlockContext;

{--
 - Defs of attributes that should be wrapped up as production attributes.
 -}
synthesized attribute productionAttributes :: [Def];
{--
 - Either the 'forward' expression, or the 'return' expression.
 - I gave it an obtuse name so it could easily be renamed in the future.
 -}
synthesized attribute uniqueSignificantExpression :: [Decorated Expr];

{--
 - The attribute we're defining on a DefLHS.
 -}
inherited attribute defLHSattr :: Decorated QNameAttrOccur;


concrete production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  top.pp = stmts.pp;
  top.location = stmts.location;

  top.productionAttributes = stmts.productionAttributes;
  top.uniqueSignificantExpression = stmts.uniqueSignificantExpression;

  top.defs = stmts.defs;
  top.errors := stmts.errors;
}

concrete production productionStmtsNil
top::ProductionStmts ::= 
{
  top.pp = "";
  top.location = bogusLocation();

  top.productionAttributes = [];
  top.uniqueSignificantExpression = [];

  top.defs = [];
  top.errors := [];
}

concrete production productionStmtsSnoc
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmt
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;
  
  top.productionAttributes = h.productionAttributes ++ t.productionAttributes;
  top.uniqueSignificantExpression = h.uniqueSignificantExpression ++ t.uniqueSignificantExpression;

  top.defs = h.defs ++ t.defs;
  top.errors := h.errors ++ t.errors;
}

----------

abstract production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;

  top.productionAttributes = h.productionAttributes ++ t.productionAttributes;
  top.uniqueSignificantExpression = h.uniqueSignificantExpression ++ t.uniqueSignificantExpression;

  top.defs = h.defs ++ t.defs;
  top.errors := h.errors ++ t.errors;
}

--------------------------------------------------------------------------------

aspect default production
top::ProductionStmt ::=
{
  -- as is usual for defaults ("base classes")
  -- can't provide pp or location, errors should NOT be defined!
  top.productionAttributes = [];
  top.uniqueSignificantExpression = [];
  
  top.defs = [];
}

concrete production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.pp = "\treturn " ++ e.pp ++ ";";
  top.location = $1.location;
  
  top.uniqueSignificantExpression = [e];

  top.errors := e.errors;
  
  top.errors <- if !top.blockContext.permitReturn
                then [err(top.location, "Return is not valid in this context. (They are only permitted in function declarations.)")]
                else [];
}

concrete production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  top.pp = "\tlocal attribute " ++ a.pp ++ "::" ++ te.pp ++ ";";
  top.location = $1.location;

  production attribute fName :: String;
  fName = top.signature.fullName ++ ":local:" ++ a.name;

  top.defs = [localDef(top.grammarName, a.location, fName, te.typerep)];

  top.errors := te.errors;

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1 
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  top.errors <- if !top.blockContext.permitLocalAttributes
                then [err(top.location, "Local attributes are not valid in this context.")]
                else [];
}

concrete production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type ';'
{
  -- TODO: we should pp the production keyword, not local here!!
  --top.pp = "\tproduction attribute " ++ a.pp ++ "::" ++ te.pp ++ ";";

  top.productionAttributes = forward.defs;
  top.defs = [];

  top.errors <- if !top.blockContext.permitProductionAttributes
                then [err(top.location, "Production attributes are not valid in this context.")]
                else [];

  forwards to localAttributeDcl(terminal(Local_kwd, "local", $1.location), $2, a, $4, te, $6);
}

concrete production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  top.pp = "\tforwards to " ++ e.pp;
  top.location = $1.location;

  top.productionAttributes = [forwardDef(top.grammarName, top.location, top.signature.outputElement.typerep)];
  top.uniqueSignificantExpression = [e];

  top.errors := e.errors;

  top.errors <- if !top.blockContext.permitForward
                then [err(top.location, "Forwarding is not permitted in this context. (Only permitted in non-aspect productions.)")]
                else [];
}

concrete production forwardsToWith
top::ProductionStmt ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{
  top.pp = "\tforwards to " ++ e.pp ++ " with {" ++ inh.pp ++ "};";
  top.location = $1.location;

  forwards to productionStmtAppend(
    forwardsTo($1, $2, $3, $8),
    forwardingWith(terminal(Forwarding_kwd, "forwarding", $1.location), $4, $5, inh, $7, $8));
}

concrete production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.pp = "\tforwarding with {" ++ inh.pp ++ "};";
  top.location = $1.location;

  production attribute fwdDcls :: [DclInfo];
  fwdDcls = getValueDcl("forward", top.env);

  top.errors := inh.errors;
  
  top.errors <- if null(fwdDcls)
                then [err(top.location, "'forwarding with' clause for a production that does not forward!")]
                else [];
}

-- TODO eliminate these (/ combine with the ones for decorate expression)
concrete production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  top.pp = lhs.pp ++ " = " ++ e.pp ++ ";";
  top.location = $2.location;

  top.errors := lhs.errors ++ e.errors;
}

concrete production forwardInhsOne
top::ForwardInhs ::= lhs::ForwardInh
{
  top.pp = lhs.pp;
  top.location = lhs.location;
  top.errors := lhs.errors;
}

concrete production forwardInhsCons
top::ForwardInhs ::= lhs::ForwardInh rhs::ForwardInhs
{
  top.pp = lhs.pp ++ " " ++ rhs.pp;
  top.location = lhs.location;
  top.errors := lhs.errors ++ rhs.errors;
}

concrete production forwardLhsExpr
top::ForwardLHSExpr ::= q::QNameAttrOccur
{
  top.pp = q.pp;
  top.location = q.location;

  top.errors := q.errors;
  top.typerep = q.typerep;
  
  q.attrFor = top.signature.outputElement.typerep;
}

concrete production attributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur '=' e::Expr ';'
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";";
  top.location = $4.location;

  top.errors := dl.errors ++ attr.errors ++ forward.errors;

  -- defs must stay here explicitly, because we dispatch on types in the forward here!
  top.productionAttributes = [];
  top.defs = [];
  
  dl.defLHSattr = attr;
  attr.attrFor = dl.typerep;

  forwards to if !null(attr.errors) then errorAttributeDef(dl, $2, attr, $4, e)
              else attr.attrDcl.attrDefDispatcher(dl, $2, attr, $4, e);
}

abstract production errorAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS '.' attr::Decorated QNameAttrOccur '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";";
  top.location = $4.location;

  top.errors := e.errors;
}

abstract production synthesizedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS '.' attr::Decorated QNameAttrOccur '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";";
  top.location = $4.location;

  top.errors := e.errors;
}

abstract production inheritedAttributeDef
top::ProductionStmt ::= dl::Decorated DefLHS '.' attr::Decorated QNameAttrOccur '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";";
  top.location = $4.location;

  top.errors := e.errors;
}

concrete production concreteDefLHS
top::DefLHS ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

  top.errors := q.lookupValue.errors ++ forward.errors;
  
  forwards to if null(q.lookupValue.dcls)
              then errorDefLHS(q)
              else q.lookupValue.dcl.defLHSDispatcher(q);
}
concrete production concreteDefLHSfwd
top::DefLHS ::= q::'forward'
{
  forwards to concreteDefLHS(qNameId(nameIdLower(terminal(IdLower_t, "forward", q.location))));
}

abstract production childDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if !null(top.defLHSattr.errors) || top.defLHSattr.attrDcl.isInherited then []
                else [err(q.location, "Cannot define synthesized attribute '" ++ top.defLHSattr.pp ++ "' on child '" ++ q.pp ++ "'")];
                
  top.typerep = q.lookupValue.typerep;
}

abstract production lhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if !null(top.defLHSattr.errors) || top.defLHSattr.attrDcl.isSynthesized then []
                else [err(q.location, "Cannot define inherited attribute '" ++ top.defLHSattr.pp ++ "' on the lhs '" ++ q.pp ++ "'")];

  top.typerep = q.lookupValue.typerep;
}

abstract production localDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if !null(top.defLHSattr.errors) || top.defLHSattr.attrDcl.isInherited then []
                else [err(q.location, "Cannot define synthesized attribute '" ++ top.defLHSattr.pp ++ "' on local '" ++ q.pp ++ "'")];

  top.typerep = q.lookupValue.typerep;
}

abstract production forwardDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if !null(top.defLHSattr.errors) || top.defLHSattr.attrDcl.isInherited then []
                else [err(q.location, "Cannot define synthesized attribute '" ++ top.defLHSattr.pp ++ "' on forward")];

  top.typerep = q.lookupValue.typerep;
}

abstract production errorDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  -- Warning: we get here two ways: one is q is lookup error. That errors at the
  -- dispatcher
  -- the other is q look up successfully and we need to error here
  -- TODO we error twice in that case!!
  top.errors := [err(q.location, "Cannot define attributes on " ++ q.pp)];
  top.typerep = q.lookupValue.typerep;
}

concrete production valueEq
top::ProductionStmt ::= val::QName '=' e::Expr ';'
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = $2.location;

  top.errors <- val.lookupValue.errors;

  -- defs must stay here explicitly, because we dispatch on types in the forward here!
  top.productionAttributes = [];
  top.defs = [];
  
  forwards to if null(val.lookupValue.dcls)
              then errorValueDef(val, $2, e)
              else val.lookupValue.dcl.defDispatcher(val, $2, e);
}

abstract production errorValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = $2.location;

  -- We get here two ways: the defDispatcher is us, or the lookup failed.
  -- TODO: this leads to duplicate error messages, when (a) the lookup fails and (b) we error here too
  top.errors := [err(val.location, val.pp ++ " cannot be assigned to.")] ++ e.errors;
}

abstract production localValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = $2.location;

  -- val is already valid here
  top.errors := e.errors;

  -- TODO: missing redefinition check
}

