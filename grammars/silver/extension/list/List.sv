grammar silver:extension:list;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax;

import silver:analysis:typechecking:core;

terminal LSqr_t '[' lexer classes {KEYWORD};
terminal RSqr_t ']' lexer classes {KEYWORD};

-- The TYPE --------------------------------------------------------------------
concrete production listType
top::Type ::= '[' te::Type ']'
{
  top.typerep = listTypeExp(te.typerep);

  forwards to refType('Decorated', 
                   nominalType(qNameUpperId(terminal(IdUpper_t, "core:List")),
                                    botlSome('<', typeListSingle(te), '>')));
}

-- The expressions -------------------------------------------------------------

concrete production emptyList
top::Expr ::= '[' ']'
{
  top.pp = "[]";

  forwards to emptyProductionApp(baseExpr(qNameId(nameIdLower(terminal (IdLower_t, "core:nil")))), '(',')');
}

-- TODO: BUG: '::' is HasType_t.  We probably want to have a different
-- terminal here, with different precedence!

concrete production consListOp
top::Expr ::= h::Expr '::' t::Expr
{
  top.pp = "(" ++ h.pp ++ " :: " ++ t.pp ++ ")" ;
  
  h.downSubst = top.downSubst; t.downSubst = top.downSubst; -- TODO BUG: don't know what this is needed... pp apparently??
  
  forwards to productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "core:cons")))),
                    '(', exprsCons(h, ',', exprsSingle(t)), ')');
}

concrete production fullList
top::Expr ::= '[' es::Exprs ']'
{ 
  top.pp = "[ " ++ es.pp ++ " ]";
  
  es.downSubst = top.downSubst; -- TODO again, pretty printing garbage.

  forwards to es.listtrans;
}

synthesized attribute listtrans :: Expr occurs on Exprs;

aspect production exprsEmpty
top::Exprs ::=
{
  top.listtrans = emptyList('[',']');
}

aspect production exprsSingle
top::Exprs ::= e::Expr
{
  top.listtrans = productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "core:cons", e.location.line, e.location.column)))),
                    '(', exprsCons(e, ',', exprsSingle(emptyList('[',']'))), ')');
}

aspect production exprsCons
top::Exprs ::= e1::Expr c::Comma_t e2::Exprs
{
  top.listtrans = productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "core:cons", e1.location.line, e1.location.column)))),
                    '(', exprsCons(e1, ',', exprsSingle(e2.listtrans)), ')');
}

aspect production exprsDecorated
top::Exprs ::= es::[Decorated Expr]
{
  top.listtrans = error("list translation for exprsDecorated is not yet implemented!");
}

-- Overloaded operators --------------------------------------------------------

abstract production listPlusPlus
top::Expr ::= e1::Decorated Expr e2::Decorated Expr
{
  forwards to productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "core:append")))),
                    '(', exprsDecorated([e1,e2]), ')');
}
abstract production listLengthBouncer
top::Expr ::= e::Decorated Expr
{
  forwards to productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "core:listLength")))),
                    '(', exprsDecorated([e]), ')');
}

