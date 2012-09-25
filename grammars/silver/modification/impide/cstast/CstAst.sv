grammar silver:modification:impide:cstast;

imports silver:definition:concrete_syntax:ast;
imports silver:definition:regex;
imports silver:definition:type;
imports silver:definition:env only typeName, unparse, unparseStrings, unparseNonStrings, quoteString, escapeString, unparseTyVars, unparseTypes, EnvTree, searchEnvTree, directBuildTree;

imports silver:translation:java:core only makeIdName, makeClassName, makeNTClassName;
imports silver:translation:java:type only transType;
imports silver:modification:impide;

-- The attribute into which the copper parser is built
synthesized attribute nxmlCopper :: String;
attribute nxmlCopper occurs on SyntaxRoot, Syntax, SyntaxDcl;

aspect default production
top::SyntaxRoot ::=
{
  top.nxmlCopper = error("nxmlCopper not defined");
}

aspect production cstRoot
top::SyntaxRoot ::= parsername::String  startnt::String  s::Syntax
{

  -- 1) Layouts (ignored tokens, such as comments)
  local attribute ignoredTerminals :: [Pair<Decorated SyntaxDcl Pair<String String>>];
  ignoredTerminals = map(createPairForSyntaxDcl, s2.allIgnoreTerminals);--s2 is defined in silver:definition:concrete_syntax:ast:cstRoot.sv
  -- for <StartLayout>
  local attribute newStartLayout :: String;
  newStartLayout = implode("\n\t\t\t", map(nxmlCopperElementRef, ignoredTerminals));  
  -- for <Grammar>-wide <Layout>
  local attribute newUnivLayout :: String;
  newUnivLayout = implode("			\n", map(nxmlCopperElementRef, ignoredTerminals));

  -- 2) The copper parser
  top.nxmlCopper =
"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n" ++

"<CopperSpec xmlns=\"http://melt.cs.umn.edu/copper/xmlns\">\n" ++
"	<Parser id=\"SingleParser\" isUnitary=\"false\">\n" ++

"		<PP>SingleParser</PP>\n" ++

"		<Grammars>\n" ++
"			<GrammarRef id=\"" ++ getGrammarId() ++ "\"/>\n" ++
"		</Grammars>\n" ++

"		<StartSymbol>\n" ++
"			<NonterminalRef grammar=\"" ++ getGrammarId() ++ "\" id=\"" ++ makeCopperName(startnt) ++ "\"/>\n" ++
"		</StartSymbol>\n" ++

"		<StartLayout>\n" ++
"			" ++ newStartLayout ++ "\n" ++
"		</StartLayout>\n" ++

"		<Package>parsers</Package>\n" ++

"		<ClassName>SingleParser</ClassName>\n" ++

"		<ClassAuxiliaryCode>\n" ++
"			<Code><![CDATA[\n" ++ 

		getAuxCode(s.fontList, s.termFontPairList) ++ 

"		]]></Code>\n" ++
"		</ClassAuxiliaryCode>\n" ++

"		<DefaultProductionCode>\n" ++
"			<Code><![CDATA[\n" ++
"/* Define default production action here. */\n" ++
"			]]></Code>\n" ++
"		</DefaultProductionCode>\n" ++

"		<DefaultTerminalCode>\n" ++
"			<Code><![CDATA[\n " ++
"/* Define default terminal action here. */\n" ++
"			]]></Code>\n" ++
"		</DefaultTerminalCode>\n" ++

"		<ParserInitCode>\n" ++
"			<Code><![CDATA[\n" ++
"reset();\n" ++
"			]]></Code>\n" ++
"		</ParserInitCode>\n" ++

"		<PostParseCode>\n" ++
"			<Code><![CDATA[\n" ++
"/* Insert PostParseCode here. */\n" ++
"			]]></Code>\n" ++
"		</PostParseCode>\n" ++

