grammar silver:compiler:definition:type:syntax;

imports silver:compiler:definition:core;
imports silver:compiler:definition:type;
imports silver:compiler:definition:env;
imports silver:compiler:definition:flow:ast;
imports silver:compiler:definition:flow:syntax;

nonterminal TypeExpr  with config, location, grammarName, errors, env, flowEnv, unparse, typerep, lexicalTypeVariables, lexicalTyVarKinds, errorsTyVars, freeVariables, errorsFullyApplied;
nonterminal Signature with config, location, grammarName, errors, env, flowEnv, unparse, typerep, lexicalTypeVariables, lexicalTyVarKinds;
nonterminal SignatureLHS with config, location, grammarName, errors, env, flowEnv, unparse, maybeType, lexicalTypeVariables, lexicalTyVarKinds;
nonterminal TypeExprs with config, location, grammarName, errors, env, flowEnv, unparse, types, missingCount, lexicalTypeVariables, lexicalTyVarKinds, errorsTyVars, freeVariables;
nonterminal BracketedTypeExprs with config, location, grammarName, errors, env, flowEnv, unparse, types, missingCount, lexicalTypeVariables, lexicalTyVarKinds, errorsTyVars, freeVariables, envBindingTyVars, initialEnv;
nonterminal BracketedOptTypeExprs with config, location, grammarName, errors, env, flowEnv, unparse, types, missingCount, lexicalTypeVariables, lexicalTyVarKinds, errorsTyVars, freeVariables, envBindingTyVars, initialEnv;

synthesized attribute maybeType :: Maybe<Type>;
synthesized attribute types :: [Type];
synthesized attribute missingCount::Integer;

-- Important: These should be IN-ORDER and include ALL type variables that appear, including duplicates!
monoid attribute lexicalTypeVariables :: [String];
-- freeVariables also occurs on TypeExprs, and should be IN ORDER

monoid attribute lexicalTyVarKinds :: [Pair<String Integer>];

-- These attributes are used if we're using the TypeExprs as type variables-only.
monoid attribute errorsTyVars :: [Message];
-- A new environment, with the type variables in this list appearing bound
inherited attribute initialEnv :: Decorated Env;
synthesized attribute envBindingTyVars :: Decorated Env;

synthesized attribute errorsFullyApplied::[Message];

propagate errors, lexicalTypeVariables, lexicalTyVarKinds on TypeExpr, Signature, SignatureLHS, TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs;
propagate errorsTyVars on TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs;

-- TODO: This function should go away because it doesn't do location correctly.
-- But for now, we'll use it. It might be easier to get rid of once we know exactly
-- how ty vars end up in the environment.
function addNewLexicalTyVars
[Def] ::= gn::String sl::Location lk::[Pair<String Integer>] l::[String]
{
  return if null(l) then []
         else lexTyVarDef(gn, sl, head(l), freshTyVar(fromMaybe(0, lookup(head(l), lk)))) ::
                  addNewLexicalTyVars(gn, sl, lk, tail(l));
}

aspect default production
top::TypeExpr ::=
{
  -- This has to do with type lists that are type variables only.
  -- We don't have a separate nonterminal for this, because we'd like to produce
  -- "semantic" errors, rather than parse errors for this.
  top.errorsTyVars := [err(top.location, top.unparse ++ " is not permitted here, only type variables are")];
  top.freeVariables = top.typerep.freeVariables;
  top.errorsFullyApplied =
    if top.typerep.kindArity > 0
    then [err(top.location, s"${top.unparse} is not fully applied, it has kind arity ${toString(top.typerep.kindArity)}")]
    else [];
}

abstract production errorTypeExpr
top::TypeExpr ::= e::[Message]
{
  top.unparse = s"{- Errors:\n${messagesToString(e)} -}";
  
  top.typerep = errorType();
  
  top.errors <- e;
}

abstract production typerepTypeExpr
top::TypeExpr ::= t::Type
{
  top.unparse = prettyType(t);

  top.typerep = t;
}

concrete production integerTypeExpr
top::TypeExpr ::= 'Integer'
{
  top.unparse = "Integer";

  top.typerep = intType();
}

concrete production floatTypeExpr
top::TypeExpr ::= 'Float'
{
  top.unparse = "Float";

  top.typerep = floatType();
}

concrete production stringTypeExpr
top::TypeExpr ::= 'String'
{
  top.unparse = "String";

  top.typerep = stringType();
}

concrete production booleanTypeExpr
top::TypeExpr ::= 'Boolean'
{
  top.unparse = "Boolean";

  top.typerep = boolType();
}

