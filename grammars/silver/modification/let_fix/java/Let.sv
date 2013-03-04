grammar silver:modification:let_fix:java;
import silver:modification:let_fix;
import silver:translation:java:core;
import silver:definition:core;
import silver:util;
import silver:definition:env;
import silver:translation:java:type;
import silver:definition:type;
import silver:definition:type:syntax;

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  local finTy :: TypeExp = finalType(top);

  -- We need to create these nested locals, so we have no choice but to create a thunk object so we can declare these things.
  -- TODO: more specific types here would be nice!
  local attribute closureExpr::String;
  closureExpr= "new common.Thunk<Object>(context) { public final Object doEval() { " 
    ++ la.let_translation
    ++ "return " ++ e.translation ++ "; } }";
    
  top.translation = "((" ++ finTy.transType ++ ")(" ++ closureExpr ++ ").eval())";

  top.lazyTranslation = 
    if top.blockContext.lazyApplication
    then closureExpr
    else top.translation;
}

synthesized attribute let_translation :: String occurs on AssignExpr;

function makeLocalValueName
String ::= s::String
{
  return "__SV_LOCAL_" ++ makeIdName(s);
}

aspect production appendAssignExpr
top::AssignExpr ::= a1::AssignExpr a2::AssignExpr
{
  top.let_translation = a1.let_translation ++ a2.let_translation;
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::Type '=' e::Expr
{
  top.let_translation = makeSpecialLocalBinding(id.name, e.translation, finalTy.transType);
}

function makeSpecialLocalBinding
String ::= fn::String  et::String  ty::String
{
  -- TODO: more specific types here would be nice!
  return "final common.Thunk<Object> " ++ makeLocalValueName(fn) ++ " = " ++ wrapThunkText("context", et, "Object") ++ ";\n";
}

aspect production lexicalLocalReference
top::Expr ::= q::Decorated QName
{
  -- To account for a magic case where we generate a let expression with a type
  -- that is, for example, a ntOrDecTypeExp or something,
  -- we do final subst on q.lookupValue ALSO here...
  -- it could be isDecorated (ntOrDecTypeExp) that later gets specialized to undecorated
  -- and therefore we must be careful not to try to undecorate it again!
  local needsUndecorating :: Boolean =
    performSubstitution(q.lookupValue.typerep, top.finalSubst).isDecorated && !finalType(top).isDecorated;
  
  top.translation = 
    if needsUndecorating
    then "((" ++ finalType(top).transType ++ ")((common.DecoratedNode)" ++ makeLocalValueName(q.lookupValue.fullName) ++ ".eval()).undecorate())"
    else "((" ++ finalType(top).transType ++ ")(" ++ makeLocalValueName(q.lookupValue.fullName) ++ ".eval()))";

  top.lazyTranslation = 
    if !top.blockContext.lazyApplication then top.translation
    else if needsUndecorating
    then "common.Thunk.transformUndecorate(" ++ makeLocalValueName(q.lookupValue.fullName) ++ ")"
    else makeLocalValueName(q.lookupValue.fullName);
}

