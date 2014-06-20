grammar silver:modification:copper_mda;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:concrete_syntax;
imports silver:definition:concrete_syntax:ast;
imports silver:modification:copper;

import silver:driver:util only computeDependencies, RootSpec;


terminal CopperMDA 'copper_mda' lexer classes {KEYWORD};

concrete production copperMdaDcl
top::AGDcl ::= 'copper_mda' testname::Name '(' orig::QName ')' '{' m::ParserComponents '}'
{
  top.pp = "";
  
  top.errors := orig.lookupValue.errors ++ m.errors;
  
  top.moduleNames = m.moduleNames;
  
  local origgram :: Decorated RootSpec =
    head(searchEnvTree(orig.lookupValue.dcl.sourceGrammar, top.compiledGrammars));
  
  local spec :: [ParserSpec] =
    findSpec(orig.lookupValue.fullName, origgram.parserSpecs);
  
  top.errors <- if !null(orig.lookupValue.errors) || !null(spec) then []
                else [err(orig.location, orig.name ++ " is not a parser.")];

  top.mdaSpecs =
    case spec of
    | parserSpec(_,_,fn,snt,hg,pfxs) :: _ -> [mdaSpec(top.grammarName, top.grammarName ++":"++ testname.name, snt, hg, m.moduleNames, pfxs)]
    | _ -> []
    end;
}

function findSpec
[ParserSpec] ::= n::String s::[ParserSpec]
{
  return if null(s) then []
         else if n == head(s).fullName then [head(s)]
         else findSpec(n, tail(s));
}

nonterminal MdaSpec with sourceGrammar, fullName, compiledGrammars,cstAst;

abstract production mdaSpec
top::MdaSpec ::= sg::String fn::String  snt::String  hostgrams::[String]  extgrams::[String]  terminalPrefixes::[Pair<String String>]
{
  top.sourceGrammar = sg;
  top.fullName = fn;
  
  -- TODO: see TODO s in ParserSpec
  production hostmed :: ModuleExportedDefs =
    moduleExportedDefs(error("no sl"), top.compiledGrammars,
      computeDependencies(hostgrams ++ extgrams, top.compiledGrammars), hostgrams, []);

  production extmed :: ModuleExportedDefs =
    moduleExportedDefs(error("no sl"), top.compiledGrammars,
      computeDependencies(hostgrams ++ extgrams, top.compiledGrammars), extgrams, []);

  top.cstAst = 
    cstCopperMdaRoot(fn, snt,
      foldr(consSyntax, nilSyntax(), hostmed.syntaxAst),
      foldr(consSyntax, nilSyntax(), extmed.syntaxAst),
      terminalPrefixes);
}

