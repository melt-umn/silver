grammar silver:definition:concrete_syntax:ast;

imports silver:translation:java:core only makeTerminalName;

-- For looking syntax elements up by name.
synthesized attribute cstDcls :: [Pair<String Decorated SyntaxDcl>];
autocopy attribute cstEnv :: EnvTree<Decorated SyntaxDcl>;
synthesized attribute cstErrors :: [String] with ++;

-- Transformation that moves productions underneath their respective nonterminals.
synthesized attribute cstProds :: [Pair<String SyntaxDcl>];
autocopy attribute cstNTProds :: EnvTree<SyntaxDcl>;
synthesized attribute cstNormalize :: [SyntaxDcl];

-- Compute and allow lookup of all terminals in a lexer class
synthesized attribute classTerminalContribs::[Pair<String String>];
autocopy attribute classTerminals::EnvTree<String>;
synthesized attribute superClassContribs::[Pair<String String>];
autocopy attribute superClasses::EnvTree<String>;
autocopy attribute subClasses::EnvTree<String>;

-- Parser attribute action code aspects
synthesized attribute parserAttributeAspectContribs::[Pair<String String>];
autocopy attribute parserAttributeAspects::EnvTree<String>;

synthesized attribute allIgnoreTerminals :: [Decorated SyntaxDcl];
synthesized attribute allMarkingTerminals :: [Decorated SyntaxDcl];
synthesized attribute disambiguationClasses :: [Decorated SyntaxDcl];
autocopy attribute univLayout :: String;
synthesized attribute classDomContribs :: String;
synthesized attribute classSubContribs :: String;
autocopy attribute containingGrammar :: String;
synthesized attribute lexerClassRefDcls :: String;

autocopy attribute prefixesForTerminals :: EnvTree<String>;


{--
 - An abstract syntax tree for representing concrete syntax.
 -}
nonterminal Syntax with cstDcls, cstEnv, cstErrors, cstProds, cstNTProds, cstNormalize, allIgnoreTerminals, allMarkingTerminals, disambiguationClasses, classTerminalContribs, classTerminals, superClassContribs, superClasses, subClasses, parserAttributeAspectContribs, parserAttributeAspects, univLayout, lexerClassRefDcls, xmlCopper, containingGrammar, prefixesForTerminals;

abstract production nilSyntax
top::Syntax ::=
{
  top.cstDcls = [];
  top.cstErrors := [];
  top.cstProds = [];
  top.cstNormalize = [];
  top.allIgnoreTerminals = [];
  top.allMarkingTerminals = [];
  top.disambiguationClasses = [];
  top.classTerminalContribs = [];
  top.superClassContribs = [];
  top.parserAttributeAspectContribs = [];
  top.lexerClassRefDcls = "";
  top.xmlCopper = "";
}
abstract production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  top.cstDcls = s1.cstDcls ++ s2.cstDcls;
  top.cstErrors := s1.cstErrors ++ s2.cstErrors;
  top.cstProds = s1.cstProds ++ s2.cstProds;
  top.cstNormalize = s1.cstNormalize ++ s2.cstNormalize;
  top.allIgnoreTerminals = s1.allIgnoreTerminals ++ s2.allIgnoreTerminals;
  top.allMarkingTerminals = s1.allMarkingTerminals ++ s2.allMarkingTerminals;
  top.disambiguationClasses = s1.disambiguationClasses ++ s2.disambiguationClasses;
  top.classTerminalContribs = s1.classTerminalContribs ++ s2.classTerminalContribs;
  top.superClassContribs = s1.superClassContribs ++ s2.superClassContribs;
  top.parserAttributeAspectContribs = s1.parserAttributeAspectContribs ++ s2.parserAttributeAspectContribs;
  top.lexerClassRefDcls = s1.lexerClassRefDcls ++ s2.lexerClassRefDcls;
  top.xmlCopper = s1.xmlCopper ++ s2.xmlCopper;
}

{--
 - An individual declaration of a concrete syntax element.
 -}
