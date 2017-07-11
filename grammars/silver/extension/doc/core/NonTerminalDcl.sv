grammar silver:extension:doc:core;

aspect production nonterminalDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  top.docs := [bodilessDclCommentItem("nonterminal", id.name ++ tl.pp, "", id.location.filename)];
}

concrete production docNonterminalDcl
top::AGDcl ::= comment::DclComment cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  top.docs := [dclCommentItem("nonterminal", id.name ++ tl.pp, "", id.location.filename, comment)];

  forwards to nonterminalDcl(cl, 'nonterminal', id, tl, ';', location=top.location);
}

concrete production noDocNonterminalDcl
top::AGDcl ::= noDoc::NoDclComment_t cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList ';'
{
  top.docs := [];

  forwards to nonterminalDcl(cl, 'nonterminal', id, tl, ';', location=top.location);
}

aspect production nonterminalWithDcl
top::AGDcl ::= cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList 'with' attrs::QNames ';'
{
  top.docs := [bodilessDclCommentItem("nonterminal", id.name ++ tl.pp, "", id.location.filename)];
}

concrete production docNonterminalWithDcl
top::AGDcl ::= comment::DclComment cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList 'with' attrs::QNames ';'
{
  top.docs := [dclCommentItem("nonterminal", id.name ++ tl.pp, "", id.location.filename, comment)];

  forwards to nonterminalWithDcl(cl, 'nonterminal', id, tl, 'with', attrs, ';', location=top.location);
}

concrete production noDocNonterminalWithDcl
top::AGDcl ::= noDoc::NoDclComment_t cl::ClosedOrNot 'nonterminal' id::Name tl::BracketedOptTypeList 'with' attrs::QNames ';'
{
  top.docs := [];

  forwards to nonterminalWithDcl(cl, 'nonterminal', id, tl, 'with', attrs, ';', location=top.location);
}
