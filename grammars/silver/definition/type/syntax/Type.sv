grammar silver:definition:type:syntax;

import silver:definition:core;
import silver:definition:type;
import silver:definition:env;

nonterminal Type      with location, grammarName, file, warnings, errors, env, pp, defs, typerep;
nonterminal Signature with location, grammarName, file, warnings, errors, env, pp,       types;
nonterminal TypeList  with location, grammarName, file, warnings, errors, env, pp, defs, types;

synthesized attribute types :: [TypeExp];

abstract production typerepType
top::Type ::= t::Decorated TypeExp
{
  top.pp = t.unparse;
  top.location = loc("typerepType", -1, -1);
  top.typerep = t;
  top.errors := [];
  top.warnings := [];
}

concrete production integerType
top::Type ::= 'Integer'
{
  top.pp = "Integer";
  top.location = loc(top.file, $1.line, $1.column);
  top.typerep = intTypeExp();
  top.errors := [];
  top.warnings := [];
}

concrete production floatType
top::Type ::= 'Float'
{
  top.pp = "Float";
  top.location = loc(top.file, $1.line, $1.column);
  top.typerep = floatTypeExp();
  top.errors := [];
  top.warnings := [];
}

concrete production stringType
top::Type ::= 'String'
{
  top.pp = "String";
  top.location = loc(top.file, $1.line, $1.column);
  top.typerep = stringTypeExp();
  top.errors := [];
  top.warnings := [];
}

concrete production booleanType
top::Type ::= 'Boolean'
{
  top.pp = "Boolean";
  top.location = loc(top.file, $1.line, $1.column);
  top.typerep = boolTypeExp();
  top.errors := [];
  top.warnings := [];
}

concrete production nominalType
top::Type ::= q::QName
{
  top.pp = q.pp;
  top.location = q.location;

  top.typerep = q.lookupType.typerep;

  top.warnings := [];
  top.errors := q.lookupType.errors;
}

concrete production refType
top::Type ::= 'Decorated' q::QName
{
  top.pp = "Decorated " ++ q.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = decoratedTypeExp(q.lookupType.typerep);

  top.warnings := [];
  top.errors := q.lookupType.errors;
  
  top.errors <- case q.lookupType.typerep of
                  nonterminalTypeExp(_,_) -> []
                | _ -> [err(q.location, q.pp ++ " is not a nonterminal, and cannot be Decorated.")]
                end;
}

concrete production prodType
top::Type ::= 'Production' '(' sig::Signature ')'
{
  top.pp = "Production(" ++ sig.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := sig.errors;
  top.warnings := sig.warnings;

  top.typerep = productionTypeExp(head(sig.types), tail(sig.types));
}

concrete production funType
top::Type ::= 'Function' '(' sig::Signature ')'
{
  top.pp = "Function(" ++ sig.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := sig.errors;
  top.warnings := sig.warnings;

  top.typerep = functionTypeExp(head(sig.types), tail(sig.types));
}

concrete production signatureEmptyRhs
top::Signature ::= t::Type '::='
{
  top.pp = t.pp ++ " ::=";
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := t.errors;
  top.warnings := [];

  top.types = [t.typerep];
}

concrete production psignature
top::Signature ::= t::Type '::=' list::TypeList 
{
  top.pp = t.pp ++ " ::= " ++ list.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.errors := t.errors ++ list.errors;
  top.warnings := [];

  top.types = [t.typerep] ++ list.types;
}

concrete production typeListSingle
top::TypeList ::= t::Type
{
  top.pp = t.pp;
  top.location = t.location;

  top.errors := t.errors;
  top.warnings := [];

  top.types = [t.typerep];
}

concrete production typeListCons
top::TypeList ::= t::Type list::TypeList
{
  top.pp = t.pp ++ " " ++ list.pp;
  top.location = t.location;

  top.errors := t.errors ++ list.errors;
  top.warnings := [];

  top.types = [t.typerep] ++ list.types;
}
