grammar silver:compiler:extension:doc:core;

aspect production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  top.docs := [bodilessDclCommentItem("nonterminal", id.name ++ tl.unparse ++ " " ++ nm.unparse, "", id.location.filename)];
}

concrete production docNonterminalDcl
top::AGDcl ::= comment::DclComment quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  top.docs := [dclCommentItem("nonterminal", id.name ++ tl.unparse ++ " " ++ nm.unparse, "", id.location.filename, comment)];

  forwards to nonterminalDcl(quals, 'nonterminal', id, tl, nm, ';', location=top.location);
}

concrete production noDocNonterminalDcl
top::AGDcl ::= noDoc::NoDclComment_t quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  top.docs := [];

  forwards to nonterminalDcl(quals, 'nonterminal', id, tl, nm, ';', location=top.location);
}

aspect production nonterminalWithDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers  'with' attrs::QNames ';'
{
  top.docs := [bodilessDclCommentItem("nonterminal", id.name ++ tl.unparse ++ " " ++ nm.unparse, "", id.location.filename)];
}

concrete production docNonterminalWithDcl
top::AGDcl ::= comment::DclComment quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers  'with' attrs::QNames ';'
{
  top.docs := [dclCommentItem("nonterminal", id.name ++ tl.unparse, "", id.location.filename, comment)];

  forwards to nonterminalWithDcl(quals, 'nonterminal', id, tl, nm, 'with', attrs, ';', location=top.location);
}

concrete production noDocNonterminalWithDcl
top::AGDcl ::= noDoc::NoDclComment_t quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers  'with' attrs::QNames ';'
{
  top.docs := [];

  forwards to nonterminalWithDcl(quals, 'nonterminal', id, tl, nm, 'with', attrs, ';', location=top.location);
}
