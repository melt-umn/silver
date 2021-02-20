grammar silver:compiler:definition:concrete_syntax:ast;

imports silver:core hiding empty, alt;

-- Translation of regex to Copper XML format.
attribute xmlCopper occurs on Regex;

{--
 - Used to prevent unneeded nesting of the same operator. (choices, sequences)
 -}
synthesized attribute altXML :: String occurs on Regex;
synthesized attribute seqXML :: String occurs on Regex;

{--
 - Used to recognize char sets
 -}
implicit synthesized attribute setXML :: Maybe<String>;
attribute setXML occurs on Regex; 

aspect default production
top::Regex ::=
{
  top.altXML = top.xmlCopper;
  top.seqXML = top.xmlCopper;
  implicit top.setXML =;
}


---------------------------------------------------------------------------------

aspect production char
top::Regex ::= _
{
  top.xmlCopper = "<CharacterSet><SingleCharacter char=\"" ++ xmlEscapeChar(char) ++ "\"/></CharacterSet>";
  top.setXML = "<SingleCharacter char=\"" ++ xmlEscapeChar(char) ++ "\"/>";
}
aspect production wildChar
top::Regex ::=
{
  -- Copper has no direct representation of dot.
  -- Dot represents everything EXCEPT \n
  top.xmlCopper = "<CharacterSet invert=\"true\"><SingleCharacter char=\"&#10;\"/></CharacterSet>";
  top.setXML = "<SingleCharacter char=\"&#10;\"/>";
}
aspect production charRange
top::Regex ::= _ _
{
  top.xmlCopper = "<CharacterSet><CharacterRange lower=\"" ++ xmlEscapeChar(lChar) ++ "\" upper=\"" ++ xmlEscapeChar(uChar) ++ "\"/></CharacterSet>";
  top.setXML = "<CharacterRange lower=\"" ++ xmlEscapeChar(lChar) ++ "\" upper=\"" ++ xmlEscapeChar(uChar) ++ "\"/>";
}
aspect production negChars
top::Regex ::= r::Regex 
{
  top.xmlCopper = "<CharacterSet invert=\"true\">" ++ r.setXML.fromJust ++ "</CharacterSet>";
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
  top.xmlCopper =
    case top.setXML of
    | just(sx) -> "<CharacterSet>" ++ sx ++ "</CharacterSet>"
    | nothing() -> "<Choice>" ++ r1.altXML ++ r2.altXML ++ "</Choice>"
    end;
  top.altXML = r1.altXML ++ r2.altXML;
  top.setXML = r1.setXML ++ r2.setXML;
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

-----------------------------------------------------------------------------------


{-- XML String to emit to represent the character 'ch' -}
function xmlEscapeChar
String ::= ch::String
{
  return
    if ch == "\r" then "&#13;"
    else if ch == "\n" then "&#10;"
    else if ch == "\t" then "&#9;"
    else if ch == ">" then "&gt;"
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
