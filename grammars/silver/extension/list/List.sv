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
                   nominalTypeWithParams(qNameId(nameIdLower(terminal(IdLower_t, "core:List"))),
                                    '<', typeListSingle(te), '>'));
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
  
  forwards to productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "core:cons")))),
                    '(', exprsCons(h, ',', exprsSingle(t)), ')');
}

concrete production fullList
top::Expr ::= '[' es::Exprs ']'
{ 
  top.pp = "[ " ++ es.pp ++ " ]";

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

-- Overloaded operators --------------------------------------------------------

aspect production plusPlus
top::Expr ::= e1::Expr '++' e2::Expr
{
  -- TODO: THIS IS A COMPLETELY BUSTED WAY TO DO THIS, BUT WORKS FOR NOW. **FRAGILE**  probable BUGS
  handler <- if !(unify(e1.typerep, listTypeExp(errorType())).failure || unify(e2.typerep, listTypeExp(errorType())).failure)
             then [productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "core:append")))),
                    '(', exprsCons(e1, ',', exprsSingle(e2)), ')')]
             else [];
}

aspect production lengthFunction
top::Expr ::= 'length' '(' e::Expr ')'
{
  handlers <- if !unify(e.typerep, listTypeExp(errorType())).failure
	      then [productionApp(baseExpr(qNameId(nameIdLower(terminal(IdLower_t, "core:listLength")))),
                    '(', exprsSingle(e), ')')]
	      else [];
}

