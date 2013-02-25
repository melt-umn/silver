grammar silver:modification:impide;

synthesized attribute resPath :: String;

{--
The nonterminal representing a message to be displayed in generated IDE.  
--}
nonterminal IdeMessage with resPath, location, severity, msg;

{--
 resPath:    the path relative to root of project, in format "path/relative/to/project/root".
 location:   standard core:Location. Note the resource can be located by {path + "/" + location.fileName}
 severity:   warning:1, error:2 
 msg:        the message to be displayed in IDE
--}
abstract production makeIdeMessage
top::IdeMessage ::= resPath::String location::Location severity::Integer msg::String
{
  top.resPath = resPath;
  top.location = location;
  top.severity = severity;
  top.msg = msg;
}
