grammar silver:translation:java:concrete_syntax:copper;
import silver:definition:concrete_syntax;

attribute disambiguationGroupDcls occurs on ParserSpec;

aspect production i_parserSpec
top::ParserSpec ::= ps::Decorated ParserDcl{
  top.disambiguationGroupDcls = ps.disambiguationGroupDcls;
}