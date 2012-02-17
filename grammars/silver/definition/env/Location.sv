grammar silver:definition:env;

nonterminal Location with line, column, fileName, unparse;

synthesized attribute fileName :: String;
synthesized attribute line :: Integer;
synthesized attribute column :: Integer;

abstract production loc
top::Location ::= file::String line::Integer column::Integer
{
  top.line = line;
  top.column = column;
  top.fileName = file;
  top.unparse = "'" ++ file ++ "', " ++ toString(line) ++ ", " ++ toString(column);
}

function locationLte
Boolean ::= l1::Location l2::Location
{
  return case l1, l2 of
         | loc(f1,l1,c1), loc(f2,l2,c2) -> !(f1 > f2 || (f1 == f2 && (l1 > l2 || (l1 == l2 && c1 > c2))))
         end;
}


