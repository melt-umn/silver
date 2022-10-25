grammar silver:compiler:extension:datalog;

import silver:langutil;
import silver:langutil:pp;

synthesized attribute params :: [String];
synthesized attribute factHead :: String;
nonterminal Fact with factHead, params, pp;

synthesized attribute datalogID :: String;

class DatalogRepresentable a {
  @{-
    Represents value with it's datalog representation.
  -}
  datalogID :: (String ::= a);

}

-- Default instances.
-- instance attribute datalogID {} occurs on a => DatalogRepresentable a {
--   datalogID = (.datalogID);
-- }

-- instance attribute datalogID {} occurs on a => DatalogRepresentable Decorated a with i {
--   datalogID = (.datalogID);
-- }

instance DatalogRepresentable String {
  datalogID = id;
}





-- TODO: Make function to capitalize the string name so we dont have issue's reading in input.
abstract production factTwo
top::Fact ::= name::String first::String second::String
{
  top.factHead = name;
  top.params = [first, second];
  top.pp = pp"${name}: ${first} <-> ${second}";
}


abstract production factThree
top::Fact ::= name::String first::String second::String third::String
{
  top.factHead = name;
  top.params = [first, second, third];
  top.pp = pp"${name}: ${first} <-> ${second} <-> ${third}";
}


abstract production factN
top::Fact ::= name::String params::[String]
{
  top.factHead = name;
  top.params = params;
  top.pp = pp"${name}: ${params}";
}


function datalogFactsFromList
DatalogRepresentable a => [Fact] ::= name::String top::String lst::[a]
{
   return map(\target::a -> factTwo(name,top,datalogID(target)),lst);
}


