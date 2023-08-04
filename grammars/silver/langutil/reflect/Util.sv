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
        getSynthesized(x, "silver:langutil:pp"),
        alt(
          map(\ pps::[Document] -> pp"[${ppImplode(pp", ", pps)}]",
            getSynthesized(x, "silver:langutil:pps")),
          map(text, getSynthesized(x, "silver:langutil:unparse")))),
      reflect(x).pp);
}
