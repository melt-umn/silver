grammar silver:compiler:extension:doc:core;

synthesized attribute body :: String;
synthesized attribute stub :: Boolean;
synthesized attribute docNames :: [String];
synthesized attribute undocNames :: [String];
tracked nonterminal CommentItem with body, doEmit, stub, docNames, undocNames;

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
String ::= forName::String docUnparse::String grammarName::String
{
  local loc::Location = getParsedOriginLocation(ambientOrigin()).fromJust;
  return
s"""## ${docUnparse} {#${sanitizeAnchor(forName)}}
Contained in grammar `[${grammarName}]`. Defined at [${substitute(":", "/", grammarName)}/${loc.filename} line ${toString(loc.line)}](https://github.com/melt-umn/silver/blob/develop/grammars/${substitute(":", "/", grammarName)}/${loc.filename}#L${toString(loc.line)}).""";
}

abstract production dclCommentItem
top::CommentItem ::= forName::String docUnparse::String grammarName::String body::Decorated DclComment
{
  top.body = body.indentBy ++ substitute("\n", "\n"++body.indentBy,
    makeStub(forName, docUnparse, grammarName) ++ "\n\n" ++ body.body);
  top.doEmit = body.doEmit;
  top.stub = false;
  top.docNames = [forName];
  top.undocNames = [];
}

abstract production standaloneDclCommentItem
top::CommentItem ::= body::Decorated DclComment
{
  top.body = substitute("\n", "\n"++body.indentBy, body.body);
  top.doEmit = body.doEmit;
  top.stub = false;
  top.docNames = [];
  top.undocNames = [];
}

abstract production undocumentedItem
top::CommentItem ::= forName::String docUnparse::String grammarName::String
{
  top.body = makeStub(forName, docUnparse, grammarName) ++ "\n\n (Undocumented.)";
  top.doEmit = true;
  top.stub = true;
  top.docNames = [];
  top.undocNames = [forName];
}

function mkUndocumentedItem
CommentItem ::= f::String t::Decorated AGDcl
{
  return undocumentedItem(f, t.docUnparse, t.grammarName);
}
