grammar silver:compiler:definition:concrete_syntax:copper;

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ParserBean
type ParserBean foreign;

function parserBean
ParserBean ::= sourceGrammar::String  location::Location  id::String
     name::String  startSymbol::ElementReference
     startLayout::[ElementReference]  interfaceNames::[String]  parserClassAuxCode::String
     parserInitCode::String  preambleCode::String  grammar_::Grammar
{
  return error("copper FFI function");
} foreign {
  "java" : return "common.CopperUtil.makeParserBean(%sourceGrammar%.toString(), %location%, %id%.toString(), %name%.toString(), (edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CopperElementReference)%startSymbol%, new common.javainterop.ConsCellCollection<>(%startLayout%), new common.javainterop.ConsCellCollection<>(%interfaceNames%), %parserClassAuxCode%.toString(), %parserInitCode%.toString(), %preambleCode%.toString(), (edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Grammar)%grammar_%)";
}

function extendedParserBean
ParserBean ::= sourceGrammar::String  location::Location  id::String
     name::String  startSymbol::ElementReference
     startLayout::[ElementReference]  interfaceNames::[String]  parserClassAuxCode::String
     parserInitCode::String  preambleCode::String  hostGrammar::Grammar
     extGrammar::Grammar
{
  return error("copper FFI function");
} foreign {
  "java" : return "common.CopperUtil.makeExtendedParserBean(%sourceGrammar%.toString(), %location%, %id%.toString(), %name%.toString(), (edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.CopperElementReference)%startSymbol%, new common.javainterop.ConsCellCollection<>(%startLayout%), new common.javainterop.ConsCellCollection<>(%interfaceNames%), %parserClassAuxCode%.toString(), %parserInitCode%.toString(), %preambleCode%.toString(), (edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Grammar)%hostGrammar%, (edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Grammar)%extGrammar%)";
}

function compileParserBeanT
IOVal<Integer> ::= parser_::ParserBean  packageName::String  parserName::String
     runMDA::Boolean  outFile::String  dumpHtml::Boolean  dumpHtmlTo::String
     xmlDump::Boolean  io::IOToken
{
  return error("copper FFI function");
} foreign {
  "java": return "common.CopperUtil.compile((edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.ParserBean)%parser_%, %packageName%.toString(), %parserName%.toString(), %runMDA%, %outFile%.toString(), %dumpHtml%, %dumpHtmlTo%.toString(), %xmlDump%, %io%)";
}

abstract production compileParserBean
top::IO<Integer> ::= parser_::ParserBean  packageName::String
     parserName::String  runMDA::Boolean  outFile::String  dumpHtml::Boolean
     dumpHtmlTo::String  xmlDump::Boolean
{
  local val::IOVal<Integer> = compileParserBeanT(parser_, packageName,
    parserName, runMDA, outFile, dumpHtml, dumpHtmlTo, xmlDump, top.stateIn);
  top.stateOut = val.io;
  top.stateVal = val.iovalue;
}

-- edu.umn.cs.melt.copper.compiletime.spec.grammarbeans.Grammar
type Grammar foreign;

function grammar_
Grammar ::= sourceGrammar::String  location::Location  id::String
    grammarElements::[GrammarElement]
-- TODO: setGrammarLayout?
{
  return error("copper FFI function");
} foreign {
  "java": return "common.CopperUtil.makeGrammar(%sourceGrammar%.toString(), %location%, %id%.toString(), new common.javainterop.ConsCellCollection<>(%grammarElements%))";
}

function extensionGrammar
Grammar ::= sourceGrammar::String  location::Location  id::String
    grammarElements::[GrammarElement]  markingTerminals::[ElementReference]
    bridgeProductions::[ElementReference]
    glueDisambiguationFunctions::[ElementReference]
{
  return error("copper FFI function");
} foreign {
  "java": return "common.CopperUtil.makeExtensionGrammar(%sourceGrammar%.toString(), %location%, %id%.toString(), new common.javainterop.ConsCellCollection<>(%grammarElements%), new common.javainterop.ConsCellCollection<>(%markingTerminals%), new common.javainterop.ConsCellCollection<>(%bridgeProductions%), new common.javainterop.ConsCellCollection<>(%glueDisambiguationFunctions%))";
}
