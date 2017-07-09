grammar silver:definition:concrete_syntax:ast;

-- Translation of regex to Copper XML format.
attribute xmlCopper occurs on Regex, RegexSeq, RegexRepetition, RegexItem, RegexCharSet, RegexCharSetItem, RegexChar;

{--
 - Used to prevent unneeded nesting of the same operator. (choices, sequences)
 -}
synthesized attribute unwrappedXML :: String occurs on Regex, RegexSeq;


---------------------------------------------------------------------------------
aspect production regexEpsilon
top::Regex ::=
{
  top.xmlCopper = "<EmptyString/>";
  top.unwrappedXML = top.xmlCopper;
}
aspect production regexSeq
top::Regex ::= h::RegexSeq
{
  top.xmlCopper = h.xmlCopper;
  top.unwrappedXML = top.xmlCopper;
}
aspect production regexChoice
top::Regex ::= h::RegexSeq _ t::Regex
{
  top.xmlCopper = "<Choice>" ++ h.xmlCopper ++ t.unwrappedXML ++ "</Choice>";
  top.unwrappedXML = h.xmlCopper ++ t.unwrappedXML;
}


aspect production regexSeqSnoc
top::RegexSeq ::= h::RegexSeq t::RegexRepetition
{
  top.xmlCopper = "<Concatenation>" ++ h.unwrappedXML ++ t.xmlCopper ++ "</Concatenation>";
  top.unwrappedXML = h.unwrappedXML ++ t.xmlCopper;
}
aspect production regexSeqOne
top::RegexSeq ::= t::RegexRepetition
{
  top.xmlCopper = t.xmlCopper;
  top.unwrappedXML = top.xmlCopper;
}


aspect production regexKleene
top::RegexRepetition ::= i::RegexItem _
{
  top.xmlCopper = "<KleeneStar>" ++ i.xmlCopper ++ "</KleeneStar>";
}
aspect production regexPlus
top::RegexRepetition ::= i::RegexItem _
{
  -- Copper has no direct translation of +
  top.xmlCopper = "<Concatenation>" ++ i.xmlCopper ++ "<KleeneStar>" ++ i.xmlCopper ++ "</KleeneStar></Concatenation>";
}
aspect production regexOptional
top::RegexRepetition ::= i::RegexItem _
{
  -- Copper has no direct translation of ?
  top.xmlCopper = "<Choice>" ++ i.xmlCopper ++ "<EmptyString/></Choice>";
}
aspect production regexOnce
top::RegexRepetition ::= i::RegexItem
{
  top.xmlCopper = i.xmlCopper;
}


aspect production regexCharItem
top::RegexItem ::= char::RegexChar
{
  top.xmlCopper = "<CharacterSet><SingleCharacter char=\"" ++ char.xmlCopper ++ "\"/></CharacterSet>";
}
aspect production regexWildcard
top::RegexItem ::= _
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


