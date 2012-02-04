grammar silver:definition:core;

nonterminal ProductionBody with grammarName, file, env, location, pp, errors, defs, productionAttributes, warnings, signature, uniqueSignificantExpression;

nonterminal ProductionStmts with grammarName, file, env, location, pp, errors, defs, productionAttributes, warnings, signature, uniqueSignificantExpression;
nonterminal ProductionStmt with grammarName, file, env, location, pp, errors, defs, productionAttributes, warnings, signature, uniqueSignificantExpression;

nonterminal DefLHS with grammarName, file, env, location, pp, errors, signature, typerep;

nonterminal ForwardInhs with grammarName, file, env, location, pp, errors, signature;
nonterminal ForwardInh with grammarName, file, env, location, pp, errors, signature;
nonterminal ForwardLHSExpr with grammarName, file, env, location, pp, errors, signature, typerep;

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
autocopy attribute signature :: Decorated NamedSignature;

{--
 - Defs of attributes that should be wrapped up as production attributes.
 -}
synthesized attribute productionAttributes :: Defs;
{--
 - Either the 'forward' expression, or the 'return' expression.
 - I gave it an obtuse name so it could easily be renamed in the future.
 -}
synthesized attribute uniqueSignificantExpression :: [Decorated Expr];

concrete production emptyProductionBodySemi
top::ProductionBody ::= ';'
{
  top.pp = ";";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to defaultProductionBody(productionStmtsNone());
}

concrete production emptyProductionBodyCurly
top::ProductionBody ::= '{' '}'
{
  top.pp = "{}";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to defaultProductionBody(productionStmtsNone());
}

concrete production productionBody
top::ProductionBody ::= '{' stmts::ProductionStmts '}'
{
  top.pp = "{" ++ stmts.pp ++ "}";
  top.location = loc(top.file, $1.line, $1.column);

  production attribute extraStmts :: ProductionStmts with productionStmtsAppend ;
  extraStmts := stmts;

  forwards to defaultProductionBody(extraStmts) ;
}

abstract production defaultProductionBody
top::ProductionBody ::= stmts::ProductionStmts
{
  top.pp = stmts.pp;
  top.location = stmts.location;
  top.defs = stmts.defs;

  top.productionAttributes = stmts.productionAttributes;
  top.uniqueSignificantExpression = stmts.uniqueSignificantExpression;

  top.errors := stmts.errors;
  top.warnings := stmts.warnings;
}

abstract production productionStmtsNone
top::ProductionStmts ::= 
{
  top.pp = "";
  top.location = loc(top.file, -1, -1);
  top.defs = emptyDefs();

  top.productionAttributes = emptyDefs();
  top.uniqueSignificantExpression = [];

  top.errors := [];
  top.warnings := [];
}

concrete production productionStmts
top::ProductionStmts ::= stmt::ProductionStmt
{
  top.pp = stmt.pp;
  top.location = stmt.location;

  top.productionAttributes = stmt.productionAttributes;
  top.uniqueSignificantExpression = stmt.uniqueSignificantExpression;
 
  top.defs = stmt.defs;
  top.errors := stmt.errors;
  top.warnings := stmt.warnings;
}

concrete production productionStmtsCons
top::ProductionStmts ::= h::ProductionStmt t::ProductionStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;
  
  top.productionAttributes = appendDefs(h.productionAttributes, t.productionAttributes);
  top.uniqueSignificantExpression = h.uniqueSignificantExpression ++ t.uniqueSignificantExpression;

  top.defs = appendDefs(h.defs, t.defs);
  top.errors := h.errors ++ t.errors;
  top.warnings := h.warnings ++ t.warnings;
}

abstract production productionStmtsAppend
top::ProductionStmts ::= h::ProductionStmts t::ProductionStmts
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;
  top.defs = appendDefs(h.defs, t.defs);

  top.productionAttributes = appendDefs(h.productionAttributes, t.productionAttributes);
  top.uniqueSignificantExpression = h.uniqueSignificantExpression ++ t.uniqueSignificantExpression;

  top.errors := h.errors ++ t.errors;
  top.warnings := h.warnings ++ t.warnings;
}

abstract production productionStmtAppend
top::ProductionStmt ::= h::ProductionStmt t::ProductionStmt
{
  top.pp = h.pp ++ "\n" ++ t.pp;
  top.location = h.location;
  top.defs = appendDefs(h.defs, t.defs);

  top.productionAttributes = appendDefs(h.productionAttributes, t.productionAttributes);
  top.uniqueSignificantExpression = h.uniqueSignificantExpression ++ t.uniqueSignificantExpression;

  top.errors := h.errors ++ t.errors;
  top.warnings := h.warnings ++ t.warnings;
}

