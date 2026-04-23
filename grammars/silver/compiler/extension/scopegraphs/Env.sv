grammar silver:compiler:extension:scopegraphs;

import silver:util:treemap as rtm;

----------------------------------
-- Scope graph definition (labels)

synthesized attribute labels::[String];
synthesized attribute labelsFn::[String];
synthesized attribute scopeType::Type;
synthesized attribute labelsAlias::String;

nonterminal ScopeGraphDclInfo with fullName, isEqual, compareTo, labels, labelsFn, scopeType, labelsAlias;

abstract production graphDcl
top::ScopeGraphDclInfo ::= gram::String name::String labs::[String] labsAlias::String
{
  top.fullName = gram ++ ":" ++ name;
  top.isEqual = ^top.compareTo == ^top;
  top.labels = sort(labs);
  top.labelsFn = map(\l::String -> gram ++ ":" ++ l, top.labels);
  top.scopeType = decScopeTy(top.labelsFn);
  top.labelsAlias = labsAlias;
}

instance Eq ScopeGraphDclInfo {
  eq = \l::ScopeGraphDclInfo r::ScopeGraphDclInfo -> l.fullName == r.fullName;
}

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
top::RootSpec ::= g::Grammar  oldInterface::Maybe<InterfaceItems> 
                  grammarName::String  grammarSource::String 
                  grammarTime::Integer  generateLocation::String
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

aspect default production top::AGDcl ::=
{ top.scopeGraphDefs := []; }

aspect production attributionDcl
top::AGDcl ::= 'attribute' at::QName attl::BracketedOptTypeExprs 
               'occurs' 'on' nt::QName nttl::BracketedOptTypeExprs ';'
{
  -- otherwise computed by fwd causing cycle
  top.scopeGraphDefs := [];
}

aspect production propagateOnNTListExcludingDcl_c
top::AGDcl ::= 'propagate' attrs::AttrNameList 'on' nts::NameList 'excluding' ps::ProdNameList ';'
{
  -- otherwise computed by fwd causing cycle
  top.scopeGraphDefs := [];
}

aspect production propagateOnNTListDcl_c
top::AGDcl ::= 'propagate' attrs::AttrNameList 'on' nts::NameList ';'
{
  -- otherwise computed by fwd causing cycle
  top.scopeGraphDefs := [];
}

aspect production propagateOnNTListDcl
top::AGDcl ::= attrs::AttrNameList nts::NameList ps::ProdNameList
{
  -- otherwise computed by fwd causing cycle
  top.scopeGraphDefs := [];
}

aspect production propagateOnOneNTDcl
top::AGDcl ::= attrs::AttrNameList nt::QName ps::ProdNameList
{
  -- otherwise computed by fwd causing cycle
  top.scopeGraphDefs := [];
}

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

--

attribute sgEnv occurs on PrimPattern, PrimPatterns, Expr, Exprs, AssignExpr, AppExpr, AppExprs;
propagate sgEnv on PrimPattern, PrimPatterns, Expr, Exprs, AssignExpr, AppExpr, AppExprs;

--

attribute sgEnv occurs on MatchRule, MRuleList, Pattern, NamedPatternList, NamedPattern;
propagate sgEnv on MatchRule, MRuleList, Pattern, NamedPatternList, NamedPattern;

--

-- non supply runtime error otherwise
aspect production applicationExpr
top::Expr ::= e::Expr '(' es::AppExprs ')'
{ e.sgEnv = top.sgEnv;
  es.sgEnv = top.sgEnv; }

-- non supply runtime error otherwise
aspect production prodPattern
top::PrimPattern ::= qn::QName '(' ns::VarBinders ')' '->' e::Expr
{ e.sgEnv = top.sgEnv; }

-- non supply runtime error otherwise
aspect production caseExpr_c
top::Expr ::= 'case' es::Exprs 'of' b::Opt_Vbar_t ml::MRuleList 'end'
{ es.sgEnv = top.sgEnv; 
  ml.sgEnv = top.sgEnv; }

aspect production attrContainsAppend
top::ProductionStmt ::= dl::DefLHS '.' attr::QNameAttrOccur '<-' e::Expr ';'
{
  propagate sgEnv;
}

--

