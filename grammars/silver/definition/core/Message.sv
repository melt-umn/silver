grammar silver:definition:core;

{-- 
  warning:1, error:2 
  (in line with IDE runtime and Eclipse severity value; see org.eclipse.core.resources.IMarker)
--}
synthesized attribute severity :: Integer; 
synthesized attribute msg :: String;

{--
 - Representation of diagnostic outputs of the compiler.
 - e.g. errors, warnings, etc.
 -}
nonterminal Message with location, pp, severity, msg;

abstract production err
top::Message ::= l::Location s::String
{
  top.location = l;
  top.pp = l.filename ++ ":" ++ toString(l.line) ++ ":" ++ toString(l.column) ++ ": error: " ++ s;
  top.severity = 2;
  top.msg = s;
}

abstract production wrn
top::Message ::= l::Location s::String
{
  top.location = l;
  top.pp = l.filename ++ ":" ++ toString(l.line) ++ ":" ++ toString(l.column) ++ ": warning: " ++ s;
  top.severity = 1;
  top.msg = s;
}

function foldMessages
String ::= es::[Message]
{
  return implode("\n", map((.pp), es)) ++ "\n";
}

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

function messageLte
Boolean ::= m1::Message m2::Message
{
  return locationLte(m1.location, m2.location);
}

