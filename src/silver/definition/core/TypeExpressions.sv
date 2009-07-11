grammar silver:definition:core;
import silver:definition:env;

concrete production integerType
top::Type ::= 'Integer'
{
  top.pp = "Integer";
--  top.location = loc(top.file, $1.line, $1.column);
  top.typerep = integerTypeRep();
  top.errors := [::Decorated Message];
  top.warnings := [::Decorated Message];
}

concrete production floatType
top::Type ::= 'Float'
{
  top.pp = "Float";
  top.typerep = floatTypeRep();
--  top.location = loc(top.file, $1.line, $1.column);
  top.errors := [::Decorated Message];
  top.warnings := [::Decorated Message];
}

concrete production stringType
top::Type ::= 'String'
{
  top.pp = "String";
  top.typerep = stringTypeRep();
--  top.location = loc(top.file, $1.line, $1.column);
  top.errors := [::Decorated Message];
  top.warnings := [::Decorated Message];
}

concrete production booleanType
top::Type ::= 'Boolean'
{
  top.pp = "Boolean";
  top.typerep = booleanTypeRep();
--  top.location = loc(top.file, $1.line, $1.column);
  top.errors := [::Decorated Message];
  top.warnings := [::Decorated Message];
}

abstract production typerepType
top::Type ::= t::Decorated TypeRep
{
  top.pp = t.unparse;
  top.location = loc("typerepType", -1, -1);
  top.typerep = t;
  top.errors := [::Decorated Message];
  top.warnings := [::Decorated Message];
}

