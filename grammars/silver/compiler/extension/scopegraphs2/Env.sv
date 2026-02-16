grammar silver:compiler:extension:scopegraphs2;

import silver:util:treemap as rtm;

--------------------------------------------------------------------------------

nonterminal SGEnv;

synthesized attribute scopeGraphsTree::EnvTree<ScopeGraphDclInfo> occurs on SGEnv;
synthesized attribute scopeLabelsTree::EnvTree<ScopeLabelDclInfo> occurs on SGEnv;

abstract production sgEnv
top::SGEnv ::= graphs::Defs labels::Defs
{
  top.scopeGraphsTree = buildTree(graphs.scopeGraphList);
  top.scopeLabelsTree = buildTree(labels.scopeLabelList);
}

--------------------------------------------------------------------------------

synthesized attribute scopeGraphList :: [EnvItem<ScopeGraphDclInfo>] occurs on Defs, Def;
synthesized attribute scopeLabelList :: [EnvItem<ScopeLabelDclInfo>] occurs on Defs, Def;

aspect production nilDefs 
top::Defs ::= 
{
  top.scopeGraphList = [];
  top.scopeLabelList = [];
}

aspect production consDefs 
top::Defs ::= e1::Def e2::Defs
{
  top.scopeGraphList = e1.scopeGraphList ++ e2.scopeGraphList;
  top.scopeLabelList = e1.scopeLabelList ++ e2.scopeLabelList;
}

--

aspect default production
top::Def ::=
{
  top.scopeGraphList = [];
  top.scopeLabelList = [];
}

abstract production scopeGraphDef
top::Def ::= d::EnvItem<ScopeGraphDclInfo>
{
  propagate isEqual, compareTo;

  top.scopeGraphList = [^d];
  top.scopeLabelList = [];

  top.filterIncludeOnly := error("todo scopeGraphDef.filterIncludeOnly");
  top.filterIncludeHiding := error("todo scopeGraphDef.filterIncludeHiding");
  top.renamed = error("todo scopeGraphDef.renamed");
  top.prepended = error("todo scopeGraphDef.prepended");
}

abstract production scopeLabelDef
top::Def ::= d::EnvItem<ScopeLabelDclInfo>
{
  propagate isEqual, compareTo;

  top.scopeGraphList = [];
  top.scopeLabelList = [^d];

  top.filterIncludeOnly := error("todo scopeLabelDef.filterIncludeOnly");
  top.filterIncludeHiding := error("todo scopeLabelDef.filterIncludeHiding");
  top.renamed = error("todo scopeLabelDef.renamed");
  top.prepended = error("todo scopeLabelDef.prepended");
}

--------------------------------------------------------------------------------

monoid attribute scopeGraphDefs::[Def];
monoid attribute scopeLabelDefs::[Def];
inherited attribute sgEnv::SGEnv;

--

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  oldInterface::Maybe<InterfaceItems>  grammarName::String  grammarSource::String  grammarTime::Integer  generateLocation::String
{ g.sgEnv = sgEnv(foldr(consDefs, nilDefs(), g.scopeGraphDefs), foldr(consDefs, nilDefs(), g.scopeLabelDefs)); }

--

attribute scopeGraphDefs, scopeLabelDefs, sgEnv occurs on Grammar;
propagate sgEnv, scopeGraphDefs, scopeLabelDefs on Grammar;

--

attribute scopeGraphDefs, scopeLabelDefs, sgEnv occurs on File;
propagate sgEnv, scopeGraphDefs, scopeLabelDefs on File;

--

attribute scopeGraphDefs, scopeLabelDefs, sgEnv occurs on AGDcls;
propagate sgEnv, scopeGraphDefs, scopeLabelDefs on AGDcls;

--

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  -- otherwise computed by fwd causing cycle
  top.scopeGraphDefs := [];
  top.scopeLabelDefs := [];
}

aspect default production top::AGDcl ::=
{ top.scopeGraphDefs := []; 
  top.scopeLabelDefs := []; }

attribute scopeGraphDefs, scopeLabelDefs, sgEnv occurs on AGDcl;
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

fun allLabelDcls [ScopeLabelDclInfo] ::= sgfn::String sgEnv::SGEnv =
  rtm:values(sgEnv.scopeLabelsTree)
;

fun lookupLabelDcl [ScopeLabelDclInfo] ::= fn::String sgEnv::SGEnv =
  searchEnvTree(fn, sgEnv.scopeLabelsTree)
;

