grammar silver:modification:patternmatching;

import silver:definition:core;
import silver:definition:env;
import silver:definition:concrete_syntax;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:analysis:typechecking:core;
import silver:analysis:typechecking;

import silver:translation:java:core;
import silver:translation:java:type;

import silver:modification:let_fix;
import silver:modification:let_fix:java;

terminal Match_kwd 'match' lexer classes {KEYWORD}; -- temporary!!!

nonterminal PrimPatterns with location, pp, file, grammarName, env, signature, errors, downSubst, upSubst, finalSubst, blockContext
                            , scrutineeType, returnType, translation;
nonterminal PrimPattern  with location, pp, file, grammarName, env, signature, errors, downSubst, upSubst, finalSubst, blockContext
                            , scrutineeType, returnType, translation;

nonterminal VarBinders with location, pp, file, grammarName, env, signature, errors, downSubst, upSubst, finalSubst, blockContext
                          , bindingTypes, bindingIndex, nameTrans, valueTrans, defs;
nonterminal VarBinder  with location, pp, file, grammarName, env, signature, errors, downSubst, upSubst, finalSubst, blockContext
                          , bindingType, bindingIndex, nameTrans, valueTrans, defs;

autocopy attribute scrutineeType :: TypeExp;
autocopy attribute returnType :: TypeExp;
inherited attribute bindingTypes :: [TypeExp];
inherited attribute bindingType :: TypeExp;
inherited attribute bindingIndex :: Integer;

