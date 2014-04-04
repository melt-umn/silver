grammar silver:modification:impide;

{-- IdeSpec --}

synthesized attribute ideExtension :: String;
synthesized attribute ideParserSpec :: ParserSpec;
--fst:the type of function, such as "builder"; snd: the full qualified name of function 
synthesized attribute funcDcls :: [Pair<String String>] with ++ ;
synthesized attribute propDcls :: [IdeProperty] with ++ ;
synthesized attribute optDcls :: [IdeOption] with ++ ;
synthesized attribute wizards :: [IdeWizardDcl] with ++;
synthesized attribute productInfo :: IdeProductInfo;
synthesized attribute pluginConfig :: PluginConfig;

nonterminal IdeSpec with ideExtension, ideParserSpec, funcDcls, propDcls, wizards, productInfo, pluginConfig;

abstract production ideSpec
top::IdeSpec ::= 
    ext::String ideFuncDcls::[Pair<String String>] idePropDcls::[IdeProperty] wizards::[IdeWizardDcl]
    pspec::ParserSpec productInfo::IdeProductInfo pluginConfig::PluginConfig --TODO more?
{
  top.ideExtension = ext;
  top.ideParserSpec = pspec;
  top.funcDcls := ideFuncDcls;
  top.propDcls := idePropDcls;
  top.wizards := wizards;
  top.productInfo = productInfo;
  top.pluginConfig = pluginConfig;
}

{-- IdeProperty --}

synthesized attribute propName :: String;
synthesized attribute propType :: String;
synthesized attribute optional :: Boolean;
synthesized attribute defaultVal :: String;
synthesized attribute displayName :: String;

abstract production makeIdeProperty
top::IdeProperty ::= propName::String propType::String options::IdePropertyOptions
{
  top.propName = propName;
  top.propType = propType;
  top.optional = options.optional;
  top.defaultVal = options.defaultVal;
  top.displayName = if options.displayName == "" then propName else options.displayName;
}

{-- IdeOption --}

synthesized attribute optKey :: String;
synthesized attribute optValue :: String;
nonterminal IdeOption with optKey, optValue;

abstract production makeIdeOption
top::IdeOption ::= k::String v::String
{
    top.optKey = k;
    top.optValue = v;
}

{-- Color --}

nonterminal Color with r, g, b;

synthesized attribute r :: Integer;
synthesized attribute g :: Integer;
synthesized attribute b :: Integer;

abstract production makeColor
top::Color ::= r::Integer g::Integer b::Integer
{
  top.r = r;
  top.g = g;
  top.b = b;
}

{-- Font --}

nonterminal Font with color, isBold, isItalic;

synthesized attribute color :: Color;
synthesized attribute isBold :: Boolean;
synthesized attribute isItalic :: Boolean;

abstract production font
top::Font ::= color::Color isBold::Boolean isItalic::Boolean
{
  top.color = color;
  top.isBold = isBold;
  top.isItalic = isItalic;
}

