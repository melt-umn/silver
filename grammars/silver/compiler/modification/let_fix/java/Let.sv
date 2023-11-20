grammar silver:compiler:modification:let_fix:java;

import silver:compiler:modification:let_fix;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;
import silver:compiler:analysis:typechecking:core;

import silver:compiler:translation:java:core;
import silver:compiler:translation:java:type;

aspect production letp
top::Expr ::= la::AssignExpr  e::Expr
{
  -- We need to create these nested locals, so we have no choice but to create a thunk object so we can declare these things.
  local closureExpr :: String =
    s"new common.Thunk<${top.finalType.transType}>(new common.Thunk.Evaluable<${top.finalType.transType}>() { public final ${top.finalType.transType} eval() { ${la.let_translation} return ${e.translation}; } })";
    --TODO: java lambdas are bugged
    --s"new common.Thunk<${top.finalType.transType}>(() -> { ${la.let_translation} return ${e.translation};\n})";
  
  top.translation = s"${closureExpr}.eval()";

  top.lazyTranslation = 
    if top.frame.lazyApplication
    then closureExpr
    else top.translation;
  
  propagate initTransDecSites;
}

synthesized attribute let_translation :: String occurs on AssignExpr;
attribute initTransDecSites occurs on AssignExpr;
propagate initTransDecSites on AssignExpr;

fun makeLocalValueName String ::= s::String = "__SV_LOCAL_" ++ makeIdName(s);

aspect production appendAssignExpr
top::AssignExpr ::= a1::AssignExpr a2::AssignExpr
{
  top.let_translation = a1.let_translation ++ a2.let_translation;
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::TypeExpr '=' e::Expr
{
  -- We must use `finalSubst` in translation.
  -- "let abuse" means type variables can appear in `t`, and if we don't
  -- use `finalSubst` we get confusion with `ntOrDecType` not knowing
  -- it's being used undecorated later.
  local finalTy :: Type = performSubstitution(t.typerep, top.finalSubst);
  top.let_translation = makeSpecialLocalBinding(fName, e.translation, finalTy.transType);
}

fun makeSpecialLocalBinding String ::= fn::String  et::String  ty::String =
  s"final common.Thunk<${ty}> ${makeLocalValueName(fn)} = ${wrapThunkText(et, ty)};\n";

aspect production lexicalLocalReference
top::Expr ::= q::Decorated! QName  _ _ _
{
  -- To account for a magic case where we generate a let expression with a type
  -- that is, for example, a ntOrDecType or something,
  -- we do final subst on q.lookupValue ALSO here...
  -- it could be isDecorated (ntOrDecType) that later gets specialized to undecorated
  -- and therefore we must be careful not to try to undecorate it again!
  local needsUndecorating :: Boolean =
    performSubstitution(q.lookupValue.typeScheme.monoType, top.finalSubst).isDecorated && !top.finalType.isDecorated;
  
  top.translation = 
    if needsUndecorating
    then "((" ++ top.finalType.transType ++ ")((common.DecoratedNode)" ++ makeLocalValueName(q.lookupValue.fullName) ++ ".eval()).undecorate())"
    else "((" ++ top.finalType.transType ++ ")(" ++ makeLocalValueName(q.lookupValue.fullName) ++ ".eval()))";

  top.lazyTranslation = 
    if !top.frame.lazyApplication then top.translation
    else if needsUndecorating
    then "common.Thunk.transformUndecorate(" ++ makeLocalValueName(q.lookupValue.fullName) ++ ")"
    else makeLocalValueName(q.lookupValue.fullName);
  
  top.initTransDecSites := "";
}

