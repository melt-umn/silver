grammar silver:extension:doc:core;

aspect production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs ';'
{
  top.docs := [bodilessDclCommentItem("nonterminal", id.name ++ tl.unparse, "", id.location.filename)];
}

concrete production docNonterminalDcl
top::AGDcl ::= comment::DclComment quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs ';'
{
  top.docs := [dclCommentItem("nonterminal", id.name ++ tl.unparse, "", id.location.filename, comment)];

  forwards to nonterminalDcl(quals, 'nonterminal', id, tl, ';', location=top.location);
}

concrete production noDocNonterminalDcl
top::AGDcl ::= noDoc::NoDclComment_t quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs ';'
{
  top.docs := [];

  forwards to nonterminalDcl(quals, 'nonterminal', id, tl, ';', location=top.location);
}

aspect production nonterminalWithDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs 'with' attrs::QNames ';'
{
  top.docs := [bodilessDclCommentItem("nonterminal", id.name ++ tl.unparse, "", id.location.filename)];
}

concrete production docNonterminalWithDcl
top::AGDcl ::= comment::DclComment quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs 'with' attrs::QNames ';'
{
  top.docs := [dclCommentItem("nonterminal", id.name ++ tl.unparse, "", id.location.filename, comment)];

  forwards to nonterminalWithDcl(quals, 'nonterminal', id, tl, 'with', attrs, ';', location=top.location);
}

concrete production noDocNonterminalWithDcl
top::AGDcl ::= noDoc::NoDclComment_t quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs 'with' attrs::QNames ';'
{
  top.docs := [];

  forwards to nonterminalWithDcl(quals, 'nonterminal', id, tl, 'with', attrs, ';', location=top.location);
}
