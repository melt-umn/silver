grammar xrobots:abstractsyntax;
import xrobots:terminals;

nonterminal Stack with  pp, frames;

synthesized attribute frames :: [Pair <Integer Frame>] ;

{--  given a stack and an index, return the frame on the stack at that
     index
--}
function getFrame
Frame ::= s::Stack i::Integer
{
 return
  if null(s.frames)
  then errorFrame()
  else if (head(s.frames)).fst == i
       then (head(s.frames)).snd
 else getFrame(pop(s), i);
}

{-- remove the top frame from the stack --}
abstract production pop
rStack::Stack ::= s::Stack
{
 rStack.frames = tail(s.frames);
 rStack.pp     = printStackList(tail(s.frames));
}

{-- push a new frame on to the stack --}
abstract production push
rStack::Stack ::= s::Stack f::Frame
{
 rStack.frames = [pair(index, f)] ++ s.frames;
 rStack.pp     = f.pp ++ "\n\n" ++ s.pp;

 local attribute index :: Integer;
 index = (head(s.frames)).fst + 1;
}		

{-- return an empty stack --}
abstract production empty_stack
rStack::Stack ::=
{
 rStack.pp = "Empty_Stack\n";
 rStack.frames = [];
}
{-- return the stack as a formatted string for printing --}
function printStackList
String ::= s::[Pair<Integer Frame>]
{
 return
  if null(s)
  then ""
 else toString((head(s)).fst) ++ "\n" ++ (head(s).snd).pp ++ "\n\n";
}

{--retrieve the static link to a variable given the stack and the id of the
   variable to lookup
--}  
function getStaticLink
Integer ::= s:: Stack n::Id_t
{
 return
   if null(s.frames)
   then 0
   else  if n.lexeme == head(s.frames).snd.name.lexeme
         then head(s.frames).fst
          else getStaticLink(pop(s), n);
     
}
  
{-- find the type of an variable in the stack --}
function getType
Type ::= s::Stack n::Id_t
{
 return
   if null(s.frames)
   then errorType()
   else  if found_b(getTopFrame(s).bindings, n)
          then lookup_b(getTopFrame(s).bindings, n)
           else getType (pop(s), n);
}

{-- determine if an variable is in the stack --}
function isDefined
Boolean  ::= s::Stack n::Id_t
{
 return if null(s.frames)
           then false
              else  if found_b(getTopFrame(s).bindings, n)
                     then true
                       else isDefined (pop(s), n);
}

{--retrieve the top frame on the stack --}
function getTopFrame
Frame ::=  s::Stack
{
  return
    if null(s.frames)
    then errorFrame()
      else  (head(s.frames)).snd;
}