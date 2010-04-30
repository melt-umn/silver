grammar silver:definition:type:decorated;
import silver:definition:core;
import silver:definition:env;

concrete production refType
top::Type ::= 'Decorated' q::QName
{
  top.pp = "Decorated " ++ q.pp;
  top.location = loc(top.file, $1.line, $1.column);

  top.typerep = if q.lookupType.typerep.typeName == "TOP"
                then topTypeRep()
                else refTypeRep(q.lookupType.typerep);

  top.warnings := [];
  top.errors := q.lookupType.errors; -- TODO: verify it's nonterminal?
}
