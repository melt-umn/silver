grammar silver:compiler:extension:scopegraphs;

--

synthesized attribute scopeLabelsTree::[EnvTree<ScopeLabelDclInfo>]
  occurs on Env;

--

aspect production emptyEnv
top::Env ::=
{
  top.scopeLabelsTree = [emptyEnvTree()];
}

aspect production appendEnv
top::Env ::= e1::Env e2::Env
{
  top.scopeLabelsTree = e1.scopeLabelsTree ++ e2.scopeLabelsTree;
}

aspect production newScopeEnv
top::Env ::= ds::[Def] e::Env
{
  top.scopeLabelsTree = buildTree(d.scopeLabelList) :: e.scopeLabelsTree;
}

aspect production occursEnv
top::Env ::= d::[OccursDclInfo]  e::Env
{
  top.scopeLabelsTree = e.scopeLabelsTree;
}

--

synthesized attribute labelSet::[String];

nonterminal ScopeLabelDclInfo with fullName, isEqual, compareTo, labelSet;

abstract production labelSetDcl
top::ScopeLabelDclInfo ::= fn::String names::[String]
{
  top.fullName = fn;
  top.isEqual = case top.compareTo of
                | labelSetDcl(fn2, _) -> fn == fn2
                | _ -> false
                end;
  top.labelSet = names;
}

instance Eq ScopeLabelDclInfo {
  eq = \l::ScopeLabelDclInfo r::ScopeLabelDclInfo -> l.fullName == r.fullName;
}

--

synthesized attribute scopeLabelList :: [EnvItem<ScopeLabelDclInfo>];

attribute scopeLabelList occurs on Defs;

aspect production nilDefs 
top::Defs ::= 
{
  top.scopeLabelList = [];
}

aspect production consDefs 
top::Defs ::= e1::Def e2::Defs
{
  top.scopeLabelList = e1.scopeLabelList ++ e2.scopeLabelList;
}

attribute scopeLabelList occurs on Def;

aspect default production
top::Def ::=
{
  top.scopeLabelList = [];
}

abstract production scopeLabelsDef
top::Def ::= d::EnvItem<ScopeLabelDclInfo>
{
  propagate isEqual, compareTo;
  top.scopeLabelList = [^d];

  top.filterIncludeOnly := error("todo");
  top.filterIncludeHiding := error("todo");
  top.renamed = error("todo");
  top.prepended = error("todo");
}

--------------------------------------------------------------------------------

monoid attribute scopeGraphDefs::[Def];
inherited attribute sgEnv::EnvTree<ScopeLabelDclInfo>;

--

aspect production grammarRootSpec
top::RootSpec ::= g::Grammar  oldInterface::Maybe<InterfaceItems>  grammarName::String  grammarSource::String  grammarTime::Integer  generateLocation::String
{ g.sgEnv = buildTree(foldr(consDefs, nilDefs(), g.scopeGraphDefs).scopeLabelList); }

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