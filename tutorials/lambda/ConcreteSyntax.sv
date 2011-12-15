grammar lambda ;

import silver:langutil only pp, ast;
import silver:langutil:pp;

synthesized attribute val::Integer;

--synthesized attribute ast_Type::TypeAST;
--synthesized attribute synTable::STable;
--inherited attribute inhTable::STable;

nonterminal Root_c with pp;

nonterminal Type_c with pp;
nonterminal Expr_c with pp,ast<Expr>;
nonterminal Term_c with pp,ast<Term>;
nonterminal Factor_c with pp,ast<Factor>;
nonterminal Expr_funct_c with pp,ast<Expr_funct>;
nonterminal Expr_arith_c with pp,ast<Expr_arith>;

concrete production root_c
r::Root_c ::=  e::Expr_c
{
  --tmp_table = createSym();
  r.pp = e.pp;	
  --e.inhTable = tmp_table;
  --local attribute tmp_table::STable;
}

concrete production root_c2
r::Expr_c ::=  e::Expr_funct 
{
  --r.ast_Type = e.ast_Type;
  --r.ttype = e.ttype;
  r.pp = e.pp;
  --e.inhTable = r.inhTable;
}

concrete production expr_let
e::Expr_c ::= 'let' ic::Id_t ':' ty::Type_c '=' e1::Expr_c 'in' e2::Expr_c
{  
  --tmp_table = addValPair(ic.lexeme,ty.ast_Type,e.inhTable);
  --tmpChk = if ( equalType(ty.ast_Type,e1.ast_Type) )
  --         then "Types Match in let expression"
  --         else "Type mismatch in let expression";
  --e1.inhTable = tmp_table;
  --e2.inhTable = tmp_table;
  
  e.pp = concat([text("let"), text(ic.lexeme), text(":"), ty.pp, text("="), e1.pp, text("in"), e2.pp]);
  --local attribute tmp_table::STable;             
  --local attribute tmpChk::String;
}


concrete production expr_lambda
ep::Expr_c ::= 'lambda' i::Id_t ':' ty::Type_c '.' e::Expr_c
{
  --tmp_table = addValPair(i.lexeme,ty.ast_Type,ep.inhTable);

  --e.inhTable = tmp_table;
  --ep.ast_Type = arrow(ty.ast_Type,e.ast_Type);
  --local attribute tmp_table::STable;             
  
  ep.pp = concat([text("lambda"), text(i.lexeme), text(":"), ty.pp, text("."), e.pp]);

}


concrete production expr_function1
e::Expr_funct_c ::= e1::Expr_funct_c e2::Expr_arith_c
{
  --e1.inhTable = e.inhTable;
  --e2.inhTable = e.inhTable;
  --local attribute t::Boolean;   
  --t = chkFunction(e1.ast_Type,e2.ast_Type);
  
  --e. ast_Type = case e1.ast_Type of
  --                  arrow(ta, tb) -> tb
  --                 | int() -> errorType()
  --                 | errorType() -> errorType()
  --                 end ;

  e.pp = cat(e1.pp, e2.pp); --TODO
}

concrete production expr_function2
e::Expr_funct_c ::= e1::Expr_arith_c
{
  --e.ast_Type = e1.ast_Type;
  --e1.inhTable = e.inhTable; 
  e.pp = e1.pp;
}

concrete production add_c
sum::Expr_arith_c ::=   e1::Expr_arith_c '+' t::Term_c
{
  --local attribute tmp::String;
  --tmp = typechk(t.ttype,e1.ttype);
  --sum.ast_Type = if (equalType(e1.ast_Type,int()) && equalType(t.ast_Type,int()))
  --              then int()
  --              else errorType();
  --t.inhTable = sum.inhTable;
  --e1.inhTable = sum.inhTable ;
  sum.pp = concat([e1.pp, text("+"), t.pp]);
}


concrete production sub_c
sum::Expr_arith_c ::=   e1::Expr_arith_c '-' t::Term_c
{
  --local attribute tmp::String;
  --tmp = typechk(t.ttype,e1.ttype);
  --sum.ast_Type = if (equalType(e1.ast_Type,int()) && equalType(t.ast_Type,int()))
  --            then int()
  --            else errorType();
  --t.inhTable = sum.inhTable;
  --e1.inhTable = sum.inhTable ;
  sum.pp = concat([e1.pp, text("-"), t.pp]);
}

concrete production exTerm_c
sum::Expr_arith_c ::=   t::Term_c
{
  --sum.ast_Type = t.ast_Type;
  --t.inhTable = sum.inhTable;
  sum.pp = t.pp;
}

concrete production mul_c
prd::Term_c ::= t::Term_c '*' f::Factor_c
{
  prd.pp = concat([t.pp, text("*"), f.pp]);
  --prd.ast_Type = if (equalType(t.ast_Type,int()) && equalType(f.ast_Type,int()))
  --              then int()
  --              else errorType();

  --f.inhTable = prd.inhTable;
  --t.inhTable = prd.inhTable;
}

concrete production div_c
d::Term_c ::= t::Term_c '/' f::Factor_c
{
  d.pp = concat([t.pp, text("/"), f.pp]);
  --d.ast_Type = if (equalType(t.ast_Type,int()) && equalType(f.ast_Type,int()))
  --             then int()
  --             else errorType();
  --t.inhTable = d.inhTable;
  --f.inhTable = d.inhTable;
}

concrete production termFactor_c
t::Term_c ::= f::Factor_c
{
  --t.ast_Type = f.ast_Type;
  --f.inhTable = t.inhTable;
  t.pp = f.pp;
}

concrete production nested_c
e::Factor_c ::= '(' inner::Expr_c ')'
{
  --inner.inhTable = e.inhTable;
  e.pp = parens(inner.pp);
  --e.ast_Type = inner.ast_Type;
}

concrete production facName_c
ic::Factor_c ::= i::Id_t
{
  --tmpType = getType(i.lexeme,ic.inhTable);
  --ic.ast_Type = tmpType;
  ic.pp = text(i.lexeme);
  --local attribute tmpType::TypeAST;
}

concrete production integerConstant_c
ic::Factor_c ::= i::IntLit_t
{
  --ic.ast_Type = int();
  ic.pp = text(i.lexeme);
}

concrete production type_right
ty::Type_c ::=  'int' '->' t::Type_c
{
  --ty.ast_Type = arrow(int(),t.ast_Type);
  ty.pp = concat([text("int"), text("->"), t.pp]);
}

concrete production type_int
ty::Type_c ::= i::Integer_t
{
  --ty.ast_Type = int();
  ty.pp = text("int");
}
concrete production type_left
ty::Type_c ::= '(' t::Type_c ')' '->' 'int'
{
  --ty.ast_Type = arrow(t.ast_Type,int());
  ty.pp = cat(parens(t.pp), text("->int"));
}

concrete production type_paren
ty::Type_c ::= '(' t::Type_c ')'
{
  --ty.ast_Type = t.ast_Type;
  ty.pp = parens(t.pp);
}



