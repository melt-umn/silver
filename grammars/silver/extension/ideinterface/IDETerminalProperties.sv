grammar silver:extension:ideinterface;

synthesized attribute lexerClasses :: [String];
attribute lexerClasses occurs on SyntaxTerminalModifiers, SyntaxTerminalModifier;

synthesized attribute isPrefix :: Boolean;
synthesized attribute regexString :: String;
nonterminal IDEInterfaceTerminalProperties with 
  isPrefix, ignored, lexerClasses, regexString;
nonterminal IDEInterfaceTerminalProperty with 
  isPrefix, ignored, lexerClasses, regexString;

abstract production nilIDETerminalProperties
top::IDEInterfaceTerminalProperties ::=
{
  top.lexerClasses = [];
  top.ignored = false;
  top.isPrefix = false;
  top.regexString = "";
}

abstract production consIDETerminalProperties
top::IDEInterfaceTerminalProperties ::= hd::IDEInterfaceTerminalProperty tl::IDEInterfaceTerminalProperties
{
  top.lexerClasses = hd.lexerClasses ++ tl.lexerClasses;
  top.ignored = hd.ignored || tl.ignored;
  top.isPrefix = hd.isPrefix || tl.isPrefix;
  top.regexString = if stringEq("", tl.regexString) then hd.regexString else tl.regexString;
}

aspect default production
top::IDEInterfaceTerminalProperty ::=
{
  top.lexerClasses = [];
  top.ignored = false;
  top.isPrefix = false;
  top.regexString = "";
}

abstract production ideTermClasses
top::IDEInterfaceTerminalProperty ::= lexClasses::[String]
{
  top.lexerClasses = lexClasses;
}

abstract production ideIgnored
top::IDEInterfaceTerminalProperty ::= i::Boolean
{
  -- passed in so we can automatically construct these from SyntaxTerminalModifiers
  -- without having conditionals 
  top.ignored = i;
}

abstract production ideIsPrefix
top::IDEInterfaceTerminalProperty ::= i::Boolean
{
  top.isPrefix = i;
}

abstract production ideTerminalRegex
top::IDEInterfaceTerminalProperty ::= reg::String
{
  top.regexString = reg;
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
  consIDETerminalProperties(
    ideIgnored(modifiers.ignored),
    nilIDETerminalProperties()
  ));
}

