grammar silver:extension:ideinterface;

synthesized attribute lexerClasses :: [String];
attribute lexerClasses occurs on SyntaxTerminalModifiers, SyntaxTerminalModifier;

nonterminal IDEInterfaceTerminalProperties with lexerClasses;
nonterminal IDEInterfaceTerminalProperty with lexerClasses;

abstract production nilIDETerminalProperties
top::IDEInterfaceTerminalProperties ::=
{
  top.lexerClasses = [];
}

abstract production consIDETerminalProperties
top::IDEInterfaceTerminalProperties ::= hd::IDEInterfaceTerminalProperty tl::IDEInterfaceTerminalProperties
{
  top.lexerClasses = hd.lexerClasses ++ tl.lexerClasses;
}

aspect default production
top::IDEInterfaceTerminalProperty ::=
{
  top.lexerClasses = [];
}

abstract production ideTermClasses
top::IDEInterfaceTerminalProperty ::= lexClasses::[String]
{
  top.lexerClasses = lexClasses;
}

{-- SYNTAX TERMINAL MODIFIERS PRODUCTIONS --}
aspect production nilTerminalMod
top::SyntaxTerminalModifiers ::=
{
  top.lexerClasses = [];
}

 aspect production consTerminalMod
top::SyntaxTerminalModifiers ::= h::SyntaxTerminalModifier  t::SyntaxTerminalModifiers
{
  -- only one syntax terminal modifier should have the lexer classes
  -- specifically the termClasses production
  top.lexerClasses =
    if (!null(h.lexerClasses))
    then h.lexerClasses
    else t.lexerClasses;
}

 aspect default production
top::SyntaxTerminalModifier ::=
{
  top.lexerClasses = [];
}

aspect production termClasses
top::SyntaxTerminalModifier ::= cls::[String]
{
  top.lexerClasses = cls;
}

function syntaxTerminalModifiersToIDEProperties
IDEInterfaceTerminalProperties ::= modifiers::SyntaxTerminalModifiers
{
  return 
  consIDETerminalProperties(
    ideTermClasses(modifiers.lexerClasses), 
    nilIDETerminalProperties());
}

