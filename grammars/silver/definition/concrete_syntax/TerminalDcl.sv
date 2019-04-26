grammar silver:definition:concrete_syntax;

import silver:definition:regex;

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

  top.defs = [termDef(top.grammarName, id.location, fName, r.terminalRegExprSpec)];

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
    if indexOf("\\n", r.terminalRegExprSpec.regString) != -1 && indexOf("\\r", r.terminalRegExprSpec.regString) == -1
    then [wrn(r.location, "Regex contains '\\n' but not '\\r'. This is your reminder about '\\r\\n' newlines.")]
    else [];

  top.errors := tm.errors;

  top.syntaxAst = [
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
nonterminal RegExpr with config, location, grammarName, unparse, terminalRegExprSpec;

synthesized attribute terminalRegExprSpec :: Regex;

concrete production regExpr
top::RegExpr ::= '/' r::Regex '/'
{
  top.unparse = "/" ++ r.regString ++ "/";
  top.terminalRegExprSpec = r;
}


nonterminal TerminalKeywordModifier with unparse, location, terminalModifiers;

concrete production terminalKeywordModifierIgnore
top::TerminalKeywordModifier ::= 'ignore'
{
  top.unparse = "ignore ";

  top.terminalModifiers = [termIgnore()];
}

concrete production terminalKeywordModifierMarking
top::TerminalKeywordModifier ::= 'marking'
{
  top.unparse = "marking ";

  top.terminalModifiers = [termMarking()];
}

concrete production terminalKeywordModifierNone
top::TerminalKeywordModifier ::=
{
  top.unparse = "";

  top.terminalModifiers = [];
}


nonterminal TerminalModifiers with config, location, unparse, terminalModifiers, errors, env, grammarName, compiledGrammars, flowEnv;
nonterminal TerminalModifier with config, location, unparse, terminalModifiers, errors, env, grammarName, compiledGrammars, flowEnv;

synthesized attribute terminalModifiers :: [SyntaxTerminalModifier];

abstract production terminalModifiersNone
top::TerminalModifiers ::=
{
  top.unparse = "";

  top.terminalModifiers = [];
  top.errors := [];
}
concrete production terminalModifierSingle
top::TerminalModifiers ::= tm::TerminalModifier
{
  top.unparse = tm.unparse;

  top.terminalModifiers = tm.terminalModifiers;
  top.errors := tm.errors;
}
concrete production terminalModifiersCons
top::TerminalModifiers ::= h::TerminalModifier ',' t::TerminalModifiers
{
  top.unparse = h.unparse ++ ", " ++ t.unparse;

  top.terminalModifiers = h.terminalModifiers ++ t.terminalModifiers;
  top.errors := h.errors ++ t.errors;
}

concrete production terminalModifierLeft
top::TerminalModifier ::= 'association' '=' 'left'
{
  top.unparse = "association = left";

  top.terminalModifiers = [termAssociation("left")];
  top.errors := [];
}
concrete production terminalModifierRight
top::TerminalModifier ::= 'association' '=' 'right'
{
  top.unparse = "association = right";

  top.terminalModifiers = [termAssociation("right")];
  top.errors := [];
}

concrete production terminalModifierPrecedence
top::TerminalModifier ::= 'precedence' '=' i::Int_t
{
  top.unparse = "precedence = " ++ i.lexeme;

  top.terminalModifiers = [termPrecedence(toInteger(i.lexeme))];
  top.errors := [];
}

concrete production terminalModifierNamed
top::TerminalModifier ::= 'named' name::String_t
{
  top.unparse = "named " ++ name.lexeme;

  top.terminalModifiers = [termPrettyName(substring(1, length(name.lexeme) - 1, name.lexeme))];
  top.errors := [];
}
