grammar patt;


function typeEqualsNonPrim
Maybe<Eq<a b>> ::= ta::Type<a>  tb::Type<b>
{
  return case ta, tb of
           unit(), unit() -> just(eq())
         | arrow(aa, ab), arrow(ba, bb) ->
              case typeEqualsNonPrim(aa,ba), typeEqualsNonPrim(ab, bb) of
                just(eq()), just(eq()) -> just(eq())
              | _, _ -> nothing()
              end
         | _, _ -> nothing()
         end;
}

equalityTest ( typeEqualsNonPrim(unit(),unit()).isJust, true, Boolean, pat_tests ) ;
equalityTest ( typeEqualsNonPrim(arrow(unit(),unit()),unit()).isJust, false, Boolean, pat_tests ) ;
equalityTest ( typeEqualsNonPrim(arrow(unit(),unit()),arrow(unit(),unit())).isJust, true, Boolean, pat_tests ) ;

