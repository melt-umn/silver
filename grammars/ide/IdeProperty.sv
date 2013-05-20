grammar ide;

synthesized attribute propName :: String;
synthesized attribute propType :: String;
synthesized attribute propValue :: String;

nonterminal IdeProperty with propName, propType, propValue;

abstract production makeIdeProperty
top::IdeProperty ::= propName::String propType::String propValue::String
{
  top.propName = propName;
  top.propType = propType;
  top.propValue = propValue;
}
