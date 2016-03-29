grammar silver:extension:doc:core;

aspect production nonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  top.docs := [bodilessCommentItem("nonterminal", id.name ++ tl.pp, "", id.location.filename)];
}

concrete production docNonterminalDcl
top::AGDcl ::= comment::DocComment cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  top.docs := [commentItem("nonterminal", id.name ++ tl.pp, "", id.location.filename, comment)];

  forwards to nonterminalDcl(cl, 'nonterminal', id, tl, ';', location=top.location);
}

concrete production noDocNonterminalDcl
top::AGDcl ::= noDoc::NoDocComment_t cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  top.docs := [];

  forwards to nonterminalDcl(cl, 'nonterminal', id, tl, ';', location=top.location);
}

aspect production nonterminalWithDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList 'with' attrs::QNames ';'
{
  top.docs := [bodilessCommentItem("nonterminal", id.name ++ tl.pp, "", id.location.filename)];
}

concrete production docNonterminalWithDcl
top::AGDcl ::= comment::DocComment cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList 'with' attrs::QNames ';'
{
  top.docs := [commentItem("nonterminal", id.name ++ tl.pp, "", id.location.filename, comment)];

  forwards to nonterminalWithDcl(cl, 'nonterminal', id, tl, 'with', attrs, ';', location=top.location);
}

concrete production noDocNonterminalWithDcl
top::AGDcl ::= noDoc::NoDocComment_t cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList 'with' attrs::QNames ';'
{
  top.docs := [];

  forwards to nonterminalWithDcl(cl, 'nonterminal', id, tl, 'with', attrs, ';', location=top.location);
}