nonterminal SyntaxDcl with cstDcls, cstEnv, cstErrors, cstProds, cstNTProds, cstNormalize, fullName, sortKey, allIgnoreTerminals, allMarkingTerminals, disambiguationClasses, classTerminalContribs, classTerminals, superClassContribs, superClasses, subClasses, parserAttributeAspectContribs, parserAttributeAspects, univLayout, lexerClassRefDcls, xmlCopper, classDomContribs, classSubContribs, prefixSeperator, containingGrammar, prefixesForTerminals;

synthesized attribute sortKey :: String;

aspect default production
top::SyntaxDcl ::=
{
  top.cstProds = [];
  top.allIgnoreTerminals = [];
  top.allMarkingTerminals = [];
  top.disambiguationClasses = [];
  top.classTerminalContribs = [];
  top.superClassContribs = [];
  top.parserAttributeAspectContribs = [];
  top.classDomContribs = error("Internal compiler error: should only ever be demanded of lexer classes");
  top.classSubContribs = error("Internal compiler error: should only ever be demanded of lexer classes");
  top.lexerClassRefDcls = "";
  top.prefixSeperator = nothing();
}


{--
 - A nonterminal. Using Type instead of String, because we'll be doing parameterization later.
 - subdcls is empty to start. A transformed version of the tree will move all
 - productions for this nonterminal under subdcls.
 -}
abstract production syntaxNonterminal
top::SyntaxDcl ::= t::Type subdcls::Syntax --modifiers::SyntaxNonterminalModifiers
{
  top.fullName = t.typeName;
  top.sortKey = "EEE" ++ t.typeName;
  top.cstDcls = [pair(t.typeName, top)] ++ subdcls.cstDcls;
  top.cstErrors := if length(searchEnvTree(t.typeName, top.cstEnv)) == 1 then []
                   else ["Name conflict with nonterminal " ++ t.typeName];
  top.cstErrors <- subdcls.cstErrors;
  top.cstProds = subdcls.cstProds;
  top.cstNormalize =
    let myProds :: [SyntaxDcl] = searchEnvTree(t.typeName, top.cstNTProds)
    in if null(myProds) then [] -- Eliminate "Useless nonterminals" as these are expected in Silver code (non-syntax)
       else [syntaxNonterminal(t, foldr(consSyntax, nilSyntax(), myProds))]
    end;

  top.xmlCopper =
    "\n  <Nonterminal id=\"" ++ makeCopperName(t.typeName) ++ "\">\n" ++
      "    <PP>" ++ t.typeName ++ "</PP>\n" ++
      "    <Type><![CDATA[" ++ makeNTClassName(t.typeName) ++ "]]></Type>\n" ++
      "  </Nonterminal>\n" ++
    subdcls.xmlCopper;

  t.boundVariables = t.freeVariables;
}

{--
 - A terminal, and regular expression.
 -}
abstract production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex modifiers::SyntaxTerminalModifiers
{
  top.fullName = n;
  top.sortKey = "CCC" ++ n;
  top.cstDcls = [pair(n, top)];
  top.cstErrors := modifiers.cstErrors;
  top.cstErrors <-
    if length(searchEnvTree(n, top.cstEnv)) == 1 then []
    else ["Name conflict with terminal " ++ n];

  modifiers.terminalName = n;

  top.allIgnoreTerminals = if modifiers.ignored then [top] else [];
  top.allMarkingTerminals = if modifiers.marking then [top] else [];
  top.classTerminalContribs = modifiers.classTerminalContribs;

  -- left(terminal name) or right(string prefix)
  production pfx::[String] = searchEnvTree(n, top.prefixesForTerminals);
  top.cstErrors <-
    if length(pfx) <= 1 then []
    else ["Multiple prefixes for terminal " ++ n];
  
  top.prefixSeperator = modifiers.prefixSeperator;
  
  top.cstNormalize =
    case modifiers.prefixSeperatorToApply of
    | just(sep) -> [syntaxTerminal(n, regexConcatenate(regex, regexLiteral(sep)), modifiers)]
    | nothing() -> [top]
    end;

  local prettyName :: String = fromMaybe(fromMaybe(n, asPrettyName(regex)), modifiers.prettyName);

  top.xmlCopper =
    "  <Terminal id=\"" ++ makeCopperName(n) ++ "\">\n" ++
    "    <PP>" ++ prettyName ++ "</PP>\n" ++
    "    <Regex>" ++ regex.xmlCopper ++ "</Regex>\n" ++
    (if modifiers.opPrecedence.isJust || modifiers.opAssociation.isJust then
    "    <Operator>\n" ++
    "      <Precedence>" ++ toString(fromMaybe(0, modifiers.opPrecedence)) ++ "</Precedence>\n" ++
    "      " ++ convertAssocNXML(modifiers.opAssociation) ++ "\n" ++ -- TODO
    "    </Operator>\n"
    else "") ++
    "    <Type>" ++ makeTerminalName(n) ++ "</Type>\n" ++
    "    <Code><![CDATA[\n" ++
    "RESULT = new " ++ makeTerminalName(n) ++ "(lexeme,virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());\n" ++
    "  tokenList.add(RESULT);\n" ++
      modifiers.acode ++
    "]]></Code>\n" ++
    "    <InClasses>" ++ modifiers.lexerclassesXML ++ "</InClasses>\n" ++
    (if null(pfx) then ""
     else "    <Prefix><TerminalRef id=\"" ++ head(pfx) ++ "\"/></Prefix>\n") ++
    "    <Submits>" ++ modifiers.submitsXML ++ "</Submits>\n" ++
    "    <Dominates>" ++ modifiers.dominatesXML ++ "</Dominates>\n" ++
    "  </Terminal>\n";
}

