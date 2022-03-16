grammar silver:core;

@{- The mechanism by which the debugger is hooked into a Silver program. When
  - the SILVER_DEBUG_LABEL environment variable is provided to the Silver
  - program, and it matches the label, the debugger is entered, debugging the
  - expression given as the second argument.
  -
  - Otherwise, this acts as the identity function, returning expr unchanged.
  -
  - Make sure labels are unique!
  -
  - This is designed to be approximately zero-cost when the SILVER_DEBUG_LABEL
  - environment variable is not set, so it should be fine to leave a bunch of
  - these scattered throughout the code.
  -}
function debugPoint
a ::= label::String  expr::a
{
  return error("foreign function debugPoint not implemented");
} foreign {
  "java" : return "common.Util.debugPoint(%?label?%, %expr%)";
}
