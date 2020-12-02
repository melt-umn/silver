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
  with config, location, grammarName, errors, env, unparse, typerep, lexicalTypeVariables;
nonterminal Signature with config, location, grammarName, errors, env, unparse, types,   lexicalTypeVariables;
nonterminal TypeExprs  with config, location, grammarName, errors, env, unparse, types,   lexicalTypeVariables, errorsTyVars, freeVariables;
nonterminal BracketedOptTypeExprs with config, location, grammarName, errors, env, unparse, types, lexicalTypeVariables, errorsTyVars, freeVariables, envBindingTyVars, initialEnv;

synthesized attribute types :: [Type];

-- Important: These should be IN-ORDER and include ALL type variables that appear, including duplicates!
synthesized attribute lexicalTypeVariables :: [String];
-- freeVariables also occurs on TypeExprs, and should be IN ORDER

-- These attributes are used if we're using the TypeExprs as type variables-only.
synthesized attribute errorsTyVars :: [Message] with ++;
-- A new environment, with the type variables in this list appearing bound
inherited attribute initialEnv :: Decorated Env;
synthesized attribute envBindingTyVars :: Decorated Env;

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

abstract production errorTypeExpr
top::TypeExpr ::= e::[Message]
{
  top.unparse = s"{- Errors:\n${messagesToString(e)} -}";
  
  top.typerep = errorType();
  
  top.errors := e;
  
  top.lexicalTypeVariables = [];
}

abstract production typerepTypeExpr
top::TypeExpr ::= t::Type
{
  top.unparse = prettyType(t);

  top.typerep = t;

  top.errors := [];

  top.lexicalTypeVariables = [];
}

concrete production integerTypeExpr
top::TypeExpr ::= 'Integer'
{
  top.unparse = "Integer";

  top.typerep = intType();

  top.errors := [];

  top.lexicalTypeVariables = [];
}

concrete production floatTypeExpr
top::TypeExpr ::= 'Float'
{
  top.unparse = "Float";

  top.typerep = floatType();

  top.errors := [];

  top.lexicalTypeVariables = [];
}

concrete production stringTypeExpr
top::TypeExpr ::= 'String'
{
  top.unparse = "String";

  top.typerep = stringType();

  top.errors := [];

  top.lexicalTypeVariables = [];
}

concrete production booleanTypeExpr
top::TypeExpr ::= 'Boolean'
{
  top.unparse = "Boolean";

  top.typerep = boolType();

  top.errors := [];

  top.lexicalTypeVariables = [];
}

concrete production termnalIdTypeExpr
top::TypeExpr ::= 'TerminalId'
{
  top.unparse = "TerminalId";

  top.typerep = terminalIdType();

  top.errors := [];

  top.lexicalTypeVariables = [];
}

concrete production nominalTypeExpr
top::TypeExpr ::= q::QNameType tl::BracketedOptTypeExprs
{
  top.unparse = q.unparse ++ tl.unparse;

  top.errors := q.lookupType.errors ++ tl.errors;
  top.lexicalTypeVariables = tl.lexicalTypeVariables;

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
  top.errors := hack.errors;

  top.lexicalTypeVariables = [tv.lexeme];
}

concrete production refTypeExpr
top::TypeExpr ::= 'Decorated' t::TypeExpr
{
  top.unparse = "Decorated " ++ t.unparse;

  top.typerep = decoratedType(t.typerep);

  top.errors := t.errors;
  
  top.errors <- case t.typerep of
                  nonterminalType(_,_,_) -> []
                | _ -> [err(t.location, t.unparse ++ " is not a nonterminal, and cannot be Decorated.")]
                end;

  top.lexicalTypeVariables = t.lexicalTypeVariables;
}

concrete production funTypeExpr
top::TypeExpr ::= '(' sig::Signature ')'
{
  top.unparse = "(" ++ sig.unparse ++ ")";

  top.errors := sig.errors;

  top.typerep = functionType(head(sig.types), tail(sig.types), []);

  top.lexicalTypeVariables = sig.lexicalTypeVariables;
}

concrete production signatureEmptyRhs
top::Signature ::= t::TypeExpr '::='
{
  top.unparse = t.unparse ++ " ::=";

  top.errors := t.errors;

  top.types = [t.typerep];

  top.lexicalTypeVariables = t.lexicalTypeVariables;
}

concrete production psignature
top::Signature ::= t::TypeExpr '::=' list::TypeExprs 
{
  top.unparse = t.unparse ++ " ::= " ++ list.unparse;

  top.errors := t.errors ++ list.errors;

  top.types = [t.typerep] ++ list.types;

  top.lexicalTypeVariables = t.lexicalTypeVariables ++ list.lexicalTypeVariables;
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

  top.errors := tl.errors;
  top.types = tl.types;

  top.lexicalTypeVariables = tl.lexicalTypeVariables;
  top.freeVariables = tl.freeVariables;
  
  top.errorsTyVars := tl.errorsTyVars ++
    if containsDuplicates(tl.lexicalTypeVariables)
    then [err(top.location, "Type parameter list repeats type variable names")]
    else [];

  top.envBindingTyVars =
    newScopeEnv(
      addNewLexicalTyVars(top.grammarName, top.location, tl.lexicalTypeVariables),
      top.initialEnv);
}

-- TypeExprss -------------------------------------------------------------------

abstract production typeListNone
top::TypeExprs ::=
{
  top.unparse = "";
  top.errors := [];
  top.types = [];
  top.lexicalTypeVariables = [];
}


concrete production typeListSingle
top::TypeExprs ::= t::TypeExpr
{
  top.unparse = t.unparse;

  top.errors := t.errors;

  top.types = [t.typerep];

  top.lexicalTypeVariables = t.lexicalTypeVariables;
}

concrete production typeListCons
top::TypeExprs ::= t::TypeExpr list::TypeExprs
{
  top.unparse = t.unparse ++ " " ++ list.unparse;

  top.errors := t.errors ++ list.errors;

  top.types = [t.typerep] ++ list.types;

  top.lexicalTypeVariables = t.lexicalTypeVariables ++ list.lexicalTypeVariables;
}

--------------------------------------------------------------------------------
-- Aspecting the above three here, just to separate out these concerns:
-- This has to do with type lists that are type variables only.
-- We don't have a separate nonterminal for this, because we'd like to produce
-- "semantic" errors, rather than parse errors for this.

aspect production typeListNone
top::TypeExprs ::=
{
  top.errorsTyVars := [];
  top.freeVariables = [];
}

aspect production typeListSingle
top::TypeExprs ::= t::TypeExpr
{
  top.errorsTyVars := case t of
                        typeVariableTypeExpr(_) -> []
                      | _ -> [err(t.location, t.unparse ++ " is not permitted here, only type variables are")]
                      end;
  top.freeVariables = t.typerep.freeVariables;
}

aspect production typeListCons
top::TypeExprs ::= t::TypeExpr list::TypeExprs
{
  top.errorsTyVars := case t of
                        typeVariableTypeExpr(_) -> []
                      | _ -> [err(t.location, t.unparse ++ " is not permitted here, only type variables are")]
                      end ++ list.errorsTyVars;
  top.freeVariables = t.typerep.freeVariables ++ list.freeVariables;
}

