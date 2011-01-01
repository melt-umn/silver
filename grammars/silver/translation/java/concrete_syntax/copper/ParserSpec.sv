grammar silver:translation:java:concrete_syntax:copper;

attribute disambiguationGroupDcls,parserAttrDcls occurs on ParserSpec;

aspect production i_parserSpec
top::ParserSpec ::= ps::Decorated ParserDcl
{
  top.disambiguationGroupDcls = ps.disambiguationGroupDcls;
  top.parserAttrDcls = ps.parserAttrDcls;
}

aspect production i_parserSpecFL
top::ParserSpec ::= name::String start::String mods::[String] grams::[Decorated RootSpec]
{
  top.disambiguationGroupDcls = med.disambiguationGroupDcls;
  top.parserAttrDcls = med.parserAttrDcls;
}

