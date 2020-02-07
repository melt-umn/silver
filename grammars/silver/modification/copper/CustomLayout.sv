grammar silver:modification:copper;

-- It would be nice if this weren't a keyword, but...
terminal Layout_kwd 'layout' lexer classes {KEYWORD,RESERVED};

concrete production productionModifierLayout
top::ProductionModifier ::= 'layout' '{' terms::TermPrecList '}'
{
  top.unparse = "layout {" ++ terms.unparse ++ "}";

  top.productionModifiers = [prodLayout(terms.precTermList)];
  top.errors := terms.errors;
}

concrete production productionModifierLayoutNone
top::ProductionModifier ::= 'layout' '{' '}'
{
  top.unparse = "layout {}";

  top.productionModifiers = [prodLayout([])];
  top.errors := [];
}

concrete production nonterminalModifierLayout
top::NonterminalModifier ::= 'layout' '{' terms::TermPrecList '}'
{
  top.unparse = "layout {" ++ terms.unparse ++ "}";
  
  top.nonterminalModifiers = [ntLayout(terms.precTermList)];
  top.errors := terms.errors;
}

concrete production nonterminalModifierLayoutNone
top::NonterminalModifier ::= 'layout' '{' '}'
{
  top.unparse = "layout {}";
  
  top.nonterminalModifiers = [ntLayout([])];
  top.errors := [];
}
