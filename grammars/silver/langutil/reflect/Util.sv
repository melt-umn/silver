grammar silver:langutil:reflect;

@{-
 - Use reflection to get the pp or unparse of an unknown term,
 - falling back to use the reflective pp.
 -}
function genericPP
Document ::= x::a
{
  return
    fromRight(
      alt(
        bind(getSynthesized(x, "silver:langutil:pp"), tryForceTerm),
        alt(
          map(\ pps::[Document] -> pp"[${ppImplode(pp", ", pps)}]",
            bind(getSynthesized(x, "silver:langutil:pps"), tryForceTerm)),
          map(text, getSynthesized(x, "silver:langutil:unparse")))),
      reflect(x).pp);
}
