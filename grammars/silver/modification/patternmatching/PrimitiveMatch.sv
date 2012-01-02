grammar silver:modification:patternmatching;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax only typerepType, Type;
import silver:analysis:typechecking;
import silver:analysis:typechecking:core only upSubst, downSubst, finalSubst;

import silver:translation:java:core;
import silver:translation:java:type;

import silver:modification:let_fix;
import silver:modification:let_fix:java;

import silver:extension:list; -- Oh no, this is a hack! TODO

terminal Match_kwd 'match' lexer classes {KEYWORD}; -- temporary!!!

nonterminal PrimPatterns with location, pp, file, grammarName, env, signature, errors, downSubst, upSubst, finalSubst, blockContext
                            , scrutineeType, returnType, translation;
nonterminal PrimPattern  with location, pp, file, grammarName, env, signature, errors, downSubst, upSubst, finalSubst, blockContext
                            , scrutineeType, returnType, translation;

nonterminal VarBinders with location, pp, file, grammarName, env, signature, errors, downSubst, upSubst, finalSubst, blockContext
                          , bindingTypes, bindingIndex, defs, let_translation;
nonterminal VarBinder  with location, pp, file, grammarName, env, signature, errors, downSubst, upSubst, finalSubst, blockContext
                          , bindingType, bindingIndex, defs, let_translation;

autocopy attribute scrutineeType :: TypeExp;
autocopy attribute returnType :: TypeExp;
inherited attribute bindingTypes :: [TypeExp];
inherited attribute bindingType :: TypeExp;
inherited attribute bindingIndex :: Integer;

concrete production matchPrimitiveConcrete
top::Expr ::= 'match' e::Expr 'return' t::Type 'with' pr::PrimPatterns 'else' '->' f::Expr 'end'
{
  forwards to matchPrimitive(loc(top.file, $1.line, $1.column), e, t, pr, f);
}
abstract production matchPrimitive
top::Expr ::= ll::Decorated Location e::Expr t::Type pr::PrimPatterns f::Expr
{
  e.downSubst = top.downSubst;
  forward.downSubst = e.upSubst;
  
  forwards to matchPrimitiveReal(ll, ensureDecoratedExpr(e), t, pr, f);
}
abstract production matchPrimitiveReal
top::Expr ::= ll::Decorated Location e::Expr t::Type pr::PrimPatterns f::Expr
{
  top.pp = "match " ++ e.pp ++ " return " ++ t.pp ++ " with " ++ pr.pp ++ " else -> " ++ f.pp ++ "end";
  top.location = ll;
  
  top.typerep = t.typerep;
  
  top.errors := e.errors ++ t.errors ++ pr.errors ++ f.errors;
  
  local attribute scrutineeType :: TypeExp;
  scrutineeType = performSubstitution(e.typerep, e.upSubst);
  
--  top.errors <- if !scrutineeType.isDecorated
--                then [err(top.location, "match scrutinee should be decorated, instead it's type " ++ prettyType(scrutineeType))]
--                else [];
  
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
  
  top.translation = 
    "new common.PatternLazy<" ++ scrutineeType.transType ++ ", " ++ t.typerep.transType ++ ">() { " ++
      "public final " ++ t.typerep.transType ++ " eval(final common.DecoratedNode context, " ++ scrutineeType.transType ++ " scrutineeIter) {" ++
        (if scrutineeType.isDecorated
         then
          "while(scrutineeIter != null) {" ++
           "final " ++ scrutineeType.transType ++ " scrutinee = scrutineeIter; " ++ -- dumb, but to get final to work out for Lazys & shizzle...
           "final common.Node scrutineeNode = scrutinee.undecorate(); " ++
            pr.translation ++
           "scrutineeIter = scrutineeIter.forward();" ++
          "}"
         else
          "final " ++ scrutineeType.transType ++ " scrutinee = scrutineeIter; " ++ -- dumb, but to get final to work out for Lazys & shizzle...
           pr.translation) ++
        "return " ++ f.translation ++ ";" ++ 
    "}}.eval(context, (" ++ scrutineeType.transType ++")" ++ e.translation ++ ")";

  top.lazyTranslation = wrapClosure(top.translation, top.blockContext.lazyApplication); 
  -- TODO there seems to be an opportunity here to avoid an anon class somehow...
  
  forwards to defaultExpr();
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
  top.location = qn.location;
  
  top.errors := qn.lookupValue.errors ++ ns.errors ++ e.errors;

  local attribute prod_type :: TypeExp;
  prod_type = skolemizeTypeExp(qn.lookupValue.typerep);
  
  ns.bindingTypes = prod_type.inputTypes;
  ns.bindingIndex = 0;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = composeSubst(errCheck2.upSubst, top.finalSubst);
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = composeSubst(errCheck2.upSubst, top.finalSubst);
  
  errCheck1 = check(decoratedTypeExp(prod_type.outputType), top.scrutineeType);
  top.errors <- if errCheck1.typeerror
                then [err(top.location, qn.pp ++ " has type " ++ errCheck1.leftpp ++ " but we're trying to match against " ++ errCheck1.rightpp)]
                else [];
  
  errCheck2 = check(e.typerep, top.returnType);
  top.errors <- if errCheck2.typeerror
                then [err(e.location, "pattern expression should have type " ++ errCheck2.rightpp ++ " instead it has type " ++ errCheck2.leftpp)]
                else [];
  
  -- Pass us by!! We're cheating here for a bit!
  top.upSubst = top.downSubst;
  -- Okay, now come back after checking everything else, let's check me... wheee
  errCheck1.downSubst = composeSubst(top.finalSubst, produceRefinement(top.scrutineeType, decoratedTypeExp(prod_type.outputType)));
  e.downSubst = errCheck1.upSubst;
  errCheck2.downSubst = e.upSubst;
  -- Okay, now update the finalSubst....
  e.finalSubst = errCheck2.upSubst;
  -- Hack over. Solved by pushing type information down... TODO
  
  e.env = newScopeEnv(ns.defs, top.env);
  
  top.translation = "if(scrutineeNode instanceof " ++ makeClassName(qn.lookupValue.fullName) ++
    ") { " ++ ns.let_translation ++ " return (" ++ top.returnType.transType ++ ")" ++ e.translation ++ "; }";
}

