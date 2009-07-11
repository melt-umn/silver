grammar silver:definition:type:productiontype;
import silver:definition:core;
import silver:definition:env;

terminal CProduction_kwd 'Production' lexer classes {KEYWORD};
terminal CFunction_kwd 'Function' lexer classes {KEYWORD};
synthesized attribute types :: [Decorated TypeRep];
nonterminal Signature with location, grammarName, file, warnings, errors, env, pp, types;
nonterminal TypeList with location, grammarName, file, warnings, errors, env, defs, pp, types;
--TODO
concrete production prodType
top::Type ::= 'Production' '(' sig::Signature ')'
{
  top.pp = "Production(" ++ sig.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := sig.errors;
  top.warnings := sig.warnings;

  top.typerep = prodTypeRep(tail(sig.types), head(sig.types));
}

concrete production funType
top::Type ::= 'Function' '(' sig::Signature ')'
{
  top.pp = "Function(" ++ sig.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  top.errors := sig.errors;
  top.warnings := sig.warnings;

  top.typerep = funTypeRep(tail(sig.types), head(sig.types));
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
