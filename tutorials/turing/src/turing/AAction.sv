grammar turing;

{--
 - AAction simple transforms a input tape to an output tape, depending on the type of action.
 -}
nonterminal AAction with oldTape, newTape;
inherited attribute oldTape :: Decorated ATape;
synthesized attribute newTape :: Decorated ATape;

abstract production moveLeft
top::AAction ::=
{
  top.newTape = decorate newATapeFull(tl, th, tr) with {};

  local attribute e :: Boolean;
  e = null(top.oldTape.tapeLeft);
  
  local attribute tl :: [String];
  tl = if e then [] else tail(top.oldTape.tapeLeft);

  local attribute th :: String;
  th = if e then "0" else head(top.oldTape.tapeLeft);

  local attribute tr :: [String];
  tr = cons(top.oldTape.tapeHead, top.oldTape.tapeRight);
}

abstract production moveRight
top::AAction ::=
{
  top.newTape = decorate newATapeFull(tl, th, tr) with {};

  local attribute e :: Boolean;
  e = null(top.oldTape.tapeRight);
  
  local attribute tl :: [String];
  tl = cons(top.oldTape.tapeHead, top.oldTape.tapeLeft);

  local attribute th :: String;
  th = if e then "0" else head(top.oldTape.tapeRight);

  local attribute tr :: [String];
  tr = if e then [] else tail(top.oldTape.tapeRight);
}

abstract production printToTape
top::AAction ::= s::String 
{
  top.newTape = decorate newATapeFull(top.oldTape.tapeLeft, s, top.oldTape.tapeRight) with {};
}

abstract production doNothing
top::AAction ::=
{
  top.newTape = top.oldTape;
}