--------------------------------------------------------------------------------

abstract production defaultProductionStmt
top::ProductionStmt ::=
{
  -- as is usual for defaults ("base classes")
  -- can't provide pp or location, errors should NOT be defined!
  top.productionAttributes = emptyDefs();
  top.uniqueSignificantExpression = [];
  
  top.defs = emptyDefs();
  top.warnings := [];
}

concrete production returnDef
top::ProductionStmt ::= 'return' e::Expr ';'
{
  top.pp = "\treturn " ++ e.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);
  
  top.uniqueSignificantExpression = [e];

  top.errors := e.errors;
  
  top.errors <- if !top.blockContext.permitReturn
                then [err(top.location, "Return is not valid in this context. (They are only permitted in function declarations.)")]
                else [];

  forwards to defaultProductionStmt();
}

concrete production localAttributeDcl
top::ProductionStmt ::= 'local' 'attribute' a::Name '::' te::Type ';'
{
  top.pp = "\tlocal attribute " ++ a.pp ++ "::" ++ te.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fName :: String;
  fName = top.signature.fullName ++ ":local:" ++ a.name;

  top.defs = addLocalDcl(top.grammarName, a.location, fName, te.typerep, emptyDefs());

  top.errors := te.errors;

  top.errors <-
        if length(getValueDclAll(fName, top.env)) > 1 
        then [err(top.location, "Value '" ++ fName ++ "' is already bound.")]
        else [];

  forwards to defaultProductionStmt();
}

concrete production productionAttributeDcl
top::ProductionStmt ::= 'production' 'attribute' a::Name '::' te::Type ';'
{
  -- TODO: we should pp the production keyword, not local here!!
  --top.pp = "\tproduction attribute " ++ a.pp ++ "::" ++ te.pp ++ ";";

  top.productionAttributes = forward.defs;
  top.defs = emptyDefs();

  top.errors <- if !top.blockContext.permitProductionAttributes
                then [err(top.location, "Production attributes are not valid in this context.")]
                else [];

  forwards to localAttributeDcl(terminal(Local_kwd, "local", $1), $2, a, $4, te, $6);
}

concrete production forwardsTo
top::ProductionStmt ::= 'forwards' 'to' e::Expr ';'
{
  top.pp = "\tforwards to " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.productionAttributes = addForwardDcl(top.grammarName, top.location, top.signature.outputElement.typerep, emptyDefs());
  top.uniqueSignificantExpression = [e];

  top.errors := e.errors;

  top.errors <- if !top.blockContext.permitForward
                then [err(top.location, "Forwarding is not permitted in this context. (Only permitted in non-aspect productions.)")]
                else [];

  forwards to defaultProductionStmt();
}

concrete production forwardsToWith
top::ProductionStmt ::= 'forwards' 'to' e::Expr 'with' '{' inh::ForwardInhs '}' ';'
{
  top.pp = "\tforwards to " ++ e.pp ++ " with {" ++ inh.pp ++ "};";
  top.location = loc(top.file, $1.line, $1.column);

  top.productionAttributes = addForwardDcl(top.grammarName, top.location, top.signature.outputElement.typerep, emptyDefs());
  top.uniqueSignificantExpression = [e];

  top.errors := e.errors ++ inh.errors;

  top.errors <- if !top.blockContext.permitForward
                then [err(top.location, "Forwarding is not permitted in this context.")]
                else [];

  forwards to defaultProductionStmt(); -- TODO forward to ordinary fwd + set of inh defs
}

concrete production forwardingWith
top::ProductionStmt ::= 'forwarding' 'with' '{' inh::ForwardInhs '}' ';'
{
  top.pp = "\tforwarding with {" ++ inh.pp ++ "};";
  top.location = loc(top.file, $1.line, $1.column);

  production attribute fwdDcls :: [Decorated DclInfo];
  fwdDcls = getValueDcl("forward", top.env);

  top.errors := inh.errors;
  
  top.errors <- if null(fwdDcls)
                then [err(top.location, "'forwarding with' clause for a production that does not forward!")]
                else [];

  forwards to defaultProductionStmt(); -- TODO forward to set of ordinary inh defs (see above)
}

