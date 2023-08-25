grammar silver:langutil;

@@{-
   - @config fileSplit
   -
   - ## A Universal error/warning message data structure
   -}

@{--
 - A Message represents a compiler output message (error/warning)
 -}
tracked data nonterminal Message with message, where, noLocOutput, output, severity;

@{--
 - The location of an error message.
 -}
synthesized attribute where :: Location;

@{--
 - The contents of the error message.
 -}
synthesized attribute message :: String;

@{--
 - A recommended way to turn this message into console output with location info.
 -}
synthesized attribute output :: String;

@{--
 - A recommended way to turn this message into console output without location info.
 -}
synthesized attribute noLocOutput :: String;

@{--
 - A convention for determining message severity.
 - err=2, wrn=1, info=0
 -}
synthesized attribute severity :: Integer;

aspect default production
top::Message ::=
{
  top.output = s"${top.where.unparse}: ${top.noLocOutput}";
}

@{--
 - A error that should halt compilation before translation proceeds on the 
 - compilation unit the error occurs in.
 -}
abstract production err
top::Message ::= l::Location m::String
{
  top.where = l;
  top.message = m;
  top.noLocOutput = s"error: ${m}";
  top.severity = 2;
}

function errFromOrigin
Message ::= a::a m::String
{
  return err(getParsedOriginLocationOrFallback(a), m);
}

@{--
 - A warning that is not required to halt compilation before translation
 - proceeds on the compilation unit the warning occurs in.
 -}
abstract production wrn
top::Message ::= l::Location m::String
{
  top.where = l;
  top.message = m;
  top.noLocOutput = s"warning: ${m}";
  top.severity = 1;
}

function wrnFromOrigin
Message ::= a::a m::String
{
  return wrn(getParsedOriginLocationOrFallback(a), m);
}

@{--
 - An informational message that does not halt compilation, but is usually
 - attached to an error or warning.
 -}
abstract production info
top::Message ::= l::Location m::String
{
  top.where = l;
  top.message = m;
  top.noLocOutput = s"info: ${m}";
  top.severity = 0;
}

function infoFromOrigin
Message ::= a::a m::String
{
  return info(getParsedOriginLocationOrFallback(a), m);
}

@{--
 - A group of messages.
 -}
abstract production nested
top::Message ::= l::Location m::String others::[Message]
{
  top.where = l;
  top.message = s"${m}\n${messagesToString(others)}";
  top.noLocOutput = s"${top.message}\n";
  top.severity = foldr(max, 0, map((.severity), others));
}

-- Users can extend Message with more messages (e.g. dbg) as they desire
-- map, filter, etc should all be quite useful on messages

@{--
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

@{--
 - Returns a list of strings, ready to be printed to the command line.
 -}
function messagesToString
String ::= msgs::[Message]
{
  return implode("\n", map((.output), sortBy(messageLte, msgs)));
}

-- for use with sortBy
-- not an instance of Eq/Ord for now, does it really make sense to compare messages for equality?
function messageLte
Boolean ::= m1::Message m2::Message
{
  return m1.where <= m2.where;
}

