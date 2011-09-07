grammar silver:langutil:pp;

import silver:util:deque;

function show
String ::= width::Integer d::Document
{
  d.indent = 0;
  d.width = width;
  
  d.inPosition = 0;
  d.inDq = dqEmpty();
  d.inCHorizontals = false :: d.horizontals;
  d.inRemaining = width;
  
  return d.result;
}

--------------------------------------------------------------------------------

function concat
Document ::= ds::[Document]
{
  return if null(ds) then notext()
         else foldl_p(cat, head(ds), tail(ds));
}
function nestlines
Document ::= n::Integer inner::Document
{
  return cat(nest(n, cat(line(), inner)), line());
}
function groupnest
Document ::= n::Integer inner::Document
{
  return group(nest(n, inner));
}
function groupnestlines
Document ::= n::Integer inner::Document
{
  return cat(groupnest(n, cat(line(), inner)), line());
}
function space
Document ::=
{
  return text(" ");
}
function softbreak
Document ::=
{
  return group(line());
}
function semi
Document ::=
{
  return text(";");
}
function comma
Document ::=
{
  return text(",");
}
function braces
Document ::= d::Document
{
  return cat(cat(text("{"), d), text("}"));
}
function parens
Document ::= d::Document
{
  return cat(cat(text("("), d), text(")"));
}
function brackets
Document ::= d::Document
{
  return cat(cat(text("["), d), text("]"));
}

{- Below this line:
text     Document ::= String
cat      Document ::= Document Document
line     Document ::= 
group    Document ::= Document
nest     Document ::= Integer Document
notext   Document ::=
box      Document ::= Document
realLine Document ::= 
-}
--------------------------------------------------------------------------------

nonterminal Document with indent, width,
                          inPosition, inDq, inCHorizontals, inRemaining,
                          outPosition, outDq, outCHorizontals, outRemaining,
                          result, horizontals;

autocopy attribute indent :: Integer;
autocopy attribute width :: Integer;

-- The scanning process
inherited attribute inPosition :: Integer;
inherited attribute inDq :: Deque<Pair<Integer [Boolean]>>;
synthesized attribute outPosition :: Integer;
synthesized attribute outDq :: Deque<Pair<Integer [Boolean]>>;

synthesized attribute horizontals :: [Boolean]; -- output of scanning process

-- The printing process
inherited attribute inCHorizontals :: [Boolean];
inherited attribute inRemaining :: Integer;
synthesized attribute outCHorizontals :: [Boolean];
synthesized attribute outRemaining :: Integer;

synthesized attribute result :: String; -- output of printing process

{-
Some notes on deciphering all this:

in/out Position is some hypothetical value that's part of the "scanning process"
that doesn't represent real horizontal position.

in/out Dq is a deque that represent all current "pending" groups, top to bottom.
It's a deque because we might "decide" groups higher up in the tree, as well as
at the bottom.  Wholy a part of the "scanning process."

in/out CHorizontals is a list of H/V values that will be consumed by groups
during the "printing process."

in/out Remaining records real horizontal position of the "printing process."
(Also peeked at by the scanning process)

horizontals is the initially produced H/V values by the "scanning process."
It's essentially a list of booleans produced by a pre-order scan of the tree,
meant to be consumed by a pre-order scan of the tree.
-}


{--
 - Literal text. (Do not use with newlines!)
 -}
abstract production text
top::Document ::= s::String
{
  local pr :: Pair<Deque<Pair<Integer [Boolean]>> [Boolean]> = prune(top.outPosition, top.inDq);
  
  top.outPosition = top.inPosition + length(s);
  top.outDq = pr.fst;
  top.outCHorizontals = top.inCHorizontals;
  top.outRemaining = top.inRemaining - length(s);

  top.result = s;
  top.horizontals = pr.snd;
}

{--
 - Concatenate two documents.
 -}
abstract production cat
top::Document ::= d1::Document d2::Document
{
  d1.inPosition = top.inPosition;
  d1.inDq = top.inDq;
  d1.inCHorizontals = top.inCHorizontals;
  d1.inRemaining = top.inRemaining;

  d2.inPosition = d1.outPosition;
  d2.inDq = d1.outDq;
  d2.inCHorizontals = d1.outCHorizontals;
  d2.inRemaining = d1.outRemaining;
  
  top.outPosition = d2.outPosition;
  top.outDq = d2.outDq;
  top.outCHorizontals = d2.outCHorizontals;
  top.outRemaining = d2.outRemaining;

  top.result = d1.result ++ d2.result;
  top.horizontals = d1.horizontals ++ d2.horizontals;
}

