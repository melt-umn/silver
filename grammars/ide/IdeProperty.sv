grammar ide;

nonterminal IdeProperty with propName, propType;

synthesized attribute propName :: String;
synthesized attribute propType :: String;

abstract production makeIdeProperty
top::IdeProperty ::= propName::String propType::String
{
  top.propName = propName;
  top.propType = propType;
}
