grammar silver:compiler:definition:concrete_syntax:copper;

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ParserBean
type ParserBean foreign;

function parserBean
ParserBean ::= id::String  name::String  grammarName::String
     startSymbol::ElementReference  startLayout::[ElementReference]
     parserClassAuxCode::String  parserInitCode::String  preambleCode::String
     grammars::[Grammar]
{
  return error("copper FFI function");
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Grammar
type Grammar foreign;

function grammar_
Grammar ::= id::String  name::String  x
{
  return error("copper FFI function");
}
