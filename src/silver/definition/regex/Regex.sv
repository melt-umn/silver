grammar silver:definition:regex;

synthesized attribute regString :: String;
synthesized attribute regXML :: String;

lexer class REGEX_OPER;
lexer class REGEX_ESC submits to REGEX_OPER;


terminal Plus_t          '+' lexer classes { REGEX_OPER };
terminal Kleene_t        '*' lexer classes { REGEX_OPER };
terminal Optional_t      '?' lexer classes { REGEX_OPER };
terminal Choice_t        '|' lexer classes { REGEX_OPER };
terminal Range_t         '-' lexer classes { REGEX_OPER };
terminal RegexNot_t      '^' lexer classes { REGEX_OPER };
terminal RegexLBrack_t   '[' lexer classes { REGEX_OPER };
terminal RegexRBrack_t   ']' lexer classes { REGEX_OPER };
terminal RegexLParen_t   '(' lexer classes { REGEX_OPER };
terminal RegexRParen_t   ')' lexer classes { REGEX_OPER };
terminal RegexWildcard_t '.' lexer classes { REGEX_OPER };
terminal RegexChar_t     /./ lexer classes { REGEX_ESC };
terminal EscapedChar_t /\\./ submits to { REGEX_ESC };

nonterminal Regex_R with regString, regXML;       -- full regex, removes choice |
nonterminal Regex_DR with regString, regXML;      -- concat possibly with * + or ?
nonterminal Regex_UR with regString, regXML;      -- characters or sequences/sets
nonterminal Regex_RR with regString, regXML;      -- back up to dr or nothing
nonterminal Regex_G with regString, regXML;       -- Inside charset
nonterminal Regex_RG with regString, regXML;      -- back to g or nothing
nonterminal Regex_UG with regString, regXML;      -- char or range
nonterminal Regex_CHAR with regString, regXML;

synthesized attribute unwrappedXML :: String occurs on Regex_DR, Regex_RR;

-- TODO list:
-- It'd be nice to get some simplification here.
-- <concat><string>a</string><string>b etc...
-- pass 1: merge the strings
-- pass 2: remove the concat as it's not needed anymore
--         also do this for choices [] that have only one option.

abstract production literalRegex
r::Regex_R ::= s::String
{
  r.regString = regexPurifyString(s);
  r.regXML = "<string>" ++ regexPurifyXML(s) ++ "</string>";
}

function regexPurifyString
String ::= s::String
{
  local attribute ch :: String;
  ch = substring(0, 1, s);

  local attribute rest :: String;
  rest = substring(1, length(s), s);

  return if length(s) == 0 
	 then ""
	 else if isAlpha(ch) || isDigit(ch)
	      then ch ++ regexPurifyString(rest)
	      else "[\\" ++ ch ++ "]" ++ regexPurifyString(rest);
}
function regexPurifyXML
String ::= s::String
{
  local attribute ch :: String;
  ch = substring(0, 1, s);

  local attribute rest :: String;
  rest = substring(1, length(s), s);

  return if length(s) == 0 
	 then ""
	 else (if ch == ">" then "&gt;" else
	       if ch == "<" then "&lt;" else
	       if ch == "&" then "&amp;" else ch)
	      ++ regexPurifyXML(rest);
}

concrete production Rtoeps
r::Regex_R ::=
{
  r.regString = "";
  r.regXML = "<emptystring/>";
}

concrete production RtoDR
r::Regex_R ::= dr::Regex_DR
{
  r.regString = dr.regString;
  r.regXML = dr.regXML;
}

concrete production RtoDR_bar_R
r::Regex_R ::= first::Regex_DR sep::Choice_t rest::Regex_R
{
  r.regString = first.regString ++ "|" ++ rest.regString;
  r.regXML = "<choice>" ++ first.regXML ++ rest.regXML ++ "</choice>";
}

concrete production DRtoUR_RR
dr::Regex_DR ::= first::Regex_UR rest::Regex_RR
{
  dr.regString = first.regString ++ rest.regString;
  dr.regXML = case rest of
                RRtoeps() -> first.regXML
              |  RRtoDR(_) -> "<concat>" ++ first.regXML ++ rest.unwrappedXML ++ "</concat>"
              end;
  dr.unwrappedXML = first.regXML ++ rest.unwrappedXML;
}

concrete production DRtoUR_star_RR
dr::Regex_DR ::= first::Regex_UR sep::Kleene_t rest::Regex_RR
{
  forwards to DRtoUR_RR(regex_kleene_of(first), rest);
}

abstract production regex_kleene_of
dr::Regex_UR ::= r::Regex_UR
{
  dr.regString = r.regString ++ "*";
  dr.regXML = "<kleenestar>" ++ r.regXML ++ "</kleenestar>";
}

