
{-- To Treesitter Strings Functions --}
{--
  Transforms a nonterminal represented as a pair(LHS, RHS) to a treesitter
  string representing the nonterminal using the precedence and associativity
  environment.
--}
function nonterminal_to_TS_string
String ::= env::[Pair<String Pair<Integer String>>] nt::Pair<String [[String]]>
{
  return nt.fst ++ ": $ => " ++ nonterminal_RHS_to_TS_string(env, nt.snd);
}

{--
  Transforms the right hand side of a nonterminal to a treesitter string.
--}
function nonterminal_RHS_to_TS_string
String ::= env::[Pair<String Pair<Integer String>>] nonterminalRHS::[[String]]
{
  return
    if length(nonterminalRHS) > 1 then
      "choice(" ++ productions_to_TS_string(env, nonterminalRHS) ++ ")"
    else
      productions_to_TS_string(env, nonterminalRHS);
}

{--
  Transforms the productions that make up a nonterminal into a string
--}
function productions_to_TS_string
String ::= env::[Pair<String Pair<Integer String>>] nonterminalProds::[[String]]
{
  return implode(",\n    ", map(production_to_TS_string(env, _), nonterminalProds));
}

{--
  Transforms a production into a treesitter string using the precedence and
  associativity environment
--}
function production_to_TS_string
String ::= env::[Pair<String Pair<Integer String>>] prod_inputs::[String]
{
  local attribute prec_assoc_info :: Maybe<Pair<Integer String>> =
    lookupByList(stringEq, env, map(TsIdentifierToDeclaration, prod_inputs));
  return
    if prec_assoc_info.isJust then
      prec_assoc_info_as_TS_string(prec_assoc_info.fromJust) ++
      production_input_list_to_string(prod_inputs) ++
      ")" -- closing paren from the precedence
    else
      production_input_list_to_string(prod_inputs);
}

{--
  Transforms the production input list into a treesitter string
--}
function production_input_list_to_string
String ::= prod_inputs::[String]
{
  return
  if length(prod_inputs) > 1 then
    "seq(" ++ implode(", ", prod_inputs) ++ ")"
  else
    head(prod_inputs);
}

{--
  Transforms the precedence and associativity info into a string that can be
  used by treesitter.
--}
function prec_assoc_info_as_TS_string
String ::= info::Pair<Integer String>
{
  -- both precedence and associativity specified
  return
  if info.fst != 0 && !stringEq(info.snd, "none") then
    s"""prec.${info.snd}(${toString(info.fst)}, """
  -- only precedence specified
  else if (info.fst != 0) then
    s"""prec(${toString(info.fst)}, """
  --only associativity specified
  else
    s"""prec.${info.snd}(""";
}
{-- GENERAL PURPOSE STRING MANIPULATION FUNCTIONS --}

{--
 - Replaces all instances matching the 'regex' with 'replace' in 'str'
 -
 - @param search  The regex
 - @param replace  The string to substitute in
 - @param str  The string to operate on
 - @return  The modified form of 'str'
 -}
function substituteRegex
String ::= regex::String replace::String str::String
{
  return error("Not Yet Implemented: substituteRegex");
} foreign {
  "java" : return "new common.StringCatter(%str%.toString().replaceAll(%regex%.toString(),%replace%.toString()))";
}

{--
 - Convert all strings in str to lowercase
 -
 - @param str  The string to operate on
 - @return  The modified form of 'str'
 -}
function toLowerCase
String ::= str::String
{
  return error("Not Yet Implemented: toLowerCase");
} foreign {
  "java" : return "new common.StringCatter(%str%.toString().toLowerCase())";
}
