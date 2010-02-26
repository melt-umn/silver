grammar tutorials:wriju:concretesyntax;

import tutorials:wriju:terminals;

nonterminal Root_c with pp, cc;

synthesized attribute cc::String;
synthesized attribute pp::String;

inherited attribute tabs::String;

--synthesized attribute cc::String; --C Code

--Integer Literal
concrete production root_intlit
r::Root_c ::= t::IntLit_t
{
	r.pp = "The Root: " ++ t.lexeme;
	r.cc = "#include <stdio.h>\n\n" ++ t.lexeme ++ "\n\n" ;
}

{-

Wriju, I commented out these productions because they reference code
that you've written but not checked into Subversion - thus I don't
have it.



--Statement List
concrete production root_assignment
r::Root_c ::= sl::StatementList_c
{
	sl.tabs = "";
	r.pp = "The Statement List: \n" ++ sl.pp;

}

--Brace Block
concrete production root_braceblock
r::Root_c ::= bb::BraceBlock_c
{
	bb.tabs = "";
	r.pp = bb.pp;
}

--Function Definition List
concrete production root_function
r::Root_c ::= fdl::FunctionDefList_c
{
	fdl.tabs = "";
	r.pp = fdl.pp;
}

--While loop
concrete production root_whileloop
r::Root_c ::= wl::WhileLoop_c
{
	wl.tabs = "";
	r.pp = wl.pp;
}


-}
