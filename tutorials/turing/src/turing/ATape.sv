grammar turing;

{--
 - ATape is a datastructure for storing the current state of the tape.
 -}
nonterminal ATape with tapeLeft, tapeHead, tapeRight, pp;

synthesized attribute tapeLeft :: [String];
synthesized attribute tapeHead :: String;
synthesized attribute tapeRight :: [String];

abstract production emptyATape
top::ATape ::=
{
  top.tapeLeft = [];
  top.tapeHead = "";
  top.tapeRight = [];

  top.pp = "[";
}


abstract production newATape
top::ATape ::= c::String
{
  top.tapeLeft = [];
  top.tapeHead = c;
  top.tapeRight = [];

  top.pp = "[" ++ c ++ "]";
}

abstract production newATapeFull
top::ATape ::= l::[String] c::String r::[String]
{
  top.tapeLeft = l;
  top.tapeHead = c;
  top.tapeRight = r;
  
  local attribute lpp :: String;
  lpp = if null(l) then "" else slfold(", ", slreverse(l)) ++ ", ";

  local attribute rpp :: String;
  rpp = if null(r) then "" else ", " ++ slfold(", ", r);
  
  top.pp = lpp ++ "[" ++ c ++ "]" ++ rpp;
}


function slreverse
[String] ::= sl::[String]
{
  return if null(sl) then [] else slreverse(tail(sl)) ++ [head(sl)];
}

function slfold
String ::= sep::String sl::[String]
{

  return if null(sl) 
	 then ""
	 else (head(sl) ++ if null(tail(sl))
       then ""
       else (sep ++ slfold(sep, tail(sl))));
	  
}
