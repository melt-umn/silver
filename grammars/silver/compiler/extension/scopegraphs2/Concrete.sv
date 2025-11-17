grammar silver:compiler:extension:scopegraphs2;

--

terminal Scope_t 'scope' lexer classes {KEYWORD};
terminal Edges_t 'edges' lexer classes {KEYWORD};

terminal MkScope_t 'mkscope' lexer classes {KEYWORD, RESERVED};

terminal EdgeLeft_t '-[';
terminal EdgeRight_t ']->';
terminal ArrRight_t '->';
terminal Colon_t ':';

--

concrete production edgesSpecConc
top::AGDcl ::= 'scope' alias::IdUpper_t 'edges' '{' lst::SGEdgeList '}' ';'
{ forwards to labelsSpecAbs(alias.lexeme, lst.edgeNames); }

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

concrete production scopeAssertionDatumConc
top::ProductionStmt ::= 'mkscope' a::Name '->' name::String_t ':' e::Expr ';'
{ forwards to scopeAssertionDatum(^a, name, ^e); }

--

concrete production edgeAssertionLocalConc
top::ProductionStmt ::= a::Name '-[' lab::IdLower_t ']->' tgt::Expr ';'
{ forwards to edgeAssertionLocal(qNameId(^a), lab.lexeme, ^tgt); }
