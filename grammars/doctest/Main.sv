
@@{-
   - @config split on
   - @config title = "Example Docs Page"
   - @config weight 15
   -}

@{-@param on First Line
  -
  - A large.
  - Set of.
  - Sentences on different lines.
  - - Bulleted list
  - - In prose
  - With @link[foo] links @link[bar] and stuff like ats @@.
  - @param abc right after prose
  - *regular markdown* _styling_ is **passed through**
  - # Like headers
  -
  - Sentance with a - in it.
  - Complicated case of a -@link[baz]- link in dashes.
  -
  - @param foo foo desc.
               more foo desc.
  - @param bar bar desc.

  - @warning counter-intuitive! See @weblink[here](http://foo.com/bar) for a tutorial.

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