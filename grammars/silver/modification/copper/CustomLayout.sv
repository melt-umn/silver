grammar silver:modification:copper;

-- It would be nice if this weren't a keyword, but...
terminal Layout_kwd 'layout' lexer classes {KEYWORD,RESERVED};

concrete production productionModifierLayout
top::ProductionModifier ::= 'layout' '{' terms::TermPrecList '}'
{
  top.pp = "layout {" ++ terms.pp ++ "}";

  top.productionModifiers = [prodLayout(terms.precTermList)];
  top.errors := terms.errors;
}

concrete production productionModifierLayoutNone
top::ProductionModifier ::= 'layout' '{' '}'
{
  top.pp = "layout {}";

  top.productionModifiers = [prodLayout([])];
  top.errors := [];
}

