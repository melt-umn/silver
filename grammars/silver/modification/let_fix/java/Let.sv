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
top::Expr ::= l::Location  la::AssignExpr  e::Expr
{
  -- We need to create these nested locals, so we have no choice but to create a closure object so we can declare these things.
  local attribute closureExpr::String;
  closureExpr= "new common.Closure(context) { public final Object eval() { " 
    ++ la.let_translation
    ++ "return " ++ e.translation ++ "; } }";
    
  top.translation = "((" ++ finalType(top).transType ++ ")(" ++ closureExpr ++ ").eval())";

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
  top.let_translation = "final common.Thunk<" ++ finalTy.transType ++ "> " ++ makeLocalValueName(id.name) ++ " = " ++ "new common.Thunk<" ++ finalTy.transType ++ ">(" ++ wrapThunkText("context", e.translation) ++ ");\n";
}

aspect production lexicalLocalReference
top::Expr ::= q::Decorated QName
{
  top.translation = 
       if q.lookupValue.typerep.isDecorated && !finalType(top).isDecorated
       then "((" ++ finalType(top).transType ++ ")((common.DecoratedNode)" ++ makeLocalValueName(q.lookupValue.fullName) ++ ".eval()).undecorate())"
       else "((" ++ finalType(top).transType ++ ")(" ++ makeLocalValueName(q.lookupValue.fullName) ++ ".eval()))";

  top.lazyTranslation = 
       if !top.blockContext.lazyApplication then top.translation else
       if q.lookupValue.typerep.isDecorated && !finalType(top).isDecorated
       then "common.Closure.transformUndecorate(new common.Closure.FromThunk(" ++ makeLocalValueName(q.lookupValue.fullName) ++ "))"
       else "new common.Closure.FromThunk(" ++ makeLocalValueName(q.lookupValue.fullName) ++ ")";
}

