grammar lib:lsp;

function findTerminalAtPosition
Maybe<TerminalDescriptor> ::= pos::Position terminals::[TerminalDescriptor]
{
  return
  if null(terminals) 
  then nothing()
  else if silverLocationContainsPosition(head(terminals).terminalLocation, pos)
  then just(head(terminals))
  else findTerminalAtPosition(pos, tail(terminals));
}

function mapSnd
Pair<a c> ::= func::(c ::= b) p::Pair<a b>
{
  return pair(fst(p), func(snd(p)));
}

function mapMaybeList
Maybe<[b]> ::= func::(b ::= a) mLst::Maybe<[a]>
{
  return
  case mLst of
  | nothing() -> nothing()
  | just(lst) -> just(map(func, lst))
  end;
}

function allRight
Boolean ::= lst::[Either<l r>]
{
  return
  case lst of
  | [] -> true
  | hd::tl -> hd.isRight && allRight(tl)
  end;
}

function anyLeft
Boolean ::= lst::[Either<l r>]
{
  return
  case lst of
  | [] -> false
  | hd::tl -> hd.isLeft || anyLeft(tl)
  end;
}

function firstLeft
Maybe<l> ::= lst::[Either<l r>]
{
  return
  case lst of
  | [] -> nothing()
  | hd::tl -> if hd.isLeft then just(hd.fromLeft) else firstLeft(tl)
  end;
}

function allFromRight
[r] ::= lst::[Either<l r>]
{
  return
  case lst of
  | [] -> []
  | hd::tl -> hd.fromRight :: allFromRight(tl)
  end;
}

function allFromRightMaybe
Maybe<[r]> ::= m::Maybe<[Either<l r>]>
{
  return
  case m of
  | nothing() -> nothing()
  | just(lst) -> just(allFromRight(lst))
  end;
}

function leftMaybe
Maybe<l> ::= m::Maybe<Either<l r>>
{
  return
  case m of
  | nothing() -> nothing()
  | just(e) -> just(e.fromLeft)
  end;
}

function rightMaybe
Maybe<r> ::= m::Maybe<Either<l r>>
{
  return
  case m of
  | nothing() -> nothing()
  | just(e) -> just(e.fromRight)
  end;
}

function fileToUri
DocumentUri ::= file::String
{
  return "file://" ++ file;
}

function uriToFile
String ::= doc::String
{
  return substring(7, length(doc), doc);
}

function identity
a ::= val::a
{
  return val;
}
