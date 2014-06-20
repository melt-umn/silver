grammar silver:definition:concrete_syntax:ast;

-- For looking syntax elements up by name.
synthesized attribute cstDcls :: [Pair<String Decorated SyntaxDcl>];
autocopy attribute cstEnv :: EnvTree<Decorated SyntaxDcl>;
synthesized attribute cstErrors :: [String] with ++;

-- Transformation that moves productions underneath their respective nonterminals.
synthesized attribute cstProds :: [Pair<String SyntaxDcl>];
autocopy attribute cstNTProds :: EnvTree<SyntaxDcl>;
synthesized attribute cstNormalize :: [SyntaxDcl];

synthesized attribute allIgnoreTerminals :: [Decorated SyntaxDcl];
autocopy attribute univLayout :: String;
synthesized attribute classDomContribs :: String;
synthesized attribute classSubContribs :: String;
autocopy attribute containingGrammar :: String;

autocopy attribute prefixesForTerminals :: EnvTree<String>;

synthesized attribute unparses :: [String];


{--
 - An abstract syntax tree for representing concrete syntax.
 -}
nonterminal Syntax with cstDcls, cstEnv, cstErrors, cstProds, cstNTProds, cstNormalize, allIgnoreTerminals, univLayout, xmlCopper, unparses, containingGrammar, prefixesForTerminals;

abstract production nilSyntax
top::Syntax ::=
{
  top.cstDcls = [];
  top.cstErrors := [];
  top.cstProds = [];
  top.cstNormalize = [];
  top.allIgnoreTerminals = [];
  top.xmlCopper = "";
  top.unparses = [];
}
abstract production consSyntax
top::Syntax ::= s1::SyntaxDcl s2::Syntax
{
  top.cstDcls = s1.cstDcls ++ s2.cstDcls;
  top.cstErrors := s1.cstErrors ++ s2.cstErrors;
  top.cstProds = s1.cstProds ++ s2.cstProds;
  top.cstNormalize = s1.cstNormalize ++ s2.cstNormalize;
  top.allIgnoreTerminals = s1.allIgnoreTerminals ++ s2.allIgnoreTerminals;
  top.xmlCopper = s1.xmlCopper ++ s2.xmlCopper;
  top.unparses = s1.unparses ++ s2.unparses;
}

{--
 - An individual declaration of a concrete syntax element.
 -}
nonterminal SyntaxDcl with cstDcls, cstEnv, cstErrors, cstProds, cstNTProds, cstNormalize, sortKey, allIgnoreTerminals, univLayout, xmlCopper, classDomContribs, classSubContribs, unparses, containingGrammar, prefixesForTerminals;

synthesized attribute sortKey :: String;

aspect default production
top::SyntaxDcl ::=
{
  top.cstProds = [];
  top.allIgnoreTerminals = [];
  top.classDomContribs = error("Internal compiler error: should only ever be demanded of lexer classes");
  top.classSubContribs = error("Internal compiler error: should only ever be demanded of lexer classes");
}


{--
 - A nonterminal. Using TypeExp instead of String, because we'll be doing parameterization later.
 - subdcls is empty to start. A transformed version of the tree will move all 
 - productions for this nonterminal under subdcls.
 -}
abstract production syntaxNonterminal
top::SyntaxDcl ::= t::TypeExp subdcls::Syntax --modifiers::SyntaxNonterminalModifiers
{
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
  top.unparses = ["nt(" ++ unparseTyVars(t.freeVariables,t.boundVariables) ++ ", " ++ t.unparse ++ ")"] ++ subdcls.unparses;
}

{--
 - A terminal, and regular expression.
 -}