-- New XML Skin START
function convertAssocNXML -- TODO remove, make attribute
String ::= opassoc::Maybe<String>
{
  local attribute assoc::String;
  assoc = fromMaybe("", opassoc);
  return if assoc=="left" then "<LeftAssociative/>"
          else if assoc=="right" then "<RightAssociative/>"
          else "<NonAssociative/>";
}
-- New XML Skin END

{--
 - A (named) production. Using types for later parameterization.
 -}
abstract production syntaxProduction
top::SyntaxDcl ::= ns::NamedSignature  modifiers::SyntaxProductionModifiers
{
  top.fullName = ns.fullName;
  top.sortKey = "FFF" ++ ns.fullName;
  top.cstDcls = [pair(ns.fullName, top)];
  modifiers.productionName = ns.fullName;

  production lhsRef :: [Decorated SyntaxDcl] =
    searchEnvTree(ns.outputElement.typerep.typeName, top.cstEnv);
  production rhsRefs :: [[Decorated SyntaxDcl]] =
    lookupStrings(map((.typeName), map((.typerep), ns.inputElements)), top.cstEnv);

  top.cstErrors := modifiers.cstErrors;
  top.cstErrors <- if length(searchEnvTree(ns.fullName, top.cstEnv)) == 1 then []
                   else ["Name conflict with production " ++ ns.fullName];

  top.cstErrors <- if length(lhsRef) == 1 then
                   case head(lhsRef) of
                   | syntaxNonterminal(_,_) -> []
                   | _ -> ["LHS of production " ++ ns.fullName ++ " is not a nonterminal"] end
                   else ["Nonterminal " ++ ns.outputElement.typerep.typeName ++ " was referenced but " ++
                         "this grammar was not included in this parser. (Referenced from LHS of production " ++ ns.fullName ++ ")"];

  top.cstErrors <- checkRHS(ns.fullName, map((.typerep), ns.inputElements), rhsRefs);

  top.cstProds = [pair(ns.outputElement.typerep.typeName, top)];
  top.cstNormalize = [];

  top.xmlCopper =
    "  <Production id=\"" ++ makeCopperName(ns.fullName) ++ "\">\n" ++
    (if modifiers.productionPrecedence.isJust then
--    "    <Class><OperatorClassRef id=\"main\"/></Class>\n" ++
    "    <Precedence>" ++ toString(modifiers.productionPrecedence.fromJust) ++ "</Precedence>\n"
    else "") ++
    "    <Code><![CDATA[\n" ++
    -- Annoying workaround for if a lambda in an action block needs to capture RESULT when accessing a child.
    -- Java complains when we capture something that is non-final.
    "final " ++ makeClassName(ns.fullName) ++ " RESULTfinal = new " ++ makeClassName(ns.fullName) ++ "(" ++ fetchChildren(0, ns.inputElements) ++ insertLocationAnnotation(ns) ++ ");\n" ++
    "RESULT = RESULTfinal;\n" ++
      modifiers.acode ++
    "]]></Code>\n" ++
    "    <LHS>" ++ xmlCopperRef(head(lhsRef)) ++ "</LHS>\n" ++
    "    <RHS>" ++ implode("", map(xmlCopperRef, map(head, rhsRefs))) ++ "</RHS>\n" ++
    (if modifiers.customLayout.isJust then
    "    <Layout>" ++ modifiers.customLayout.fromJust ++ "</Layout>\n"
    else "") ++
    (if modifiers.productionOperator.isJust then
    "    <Operator>" ++ modifiers.productionOperator.fromJust ++ "</Operator>\n"
    else "") ++
    "  </Production>\n";
}

