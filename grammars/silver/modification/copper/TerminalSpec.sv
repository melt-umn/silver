grammar silver:modification:copper;

attribute lexerClasses,submitsTo,termDominates,actionCode occurs on TerminalSpec, TerminalModifierSpec ;

synthesized attribute lexerClasses :: [String] ;
synthesized attribute termDominates :: [String];
synthesized attribute submitsTo :: [String];

aspect production i_terminalSpec
top::TerminalSpec ::= fn::String t::[Decorated TerminalModifierSpec] _
{
  top.lexerClasses = findLexerClasses(t) ;
  top.submitsTo = findSubmitsTo(t) ;
  top.termDominates = findDominates(t) ;
  top.actionCode = findActionCode(t);
}

aspect production defaultTerminalModifierSpec
top::TerminalModifierSpec ::=
{
  top.lexerClasses = [];
  top.submitsTo = [];
  top.termDominates = [];
  top.actionCode = "";
}

function lexerClassesTerminalModifierSpec
Decorated TerminalModifierSpec ::= s::[String]
{
  return decorate i_lexerClassesTerminalModifierSpec(s) with {};
}

abstract production i_lexerClassesTerminalModifierSpec
top::TerminalModifierSpec ::= s::[String]
{
  top.unparse = "lexer_class[" ++ (if null(s) then "" else "'" ++ implode("','", s) ++ "'") ++ "]";
  top.lexerClasses = s;
  forwards to defaultTerminalModifierSpec();
}

function submitsToTerminalModifierSpec
Decorated TerminalModifierSpec ::= s::[String]
{
  return decorate i_submitsToTerminalModifierSpec(s) with {};
}

abstract production i_submitsToTerminalModifierSpec
top::TerminalModifierSpec ::= s::[String]
{
  top.unparse = "submits[" ++ (if null(s) then "" else "'" ++ implode("','", s) ++ "'") ++ "]";
  top.submitsTo = s;
  forwards to defaultTerminalModifierSpec();
}

function dominatesTerminalModifierSpec
Decorated TerminalModifierSpec ::= s::[String]
{
  return decorate i_dominatesTerminalModifierSpec(s) with {};
}

abstract production i_dominatesTerminalModifierSpec
top::TerminalModifierSpec ::= s::[String]
{
  top.unparse = "dominates[" ++ (if null(s) then "" else "'" ++ implode("','", s) ++ "'") ++ "]";
  top.termDominates = s;
  forwards to defaultTerminalModifierSpec();
}

function actionCodeTerminalModifierSpec
Decorated TerminalModifierSpec ::= s::String
{
  return decorate i_actionCodeTerminalModifierSpec(s) with {};
}

abstract production i_actionCodeTerminalModifierSpec
top::TerminalModifierSpec ::= s::String
{
  top.unparse = "action \"" ++ escapeString(s) ++ "\"";
  top.actionCode = s;
  forwards to defaultTerminalModifierSpec();
}


function findLexerClasses
[String] ::= l::[Decorated TerminalModifierSpec]{
  return if null(l) then [] else head(l).lexerClasses ++ findLexerClasses(tail(l));
}

function findSubmitsTo
[String] ::= l::[Decorated TerminalModifierSpec]{
  return if null(l) then [] else head(l).submitsTo ++ findSubmitsTo(tail(l));
}

function findDominates
[String] ::= l::[Decorated TerminalModifierSpec]{
  return if null(l) then [] else head(l).termDominates ++ findDominates(tail(l));
}

function findActionCode
String ::= l::[Decorated TerminalModifierSpec]{
  return if null(l) then "" else if head(l).actionCode != "" then head(l).actionCode else findActionCode(tail(l));
}

