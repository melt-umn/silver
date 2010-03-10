grammar silver:translation:java:concrete_syntax:copper;
import silver:definition:concrete_syntax;

attribute disambiguationGroupDcls occurs on ParserSpec;
attribute parserAttrDcls occurs on ParserSpec;

aspect production i_parserSpec
top::ParserSpec ::= ps::Decorated ParserDcl{
  top.disambiguationGroupDcls = ps.disambiguationGroupDcls;
  top.parserAttrDcls = ps.parserAttrDcls;
}

aspect production i_parserSpecFL
--top::ParserSpec ::= name::String start::String mods::[String] grams::[Decorated RootSpec] {
top::ParserSpec ::= _ _ _ _ {
  top.disambiguationGroupDcls = med.disambiguationGroupDcls;
  top.parserAttrDcls = med.parserAttrDcls;
}

