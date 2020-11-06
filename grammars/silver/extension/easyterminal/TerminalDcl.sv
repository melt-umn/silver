grammar silver:extension:easyterminal;

import silver:definition:core;
import silver:definition:env;
import silver:definition:type;
import silver:definition:type:syntax;
import silver:definition:concrete_syntax;
import silver:definition:regex only Regex, regString, regexLiteral;

terminal Terminal_t /\'[^\'\r\n]*\'/ lexer classes {LITERAL};

-- TODO: refactor this to actually create two separate terminal declarations, one regular regex, one single quote.
    -- TODO: alternatively, we keep this as a 'RegExpr', but we introduce "added terminal modifiers" synthesized
    -- attribute on RegExpr. This would preserve the marking token aspect of the extension here.
-- TODO: Add a 'quoted name' terminal modifier to regular terminal declarations.
    -- e.g. "terminal Attr /_?_?attribute_?_?/ quoted='__attribute__';"
-- TODO: Make this declaration forward to regular terminal declaration, adding the "quoted" modifier.

-- TODO: We could probably remove single quoted terminal references from all the RHSs, and add them to the Type syntax
    -- Standing in the way of doing this is the busted aspect syntax. We'd have to fix that first. See the forward with the TODO.


{-- Introduce single quoted terminal declarations -}
concrete production regExprEasyTerm
top::RegExpr ::= t::Terminal_t
{
  top.unparse = t.lexeme;
  
  top.terminalRegExprSpec = regexLiteral(substring(1, length(t.lexeme) - 1, t.lexeme));
  
  forwards to regExpr('/', top.terminalRegExprSpec, '/', location=top.location);
}

{-- Abstracts away looking up terminals in the environment -}
nonterminal EasyTerminalRef with config, location, grammarName, unparse, errors, typerep, easyString, env, dcls;

{-- String literal between quotes. e.g. 'hi"' is hi" -}
synthesized attribute easyString :: String;

concrete production easyTerminalRef
top::EasyTerminalRef ::= t::Terminal_t
{
  top.unparse = t.lexeme;
  top.easyString = substring(1, length(t.lexeme) - 1, t.lexeme);

  -- TODO: This is necessary because the environment is still populated using the regex, so we have to look up the corresponding regex.
  local regHack :: Regex = regexLiteral(top.easyString);

  top.dcls = getTerminalRegexDclAll(regHack.regString, top.env);

  top.errors :=
    if null(top.dcls) then
      [err(t.location, "Could not find terminal declaration for " ++ t.lexeme )]
    else if length(top.dcls) > 1 then
      [err(t.location, "Found ambiguous possibilities for " ++ t.lexeme ++ "\n" ++ printPossibilities(top.dcls))]
    else [];
  
  top.typerep = if null(top.dcls) then errorType() else head(top.dcls).typerep;
}


concrete production productionRhsElemEasyReg
top::ProductionRHSElem ::= id::Name '::' reg::EasyTerminalRef
{
  top.unparse = id.unparse ++ "::" ++ reg.unparse;
  top.errors <- reg.errors;

  forwards to productionRHSElem(id, $2, typerepTypeExpr(reg.typerep, location=reg.location), location=top.location);
}

concrete production productionRhsElemTypeEasyReg
top::ProductionRHSElem ::= reg::EasyTerminalRef
{
  top.unparse = reg.unparse;
  top.errors <- reg.errors;

  forwards to productionRHSElemType(typerepTypeExpr(reg.typerep, location=top.location), location=top.location);
}

concrete production aspectRHSElemEasyReg
top::AspectRHSElem ::= reg::EasyTerminalRef
{
  top.unparse = reg.unparse;
  top.errors <- reg.errors;

  forwards to aspectRHSElemNone('_', location=reg.location); -- TODO This isn't checking if the type is right!!
}

concrete production aspectRHSElemTypedEasyReg
top::AspectRHSElem ::= id::Name '::' reg::EasyTerminalRef
{
  top.unparse = id.unparse ++ " :: " ++ reg.unparse;
  top.errors <- reg.errors;

  forwards to aspectRHSElemTyped(id, $2, typerepTypeExpr(reg.typerep, location=reg.location), location=top.location);
}

{-- Introduce single quoted terminal literals in expressions -}
concrete production terminalExprReg
top::Expr ::= reg::EasyTerminalRef
{
  top.unparse = reg.unparse;
  top.errors <- reg.errors;
  
  local escapedName :: String = escapeString(reg.easyString);

  forwards to terminalFunction('terminal', '(',
    typerepTypeExpr(reg.typerep, location=reg.location),
    ',', stringConst(terminal(String_t, "\"" ++ escapedName ++ "\""), location=reg.location), ')', location=top.location);
}

