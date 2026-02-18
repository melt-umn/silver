grammar silver:compiler:extension:scopegraphs2;

import silver:util:treemap as rtm;

--------------------------------------------------------------------------------

nonterminal SGEnv;

synthesized attribute scopeGraphsTree::EnvTree<ScopeGraphDclInfo> occurs on SGEnv;

abstract production sgEnv
top::SGEnv ::= graphs::Defs
{
  top.scopeGraphsTree = buildTree(graphs.scopeGraphList);
}

--------------------------------------------------------------------------------

synthesized attribute scopeGraphList :: [EnvItem<ScopeGraphDclInfo>] occurs on Defs, Def;

aspect production nilDefs 
top::Defs ::= 
{
  top.scopeGraphList = [];
}

aspect production consDefs 
top::Defs ::= e1::Def e2::Defs
{
  top.scopeGraphList = e1.scopeGraphList ++ e2.scopeGraphList;
}

--

aspect default production
top::Def ::=
{
  top.scopeGraphList = [];
}

abstract production scopeGraphDef
top::Def ::= d::EnvItem<ScopeGraphDclInfo>
{
  propagate isEqual, compareTo;

  top.scopeGraphList = [^d];

  top.filterIncludeOnly := error("todo scopeGraphDef.filterIncludeOnly");
  top.filterIncludeHiding := error("todo scopeGraphDef.filterIncludeHiding");
  top.renamed = error("todo scopeGraphDef.renamed");
  top.prepended = error("todo scopeGraphDef.prepended");
}

--------------------------------------------------------------------------------

monoid attribute scopeGraphDefs::[Def];
inherited attribute sgEnv::SGEnv;

--

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  oldInterface::Maybe<InterfaceItems>  grammarName::String  grammarSource::String  grammarTime::Integer  generateLocation::String
{ g.sgEnv = sgEnv(foldr(consDefs, nilDefs(), g.scopeGraphDefs)); }

--

attribute scopeGraphDefs, sgEnv occurs on Grammar;
propagate sgEnv, scopeGraphDefs on Grammar;

--

attribute scopeGraphDefs, sgEnv occurs on File;
propagate sgEnv, scopeGraphDefs on File;

--

attribute scopeGraphDefs, sgEnv occurs on AGDcls;
propagate sgEnv, scopeGraphDefs on AGDcls;

--

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  -- otherwise computed by fwd causing cycle
  top.scopeGraphDefs := [];
}

aspect default production top::AGDcl ::=
{ top.scopeGraphDefs := []; }

attribute scopeGraphDefs, sgEnv occurs on AGDcl;
propagate sgEnv on AGDcl;

--

attribute sgEnv occurs on ProductionBody;
propagate sgEnv on ProductionBody;

--

attribute sgEnv occurs on ProductionStmts;
propagate sgEnv on ProductionStmts;

--

attribute sgEnv occurs on ProductionStmt;
propagate sgEnv on ProductionStmt;

--------------------------------------------------------------------------------

fun lookupGraphDcl [ScopeGraphDclInfo] ::= sgfn::String sgEnv::SGEnv =
  searchEnvTree(sgfn, sgEnv.scopeGraphsTree)
;
