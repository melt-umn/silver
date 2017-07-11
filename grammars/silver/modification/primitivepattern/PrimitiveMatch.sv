grammar silver:modification:primitivepattern;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type;

import silver:definition:type:syntax only typerepType, Type;
import silver:extension:patternmatching only Arrow_kwd, Vbar_kwd, ensureDecoratedExpr; -- TODO remove

import silver:translation:java:core;
import silver:translation:java:type;

-- Actually only used for lists, in this file... TODO
import silver:modification:let_fix only makeSpecialLocalBinding, lexicalLocalDef, End_kwd;
import silver:definition:flow:ast only noVertex;

import silver:extension:list; -- Oh no, this is a hack! TODO

terminal Match_kwd 'match' lexer classes {KEYWORD,RESERVED}; -- temporary!!!

nonterminal PrimPatterns with 
  config, grammarName, env, compiledGrammars, signature, blockContext,
  location, pp, errors,
  downSubst, upSubst, finalSubst,
  scrutineeType, returnType, translation;
nonterminal PrimPattern with 
  config, grammarName, env, compiledGrammars, signature, blockContext,
  location, pp, errors,
  downSubst, upSubst, finalSubst,
  scrutineeType, returnType, translation;

autocopy attribute scrutineeType :: TypeExp;
autocopy attribute returnType :: TypeExp;
inherited attribute bindingTypes :: [TypeExp];


