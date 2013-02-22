grammar silver:extension:easyterminal;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:definition:concrete_syntax;
import silver:definition:regex;

terminal Terminal_t /\'[^\'\n]*\'/ lexer classes {LITERAL};

concrete production regExprEasyTerm
top::RegExpr ::= t::Terminal_t
{
  top.pp = t.lexeme;
  top.location = $1.location;
  
  top.terminalRegExprSpec = literalRegex(substring(1, length(t.lexeme)-1, t.lexeme));
  
  forwards to regExpr('/', top.terminalRegExprSpec, '/');
}

concrete production productionRhsElemEasyReg
top::ProductionRHSElem ::= id::Name '::' reg::RegExpr
{
  top.pp = id.pp ++ "::" ++ reg.pp;
  
  local attribute regName :: [DclInfo];
  regName = getTerminalRegexDclAll(reg.terminalRegExprSpec.regString, top.env);

  top.errors <- if null(regName) 
                then [err(reg.location, "Could not find terminal declaration for " ++ reg.pp )]
                else [];

  top.errors <- if length(regName) > 1
                then [err(reg.location, "Found ambiguous possibilities for " ++ reg.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to productionRHSElem(id, $2, typerepType(if null(regName) then errorType() else head(regName).typerep));
}

concrete production productionRhsElemTypeEasyReg
top::ProductionRHSElem ::= reg::RegExpr
{
  top.pp = reg.pp;
  
  local attribute regName :: [DclInfo];
  regName = getTerminalRegexDclAll(reg.terminalRegExprSpec.regString, top.env);

  top.errors <- if null(regName) 
                then [err(reg.location, "Could not find terminal declaration for " ++ reg.pp )]
                else [];

  top.errors <- if length(regName) > 1
                then [err(reg.location, "Found ambiguous possibilities for " ++ reg.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  -- TODO: we lose location information here!
  forwards to productionRHSElemType(typerepType(if null(regName) then errorType() else head(regName).typerep));
}

concrete production aspectRHSElemEasyReg
top::AspectRHSElem ::= reg::RegExpr
{
  top.pp = reg.pp;
  
  local attribute regName :: [DclInfo];
  regName = getTerminalRegexDclAll(reg.terminalRegExprSpec.regString, top.env);

  top.errors <- if null(regName) 
                then [err(top.location, "Could not find terminal declaration for " ++ reg.pp )]
                else [];

  top.errors <- if length(regName) > 1
                then [err(reg.location, "Found ambiguous possibilities for " ++ reg.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to aspectRHSElemNone('_'); -- TODO This isn't checking if the type is right!!
}

concrete production aspectRHSElemTypedEasyReg
top::AspectRHSElem ::= id::Name '::' reg::RegExpr
{
  top.pp = id.pp ++ " :: " ++ reg.pp;
  top.location = id.location;
  
  local attribute regName :: [DclInfo];
  regName = getTerminalRegexDclAll(reg.terminalRegExprSpec.regString, top.env);

  top.errors <- if null(regName) 
                then [err(top.location, "Could not find terminal declaration for " ++ reg.pp )]
                else [];
  
  top.errors <- if length(regName) > 1
                then [err(reg.location, "Found ambiguous possibilities for " ++ reg.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to aspectRHSElemTyped(id, $2, typerepType(if null(regName) then errorType() else head(regName).typerep));
}

concrete production terminalExprReg
top::Expr ::= t::RegExpr
{
  top.pp = t.pp;
  top.location = t.location;
  
  local attribute regExpPat :: String;
  regExpPat = t.terminalRegExprSpec.regString;

  local attribute regName :: [DclInfo];
  regName = getTerminalRegexDclAll(regExpPat, top.env);

  local attribute escapedName :: String;
  escapedName = makeEscapedName(regExpPat);

  top.errors <- if null(regName) 
                then [err(top.location, "Could not find terminal declaration for " ++ t.pp )]
                else [];

  top.errors <- if length(regName) > 1
                then [err(t.location, "Found ambiguous possibilities for " ++ t.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to terminalFunction(terminal(Terminal_kwd, "terminal", t.location.line, t.location.column),
                               terminal(LParen_t, "("),
                               typerepType(if null(regName) then errorType() else head(regName).typerep),
                               terminal(Comma_t, ","),
                               stringConst(terminal(String_t, "\"" ++ escapedName ++ "\"")),
                               terminal(RParen_t, ")"));
}

function makeEscapedName
String ::= s::String
{
  local attribute ch :: String;
  ch = substring(0, 1, s);

  local attribute rest :: String;
  rest = substring(1, length(s), s);

  return if length(s) == 0 
         then ""
         else if ch == "\\" || ch == "\""
              then "\\" ++ ch ++ makeEscapedName(rest)
              else ch ++ makeEscapedName(rest);
}

