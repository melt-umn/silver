grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal Dec with pp,
                     name,
                     type,
                     bindingHTrans,
                     decHTrans,
                     frameIn,
                     errors;

abstract production declare
d::Dec ::= t::Type n::Id_t
{
 d.pp       = "Declaring " ++ n.lexeme ++ " as " ++ t.pp;
 d.name     = n;
 d.type     = t;
 d.decHTrans= "Dec{name=\"" ++ n.lexeme ++ "\", xr_type=" ++ t.htrans ++ "}";
 d.bindingHTrans 
            = "Binding{sn=\"" ++ n.lexeme ++  "\", value=NullValue}";
 d.errors   = 
              if found_b(d.frameIn.bindings, n)
              then  mk_error(n.lexeme ++  
                           " has alread been declared in this behavior.",
	 		   n.line, n.column)
              else [];
}	    

abstract production declare_value
d::Dec ::= t::Type n::Id_t 
{
 d.pp       = "Declaring " ++ n.lexeme ++ " as " ++ t.pp;
 d.name     = n;
 d.type     = t;
 d.decHTrans= "Dec{name=\"" ++ n.lexeme ++ "\", xr_type=" ++ t.htrans ++ "}";
 d.bindingHTrans 
            = "Binding{sn=\"" ++ n.lexeme ++  "\", value=" ++
                             n.lexeme ++ "BR\"}";
 d.errors   = 
              if found_b(d.frameIn.bindings, n)
              then  mk_error(n.lexeme ++  
                           " has alread been declared in this behavior.",
	 		   n.line, n.column)
              else [];
}	    