concrete production matchPrimitiveConcrete
top::Expr ::= 'match' e::Expr 'return' t::Type 'with' pr::PrimPatterns 'else' '->' f::Expr 'end'
{
  top.pp = "match " ++ e.pp ++ " return " ++ t.pp ++ " with " ++ pr.pp ++ " else -> " ++ f.pp ++ "end";

  forwards to matchPrimitive(e, t, pr, f, location=top.location);
}
abstract production matchPrimitive
top::Expr ::= e::Expr t::Type pr::PrimPatterns f::Expr
{
  top.pp = "match " ++ e.pp ++ " return " ++ t.pp ++ " with " ++ pr.pp ++ " else -> " ++ f.pp ++ "end";

  e.downSubst = top.downSubst;
  forward.downSubst = e.upSubst;
  
  -- ensureDecoratedExpr is currently wrapping 'e' in 'exprRef' which suppresses errors
  -- TODO: the use of 'exprRef' should be reviewed, given that this error slipped through...
  top.errors <- e.errors;
  
  forwards to matchPrimitiveReal(ensureDecoratedExpr(e), t, pr, f, location=top.location);
}
{--
 - @param e  The value to match against (should be DECORATED if it's nonterminal type at all)
 - @param t  The RETURN TYPE, explicitly.
 - @param pr  The cases of this match expression
 - @param f  The failure expression. (if the patterns don't match, evaluate to this.)
 -}
abstract production matchPrimitiveReal
top::Expr ::= e::Expr t::Type pr::PrimPatterns f::Expr
{
  top.pp = "match " ++ e.pp ++ " return " ++ t.pp ++ " with " ++ pr.pp ++ " else -> " ++ f.pp ++ "end";
  
  top.typerep = t.typerep;
  
  top.errors := e.errors ++ t.errors ++ pr.errors ++ f.errors;
  
  {--
   - Invariant: if we were given an undecorated expression, it should have been
   - decorated by matchPrimitive before we got here, so we should either
   - have a decorated expr, or some other type.
   -}
  local attribute scrutineeType :: TypeExp;
  scrutineeType = performSubstitution(e.typerep, e.upSubst);
  
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  errCheck2 = check(f.typerep, t.typerep);
  top.errors <-
    if errCheck2.typeerror
    then [err(top.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
    else [];

  -- ordinary threading: e, pr, f, errCheck2
  e.downSubst = top.downSubst;
  pr.downSubst = e.upSubst;
  f.downSubst = pr.upSubst;
  errCheck2.downSubst = f.upSubst;
  top.upSubst = errCheck2.upSubst;
  
  pr.scrutineeType = scrutineeType;
  pr.returnType = t.typerep;
  
  local resultTransType :: String = performSubstitution(t.typerep, top.finalSubst).transType;
  -- It is necessary to subst on scrutineeType here for the horrible reason that the type we're matching on
  -- may not be determined until we get to the constructor list. e.g. 'case error("lol") of pair(x,_) -> x end'
  -- which is legal, but if we don't do this will result in java translation errors (as the scrutinee will be
  -- type 'a' which is Object, which doesn't have .childAsIs for 'x'.)
  local scrutineeFinalType :: TypeExp = performSubstitution(scrutineeType, top.finalSubst);
  local scrutineeTransType :: String = scrutineeFinalType.transType;
  
  top.translation = 
    "new common.PatternLazy<" ++ scrutineeTransType ++ ", " ++ resultTransType ++ ">() { " ++
      "public final " ++ resultTransType ++ " eval(final common.DecoratedNode context, " ++ scrutineeTransType ++ " scrutineeIter) {" ++
        (if scrutineeFinalType.isDecorated
         then
          "while(true) {" ++
           "final " ++ scrutineeTransType ++ " scrutinee = scrutineeIter; " ++ -- our Lazy needs a final variable
           "final common.Node scrutineeNode = scrutinee.undecorate(); " ++
            pr.translation ++
           "if(!scrutineeIter.undecorate().hasForward()) break;" ++ 
           "scrutineeIter = scrutineeIter.forward();" ++
          "}"
         else
          "final " ++ scrutineeTransType ++ " scrutinee = scrutineeIter; " ++ -- ditto
           pr.translation) ++
        "return " ++ f.translation ++ ";" ++ 
    "}}.eval(context, (" ++ scrutineeTransType ++")" ++ e.translation ++ ")";

  top.lazyTranslation = wrapThunk(top.translation, top.blockContext.lazyApplication); 
  -- TODO there seems to be an opportunity here to avoid an anon class somehow...
}

concrete production onePattern
top::PrimPatterns ::= p::PrimPattern
{
  top.pp = p.pp;
  
  top.errors := p.errors;
  top.translation = p.translation;
  
  p.downSubst = top.downSubst;
  top.upSubst = p.upSubst;
}
concrete production consPattern
top::PrimPatterns ::= p::PrimPattern '|' ps::PrimPatterns
{
  top.pp = p.pp ++ " | " ++ ps.pp;
  
  top.errors := p.errors ++ ps.errors;
  top.translation = p.translation ++ "\nelse " ++ ps.translation;

  p.downSubst = top.downSubst;
  ps.downSubst = p.upSubst;
  top.upSubst = ps.upSubst;
}

-- TODO: Long term, I'd like to switch to having a PrimRule and rename PrimPatterns PrimRules.
-- However, we cannot do this yet, because the GADT case does CRAZY things with typing.
-- (Ideally, we'd be able to do those crazy things with constraints added to the
--  context there, instead...)

concrete production prodPattern
top::PrimPattern ::= qn::QName '(' ns::VarBinders ')' '->' e::Expr
{
  top.pp = qn.pp ++ "(" ++ ns.pp ++ ") -> " ++ e.pp;

  local isGadt :: Boolean =
    case qn.lookupValue.typerep.outputType of
    -- If the lookup is successful, and it's a production type, and it 
    -- constructs a nonterminal that either:
    --  1. has a non-type-variable parameter (e.g. Expr<Boolean>)
    --  2. has fewer free variables than parameters (e.g. Eq<a a>)
    -- THEN it's a gadt.
    | nonterminalTypeExp(_, tvs) -> !isOnlyTyVars(tvs) || length(tvs) != length(setUnionTyVarsAll(map((.freeVariables), tvs)))
    | _ -> false
    end;
  
  -- The reason we do it this way is because the threading of type information
  -- around is very different, and I don't want to confuse myself while I'm writing
  -- the code. After it works, perhaps these can be merged into one non-forwarding
  -- production, once the code is understood fully.
  forwards to if isGadt
              then prodPatternGadt(qn, ns, e, location=top.location)
              else prodPatternNormal(qn, ns, e, location=top.location);
}
abstract production prodPatternNormal
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.pp = qn.pp ++ "(" ++ ns.pp ++ ") -> " ++ e.pp;
  
  top.errors := qn.lookupValue.errors ++ ns.errors ++ e.errors;

  -- Turns the existential variables existential
  local attribute prod_type :: TypeExp;
  prod_type = skolemizeProductionType(qn.lookupValue.typerep);
  -- Note that we're going to check prod_type against top.scrutineeType shortly.
  -- This is where the type variables become unified.
  
  ns.bindingTypes = prod_type.inputTypes;
  ns.bindingIndex = 0;
  
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
  
  -- Thread NORMALLY! YAY!
  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  errCheck2.downSubst = e.upSubst;
  top.upSubst = errCheck2.upSubst;
  
  e.env = newScopeEnv(ns.defs, top.env);
  
  top.translation = "if(scrutineeNode instanceof " ++ makeClassName(qn.lookupValue.fullName) ++
    ") { " ++ ns.translation ++ " return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++ e.translation ++ "; }";
}

abstract production prodPatternGadt
top::PrimPattern ::= qn::Decorated QName  ns::VarBinders  e::Expr
{
  top.pp = qn.pp ++ "(" ++ ns.pp ++ ") -> " ++ e.pp;
  
  top.errors := qn.lookupValue.errors ++ ns.errors ++ e.errors;

  local attribute prod_type :: TypeExp;
  prod_type = fullySkolemizeProductionType(qn.lookupValue.typerep); -- that says FULLY. See the comments on that function.
  
  ns.bindingTypes = prod_type.inputTypes;
  ns.bindingIndex = 0;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = composeSubst(errCheck2.upSubst, top.finalSubst); -- part of the
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = composeSubst(errCheck2.upSubst, top.finalSubst); -- threading hack
  
  errCheck1 = check(decoratedTypeExp(prod_type.outputType), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, qn.pp ++ " has type " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];
  
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  
  -- For GADTs, threading gets a bit weird.
  -- TODO: we SHOULD check that the "base type" is accurate for the pattern / scrutineeType first.
  --       but for now for simplicity, we avoid that.
  -- So for now, we're just skipping over this case entirely:
  top.upSubst = top.downSubst;
  
  -- AFTER everything is done elsewhere, we come back with finalSubst, and we produce the refinement, and thread THAT through everything.
  errCheck1.downSubst = composeSubst(top.finalSubst, produceRefinement(top.scrutineeType, decoratedTypeExp(prod_type.outputType)));
  e.downSubst = errCheck1.upSubst;
  errCheck2.downSubst = e.upSubst;
  -- Okay, now update the finalSubst....
  e.finalSubst = errCheck2.upSubst;
  -- Here ends the hack
  
  e.env = newScopeEnv(ns.defs, top.env);
  
  top.translation = "if(scrutineeNode instanceof " ++ makeClassName(qn.lookupValue.fullName) ++
    ") { " ++ ns.translation ++ " return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++ e.translation ++ "; }";
}

abstract production integerPattern
top::PrimPattern ::= i::Int_t '->' e::Expr
{
  top.pp = i.lexeme ++ " -> " ++ e.pp;
  
  top.errors := e.errors;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(intTypeExp(), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, i.lexeme ++ " is an " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];
  
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  
  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  errCheck2.downSubst = e.upSubst;
  top.upSubst = errCheck2.upSubst;

  top.translation = "if(scrutinee == " ++ i.lexeme ++ ") { return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++
                         e.translation ++ "; }";
}
abstract production stringPattern
top::PrimPattern ::= i::String_t '->' e::Expr
{
  top.pp = i.lexeme ++ " -> " ++ e.pp;
  
  top.errors := e.errors;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(stringTypeExp(), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, i.lexeme ++ " is a " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];
  
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  
  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  errCheck2.downSubst = e.upSubst;
  top.upSubst = errCheck2.upSubst;

  top.translation = "if(scrutinee.equals(" ++ i.lexeme ++ ")) { return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++
                         e.translation ++ "; }";
}
abstract production booleanPattern
top::PrimPattern ::= i::String '->' e::Expr
{
  top.pp = i ++ " -> " ++ e.pp;
  
  top.errors := e.errors;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(boolTypeExp(), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, i ++ " is a " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];
  
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  
  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  errCheck2.downSubst = e.upSubst;
  top.upSubst = errCheck2.upSubst;

  top.translation = "if(scrutinee == " ++ i ++ ") { return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++
                         e.translation ++ "; }";
}
abstract production nilPattern
top::PrimPattern ::= e::Expr
{
  top.pp = "nil() -> " ++ e.pp;
  
  top.errors := e.errors;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(listTypeExp(freshType()), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, "nil() constructs type " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];
  
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  
  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  errCheck2.downSubst = e.upSubst;
  top.upSubst = errCheck2.upSubst;

  top.translation = "if(scrutinee.nil()) { return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++
                         e.translation ++ "; }";
}
abstract production conslstPattern
top::PrimPattern ::= h::Name t::Name e::Expr
{
  top.pp = "cons(" ++ h.pp ++ ", " ++ t.pp ++ ") -> " ++ e.pp;
  
  top.errors := e.errors;

  local h_fName :: String = toString(genInt()) ++ ":" ++ h.name;
  local t_fName :: String = toString(genInt()) ++ ":" ++ t.name;
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  local elemType :: TypeExp = freshType();
  
  errCheck1 = check(listTypeExp(elemType), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, "cons() constructs type " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];
  
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  
  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  errCheck2.downSubst = e.upSubst;
  top.upSubst = errCheck2.upSubst;
  
  local consdefs :: [Def] =
    [lexicalLocalDef(top.grammarName, top.location, h_fName, elemType, noVertex(), []), -- TODO these deps??
     lexicalLocalDef(top.grammarName, top.location, t_fName, top.scrutineeType, noVertex(), [])];
  
  e.env = newScopeEnv(consdefs, top.env);
  
  top.translation = "if(!scrutineeIter.nil()) {" ++
  makeSpecialLocalBinding(h_fName, "scrutinee.head()", performSubstitution(elemType, top.finalSubst).transType) ++
  makeSpecialLocalBinding(t_fName, "scrutinee.tail()", performSubstitution(top.scrutineeType, top.finalSubst).transType) ++
  "return " ++ e.translation ++ "; }";
}


