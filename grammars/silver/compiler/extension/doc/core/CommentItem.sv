grammar silver:compiler:extension:doc:core;

synthesized attribute body :: String;
synthesized attribute loc :: Location;
synthesized attribute stub :: Boolean;
synthesized attribute docNames :: [String];
synthesized attribute undocNames :: [String];
tracked nonterminal CommentItem with body, loc, doEmit, stub, docNames, undocNames;

{-
Used by other productions to construct 
items for the list of CommentItems that 
AGDcl is given
-}

function sanitizeAnchor
String ::= n::String
{
  return substitute(" ", "_", substitute("[", "_", substitute("]", "_", 
       substitute("<", "_", substitute(">", "_", substitute("(", "_", 
       substitute(")", "_", n)))))));
}

function makeStub
String ::= forName::String docUnparse::String grammarName::String loc::Location
{
  return
s"""## ${docUnparse} {#${sanitizeAnchor(forName)}}
Contained in grammar `[${grammarName}]`. Defined at [${substitute(":", "/", grammarName)}/${loc.filename} line ${toString(loc.line)}](https://github.com/melt-umn/silver/blob/develop/grammars/${substitute(":", "/", grammarName)}/${loc.filename}#L${toString(loc.line)}).""";
}

abstract production dclCommentItem
top::CommentItem ::= forName::String docUnparse::String grammarName::String location::Location body::Decorated DclComment
{
  top.body = body.indentBy ++ substitute("\n", "\n"++body.indentBy,
    makeStub(forName, docUnparse, grammarName, location) ++ "\n\n" ++ body.body);
  top.loc = location;
  top.doEmit = body.doEmit;
  top.stub = false;
  top.docNames = [forName];
  top.undocNames = [];
}

abstract production standaloneDclCommentItem
top::CommentItem ::= body::Decorated DclComment
{
  top.body = substitute("\n", "\n"++body.indentBy, body.body);
  top.loc = body.location;
  top.doEmit = body.doEmit;
  top.stub = false;
  top.docNames = [];
  top.undocNames = [];
}

abstract production undocumentedItem
top::CommentItem ::= forName::String docUnparse::String grammarName::String location::Location 
{
  top.body = makeStub(forName, docUnparse, grammarName, location) ++ "\n\n (Undocumented.)";
  top.loc = location;
  top.doEmit = true;
  top.stub = true;
  top.docNames = [];
  top.undocNames = [forName];
}

function mkUndocumentedItem
CommentItem ::= f::String t::Decorated AGDcl
{
  return undocumentedItem(f, t.docUnparse, t.grammarName, t.location);
}
