grammar patt;


function typeEqualsNonPrim
Maybe<Eq<a b>> ::= ta::Type<a>  tb::Type<b>
{
  return case ta, tb of
           unitT(), unitT() -> just(refl())
         | arrow(aa, ab), arrow(ba, bb) ->
              case typeEqualsNonPrim(new(aa),new(ba)), typeEqualsNonPrim(new(ab), new(bb)) of
                just(refl()), just(refl()) -> just(refl())
              | _, _ -> nothing()
              end
         | _, _ -> nothing()
         end;
}

equalityTest ( typeEqualsNonPrim(unitT(),unitT()).isJust, true, Boolean, pat_tests ) ;
equalityTest ( typeEqualsNonPrim(arrow(unitT(),unitT()),unitT()).isJust, false, Boolean, pat_tests ) ;
equalityTest ( typeEqualsNonPrim(arrow(unitT(),unitT()),arrow(unitT(),unitT())).isJust, true, Boolean, pat_tests ) ;

