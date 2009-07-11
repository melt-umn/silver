grammar silver:definition:type:anytype;
import silver:definition:core;
import silver:definition:env;

terminal Cast_kwd    /cast/ lexer classes {KEYWORD};

concrete production cast_c
top::Expr ::= 'cast' '(' e::Expr ',' t::Type ')'
{
  top.pp = "cast (" ++ e.pp ++ ", " ++ t.pp ++ ")";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to cast_t(e ,t.typerep) ;
}

abstract production cast_t
top::Expr ::= e::Expr t::Decorated TypeRep
{
  top.pp = "cast ( " ++ e.pp ++ ", " ++ t.typeName ++ " )" ;
  top.location = loc(top.file, -1, -1);

  top.typerep = t ;

  top.errors := e.errors;
  top.warnings := e.warnings;
}