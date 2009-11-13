grammar edu:umn:cs:melt:tutorial:expr:host_op_overloading ;

-- The Add.sv, Sub.sv, Mul.sv, and Div.sv define the operator overloaded
-- versions of, respectively, addition, subtraction, multiplication, and
-- division.

-- These files follow the same pattern and Add.sv has additional
-- comments explaining the process.




-- This file defines an "erroneous expression" production that is 
-- forwarded to in the case of an error in overloading.

import edu:umn:cs:melt:tutorial:expr:terminals ;
import edu:umn:cs:melt:tutorial:expr:abstractsyntax ;

abstract production error_Expr
e::Expr ::= ln::Integer cl::Integer msg::String
{
 e.errors := [ mk_error (ln, cl, msg )  ] ;
 e.pp = "ERROR : " ++ msg ;
 forwards to integerConstant(terminal(IntLit_t,"0",ln,cl));
}

