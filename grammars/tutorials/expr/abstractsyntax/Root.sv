grammar tutorials:expr:abstractsyntax ;

import tutorials:expr:terminals ;

nonterminal Root with pp, value, errors ;

synthesized attribute pp :: String ; -- with ++ ;
synthesized attribute value :: Integer ;
synthesized attribute errors :: [String] with ++ ;

abstract production root
r::Root ::= e::Expr
{
 r.pp = e.pp 
         -- uncomment either of the following and an 
         --   "empty list" error occurs
         -- ++ head(strs) 
         -- ++ head(strsnt).new_pp 
         ;
 -- r.pp <- "MORE STUFF" ; -- this doesn't show up in output
 r.value = e.value ;
 r.errors := e.errors ;

 production attribute strs :: [ String ] with ++ ;
 strs := [ ] ;
 strs <- [ "HELLO" ] ;

 production attribute strsnt :: [ StrNT ] with ++ ;
 strsnt := [ ] ;
 strsnt <- [ mkStr("HELLO") ] ;
}

nonterminal StrNT with new_pp ;
synthesized attribute new_pp :: String ;

abstract production mkStr
ns::StrNT ::= s::String
{ ns.new_pp = s ;
}

