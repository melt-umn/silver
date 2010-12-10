
grammar tutorials:xrobots:concretesyntax ;

-- import tutorials:xrobots:abstractsyntax ;
 import tutorials:xrobots:terminals;


--concrete production block_c
--b::Stmt_c ::= '{' l::DecList_c  s::StmtList_c '}'
--{
--   b.pp =  l.pp ++ "\n"++ s.pp;
 --  b.ast_Stmt = makeBlock(l.ast_DecList, s.ast_StmtList);
--}

concrete production block2_c
b::Stmt_c ::= '{'  s::StmtList_c '}'
{
   b.pp = s.pp;
  -- b.ast_Stmt = makeBlock_2(s.ast_StmtList);
}
