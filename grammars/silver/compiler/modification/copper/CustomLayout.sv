grammar silver:compiler:modification:copper;

-- It would be nice if this weren't a keyword, but...
terminal Layout_kwd 'layout' lexer classes {KEYWORD,RESERVED};

concrete production productionModifierLayout
top::ProductionModifier ::= 'layout' '{' terms::TermList '}'
{
  top.unparse = "layout {" ++ terms.unparse ++ "}";

  top.productionModifiers := [prodLayout(terms.termList)];
  top.errors := terms.errors;
}

concrete production productionModifierLayoutNone
top::ProductionModifier ::= 'layout' '{' '}'
{
  top.unparse = "layout {}";

  top.productionModifiers := [prodLayout([])];
  top.errors := [];
}

concrete production nonterminalModifierLayout
top::NonterminalModifier ::= 'layout' '{' terms::TermList '}'
{
  top.unparse = "layout {" ++ terms.unparse ++ "}";
  
  top.nonterminalModifiers := [ntLayout(terms.termList)];
  top.errors := terms.errors;
}

concrete production nonterminalModifierLayoutNone
top::NonterminalModifier ::= 'layout' '{' '}'
{
  top.unparse = "layout {}";
  
  top.nonterminalModifiers := [ntLayout([])];
  top.errors := [];
}

attribute customLayout occurs on ParserComponents, ParserComponent;

propagate customLayout on ParserComponents, ParserComponent;

aspect default production
top::ParserComponent ::=
{
  propagate customLayout;
}

concrete production parserComponentLayout
top::ParserComponent ::= 'layout' '{' terms::TermList '}' ';'
{
  top.unparse = "layout {" ++ terms.unparse ++ "};";
  top.customLayout <- just(terms.termList);
}

concrete production parserComponentLayoutNone
top::ParserComponent ::= 'layout' '{' '}' ';'
{
  top.unparse = "layout {};";
  top.customLayout <- just([]);
}

