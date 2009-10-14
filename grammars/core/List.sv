grammar core;

closed nonterminal AnyTypeList ;

synthesized attribute empty_AnyTypeList :: Boolean ;
synthesized attribute head_AnyTypeList :: AnyType ;
synthesized attribute tail_AnyTypeList :: Decorated AnyTypeList ;
synthesized attribute length_AnyTypeList :: Integer ;

attribute empty_AnyTypeList occurs on AnyTypeList ;
attribute head_AnyTypeList occurs on AnyTypeList ;
attribute tail_AnyTypeList occurs on AnyTypeList ;
attribute length_AnyTypeList occurs on AnyTypeList ;

function nil_AnyTypeList
Decorated AnyTypeList ::= {
  return decorate internal_nil_AnyTypeList() with {};
}

abstract production internal_nil_AnyTypeList
l::AnyTypeList ::= {
  l.empty_AnyTypeList = true ;
  l.length_AnyTypeList = 0 ;
  l.head_AnyTypeList = error ("Accessing head on value of type [ ] not allowed.\n" );
  l.tail_AnyTypeList = error ("Accessing tail on value of type [ ] not allowed.\n" );
}

function cons_AnyTypeList
Decorated AnyTypeList ::= h::AnyType t::Decorated AnyTypeList {
  return decorate internal_cons_AnyTypeList(h, t) with{}; 
}
abstract production internal_cons_AnyTypeList
l::AnyTypeList ::= h::AnyType t::Decorated AnyTypeList
{
  l.empty_AnyTypeList = false ;
  l.length_AnyTypeList = 1 + t.length_AnyTypeList ;
  l.head_AnyTypeList = h ;
  l.tail_AnyTypeList = t ;
}
function append_AnyTypeList
Decorated AnyTypeList ::= l1::Decorated AnyTypeList l2::Decorated AnyTypeList
{
  return if l1.empty_AnyTypeList
           then l2 
           else cons_AnyTypeList ( l1.head_AnyTypeList ,
                                   append_AnyTypeList ( l1.tail_AnyTypeList , l2 ) ) ;
}