grammar silver:extension:easyterminal;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type:higherorder;
import silver:definition:concrete_syntax;
import silver:definition:regex;

terminal Terminal_t /[\']([^\']|([\\][\']))*[\']/;

function findTerminalWithRegex
[Decorated DclInfo] ::= re::String e::Decorated Env
{
  return findTerminalWithRegexHelp(re, collapseEnvScope(e.typeTree));
}

function findTerminalWithRegexHelp
[Decorated DclInfo] ::= re::String e::[Decorated DclInfo]
{
  local attribute hre :: Decorated Regex_R;
  hre = case head(e) of
          termDcl(_, _, _, regex) -> regex
        | _                       -> decorate Rtoeps() with {} -- TODO: this might be fragile.
        end;
        
  return if null(e) then []
         else (if hre.regString == re then [head(e)] else [])
              ++ findTerminalWithRegexHelp(re, tail(e));
}

concrete production regExprEasyTerm
top::RegExpr ::= t::Terminal_t
{
  top.pp = t.lexeme;
  top.location = loc(top.file, t.line, t.column);
  top.terminalRegExprSpec = decorate literalRegex(substring(1, length(t.lexeme)-1, t.lexeme)) with {};
}

concrete production productionRhsElemEasyReg
top::ProductionRHSElem ::= id::Name '::' reg::RegExpr
{
  local attribute regName :: [Decorated DclInfo];
  regName = findTerminalWithRegex(reg.terminalRegExprSpec.regString, top.env);

  top.errors <- if null(regName) 
                then [err(reg.location, "Could not find terminal declaration for " ++ reg.pp )]
                else [];

  top.errors <- if length(regName) > 1
                then [err(reg.location, "Found ambiguous possibilities for " ++ reg.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to productionRHSElem(id, $2, typerepType(if null(regName) then topTypeRep() else head(regName).typerep));
}

concrete production productionRhsElemTypeEasyReg
top::ProductionRHSElem ::= reg::RegExpr
{
  local attribute regName :: [Decorated DclInfo];
  regName = findTerminalWithRegex(reg.terminalRegExprSpec.regString, top.env);

  top.errors <- if null(regName) 
                then [err(reg.location, "Could not find terminal declaration for " ++ reg.pp )]
                else [];

  top.errors <- if length(regName) > 1
                then [err(reg.location, "Found ambiguous possibilities for " ++ reg.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to productionRHSElemType(typerepType(if null(regName) then topTypeRep() else head(regName).typerep));
}

concrete production aspectRHSElemEasyReg
top::AspectRHSElem ::= reg::RegExpr
{
  local attribute regName :: [Decorated DclInfo];
  regName = findTerminalWithRegex(reg.terminalRegExprSpec.regString, top.env);

  top.errors <- if null(regName) 
                then [err(top.location, "Could not find terminal declaration for " ++ reg.pp )]
                else [];

  top.errors <- if length(regName) > 1
                then [err(reg.location, "Found ambiguous possibilities for " ++ reg.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to aspectRHSElemNone('_');
}

concrete production aspectRHSElemTypedEasyReg
top::AspectRHSElem ::= id::Name '::' reg::RegExpr
{
  local attribute regName :: [Decorated DclInfo];
  regName = findTerminalWithRegex(reg.terminalRegExprSpec.regString, top.env);

  top.errors <- if null(regName) 
                then [err(top.location, "Could not find terminal declaration for " ++ reg.pp )]
                else [];
  
  top.errors <- if length(regName) > 1
                then [err(reg.location, "Found ambiguous possibilities for " ++ reg.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to aspectRHSElemTyped(id, $2, typerepType(if null(regName) then topTypeRep() else head(regName).typerep));
}

concrete production terminalExprReg
top::Expr ::= t::RegExpr
{
  local attribute regExpPat :: String;
  regExpPat = t.terminalRegExprSpec.regString;

  local attribute regName :: [Decorated DclInfo];
  regName = findTerminalWithRegex(regExpPat, top.env);

  local attribute escapedName :: String;
  escapedName = makeEscapedName(substring(1, length(regExpPat)-1, regExpPat));

  top.errors := if null(regName) 
                then [err(top.location, "Could not find terminal declaration for " ++ t.pp )]
                else [];

  top.errors <- if length(regName) > 1
                then [err(t.location, "Found ambiguous possibilities for " ++ t.pp ++ "\n" ++ printPossibilities(regName))]
                else [];

  forwards to terminalFunction(terminal(Terminal_kwd, "terminal", t.location.line, t.location.column),
                               terminal(LParen_t, "("),
                               typerepType(if null(regName) then topTypeRep() else head(regName).typerep),
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