concrete production DRtoUR_plus_RR
dr::Regex_DR ::= first::Regex_UR sep::Plus_t rest::Regex_RR
{
  forwards to DRtoUR_RR(regex_plus_of(first), rest);
}

abstract production regex_plus_of
dr::Regex_UR ::= r::Regex_UR
{
  dr.regString = r.regString ++ "+";
  dr.regXML = "<plus>" ++ r.regXML ++ "</plus>";
}

concrete production DRtoUR_question_RR
dr::Regex_DR ::= first::Regex_UR sep::Optional_t rest::Regex_RR
{
  forwards to DRtoUR_RR(regex_opt_of(first), rest);
}

abstract production regex_opt_of
dr::Regex_UR ::= r::Regex_UR
{
  dr.regString = r.regString ++ "?";
  dr.regXML = "<opt>" ++ r.regXML ++ "</opt>";
}

concrete production RRtoDR
rr::Regex_RR ::= dr::Regex_DR
{
  rr.regString = dr.regString;
  rr.regXML = dr.regXML;
  rr.unwrappedXML = dr.unwrappedXML;
}

concrete production RRtoeps
rr::Regex_RR ::=
{
  rr.regString = "";
  rr.regXML = "<emptystring/>";
  rr.unwrappedXML = "";
}

concrete production URtoCHAR
ur::Regex_UR ::= char::Regex_CHAR
{
  ur.regString = char.regString;
  ur.regXML = "<string>" ++ char.regXML ++ "</string>";
}

concrete production URtowildcard
ur::Regex_UR ::= wildcard::RegexWildcard_t
{
  ur.regString = ".";
  ur.regXML = "<dot/>";
}

concrete production URtolb_G_rb
ur::Regex_UR ::= lb::RegexLBrack_t g::Regex_G rb::RegexRBrack_t
{
  ur.regString = "[" ++ g.regString ++ "]";
  ur.regXML = "<charset>" ++ g.regXML ++ "</charset>";
}

concrete production URtolb_not_G_rb
ur::Regex_UR ::= lb::RegexLBrack_t sep::RegexNot_t g::Regex_G rb::RegexRBrack_t
{
  ur.regString = "[^" ++ g.regString ++ "]";
  ur.regXML = "<charset invert=\"true\">" ++ g.regXML ++ "</charset>";
}

concrete production URtolp_R_rp
ur::Regex_UR ::= lp::RegexLParen_t r::Regex_R rp::RegexRParen_t
{
  ur.regString = "(" ++ r.regString ++ ")";
  ur.regXML = r.regXML;
}

concrete production GtoUG_RG
g::Regex_G ::= ug::Regex_UG rg::Regex_RG
{
  g.regString = ug.regString ++ rg.regString;
  g.regXML = ug.regXML ++ rg.regXML;
}

concrete production UGtoCHAR
ug::Regex_UG ::= char::Regex_CHAR
{
  ug.regString = char.regString;
  ug.regXML = "<loosechar>" ++ char.regXML ++ "</loosechar>";
}

concrete production UGtoCHAR_dash_CHAR
ug::Regex_UG ::= leastchar::Regex_CHAR sep::Range_t greatestchar::Regex_CHAR
{
  ug.regString = leastchar.regString ++ "-" ++ greatestchar.regString;
  ug.regXML = "<range><lower>" ++ leastchar.regXML ++ "</lower><upper>" ++ greatestchar.regXML ++ "</upper></range>";
}

concrete production RGtoG
rg::Regex_RG ::= g::Regex_G
{
  rg.regString = g.regString;
  rg.regXML = g.regXML;
}

concrete production RGtoeps
rg::Regex_RG ::=
{
  rg.regString = "";
  rg.regXML = "";
}

concrete production CHARtochar
top::Regex_CHAR ::= char::RegexChar_t
{
  top.regString = char.lexeme;
  
  top.regXML = if char.lexeme == "<" then "&lt;"
                else if char.lexeme == ">" then "&gt;"
                else if char.lexeme == "&" then "&amp;"
                else char.lexeme;
}

concrete production CHARtoescaped
top::Regex_CHAR ::= esc::EscapedChar_t
{
  top.regString = esc.lexeme;
  
  top.regXML = if esc.lexeme == "\\<" then "&lt;"
                else if esc.lexeme == "\\>" then "&gt;"
                else if esc.lexeme == "\\&" then "&amp;"
                else if esc.lexeme == "\\r" then "&#13;"
                else if esc.lexeme == "\\n" then "&#10;"
                else if esc.lexeme == "\\t" then "&#9;"
                else substring(1,2,esc.lexeme);
}

