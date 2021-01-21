grammar silver:compiler:definition:concrete_syntax;

import silver:langutil:pp;
import silver:regex as abs;
import silver:regex:concrete_syntax;

terminal Ignore_kwd      'ignore'      lexer classes {KEYWORD};
terminal Marking_kwd     'marking'     lexer classes {KEYWORD};
terminal Named_kwd       'named'       lexer classes {KEYWORD};
terminal Left_kwd        'left'        lexer classes {KEYWORD};
terminal Association_kwd 'association' lexer classes {KEYWORD};
terminal Right_kwd       'right'       lexer classes {KEYWORD};

-- We actually need to reserved this due to its appearance in PRODUCTION modifiers.
terminal Precedence_kwd  'precedence'  lexer classes {KEYWORD,RESERVED};

abstract production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr tm::TerminalModifiers
{
  top.unparse = t.unparse ++ "terminal " ++ id.unparse ++ " " ++ r.unparse ++ " " ++ tm.unparse ++ ";";

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.defs := [termDef(top.grammarName, id.location, fName, r.terminalRegExprSpec, r.easyName)];

  top.errors <-
    if length(getTypeDclAll(fName, top.env)) > 1
    then [err(id.location, "Type '" ++ fName ++ "' is already bound.")]
    else [];

  top.errors <-
    if isLower(substring(0,1,id.name))
    then [err(id.location, "Types must be capitalized. Invalid terminal name " ++ id.name)]
    else [];

  -- This is a crude check, but effective.
  top.errors <-
    if indexOf("\\n", r.unparse) != -1 && indexOf("\\r", r.unparse) == -1
    then [wrn(r.location, "Regex contains '\\n' but not '\\r'. This is your reminder about '\\r\\n' newlines.")]
    else [];

  propagate errors;

  top.syntaxAst := [
    syntaxTerminal(fName, r.terminalRegExprSpec,
      foldr(consTerminalMod, nilTerminalMod(), t.terminalModifiers ++ tm.terminalModifiers))];
}

concrete production terminalDclKwdModifiers
top::AGDcl ::= t::TerminalKeywordModifier 'terminal' id::Name r::RegExpr ';'
{
  forwards to terminalDclDefault(t, id, r, terminalModifiersNone(location=$5.location), location=top.location);
}

concrete production terminalDclAllModifiers
top::AGDcl ::= t::TerminalKeywordModifier 'terminal' id::Name r::RegExpr tm::TerminalModifiers ';'
{
  forwards to terminalDclDefault(t, id, r, tm, location=top.location);
}

{--
 - This exists as a catch-all for representing regular expressions for terminals.
 - There's only one option here, but it's an extension point.
 -}
nonterminal RegExpr with config, location, grammarName, unparse, terminalRegExprSpec, easyName;

synthesized attribute terminalRegExprSpec :: abs:Regex;
synthesized attribute easyName :: Maybe<String>;

concrete production regExpr_c
top::RegExpr ::= '/' r::Regex '/'
layout {}
{
  top.unparse = "/" ++ r.unparse ++ "/";
  forwards to regExpr(r.ast, location=top.location);
}

abstract production regExpr
top::RegExpr ::= r::abs:Regex
{
  top.unparse = "/" ++ show(80, r.pp) ++ "/";
  top.terminalRegExprSpec = r;
  top.easyName = nothing();
}


closed nonterminal TerminalKeywordModifier with unparse, location, terminalModifiers;

concrete production terminalKeywordModifierIgnore
top::TerminalKeywordModifier ::= 'ignore'
{
  top.unparse = "ignore ";

  top.terminalModifiers := [termIgnore()];
}

concrete production terminalKeywordModifierMarking
top::TerminalKeywordModifier ::= 'marking'
{
  top.unparse = "marking ";

  top.terminalModifiers := [termMarking()];
}

concrete production terminalKeywordModifierNone
top::TerminalKeywordModifier ::=
{
  top.unparse = "";

  top.terminalModifiers := [];
}


nonterminal TerminalModifiers with config, location, unparse, terminalModifiers, errors, env, grammarName, compiledGrammars, flowEnv;
closed nonterminal TerminalModifier with config, location, unparse, terminalModifiers, errors, env, grammarName, compiledGrammars, flowEnv;

monoid attribute terminalModifiers :: [SyntaxTerminalModifier];

propagate terminalModifiers, errors on TerminalModifiers;

abstract production terminalModifiersNone
top::TerminalModifiers ::=
{
  top.unparse = "";
}
concrete production terminalModifierSingle
top::TerminalModifiers ::= tm::TerminalModifier
{
  top.unparse = tm.unparse;
}
concrete production terminalModifiersCons
top::TerminalModifiers ::= h::TerminalModifier ',' t::TerminalModifiers
{
  top.unparse = h.unparse ++ ", " ++ t.unparse;
}

concrete production terminalModifierLeft
top::TerminalModifier ::= 'association' '=' 'left'
{
  top.unparse = "association = left";

  top.terminalModifiers := [termAssociation("left")];
  top.errors := [];
}
concrete production terminalModifierRight
top::TerminalModifier ::= 'association' '=' 'right'
{
  top.unparse = "association = right";

  top.terminalModifiers := [termAssociation("right")];
  top.errors := [];
}

concrete production terminalModifierPrecedence
top::TerminalModifier ::= 'precedence' '=' i::Int_t
{
  top.unparse = "precedence = " ++ i.lexeme;

  top.terminalModifiers := [termPrecedence(toInteger(i.lexeme))];
  top.errors := [];
}

concrete production terminalModifierNamed
top::TerminalModifier ::= 'named' name::String_t
{
  top.unparse = "named " ++ name.lexeme;

  top.terminalModifiers := [termPrettyName(substring(1, length(name.lexeme) - 1, name.lexeme))];
  top.errors := [];
}