concrete production matchPrimitive
top::Expr ::= 'match' e::Expr 'return' t::Type 'with' pr::PrimPatterns 'else' '->' f::Expr 'end'
{
  top.pp = "match " ++ e.pp ++ " return " ++ t. pp ++ " with " ++ pr.pp ++ " else -> " ++ f.pp ++ "end";
  top.location = loc(top.file, $1.line, $1.column);
  
  top.typerep = t.typerep;
  
  top.errors := e.errors ++ t.errors ++ pr.errors ++ f.errors;
  
  local attribute scrutineeType :: TypeExp;
  scrutineeType = performSubstitution(e.typerep, e.upSubst);
  
  top.errors <- if !scrutineeType.isDecorated
                then [err(top.location, "match scrutinee should be decorated, instead it's type " ++ prettyType(scrutineeType))]
                else [];
  
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck2 = check(f.typerep, t.typerep);
  top.errors <- if errCheck2.typeerror
                then [err(f.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];

  e.downSubst = top.downSubst;
  pr.downSubst = e.upSubst;
  f.downSubst = pr.upSubst;
  errCheck2.downSubst = f.upSubst;
  top.upSubst = errCheck2.upSubst;
  
  pr.scrutineeType = scrutineeType;
  pr.returnType = t.typerep;
  
  top.isAppReference = false;
  top.appReference = error("mP demanded appreference");
  top.translation = "(" ++ t.typerep.transType ++ ")common.PatternLazy.runPattern(context, " ++ e.translation ++ ", " ++ wrapPatternLazy(pr) ++ ", " ++ wrapLazy(f) ++ ")";
}

function wrapPatternLazy
String ::= p::Decorated PrimPatterns
{
  return "new common.PatternLazy() { public final Object eval(final common.DecoratedNode context, final common.DecoratedNode scrutinee) { final common.Node scrutineeNode = scrutinee.undecorate();\n" ++ p.translation ++ "; throw new common.exceptions.PatternMatchFailure(\"Nonexhuastive match in " ++ p.location.fileName ++ " on line " ++ toString(p.location.line) ++ "\"); } }";
}

concrete production onePattern
top::PrimPatterns ::= p::PrimPattern
{
  top.pp = p.pp;
  top.location = p.location;
  
  top.errors := p.errors;
  top.translation = p.translation;
  
  p.downSubst = top.downSubst;
  top.upSubst = p.upSubst;
}
concrete production consPattern
top::PrimPatterns ::= p::PrimPattern '|' ps::PrimPatterns
{
  top.pp = p.pp ++ " | " ++ ps.pp;
  top.location = loc(top.file, $2.line, $2.column);
  
  top.errors := p.errors ++ ps.errors;
  top.translation = p.translation ++ "\nelse " ++ ps.translation;

  p.downSubst = top.downSubst;
  ps.downSubst = p.upSubst;
  top.upSubst = ps.upSubst;
}

concrete production prodPattern
top::PrimPattern ::= qn::QName '(' ns::VarBinders ')' '->' e::Expr
{
  top.pp = qn.pp ++ "(" ++ ns.pp ++ ") -> " ++ e.pp;
  top.location = loc(top.file, $5.line, $5.column);
  
  top.errors := qn.lookupValue.errors ++ ns.errors ++ e.errors;

  local attribute prod_type :: TypeExp;
  prod_type = freshenCompletely(qn.lookupValue.typerep); -- TODO: busted. existential types. freshen all vars in outputType, but skolemize all others.

  ns.bindingTypes = prod_type.inputTypes;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(decoratedTypeExp(prod_type.outputType), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, qn.pp ++ " has type " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];
  
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  
  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  errCheck2.downSubst = e.upSubst;
  top.upSubst = errCheck2.upSubst;
  
  e.env = newScopeEnv(ns.defs, top.env);
  
  top.translation = "if(scrutineeNode instanceof " ++ makeClassName(qn.lookupValue.fullName) ++
    ") { return (common.Util.let(context, new String[]{"
        ++ implode(", ", ns.nameTrans) ++ "}, " ++ "new common.Lazy[]{"
        ++ implode(", ", ns.valueTrans) ++ "}, " ++ wrapLazy(e) ++ "))" ++ "; }";
}

concrete production oneVarBinder
top::VarBinders ::= v::VarBinder
{
  top.pp = v.pp;
  top.location = v.location;
  top.nameTrans = v.nameTrans;
  top.valueTrans = v.valueTrans;
  top.defs = v.defs;
  top.errors := v.errors;
}
concrete production consVarBinder
top::VarBinders ::= v::VarBinder ',' vs::VarBinders
{
  top.pp = v.pp ++ ", " ++ vs.pp;
  top.location = v.location;
  top.nameTrans = v.nameTrans ++ vs.nameTrans;
  top.valueTrans = v.valueTrans ++ vs.valueTrans;
  top.defs = appendDefs(v.defs, vs.defs);
  top.errors := v.errors ++ vs.errors;
}
concrete production nilVarBinder
top::VarBinders ::= -- technically a bug, but forget it for now
{
  top.pp = "";
  top.location = loc("??", -1, -2);
  top.nameTrans = [];
  top.valueTrans = [];
  top.defs = emptyDefs();
  top.errors := [];
}

concrete production varVarBinder
top::VarBinder ::= n::Name
{
  top.pp = n.pp;
  top.location = n.location;
  
  production attribute fName :: String;
  fName = top.signature.fullName ++ ":local:" ++ n.name;

  local attribute ty :: TypeExp;
  ty = if top.bindingType.isDecorable
       then decoratedTypeExp(top.bindingType)
       else top.bindingType;

  top.defs = addLexicalLocalDcl(top.grammarName, n.location, fName, ty, emptyDefs());

  top.nameTrans = ["\"" ++ fName ++ "\""];
  -- TODO: this demands Lazys, but values here would make more sense?
  top.valueTrans = ["new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (" ++ ty.transType ++ ")scrutinee." ++ 
     (if top.bindingType.isDecorable
     then "childDecorated("
     else "childAsIs(")
     ++ toString(top.bindingIndex) ++ "); } }"];
     
  top.errors := [];
}
concrete production ignoreVarBinder
top::VarBinder ::= '_'
{
  top.pp = "_";
  top.location = loc(top.file, $1.line, $1.column);
  top.defs = emptyDefs();
  top.nameTrans = [];
  top.valueTrans = [];
  top.errors := [];
}


