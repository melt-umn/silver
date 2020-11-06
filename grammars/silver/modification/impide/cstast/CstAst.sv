grammar silver:modification:impide:cstast;

imports silver:definition:concrete_syntax:ast;
imports silver:definition:regex;
imports silver:definition:type;
imports silver:definition:env;

imports silver:modification:impide:spec;

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
