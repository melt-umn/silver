grammar silver:util:command;
import silver:util;

terminal Flag_t /[\-]([\-]?)[a-zA-Z0-9\-]+/ dominates {WhiteSpace_t};
terminal Chunk_t /[^\-\ ][^\ ]*/ dominates {WhiteSpace_t};
ignore terminal WhiteSpace_t /[\ \t]+/;

nonterminal Command with flags, grammarName, okay, usage, flag_usage;
nonterminal PieceList with flags, lookups, needChunk, nextChunk, lastChunk, grammarName, hasChunk;

synthesized attribute okay :: Boolean;
synthesized attribute usage :: String;

-- EVW: I'm adding this so that we can re use the command line grammar for other
-- languages and build custom error messages (e.g. that don't include "GrammarName")
synthesized attribute flag_usage :: String;

autocopy attribute lookups :: [Flag];
inherited attribute needChunk :: Boolean;
synthesized attribute nextChunk :: String;
synthesized attribute lastChunk :: Boolean;

synthesized attribute grammarName :: String;

synthesized attribute flags :: [Flag];

--abstract production main
--top::Main ::= args::String{
--  local attribute p :: Command;
--  p = parse(args);

--  top.ioOut = if !p.okay then print(p.usage, top.ioIn) else print(parse(args).grammarName ++ "\n" ++ printf(parse(args).flags) ++ "\n\n", top.ioIn);
--}

concrete production cRootAll
top::Command ::=  c1::PieceList
{
  production attribute uses :: [String] with ++;
  uses := [];

  production attribute flagLookups :: [Flag] with ++;
  flagLookups := [];


  top.flags = c1.flags;
  top.grammarName = c1.grammarName;
  top.usage = "\nsilver [Options] GrammarName\n" ++ implode("", uses) ++ "\n\n";
  top.flag_usage = implode("", uses) ++ "\n\n";

  top.okay = isOkay(flagLookups, c1.flags);

  c1.lookups = flagLookups;

  c1.needChunk = false ;
}

function isOkay
Boolean ::= exp::[Flag] fnd::[Flag]{

  local attribute fl :: [Flag];
  fl = findFlag(head(fnd).flag, exp);
 
  return if null(fnd) then true else !null(fl) && (head(fnd).hasChunk || !head(fl).hasChunk) && isOkay(exp, tail(fnd));
}

concrete production cPieceNone
top::PieceList ::=
{
  top.grammarName = "Not:Defined";
  top.lastChunk = true;
  top.hasChunk = false;
  top.flags = [];
}

concrete production cFlag
top::PieceList ::= c1::Flag_t c2::PieceList
{
  top.grammarName = c2.grammarName;

  top.hasChunk = false;
  top.lastChunk = false;
  top.nextChunk = "";

  local attribute fl :: [Flag];
  fl = findFlag(c1.lexeme, top.lookups);

  local attribute nChunk :: Boolean;
  nChunk = !null(fl) && head(fl).hasChunk;

  c2.needChunk = nChunk;

  top.flags = [flagDef(c1.lexeme, if nChunk then c2.nextChunk else "", nChunk && c2.hasChunk)] ++ c2.flags;
}

concrete production cChunk
top::PieceList ::= c1::Chunk_t c2::PieceList
{
  top.hasChunk = true;
  top.lastChunk = false;
  top.nextChunk = c1.lexeme;

  c2.needChunk = false;

  top.flags = c2.flags;
  top.grammarName = if !top.needChunk && c2.lastChunk then c1.lexeme else c2.grammarName;
}

function printf
String ::= l::[Flag]{
  return if null(l) then "\n" else head(l).flag ++ " " ++ head(l).chunk ++ "\n" ++ printf(tail(l));
}

nonterminal Flag with flag, chunk, hasChunk;
synthesized attribute flag :: String;
synthesized attribute chunk :: String;
synthesized attribute hasChunk :: Boolean;

abstract production flagDef
top::Flag ::= f::String c::String b::Boolean{
  top.flag = f;
  top.chunk = c;
  top.hasChunk = b;
}

abstract production flagLookup
top::Flag ::= f::String b::Boolean {
  top.flag = f;
  top.chunk = "";
  top.hasChunk = b;
}


function findFlag
[Flag] ::= f::String l::[Flag]{
  return if null(l) then [] else (if head(l).flag == f then [head(l)] else []) ++ findFlag(f, tail(l));
}

function foldf
String ::= s::String c1::[Flag]
{
  return if null(c1) then "" else head(c1).chunk ++ (if null(tail(c1)) then "" else s) ++ foldf(s, tail(c1));
}

function getFlagChunk
String ::= f::Flag
{
  return f.chunk;
}