"		<Preamble>\n" ++
"<Code><![CDATA[\n" ++
"import java.util.ArrayList;\n" ++
"import java.util.Iterator;\n" ++
"import java.util.List;\n" ++
"import java.util.HashMap;\n" ++
"import java.util.Map;\n" ++
"import edu.umn.cs.melt.copper.runtime.engines.single.scanner.SingleDFAMatchData;\n" ++
"import edu.umn.cs.melt.ide.copper.*;\n" ++
"import edu.umn.cs.melt.ide.copper.coloring.*;\n" ++
"import org.eclipse.jface.text.TextAttribute;\n" ++
"import org.eclipse.swt.widgets.Display;\n" ++
"]]></Code>\n" ++
"		</Preamble>\n" ++

"		<SemanticActionAuxiliaryCode>\n" ++
"			<Code><![CDATA[\n" ++ 
"private int getCurrentProduction(){\n" ++
" 	return _prod;\n" ++
"}\n" ++
"			]]></Code>\n" ++
"		</SemanticActionAuxiliaryCode>\n" ++

"	</Parser>\n\n" ++

"	<Grammar id=\"" ++ getGrammarId() ++ "\">\n\n" ++
	
"		<PP>" ++ getGrammarId() ++ "</PP>\n\n" ++
	
"		<Layout>\n" ++
"			" ++ newUnivLayout ++ "\n" ++
"		</Layout>\n\n" ++
			
"  		<Declarations>\n" ++

			s2.nxmlCopper ++ 

"			<ParserAttribute id=\"context\">\n" ++
"				<Type><![CDATA[common.DecoratedNode]]></Type>\n" ++
"				<Code><![CDATA[ " ++ getGrammarId() ++ "$context = common.TopNode.singleton; ]]></Code>\n" ++
"			</ParserAttribute>\n" ++

"		</Declarations>\n" ++
"	</Grammar>\n" ++
"</CopperSpec>";
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

function getGrammarId
String ::=
{
  return "host";--used to be "singlegrammar"
}

function createPairForSyntaxDcl
Pair<Decorated SyntaxDcl Pair<String String>> ::= term::Decorated SyntaxDcl
{
  return pair(term, pair(getGrammarId(), ""));
}

function createNamedPairForSyntaxDcl
Pair<Decorated SyntaxDcl Pair<String String>> ::= term::Decorated SyntaxDcl index::Integer
{
  return pair(term, pair(getGrammarId(), "r" ++ toString(index)));
}

function createNamedSyntaxDclsForRHSXml
[Pair<Decorated SyntaxDcl Pair<String String>>] ::= syns::[Decorated SyntaxDcl] index::Integer
{
  return if (null(syns)) then [] else (createNamedPairForSyntaxDcl(head(syns), index) :: (createNamedSyntaxDclsForRHSXml(tail(syns), index+1)));
}

function getAccesserCodeForTermNonterm
String ::= syn::Pair<Decorated SyntaxDcl Pair<String String>>
{
    return case syn.fst of
         | syntaxTerminal(n, _, _)   -> syn.snd.snd
         | syntaxNonterminal(n, _)   -> syn.snd.snd ++ ".getLangSpecNode()"
         end;
}

