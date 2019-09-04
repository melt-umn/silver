grammar silver:modification:impide:cstast;

imports silver:definition:concrete_syntax:ast;
imports silver:definition:regex;
imports silver:definition:type;
imports silver:definition:env;

imports silver:modification:impide:spec;

aspect default production
top::SyntaxRoot ::=
{
  top.fontList = error("This should only ever be demanded from cstRoot.");
  top.classFontList = error("This should only ever be demanded from cstRoot.");
}

aspect production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax  terminalPrefixes::[Pair<String String>]
{
  -- 1) font information
  top.fontList = s2.fontList;
  top.classFontList = s2.classFontList;
}