-- TODO eliminate these (/ combine with the ones for decorate expression)
concrete production forwardInh
top::ForwardInh ::= lhs::ForwardLHSExpr '=' e::Expr ';'
{
  top.pp = lhs.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

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
top::ForwardLHSExpr ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(q, top.signature.outputElement.typerep);

  top.errors := q.lookupAttribute.errors ++ occursCheck.errors;
  top.typerep = occursCheck.typerep;
}

concrete production attributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::QName '=' e::Expr ';'
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $4.line, $4.column);

  top.errors <- attr.lookupAttribute.errors;

  -- defs must stay here explicitly, because we dispatch on types in the forward here!
  top.productionAttributes = emptyDefs();
  top.defs = emptyDefs();

  forwards to if null(attr.lookupAttribute.dcls)
              then errorAttributeDef(dl, $2, attr, $4, e)
              else attr.lookupAttribute.dcl.attrDefDispatcher(dl, $2, attr, $4, e);

  -- TODO: this design might be somewhat broken.
  -- We'd like to automatically disambiguate attributes that don't occur on
  -- the NT of interest. (e.g. two 'pp', but this NT only has one of them.)
  -- Currently, we're uniquely choosing the attribute, before considering
  -- the lhs. Perhaps that should be reversed!
}

abstract production errorAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $4.line, $4.column);

  -- No error message. We only get here via attributeDef, which will error for us
  top.errors := e.errors;
  
  -- ignore dl, we don't have the proper set of inh attrs to give it!
  
  forwards to defaultProductionStmt();
}

abstract production synthesizedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $4.line, $4.column);

  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(attr, dl.typerep);

  -- we already know attr is valid here.
  top.errors := dl.errors ++ occursCheck.errors ++ e.errors;
  
  -- TODO: missing redefinition check

  dl.isSynthesizedDefinition = true;
  
  forwards to defaultProductionStmt();
}

abstract production inheritedAttributeDef
top::ProductionStmt ::= dl::DefLHS '.' attr::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ dl.pp ++ "." ++ attr.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $4.line, $4.column);

  production attribute occursCheck :: OccursCheck;
  occursCheck = occursCheckQName(attr, dl.typerep);

  -- we already know attr is valid here.
  top.errors := dl.errors ++ occursCheck.errors ++ e.errors;

  -- TODO: missing redefinition check

  dl.isSynthesizedDefinition = false;
  
  forwards to defaultProductionStmt();
}

inherited attribute isSynthesizedDefinition :: Boolean occurs on DefLHS; -- true = syn, false = inh

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
  forwards to concreteDefLHS(qNameId(nameIdLower(terminal(IdLower_t, "forward", q))));
}

abstract production childDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if top.isSynthesizedDefinition
                then [err(q.location, "Cannot define synthesized attribute on child " ++ q.pp)]
                else [];
                
  top.typerep = q.lookupValue.typerep;
}

abstract production lhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if !top.isSynthesizedDefinition
                then [err(q.location, "Cannot define inherited attribute on " ++ q.pp)]
                else [];
  top.typerep = q.lookupValue.typerep;
}

abstract production localDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if top.isSynthesizedDefinition
                then [err(q.location, "Cannot define synthesized attribute on local " ++ q.pp)]
                else [];
  top.typerep = q.lookupValue.typerep;
}

abstract production forwardDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if top.isSynthesizedDefinition
                then [err(q.location, "Cannot define synthesized attribute on forward")]
                else [];
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

concrete production valueDef
top::ProductionStmt ::= val::QName '=' e::Expr ';'
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  top.errors <- val.lookupValue.errors;

  -- defs must stay here explicitly, because we dispatch on types in the forward here!
  top.productionAttributes = emptyDefs();
  top.defs = emptyDefs();
  
  forwards to if null(val.lookupValue.dcls)
              then errorValueDef(val, $2, e)
              else val.lookupValue.dcl.defDispatcher(val, $2, e);
}

abstract production errorValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  -- We get here two ways: the defDispatcher is us, or the lookup failed.
  -- TODO: this leads to duplicate error messages, when (a) the lookup fails and (b) we error here too
  top.errors := [err(val.location, val.pp ++ " cannot be assigned to.")] ++ e.errors;

  forwards to defaultProductionStmt();
}

abstract production localValueDef
top::ProductionStmt ::= val::Decorated QName '=' e::Expr
{
  top.pp = "\t" ++ val.pp ++ " = " ++ e.pp ++ ";";
  top.location = loc(top.file, $2.line, $2.column);

  -- val is already valid here
  top.errors := e.errors;

  -- TODO: missing redefinition check

  forwards to defaultProductionStmt();
}

