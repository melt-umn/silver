grammar silver:extension:easyterminal;
import silver:definition:core;
import silver:definition:env;
import silver:definition:type:higherorder;
import silver:definition:concrete_syntax;
import silver:definition:regex;

terminal Terminal_t /[\']([^\']|([\\][\']))*[\']/ lexer classes {SIX};

abstract production findRegularExpression
top::EnvFilter ::= re::String
{
  top.keep = top.inEnvItem.isTypeDeclaration
	  && top.inEnvItem.typerep.isTerminal
	  && top.inEnvItem.typerep.regularExpression.regString == re;
}

concrete production regExprEasyTerm
top::RegExpr ::= t::Terminal_t
{
  top.pp = t.lexeme;
  top.location = loc(top.file, t.line, t.column);
  top.terminalRegExprSpec = decorate literalRegex(substring(1, length(t.lexeme)-1, t.lexeme)) with {};
}

concrete production productionRhsElemEasyReg
top::ProductionRHSElem ::= reg::RegExpr
{
  top.location = reg.location;

  local attribute regExpPat :: String;
  regExpPat = reg.terminalRegExprSpec.regString;

  local attribute regName :: [Decorated EnvItem];
  regName = filterEnvItems(findRegularExpression(regExpPat), getAllTypeDcls(top.env));

  top.errors := if null(regName) 
	       then [err(top.location, "Could not find terminal declaration for " ++ reg.pp ++ "\n\n" ++ printEnvItems(getAllTypeDcls(top.env)))]
 	       else [];

  forwards to productionRHSElemType(typerepType(head(regName).typerep));
}

concrete production aspectRHSElemEasyReg
top::AspectRHSElem ::= reg::RegExpr
{
  local attribute regExpPat :: String;
  regExpPat = reg.terminalRegExprSpec.regString;

  local attribute regName :: [Decorated EnvItem];
  regName = filterEnvItems(findRegularExpression(regExpPat), getAllTypeDcls(top.env));

  top.errors := if null(regName) 
	       then [err(top.location, "Could not find terminal declaration for " ++ reg.pp )]
 	       else [];

  forwards to aspectRHSElemNone('_');
}

concrete production terminalExprReg
top::Expr ::= t::RegExpr
{
  local attribute regExpPat :: String;
  regExpPat = t.terminalRegExprSpec.regString;

  local attribute regName :: [Decorated EnvItem];
  regName = filterEnvItems(findRegularExpression(regExpPat), getAllTypeDcls(top.env));

  local attribute escapedName :: String;
  escapedName = makeEscapedName(substring(1, length(regExpPat)-1, regExpPat));

  top.errors := if null(regName) 
	       then [err(top.location, "Could not find terminal declaration for " ++ t.pp )]
 	       else [];

  forwards to terminalFunction(terminal(Terminal_kwd, "terminal", t.location.line, t.location.column),
			       terminal(LParen_t, "("),
			       typerepType(head(regName).typerep),
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

