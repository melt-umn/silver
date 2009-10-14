grammar silver:definition:concrete_syntax;
import silver:definition:env;
import silver:definition:regex;

nonterminal TerminalSpec with terminalModifiers, terminalName, terminalRegExprSpec, unparse, ignoreTerminal, parserPrecedence, parserAssociation;

synthesized attribute terminalModifiers :: [Decorated TerminalModifierSpec];
synthesized attribute ignoreTerminal :: Boolean;
synthesized attribute terminalName :: String;
synthesized attribute parserAssociation :: String;
synthesized attribute parserPrecedence :: Integer;
synthesized attribute terminalRegExprSpec :: Decorated Regex_R;

function terminalSpec
Decorated TerminalSpec ::= fn::String t::[Decorated TerminalModifierSpec] reg::Decorated Regex_R
{
  return decorate i_terminalSpec(fn, t, reg) with {};
}
abstract production i_terminalSpec
top::TerminalSpec ::= fn::String t::[Decorated TerminalModifierSpec] reg::Decorated Regex_R
{
  top.unparse = "('" ++ fn ++ "', [" ++ foldModifiers(t) ++ "], /" ++ reg.regString ++ "/)";
  top.terminalName = fn;
  top.terminalRegExprSpec = reg;

  top.ignoreTerminal = findIgnore(t);
  top.parserPrecedence = findPrecedence(t);
  top.parserAssociation = findAssociation(t);

  top.terminalModifiers = t;
}

function findIgnore
Boolean ::= l::[Decorated TerminalModifierSpec]{
  return !null(l) && (head(l).ignoreTerminal || findIgnore(tail(l))); 
}

function findPrecedence
Integer ::= l::[Decorated TerminalModifierSpec]{
  return if null(l) then 0 else if head(l).parserPrecedence != 0 then head(l).parserPrecedence else findPrecedence(tail(l));
}

function findAssociation
String ::= l::[Decorated TerminalModifierSpec]{
  return if null(l) then "nonassoc" else if head(l).parserAssociation != "" then head(l).parserAssociation else findAssociation(tail(l));
}

function foldModifiers
String ::= l::[Decorated TerminalModifierSpec]
{
  return if null(l) then "" else head(l).unparse ++ (if null(tail(l)) then "" else ", " ++ foldModifiers(tail(l)));
}

function terminalCheck
Boolean ::= s::String l::[Decorated TerminalSpec]
{
  return !null(l) && ( s == head(l).terminalName || terminalCheck(s, tail(l)));
}


nonterminal TerminalModifierSpec with unparse, ignoreTerminal, parserPrecedence, parserAssociation;

abstract production defaultTerminalModifierSpec
top::TerminalModifierSpec ::={
  top.unparse = "";
  top.ignoreTerminal = false;
  top.parserPrecedence = 0;
  top.parserAssociation = "";
}

function ignoreTerminalModifierSpec
Decorated TerminalModifierSpec ::={
  return decorate i_ignoreTerminalModifierSpec() with {};
}

abstract production i_ignoreTerminalModifierSpec
top::TerminalModifierSpec ::={
  top.unparse = "ignore";
  top.ignoreTerminal = true;
  forwards to defaultTerminalModifierSpec();
}

function precedenceTerminalModifierSpec
Decorated TerminalModifierSpec ::= i::Integer{
  return decorate i_precedenceTerminalModifierSpec(i) with {};
}

abstract production i_precedenceTerminalModifierSpec
top::TerminalModifierSpec ::= i::Integer{
  top.unparse = "precedence " ++ toString(i);
  top.parserPrecedence = i;
  forwards to defaultTerminalModifierSpec();
}


function associationTerminalModifierSpec
Decorated TerminalModifierSpec ::= s::String{
  return decorate i_associationTerminalModifierSpec(s) with {};
}

abstract production i_associationTerminalModifierSpec
top::TerminalModifierSpec ::= s::String{
  top.unparse = "association " ++ s;
  top.parserAssociation = s;
  forwards to defaultTerminalModifierSpec();
}