abstract production integerPattern
top::PrimPattern ::= i::Int_t '->' e::Expr
{
  top.pp = i.lexeme ++ " -> " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);
  
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

  top.translation = "if(scrutinee == " ++ i.lexeme ++ ") { return (" ++ top.returnType.transType ++ ")" ++
                         e.translation ++ "; }";
}
abstract production stringPattern
top::PrimPattern ::= i::String_t '->' e::Expr
{
  top.pp = i.lexeme ++ " -> " ++ e.pp;
  top.location = loc(top.file, $1.line, $1.column);
  
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

  top.translation = "if(scrutinee.equals(" ++ i.lexeme ++ ")) { return (" ++ top.returnType.transType ++ ")" ++
                         e.translation ++ "; }";
}
abstract production booleanPattern
top::PrimPattern ::= i::String '->' e::Expr
{
  top.pp = i ++ " -> " ++ e.pp;
  top.location = e.location;
  
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

  top.translation = "if(scrutinee == " ++ i ++ ") { return (" ++ top.returnType.transType ++ ")" ++
                         e.translation ++ "; }";
}
abstract production nilPattern
top::PrimPattern ::= e::Expr
{
  top.pp = "nil() -> " ++ e.pp;
  top.location = e.location;
  
  top.errors := e.errors;
  
  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  
  errCheck1 = check(listTypeExp(errorType()), top.scrutineeType);
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

  top.translation = "if(scrutinee.nil()) { return (" ++ top.returnType.transType ++ ")" ++
                         e.translation ++ "; }";
}
abstract production conslstPattern
top::PrimPattern ::= h::String t::String e::Expr
{
  top.pp = "cons(" ++ h ++ ", " ++ t ++ ") -> " ++ e.pp;
  top.location = e.location;
  
  top.errors := e.errors;

  local attribute errCheck1 :: TypeCheck; errCheck1.finalSubst = top.finalSubst;
  local attribute errCheck2 :: TypeCheck; errCheck2.finalSubst = top.finalSubst;
  local attribute elemType :: TypeExp;
  elemType = errorType();
  
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
  
  local attribute hFName :: String; hFName = top.signature.fullName ++ ":local:" ++ h;
  local attribute tFName :: String; tFName = top.signature.fullName ++ ":local:" ++ t;
  local attribute consdefs :: Defs; -- TODO: eliminate :local: garbage later.
  consdefs = addLexicalLocalDcl(top.grammarName, top.location, hFName, elemType, 
             addLexicalLocalDcl(top.grammarName, top.location, tFName, top.scrutineeType, emptyDefs()));
  
  e.env = newScopeEnv(consdefs, top.env);
  
  top.translation = "if(!scrutineeIter.nil()) {" ++
  makeSpecialLocalBinding(hFName, "scrutinee.head()", performSubstitution(elemType, top.finalSubst).transType) ++
  makeSpecialLocalBinding(tFName, "scrutinee.tail()", performSubstitution(top.scrutineeType, top.finalSubst).transType) ++
  "return " ++ e.translation ++ "; }";
}
--------------------------------------------------------------------------------
concrete production oneVarBinder
top::VarBinders ::= v::VarBinder
{
  top.pp = v.pp;
  top.location = v.location;
  top.defs = v.defs;
  top.errors := v.errors;

  top.let_translation = v.let_translation;

  v.bindingIndex = top.bindingIndex;
  v.bindingType = if null(top.bindingTypes)
                  then errorType()
                  else head(top.bindingTypes);
  
  top.errors <- if null(top.bindingTypes)
                then [err(top.location, "More patterns than expected in pattern list")]
                else [];
  top.errors <- if length(top.bindingTypes) > 1
                then [err(top.location, "Fewer patterns than expected in pattern list")]
                else [];
}
concrete production consVarBinder
top::VarBinders ::= v::VarBinder ',' vs::VarBinders
{
  top.pp = v.pp ++ ", " ++ vs.pp;
  top.location = v.location;
  top.defs = appendDefs(v.defs, vs.defs);
  top.errors := v.errors ++ vs.errors;

  top.let_translation = v.let_translation ++ vs.let_translation;

  v.bindingIndex = top.bindingIndex;
  vs.bindingIndex = top.bindingIndex + 1;

  v.bindingType = if null(top.bindingTypes)
                  then errorType()
                  else head(top.bindingTypes);
  vs.bindingTypes = if null(top.bindingTypes)
                  then []
                  else tail(top.bindingTypes);
}
concrete production nilVarBinder
top::VarBinders ::= Epsilon_For_Location  -- technically a bug, but forget it for now
{
  top.pp = "";
  top.location = loc(top.file, $1.line, $1.column);
  top.defs = emptyDefs();
  top.errors := [];
  
  top.let_translation = "";

  top.errors <- if !null(top.bindingTypes)
                then [err(top.location, "Fewer patterns than expected in pattern list")]
                else [];
}

