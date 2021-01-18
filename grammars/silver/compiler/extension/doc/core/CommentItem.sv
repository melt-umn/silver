grammar silver:compiler:extension:doc:core;

synthesized attribute body :: String;
synthesized attribute loc :: Location;
nonterminal CommentItem with body, loc, doEmit;

{-
Used by other productions to construct 
items for the list of CommentItems that 
AGDcl is given
-}

function makeStub
String ::= docUnparse::String grammarName::String loc::Location
{
	return
s"""## ${docUnparse}
Contained in grammar ${grammarName}. [Defined at ${substitute(":", "/", grammarName)}/${loc.filename} line ${toString(loc.line)}](https://github.com/melt-umn/silver/blob/develop/grammars/${substitute(":", "/", grammarName)}/${loc.filename}#L${toString(loc.line)})""";
}

abstract production dclCommentItem
top::CommentItem ::= dcl::Decorated AGDcl body::Decorated DclComment
{
	top.body = makeStub(dcl.docUnparse, dcl.grammarName, dcl.location) ++ "\n\n" ++ body.body;
	top.loc = dcl.location;
	top.doEmit = body.doEmit;
}

abstract production standaloneDclCommentItem
top::CommentItem ::= body::Decorated DclComment
{
	top.body = body.body;
	top.loc = body.location;
	top.doEmit = body.doEmit;
}