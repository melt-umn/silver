grammar silver:modification:impide;

nonterminal IdeSpec with ideExtension, ideParserSpec, funcDcls, propDcls;

synthesized attribute ideExtension :: String;
synthesized attribute ideParserSpec :: ParserSpec;
--fst:the type of function, such as "analyzer"; snd: the full qualified name of function 
synthesized attribute funcDcls :: [Pair<String String>] with ++ ;
synthesized attribute propDcls :: [IdeProperty] with ++ ;

abstract production ideSpec
top::IdeSpec ::= ext::String ideFuncDcls::[Pair<String String>] idePropDcls::[IdeProperty] pspec::ParserSpec --TODO more?
{
  top.ideExtension = ext;
  top.ideParserSpec = pspec;
  top.funcDcls := ideFuncDcls;
  top.propDcls := idePropDcls;
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

nonterminal IdeProperty with propName, propType;

synthesized attribute propName :: String;
synthesized attribute propType :: String;

abstract production makeIdeProperty
top::IdeProperty ::= propName::String propType::String
{
  top.propName = propName;
  top.propType = propType;
}

