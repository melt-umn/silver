grammar silver:modification:copper_mda;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:concrete_syntax;
imports silver:definition:concrete_syntax:ast;
imports silver:modification:copper;

import silver:driver only computeDependencies;


terminal CopperMDA 'copper_mda' ;

concrete production copperMdaDcl
top::AGDcl ::= 'copper_mda' testname::Name '(' orig::QName ')' '{' m::ModuleList '}'
{
  top.pp = "";
  top.location = orig.location;
  
  top.errors := orig.lookupValue.errors ++ m.errors;
  
  local attribute origgram :: Decorated RootSpec;
  origgram = head(searchEnvTree(orig.lookupValue.dcl.sourceGrammar, top.compiledGrammars));
  
  local attribute spec :: [ParserSpec];
  spec = findSpec(orig.lookupValue.fullName, origgram.parserSpecs);
  
  top.errors <- if !null(orig.lookupValue.errors) || !null(spec) then []
                else [err(orig.location, orig.name ++ " is not a parser.")];

  top.mdaSpecs =
    case spec of
    | parserSpec(_,_,fn,snt,hg) :: _ -> [mdaSpec(top.grammarName, top.grammarName ++":"++ testname.name, snt, hg, m.moduleNames)]
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
top::MdaSpec ::= sg::String fn::String  snt::String  hostgrams::[String]  extgrams::[String]
{
  top.sourceGrammar = sg;
  top.fullName = fn;
  -- TODO: see TODO s in ParserSpec
  production attribute hostmed :: ModuleExportedDefs;
  hostmed = moduleExportedDefs(error("no sl"), top.compiledGrammars, computeDependencies(hostgrams ++ extgrams, top.compiledGrammars), hostgrams, []);

  production attribute extmed :: ModuleExportedDefs;
  extmed = moduleExportedDefs(error("no sl"), top.compiledGrammars, computeDependencies(hostgrams ++ extgrams, top.compiledGrammars), extgrams, []);

  top.cstAst = cstCopperMdaRoot(fn, snt, foldr(consSyntax, nilSyntax(), hostmed.syntaxAst), foldr(consSyntax, nilSyntax(), extmed.syntaxAst));
}