abstract production syntaxTerminal
top::SyntaxDcl ::= n::String regex::Regex_R modifiers::SyntaxTerminalModifiers
{
  top.sortKey = "CCC" ++ n;
  top.cstDcls = [pair(n, top)];
  -- TODO get errors from modifiers
  top.cstErrors := if length(searchEnvTree(n, top.cstEnv)) == 1 then []
                   else ["Name conflict with terminal " ++ n];

  top.cstNormalize = [top];
  top.allIgnoreTerminals = if modifiers.ignored then [top] else [];

  local pfx :: [String] = searchEnvTree(n, top.prefixesForTerminals);
  
  production lexeme_source :: String =
    if null(pfx) then "lexeme"
    else "lexeme.substring(" ++ toString(length(head(pfx))) ++ ")";
  production regex_to_use :: Regex_R =
    if null(pfx) then regex
    else concatenateRegex(literalRegex(head(pfx)), regex);

  top.xmlCopper =
    "  <Terminal id=\"" ++ makeCopperName(n) ++ "\">\n" ++
    "    <PP>" ++ n ++ "</PP>\n" ++
    "    <Regex>" ++ regex_to_use.xmlCopper ++ "</Regex>\n" ++ 
    (if modifiers.opPrecedence.isJust || modifiers.opAssociation.isJust then
    "    <Operator>\n" ++
    "      <Class>main</Class>\n" ++
    "      <Precedence>" ++ toString(fromMaybe(0, modifiers.opPrecedence)) ++ "</Precedence>\n" ++
    "      " ++ convertAssocNXML(modifiers.opAssociation) ++ "\n" ++ -- TODO
    "    </Operator>\n"
    else "") ++
    "    <Type>common.TerminalRecord</Type>\n" ++ 
    "    <Code><![CDATA[\n" ++ 
    "RESULT = new common.TerminalRecord(" ++ lexeme_source ++ ",virtualLocation,(int)getStartRealLocation().getPos(),(int)getEndRealLocation().getPos());\n" ++
      modifiers.acode ++
    "]]></Code>\n" ++ 
    "    <InClasses>" ++ modifiers.lexerclassesXML ++ "</InClasses>\n" ++ 
    -- TODO: prefix?
    "    <Submits>" ++ modifiers.submitsXML ++ "</Submits>\n" ++ 
    "    <Dominates>" ++ modifiers.dominatesXML ++ "</Dominates>\n" ++
    "  </Terminal>\n";

  top.unparses = ["term('" ++ n ++ "', /" ++ regex.regString ++ "/, " ++ unparseNonStrings(modifiers.unparses) ++ ")"];
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
  top.sortKey = "FFF" ++ ns.fullName;
  top.cstDcls = [pair(ns.fullName, top)];
  
  production lhsRef :: [Decorated SyntaxDcl] =
    searchEnvTree(ns.outputElement.typerep.typeName, top.cstEnv);
  production rhsRefs :: [[Decorated SyntaxDcl]] =
    lookupStrings(map((.typeName), map((.typerep), ns.inputElements)), top.cstEnv);

  -- TODO modifiers errors
  top.cstErrors := if length(searchEnvTree(ns.fullName, top.cstEnv)) == 1 then []
                   else ["Name conflict with production " ++ ns.fullName];
  top.cstErrors <- if length(lhsRef) == 1 then
                   case head(lhsRef) of 
                   | syntaxNonterminal(_,_) -> []
                   | _ -> ["LHS of production " ++ ns.fullName ++ " is not a nonterminal"] end
                   else ["Lookup error with LHS nonterminal " ++ ns.outputElement.typerep.typeName];
                   
  top.cstErrors <- checkRHS(ns.fullName, map((.typerep), ns.inputElements), rhsRefs);

  top.cstProds = [pair(ns.outputElement.typerep.typeName, top)];
  top.cstNormalize = [];
  
  top.xmlCopper =
    "  <Production id=\"" ++ makeCopperName(ns.fullName) ++ "\">\n" ++
    (if modifiers.productionPrecedence.isJust then
    "    <Class>main</Class>\n" ++
    "    <Precedence>" ++ toString(modifiers.productionPrecedence.fromJust) ++ "</Precedence>\n"
    else "") ++
    "    <Code><![CDATA[\n" ++ 
    "RESULT = new " ++ makeClassName(ns.fullName) ++ "(" ++ fetchChildren(0, ns.inputElements) ++ insertLocationAnnotation(ns) ++ ");\n" ++
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

  ns.boundVariables = ns.typerep.freeVariables;
  top.unparses = ["prod(" ++ unparseTyVars(ns.boundVariables, ns.boundVariables) ++ ", " ++ ns.unparse ++ ", " ++ unparseNonStrings(modifiers.unparses) ++ ")"];
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
  else pfx ++ "common.TerminalRecord.createSpan(_children, virtualLocation, (int)_pos.getPos())";
}


