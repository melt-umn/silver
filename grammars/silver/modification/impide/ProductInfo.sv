grammar silver:modification:impide;

nonterminal IdeProductInfo with prodName, prodVersion;

synthesized attribute prodName :: String;
synthesized attribute prodVersion :: String;

abstract production ideProductInfo
top::IdeProductInfo ::= name::String version::String
{
  top.prodName = name;
  top.prodVersion = version;
}

function makeIdeProductInfo
IdeProductInfo ::= kvps::[Pair<String String>]
{
  local attribute prodInfo :: IdeProductInfo = 
    ideProductInfo(
        fromMaybe("DEFAULT", lookupBy(stringEq, "name", kvps)),
        fromMaybe("1.0.0", lookupBy(stringEq, "version", kvps))
    );

  return prodInfo;
}

function makeDefaultIdeProductInfo
IdeProductInfo ::= 
{
  return makeIdeProductInfo([]);
}
