grammar silver:modification:let_fix:java;
import silver:modification:let_fix;
import silver:translation:java:core;
import silver:definition:core;
import silver:util;
import silver:definition:env;
import silver:translation:java:type;
import silver:definition:type;
import silver:definition:type:syntax;

-- TODO: this is an area where we're creating Lazys in a nested fashion.
-- (i.e. at execution, rather than initialization, continuously)
-- Ideally, we wouldn't be doing this...

aspect production letp
top::Expr ::= 'let' la::LetAssigns 'in' e::Expr 'end'
{
  top.translation = "((" ++ finalType(top).transType ++ ")(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { " 
    ++ la.let_translation
    ++ "return " ++ forward.translation ++ "; } }).eval(context))";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

synthesized attribute let_translation :: String occurs on LetAssigns, AssignExpr;

function makeLocalValueName
String ::= s::String
{
  return "__SV_LOCAL_" ++ substitute("_", ":", s);
}

aspect production assigns
top::LetAssigns ::= ae::AssignExpr ',' list::LetAssigns
{
  top.let_translation = ae.let_translation ++ list.let_translation;
}

aspect production assignListSingle 
top::LetAssigns ::= ae::AssignExpr
{
  top.let_translation = ae.let_translation;
}

aspect production assignExpr
top::AssignExpr ::= id::Name '::' t::Type '=' e::Expr
{
  -- " ++ t.typerep.transType ++ "    TODO
  top.let_translation = "final common.Thunk " ++ makeLocalValueName(fName) ++ " = " ++ "new common.Thunk(" ++ wrapThunkText("context", e.translation) ++ ");\n";
}

aspect production lexicalLocalReference
top::Expr ::= q::Decorated QName
{
  top.translation = if q.lookupValue.typerep.isDecorated && !finalType(top).isDecorated
                    then "((" ++ finalType(top).transType ++ ")((common.DecoratedNode)" ++ makeLocalValueName(q.lookupValue.fullName) ++ ".eval()).undecorate())"
                    else "((" ++ finalType(top).transType ++ ")(" ++ makeLocalValueName(q.lookupValue.fullName) ++ ".eval()))";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication);
}

