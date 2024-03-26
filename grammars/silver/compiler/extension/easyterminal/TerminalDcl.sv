grammar silver:compiler:extension:easyterminal;

import silver:compiler:definition:core;
import silver:compiler:definition:env;
import silver:compiler:definition:type;
import silver:compiler:definition:type:syntax;
import silver:compiler:definition:concrete_syntax;
import silver:compiler:modification:lambda_fn;

import silver:regex only Regex, regexLiteral;

import silver:util:treeset as ts;
import silver:langutil:lsp as lsp;

terminal Terminal_t /\'[^\'\r\n]*\'/ lexer classes {LITERAL, lsp:Regexp};

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
  
  production easyName::String = substring(1, length(t.lexeme) - 1, t.lexeme);
  top.easyName = just(easyName);
  
  forwards to regExpr(regexLiteral(easyName));
}

{-- Abstracts away looking up terminals in the environment -}
tracked nonterminal EasyTerminalRef with config, grammarName, unparse, errors, typerep, easyString, env, dcls<TypeDclInfo>;

{-- String literal between quotes. e.g. 'hi"' is hi" -}
synthesized attribute easyString :: String;

concrete production easyTerminalRef
top::EasyTerminalRef ::= t::Terminal_t
{
  top.unparse = t.lexeme;
  top.easyString = substring(1, length(t.lexeme) - 1, t.lexeme);

  top.dcls = getTerminalRegexDclAll(top.easyString, top.env);

  top.errors :=
    if null(top.dcls) then
      [errFromOrigin(t, "Could not find terminal declaration for " ++ t.lexeme )]
    else if length(top.dcls) > 1 then
      [errFromOrigin(t, "Found ambiguous possibilities for " ++ t.lexeme ++ "\n" ++ printPossibilities(top.dcls))]
    else [];
  
  top.typerep = if null(top.dcls) then errorType() else head(top.dcls).typeScheme.monoType;
}

-- sharing doesn't make sense here, but we need MaybeShared here to avoid shift/reduce:
concrete production productionRhsElemEasyReg
top::ProductionRHSElem ::= ms::MaybeShared id::Name '::' reg::EasyTerminalRef
{
  top.unparse = ms.unparse ++ id.unparse ++ "::" ++ reg.unparse;
  propagate env;
  top.errors <- reg.errors;

  forwards to productionRHSElem(@ms, id, $3, typerepTypeExpr(reg.typerep));
}

concrete production productionRhsElemTypeEasyReg
top::ProductionRHSElem ::= reg::EasyTerminalRef
{
  top.unparse = reg.unparse;
  propagate env;
  top.errors <- reg.errors;

  forwards to productionRHSElemType(elemNotShared(), typerepTypeExpr(reg.typerep));
}

concrete production aspectRHSElemEasyReg
top::AspectRHSElem ::= reg::EasyTerminalRef
{
  top.unparse = reg.unparse;
  propagate env;
  top.errors <- reg.errors;

  forwards to aspectRHSElemNone('_'); -- TODO This isn't checking if the type is right!!
}

concrete production aspectRHSElemTypedEasyReg
top::AspectRHSElem ::= id::Name '::' reg::EasyTerminalRef
{
  top.unparse = id.unparse ++ " :: " ++ reg.unparse;
  propagate env;
  top.errors <- reg.errors;

  forwards to aspectRHSElemTyped(id, $2, typerepTypeExpr(reg.typerep));
}

{-- Introduce single quoted terminal literals in expressions -}
concrete production terminalExprReg
top::Expr ::= reg::EasyTerminalRef
{
  top.unparse = reg.unparse;
  propagate env, freeVars;
  top.errors <- reg.errors;
  
  local escapedName :: String = escapeString(reg.easyString);

  forwards to terminalFunction('terminal', '(',
    typerepTypeExpr(reg.typerep),
    ',', stringConst(terminal(String_t, "\"" ++ escapedName ++ "\"")), ')');
}

