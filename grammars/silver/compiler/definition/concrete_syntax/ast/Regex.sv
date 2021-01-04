grammar silver:compiler:definition:concrete_syntax:ast;

-- Translation of regex to Copper XML format.
attribute xmlCopper occurs on Regex;

{--
 - Used to prevent unneeded nesting of the same operator. (choices, sequences)
 -}
synthesized attribute altXML :: String occurs on Regex;
synthesized attribute seqXML :: String occurs on Regex;

aspect default production
top::Regex ::=
{
  top.altXML = top.xmlCopper;
  top.seqXML = top.xmlCopper;
}


---------------------------------------------------------------------------------

aspect production char
top::Regex ::= _
{
  top.xmlCopper = "<CharacterSet><SingleCharacter char=\"" ++ xmlEscapeChar(char) ++ "\"/></CharacterSet>";
}
aspect production wildChar
top::Regex ::=
{
  -- Copper has no direct representation of dot.
  -- Dot represents everything EXCEPT \n
  top.xmlCopper = "<CharacterSet invert=\"true\"><SingleCharacter char=\"&#10;\"/></CharacterSet>";
}
aspect production regexSet
top::RegexItem ::= _ g::RegexCharSet _
{
  top.xmlCopper = "<CharacterSet>" ++ g.xmlCopper ++ "</CharacterSet>";
}
aspect production regexSetInverted
top::RegexItem ::= _ _ g::RegexCharSet _
{
  top.xmlCopper = "<CharacterSet invert=\"true\">" ++ g.xmlCopper ++ "</CharacterSet>";
}

aspect production empty
top::Regex ::=
{
  top.xmlCopper = "<Choice></Choice>";
  top.altXML = "";
}
aspect production epsilon
top::Regex ::=
{
  top.xmlCopper = "<EmptyString/>";
  top.seqXML = "";
}
aspect production alt
top::Regex ::= r1::Regex r2::Regex
{
  top.xmlCopper = "<Choice>" ++ r1.altXML ++ r2.altXML ++ "</Choice>";
  top.altXML = r1.altXML ++ r2.altXML;
}
aspect production seq
top::Regex ::= r1::Regex r2::Regex
{
  top.xmlCopper = "<Concatenation>" ++ r1.seqXML ++ r2.seqXML ++ "</Concatenation>";
  top.seqXML = r1.seqXML ++ r2.seqXML;
}
aspect production star
top::Regex ::= r::Regex
{
  top.xmlCopper = "<KleeneStar>" ++ r.xmlCopper ++ "</KleeneStar>";
}
aspect production regexGroup
top::RegexItem ::= _ r::Regex _
{
  -- As an AST, Copper has no need to represent groups ()
  top.xmlCopper = r.xmlCopper;
}


aspect production regexCharSetSnoc
top::RegexCharSet ::= h::RegexCharSet  t::RegexCharSetItem
{
  top.xmlCopper = h.xmlCopper ++ t.xmlCopper;
}
aspect production regexCharSetOne
top::RegexCharSet ::= t::RegexCharSetItem
{
  top.xmlCopper = t.xmlCopper;
}


aspect production regexSetChar
top::RegexCharSetItem ::= char::RegexChar
{
  top.xmlCopper = "<SingleCharacter char=\"" ++ char.xmlCopper ++ "\"/>";
}
aspect production regexSetRange
top::RegexCharSetItem ::= l::RegexChar _ u::RegexChar
{
  top.xmlCopper = "<CharacterRange lower=\"" ++ l.xmlCopper ++ "\" upper=\"" ++ u.xmlCopper ++ "\"/>";
}


aspect production regexChar
top::RegexChar ::= char::RegexChar_t
{
  -- Can be special characters like &
  top.xmlCopper = xmlEscapeChar(char.lexeme);
}
aspect production regexEscapedChar
top::RegexChar ::= esc::EscapedChar_t
{
  -- These are ESCAPED character (e.g. \n) we need to represent as XML.
  -- We only animate \r\n\t. Otherwise, literal translation.
  top.xmlCopper =
    if esc.lexeme == "\\r" then "&#13;"
    else if esc.lexeme == "\\n" then "&#10;"
    else if esc.lexeme == "\\t" then "&#9;"
    else xmlEscapeChar(substring(1, 2, esc.lexeme));
}

-----------------------------------------------------------------------------------


{-- XML String to emit to represent the character 'ch' -}
function xmlEscapeChar
String ::= ch::String
{
  return
    if ch == ">" then "&gt;"
    else if ch == "<" then "&lt;"
    else if ch == "&" then "&amp;"
    else if ch == "\"" then "&quot;"
    -- For completeness, there is "'" --> &apos;
    -- but this should only be necessary if we used single quotes for xml-attribute values
    -- e.g. <tag attr='val'>
    -- We used double quotes, so we should be okay...
    else ch;
}

function xmlEscapeString
String ::= s::String
{
  return implode("", map(xmlEscapeChar, explode("", s)));
}