function getAuxCode
String ::= fontList::[Pair<String Font>] termFontPairList::[Pair<String String>] 
{
return 
"\t//IDE Extension START\n" ++
"    \n" ++
"\tprotected AdaptiveEnhancedParseTreeInnerNode createPTNode(final Object obj){\n" ++
"\t    int production = semantics.getCurrentProduction();\n" ++
"\t\tnodeBuilder.setLangSpecNode(obj);\n" ++
"\t\tint _productionLength = actionIndex(getSymbolNumbers()[production]);\n" ++
"\t\tint _productionLHS = actionIndex(getProductionLHSs()[production - getGRAMMAR_SYMBOL_COUNT()]);		\n" ++
"\t\tenParseTree.reduce(nodeBuilder, production, _productionLength, _productionLHS);\n" ++
"\t\treturn (AdaptiveEnhancedParseTreeInnerNode)enParseTree.getLowestNode();\n" ++
"\t}\n" ++
"\t\n" ++
"\tprotected void shiftPTNode(final Object obj, SingleDFAMatchData scanResult){\n" ++
"\t\tnodeBuilder.setLangSpecNode(obj);\n" ++
"\t\tenParseTree.shift(nodeBuilder, scanResult, _startOffset, _endOffset);\n" ++
"\t}\n" ++
"\t\n" ++
"\tprotected void shiftPTNode(SingleDFAMatchData scanResult){\n" ++
"\t\tshiftPTNode(null, scanResult);\n" ++
"\t}\n" ++
"\t\n" ++
"\tclass AdaptiveParseNodeBuilder implements ParseNodeBuilder<IEnhancedParseTreeNode> {	\n" ++
"\t\tprivate Object langSpecNode;\n" ++
"\t\t\n" ++
"\t\tAdaptiveParseNodeBuilder(){\n" ++
"\n" ++
"\t\t}\n" ++
"\t\t\n" ++
"\t\tvoid setLangSpecNode(Object langSpecNode){\n" ++
"\t\t\tthis.langSpecNode = langSpecNode;\n" ++
"\t\t}\n" ++
"\t\t\n" ++
"\t\t@Override\n" ++
"\t\tpublic AdaptiveEnhancedParseTreeInnerNode<Object> buildNode(\n" ++
"\t\t\tIEnhancedParseTreeNode[] children, int production, int term) {\n" ++
"\t\t\t\n" ++
"\t\t\tint start = 0;\n" ++
"\t\t\tint end = 0;\n" ++
"\t\t\t\n" ++
"\t\t\tif(children != null && children.length>0){\n" ++
"\t\t\t\tstart = children[0].getStart();\n" ++
"\t\t\t\tend = children[children.length-1].getEnd();\n" ++
"\t\t\t} else if (tokenList != null) {\n" ++
"\t\t\t\t//For empty production (RHS = 0), use the last token scanned as the location\n" ++
"\t\t\t\tint sz = tokenList.size();\n" ++
"\t\t\t\tif(sz>0){\n" ++
"\t\t\t\t\tstart = tokenList.get(sz-1).getEndOffset() + 1;\n" ++
"\t\t\t\t\tend = start;\n" ++
"\t\t\t\t}\n" ++
"\t\t\t}\n" ++
"\t\t\t\n" ++
"\t\t\treturn new AdaptiveEnhancedParseTreeInnerNode<Object>(\n" ++
"\t\t\t\t\tchildren, production, term, start, end, langSpecNode);\n" ++
"\t\t}\n" ++
"\n" ++
"\t\t@Override\n" ++
"\t\tpublic AdaptiveEnhancedParseTreeLeafNode<Object> buildNode(\n" ++
"\t\t\tSingleDFAMatchData scanResult, int start, int end) {\n" ++
"\t\t\treturn new AdaptiveEnhancedParseTreeLeafNode<Object>(\n" ++
"\t\t\t\tscanResult.lexeme, scanResult.firstTerm, start, end, langSpecNode);\n" ++
"\t\t}\n" ++
"\t}\n" ++
"\t\n" ++
"\tprivate AdaptiveParseNodeBuilder nodeBuilder = new AdaptiveParseNodeBuilder();\n" ++
"\t\n" ++
"\t//The following variables are used for building token list\n" ++
"\tprivate int _totalOffset = 0;\n" ++
"\tprivate int _startOffset = 0;\n" ++
"\tprivate int _endOffset = 0;\n" ++
"\tprotected List<IToken> tokenList = null;\n" ++
"\tprivate final Object LOCK = new Object();\n" ++
"\n" ++
"    private void addToken(SingleDFAMatchData _terminal){\n" ++
"    	IToken t = createToken(_terminal);\n" ++
"\t\tif(t != null){\n" ++
"\t\t\tsynchronized(LOCK){\n" ++
"\t\t\t\ttokenList.add(t);\n" ++
"\t\t\t}\n" ++
"\t\t}  	\n" ++
"    }\n" ++
"    \n" ++
"    /**\n" ++
"     * Called at the beginning of parse\n" ++
"     */\n" ++
"    public void reset(){\n" ++
"\t\tsynchronized(LOCK){\n" ++
"\t\t\ttokenList = new ArrayList<IToken>();\n" ++
"\t\t}\n" ++
"\t\t_totalOffset = 0;\n" ++
"\t\t_startOffset = 0;\n" ++
"\t\t_endOffset = 0;\n" ++
"\t\tenParseTree.clear();\n" ++
"    }\n" ++
"\t\n" ++
"    protected IToken createToken(SingleDFAMatchData scanResult) {\n" ++
"    	_startOffset = _totalOffset;\n" ++
"\t\tif(scanResult.lexeme.length()>0){\n" ++
"\t\t\t_totalOffset += scanResult.lexeme.length();\n" ++
"\t\t}\n" ++
"\t\t_endOffset = _totalOffset;\n" ++
"    	if(_startOffset == _endOffset){\n" ++
"    		return null;\n" ++
"    	}\n" ++
"    	\n" ++
"    	_endOffset--;\n" ++
"\t\tint line =  virtualLocation.getLine();\n" ++
"\t\tint startColumn = virtualLocation.getColumn();\n" ++
"\t\tint endColumn = startColumn + scanResult.lexeme.length();\n" ++
"    	int kind = getKind(scanResult);\n" ++
"    	\n" ++
"\t\treturn new EnhancedCopperToken(\n" ++
"\t\t\tkind, \n" ++
"\t\t\tscanResult.firstTerm, \n" ++
"\t\t\ttokenList.size(), \n" ++
"\t\t\t_startOffset, \n" ++
"\t\t\t_endOffset, \n" ++
"\t\t\tline, \n" ++
"\t\t\tline, \n" ++
"\t\t\tstartColumn, \n" ++
"\t\t\tendColumn);\n" ++
"\t}\n" ++
"    \n" ++

"    class EnhancedCopperToken extends CopperToken {\n" ++
"\t\tprivate String str;\n" ++
"\t\t\n" ++
"\t\tpublic EnhancedCopperToken(int kind, int term, int tokenIndex, int startOffset,\n" ++
"\t\t\t\tint endOffset, int startLine, int endLine, int startColumn, \n" ++
"\t\t\t\tint endColumn) {\n" ++
"\t\t\tsuper(kind, term, tokenIndex, startOffset, endOffset, startLine, endLine, \n" ++
"\t\t\t\tstartColumn, endColumn);\n" ++
"\t\t}\n" ++
"\t\t\n" ++
"\t\tpublic String toString(){\n" ++
"\t\t\tif(str==null){\n" ++
"\t\t\t\tstr = \"[\" + getSymbolNames()[term] + \":\" + startLine + \"/\" + startColumn + \"(\" + startOffset + \"/\" + endOffset + \")]\";\n" ++
"\t\t\t}\n" ++
"\t\t\t\n" ++
"\t\t\treturn str;\n" ++
"\t\t}\n" ++
"    }\n" ++
"\t\n" ++
"\tpublic void printTokenList(){\n" ++
"\t\tIterator<IToken> iter = tokenList.iterator();\n" ++
"\t\tStringBuilder sb = new StringBuilder(\"TOKEN LIST (\" + tokenList.size() + \" in total) = \");\n" ++
"\t\twhile(iter.hasNext()){\n" ++
"\t\t\tsb.append(iter.next().toString());\n" ++
"\t\t\tsb.append(\", \");\n" ++
"\t\t}\n" ++
"\t\tSystem.out.println(sb);\n" ++
"\t}\n" ++
"\t\n" ++
"\tpublic Iterator<IToken> getTokenIterator(org.eclipse.jface.text.IRegion region) {\n" ++
"\t\tint startOffset = region.getOffset();\n" ++
"\t\tint endOffset = startOffset + region.getLength();\n" ++
"\t\t\n" ++
"\t\tList<IToken> list = new ArrayList<IToken>();\n" ++
"\t\tsynchronized(LOCK){\n" ++
"\t\t\tIterator<IToken> iter = tokenList.iterator();\n" ++
"\t\t\twhile(iter.hasNext()){\n" ++
"\t\t\t\tIToken next = iter.next();\n" ++
"\t\t\t\tif(next.getStartOffset() < startOffset || next.getEndOffset() > endOffset){\n" ++
"\t\t\t\t\tcontinue;\n" ++
"\t\t\t\t} else {\n" ++
"\t\t\t\t\tlist.add(next);\n" ++
"\t\t\t\t}\n" ++
"\t\t\t}\n" ++
"\t\t}\n" ++
"\t\t\n" ++
"\t\treturn list.iterator();\n" ++
"\t}\n" ++
"\t    \n" ++

"    /**\n" ++
"     * The parse tree generated during the parsing process.\n" ++
"     */\n" ++
"    protected EnhancedParseTree<IEnhancedParseTreeNode> enParseTree = new EnhancedParseTree<IEnhancedParseTreeNode>();\n\n" ++

"\tprotected int getKind(SingleDFAMatchData scanResult){\n" ++
"\t\tString term = getSymbolNames()[scanResult.firstTerm];\n" ++
"\t\treturn tokenClassifier.getKind(term);\n" ++
"\t}\n\n" ++
"\t\n" ++
"\tprivate ICopperTokenClassifier tokenClassifier = TokenClassifier.getInstance();\n" ++
"\t\n" ++
getTokenClassifier(fontList, termFontPairList) ++
"\t\n" ++
getTextAttributeDecider(fontList) ++
"\t//IDE Extension END\n";
}

