grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal Stmt with pp, 
                     type,
                     htrans,
                     stack,
                     haskellStack,
                     errors;

nonterminal StmtList
                with pp, 
                     type,
                     htrans,
                     stack,
                     errors;

abstract production assignment
s::Stmt ::= n::Id_t  e::Expr
{
 s.pp      = n.lexeme ++ " := " ++ e.pp ++ 
             "     (" ++ (getType(s.stack, n)).pp ++ " := " ++ e.type.pp ++ ")";
 s.type    = e.type;
 s.htrans  = "(store "  ++ s.haskellStack ++ " " ++
                     "(getStackPointerByStaticLinks stack " ++ 
                     toString(getStaticLink(s.stack, n)) ++
                     " si) \"" ++ n.lexeme ++ "\" " ++ e.htrans ++ ")";
   
  s.errors =
             if !isDefined(s.stack, n)
             then  mk_error(n.lexeme ++ " is not defined.", n.line, n.column)
             else if equal_types(getType(s.stack, n), e.type)
                   then []
                    else mk_error("Cannot store a "  ++ e.type.pp ++
                          " in a " ++ (getType(s.stack, n)).pp,
				  n.line, n.column);
       
 e.stack   = s.stack;

 local attribute ty::Type;
 ty = getType(s.stack, n); 		
			    
}

  
abstract production sequence
s::Stmt ::= s1::Stmt s2::Stmt
{
 s.pp            = s1.pp ++ ";\n" ++ s2.pp;
 s.type          = intType();
 s2.haskellStack = s1.htrans;
 s1.haskellStack = "(" ++ s.haskellStack ++ ")";
 s.htrans        = s2.htrans;         
 s.errors        = s1.errors ++ s2.errors;
 s1.stack        = s.stack;
 s2.stack        = s.stack;
}