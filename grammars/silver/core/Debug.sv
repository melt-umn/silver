grammar silver:core;

@{-
 - Launch an interactive debug session to explore a tree.
 -}
function debug
Decorated a with i ::= tree::Decorated a with i
{
  return error("foreign function");
} foreign {
  "java" : return "common.Debug.runDebug(%tree%)";
}
