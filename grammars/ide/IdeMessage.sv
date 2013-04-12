grammar ide;

-- hack
-- import silver:definition:core;

synthesized attribute resPath :: String;
synthesized attribute systemLevel :: Boolean;

synthesized attribute severity :: Integer; 
synthesized attribute msg :: String;
synthesized attribute loc :: Location;

{--
The nonterminal representing a message to be displayed in generated IDE.  
--}
nonterminal IdeMessage with resPath, loc, severity, msg, systemLevel;

{--
 Make a message which is related to a specific file.

 resPath:    the path relative to root of project, in format "path/relative/to/project/root".
 location:   standard core:Location. Note the resource can be located by {path + "/" + location.fileName}
 severity:   warning:1, error:2 
 msg:        the message to be displayed in IDE
--}
abstract production makeIdeMessage
top::IdeMessage ::= resPath::String location::Location severity::Integer msg::String
{
  top.resPath = resPath;
  top.loc = location;
  top.severity = severity;
  top.msg = msg;
  top.systemLevel = false;
}

{--
 Make a system-level message. Message of this kind is not related to a specific file.

 severity:   warning:1, error:2 
 msg:        the message to be displayed in IDE
--}
abstract production makeSysIdeMessage
top::IdeMessage ::= severity::Integer msg::String
{
  top.resPath = "";
  top.loc = loc("", 0, 0, 0, 0, 0, 0);-- a bogus location.
  top.severity = severity;
  top.msg = msg;
  top.systemLevel = true;
}
