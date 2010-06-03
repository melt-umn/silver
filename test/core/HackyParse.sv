grammar core;

nonterminal HackyParse;
synthesized attribute parseSuccess :: Boolean occurs on HackyParse;
synthesized attribute parseTree :: AnyType occurs on HackyParse;
synthesized attribute parseErrors :: String occurs on HackyParse;


abstract production hackyParser
top::HackyParse ::= succ::Boolean tree::AnyType fail::String
{
  top.parseSuccess = succ;
  top.parseTree = tree;
  top.parseErrors = fail;
}

function hackParse
HackyParse ::= pfunc::AnyType dat::String
{
  return error("Not impl");
} foreign {
  "java" : return "(common.Util.hackyhackyParse(%pfunc%, %dat%))";
}

