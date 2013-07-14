grammar silver:extension:easyterminal;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:definition:concrete_syntax;
import silver:definition:regex only regString, literalRegex;

terminal Terminal_t /\'[^\'\n]*\'/ lexer classes {LITERAL};

concrete production regExprEasyTerm
top::RegExpr ::= t::Terminal_t
{
  top.pp = t.lexeme;
  
  top.terminalRegExprSpec = literalRegex(substring(1, length(t.lexeme)-1, t.lexeme));
  
  forwards to regExpr('/', top.terminalRegExprSpec, '/', location=top.location);
}

concrete production productionRhsElemEasyReg
top::ProductionRHSElem ::= id::Name '::' reg::RegExpr
{
  top.pp = id.pp ++ "::" ++ reg.pp;
  
  local regName :: [DclInfo] = getTerminalRegexDclAll(reg.terminalRegExprSpec.regString, top.env);

  top.errors <- if null(regName) 
                then [err(reg.location, "Could not find terminal declaration for " ++ reg.pp )]
                else [];

  top.errors <- if length(regName) > 1
                then [err(reg.location, "Found ambiguous possibilities for " ++ reg.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to productionRHSElem(id, $2,
    typerepType(if null(regName) then errorType() else head(regName).typerep, location=reg.location),
    location=top.location);
}

concrete production productionRhsElemTypeEasyReg
top::ProductionRHSElem ::= reg::RegExpr
{
  top.pp = reg.pp;
  
  local regName :: [DclInfo] = getTerminalRegexDclAll(reg.terminalRegExprSpec.regString, top.env);

  top.errors <- if null(regName) 
                then [err(reg.location, "Could not find terminal declaration for " ++ reg.pp )]
                else [];

  top.errors <- if length(regName) > 1
                then [err(reg.location, "Found ambiguous possibilities for " ++ reg.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to productionRHSElemType(
    typerepType(if null(regName) then errorType() else head(regName).typerep, location=top.location),
    location=top.location);
}

concrete production aspectRHSElemEasyReg
top::AspectRHSElem ::= reg::RegExpr
{
  top.pp = reg.pp;
  
  local regName :: [DclInfo] = getTerminalRegexDclAll(reg.terminalRegExprSpec.regString, top.env);

  top.errors <- if null(regName) 
                then [err(reg.location, "Could not find terminal declaration for " ++ reg.pp )]
                else [];

  top.errors <- if length(regName) > 1
                then [err(reg.location, "Found ambiguous possibilities for " ++ reg.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to aspectRHSElemNone('_', location=reg.location); -- TODO This isn't checking if the type is right!!
}

concrete production aspectRHSElemTypedEasyReg
top::AspectRHSElem ::= id::Name '::' reg::RegExpr
{
  top.pp = id.pp ++ " :: " ++ reg.pp;
  
  local regName :: [DclInfo] = getTerminalRegexDclAll(reg.terminalRegExprSpec.regString, top.env);

  top.errors <- if null(regName) 
                then [err(reg.location, "Could not find terminal declaration for " ++ reg.pp )]
                else [];
  
  top.errors <- if length(regName) > 1
                then [err(reg.location, "Found ambiguous possibilities for " ++ reg.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to aspectRHSElemTyped(id, $2,
    typerepType(if null(regName) then errorType() else head(regName).typerep, location=reg.location),
    location=top.location);
}

concrete production terminalExprReg
top::Expr ::= t::RegExpr
{
  top.pp = t.pp;
  
  local attribute regExpPat :: String;
  regExpPat = t.terminalRegExprSpec.regString;

  local attribute regName :: [DclInfo];
  regName = getTerminalRegexDclAll(regExpPat, top.env);

  local attribute escapedName :: String;
  escapedName = makeEscapedName(regExpPat);

  top.errors <- if null(regName) 
                then [err(t.location, "Could not find terminal declaration for " ++ t.pp )]
                else [];

  top.errors <- if length(regName) > 1
                then [err(t.location, "Found ambiguous possibilities for " ++ t.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to terminalFunction('terminal', '(',
    typerepType(if null(regName) then errorType() else head(regName).typerep, location=t.location),
    ',', stringConst(terminal(String_t, "\"" ++ escapedName ++ "\""), location=t.location), ')', location=top.location);
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