-- Inner class TokenClassifier
function getTokenClassifier
String ::= fontList::[Pair<String Font>] termFontPairList::[Pair<String String>]
{
return
"\tpublic static class TokenClassifier implements ICopperTokenClassifier {\n" ++
"\t\tstatic final String PREFIX = \"" ++ getGrammarId() ++ "\" + \"$\";\n" ++
"\t\t\n" ++
"\t\tprivate static Map<String, Integer> map = new HashMap<String, Integer>();\n" ++	
"\t\t\n" ++
"\t\tpublic final static class TokenType {\n" ++
"\t\t\tpublic static final int DEFAULT = 0;\n" ++ 
"\t\t\t\n" ++ 
getConstantDeclarations(1, fontList) ++
"\t\t\t\n" ++ 
"\t\t\tpublic static final int TOTAL = " ++ toString(length(fontList)+1) ++ ";\n" ++ 
"\t\t}\n" ++	
"\t\t\n" ++	
"\t\tstatic{\n" ++	
"\t\t\t" ++ getPutNameFontPairsIntoMap(termFontPairList) ++ "\n" ++ 
"\t\t}\n" ++
"\t\t\n" ++
"\t\t@Override\n" ++
"\t\tpublic int getKind(String symbolName) {\n" ++
"\t\t\tif(symbolName==null || \"\".equals(symbolName)){\n" ++
"\t\t\t\treturn TokenType.DEFAULT;\n" ++
"\t\t\t}\n" ++
"\t\t\t\n" ++		
"\t\t\tInteger kind = map.get(symbolName);\n" ++
"\t\t\t\n" ++			
"\t\t\tif(kind==null){\n" ++
"\t\t\t\treturn TokenType.DEFAULT;\n" ++
"\t\t\t}\n" ++
"\t\t\t\n" ++		
"\t\t\treturn kind;\n" ++
"\t\t}\n" ++
"\t\t\n" ++			
"\t\tprivate static TokenClassifier INSTANCE = new TokenClassifier();\n" ++
"\t\t\n" ++		
"\t\tpublic static TokenClassifier getInstance(){\n" ++
"\t\t\treturn INSTANCE;\n" ++
"\t\t}\n" ++
"\t\t\n" ++		
"\t\tprivate TokenClassifier(){\n" ++	
"\t\t\n" ++
"\t\t}\n" ++
"\t\n" ++
"\t}\n";
}

