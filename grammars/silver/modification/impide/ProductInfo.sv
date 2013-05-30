grammar silver:modification:impide;

nonterminal IdeProductInfo with prodName, prodVersion;

synthesized attribute prodName :: String;
synthesized attribute prodVersion :: String;

abstract production ideProductInfo
top::IdeProductInfo ::= name::String version::String -- add more in future
{
  top.prodName = name;
  top.prodVersion = version;
}

function makeIdeProductInfo
IdeProductInfo ::= kvps::[Pair<String String>]
{
  local attribute prodInfo :: IdeProductInfo = 
    ideProductInfo(
        fromMaybe("", lookupBy(stringEq, "name", kvps)),
        fromMaybe("", lookupBy(stringEq, "version", kvps))
    );

  return prodInfo;
}

function makeEmptyIdeProductInfo
IdeProductInfo ::= 
{
  return makeIdeProductInfo([]);
}
