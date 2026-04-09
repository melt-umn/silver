grammar silver:compiler:extension:scopegraphs;

------------------------------------------
-- Scope attribute environment declaration

abstract production scopeInhDcl
top::AttributeDclInfo ::= fn::String ty::Type sglabs::[String]
{
  propagate compareKey;
  
  top.propagateDispatcher = propagateInh; -- todo(?)
  top.decoratedAccessHandler = inhDecoratedAccessHandler;
  top.undecoratedAccessHandler = accessBounceDecorate (inhDecoratedAccessHandler);
  top.dataAccessHandler = inhUndecoratedAccessErrorHandler;

  top.attrDefDispatcher = scopeAttributeDef;
  top.attributionDispatcher = scopeAttributeAttributionDcl;

  top.fullName = fn;
  top.typeScheme = monoType(^ty);
  top.isInherited = true;
}

-----------------------------
-- Scope attribute definition

-- todo: reduce number of times we loop over label list
abstract production scopeAttributeDef implements AttributeDef
top::ProductionStmt ::= @dl::DefLHS @attr::QNameAttrOccur e::Expr
{
  local attrQn::QName = case attr of qNameAttrOccur(qn) -> ^qn end;
  attrQn.env = top.env;

  e.env = top.env;
  e.isRoot = true;
  e.flowEnv = top.flowEnv;
  e.appDecSiteVertexInfo = nothing();
  e.decSiteVertexInfo = nothing();
  e.alwaysDecorated = true;
  e.config = top.config;
  e.compiledGrammars = top.compiledGrammars;
  e.originRules = [];
  e.grammarName = top.grammarName;
  e.frame = top.frame;
  e.finalSubst = top.finalSubst;
  e.downSubst = top.downSubst;

  local labs::[String] =
    let res::AttributeDclInfo = attrQn.lookupAttribute.dcl in
      case res of
      | scopeInhDcl(_, _, labs) -> labs
      | _ -> error("scopeAttributeAttributionDcl.labs")
      end
    end;

  forwards to productionStmtAppend(
    inheritedAttributeDef(dl, attr, ^e),
    productionStmtAppend(
      edgeContributions(^dl, ^attr, e, labs),
      undecContributions(^dl, ^attr, e)
    )
  );
}

--------------------
-- Scope attribution

-- todo: reduce number of times we loop over label list
abstract production scopeAttributeAttributionDcl implements AttributionDcl
top::AGDcl ::= at::QName attl::BracketedOptTypeExprs nt::QName
               nttl::BracketedOptTypeExprs
{
  at.env = top.env;

  local labs::[String] =
    let res::AttributeDclInfo = at.lookupAttribute.dcl in
      case res of
      | scopeInhDcl(_, _, labs) -> labs
      | _ -> error("scopeAttributeAttributionDcl.labs")
      end
    end;

  forwards to appendAGDcl(
    defaultAttributionDcl(^at, botlNone(), ^nt, ^nttl),
    appendAGDcl(
      edgeSynsOccurDcls(^at, ^nt, ^nttl, labs),
      aspectBaseDefinitions(^nt, at.name, labs)
    )
  );
}