concrete production varVarBinder
top::VarBinder ::= n::Name
{
  top.pp = n.pp;
  top.location = n.location;
  
  production attribute fName :: String;
  fName = top.signature.fullName ++ ":local:" ++ n.name; -- TODO: eliminate this :local: garbage later

  local attribute ty :: TypeExp;
  ty = if top.bindingType.isDecorable
       then decoratedTypeExp(top.bindingType)
       else top.bindingType;

  top.defs = addLexicalLocalDcl(top.grammarName, n.location, fName, ty, emptyDefs());

  top.let_translation = makeSpecialLocalBinding(fName, 
             "(" ++ ty.transType ++ ")scrutinee." ++ 
                (if top.bindingType.isDecorable
                 then "childDecorated("
                 else "childAsIs(")
             ++ toString(top.bindingIndex) ++ ")", ty.transType);
  
  top.errors := []; -- TODO: check for rebinding? or not perhaps...
}
concrete production ignoreVarBinder
top::VarBinder ::= '_'
{
  top.pp = "_";
  top.location = loc(top.file, $1.line, $1.column);
  top.defs = emptyDefs();
  top.errors := [];
  top.let_translation = "";
}


function makeSpecialLocalBinding
String ::= fn::String  et::String  ty::String
{
  return "final common.Thunk<" ++ ty ++ "> " ++ makeLocalValueName(fn) ++ " = " ++ "new common.Thunk<" ++ ty ++ ">(" ++ wrapThunkText("context", et) ++ ");\n";
}

-----

function skolemizeTypeExp
TypeExp ::= te::TypeExp
{
  return performSubstitution(te, zipVarsIntoSkolemizedSubstitution(te.freeVariables, freshTyVars(length(te.freeVariables))));
}


--- This is unification, EXCEPT that skolem constants behave like type variables!

inherited attribute refineWith :: TypeExp occurs on TypeExp;
synthesized attribute refine :: Substitution occurs on TypeExp;

aspect production varTypeExp
top::TypeExp ::= tv::TyVar
{
  top.refine = case top.refineWith of
               varTypeExp(j) -> if tyVarEqual(tv, j)
                                then emptySubst()
                                else subst( tv, top.refineWith )
             | _ -> if containsTyVar(tv, top.refineWith.freeVariables)
                    then errorSubst("Infinite type! Tried to refine with " ++ prettyType(top.refineWith))
                    else subst(tv, top.refineWith)
              end;
}

aspect production skolemTypeExp
top::TypeExp ::= tv::TyVar
{
  top.refine = case top.refineWith of
               skolemTypeExp(j) -> if tyVarEqual(tv, j)
                                then emptySubst()
                                else subst( tv, top.refineWith )
             | _ -> if containsTyVar(tv, top.refineWith.freeVariables)
                    then errorSubst("Infinite type! Tried to refine with " ++ prettyType(top.refineWith))
                    else subst(tv, top.refineWith)
              end;
}

