grammar silver:modification:defaultattr;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:gatherfreevars;
import silver:definition:type:syntax;
import silver:analysis:typechecking;
import silver:analysis:typechecking:core;
import silver:translation:java;


terminal Default_kwd 'default' ;

concrete production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' 
               lhs::Name '::' te::Type '::=' body::ProductionBody 
{
  top.pp = "aspect default production\n" ++ lhs.pp ++ "::" ++ prettyType(te.typerep) ++ " ::=\n" ++ body.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.defs = emptyDefs();

  production attribute namedSig :: NamedSignature;
  namedSig = namedSignature("_DEFAULT_ERROR_", [], namedSignatureElement(lhs.name, te.typerep));

  top.errors := te.errors ++ body.errors;

  local attribute fakedDefs :: Defs;
  fakedDefs = addDefaultLhsDcl(top.grammarName, lhs.location, lhs.name, te.typerep, emptyDefs());
  
  local attribute sigDefs :: Defs;
  sigDefs = addNewLexicalTyVars_ActuallyVariables(top.grammarName, top.location, te.lexicalTypeVariables);

  body.env = newScopeEnv(appendDefs(fakedDefs, sigDefs), top.env);
  body.signature = namedSig;
  body.blockContext = defaultContext();
  -- TODO: must also forbid local attributes.

  body.downSubst = emptySubst();
  body.finalSubst = body.upSubst;

  top.setupInh := body.setupInh; -- Probably should be empty?
  top.initProd := "\t\t//ASPECT DEFAULT PRODUCTION for " ++ te.pp ++ "\n" ++ body.translation;
  top.valueWeaving := body.valueWeaving; -- Probably should be empty?
}

function addDefaultLhsDcl
Defs ::= sg::String sl::Location fn::String ty::TypeExp defs::Defs
{
  return consValueDef(defaultEnvItem(decorate defaultLhsDcl(sg,sl,fn,ty) with {}), defs);
}
abstract production defaultLhsDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = error("INTERNAL ERROR: default-lhs values should never appear in interface files");
  
  top.typerep = ty;
  
  top.refDispatcher = errorReference; -- Technically, we can make this lhsReference, but the semantics of that are stupid... (it would refer to the last (non-forwarding) production)
  top.defDispatcher = errorValueDef; -- TODO: be smarter about the error message
  top.defLHSDispatcher = defaultLhsDefLHS;
}
abstract production defaultLhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  top.location = q.location;
  
  top.errors := if !top.isSynthesizedDefinition
                then [err(q.location, "Cannot define inherited attribute on " ++ q.pp)]
                else [];
  top.typerep = q.lookupValue.typerep;

  top.translation = makeNTClassName(top.signature.outputElement.typerep.typeName) ++ ".defaultSynthesizedAttributes";
}

