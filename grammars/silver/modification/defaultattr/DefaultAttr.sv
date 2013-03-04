grammar silver:modification:defaultattr;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax;
--import silver:analysis:typechecking:core;
import silver:translation:java;


terminal Default_kwd 'default' lexer classes {KEYWORD};

concrete production aspectDefaultProduction
top::AGDcl ::= 'aspect' 'default' 'production' 
               lhs::Name '::' te::Type '::=' body::ProductionBody 
{
  top.pp = "aspect default production\n" ++ lhs.pp ++ "::" ++ te.pp ++ " ::=\n" ++ body.pp;

  top.defs = [];

  production attribute namedSig :: NamedSignature;
  namedSig = namedSignature(top.grammarName ++ ":default" ++ te.typerep.typeName, [], namedSignatureElement(lhs.name, te.typerep),
    annotationsForNonterminal(te.typerep, top.env));

  top.errors := te.errors ++ body.errors;

  local attribute fakedDefs :: [Def];
  fakedDefs = [defaultLhsDef(top.grammarName, lhs.location, lhs.name, te.typerep)];
  
  local attribute sigDefs :: [Def];
  sigDefs = addNewLexicalTyVars_ActuallyVariables(top.grammarName, top.location, te.lexicalTypeVariables);

  body.env = newScopeEnv(fakedDefs ++ sigDefs, top.env);
  body.signature = namedSig;
  body.blockContext = defaultAspectContext();

  body.downSubst = emptySubst();
  body.finalSubst = body.upSubst;

  top.setupInh := body.setupInh; -- Probably should be empty?
  top.initProd := "\t\t//ASPECT DEFAULT PRODUCTION for " ++ te.pp ++ "\n" ++ body.translation;
  top.valueWeaving := body.valueWeaving; -- Probably should be empty?
}

function defaultLhsDef
Def ::= sg::String sl::Location fn::String ty::TypeExp
{
  return valueDef(defaultEnvItem(defaultLhsDcl(sg,sl,fn,ty)));
}
abstract production defaultLhsDcl
top::DclInfo ::= sg::String sl::Location fn::String ty::TypeExp
{
  top.sourceGrammar = sg;
  top.sourceLocation = sl;
  top.fullName = fn;

  top.unparse = error("INTERNAL ERROR: default-lhs values should never appear in interface files");
  
  top.typerep = ty;
  
  top.refDispatcher = errorReference(_, location=_); -- Technically, we can make this lhsReference, but the semantics of that are stupid... (it would refer to the last (non-forwarding) production)
  top.defDispatcher = errorValueDef(_, _, location=_); -- TODO: be smarter about the error message
  top.defLHSDispatcher = defaultLhsDefLHS(_, location=_);
}
abstract production defaultLhsDefLHS
top::DefLHS ::= q::Decorated QName
{
  top.pp = q.pp;
  
  top.errors := if !null(top.defLHSattr.errors) || top.defLHSattr.attrDcl.isSynthesized then []
                else [err(q.location, "Cannot define inherited attribute '" ++ top.defLHSattr.pp ++ "' on the lhs '" ++ q.pp ++ "'")];

  top.typerep = q.lookupValue.typerep;

  top.translation = makeNTClassName(top.signature.outputElement.typerep.typeName) ++ ".defaultSynthesizedAttributes";
}

abstract production defaultAspectContext
top::BlockContext ::=
{
}