{--
 - Either a space, or a linebreak plus indentation.
 - The behavior of EVERY line in a group is identical.
 -}
abstract production line
top::Document ::= 
{
  local pr :: Pair<Deque<Pair<Integer [Boolean]>> [Boolean]> = prune(top.outPosition, top.inDq);
  local horizontal :: Boolean = head(top.inCHorizontals);
  
  top.outPosition = top.inPosition + 1;
  top.outDq = pr.fst;
  top.outCHorizontals = top.inCHorizontals;
  top.outRemaining = if horizontal then top.inRemaining - 1 else top.width - top.indent;

  top.result = if horizontal then " " else "\n" ++ replicate(top.indent, " ");
  top.horizontals = pr.snd;
}

{--
 - Does nothing but control the behavior of all lines that have this group
 - as their closest enclosing group.
 -}
abstract production group
top::Document ::= d::Document
{
  d.inPosition = top.inPosition;
  d.inDq = enter(top.inPosition + top.inRemaining, top.inDq);
  -- the head of this is the "is local group horizontal" information. So, we remove it to
  -- expose the inner group's info
  d.inCHorizontals = tail(top.inCHorizontals);
  d.inRemaining = top.inRemaining;

  local le :: Pair<Deque<Pair<Integer [Boolean]>> [Boolean]> = leave(d.outPosition, d.outDq);

  top.outPosition = d.outPosition;
  top.outDq = le.fst;
  -- Put the local group's info back on the top, then consume the inner group's info
  -- so it's no longer there. (so the next group get its inner when it does the tail)
  top.outCHorizontals = head(top.inCHorizontals) :: tail(d.outCHorizontals);
  top.outRemaining = d.outRemaining;

  top.result = d.result;
  top.horizontals = d.horizontals ++ le.snd;
}

{--
 - Increase the indentation level (but does not directly indent itself!)
 -}
abstract production nest
top::Document ::= depth::Integer d::Document
{
  forwards to d;
  forward.indent = top.indent + depth;
}

abstract production notext
top::Document ::=
{
  forwards to text("");
}

abstract production box
top::Document ::= d::Document
{
  forwards to d;
  -- top.inPosition doesn't represent our horizontal position in actual printing
  forward.indent = top.width - top.inRemaining;
}

abstract production realLine
top::Document ::= 
{
  -- I'm not 100% on this combinator
  top.outPosition = top.inPosition + (top.inRemaining - top.outRemaining);
  top.outDq = top.inDq;
  top.outCHorizontals = top.inCHorizontals;
  top.outRemaining = top.width - top.indent;

  top.result = "\n" ++ replicate(top.indent, " ");
  top.horizontals = [];
}

--------------------------------------------------------------------------------

function prune
Pair<Deque<Pair<Integer [Boolean]>> [Boolean]> ::= p::Integer q::Deque<Pair<Integer [Boolean]>>
{
  return if dqIsEmpty(q) then pair(q, [])
         else let h::Pair<Integer [Boolean]> = dqHead(q)
               in if p <= h.fst then pair(q, [])
                  else let recur::Pair<Deque<Pair<Integer [Boolean]>> [Boolean]> = prune(p, dqTail(q))
                        in pair(recur.fst, false :: (h.snd ++ recur.snd))
                       end
              end;
}

function enter
Deque<Pair<Integer [Boolean]>> ::= p::Integer q::Deque<Pair<Integer [Boolean]>>
{
  return dqSnoc(q, pair(p, []));
}

function leave
Pair<Deque<Pair<Integer [Boolean]>> [Boolean]> ::= p::Integer q::Deque<Pair<Integer [Boolean]>>
{
  return if dqIsEmpty(q) then pair(q, [])
         else let h1::Pair<Integer [Boolean]> = dqLast(q),
                  t1::Deque<Pair<Integer [Boolean]>> = dqInit(q)
               in if dqIsEmpty(t1) then pair(t1, true :: h1.snd)
                  else let h2::Pair<Integer [Boolean]> = dqLast(t1),
                           t2::Deque<Pair<Integer [Boolean]>> = dqInit(t1)
                        in pair(dqSnoc(t2, pair(h2.fst, h2.snd ++ [p <= h1.fst] ++ h1.snd)), [])
                       end
              end;
}

