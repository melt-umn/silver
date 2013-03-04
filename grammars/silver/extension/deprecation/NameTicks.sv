grammar silver:extension:deprecation;

terminal IdTick_t /[A-Za-z][A-Za-z0-9\_]*[\']/ lexer classes {IDENTIFIER};
terminal IdTickTick_t /[A-Za-z][A-Za-z0-9\_]*[\'][\']/ lexer classes {IDENTIFIER};

concrete production concreteDecorateExpr
top::Expr ::= q::NameTick
{
  top.pp = q.pp;

  top.errors <- [wrn(top.location, "Tick suffixes no longer do ANYTHING. Remove it!")];

  forwards to baseExpr(qName(q.location, q.name), location=q.location);
}
concrete production concreteDontDecorateExpr
top::Expr ::= q::NameTickTick
{
  top.pp = q.pp;
  top.errors <- [wrn(top.location, "Double tick suffixes no longer do ANYTHING. Remove it!")];

  forwards to baseExpr(qName(q.location, q.name), location=q.location);
}


nonterminal NameTick with config, grammarName, file, location, pp, name;
nonterminal NameTickTick with config, grammarName, file, location, pp, name;

concrete production nameIdTick
top::NameTick ::= id::IdTick_t
{
  top.pp = id.lexeme;
  top.name = substring(0, length(id.lexeme) -1, id.lexeme);
}

concrete production nameIdTickTick
top::NameTickTick ::= id::IdTickTick_t
{
  top.pp = id.lexeme;
  top.name = substring(0, length(id.lexeme) -2, id.lexeme);
}

