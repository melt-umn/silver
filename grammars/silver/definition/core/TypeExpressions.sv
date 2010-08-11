grammar silver:definition:core;
import silver:definition:env;

abstract production typerepType
top::Type ::= t::Decorated TypeRep
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
  top.typerep = integerTypeRep();
  top.errors := [];
  top.warnings := [];
}

concrete production floatType
top::Type ::= 'Float'
{
  top.pp = "Float";
  top.location = loc(top.file, $1.line, $1.column);
  top.typerep = floatTypeRep();
  top.errors := [];
  top.warnings := [];
}

concrete production stringType
top::Type ::= 'String'
{
  top.pp = "String";
  top.location = loc(top.file, $1.line, $1.column);
  top.typerep = stringTypeRep();
  top.errors := [];
  top.warnings := [];
}

concrete production booleanType
top::Type ::= 'Boolean'
{
  top.pp = "Boolean";
  top.location = loc(top.file, $1.line, $1.column);
  top.typerep = booleanTypeRep();
  top.errors := [];
  top.warnings := [];
}

-- TODO: this applies to both nonterminals, and terminals, and anything else that appears in the type namespace. Fix the name?
concrete production nttType
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

  top.typerep = if q.lookupType.typerep.typeName == "TOP"  -- TODO: put a 'decoratedTypeRep on TypeRep, and make it work that way, rather than an if here
                then topTypeRep()
                else refTypeRep(q.lookupType.typerep);

  top.warnings := [];
  top.errors := q.lookupType.errors; -- TODO: verify it's nonterminal?
}