function getPutNameFontPairsIntoMap
String ::= termFontPairList::[Pair<String String>]
{
return implode("\n\t\t\t", map(getPutNameFontPairIntoMap, termFontPairList));
}

function getPutNameFontPairIntoMap
String ::= tokenNameAndFontName::Pair<String String>
{
return "map.put(PREFIX+\"" ++ tokenNameAndFontName.fst ++ "\", " ++ "TokenType." ++ 
       (if(tokenNameAndFontName.snd!="") 
        then tokenNameAndFontName.snd --TODO check if font name is defined, if not, use DEFAULT
        else "DEFAULT") ++ ");"; 
}

function getConstantDeclarations
String ::= i::Integer fontList::[Pair<String Font>]
{
  return if (null(fontList)) 
         then "" 
         else ("\t\t\tpublic static final int " ++ 
              (head(fontList)).fst ++ 
              " = " ++ toString(i) ++ ";\n" ++ 
              getConstantDeclarations(i+1, tail(fontList)));
}

-- Inner class TextAttributeDecider
function getTextAttributeDecider
String ::= fontList::[Pair<String Font>]
{
return
"\tpublic static class TextAttributeDecider extends CopperTextAttributeDecider {\n" ++
"\t\t\n" ++
"\t\tprivate static TextAttributeDecider INSTANCE = new TextAttributeDecider();\n" ++
"\t\t\n" ++
"\t\tpublic static TextAttributeDecider getInstance(){\n" ++
"\t\t\treturn INSTANCE;\n" ++
"\t\t}\n" ++
"\t\t\n" ++
"\t\tprotected final TextAttribute[] attributes = new TextAttribute[TokenClassifier.TokenType.TOTAL];\n" ++
"\t\t\n" ++
"\t\tprivate TextAttributeDecider(){\n" ++
"\t\t\tDisplay display = Display.getDefault();\n" ++
"\t\t\t\n" ++
"\t\t\t" ++ getAttributeInitializations(fontList) ++ "\n" ++
"\t\t\t\n" ++
"\t\t\t" ++ getPutAttributesIntoMap(fontList) ++ "\n" ++
"\t\t}\n" ++
"\t\n" ++
"\t}\n";
}

