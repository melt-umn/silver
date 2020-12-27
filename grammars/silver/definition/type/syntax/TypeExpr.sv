grammar silver:definition:type:syntax;

imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:env;
imports silver:util;

nonterminal TypeExpr  with config, location, grammarName, errors, env, unparse, typerep, lexicalTypeVariables, errorsTyVars, freeVariables;
nonterminal Signature with config, location, grammarName, errors, env, unparse, types,   lexicalTypeVariables;
nonterminal TypeExprs with config, location, grammarName, errors, env, unparse, types, missingCount, lexicalTypeVariables, errorsTyVars, freeVariables;
nonterminal BracketedTypeExprs with config, location, grammarName, errors, env, unparse, types, missingCount, lexicalTypeVariables, errorsTyVars, freeVariables, envBindingTyVars, initialEnv;
nonterminal BracketedOptTypeExprs with config, location, grammarName, errors, env, unparse, types, missingCount, lexicalTypeVariables, errorsTyVars, freeVariables, envBindingTyVars, initialEnv;

synthesized attribute types :: [Type];
synthesized attribute missingCount::Integer;

-- Important: These should be IN-ORDER and include ALL type variables that appear, including duplicates!
monoid attribute lexicalTypeVariables :: [String] with [], ++;
-- freeVariables also occurs on TypeExprs, and should be IN ORDER

-- These attributes are used if we're using the TypeExprs as type variables-only.
monoid attribute errorsTyVars :: [Message] with [], ++;
-- A new environment, with the type variables in this list appearing bound
inherited attribute initialEnv :: Decorated Env;
synthesized attribute envBindingTyVars :: Decorated Env;

propagate errors, lexicalTypeVariables on TypeExpr, Signature, TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs
  excluding appTypeExpr;
propagate errorsTyVars on TypeExprs, BracketedTypeExprs, BracketedOptTypeExprs;

-- TODO: This function should go away because it doesn't do location correctly.
-- But for now, we'll use it. It might be easier to get rid of once we know exactly
-- how ty vars end up in the environment.
function addNewLexicalTyVars
[Def] ::= gn::String sl::Location l::[String]
{
  return if null(l) then []
         else lexTyVarDef(gn, sl, head(l), freshTyVar(), 0) ::
                  addNewLexicalTyVars(gn, sl, tail(l));
}

aspect default production
top::TypeExpr ::=
{
  -- This has to do with type lists that are type variables only.
  -- We don't have a separate nonterminal for this, because we'd like to produce
  -- "semantic" errors, rather than parse errors for this.
  top.errorsTyVars := [err(top.location, top.unparse ++ " is not permitted here, only type variables are")];
  top.freeVariables = top.typerep.freeVariables;
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
  
  propagate lexicalTypeVariables;
  
  production isAlias::Boolean =
    case ty of
    | nominalTypeExpr(q) -> q.lookupType.found && q.lookupType.dcl.isTypeAlias
    | _ -> false
    end;
  local q::Decorated QNameType =
    case ty of
    | nominalTypeExpr(q) -> q
    end;
  local ts::PolyType = q.lookupType.typeScheme;

  top.errors := (if isAlias then [] else ty.errors) ++ tl.errors;

  production tlCount::Integer = length(tl.types) + tl.missingCount;
  production arity::Integer = if isAlias then length(ts.boundVars) else ty.typerep.kindArity;
  top.errors <-
    if tlCount != arity
    then [err(top.location, prettyType(ty.typerep) ++ " has kind arity " ++ toString(arity) ++ ", but there are " ++ toString(tlCount) ++ " type arguments supplied here.")]
    else [];
  top.errors <-
    if isAlias && tl.missingCount > 0
    then [err(tl.location, "Alias types cannot be partially applied")]
    else [];

  top.typerep =
    if isAlias
    then performRenaming(ts.typerep, zipVarsAndTypesIntoSubstitution(ts.boundVars, tl.types))
    else appTypes(ty.typerep, tl.types);
}

concrete production refTypeExpr
top::TypeExpr ::= 'Decorated' t::TypeExpr
{
  top.unparse = "Decorated " ++ t.unparse;

  top.typerep = decoratedType(t.typerep);
  
  top.errors <-
    case t.typerep.baseType of
    | nonterminalType(_,_) -> []
    | _ -> [err(t.location, t.unparse ++ " is not a nonterminal, and cannot be Decorated.")]
    end;
  top.errors <-
    if t.typerep.kindArity > 0
    then [err(t.location, s"Type ${t.unparse} is not fully applied")]
    else [];
}

concrete production funTypeExpr
top::TypeExpr ::= '(' sig::Signature ')'
{
  top.unparse = "(" ++ sig.unparse ++ ")";

  top.typerep = functionType(head(sig.types), tail(sig.types), []);
}

concrete production signatureEmptyRhs
top::Signature ::= t::TypeExpr '::='
{
  top.unparse = t.unparse ++ " ::=";

  top.types = [t.typerep];
}

concrete production psignature
top::Signature ::= t::TypeExpr '::=' list::TypeExprs 
{
  top.unparse = t.unparse ++ " ::= " ++ list.unparse;

  top.types = [t.typerep] ++ list.types;

  top.errors <-
    if list.missingCount > 0
    then [err(list.location, "Signature type cannot contain _")]
    else []; 
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
    if containsDuplicates(tl.lexicalTypeVariables)
    then [err(top.location, "Type parameter list repeats type variable names")]
    else [];
  top.errorsTyVars <-
    if tl.missingCount > 0
    then [err(top.location, "Type parameter list cannot contain _")]
    else [];

  top.envBindingTyVars =
    newScopeEnv(
      addNewLexicalTyVars(top.grammarName, top.location, tl.lexicalTypeVariables),
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
  
  top.errors <-
    if t.typerep.kindArity > 0
    then [err(t.location, s"Type ${t.unparse} is not fully applied")]
    else [];
  top.errors <-
    if list.missingCount > 0
    then [err(t.location, "Provided type argument cannot be followed by unprovided argument")]
    else [];
}

concrete production typeListConsMissing
top::TypeExprs ::= '_' list::TypeExprs
{
  top.unparse = "_ " ++ list.unparse;
  top.types = list.types;
  top.missingCount = list.missingCount + 1;
  top.freeVariables = list.freeVariables;
}
