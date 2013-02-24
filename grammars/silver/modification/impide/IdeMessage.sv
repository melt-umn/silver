grammar silver:modification:impide;

synthesized attribute declName :: String;

{--
The nonterminal representing a message to be displayed in generated IDE.  
--}
nonterminal IdeMessage with declName, location, severity, msg;

{--
 declName:      in format "a:b:c"
 location:      standard core:Location
 severity:      warning:1, error:2 
 msg:           the message to be displayed in IDE
--}
abstract production makeIdeMessage
top::IdeMessage ::= declName::String location::Location severity::Integer msg::String
{
  top.declName = declName;
  top.location = location;
  top.severity = severity;
  top.msg = msg;
}