concrete production termnalIdTypeExpr
top::TypeExpr ::= 'TerminalId'
{
  top.unparse = "TerminalId";

  top.typerep = terminalIdType();
}

concrete production nominalTypeExpr
top::TypeExpr ::= q::QNameType
{
  top.unparse = q.unparse;

  top.errors <- q.lookupType.errors;
  top.errors <-
    if !q.lookupType.found || q.lookupType.dcl.isType then []
    else if q.lookupType.dcl.isTypeAlias  -- Raise a less confusing error if we see an unapplied type alias
    then [err(top.location, q.name ++ " is a type alias, expecting " ++ toString(length(q.lookupType.dcl.typeScheme.boundVars)) ++ " type arguments.")]
    else [err(top.location, q.name ++ " is not a type.")];

  top.typerep = q.lookupType.typeScheme.typerep; -- NOT .monoType since this can be a polyType when an error is raised
}

concrete production typeVariableTypeExpr
top::TypeExpr ::= tv::IdLower_t
{
  top.unparse = tv.lexeme;
  
  local attribute hack::QNameLookup;
  hack = customLookup("type", getTypeDcl(tv.lexeme, top.env), tv.lexeme, top.location);
  
  top.typerep = hack.typeScheme.monoType;
  top.errors <- hack.errors;
  top.errorsTyVars := [];

  top.lexicalTypeVariables <- [tv.lexeme];
}

concrete production appTypeExpr
top::TypeExpr ::= ty::TypeExpr tl::BracketedTypeExprs
{
  top.unparse = ty.unparse ++ tl.unparse;
  
  propagate lexicalTypeVariables; -- Needed to avoid circularity

  forwards to
    case ty of
    | nominalTypeExpr(q) when q.lookupType.found && q.lookupType.dcl.isTypeAlias ->
      aliasAppTypeExpr(q, tl, location=top.location)
    | _ -> typeAppTypeExpr(ty, tl, location=top.location)
    end;
}

abstract production aliasAppTypeExpr
top::TypeExpr ::= q::Decorated QNameType tl::BracketedTypeExprs
{
  top.unparse = q.unparse ++ tl.unparse;

  production ts::PolyType = q.lookupType.typeScheme;
  top.typerep = performRenaming(ts.typerep, zipVarsAndTypesIntoSubstitution(ts.boundVars, tl.types));

  local tlCount::Integer = length(tl.types) + tl.missingCount;
  top.errors <-
    if tlCount != length(ts.boundVars)
    then [err(top.location, q.lookupType.fullName ++ " expects " ++ toString(length(ts.boundVars)) ++ " type arguments, but there are " ++ toString(tlCount) ++ " supplied here.")]
    else [];
  top.errors <-
    if tl.missingCount > 0
    then [err(tl.location, q.lookupType.fullName ++ " is a type alias and cannot be partially applied.")]
    else [];
}

abstract production typeAppTypeExpr
top::TypeExpr ::= ty::Decorated TypeExpr tl::BracketedTypeExprs
{
  top.unparse = ty.unparse ++ tl.unparse;

  top.typerep = appTypes(ty.typerep, tl.types);

  top.errors <- ty.errors;

  local tlCount::Integer = length(tl.types) + tl.missingCount;
  top.errors <-
    if tlCount != ty.typerep.kindArity
    then [err(top.location, ty.unparse ++ " has kind arity " ++ toString(ty.typerep.kindArity) ++ ", but there are " ++ toString(tlCount) ++ " type arguments supplied here.")]
    else [];

  top.lexicalTyVarKinds <-
    case ty of
    | typeVariableTypeExpr(tv) -> [pair(tv.lexeme, tlCount)]
    | _ -> []
    end;
}

concrete production refTypeExpr
top::TypeExpr ::= 'Decorated' t::TypeExpr
{
  top.unparse = "Decorated " ++ t.unparse;

  top.typerep =
    case getInhsForNtRef(t.typerep.typeName, top.flowEnv) of
    | ntRefFlowDef(_, inhs) :: _ -> decoratedType(inhs, t.typerep)
    | _ -> decoratedType([], t.typerep)
    end;
  
  top.errors <-
    case t.typerep.baseType of
    | nonterminalType(_,_,_) -> []
    | skolemType(_) -> []
    | _ -> [err(t.location, t.unparse ++ " is not a nonterminal, and cannot be Decorated.")]
    end;
  top.errors <- t.errorsFullyApplied;
}

