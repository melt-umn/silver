grammar silver:definition:concrete_syntax:ast;

-- For looking syntax elements up by name.
synthesized attribute cstDcls :: [Pair<String Decorated SyntaxDcl>];
autocopy attribute cstEnv :: TreeMap<String Decorated SyntaxDcl>;
synthesized attribute cstErrors :: [String] with ++;

-- Transformation that moves productions underneath their respective nonterminals.
synthesized attribute cstProds :: [Pair<String SyntaxDcl>];
autocopy attribute cstNTProds :: TreeMap<String SyntaxDcl>;
synthesized attribute cstNormalize :: [SyntaxDcl];

synthesized attribute allIgnoreTerminals :: [Decorated SyntaxDcl];
autocopy attribute univLayout :: String;
synthesized attribute classDomContribs :: String;
synthesized attribute classSubContribs :: String;

synthesized attribute unparses :: [String];

{--
 - An abstract syntax tree for representing concrete syntax.
 -}
nonterminal Syntax with cstDcls, cstEnv, cstErrors, cstProds, cstNTProds, cstNormalize, allIgnoreTerminals, univLayout, xmlCopper, unparses;

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
nonterminal SyntaxDcl with cstDcls, cstEnv, cstErrors, cstProds, cstNTProds, cstNormalize, sortKey, allIgnoreTerminals, univLayout, xmlCopper, classDomContribs, classSubContribs, unparses;

synthesized attribute sortKey :: String;

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
  top.cstErrors := if length(treeLookup(t.typeName, top.cstEnv)) == 1 then []
                   else ["Name conflict with nonterminal " ++ t.typeName];
  top.cstErrors <- subdcls.cstErrors;
  top.cstProds = subdcls.cstProds;
  top.cstNormalize = [syntaxNonterminal(t, foldr_p(consSyntax, nilSyntax(), treeLookup(t.typeName, top.cstNTProds)))];
  top.allIgnoreTerminals = [];
  
  top.xmlCopper = 
    "\n  <nonterm id=\"" ++ makeCopperName(t.typeName) ++ "\" />\n" ++
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
  top.cstErrors := if length(treeLookup(n, top.cstEnv)) == 1 then []
                   else ["Name conflict with terminal " ++ n];

  top.cstProds = [];
  top.cstNormalize = [top];
  top.allIgnoreTerminals = if modifiers.ignored then [top] else [];

  top.xmlCopper =
    "  <term id=\"" ++ makeCopperName(n) ++ "\">\n" ++
    "    <code><![CDATA[\n" ++
-- "RESULT = new common.TerminalRecord(lexeme,virtualLocation.getFileName(),virtualLocation.getLine(),virtualLocation.getColumn());\n" ++
    "RESULT = new common.TerminalRecord(lexeme,virtualLocation);\n" ++
      modifiers.acode ++ 
    "    ]]></code>\n" ++
    "    <classes>" ++ modifiers.lexerclasses ++ "</classes>\n" ++
    "    <regex>" ++ regex.regXML ++ "</regex>\n" ++
    "    <dominates>" ++ modifiers.dominatesXML ++ "</dominates>\n" ++
    "    <submits>" ++ modifiers.submitsXML ++ "</submits>\n" ++

    (if modifiers.opPrecedence.isJust || modifiers.opAssociation.isJust then
    "    <operator>\n" ++
    "      <precedence>" ++ toString(fromMaybe(0, modifiers.opPrecedence)) ++ "</precedence>\n" ++
    "      <associativity>" ++ fromMaybe("nonassoc", modifiers.opAssociation) ++ "</associativity>\n" ++
    "      <opclass id=\"main\"/>\n" ++
    "    </operator>\n"
     else "") ++

-- TODO: prefix isn't currently used!
--"    <prefix>
--"      <term id="-"/>
--"    </prefix>

    "  </term>\n";
    
  top.unparses = ["term('" ++ n ++ "', /" ++ regex.regString ++ "/, " ++ unparseNonStrings(modifiers.unparses) ++ ")"];
}

{--
 - A (named) production. Using types for later parameterization.
 -}
abstract production syntaxProduction
top::SyntaxDcl ::= n::String lhs::TypeExp rhs::[TypeExp] modifiers::SyntaxProductionModifiers
{
  top.sortKey = "FFF" ++ n;
  top.cstDcls = [pair(n, top)];
  -- TODO modifiers errors
  top.cstErrors := if length(treeLookup(n, top.cstEnv)) == 1 then []
                   else ["Name conflict with production " ++ n];
  local attribute lhsRef :: [Decorated SyntaxDcl];
  lhsRef = treeLookup(lhs.typeName, top.cstEnv);
  top.cstErrors <- if length(lhsRef) == 1 then
                   case head(lhsRef) of syntaxNonterminal(_,_) -> []
                      | _ -> ["LHS of production " ++ n ++ " is not a nonterminal"] end
                   else ["Lookup error with LHS nonterminal " ++ lhs.typeName];
  local attribute rhsRefs :: [[Decorated SyntaxDcl]];
  rhsRefs = lookupStrings(map(getTypeName, rhs), top.cstEnv);
  top.cstErrors <- checkRHS(n, rhs, rhsRefs);

  top.cstProds = [pair(lhs.typeName,top)];
  top.cstNormalize = [];
  top.allIgnoreTerminals = [];
  
  top.xmlCopper =
    "  <prod id=\"" ++ makeCopperName(n) ++ "\" class=\"main\" precedence=\"" ++ toString(fromMaybe(0, modifiers.productionPrecedence)) ++"\">\n" ++
    "    <code><![CDATA[\n" ++
    "RESULT = new " ++ makeClassName(n) ++ "(_children);\n" ++
      modifiers.acode ++
    "    ]]></code>\n" ++
    "    <lhs><nonterm id=\"" ++ makeCopperName(lhs.typeName) ++ "\"/></lhs>\n" ++
    "    <rhs>" ++ implode("", map(xmlCopperRef, map(head, rhsRefs))) ++ "</rhs>\n" ++
    "    <layout>" ++ fromMaybe(top.univLayout, modifiers.customLayout) ++ "</layout>\n" ++
    
    (if modifiers.productionOperator.isJust then
     "    <operator>" ++ xmlCopperTermRef(modifiers.productionOperator.fromJust) ++ "</operator>\n"
     else "") ++

    "  </prod>\n";

  local attribute tvs :: [TyVar];
  tvs = setUnionTyVarsAll(mapFreeVariables(lhs :: rhs));
  lhs.boundVariables = tvs;
  top.unparses = ["prod('" ++ n ++ "'," ++ unparseTyVars(tvs,tvs) ++ "," ++ lhs.unparse ++ "," ++ unparseTypes(rhs, tvs) ++ "," ++ unparseNonStrings(modifiers.unparses) ++ ")"];
}

