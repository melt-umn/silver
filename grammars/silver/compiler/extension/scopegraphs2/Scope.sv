grammar silver:compiler:extension:scopegraphs2;

--

synthesized attribute id::Integer;
synthesized attribute name::String;
synthesized attribute datum::Decorated Datum;

-- put this in silver:langutil:scopegraphs:

nonterminal SGScope with id, datum;

abstract production scope
top::SGScope ::= 
{ top.id = genInt();
  top.datum = decorate datumNone() with {}; }

abstract production scopeDatum
top::SGScope ::= name::String e::a
{ top.id = genInt();
  top.datum = decorate datumJust(name, e) with {}; }

type DecScope<(i::InhSet)> = Decorated SGScope with i;

-- put this in silver:langutil:scopegraphs:

nonterminal Datum with name;

production datumNone
top::Datum ::=
{ top.name = ""; }

production datumJust
top::Datum ::= name::String expr::a
{ top.name = name; }

-- put this in silver:langutil:scopegraphs:

nonterminal Label<(i::InhSet)> with name, demand<i>;

synthesized attribute demand<(i::InhSet)>::([DecScope<i>] ::= DecScope<i>);

production label
top::Label<(i::InhSet)> ::=
{ top.demand = error("label.demand");
  top.name = error("label.name"); }

instance Eq Label<(i::InhSet)> {
  eq = \left::Label<(i::InhSet)> right::Label<(i::InhSet)> -> 
    left.name == right.name;
}

-- Viz stuff:

synthesized attribute col::String occurs on Label<(i::InhSet)>;

aspect production label
top::Label<(i::InhSet)> ::=
{ top.col = "black"; }

--

fun vizStr String ::= labs::[Label<i>] scopes::[DecScope<i>] =
  "digraph {layoud=dot\n" ++ 
    implode("\n", map(vizStrScope, scopes)) ++ "\n" ++
    implode("\n", concat(map(vizStrEdges(labs, _), scopes))) ++ "\n" ++
  "}\n"
;

--

fun vizStrScope String ::= scope::DecScope<(i::InhSet)> =
  "{ node [label=\"" ++ vizStrScopeLabel(scope) ++ "\" " ++ 
    "style=rounded shape=rect fontsize=12 margin=0 fillcolor=white] " ++ 
    toString(scope.id) ++ 
  "}"
;

fun vizStrScopeLabel String ::= scope::DecScope<(i::InhSet)> =
  case scope.datum of
  | datumNone()  -> toString(scope.id)
  | datumJust(n, _) -> toString(scope.id) ++ " ↦ " ++ n
  end
;

--

fun vizStrEdges [String] ::= labs::[Label<i>] scope::DecScope<i> =
  concat(map(
    \l::Label<i> ->
      map (vizStrEdge(l, scope, _), l.demand(scope)),
    labs
  ))
;

fun vizStrEdge String ::= lab::Label<i> src::DecScope<i> tgt::DecScope<i> =
  "{edge [label=\"" ++ lab.name ++ "\" color=" ++ lab.col ++ 
                                     " fontcolor=" ++ lab.col ++ "] " ++ 
  toString(src.id) ++ " -> " ++ toString(tgt.id) ++ "}"
;