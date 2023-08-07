grammar silver:langutil:reflect;

@{-
 - Use reflection to get the pp or unparse of an unknown term,
 - falling back to use the reflective pp.
 -}
function genericPP
Document ::= x::a
{
  local typeNamePP::Document = text(fromMaybe("<OBJ>", reflectTypeName(x)));
  return
    fromRight(
      alt(
        map(\ pp::Document -> pp"${typeNamePP} { ${pp} }",
          getSynthesized(x, "silver:langutil:pp")),
        alt(
          map(\ pps::[Document] -> pp"${typeNamePP} [ ${ppImplode(pp", ", pps)} ]",
            getSynthesized(x, "silver:langutil:pps")),
          map(\ unparse::String -> pp"${typeNamePP} { ${text(unparse)} }",
            getSynthesized(x, "silver:langutil:unparse")))),
      reflect(x).pp);
}
