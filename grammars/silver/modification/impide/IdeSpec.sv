grammar silver:modification:impide;

nonterminal IdeSpec with ideExtension, ideParserSpec, funcDcls, propDcls, productInfo, pluginConfig;

synthesized attribute ideExtension :: String;

synthesized attribute ideParserSpec :: ParserSpec;

--fst:the type of function, such as "builder"; snd: the full qualified name of function 
synthesized attribute funcDcls :: [Pair<String String>] with ++ ;

synthesized attribute propDcls :: [IdeProperty] with ++ ;

synthesized attribute productInfo :: IdeProductInfo;

synthesized attribute pluginConfig :: PluginConfig;

abstract production ideSpec
top::IdeSpec ::= 
    ext::String ideFuncDcls::[Pair<String String>] idePropDcls::[IdeProperty] 
    pspec::ParserSpec productInfo::IdeProductInfo pluginConfig::PluginConfig --TODO more?
{
  top.ideExtension = ext;
  top.ideParserSpec = pspec;
  top.funcDcls := ideFuncDcls;
  top.propDcls := idePropDcls;
  top.productInfo = productInfo;
  top.pluginConfig = pluginConfig;
}

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



