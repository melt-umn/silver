
@{-@param on First Line
  -
  - A large.
  - Set of.
  - Sentences on different lines.
  - With @link[foo] links @link[bar] and stuff like ats @@.
  - @param abc right after prose
  -
  - Sentance with a - in it.
  - Complicated case of a -@link[baz]- link in dashes.
  -
  - @param foo foo desc.
               more foo desc.
  - @param bar bar desc.


  - @return return desc
  -}
function main
IOVal<Integer> ::= args::[String] ioIn::IO
{
  return ioval(ioIn, 0);
}

function dummyFunction
Integer ::=
{
  return 1;
}