grammar silver:modification:copper;

terminal Layout_kwd 'layout' lexer classes {KEYWORD};

concrete production productionModifierLayout
top::ProductionModifier ::= 'layout' '{' terms::TermPrecList '}'
{
  top.pp = "layout {" ++ terms.pp ++ "}";
  top.location = loc(top.file, $1.line, $1.column);

  top.productionModifiers = [prodLayout(terms.precTermList)];
  top.errors := terms.errors;
}

