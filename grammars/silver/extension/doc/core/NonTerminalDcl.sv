grammar silver:extension:doc:core;

aspect production nonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  top.docs := [toNoCommentMarkdown("nonterminal", id.name ++ tl.pp, "")];
}

concrete production docNonterminalDcl
top::AGDcl ::= comment::DocComment cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  top.docs := [toMarkdown("nonterminal", id.name ++ tl.pp, "", comment)];

  forwards to nonterminalDcl(cl, 'nonterminal', id, tl, ';', location=top.location);
}

aspect production nonterminalWithDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList 'with' attrs::QNames ';'
{
  top.docs := [toNoCommentMarkdown("nonterminal", id.name ++ tl.pp, "")];
}

concrete production docNonterminalWithDcl
top::AGDcl ::= comment::DocComment cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList 'with' attrs::QNames ';'
{
  top.docs := [toMarkdown("nonterminal", id.name ++ tl.pp, "", comment)];

  forwards to nonterminalWithDcl(cl, 'nonterminal', id, tl, 'with', attrs, ';', location=top.location);
}
