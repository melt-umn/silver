grammar silver:definition:type:syntax;

import silver:definition:core;
import silver:definition:type;
import silver:definition:env;
import silver:util;
import silver:definition:type:gatherfreevars;

nonterminal Type      with location, grammarName, file, errors, env, pp, typerep, lexicalTypeVariables;
nonterminal Signature with location, grammarName, file, errors, env, pp, types,   lexicalTypeVariables;
nonterminal TypeList  with location, grammarName, file, errors, env, pp, types,   lexicalTypeVariables, errorsTyVars, freeVariables;

synthesized attribute types :: [TypeExp];

-- Important: These should be IN-ORDER and include ALL type variables that appear, including duplicates!
synthesized attribute lexicalTypeVariables :: [String];
-- freeVariables also occurs on TypeList, and should be IN ORDER

-- These attributes are used if we're using the TypeList as type variables-only.
synthesized attribute errorsTyVars :: [Decorated Message] with ++;

-- TODO: This function should go away because it doesn't do location correctly.
-- But for now, we'll use it. It might be easier to get rid of once we know exactly
-- how ty vars end up in the environment.
function addNewLexicalTyVars
Defs ::= gn::String sl::Decorated Location l::[String]
{
  return if null(l) then emptyDefs()
         else addLexTyVarDcl(gn, sl, head(l), skolemTypeExp(freshTyVar()),
                  addNewLexicalTyVars(gn, sl, tail(l)));
}

abstract production typerepType
top::Type ::= t::TypeExp
{
  top.pp = prettyType(top.typerep);
  top.location = loc("typerepType", -1, -1);

  top.typerep = t;

  top.errors := [];

  top.lexicalTypeVariables = [];
}

concrete production integerType
top::Type ::= 'Integer'
{
  top.pp = "Integer";
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = intTypeExp();

  top.errors := [];

  top.lexicalTypeVariables = [];
}

concrete production floatType
top::Type ::= 'Float'
{
  top.pp = "Float";
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = floatTypeExp();

  top.errors := [];

  top.lexicalTypeVariables = [];
}

concrete production stringType
top::Type ::= 'String'
{
  top.pp = "String";
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = stringTypeExp();

  top.errors := [];

  top.lexicalTypeVariables = [];
}

concrete production booleanType
top::Type ::= 'Boolean'
{
  top.pp = "Boolean";
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = boolTypeExp();

  top.errors := [];

  top.lexicalTypeVariables = [];
}

concrete production nominalType
top::Type ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

  top.typerep = q.lookupType.typerep;

  top.errors := q.lookupType.errors;

  top.errors <- case q.lookupType.typerep of
                  nonterminalTypeExp(_,orig) ->
                       if !null(orig)
                       then [err(top.location, q.pp ++ " has " ++ toString(length(orig)) ++ " type variables, but there are 0 supplied here.")]
                       else []
                | _ -> []
                end;

  top.lexicalTypeVariables = [];
}

concrete production nominalTypeWithParams
top::Type ::= q::QName '<' tl::TypeList '>'
{
  top.pp = q.pp ++ "<" ++ tl.pp ++ ">";
  top.location = q.location;

  top.typerep = case q.lookupType.typerep of
                  nonterminalTypeExp(ofn, op) -> nonterminalTypeExp(ofn, tl.types)
                | _ -> errorType()
                end;

  top.errors := q.lookupType.errors ++ tl.errors;
  
  top.errors <- case q.lookupType.typerep of
                  nonterminalTypeExp(_,orig) ->
                       if length(orig) != length(tl.types)
                       then [err(top.location, q.pp ++ " has " ++ toString(length(orig)) ++ " type variables, but there are " ++ toString(length(tl.types)) ++ " supplied here.")]
                       else []
                | _ -> [err(top.location, q.pp ++ " is not a nonterminal, and cannot be parameterized by types")]
                end;

  top.lexicalTypeVariables = tl.lexicalTypeVariables;
}

concrete production typeVariableType
top::Type ::= tv::TypeVariable_t
{
  top.pp = tv.lexeme;
  top.location = loc(top.file, $1.line, $1.column);
  
  local attribute hack::QNameLookup;
  hack = customLookup("type", getTypeDcl, tv.lexeme, top.location);
  hack.env = top.env;
  
  top.typerep = hack.typerep;
  top.errors := hack.errors;

  top.lexicalTypeVariables = [tv.lexeme];
}

concrete production refType
top::Type ::= 'Decorated' t::Type
{
  top.pp = "Decorated " ++ t.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = decoratedTypeExp(t.typerep);

  top.errors := t.errors;
  
  top.errors <- case t.typerep of
                  nonterminalTypeExp(_,_) -> []
                | _ -> [err(t.location, t.pp ++ " is not a nonterminal, and cannot be Decorated.")]
                end;

  top.lexicalTypeVariables = t.lexicalTypeVariables;
}

concrete production prodType
top::Type ::= 'Production' '(' sig::Signature ')'
{
  top.pp = "Production(" ++ sig.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := sig.errors;

  top.typerep = productionTypeExp(head(sig.types), tail(sig.types));

  top.lexicalTypeVariables = sig.lexicalTypeVariables;
}

concrete production funType
top::Type ::= 'Function' '(' sig::Signature ')'
{
  top.pp = "Function(" ++ sig.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := sig.errors;

  top.typerep = functionTypeExp(head(sig.types), tail(sig.types));

  top.lexicalTypeVariables = sig.lexicalTypeVariables;
}

concrete production signatureEmptyRhs
top::Signature ::= t::Type '::='
{
  top.pp = t.pp ++ " ::=";
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := t.errors;

  top.types = [t.typerep];

  top.lexicalTypeVariables = t.lexicalTypeVariables;
}

concrete production psignature
top::Signature ::= t::Type '::=' list::TypeList 
{
  top.pp = t.pp ++ " ::= " ++ list.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := t.errors ++ list.errors;

  top.types = [t.typerep] ++ list.types;

  top.lexicalTypeVariables = t.lexicalTypeVariables ++ list.lexicalTypeVariables;
}

-- TypeLists -------------------------------------------------------------------

abstract production typeListNone
top::TypeList ::=
{
  top.pp = "";
  top.location = loc(top.file, -1, -1);
  top.errors := [];
  top.types = [];
  top.lexicalTypeVariables = [];
}


concrete production typeListSingle
top::TypeList ::= t::Type
{
  top.pp = t.pp;
  top.location = t.location;

  top.errors := t.errors;

  top.types = [t.typerep];

  top.lexicalTypeVariables = t.lexicalTypeVariables;
}

concrete production typeListCons
top::TypeList ::= t::Type list::TypeList
{
  top.pp = t.pp ++ " " ++ list.pp;
  top.location = t.location;

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
top::TypeList ::=
{
  top.errorsTyVars := [];
  top.freeVariables = [];
}

aspect production typeListSingle
top::TypeList ::= t::Type
{
  top.errorsTyVars := case t of
                        typeVariableType(_) -> []
                      | _ -> [err(t.location, t.pp ++ " is not permitted here, only type variables are")]
                      end;
  top.freeVariables = t.typerep.freeVariables;
}

aspect production typeListCons
top::TypeList ::= t::Type list::TypeList
{
  top.errorsTyVars := case t of
                        typeVariableType(_) -> []
                      | _ -> [err(t.location, t.pp ++ " is not permitted here, only type variables are")]
                      end ++ list.errorsTyVars;
  top.freeVariables = t.typerep.freeVariables ++ list.freeVariables;
}