aspect production intTypeExp
top::TypeExp ::=
{
  top.refine = case top.refineWith of
               intTypeExp() -> emptySubst()
             | _ -> errorSubst("Tried to refine Integer with " ++ prettyType(top.refineWith))
              end;
}

aspect production boolTypeExp
top::TypeExp ::=
{
  top.refine = case top.refineWith of
               boolTypeExp() -> emptySubst()
             | _ -> errorSubst("Tried to refine Boolean with " ++ prettyType(top.refineWith))
              end;
}

aspect production floatTypeExp
top::TypeExp ::=
{
  top.refine = case top.refineWith of
               floatTypeExp() -> emptySubst()
             | _ -> errorSubst("Tried to refine Float with " ++ prettyType(top.refineWith))
              end;
}

aspect production stringTypeExp
top::TypeExp ::=
{
  top.refine = case top.refineWith of
               stringTypeExp() -> emptySubst()
             | _ -> errorSubst("Tried to refine Boolean with " ++ prettyType(top.refineWith))
              end;
}

aspect production nonterminalTypeExp
top::TypeExp ::= fn::String params::[TypeExp]
{
  top.refine = case top.refineWith of
               nonterminalTypeExp(ofn, op) -> if fn == ofn
                                            then refineAll( params, op )
                                            else errorSubst("Tried to refine conflicting nonterminal types " ++ fn ++ " and " ++ ofn)
             | _ -> errorSubst("Tried to refine nonterminal type " ++ fn ++ " with " ++ prettyType(top.refineWith))
              end;
}

aspect production terminalTypeExp
top::TypeExp ::= fn::String
{
  top.refine = case top.refineWith of
               terminalTypeExp(ofn) -> if fn == ofn
                                     then emptySubst()
                                     else errorSubst("Tried to refine conflicting terminal types " ++ fn ++ " and " ++ ofn)
             | _ -> errorSubst("Tried to refine terminal type " ++ fn ++ " with " ++ prettyType(top.refineWith))
              end;
}

aspect production decoratedTypeExp
top::TypeExp ::= te::TypeExp
{
  top.refine = case top.refineWith of
               decoratedTypeExp(ote) -> refine(te, ote)
             | _ -> errorSubst("Tried to refine decorated type with " ++ prettyType(top.refineWith))
              end;
}

aspect production functionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.refine = case top.refineWith of
               functionTypeExp(oo, op) -> refineAll(out :: params, oo :: op)
             | _ -> errorSubst("Tried to refine function type with " ++ prettyType(top.refineWith))
              end;
}

aspect production productionTypeExp
top::TypeExp ::= out::TypeExp params::[TypeExp]
{
  top.refine = case top.refineWith of
               productionTypeExp(oo, op) -> refineAll(out :: params, oo :: op)
             | _ -> errorSubst("Tried to refine production type with " ++ prettyType(top.refineWith))
              end;
}

{--
 - Produces substitutions that may involve skolem constants, as well as free variables
 - for constructors.
 -
 - @param scrutineeType  The decorated type of the value being examined. Should not be a type variable!
 - @param constructorType  The decorated type of the production's product (i.e. the type it constructs)
 -}
function produceRefinement
Substitution ::= scrutineeType::TypeExp  constructorType::TypeExp
{
  -- only do refinement if they're the same type constructor.
  -- If you look at the type rules, you'll notice they're requiring "T" be the same,
  -- and this refinement only happens on the parameters (i.e. fmgu(T p = T a))
  return case scrutineeType, constructorType of
         | decoratedTypeExp(nonterminalTypeExp(n1, p1)), decoratedTypeExp(nonterminalTypeExp(n2,p2))
            -> if n1 == n2 then refineAll(p1,p2) else emptySubst()
         | _ -> emptySubst()
         end;
}

function refine
Substitution ::= te1::TypeExp te2::TypeExp
{
  local attribute leftward :: Substitution;
  leftward = te1.refine;
  te1.refineWith = te2;
  
  local attribute rightward :: Substitution;
  rightward = te2.refine;
  te2.refineWith = te1;
  
  return if null(leftward.substErrors)
         then leftward   -- arbitrary choice if both work, but if they are confluent, it's okay
         else rightward; -- arbitrary choice of errors. Non-confluent!!
}
function refineAll
Substitution ::= te1::[TypeExp] te2::[TypeExp]
{
  local attribute first :: Substitution;
  first = refine(head(te1), head(te2));
  
  return if null(te1) && null(te2)
         then emptySubst()
         else if null(te1) || null(te2)
         then errorSubst("Internal error: refineing mismatching numbers")
         else composeSubst(first, refineAll( mapSubst(tail(te1), first),
                                            mapSubst(tail(te2), first) ));
}

