{-- SYNTAX TERMINAL MODIFIERS PRODUCTIONS --}
aspect production nilTerminalMod
top::SyntaxTerminalModifiers ::=
{
  top.lexer_classes = [];
}

aspect production consTerminalMod
top::SyntaxTerminalModifiers ::= h::SyntaxTerminalModifier  t::SyntaxTerminalModifiers
{
  -- only one syntax terminal modifier should have the lexer classes
  -- specifically the termClasses production
  top.lexer_classes = 
    if (!null(h.lexer_classes)) 
    then h.lexer_classes 
    else t.lexer_classes;
}

aspect default production
top::SyntaxTerminalModifier ::=
{
  top.lexer_classes = [];
}

aspect production termClasses
top::SyntaxTerminalModifier ::= cls::[String]
{
  top.lexer_classes = cls;
}
