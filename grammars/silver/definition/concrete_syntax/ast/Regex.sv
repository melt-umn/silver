grammar silver:definition:concrete_syntax:ast;

attribute xmlCopper occurs on Regex_R, Regex_DR, Regex_UR, Regex_RR, Regex_G, Regex_RG, Regex_UG, Regex_CHAR;
{--
 - Used to prevent unneeded nesting of concatenations.
 -}
synthesized attribute unwrappedXML :: String occurs on Regex_DR, Regex_RR;

aspect production literalRegex
r::Regex_R ::= s::String
{
  r.xmlCopper = if length(s) == 0 then "<EmptyString/>"
                else if length(s) == 1 then literalNRegexToXML(s)
                else "<Concatenation>" ++ literalNRegexToXML(s) ++ "</Concatenation>";
}

function literalNRegexToXML
String ::= s::String
{
  local attribute ch :: String;
  ch = substring(0, 1, s);

  local attribute rest :: String;
  rest = substring(1, length(s), s);

  return if length(s) == 0 
         then ""
         else (if ch == ">" then getSingleCharNRegexXML("&gt;")
               else if ch == "<" then getSingleCharNRegexXML("&lt;")
               else if ch == "&" then getSingleCharNRegexXML("&amp;")
               else getSingleCharNRegexXML(ch))
               ++ literalNRegexToXML(rest);
}

function getSingleCharNRegexXML
String ::= s::String
{
  return "<CharacterSet><SingleCharacter char=\"" ++ s ++ "\"/></CharacterSet>";
}

aspect production Rtoeps
r::Regex_R ::=
{
  r.xmlCopper = "<EmptyString/>";
}

aspect production RtoDR
r::Regex_R ::= dr::Regex_DR
{
  r.xmlCopper = dr.xmlCopper;
}

aspect production RtoDR_bar_R
r::Regex_R ::= first::Regex_DR sep::Choice_t rest::Regex_R
{
  r.xmlCopper = "<Choice>" ++ first.xmlCopper ++ rest.xmlCopper ++ "</Choice>";
}

aspect production DRtoUR_RR
dr::Regex_DR ::= first::Regex_UR rest::Regex_RR
{
  dr.xmlCopper = 
    case rest of
    | RRtoeps() -> first.xmlCopper
    | RRtoDR(_) -> "<Concatenation>" ++ first.xmlCopper ++ rest.unwrappedXML ++ "</Concatenation>"
    end;
  dr.unwrappedXML = first.xmlCopper ++ rest.unwrappedXML;
}

aspect production regex_kleene_of
dr::Regex_UR ::= r::Regex_UR
{
  dr.xmlCopper = "<KleeneStar>" ++ r.xmlCopper ++ "</KleeneStar>";
}

aspect production regex_plus_of
dr::Regex_UR ::= r::Regex_UR
{
  dr.xmlCopper = "<Concatenation>" ++ r.xmlCopper ++ "<KleeneStar>" ++ r.xmlCopper ++ "</KleeneStar></Concatenation>";
}

aspect production regex_opt_of
dr::Regex_UR ::= r::Regex_UR
{
  dr.xmlCopper = "<Choice>" ++ r.xmlCopper ++ "<EmptyString/></Choice>";
}

aspect production RRtoDR
rr::Regex_RR ::= dr::Regex_DR
{
  rr.xmlCopper = dr.xmlCopper;
  rr.unwrappedXML = dr.unwrappedXML;
}

aspect production RRtoeps
rr::Regex_RR ::=
{
  rr.xmlCopper = "<EmptyString/>";
  rr.unwrappedXML = "";
}


aspect production URtoCHAR
ur::Regex_UR ::= char::Regex_CHAR
{
  ur.xmlCopper = getSingleCharNRegexXML(char.xmlCopper);
}

aspect production URtowildcard
ur::Regex_UR ::= wildcard::RegexWildcard_t
{
  ur.xmlCopper = "<CharacterSet invert=\"true\"><SingleCharacter char=\"&#10;\"/></CharacterSet>";
}

aspect production URtolb_G_rb
ur::Regex_UR ::= lb::RegexLBrack_t g::Regex_G rb::RegexRBrack_t
{
  ur.xmlCopper = "<CharacterSet>" ++ g.xmlCopper ++ "</CharacterSet>";
}

aspect production URtolb_not_G_rb
ur::Regex_UR ::= lb::RegexLBrack_t sep::RegexNot_t g::Regex_G rb::RegexRBrack_t
{
  ur.xmlCopper = "<CharacterSet invert=\"true\">" ++ g.xmlCopper ++ "</CharacterSet>";
}

aspect production URtolp_R_rp
ur::Regex_UR ::= lp::RegexLParen_t r::Regex_R rp::RegexRParen_t
{
  ur.xmlCopper = r.xmlCopper;
}

aspect production GtoUG_RG
g::Regex_G ::= ug::Regex_UG rg::Regex_RG
{
  g.xmlCopper = ug.xmlCopper ++ rg.xmlCopper;
}

aspect production UGtoCHAR
ug::Regex_UG ::= char::Regex_CHAR
{
  ug.xmlCopper = "<SingleCharacter char=\"" ++ char.xmlCopper ++ "\"/>";
}

aspect production UGtoCHAR_dash_CHAR
ug::Regex_UG ::= leastchar::Regex_CHAR sep::Range_t greatestchar::Regex_CHAR
{
  ug.xmlCopper = "<CharacterRange lower=\"" ++ leastchar.xmlCopper ++ "\" upper=\"" ++ greatestchar.xmlCopper ++ "\"/>";
}

aspect production RGtoG
rg::Regex_RG ::= g::Regex_G
{
  rg.xmlCopper = g.xmlCopper;
}

aspect production RGtoeps
rg::Regex_RG ::=
{
  rg.xmlCopper = "";
}

aspect production CHARtochar
top::Regex_CHAR ::= char::RegexChar_t
{
  top.xmlCopper = if char.lexeme == "<" then "&lt;"
                else if char.lexeme == ">" then "&gt;"
                else if char.lexeme == "&" then "&amp;"
                else if char.lexeme == "\"" then "&quot;"
                else char.lexeme;
}

aspect production CHARtoescaped
top::Regex_CHAR ::= esc::EscapedChar_t
{
  top.xmlCopper = if esc.lexeme == "\\<" then "&lt;"
                else if esc.lexeme == "\\>" then "&gt;"
                else if esc.lexeme == "\\&" then "&amp;"
                else if esc.lexeme == "\\r" then "&#13;"
                else if esc.lexeme == "\\n" then "&#10;"
                else if esc.lexeme == "\\t" then "&#9;"
                else if esc.lexeme == "\\\"" then "&quot;"
                else substring(1,2,esc.lexeme);
}

