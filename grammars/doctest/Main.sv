
@@{-
   - @config noDocs off 
   - @config title = "Example Docs Page"
   - @config weight 15
   -}

@{-
  -
  - A large.
  - Set of.
  - Sentences on different lines.
  - - Bulleted list
  - - In prose
  - With @link[foo] links @link[bar] and stuff like ats @@.
  - *regular markdown* _styling_ is **passed through**
  - # Like headers
  -
  - Sentance with a - in it.
  - Complicated case of a -@link[baz]- link in dashes.
  -
  - @param args Input Arguments
  - @param ioIn input IO
  
  - @prodattr a a
  @prodattr z z
  @prodattr b b

  - @warning counter-intuitive! See @file[here/there/EveryWhere.sv] for a tutorial.
  - Or use a regular [markdown link](http://somewhere.com)

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