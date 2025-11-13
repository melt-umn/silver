grammar silver:compiler:extension:scopegraphs2;

--

terminal Scope_t 'scope' lexer classes {KEYWORD};
terminal Graph_t 'graph' lexer classes {KEYWORD};
terminal Edges_t 'edges' lexer classes {KEYWORD};

terminal MkScope_t 'mkscope' lexer classes {KEYWORD, RESERVED};

--

concrete production edgesSpecConc
top::AGDcl ::= 'scope' 'graph' 'edges' '{' lst::SGEdgeList '}' ';'
{ forwards to labelsSpecAbs(lst.edgeNames); }

--

synthesized attribute edgeNames::[String];

nonterminal SGEdgeList with edgeNames;

concrete production edgesListCons
top::SGEdgeList ::= n::Name ',' lst::SGEdgeList
{ top.edgeNames = n.name :: lst.edgeNames; }

concrete production edgesListLast
top::SGEdgeList ::= n::Name
{ top.edgeNames = [n.name]; }

--

concrete production scopeAssertionNoDatumConc
top::ProductionStmt ::= 'mkscope' a::Name ';'
{ forwards to scopeAssertionNoDatum(^a); }