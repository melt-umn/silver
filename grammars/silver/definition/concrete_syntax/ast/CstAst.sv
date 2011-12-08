grammar silver:definition:concrete_syntax:ast;

imports silver:definition:regex;
imports silver:definition:type;
imports silver:util:treemap;
imports silver:definition:env only typeName, unparse, unparseStrings, unparseNonStrings, quoteString, escapeString, unparseTyVars, unparseTypes;

imports silver:translation:java:core only makeIdName, makeClassName;
imports silver:translation:java:type only transType;

{--
 - Encapsulates transformations and analysis of Syntax
 -}
nonterminal SyntaxRoot with cstErrors, cstNormal, xmlCopper, {-TODO:debugging-}unparse;

synthesized attribute cstNormal :: SyntaxRoot; -- TODO basically just a debugging thing
synthesized attribute xmlCopper :: String;


abstract production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax
{
  s.cstEnv = treeConvert(s.cstDcls, treeNew(compareString));
  s.cstNTProds = treeConvert(s.cstProds, treeNew(compareString));
  
  -- Move productions under their nonterminal, and sort the declarations
  local attribute s2 :: Syntax;
  s2 = foldr_p(consSyntax, nilSyntax(), sortBy(syntaxDclLte, s.cstNormalize));
  s2.cstEnv = treeConvert(s.cstDcls, treeNew(compareString));
  
  -- This should be on s1, because the s2 transform assumes everything is well formed.
  -- In particular, it drops productions it can't find an NT for.
  top.cstErrors := s.cstErrors;
  top.cstNormal = cstRoot(parsername, startnt, s2);

  local attribute univLayout :: String;
  univLayout = implode("", map(xmlCopperRef, s2.allIgnoreTerminals));

  s2.univLayout = univLayout;
  top.xmlCopper =
    "<?xml version=\"1.0\"?>\n\n" ++

    "<copperspec id=\"" ++ parsername ++ "\" type=\"LALR1\" version=\"1.1\">\n" ++
    "  <preamble>\n" ++
    "     <code><![CDATA[\n" ++
    "import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;\n" ++
    "     ]]></code>\n" ++
    "  </preamble>\n" ++
    "  <attribute id=\"context\" type=\"common.DecoratedNode\">\n" ++
    "    <code>context = common.TopNode.singleton;</code>\n" ++
    "  </attribute>\n\n" ++ 
    
    "  <start>\n" ++
    "    <nonterm id=\"" ++ makeCopperName(startnt) ++ "\"/>\n" ++
    "    <layout>" ++ univLayout ++ "</layout>\n" ++
    "  </start>\n\n" ++

      s2.xmlCopper ++
    
    "</copperspec>\n";

  top.unparse = implode(",\n ", s.unparses);
}


{-
Assumptions we make about initial Syntax:

1. All type parameter lists are the appropriate length. (Silver type checking)
-}

function makeCopperName
String ::= str::String
{
  return makeIdName(str);
}

