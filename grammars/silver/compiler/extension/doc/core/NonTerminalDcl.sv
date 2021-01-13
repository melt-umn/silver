grammar silver:compiler:extension:doc:core;

aspect production nonterminalDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  top.docs := [bodilessDclCommentItem("nonterminal", top.grammarName, id.name ++ tl.unparse ++ " " ++ nm.unparse, "", id.location)];
}

concrete production docNonterminalDcl
top::AGDcl ::= comment::DocComment_t quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers ';'
{
  top.docs := [dclCommentItem("nonterminal", top.grammarName, id.name ++ tl.unparse ++ " " ++ nm.unparse, "", id.location, comment)];

  forwards to nonterminalDcl(quals, 'nonterminal', id, tl, nm, ';', location=top.location);
}

aspect production nonterminalWithDcl
top::AGDcl ::= quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers  'with' attrs::QNames ';'
{
  top.docs := [bodilessDclCommentItem("nonterminal", top.grammarName, id.name ++ tl.unparse ++ " " ++ nm.unparse, "", id.location)];
}

concrete production docNonterminalWithDcl
top::AGDcl ::= comment::DocComment_t quals::NTDeclQualifiers 'nonterminal' id::Name tl::BracketedOptTypeExprs nm::NonterminalModifiers  'with' attrs::QNames ';'
{
  top.docs := [dclCommentItem("nonterminal", top.grammarName, id.name ++ tl.unparse, "", id.location, comment)];

  forwards to nonterminalWithDcl(quals, 'nonterminal', id, tl, nm, 'with', attrs, ';', location=top.location);
}
