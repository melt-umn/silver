grammar silver:modification:copper;

-- It would be nice if this weren't a keyword, but...
terminal Layout_kwd 'layout' lexer classes {KEYWORD,RESERVED};

concrete production productionModifierLayout
top::ProductionModifier ::= 'layout' '{' terms::TermPrecList '}'
{
  top.pp = "layout {" ++ terms.pp ++ "}";
  top.location = loc(top.file, $1.line, $1.column);

  top.productionModifiers = [prodLayout(terms.precTermList)];
  top.errors := terms.errors;
}

