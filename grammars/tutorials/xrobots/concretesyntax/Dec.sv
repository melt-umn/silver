grammar tutorials:xrobots:concretesyntax ;


 import tutorials:xrobots:terminals ;
 --import tutorials:xrobots:abstractsyntax ;

nonterminal Dec_c with pp;
--synthesized attribute ast_Dec::Dec occurs on Dec_c;

concrete production primDec_c
d::Dec_c ::= pd::PrimDec_c ';'
{
 d.pp = pd.pp ++ " ;" ;
-- d.ast_Dec = pd.ast_Dec ;
}

nonterminal PrimDec_c with pp; --, ast_Dec ;

concrete production decInt_c
d::PrimDec_c ::= t::Int_t n::Id_t
{
	d.pp = "Declaring variable " ++ n.lexeme ++ " of type int.\n ";
-- 	d.ast_Dec = primitiveDec(intTypeExpr(t), n, "0", false, false, false);
}

concrete production decFloat_c
d::PrimDec_c ::= t::Float_t n::Id_t
{
	d.pp = "Declaring variable " ++ n.lexeme ++ " of type float.\n ";
 --	d.ast_Dec = primitiveDec(floatTypeExpr(t), n, "0.0", false, false, false);
}

concrete production decBool_c
d::PrimDec_c ::= t::Boolean_t n::Id_t
{
	d.pp = "Declaring variable " ++ n.lexeme ++ " of type bool.\n ";
 --	d.ast_Dec = primitiveDec(booleanTypeExpr(t), n, "False", false, false, false);
}

concrete production decArray_c
d::PrimDec_c ::= dIn::PrimDec_c '[' i::IntLit_t ']'
{
	d.pp = "Declaring variable  of type array.\n ";
 --	d.ast_Dec = primitiveDec(arrayTypeExpr(dIn.ast_Dec.typerep,
--														 toInt(i.lexeme)), 
--										dIn.ast_Dec.decId, 
	--									getArrayInitVal(dIn.ast_Dec.typerep, 
	--															toInt(i.lexeme)), 
	--									false, false, false);
}

concrete production dec2dArray_c
d::PrimDec_c ::= dIn::PrimDec_c '[' i::IntLit_t ',' j::IntLit_t ']'
{
	d.pp = "Declaring variable  of type array.\n ";
 	--d.ast_Dec = primitiveDec(array2dTypeExpr(dIn.ast_Dec.typerep,
	--															toInt(i.lexeme),
	--															toInt(j.lexeme)),
	--									dIn.ast_Dec.decId, 
	--									getArray2dInitVal(dIn.ast_Dec.typerep, 
		--														toInt(i.lexeme),
			--													toInt(j.lexeme)), 
			--							false, false, false);
}


concrete production behaviorDec_c
d::PrimDec_c ::= bt::PBehavior_t n::Id_t opl::OptionalParamDecList_c
{
   d.pp = "A Behavior " ++ n.lexeme;
--   d.ast_Dec = primitiveDec(behaviorTypeExpr(n, opl.ast_DecList), n, "0", false, false, true);
}
