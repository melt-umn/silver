grammar silver:compiler:extension:deprecation;

terminal IdTick_t /[A-Za-z][A-Za-z0-9\_]*[\']/ lexer classes {IDENTIFIER};
terminal IdTickTick_t /[A-Za-z][A-Za-z0-9\_]*[\'][\']/ lexer classes {IDENTIFIER};

concrete production concreteDecorateExpr
top::Expr ::= q::NameTick
{
  top.unparse = q.unparse;

  top.errors <- [wrnFromOrigin(top, "Tick suffixes no longer do ANYTHING. Remove it!")];

  forwards to baseExpr(qName(q.name));
}
concrete production concreteDontDecorateExpr
top::Expr ::= q::NameTickTick
{
  top.unparse = q.unparse;
  top.errors <- [wrnFromOrigin(top, "Double tick suffixes no longer do ANYTHING. Remove it!")];

  forwards to baseExpr(qName(q.name));
}


tracked nonterminal NameTick with config, grammarName, unparse, name;
tracked nonterminal NameTickTick with config, grammarName, unparse, name;

concrete production nameIdTick
top::NameTick ::= id::IdTick_t
{
  top.unparse = id.lexeme;
  top.name = substring(0, length(id.lexeme) -1, id.lexeme);
}

concrete production nameIdTickTick
top::NameTickTick ::= id::IdTickTick_t
{
  top.unparse = id.lexeme;
  top.name = substring(0, length(id.lexeme) -2, id.lexeme);
}

