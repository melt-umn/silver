grammar silver:compiler:modification:impide:cstast;

imports silver:compiler:definition:concrete_syntax:ast;
imports silver:compiler:definition:type;
imports silver:compiler:definition:env;

imports silver:regex:concrete_syntax;

imports silver:compiler:modification:impide:spec;

aspect default production
top::SyntaxRoot ::=
{
  propagate fontList, classFontList;
}

aspect production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax  customStartLayout::Maybe<[String]>  terminalPrefixes::[Pair<String String>]  componentGrammarMarkingTerminals::[Pair<String [String]>]
{
  -- 1) font information
  top.fontList := s2.fontList;
  top.classFontList := s2.classFontList;
}
