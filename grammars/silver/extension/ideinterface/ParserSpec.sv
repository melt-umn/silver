{--
DO NOT NEED NOR WANT FOR NOW
grammar silver:extension:ideinterface;

nonterminal IDEInterfaceParserSpec;
synthesized attribute ideParserSpec :: IDEInterfaceParserSpec occurs on ParserSpec;

abstract production ideParserSpec
top::IDEInterfaceParserSpec ::= s::IDEInterfaceSyntaxRoot
{
  
}

aspect production parserSpec
top::ParserSpec ::= sl::Location  sg::String  fn::String  snt::String  grams::[String]  terminalPrefixes::[Pair<String String>] addedDcls::[SyntaxDcl]
{
  top.ideParserSpec = ideParserSpec(top.cstAst.ideSyntaxRoot);
}
--}
