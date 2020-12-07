grammar silver:definition:type:syntax;

imports silver:definition:core;
imports silver:definition:type;
imports silver:definition:env;
imports silver:util;

nonterminal TypeExpr
  -- This grammar doesn't export silver:definition:core, so the type concrete
  -- syntax doesn't "know about" the core layout terminals.
  -- Thus we have to set the layout explicitly for the "root" nonterminal here.
  layout {BlockComments, Comments, WhiteSpace}
  with config, location, grammarName, errors, env, unparse, typerep, lexicalTypeVariables, errorsTyVars, freeVariables;
nonterminal Signature with config, location, grammarName, errors, env, unparse, types,   lexicalTypeVariables;
nonterminal TypeExprs  with config, location, grammarName, errors, env, unparse, types,   lexicalTypeVariables, errorsTyVars, freeVariables;
nonterminal BracketedOptTypeExprs with config, location, grammarName, errors, env, unparse, types, lexicalTypeVariables, errorsTyVars, freeVariables, envBindingTyVars, initialEnv;

synthesized attribute types :: [Type];

-- Important: These should be IN-ORDER and include ALL type variables that appear, including duplicates!
monoid attribute lexicalTypeVariables :: [String] with [], ++;
-- freeVariables also occurs on TypeExprs, and should be IN ORDER

-- These attributes are used if we're using the TypeExprs as type variables-only.
monoid attribute errorsTyVars :: [Message] with [], ++;
-- A new environment, with the type variables in this list appearing bound
inherited attribute initialEnv :: Decorated Env;
synthesized attribute envBindingTyVars :: Decorated Env;

propagate errors, lexicalTypeVariables on TypeExpr, Signature, TypeExprs, BracketedOptTypeExprs;
propagate errorsTyVars on TypeExprs, BracketedOptTypeExprs;

-- TODO: This function should go away because it doesn't do location correctly.
-- But for now, we'll use it. It might be easier to get rid of once we know exactly
-- how ty vars end up in the environment.
function addNewLexicalTyVars
[Def] ::= gn::String sl::Location l::[String]
{
  return if null(l) then []
         else lexTyVarDef(gn, sl, head(l), freshTyVar()) ::
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
top::TypeExpr ::= q::QNameType tl::BracketedOptTypeExprs
{
  top.unparse = q.unparse ++ tl.unparse;

  top.errors <- q.lookupType.errors;
  top.errors <-
    if q.lookupType.dcl.isType then []
    else [err(top.location, q.name ++ " is not a type.")];

  local ts::PolyType = q.lookupType.typeScheme;
  top.errors <- if length(tl.types) != length(ts.boundVars)
                then [err(top.location, q.name ++ " has " ++ toString(length(ts.boundVars)) ++ " type variables, but there are " ++ toString(length(tl.types)) ++ " supplied here.")]
                else [];

  -- Not necessarily a nonterminalType, so we should take original type and substitution
  -- e.g. consider `type Blah<a> = Foo<String a>`
  top.typerep = performRenaming(ts.typerep, zipVarsAndTypesIntoSubstitution(ts.boundVars, tl.types));
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

concrete production refTypeExpr
top::TypeExpr ::= 'Decorated' t::TypeExpr
{
  top.unparse = "Decorated " ++ t.unparse;

  top.typerep = decoratedType(t.typerep);
  
  top.errors <- case t.typerep of
                  nonterminalType(_,_) -> []
                | _ -> [err(t.location, t.unparse ++ " is not a nonterminal, and cannot be Decorated.")]
                end;
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
}

-- Bracketed Optional Type Lists -----------------------------------------------

concrete production botlNone
top::BracketedOptTypeExprs ::=
{
  top.unparse = "";
  forwards to botlSome('<', typeListNone(location=top.location), '>', location=top.location);
}

concrete production botlSome
top::BracketedOptTypeExprs ::= '<' tl::TypeExprs '>'
{
  top.unparse = "<" ++ tl.unparse ++ ">";

  top.types = tl.types;

  top.freeVariables = tl.freeVariables;
  
  top.errorsTyVars <-
    if containsDuplicates(tl.lexicalTypeVariables)
    then [err(top.location, "Type parameter list repeats type variable names")]
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
  top.freeVariables = [];
}


concrete production typeListSingle
top::TypeExprs ::= t::TypeExpr
{
  top.unparse = t.unparse;
  top.types = [t.typerep];
  top.freeVariables = t.freeVariables;
}

concrete production typeListCons
top::TypeExprs ::= t::TypeExpr list::TypeExprs
{
  top.unparse = t.unparse ++ " " ++ list.unparse;
  top.types = t.typerep :: list.types;
  top.freeVariables = t.freeVariables ++ list.freeVariables;
}
