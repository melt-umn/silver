grammar silver:definition:concrete_syntax;
import silver:definition:core;
import silver:definition:env;
import silver:definition:regex;


nonterminal RegExpr with location, grammarName, file, pp, terminalRegExprSpec;
nonterminal TerminalModifiers with location, file, pp, terminalModifiers, errors, env, grammarName;
nonterminal TerminalModifier with location, file, pp, terminalModifiers, errors, env, grammarName;
nonterminal TerminalKeywordModifier with  location, file, pp, terminalModifiers, errors, env, grammarName;

terminal Ignore_kwd /ignore/ lexer classes {KEYWORD};
terminal Left_kwd /left/ lexer classes {KEYWORD};
terminal Association_kwd /association/ lexer classes {KEYWORD};
terminal Right_kwd /right/ lexer classes {KEYWORD};
terminal Precedence_kwd /precedence/ lexer classes {KEYWORD};

abstract production terminalDclDefault
top::AGDcl ::= t::TerminalKeywordModifier id::Name r::RegExpr tm::TerminalModifiers
{
  top.location = t.location;

  production attribute fName :: String;
  fName = top.grammarName ++ ":" ++ id.name;

  top.moduleNames = [];

  top.defs = addTermDcl(top.grammarName, id.location, fName, r.terminalRegExprSpec, emptyDefs());
  
  local attribute er2 :: [Decorated Message];
  er2 = if length(getTypeDcl(fName, top.env)) > 1
        then [err(top.location, "Type '" ++ fName ++ "' is already bound.")]
        else [];	

  top.errors := t.errors ++ er2 ++ tm.errors;

  top.parserDcls = [];
  top.nonTerminalDcls = [];
  top.terminalDcls = [terminalSpec(fName, t.terminalModifiers ++ tm.terminalModifiers, r.terminalRegExprSpec)];			   
  top.ruleDcls = [];

}


concrete production terminalDcl
top::AGDcl ::= 'terminal' id::Name r::RegExpr ';'
{
  top.pp = "terminal " ++ id.pp ++ r.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to terminalDclDefault(terminalKeywordModifierDefault(), id, r, terminalModifiersNone());
}

concrete production terminalDclModifiers
top::AGDcl ::= 'terminal' id::Name r::RegExpr tm::TerminalModifiers ';'
{
  top.pp = "terminal " ++ id.pp ++ " " ++ r.pp ++ " " ++ tm.pp ++ ";";
  top.location = loc(top.file, $1.line, $1.column);

  forwards to terminalDclDefault(terminalKeywordModifierDefault(), id, r, tm);
}

concrete production terminalDclKwdModifiers
top::AGDcl ::= t::TerminalKeywordModifier 'terminal' id::Name r::RegExpr ';'
{
  top.pp = t.pp ++ " terminal " ++ id.pp ++ " " ++ r.pp ++ ";";
  top.location = t.location;

  forwards to terminalDclDefault(t, id, r, terminalModifiersNone());
}

concrete production terminalDclAllModifiers
top::AGDcl ::= t::TerminalKeywordModifier 'terminal' id::Name r::RegExpr tm::TerminalModifiers ';'
{
  top.pp = t.pp ++ " terminal " ++ id.pp ++ " " ++ r.pp ++ " " ++ tm.pp ++ ";";
  top.location = t.location;

  forwards to terminalDclDefault(t, id, r, tm);
}


concrete production terminalKeywordModifierIgnore
top::TerminalKeywordModifier ::= 'ignore'
{
  top.pp = "ignore";
  top.location = loc(top.file, $1.line, $1.column);

  top.terminalModifiers = [ignoreTerminalModifierSpec()];

  forwards to terminalKeywordModifierDefault();
}

abstract production terminalKeywordModifierDefault
top::TerminalKeywordModifier ::= {
  top.pp = "";
  top.location = loc(top.file, -1, -1);

  top.errors := [];

  top.terminalModifiers = [];
}


abstract production terminalModifierDefault
top::TerminalModifier ::=
{
  top.errors := [];
  top.terminalModifiers = [];
}

abstract production terminalModifiersNone
top::TerminalModifiers ::= 
{

  production attribute tm :: TerminalModifier;
  tm = terminalModifierDefault();

  top.pp = tm.pp;
  top.location = tm.location;

  top.terminalModifiers = tm.terminalModifiers;

  top.errors := tm.errors;
}
concrete production terminalModifierSingle
top::TerminalModifiers ::= tm::TerminalModifier
{
  top.pp = tm.pp;
  top.location = tm.location;

  top.terminalModifiers = tm.terminalModifiers;
  top.errors := tm.errors; 
}

concrete production terminalModifiersCons
top::TerminalModifiers ::= h::TerminalModifier ',' t::TerminalModifiers
{
  top.pp = h.pp ++ ", " ++ t.pp;
  top.location = loc(top.file, $2.line, $2.column);

  top.terminalModifiers = h.terminalModifiers ++ t.terminalModifiers;

  top.errors := h.errors ++ t.errors;
}

concrete production terminalModifierLeft
top::TerminalModifier ::= 'association' '=' 'left'
{
  top.pp = "association = left";
  top.location = loc(top.file, $1.line, $1.column);

  top.terminalModifiers = [associationTerminalModifierSpec("left")];
  
  forwards to terminalModifierDefault();
}

concrete production terminalModifierRight
top::TerminalModifier ::= 'association' '=' 'right'
{
  top.pp = "association = right";
  top.location = loc(top.file, $1.line, $1.column);

  top.terminalModifiers = [associationTerminalModifierSpec("right")];

  forwards to terminalModifierDefault();
}

concrete production terminalModifierPrecedence
top::TerminalModifier ::= 'precedence' '=' i::Int_t
{
  top.pp = "precedence = " ++ i.lexeme;
  top.location = loc(top.file, $1.line, $1.column);

  top.terminalModifiers = [precedenceTerminalModifierSpec(toInt(i.lexeme))];

  forwards to terminalModifierDefault();
}

concrete production regExpr
top::RegExpr ::= '/' r::Regex_R '/'
{
  top.pp = "/" ++ r.regString ++ "/";
  top.location = loc(top.file, $1.line, $1.column);
  top.terminalRegExprSpec = r;
}