concrete production refSpecTypeExpr
top::TypeExpr ::= 'Decorated' '{' inhs::FlowSpecInhs '}' t::TypeExpr
{
  top.unparse = s"Decorated {${inhs.unparse}} ${t.unparse}";

  top.typerep = decoratedType(inhs.inhList, t.typerep);
  
  top.errors <-
    case t.typerep.baseType of
    | nonterminalType(_,_,_) -> []
    | skolemType(_) -> []
    | _ -> [err(t.location, t.unparse ++ " is not a nonterminal, and cannot be Decorated.")]
    end;
  top.errors <- t.errorsFullyApplied;
  
  inhs.onNt = t.typerep;
}

concrete production funTypeExpr
top::TypeExpr ::= '(' sig::Signature ')'
{
  top.unparse = "(" ++ sig.unparse ++ ")";
  top.typerep = sig.typerep;
}

concrete production signatureEmptyRhs
top::Signature ::= l::SignatureLHS '::='
{
  top.unparse = l.unparse ++ " ::=";
  top.typerep = appTypes(functionType(0, []), case l.maybeType of just(t) -> [t] | nothing() -> [] end);
}

concrete production psignature
top::Signature ::= l::SignatureLHS '::=' list::TypeExprs 
{
  top.unparse = l.unparse ++ " ::= " ++ list.unparse;
  top.typerep =
    appTypes(
      functionType(length(list.types) + list.missingCount, []),
      list.types ++ case l.maybeType of just(t) -> [t] | nothing() -> [] end);
  
  top.errors <-
    if l.maybeType.isJust && list.missingCount > 0
    then [err($1.location, "Return type cannot be present when argument types are missing")]
    else [];
}

concrete production presentSignatureLhs
top::SignatureLHS ::= t::TypeExpr
{
  top.unparse = t.unparse;
  top.maybeType = just(t.typerep);
}

concrete production missingSignatureLhs
top::SignatureLHS ::= '_'
{
  top.unparse = "_";
  top.maybeType = nothing();
}

-- Bracketed Optional Type Lists -----------------------------------------------

concrete production botlNone
top::BracketedOptTypeExprs ::=
{
  top.unparse = "";
  forwards to botlSome(bTypeList('<', typeListNone(location=top.location), '>', location=top.location), location=top.location);
}

concrete production botlSome
top::BracketedOptTypeExprs ::= btl::BracketedTypeExprs
{
  top.unparse = btl.unparse;
  top.types = btl.types;
  top.missingCount = btl.missingCount;
  top.freeVariables = btl.freeVariables;
  top.envBindingTyVars = btl.envBindingTyVars;
  
  btl.initialEnv = top.initialEnv;
}

concrete production bTypeList
top::BracketedTypeExprs ::= '<' tl::TypeExprs '>'
{
  top.unparse = "<" ++ tl.unparse ++ ">";

  top.types = tl.types;
  top.missingCount = tl.missingCount;

  top.freeVariables = tl.freeVariables;
  
  top.errorsTyVars <-
    if length(tl.lexicalTypeVariables) != length(nub(tl.lexicalTypeVariables))
    then [err(top.location, "Type parameter list repeats type variable names")]
    else [];
  top.errorsTyVars <-
    if tl.missingCount > 0
    then [err(top.location, "Type parameter list cannot contain _")]
    else [];

  top.envBindingTyVars =
    newScopeEnv(
      addNewLexicalTyVars(top.grammarName, top.location, tl.lexicalTyVarKinds, tl.lexicalTypeVariables),
      top.initialEnv);
}

-- TypeExprs -------------------------------------------------------------------

abstract production typeListNone
top::TypeExprs ::=
{
  top.unparse = "";
  top.types = [];
  top.missingCount = 0;
  top.freeVariables = [];
}

concrete production typeListSingle
top::TypeExprs ::= t::TypeExpr
{
  top.unparse = t.unparse;
  forwards to typeListCons(t, typeListNone(location=top.location), location=top.location);
}

concrete production typeListSingleMissing
top::TypeExprs ::= '_'
{
  top.unparse = "_";
  forwards to typeListConsMissing($1, typeListNone(location=top.location), location=top.location);
}

concrete production typeListCons
top::TypeExprs ::= t::TypeExpr list::TypeExprs
{
  top.unparse = t.unparse ++ " " ++ list.unparse;
  top.types = t.typerep :: list.types;
  top.missingCount = list.missingCount;
  top.freeVariables = t.freeVariables ++ list.freeVariables;
  
  top.errors <- t.errorsFullyApplied;
}

concrete production typeListConsMissing
top::TypeExprs ::= '_' list::TypeExprs
{
  top.unparse = "_ " ++ list.unparse;
  top.types = list.types;
  top.missingCount = list.missingCount + 1;
  top.freeVariables = list.freeVariables;
  
  top.errors <-
    if length(list.types) > 0
    then [err($1.location, "Missing type argument cannot be followed by a provided argument")]
    else [];
}
