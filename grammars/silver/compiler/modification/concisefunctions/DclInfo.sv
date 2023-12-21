grammar silver:compiler:modification:concisefunctions;

abstract production shortFunDcl
top::ValueDclInfo ::= ns::NamedSignature
{
  propagate isEqual;
  top.fullName = ns.fullName;
  
  top.namedSignature = ns;
  top.typeScheme = ns.typeScheme;

  top.refDispatcher = functionReference;  -- Same API
  top.defDispatcher = errorValueDef; -- should be impossible (never in scope at production level?)
  top.defLHSDispatcher = errorDefLHS; -- ditto
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}

abstract production shortFunParamDcl
top::ValueDclInfo ::= fn::String ty::Type
{
  top.fullName = fn;
  propagate isEqual;

  top.typeScheme = monoType(ty);

  top.refDispatcher = shortFunParamReference;
  top.defDispatcher = errorValueDef; -- should be impossible (never in scope at production level?)
  top.defLHSDispatcher = errorDefLHS; -- ditto
  top.transDefLHSDispatcher = errorTransAttrDefLHS;
}

function shortFunDef
Def ::= sg::String sl::Location ns::NamedSignature
{
  return valueDef(defaultEnvItem(shortFunDcl(ns,sourceGrammar=sg,sourceLocation=sl)));
}

function shortFunParamDef
Def ::= sg::String sl::Location fn::String ty::Type
{
  return valueDef(defaultEnvItem(shortFunParamDcl(fn,ty,sourceGrammar=sg,sourceLocation=sl)));
}
