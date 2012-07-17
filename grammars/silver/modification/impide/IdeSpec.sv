grammar silver:modification:impide;

{--
 - This attribute will by accumulated up on each 
 -}
synthesized attribute ideSpecs :: [IdeSpec];

nonterminal IdeSpec with ideExtension, ideParserSpec;

synthesized attribute ideExtension :: String;
synthesized attribute ideParserSpec :: ParserSpec;

abstract production ideSpec
top::IdeSpec ::= ext::String  pspec::ParserSpec --TODO more?
{
  top.ideExtension = ext;
  top.ideParserSpec = pspec;
}
