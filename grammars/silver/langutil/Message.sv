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
 - err=2, wrn=1, info=0
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
  top.output = s"${l.unparse}: error: ${m}";
  top.severity = 2;
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
  top.output = s"${l.unparse}: warning: ${m}";
  top.severity = 1;
}

{--
 - An informational message that does not halt compilation, but is usually
 - attached to an error or warning.
 -}
abstract production info
top::Message ::= l::Location m::String
{
  top.where = l;
  top.message = m;
  top.output = s"${l.unparse}: info: ${m}";
  top.severity = 0;
}

{--
 - A group of messages.
 -}
abstract production nested
top::Message ::= l::Location m::String others::[Message]
{
  top.where = l;
  top.message = s"${m}\n${messagesToString(others)}";
  top.output = s"${l.unparse}: ${m}\n${messagesToString(others)}\n";
  top.severity = foldr(max, 0, map((.severity), others));
}

-- Users can extend Message with more messages (e.g. dbg) as they desire
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
         | nested(_, _, e) :: t -> containsErrors(e, wError) || containsErrors(t, wError)
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