function fetchChildren
String ::= i::Integer  ns::[NamedSignatureElement]
{
  return if null(ns) then ""
  else if null(tail(ns)) then "_children[" ++ toString(i) ++ "]"
  else "_children[" ++ toString(i) ++ "], " ++ fetchChildren(i + 1, tail(ns));
}

function insertLocationAnnotation
String ::= ns::Decorated NamedSignature
{
  local pfx :: String = if null(ns.inputElements) then "" else ", ";

  return if null(ns.namedInputElements) then ""
  else if length(ns.namedInputElements) > 1 then pfx ++ "multiple_annotation_problem" -- TODO
  else if head(ns.namedInputElements).elementName != "core:location" then pfx ++ "unknown_annotation_type_problem"
  else pfx ++ "common.Terminal.createSpan(_children, virtualLocation, (int)_pos.getPos())";
}


function lookupStrings
[[a]] ::= t::[String] e::EnvTree<a>
{
  return map(searchEnvTree(_, e), t);
}
function checkRHS
[String] ::= pn::String rhs::[Type] refs::[[Decorated SyntaxDcl]]
{
  return if null(rhs) then []
         else (if length(head(refs)) == 1 then
                case head(head(refs)) of
                | syntaxNonterminal(_,_) -> []
                | syntaxTerminal(_,_,_) -> []
                | _ -> ["parameter " ++ head(rhs).typeName ++ " of production " ++ pn ++ " is not syntax."]
                end
              else ["Terminal " ++ head(rhs).typeName ++ " was referenced but " ++
                    "this grammar was not included in this parser. (Referenced from RHS of " ++ pn ++ ")"])
              ++ checkRHS(pn, tail(rhs), tail(refs));
}

{--
 - A lexer class. Copper doesn't take these, so we'll have to translate away
 - the domlist/sublist that appear here.
 -}
abstract production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  top.fullName = n;
  top.sortKey = "AAA" ++ n;
  top.cstDcls = [pair(n, top)];
  top.cstErrors := modifiers.cstErrors ++
    if length(searchEnvTree(n, top.cstEnv)) == 1 then []
    else ["Name conflict with lexer class " ++ n];
  modifiers.className = n;

  -- TODO: these attributes are on all SyntaxDcls, but only have meaning for this production
  -- that's UUUUGLY.
  top.classDomContribs = modifiers.dominatesXML;
  top.classSubContribs = modifiers.submitsXML;

  top.cstNormalize = [top];
  top.superClassContribs = modifiers.superClassContribs;
  top.disambiguationClasses = modifiers.disambiguationClasses;

  production terms :: [String] = searchEnvTree(n, top.classTerminals);
  local termsInit::String =
    foldr(
      \ term::String rest::String -> s"new common.ConsCell(Terminals.${makeCopperName(term)}.num(), ${rest})",
      "common.ConsCell.nil",
      terms);
  top.lexerClassRefDcls =
    s"    protected common.ConsCell ${makeCopperName(n)} = ${termsInit};\n";
  
  top.xmlCopper =
    "  <TerminalClass id=\"" ++ makeCopperName(n) ++ "\" />\n";
  
  top.prefixSeperator = modifiers.prefixSeperator;
}

{--
 - A parser attribute. The acode initializes it.
 -}