function getTypeName
String ::= t::TypeExp
{ return t.typeName; }
function lookupStrings
[[Decorated SyntaxDcl]] ::= t::[String] e::TreeMap<String Decorated SyntaxDcl>
{
  return if null(t) then []
         else treeLookup(head(t), e) :: lookupStrings(tail(t), e);
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
top::SyntaxDcl ::= n::String domlist::[String] sublist::[String]
{
  top.sortKey = "AAA" ++ n;
  top.cstDcls = [pair(n, top)];
  top.cstErrors := if length(treeLookup(n, top.cstEnv)) == 1 then []
                   else ["Name conflict with lexer class " ++ n];
  local attribute drefs :: [[Decorated SyntaxDcl]];
  drefs = lookupStrings(domlist, top.cstEnv);
  local attribute srefs :: [[Decorated SyntaxDcl]];
  srefs = lookupStrings(sublist, top.cstEnv);
  -- TODO: check terminal/lexer class

  -- TODO: this is an ugly way to do this...
  top.classDomContribs = implode("", map(xmlCopperRef, map(head, drefs)));
  top.classSubContribs = implode("", map(xmlCopperRef, map(head, srefs)));

  top.cstProds = [];
  top.cstNormalize = [top];
  top.allIgnoreTerminals = [];
  
  top.xmlCopper = ""; -- Apparently does not exist as a declaration in the copper xml skin
  top.unparses = ["lclass('" ++ n ++ "'," ++ unparseStrings(domlist) ++ "," ++ unparseStrings(sublist) ++ ")"];
}

{--
 - A parser attribute. The acode initializes it.
 -}
abstract production syntaxParserAttribute
top::SyntaxDcl ::= n::String ty::TypeExp acode::String
{
  top.sortKey = "BBB" ++ n;
  top.cstDcls = [pair(n, top)];
  top.cstErrors := if length(treeLookup(n, top.cstEnv)) == 1 then []
                   else ["Name conflict with parser attribute " ++ n];

  top.cstProds = [];
  top.cstNormalize = [top];
  top.allIgnoreTerminals = [];

  top.xmlCopper =
    "  <attribute id=\"" ++ makeCopperName(n) ++"\" type=\"" ++ ty.transType ++ "\">\n" ++
    "    <code><![CDATA[" ++
      acode ++
    "    ]]></code>\n" ++
    "  </attribute>\n";

  -- TODO: technically, there should be no free variables in ty.
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

  top.cstProds = [];
  top.cstNormalize = [top];
  top.allIgnoreTerminals = [];

  top.xmlCopper =
    "  <disambig_func id=\"" ++ makeCopperName(n) ++ "\">\n    " ++
      implode("\n    ", map(xmlCopperTermRef, terms)) ++
    "\n    <code><![CDATA[" ++
      acode ++
    "    ]]></code>\n" ++
    "  </disambig_func>\n";
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

function xmlCopperTermRef
String ::= s::String
{
  return "<term id=\"" ++ makeCopperName(s) ++ "\" />";
}
function xmlCopperClassRef
String ::= s::String
{
  return "<termclass id=\"" ++ makeCopperName(s) ++ "\" />";
}
function xmlCopperNontermRef
String ::= s::String
{
  return "<nonterm id=\"" ++ makeCopperName(s) ++ "\" />";
}
function xmlCopperRef
String ::= d::Decorated SyntaxDcl
{
  return case d of
         | syntaxLexerClass(n, _, _) -> xmlCopperClassRef(n)
         | syntaxTerminal(n, _, _)   -> xmlCopperTermRef(n)
         | syntaxNonterminal(n, _)   -> xmlCopperNontermRef(n.typeName)
         end;
}

-- TODO uggggllly
function getclassDomContribs
String ::= d::Decorated SyntaxDcl
{ return d.classDomContribs; }
function getclassSubContribs
String ::= d::Decorated SyntaxDcl
{ return d.classSubContribs; }

-- TODO: fix
function escapeString
String ::= s::String
{
  return substitute("\"", "\\\"", s);
}

function unescapeString
String ::= s::String
{
  return substitute("\\\"", "\"", s);
}

