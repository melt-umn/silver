grammar silver:translation:java:concrete_syntax:copper;

attribute disambiguationGroupDcls,parserAttrDcls occurs on ParserSpec;

aspect production i_parserSpecFL
top::ParserSpec ::= locat::Decorated Location name::String start::String mods::[String] grams::[Decorated RootSpec]
{
  top.disambiguationGroupDcls = med.disambiguationGroupDcls;
  top.parserAttrDcls = med.parserAttrDcls;
}

