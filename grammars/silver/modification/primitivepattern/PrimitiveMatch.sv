grammar silver:modification:primitivepattern;

imports silver:definition:core;
imports silver:definition:env;
imports silver:definition:type;

import silver:definition:type:syntax only typerepType, TypeExpr;
import silver:extension:patternmatching only Arrow_kwd, Vbar_kwd, ensureDecoratedExpr; -- TODO remove

import silver:translation:java:core;
import silver:translation:java:type;

-- Actually only used for lists, in this file... TODO
import silver:modification:let_fix only makeSpecialLocalBinding, lexicalLocalDef, End_kwd;
import silver:definition:flow:ast only noVertex;

import silver:extension:list; -- Oh no, this is a hack! TODO

terminal Match_kwd 'match' lexer classes {KEYWORD,RESERVED}; -- temporary!!!

nonterminal PrimPatterns with 
  config, grammarName, env, compiledGrammars, frame,
  location, unparse, errors,
  downSubst, upSubst, finalSubst,
  scrutineeType, returnType, translation;
nonterminal PrimPattern with 
  config, grammarName, env, compiledGrammars, frame,
  location, unparse, errors,
  downSubst, upSubst, finalSubst,
  scrutineeType, returnType, translation;

autocopy attribute scrutineeType :: Type;
autocopy attribute returnType :: Type;

propagate errors on PrimPatterns, PrimPattern;

