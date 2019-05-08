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

-- a list of conflicts for treesitter that exist on lexer classes
-- the first element in the pair is the name of the disambiguation function
-- the second element is the terms involved
attribute terminalConflicts occurs on SyntaxLexerClassModifier, SyntaxLexerClassModifiers;
aspect production lexerClassDisambiguate
top::SyntaxLexerClassModifier ::= acode::String
{
  top.terminalConflicts = [pair(funName, terms)];
}

{- We default ALL attributes, so we can focus only on those that are interesting in each case... -}
aspect default production
top::SyntaxLexerClassModifier ::=
{
  top.terminalConflicts = [];
}

aspect production consLexerClassMod
top::SyntaxLexerClassModifiers ::= h::SyntaxLexerClassModifier  t::SyntaxLexerClassModifiers
{
  top.terminalConflicts = h.terminalConflicts ++ t.terminalConflicts;
}

aspect production nilLexerClassMod
top::SyntaxLexerClassModifiers ::=
{
  top.terminalConflicts = [];
}
