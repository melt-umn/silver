grammar silver:compiler:extension:auto_ast;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:type;
--import silver:compiler:analysis:typechecking:core;


concrete production autoAstDcl
top::ProductionStmt ::= 'abstract' v::QName ';'
{
  top.unparse = "abstract " ++ v.unparse ++ ";";
  propagate env;

  local vty :: Type = v.lookupValue.typeScheme.typerep;
  
  local hasLoc :: Boolean =
    !null(vty.namedTypes) && head(vty.namedTypes).fst == "location";
  
  local elems :: [NamedSignatureElement] =
    filter(hasAst(_, top.env), top.frame.signature.inputElements);
    
  local inferredType :: Type =
    appTypes(
      functionType(length(elems), if hasLoc then ["location"] else []),
      map(astType(_, top.env), elems) ++
      (if hasLoc
       then [ nonterminalType("silver:core:Location", [], false)]
       else []) ++
      [astType(top.frame.signature.outputElement, top.env)]);
  
  top.errors <-
    if hasAst(top.frame.signature.outputElement, top.env) then []
    else [err(top.location, "This lhs '" ++ top.frame.signature.outputElement.elementName ++ "' does not have the 'silver:langutil:ast' attribute.")];
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  
  thread downSubst, upSubst on top, errCheck1, forward;

  errCheck1 = check(vty, inferredType);
  top.errors <-
    if !errCheck1.typeerror then []
    else [err(v.location, "Signature yields ast type " ++ errCheck1.rightpp ++ ", but the supplied ast constructor has type " ++ errCheck1.leftpp)];
  
  top.errors <-
    if hasLoc && null(getOccursDcl("silver:core:location", top.frame.lhsNtName, top.env))
    then [err(top.location, "Ast constructor wants 'location' but this nonterminal does not have a location")]
    else [];
  
  local lhsQName :: QName =
    qName(top.location, top.frame.signature.outputElement.elementName);
  local astQName :: QNameAttrOccur =
    qNameAttrOccur(qName(top.location, "silver:langutil:ast"), location=top.location);

  -- lhs.ast = v( (.ast) on elems, location=lhs.location if present);
  forwards to
    attributeDef(
      concreteDefLHS(lhsQName, location=top.location),
      '.',
      astQName,
      '=',
      mkFullFunctionInvocation(
        top.location,
        baseExpr(v, location=v.location),
        map(accessAst(_, top.location), elems),
        if hasLoc then
         [pair("location", 
            access(
              baseExpr(lhsQName, location=top.location),
              '.',
              qNameAttrOccur(qName(top.location, "location"), location=top.location),
              location=top.location))]
        else []),
      ';',
      location=top.location);
}


function hasAst
Boolean ::= ns::NamedSignatureElement  env::Decorated Env
{
  return isDecorable(ns.typerep, env) &&
    !null(getOccursDcl("silver:langutil:ast", ns.typerep.typeName, env));
}
function astType
Type ::= ns::NamedSignatureElement  env::Decorated Env
{
  local occursCheck :: [OccursDclInfo] =
    getOccursDcl("silver:langutil:ast", ns.typerep.typeName, env);
  
  return 
    if null(occursCheck) then errorType()
    else determineAttributeType(head(occursCheck), ns.typerep);
}

function accessAst
Expr ::= ns::NamedSignatureElement  l::Location
{
  return
    access(
      baseExpr(qName(l, ns.elementName), location=l),
      '.',
      qNameAttrOccur(qName(l, "silver:langutil:ast"), location=l),
      location=l);
}


