grammar silver:translation:java:concrete_syntax:copper;
import silver:definition:concrete_syntax;
import silver:definition:env;
import silver:translation:java:core hiding makeName;

import silver:util;

function makeCopperName
String ::= str::String
{
  return substitute("_", ":", str);
}

function makeCopperNames
String ::= sep::String str::[String]
{
  return if null(str) then "" else substitute("_", ":", head(str)) ++ (if null(tail(str)) then "" else sep ++ makeCopperNames(sep, tail(str))) ;
}


function makeCopperGrammarSpec
String ::= grammar_name::String spec::Decorated ParserSpec
{
  local attribute s :: String;
  s = makeCopperName(spec.startName);

----  local attribute filtered :: FilterResult;
----  filtered = filterSyntax(spec.hiddenSyntax, spec.terminalDcls, spec.ruleDcls);

  local attribute precsRules :: [Decorated ParserPrecSpec];
----  precsRules = getParserPrecSpecRules(filtered.ruleDcls);
  precsRules = getParserPrecSpecRules(spec.ruleDcls);

--  local attribute merged :: [Decorated RuleSpec];
----  merged = mergeRules(filtered.ruleDcls);
--  merged = mergeRules(spec.ruleDcls);

  local attribute univLayout :: String;
  univLayout = generateUniversalLayout(grammar_name, spec.terminalDcls) ++ " EmptyString";

  local attribute rv :: String;
  rv = 
             makeDisambiguateSpecString(spec.disambiguationGroupDcls) ++ "\n" ++
----             makeTermTokenSpecString(filtered.terminalDcls, grammar_name) ++
             makeTermTokenSpecString(spec.terminalDcls, grammar_name) ++
             makeStartDclString(grammar_name, spec.startName, univLayout) ++ "\n" ++
             makeNonTermDclString(spec.nonTerminalDcls, spec.startName) ++ "\n" ++
----             makePrecSpecCopperSpec(filtered.terminalDcls) ++ "\n\n" ++
             makePrecSpecCopperSpec(spec.terminalDcls) ++ "\n\n" ++
----             makeProdDclString(spec, filtered.terminalDcls, filtered.ruleDcls, univLayout);
             makeProdDclString(univLayout,spec.ruleDcls);
  return rv;
}

function makeDisambiguateSpecString
String ::= specs::[Decorated DisambiguationGroupSpec]
{
   return if null(specs)
          then ""
          else "ambiguous term group " ++ makeCopperName(head(specs).groupName)
               ++ " code @" ++ head(specs).actionCode ++ "\n@ members "
               ++ makeCopperNames(" ", head(specs).groupMembers) ++ "\n" ++
               makeDisambiguateSpecString(tail(specs));
}
               
function makeTermTokenSpecString
String ::= specs::[Decorated TerminalSpec] grammar_name::String
{
  return if null(specs)
	 then ""
	 else "term " ++ makeCopperName(head(specs).terminalName) ++ " :" ++
              substring(1, length(head(specs).terminalRegExpr) -1, head(specs).terminalRegExpr) ++
              "\n" ++
              token_dcl ++
              makeTermTokenSpecString(tail(specs),grammar_name);

  local attribute token_dcl :: String ;
  token_dcl = "token " ++ makeCopperName(head(specs).terminalName) ++ 
                 " class { " ++ makeCopperNames(" ", head(specs).lexerClasses) ++ " } " ++
                 " precedence submits to { " ++ makeCopperNames(" ", head(specs).submitsTo) ++ " } " ++
                 " dominates { " ++ makeCopperNames(" ", head(specs).termDominates) ++ " } " ++
                 " prefix { } code @" ++ 
		head(specs).actionCode ++ 
"\nRESULT = new common.Terminal(lexeme,virtualLocation.getLine(),virtualLocation.getColumn());\n" ++ 
"@\n\n" ;  

}

function makeStartDclString
String ::= grammar_name::String sym::String univLayout::String
{
  return "nonterm " ++ makeCopperName(sym) ++ "\n" ++
                "start " ++ makeCopperName(sym) ++ " layout { " ++ univLayout ++ " }\n" ++
         "term EmptyString :\n" ++
         "token EmptyString class { _ignore } precedence submits to { } dominates { }  prefix {} code @@\n\n";
}

function makeNonTermDclString
String ::= syms::[Decorated NonTerminalSpec] sName::String
{
    return if null(syms)
           then ""
           else (if !(sName == head(syms).nonTerminalName) 
		then "nonterm " ++ makeCopperName(head(syms).nonTerminalName) ++ "\n" 
		else "") ++ 
		makeNonTermDclString(tail(syms), sName);

}


function makePrecSpecCopperSpec
String ::= specs::[Decorated TerminalSpec]
{
  local attribute precedenceClass :: String;
  precedenceClass = "main";

  return if null(specs) 
	 then ""
	 else (if head(specs).parserPrecedence != 0
               then ("operator " ++
              makeCopperName(head(specs).terminalName) ++
              " class " ++
              precedenceClass ++
              " precedence " ++
              toString(head(specs).parserPrecedence) ++
              " associativity " ++
              head(specs).parserAssociation ++
              "\n")
               else "") ++
              makePrecSpecCopperSpec(tail(specs));
}


function makeProdDclString
String ::= univLayout::String rules::[Decorated RuleSpec]
{
  return if null(rules)
         then ""
         else makeProdSpecsNonterm(univLayout,makeCopperName(head(rules).ruleLHS),head(rules).ruleRHSSpec) ++
              makeProdDclString(univLayout,tail(rules));
}

function makeProdSpecsNonterm
String ::= univLayout::String lhs::String rhs::[Decorated RHSSpec]
{
  return if null(rhs)
         then ""
         else "# " ++ head(rhs).ruleGrammarName ++  " --- " ++ head(rhs).ruleGrammarName ++ "\n" ++
              "prod " ++
              "Production_" ++ makeCopperName(head(rhs).ruleName) ++
              " class " ++
              "main" ++
              " precedence " ++
              toString(head(rhs).parserPrecedence) ++ 
              " operator { " ++
              " } layout { " ++
              univLayout ++ " EmptyString" ++
              " } code @\n" ++
"RESULT = new " ++ makeClassName(head(rhs).ruleName) ++ "(_children);\n" ++
              "\n     " ++ head(rhs).actionCode ++
              "\n@ bnf " ++
              lhs ++
              " -> " ++
              (if null(head(rhs).ruleRHS)
                  then "eps"
                  else makeProdRHS(head(rhs).ruleRHS)) ++
              "\n\n" ++
              makeProdSpecsNonterm(univLayout,lhs,tail(rhs));            
}

function makeProdRHS
String ::= syms::[String]
{
  return if null(syms)
         then ""
         else makeCopperName(head(syms)) ++ (if null(tail(syms)) then "" else " ") ++ makeProdRHS(tail(syms));
}

function generateUniversalLayout
String ::= grammar_name::String terminals::[Decorated TerminalSpec]{
  local attribute layouts :: [Decorated TerminalSpec];
  layouts = filterIgnores(terminals);

  return generateLayoutList(layouts);
}

function generateLayoutList
String ::= layouts::[Decorated TerminalSpec]{
  return if null(layouts)
         then ""
         else makeCopperName(head(layouts).terminalName) ++ " " ++
              generateLayoutList(tail(layouts));
}

function filterIgnores
[Decorated TerminalSpec] ::= terminals::[Decorated TerminalSpec]
{
  return if null(terminals)
         then []
         else if head(terminals).ignoreTerminal
              then cons(head(terminals),filterIgnores(tail(terminals)))
              else filterIgnores(tail(terminals));
}
