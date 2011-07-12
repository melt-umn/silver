grammar xrobots:concretesyntax ;


 import xrobots:terminals ;
 import xrobots:abstractsyntax ;

nonterminal Dec_c with pp;
synthesized attribute ast_Dec::Dec occurs on Dec_c;

concrete production primDec_c
d::Dec_c ::= pd::PrimDec_c ';'
{
 d.pp = pd.pp ++ " ;" ;
 d.ast_Dec = pd.ast_Dec ;
}

nonterminal PrimDec_c with pp, name, ast_Dec ;

concrete production decInt_c
d::PrimDec_c ::= t::Int_t n::Id_t
{
 d.pp = "Declaring variable " ++ n.lexeme ++ " of type int.\n ";
 d.ast_Dec = declare(intType(), n);
 d.name = n;
}

concrete production decFloat_c
d::PrimDec_c ::= t::Float_t n::Id_t
{
  d.pp = "Declaring variable " ++ n.lexeme ++ " of type float.\n ";
  d.ast_Dec = declare(floatType(), n); 
  d.name = n;
}

concrete production decBool_c
d::PrimDec_c ::= t::Boolean_t n::Id_t
{
 d.pp = "Declaring variable " ++ n.lexeme ++ " of type bool.\n ";
 d.ast_Dec = declare(boolType(), n); 
 d.name = n;
}

concrete production decArray_c
d::PrimDec_c ::= dIn::PrimDec_c '[' i::IntLit_t ']'
{
 d.pp = "Declaring variable  of type array.\n ";
 d.ast_Dec = declare(arrayType(dIn.ast_Dec.type), dIn.name);
 d.name = dIn.name;
}


concrete production behaviorDec_c
d::PrimDec_c ::= bt::PBehavior_t n::Id_t opl::OptionalParamDecList_c
{
   d.pp = "A Behavior " ++ n.lexeme;
   d.ast_Dec = declare(behaviorType([]), n);
   d.name = n;
}