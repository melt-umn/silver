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

{-- lookupIdeProperty
 - prop : name of property to lookup
 - args : list of IdePropertys 
 - 
 - output : if the named property was found, then a just containing the string
 -          representing the value, otherwise a nothing
 -}
function lookupIdeProperty
Maybe<String> ::= prop::String args::[IdeProperty]
{
  return
    if null(args)
    then nothing()
    else if head(args).propName == prop
         then just(head(args).propValue)
         else lookupIdeProperty(prop, tail(args));
}