function lookupStrings
[[Decorated SyntaxDcl]] ::= t::[String] e::EnvTree<Decorated SyntaxDcl>
{
  return map(searchEnvTree(_, e), t);
}
function checkRHS
[String] ::= pn::String rhs::[TypeExp] refs::[[Decorated SyntaxDcl]]
{
  return if null(rhs) then []
         else (if length(head(refs)) == 1 then 
                case head(head(refs)) of
                | syntaxNonterminal(_,_) -> []
                | syntaxTerminal(_,_,_) -> []
                | _ -> ["parameter " ++ head(rhs).typeName ++ " of production " ++ pn ++ " is not syntax."]
                end
              else ["Lookup error with parameter " ++ head(rhs).typeName ++ " of production " ++ pn])
             ++ checkRHS(pn, tail(rhs), tail(refs));
}

{--
 - A lexer class. Copper doesn't take these, so we'll have to translate away
 - the domlist/sublist that appear here.
 -}
abstract production syntaxLexerClass
top::SyntaxDcl ::= n::String modifiers::SyntaxLexerClassModifiers
{
  top.sortKey = "AAA" ++ n;
  top.cstDcls = [pair(n, top)];
  top.cstErrors := modifiers.cstErrors ++ 
    if length(searchEnvTree(n, top.cstEnv)) == 1 then []
    else ["Name conflict with lexer class " ++ n];

  -- TODO: these attributes are on all SyntaxDcls, but only have meaning for this production
  -- that's UUUUGLY.
  top.classDomContribs = modifiers.dominatesXML;
  top.classSubContribs = modifiers.submitsXML;

  top.cstNormalize = [top];
  
  top.xmlCopper = 
    "  <TerminalClass id=\"" ++ makeCopperName(n) ++ "\" />\n";

  top.unparses = ["lclass('" ++ n ++ "', " ++ unparseNonStrings(modifiers.unparses) ++ ")"];
}

{--
 - A parser attribute. The acode initializes it.
 -}
abstract production syntaxParserAttribute
top::SyntaxDcl ::= n::String ty::TypeExp acode::String
{
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
    "]]></Code>\n" ++
    "  </ParserAttribute>\n";

  -- TODO: technically, there should be no free variables in ty.
  ty.boundVariables = [];
  top.unparses = ["pattr('" ++ n ++ "', " ++ ty.unparse ++ ",\"" ++ escapeString(acode) ++ "\")"];
}

{--
 - A disambiguation group.
 - The acode distinguished between the listed terminals.
 -}
abstract production syntaxDisambiguationGroup
top::SyntaxDcl ::= n::String terms::[String] acode::String
{
  top.sortKey = "DDD" ++ n;
  top.cstDcls = [];
  top.cstErrors := [];
  local attribute trefs :: [[Decorated SyntaxDcl]];
  trefs = lookupStrings(terms, top.cstEnv);
  -- TODO: check terminal

  top.cstNormalize = [top];

  top.xmlCopper =
    "  <DisambiguationFunction id=\"" ++ makeCopperName(n) ++ "\">\n" ++
    "    <Members>" ++ implode("", map(xmlCopperRef, map(head, trefs))) ++ "</Members>\n" ++
    "    <Code><![CDATA[\n" ++
    acode ++  
    "]]></Code>\n" ++
    "  </DisambiguationFunction>\n";

  top.unparses = ["disambig('" ++ n ++ "', " ++ unparseStrings(terms) ++ ", \"" ++ escapeString(acode) ++ "\")"];
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
--}
}

function xmlCopperRef
String ::= d::Decorated SyntaxDcl
{
  return case d of
  | syntaxLexerClass(n, _) -> "<TerminalClassRef id=\"" ++ makeCopperName(n) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />"
  | syntaxTerminal(n, _, _) -> "<TerminalRef id=\"" ++ makeCopperName(n) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />"
  | syntaxNonterminal(n, _) -> "<NonterminalRef id=\"" ++ makeCopperName(n.typeName) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />"
  | syntaxProduction(ns, _) -> "<ProductionRef id=\"" ++ makeCopperName(ns.fullName) ++ "\" grammar=\"" ++ d.containingGrammar ++ "\" />"
  end;
}

