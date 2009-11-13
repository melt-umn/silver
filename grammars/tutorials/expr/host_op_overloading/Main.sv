grammar edu:umn:cs:melt:tutorial:expr:host_op_overloading ;
 export edu:umn:cs:melt:tutorial:expr:host_op_overloading ;

-- import edu:umn:cs:melt:tutorial:expr:host hiding parse ;

export edu:umn:cs:melt:tutorial:expr:terminals ;
export edu:umn:cs:melt:tutorial:expr:abstractsyntax ;
export edu:umn:cs:melt:tutorial:expr:concretesyntax hiding parse, add_c ;
export edu:umn:cs:melt:tutorial:expr:driver ;

--export edu:umn:cs:melt:tutorial:expr:host_op_overloading:overloading ;

-- Use the concrete syntax of the original language, but do not
-- use the specifications for the arithmetic operators.
--
-- New versions that support operator overloading are added in the files
-- Add.sv, Sub.sv, Mul.sv, and Div.sv

syntax edu:umn:cs:melt:tutorial:expr:concretesyntax 
  hiding edu:umn:cs:melt:tutorial:expr:concretesyntax:add_c ,
         edu:umn:cs:melt:tutorial:expr:concretesyntax:sub_c , 
         edu:umn:cs:melt:tutorial:expr:concretesyntax:mul_c , 
         edu:umn:cs:melt:tutorial:expr:concretesyntax:div_c ; 

syntax edu:umn:cs:melt:tutorial:expr:terminals ;

--syntax edu:umn:cs:melt:tutorial:expr:host_op_overloading:overloading ;