function getAttributeInitializations
String ::= fontList::[Pair<String Font>]
{
return implode("\n\t\t\t", map(getAttributeInitialization, fontList));
}

function getAttributeInitialization
String ::= namedFont::Pair<String Font>
{
return "attributes[TokenClassifier.TokenType." ++ namedFont.fst ++ "] = " ++ getAttributeInitializer(namedFont.snd) ++ ";";
}

function getAttributeInitializer
String ::= font::Font
{
  local attribute color :: Color;
  color = font.color;

  return "TextAttributeProvider.getAttribute(display, " ++ 
          toString(color.r) ++ ", " ++ toString(color.g) ++ ", " ++ toString(color.b) ++ ", " ++ 
          (if(font.isBold) then "true" else "false") ++ ", " ++ 
          (if(font.isItalic) then "true" else "false") ++ ")";
}

function getPutAttributesIntoMap
String ::= fontList::[Pair<String Font>]
{
return implode("\n\t\t\t", map(getPutAttributeIntoMap, fontList));
}

function getPutAttributeIntoMap
String ::= namedFont::Pair<String Font>
{
return "addTextAttribute(TokenClassifier.TokenType." ++ namedFont.fst ++ ", attributes[TokenClassifier.TokenType." ++ namedFont.fst ++ "]);";
}
