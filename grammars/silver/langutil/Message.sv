{- A Universal error/warning message data structure -}
grammar silver:langutil;

{--
 - A Message represents a compiler output message (error/warning)
 -}
nonterminal Message with message, where, output, severity;

{--
 - The location of an error message.
 -}
synthesized attribute where :: Location;
{--
 - The contents of the error message.
 -}
synthesized attribute message :: String;
{--
 - A recommended way to turn this message into console output.
 -}
synthesized attribute output :: String;
{--
 - A convention for determining message severity.
 - Lower is more severe.
 -}
synthesized attribute severity :: Integer;

{--
 - A error that should halt compilation before translation proceeds on the 
 - compilation unit the error occurs in.
 -}
abstract production err
top::Message ::= l::Location m::String
{
  top.where = l;
  top.message = m;
  top.output = l.filename ++ ":" ++ toString(l.line) ++ ":" ++ toString(l.column) ++ ": error: " ++ m;
  top.severity = 0;
}

{--
 - A warning that is not required to halt compilation before translation
 - proceeds on the compilation unit the warning occurs in.
 -}
abstract production wrn
top::Message ::= l::Location m::String
{
  top.where = l;
  top.message = m;
  top.output = l.filename ++ ":" ++ toString(l.line) ++ ":" ++ toString(l.column) ++ ": warning: " ++ m;
  top.severity = 0;
}

-- Users can extend Message with more messages (info, dbg) as they desire
-- map, filter, etc should all be quite useful on messages

{--
 - Determines if a list has any errors (or, optionally, warnings, too)
 - Note: user extended messages that forward to err or wrn will have
 - the same effect, and unknown completely messages will be skipped as
 - though they do not exist.
 -}
function containsErrors
Boolean ::= l::[Message] wError::Boolean
{
  return case l of
         | [] -> false
         | err(_,_) :: _ -> true
         | wrn(_,_) :: t -> if wError then true else containsErrors(t, false)
         | _ :: t -> containsErrors(t, wError)
         end;
}

{--
 - Returns a list of strings, ready to be printed to the command line.
 -}
function messagesToString
String ::= msgs::[Message]
{
  return implode("\n", map((.output), sortBy(messageLte, msgs)));
}

-- for use with sortBy
function messageLte
Boolean ::= m1::Message m2::Message
{
  return locationLte(m1.where, m2.where);
}