concrete production matchPrimitiveConcrete
top::Expr ::= 'match' e::Expr 'return' t::TypeExpr 'with' pr::PrimPatterns 'else' '->' f::Expr 'end'
{
  top.unparse = "match " ++ e.unparse ++ " return " ++ t.unparse ++ " with " ++ pr.unparse ++ " else -> " ++ f.unparse ++ "end";

  forwards to matchPrimitive(e, t, pr, f, location=top.location);
}
abstract production matchPrimitive
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  top.unparse = "match " ++ e.unparse ++ " return " ++ t.unparse ++ " with " ++ pr.unparse ++ " else -> " ++ f.unparse ++ "end";

  e.downSubst = top.downSubst;
  forward.downSubst = e.upSubst;
  
  -- ensureDecoratedExpr is currently wrapping 'e' in 'exprRef' which suppresses errors
  -- TODO: the use of 'exprRef' should be reviewed, given that this error slipped through...
  top.errors := e.errors ++ forward.errors;
  
  forwards to matchPrimitiveReal(ensureDecoratedExpr(e), t, pr, f, location=top.location);
}
{--
 - @param e  The value to match against (should be DECORATED if it's nonterminal type at all)
 - @param t  The RETURN TYPE, explicitly.
 - @param pr  The cases of this match expression
 - @param f  The failure expression. (if the patterns don't match, evaluate to this.)
 -}
abstract production matchPrimitiveReal
top::Expr ::= e::Expr t::TypeExpr pr::PrimPatterns f::Expr
{
  top.unparse = "match " ++ e.unparse ++ " return " ++ t.unparse ++ " with " ++ pr.unparse ++ " else -> " ++ f.unparse ++ "end";
  
  propagate errors;
  top.typerep = t.typerep;
  
  {--
   - Invariant: if we were given an undecorated expression, it should have been
   - decorated by matchPrimitive before we got here, so we should either
   - have a decorated expr, or some other type.
   -}
  local attribute scrutineeType :: Type;
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
  local scrutineeFinalType :: Type = performSubstitution(scrutineeType, top.finalSubst);
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

  top.lazyTranslation = wrapThunk(top.translation, top.frame.lazyApplication); 
  -- TODO there seems to be an opportunity here to avoid an anon class somehow...
}

concrete production onePattern
top::PrimPatterns ::= p::PrimPattern
{
  top.unparse = p.unparse;
  
  top.translation = p.translation;
  
  p.downSubst = top.downSubst;
  top.upSubst = p.upSubst;
}
concrete production consPattern
top::PrimPatterns ::= p::PrimPattern '|' ps::PrimPatterns
{
  top.unparse = p.unparse ++ " | " ++ ps.unparse;
  
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
  top.unparse = qn.unparse ++ "(" ++ ns.unparse ++ ") -> " ++ e.unparse;

  local isGadt :: Boolean =
    case qn.lookupValue.typeScheme.typerep.outputType of
    -- If the lookup is successful, and it's a production type, and it 
    -- constructs a nonterminal that either:
    --  1. has a non-type-variable parameter (e.g. Expr<Boolean>)
    --  2. has fewer free variables than parameters (e.g. Eq<a a>)
    -- THEN it's a gadt.
    | nonterminalType(_, tvs) -> !isOnlyTyVars(tvs) || length(tvs) != length(setUnionTyVarsAll(map((.freeVariables), tvs)))
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
  top.unparse = qn.unparse ++ "(" ++ ns.unparse ++ ") -> " ++ e.unparse;
  
  local chk :: [Message] =
    if null(qn.lookupValue.dcls) || ns.varBinderCount == prod_type.arity then []
    else [err(qn.location, qn.name ++ " has " ++ toString(prod_type.arity) ++ " parameters but " ++ toString(ns.varBinderCount) ++ " patterns were provided")];
  
  top.errors <- qn.lookupValue.errors;

  -- Turns the existential variables existential
  local prod_type :: Type = skolemizeProductionType(qn.lookupValue.typeScheme);
  -- Note that we're going to check prod_type against top.scrutineeType shortly.
  -- This is where the type variables become unified.
  
  ns.bindingTypes = prod_type.inputTypes;
  ns.bindingIndex = 0;
  ns.bindingNames = if null(qn.lookupValue.dcls) then [] else qn.lookupValue.dcl.namedSignature.inputNames;
  ns.matchingAgainst = if null(qn.lookupValue.dcls) then nothing() else just(qn.lookupValue.dcl);
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(decoratedType(prod_type.outputType), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, qn.name ++ " has type " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
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
  top.unparse = qn.unparse ++ "(" ++ ns.unparse ++ ") -> " ++ e.unparse;
  
  local chk :: [Message] =
    if null(qn.lookupValue.dcls) || ns.varBinderCount == prod_type.arity then []
    else [err(qn.location, qn.name ++ " has " ++ toString(prod_type.arity) ++ " parameters but " ++ toString(ns.varBinderCount) ++ " patterns were provided")];
  
  top.errors <- qn.lookupValue.errors;

  local prod_type :: Type = fullySkolemizeProductionType(qn.lookupValue.typeScheme); -- that says FULLY. See the comments on that function.
  
  ns.bindingTypes = prod_type.inputTypes;
  ns.bindingIndex = 0;
  ns.bindingNames = if null(qn.lookupValue.dcls) then [] else qn.lookupValue.dcl.namedSignature.inputNames;
  ns.matchingAgainst = if null(qn.lookupValue.dcls) then nothing() else just(qn.lookupValue.dcl);
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = composeSubst(errCheck2.upSubst, top.finalSubst); -- part of the
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = composeSubst(errCheck2.upSubst, top.finalSubst); -- threading hack
  
  errCheck1 = check(decoratedType(prod_type.outputType), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, qn.name ++ " has type " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
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
  errCheck1.downSubst = composeSubst(top.finalSubst, produceRefinement(top.scrutineeType, decoratedType(prod_type.outputType)));
  e.downSubst = errCheck1.upSubst;
  errCheck2.downSubst = e.upSubst;
  -- Okay, now update the finalSubst....
  e.finalSubst = errCheck2.upSubst;
  -- Here ends the hack
  
  e.env = newScopeEnv(ns.defs, top.env);
  
  top.translation = "if(scrutineeNode instanceof " ++ makeClassName(qn.lookupValue.fullName) ++
    ") { " ++ ns.translation ++ " return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++ e.translation ++ "; }";
}

-- TODO: We currently provide the below for ease of translation from complex case exprs, but
-- we should really translate those to appropriate expressions, and not handle primitive types here

abstract production integerPattern
top::PrimPattern ::= i::Int_t '->' e::Expr
{
  top.unparse = i.lexeme ++ " -> " ++ e.unparse;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(intType(), top.scrutineeType);
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
abstract production floatPattern
top::PrimPattern ::= f::Float_t '->' e::Expr
{
  top.unparse = f.lexeme ++ " -> " ++ e.unparse;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(floatType(), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, f.lexeme ++ " is a " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];
  
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  
  errCheck1.downSubst = top.downSubst;
  e.downSubst = errCheck1.upSubst;
  errCheck2.downSubst = e.upSubst;
  top.upSubst = errCheck2.upSubst;

  top.translation = "if(scrutinee == " ++ f.lexeme ++ ") { return (" ++ performSubstitution(top.returnType, top.finalSubst).transType ++ ")" ++
                         e.translation ++ "; }";
}
abstract production stringPattern
top::PrimPattern ::= i::String_t '->' e::Expr
{
  top.unparse = i.lexeme ++ " -> " ++ e.unparse;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(stringType(), top.scrutineeType);
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
  top.unparse = i ++ " -> " ++ e.unparse;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(boolType(), top.scrutineeType);
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
  top.unparse = "nil() -> " ++ e.unparse;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(listType(freshType()), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, "nil matches lists but we're trying to match against " ++ errCheck1.rightpp)]
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
  top.unparse = "cons(" ++ h.unparse ++ ", " ++ t.unparse ++ ") -> " ++ e.unparse;

  local h_fName :: String = toString(genInt()) ++ ":" ++ h.name;
  local t_fName :: String = toString(genInt()) ++ ":" ++ t.name;
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  local elemType :: Type = freshType();
  
  errCheck1 = check(listType(elemType), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, "cons matches lists but we're trying to match against " ++ errCheck1.rightpp)]
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
    [lexicalLocalDef(top.grammarName, top.location, h_fName, elemType, noVertex(), []),
     lexicalLocalDef(top.grammarName, top.location, t_fName, top.scrutineeType, noVertex(), [])];
  
  e.env = newScopeEnv(consdefs, top.env);
  
  top.translation =
    let
      elemTrans :: String = performSubstitution(elemType, top.finalSubst).transType,
      listTrans :: String = performSubstitution(top.scrutineeType, top.finalSubst).transType
    in
      "if(!scrutineeIter.nil()) {" ++
      makeSpecialLocalBinding(h_fName, s"(${elemTrans})scrutinee.head()", elemTrans) ++
      makeSpecialLocalBinding(t_fName, s"(${listTrans})scrutinee.tail()", listTrans) ++
      "return " ++ e.translation ++ "; }"
    end;
}


