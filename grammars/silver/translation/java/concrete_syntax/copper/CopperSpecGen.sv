grammar silver:translation:java:concrete_syntax:copper;
import silver:definition:core;
import silver:definition:concrete_syntax;
import silver:definition:env;
import silver:translation:java:core hiding makeName;
import silver:translation:java:type;

import silver:util;

import silver:definition:regex;

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

  -- Copper does something odd with layouts, apparently.
  local attribute emptyStr :: Decorated TerminalSpec;
  emptyStr = terminalSpec("EmptyString", [ignoreTerminalModifierSpec()], decorate Rtoeps() with {});

  local attribute univLayout :: String;
  univLayout = generateUniversalLayout(grammar_name, cons(emptyStr, spec.terminalDcls));

  local attribute rv :: String;
  rv = 
"<?xml version=\"1.0\"?>\n\n" ++

"<copperspec id=\"" ++ grammar_name ++ "\" type=\"LALR1\" version=\"1.1\">\n" ++
"  <preamble>\n" ++
"     <code><![CDATA[\n" ++
"import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;\n" ++
"     ]]></code>\n" ++
"  </preamble>\n" ++
"  <attribute id=\"context\" type=\"common.DecoratedNode\">\n" ++
"    <code>context = common.TopNode.singleton;</code>\n" ++
"  </attribute>\n\n" ++ 

             makeDisambiguateSpecString(spec.disambiguationGroupDcls) ++ "\n" ++
             makeParserAttrString(spec.parserAttrDcls) ++ "\n" ++
             makeTermTokenSpecString(cons(emptyStr, spec.terminalDcls), grammar_name) ++
             makeNonTermList(spec.nonTerminalDcls) ++ "\n" ++
             makeStartDclString(spec.startName, univLayout) ++ "\n" ++
             makeProdDclString(univLayout,spec.ruleDcls) ++

"\n</copperspec>\n";

  return rv;
}


function makeParserAttrString
String ::= members::[Decorated ParserAttrSpec]
{
  return if null(members)
         then ""
         else -- TODO: BUG: escape the type here!
"  <attribute id=\"" ++ head(members).name ++"\" type=\"" ++ head(members).typerep.transType ++ "\">\n" ++
"    <code><![CDATA[" ++
  head(members).actionCode ++
"    ]]></code>\n" ++
"  </attribute>\n" ++ makeParserAttrString(tail(members));
}

function makeTermList
String ::= members::[String]
{
  return if null(members)
         then ""
         else "    <term id=\"" ++ makeCopperName(head(members)) ++ "\" />\n" ++ makeTermList(tail(members));
}
function makeTermClassList
String ::= members::[String]
{
  return if null(members)
         then ""
         else "      <termclass id=\"" ++ makeCopperName(head(members)) ++ "\" />\n" ++ makeTermClassList(tail(members));
}
function makeNonTermList
String ::= members::[Decorated NonTerminalSpec]
{
  return if null(members)
         then ""
         else "  <nonterm id=\"" ++ makeCopperName(head(members).nonTerminalName) ++ "\" />\n" ++ makeNonTermList(tail(members));
}

function makeDisambiguateSpecString
String ::= specs::[Decorated DisambiguationGroupSpec]
{
   return if null(specs)
          then ""
          else 

"  <disambig_func id=\"" ++ makeCopperName(head(specs).groupName) ++ "\">\n" ++
	makeTermList(head(specs).groupMembers) ++
"    <code><![CDATA[\n" ++
	head(specs).actionCode ++
"    ]]></code>\n" ++
"  </disambig_func>\n" ++
	makeDisambiguateSpecString(tail(specs));
}
               
function makeTermTokenSpecString
String ::= specs::[Decorated TerminalSpec] grammar_name::String
{
  return if null(specs)
	 then ""
	 else 

"  <term id=\"" ++ makeCopperName(head(specs).terminalName) ++ "\">\n" ++
"    <code><![CDATA[\n" ++
"RESULT = new common.TerminalRecord(lexeme,virtualLocation.getFileName(),virtualLocation.getLine(),virtualLocation.getColumn());\n" ++
	head(specs).actionCode ++ 
"    ]]></code>\n" ++
"    <classes>\n" ++
	makeTermClassList(head(specs).lexerClasses) ++
"    </classes>\n" ++
"    <regex>\n" ++
	head(specs).terminalRegExprSpec.regXML ++

"    </regex>\n" ++
"    <dominates>\n" ++
	makeTermList(head(specs).termDominates) ++
"    </dominates>\n" ++
"    <submits>\n" ++
	makeTermList(head(specs).submitsTo) ++
"    </submits>\n" ++

	(if head(specs).parserPrecedence != 0
	 then 
	"    <operator>\n" ++
	"      <precedence>" ++ toString(head(specs).parserPrecedence) ++ "</precedence>\n" ++
	"      <associativity>" ++ head(specs).parserAssociation ++ "</associativity>\n" ++
	"      <opclass id=\"main\"/>\n" ++
	"    </operator>\n"
	 else "") ++

-- TODO: prefix isn't currently used!
--"    <prefix>
--"      <term id="-"/>
--"    </prefix>

"  </term>\n" ++
	makeTermTokenSpecString(tail(specs),grammar_name);
}

function makeStartDclString
String ::= sym::String univLayout::String
{
  return
"  <start>\n" ++
"    <nonterm id=\"" ++ makeCopperName(sym) ++ "\"/>\n" ++
"    <layout>" ++ univLayout ++ "</layout>\n" ++
"  </start>\n";
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
         else 

"  <prod id=\"Production_" ++ makeCopperName(head(rhs).ruleName) ++ "\" class=\"main\" precedence=\"" ++ toString(head(rhs).parserPrecedence) ++"\">\n" ++
"    <code><![CDATA[\n" ++
"RESULT = new " ++ makeClassName(head(rhs).ruleName) ++ "(_children);\n" ++
	head(rhs).actionCode ++
"    ]]></code>\n" ++
"    <lhs><nonterm id=\"" ++ lhs ++ "\"/></lhs>\n" ++
"    <rhs>\n" ++
	makeProdRHS(head(rhs).ruleRHS) ++
"    </rhs>\n" ++
"    <layout>" ++ (if head(rhs).hasCustomLayout
                   then generateCustomLayoutList(head(rhs).customLayout)
                   else univLayout) ++
    "</layout>\n" ++

(if head(rhs).productionOperatorPrecedence != "" then
"    <operator><term id=\"" ++ makeCopperName(head(rhs).productionOperatorPrecedence) ++ "\" /></operator>"
else "") ++

"  </prod>\n" ++
	makeProdSpecsNonterm(univLayout,lhs,tail(rhs));            
}

function makeProdRHS
String ::= syms::[String]
{
  return if null(syms)
         then ""
         -- WARNING TODO BUG: always says 'nonterm' here, though it may be a terminal!
         -- currently copper doesn't care it just wants the name, but potential bug nonetheless!
         else "    <nonterm id=\"" ++ makeCopperName(head(syms)) ++ "\"/>\n" ++ makeProdRHS(tail(syms));
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
         else "<term id=\"" ++ makeCopperName(head(layouts).terminalName) ++ "\"/>" ++
              generateLayoutList(tail(layouts));
}

function generateCustomLayoutList
String ::= layouts::[String]{
  return if null(layouts)
         then ""
         else "<term id=\"" ++ makeCopperName(head(layouts)) ++ "\"/>" ++
              generateCustomLayoutList(tail(layouts));
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
