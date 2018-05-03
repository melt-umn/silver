---
layout: sv_wiki
title: IO
menu_title: IO
---

> _**Note:**_ Silver now has support for Monads, so this is in the process of being obsoleted, eventually.  See <update this with a link> for more details.

Silver's IO support ~~is~~**was** terrible. Let's get that out of the way right from the beginning. Silver programs are largely intended to (1) read a file in its entirety and (2) write a file in its entirety, while (3) maybe printing some messages to the console. If you try to be fancier, you'll be all _smdh_.

## How it works

Start with `main`:

```
function main 
IOVal<Integer> ::= args::[String] ioin::IO
{
  return ...;
}
```

You get an _input IO token_ `ioin`. You are then responsible for threading this through every IO function you call, in the correct order, and then out through the standard "IO and also some other value" type called `IOVal`.

Lets take a look at some types:

```
abstract production ioval
top::IOVal<a> ::= i::IO v::a

function print
IO ::= s::String i::IO

function readFile
IOVal<String> ::= s::String i::IO

function writeFile
IO ::= file::String contents::String i::IO
```

So, first off, if you're getting used to Silver conventions, you'll notice that `ioval` has its parameters _backwards_ from what you'd expect. This is indefensible. Sorry.

Next, you can see how every function follows the same pattern: you pass in the token, then you either get a token back, or an `IOVal` back.

This is partly because we don't have a `unit` type yet, so print can't return `IOVal<Unit>` or whatever. You know, to be consistent. Oh well. When we fix IO all this will be thrown out anyway.

Here's a helpful google images search:

https://www.google.com/search?tbm=isch&q=facepalm

Could be worse. Back in the day we had type like `IOString` and `IOBoolean` and `IOInteger` and of course, names of the attributes were different on each. You kids these days are your `IOVal`...

## Troubles

If you don't pass your IO tokens around properly, things can happen weird. Sometimes the order of stuff might get switched around like time travel. Or things don't get done.

But most often, the issue people hit is the performing of IO actions getting driven by the demand for values other than the IO token. (e.g. you read from a file, and instead of the IO token being what causes the read to happen, it's the demand for the file's contents as a string.)

I invite you to marvel at this code:

```
{--
 - Run units until a non-zero error code is encountered.
 -}
function runAll
IOVal<Integer> ::= l::[Unit] i::IO
{
  local attribute now :: Unit;
  now = head(l);
  now.ioIn = i;

  return  if unsafeTrace(null(l), i) -- TODO: this is just to force strictness...
	  then ioval(i, 0)
	  else if now.code != 0
	       then ioval(now.io, now.code)
	       else runAll(tail(l), now.io);
}
```

The call to `unsafeTrace` demands the IO token `i` before returning the other parameter. Why? Well, you'll notice that accessing `now.code` may depend upon that `Unit`'s IO actions... which means we'd be demanding actions get taken via their return value, not their IO token. Which means ordering can get ~wonky~.

This is evidence that our token passing model is fundamentally broken. Take a laugh.

How did pre-monad Haskell get around this? Our `IOVal` type isn't really adequate. It's a pair. pre-monad Haskell would use a function. i.e.:

```
abstract production ioval
IOVal<a> ::= f::(Pair<a IO> ::= IO)
```

How demand was driven (demanding the value or the IO token), in this case, doesn't matter, because both will trigger calling the function, which will trigger demanding the previous IO token. All good.

Silver doesn't have good enough lambda support to do this yet. We probably could stick an `unsafeTrace` inside the `ioval` production for accessing the `iovalue` attribute, though. Someone should do that. Maybe.

## Convention that you must follow

Always bind `IOVal` returning functions to a local. That is:

```
local file :: IOVal<String> = readFile(filename, ioin);
```

Why? Because that way, there's exactly one decoration of the `IOVal` undecorated "tree". And therefore, there's one decorated copy (the one implicitly created by the local.) And therefore, there's one cached value of the IO token.

If you, somehow, manage to get two decorated trees corresponding to a single `IOVal` undecorated tree, you may get multiple evaluation of IO functions! Having fun yet?

Here's a forced example, continuing from the above:

```
local readFileTwice :: String = new(file).iovalue ++ new(file).iovalue;
```

Bonus: this will actually cause the file to be read three times, when later on `file.io` is demanded.

Double Bonus: The IO actions that will be repeated will be those all the way back to the last properly cached IO token value. So you might even execute more than just a read action three times here!

Aren't you lucky?
