{- A Universal location information data structure -}
grammar silver:langutil;

nonterminal Location with unparse;

{--
 - The sole constructor for location information.
 - FUTURE WARNING: This may change to include additional end line/col & character index information!
 -}
abstract production loc
top::Location ::= file::String line::Integer column::Integer
{
  top.unparse = file ++ ":" ++ toString(line) ++ "." ++ toString(column);
}

{--
 - Less than or equal predicate, for use with sortBy, if desired.
 -}
function locationLte
Boolean ::= l1::Location l2::Location
{
  return case l1, l2 of
           loc(f1,l1,c1), loc(f2,l2,c2) -> !(f1 > f2 || (f1 == f2 && (l1 > l2 || (l1 == l2 && c1 > c2))))
         end;
--f1 <= f2 && (f1 != f2 || (l1 <= l2 && (l1 != l2 || c1 <= c2)))
--!(f1 > f2 || (f1 == f2 && (l1 > l2 || (l1 == l2 && c1 > c2))))
}


