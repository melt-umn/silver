grammar silver:compiler:definition:concrete_syntax:copper;

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ParserBean
type ParserBean foreign;

function parserBean
ParserBean ::= id::String  name::String  startSymbol::ElementReference
     startLayout::[ElementReference]  parserClassAuxCode::String
     parserInitCode::String  preambleCode::String  grammar_::Grammar
{
  return error("copper FFI function");
} foreign {
  "java" : return "common.CopperUtil.makeParserBean(%id%.toString(), %name%.toString(), (edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CopperElementReference)%startSymbol%, new common.javainterop.ConsCellCollection(%startLayout%), %parserClassAuxCode%.toString(), %parserInitCode%.toString(), %preambleCode%.toString(), (edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Grammar)%grammar_%)";
}

function compileParserBean
IO ::= parser_::ParserBean  io::IO
{
  return error("copper FFI function");
} foreign {
  "java": return "common.CopperUtil.compile((edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ParserBean)%parser_%, %io%)";
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Grammar
type Grammar foreign;

function grammar_
Grammar ::= id::String  copperGrammarElements::[GrammarElement]
-- TODO: setGrammarLayout?
{
  return error("copper FFI function");
} foreign {
  "java": return "common.CopperUtil.makeGrammar(%id%.toString(), new common.javainterop.ConsCellCollection(%copperGrammarElements%))";
}
