grammar silver:modification:impide;

{--
 - This attribute will by accumulated up on each 
 -}
synthesized attribute ideSpecs :: [IdeSpec];

nonterminal IdeSpec with ideExtension, ideParserSpec, funcDcls, propDcls;

synthesized attribute ideExtension :: String;
synthesized attribute ideParserSpec :: ParserSpec;
synthesized attribute funcDcls :: [Pair<String String>] with ++ ;--fst:the type of function, such as "analyzer"; snd: the full qualified name of function 
synthesized attribute propDcls :: [IdeProperty] with ++ ;

abstract production ideSpec
top::IdeSpec ::= ext::String ideFuncDcls::[Pair<String String>] idePropDcls::[IdeProperty] pspec::ParserSpec --TODO more?
{
  top.ideExtension = ext;
  top.ideParserSpec = pspec;
  top.funcDcls := ideFuncDcls;
  top.propDcls := idePropDcls;
}