abstract production syntaxParserAttribute
top::SyntaxDcl ::= n::String ty::Type acode::String
{
  top.fullName = n;
  top.sortKey = "BBB" ++ n;
  top.cstDcls = [pair(n, top)];
  top.cstErrors := if length(searchEnvTree(n, top.cstEnv)) == 1 then []
                   else ["Name conflict with parser attribute " ++ n];

  top.cstNormalize = [top];

  top.xmlCopper =
    "  <ParserAttribute id=\"" ++ makeCopperName(n) ++ "\">\n" ++
    "    <Type><![CDATA[" ++ ty.transType ++ "]]></Type>\n" ++
    "    <Code><![CDATA[\n" ++
      acode ++
      implode("\n", searchEnvTree(n, top.parserAttributeAspects)) ++
    "]]></Code>\n" ++
    "  </ParserAttribute>\n";

  -- TODO: technically, there should be no free variables in ty.
  ty.boundVariables = [];
}

{--
 - Additonal action code that should be added to the initialization of
 - a parser attribute. 
 -}
abstract production syntaxParserAttributeAspect
top::SyntaxDcl ::= n::String acode::String
{
  top.fullName = n;
  top.sortKey = "BBB" ++ n;
  top.cstDcls = [];
  top.cstErrors :=
    if !null(searchEnvTree(n, top.cstEnv)) then []
    else ["Parser attribute " ++ n ++ " was referenced but this grammar was not included in this parser."];

  top.cstNormalize = [top];

  top.parserAttributeAspectContribs = [pair(n, acode)];
  top.xmlCopper = "";
}

{--
 - A disambiguation group.
 - The acode distinguished between the listed terminals.
 -}
abstract production syntaxDisambiguationGroup
top::SyntaxDcl ::= n::String terms::[String] applicableToSubsets::Boolean acode::String
{
  top.fullName = n;
  top.sortKey = "DDD" ++ n;
  top.cstDcls = [];

  local trefs::[[Decorated SyntaxDcl]] = lookupStrings(terms, top.cstEnv);

  -- this 'n' here appears to actually hold the line number of the
  -- disambiguation, and the grammar. But we arent supposed to know this?
  top.cstErrors := flatMap(\p ::Pair<String [Decorated SyntaxDcl]> ->
      if !null(p.snd) then []
      else ["Terminal " ++ p.fst ++ " was referenced but " ++
            "this grammar was not included in this parser. (Referenced from disambiguation group " ++ n ++ ")"],
    zipWith(pair, terms, trefs));

  top.cstNormalize = [top];

  top.xmlCopper =
    "  <DisambiguationFunction id=\"" ++ makeCopperName(n) ++ "\" applicableToSubsets=\"" ++ toString(applicableToSubsets) ++ "\">\n" ++
    "    <Members>" ++ implode("", map(xmlCopperRef, map(head, trefs))) ++ "</Members>\n" ++
    "    <Code><![CDATA[\n" ++
    acode ++
    "]]></Code>\n" ++
    "  </DisambiguationFunction>\n";
}

function syntaxDclLte
Boolean ::= l::SyntaxDcl r::SyntaxDcl
{
  return l.sortKey <= r.sortKey;
{-- Sort key PREFIXES are as follows:
    | syntaxLexerClass(_,_,_)           ->  AAA
    | syntaxParserAttribute(_,_,_)      ->  BBB
    | syntaxTerminal(_,_,_)             ->  CCC
    | syntaxDisambiguationGroup(_,_,_)  ->  DDD
    | syntaxNonterminal(_,_)            ->  EEE
    | syntaxProduction(_,_,_,_)         ->  FFF
-}
}

function xmlCopperRef
String ::= d::Decorated SyntaxDcl
{
  return case d of
  | syntaxLexerClass(n, _) -> "<TerminalClassRef id=\"" ++ makeCopperName(n) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />"
  | syntaxTerminal(n, _, _) -> "<TerminalRef id=\"" ++ makeCopperName(n) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />"
  | syntaxNonterminal(n, _) -> "<NonterminalRef id=\"" ++ makeCopperName(n.typeName) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />"
  | syntaxProduction(ns, _) -> "<ProductionRef id=\"" ++ makeCopperName(ns.fullName) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />"
  | syntaxDisambiguationGroup(n, _, _, _) -> "<DisambiguationFunctionRef id=\"" ++ makeCopperName(n) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />"
  end;
}

