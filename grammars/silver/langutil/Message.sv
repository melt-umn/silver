{- A Universal error/warning message data structure -}
grammar silver:langutil;

{--
 - A Message represents a compiler output message (error/warning)
 -}
nonterminal Message with unparse, location;

{--
 - A error that should halt compilation before translation proceeds on the 
 - compilation unit the error occurs in.
 -}
abstract production err
top::Message ::= l::Location m::String
{
  top.unparse = l.unparse ++ ": error: " ++ m;
  top.location = l;
}

{--
 - A warning that is not required to halt compilation before translation
 - proceeds on the compilation unit the warning occurs in.
 -}
abstract production wrn
top::Message ::= l::Location m::String
{
  top.unparse = l.unparse ++ ": warning: " ++ m;
  top.location = l;
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
-- someday we can just or(map(isError, l)), but today is not yet that day
  return case l of
           [] -> false
         | err(_,_) :: _ -> true
         | wrn(_,_) :: t -> if wError then true else containsErrors(t, false)
         | _ :: t -> containsErrors(t, wError)
         end;
}

{--
 - Returns a list of strings, ready to be printed to the command line.
 -}
function ppMessages
[String] ::= l::[Message]
{
-- someday we can just map((.unparse), msgs), but today is not yet that day
  return case l of
           [] -> []
         | h::t -> h.unparse :: ppMessages(t)
         end;
}

-- for use with sortBy
function messageLte
Boolean ::= m1::Message m2::Message
{
  return locationLte(m1.location, m2.location);
}

