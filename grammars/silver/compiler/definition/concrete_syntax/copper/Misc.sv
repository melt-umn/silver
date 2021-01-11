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
} foreign {
  "java" : return "common.CopperUtil.makeParserBean(%id%.toString(), %name%.toString(), %grammarName%.toString(), (edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CopperElementReference)%startSymbol%, new java.util.ArrayList<edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CopperElementReference>(new common.javainterop.ConsCellCollection(%startLayout%)), %parserClassAuxCode%.toString(), %parserInitCode%.toString(), %preambleCode%.toString(), new java.util.ArrayList<edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Grammar>(new common.javainterop.ConsCellCollection(%grammars%)))";
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Grammar
type Grammar foreign;

function grammar_
Grammar ::= id::String  name::String
{
  return error("copper FFI function");
}
