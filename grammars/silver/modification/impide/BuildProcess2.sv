grammar silver:modification:impide;

import silver:driver;
import silver:translation:java;
import silver:util:cmdargs;

aspect production compilation
top::Compilation ::= g::Grammars _ buildGrammar::String silverHome::String silverGen::String
{
  -- The RootSpec representing the grammar actually being built (specified on the command line)
  production builtGrammar :: [Decorated RootSpec] = searchEnvTree(buildGrammar, g.compiledGrammars);
  
  -- Empty if no ide decl in that grammar, otherwise has at least one spec... note that
  -- we're going to go with assuming there's just one IDE declaration...
  production isIde :: Boolean = !null(builtGrammar) && !null(head(builtGrammar).ideSpecs);

  local startNTClassName::String = makeNTClassName(head(allParsers).cstAst.startNT);

  -- pkgName is derived in the aspect defined in ./BuildProcess.sv
  top.postOps <- if !isIde then [] else [generateNCS(g.compiledGrammars, allParsers, silverGen, ide, pkgName, startNTClassName)];

  extraTopLevelDecls <- if !isIde then [] else [
    "<property name='start.nonterminal.class' value='" ++ startNTClassName ++ "'/>"]; 
  -- FIXME? we now only track the first parser.
}

abstract production generateNCS
top::Unit ::= grams::EnvTree<Decorated RootSpec> specs::[ParserSpec] silvergen::String ide::IdeSpec pkgName::String startNTClassName::String
{
  local attribute pr::IO;
  pr = print("Generating Parsers and Scanners for IMP-based IDE.\n", top.ioIn);
  
  local attribute pr2::IO;
  pr2 = writeFile(getIDETempFolder() ++ "eclipse/property/PropertyControlsProvider.java.template", getPropertyProvider(ide.propDcls),
		mkdir(getIDETempFolder() ++ "eclipse/property", pr).io);

  top.io = writeNCSSpec(
		writeFile(getIDETempFolder() ++ "eclipse/wizard/PropertyGenerator.java.template", getPropertyGenerator(ide.propDcls),
		mkdir(getIDETempFolder() ++ "eclipse/wizard", pr2).io), 
		grams, silvergen ++ "src/", specs, pkgName, startNTClassName);

  top.code = 0;
  top.order = 7;
}

function getPropertyProvider 
String ::= propDcls :: [IdeProperty]
{
  return 
	"package @PKG_NAME@.eclipse.property;\n" ++
	"\n" ++	
	"import java.util.ArrayList;\n" ++
	"import java.util.List;\n" ++
	"\n" ++	
	"import org.eclipse.swt.widgets.Composite;\n" ++
	"\n" ++	
	"import edu.umn.cs.melt.ide.silver.property.ui.*;\n" ++
	"\n" ++	
	"public class PropertyControlsProvider implements IPropertyControlsProvider {\n" ++
	"\n" ++	
	"    private List<PropertyControl> controls;\n" ++
	"\n" ++		
	"    @Override\n" ++
	"    public List<PropertyControl> getPropertyControls(Composite panel) {\n" ++
	"	    if(controls==null){\n" ++
	"		    controls = new ArrayList<PropertyControl>();\n" ++
	"\n" ++				
	"		    //Generated based on IDE declaration\n" ++

	            getProperties2(propDcls) ++

	"	    }\n" ++
	"\n" ++			
	"	    return controls;\n" ++
	"    }\n" ++
	"\n" ++	
	"    @Override\n" ++
	"    public boolean validateAll() {\n" ++
	"	    boolean valid = true;\n" ++
	"\n" ++			
	"	    if(controls!=null){\n" ++
	"		    for(PropertyControl control:controls){\n" ++
	"			    if(!control.validate()){\n" ++
	"				    valid = false;\n" ++
	"			    }\n" ++
	"		    }	\n" ++		
	"	    }\n" ++
	"\n" ++			
	"	    //TODO\n" ++
	"	    //IDE developers may add validations here. This is mainly for \n" ++
	"	    //context-aware check, such as determining the validity of one field \n" ++
	"	    //based on that of another.\n" ++
	"\n" ++			
	"	    return valid;\n" ++
	"    }\n" ++
	"\n" ++	
	"}\n";

}

function getProperties2 
String ::= propDcls :: [IdeProperty]
{
  return if null(propDcls) 
         then "\n"	
         else getProperty2(head(propDcls)) ++ getProperties2(tail(propDcls));
}

function getProperty2
String ::= propDcl :: IdeProperty
{
  return "            "
         ++ "controls.add(new " ++ getConstructorByType(propDcl.propType) ++ "(panel, \"" 
         ++ propDcl.propName ++ "\", \"" 
         ++ propDcl.displayName ++ "\", \"" 
         ++ propDcl.defaultVal ++ "\", "
         ++ (if propDcl.optional then "false" else "true") --translating "optional" to "required" by negating
         ++ "));\n";
}

function getConstructorByType
String ::= propType :: String
{
  return if propType == "string" then "TextPropertyControl"
    else if propType == "path" then "PathPropertyControl"
    else if propType == "url" then "URLPropertyControl"
    else -- propType == "integer" 
    "IntegerPropertyControl";
}

function getPropertyGenerator 
String ::= propDcls :: [IdeProperty]
{
  return 
	"package @PKG_NAME@.eclipse.wizard;\n" ++
	"\n" ++
	"import java.util.ArrayList;\n" ++
	"import java.util.List;\n" ++
	"\n" ++
	"public class PropertyGenerator {\n" ++
	"    \n" ++
	"    private static String properties = null;\n" ++
	"    \n" ++	
	"    public static String getAll() {\n" ++
	"        if(properties==null){\n" ++
	"            StringBuilder sb = new StringBuilder();\n" ++
	"    \n" ++				
                 getProperties(propDcls) ++
	"    \n" ++			
	"            properties = sb.toString();\n" ++
	"        }\n" ++
	"    \n" ++		
	"        return properties;\n" ++
	"    }\n" ++
	"    \n" ++	

	"    private static String escape(String str){\n" ++	
	"        char[] orig = str.toCharArray();\n" ++	
	"        List<Character> list = new ArrayList<Character>();\n" ++	
	"        for(char c:orig){\n" ++	
	"            if(c=='='||c=='#'||c=='\\\\'||c==':'){\n" ++	
	"               list.add('\\\\');\n" ++	
	"            }\n" ++	
	"            list.add(c);\n" ++	
	"        }\n" ++	
	"        \n" ++	      	
	"        //Convert to a char array\n" ++	
	"        char[] mod = new char[list.size()];\n" ++	
	"        for(int i=0;i<mod.length;i++){\n" ++	
	"            mod[i] = list.get(i);\n" ++	
	"        }\n" ++	
	"        \n" ++	    
	"        return new String(mod);\n" ++	
	"    }\n" ++	
	"    \n" ++	

	"}\n";
}

function getProperties 
String ::= propDcls :: [IdeProperty]
{
  return if null(propDcls) 
         then ""	
         else getProperty(head(propDcls)) ++ getProperties(tail(propDcls));
}

function getProperty
String ::= propDcl :: IdeProperty
{
  return "            sb.append(\"" ++ propDcl.propName ++ 
        "\");sb.append(\"/\");sb.append(\"" ++ propDcl.propType ++ 
        "=\");sb.append(escape(\"" ++ propDcl.defaultVal ++ 
        "\"));sb.append(\"\\n\");\n";
}

function writeNCSSpec
IO ::= i::IO grams::EnvTree<Decorated RootSpec> silvergen::String specs::[ParserSpec] pkgName::String startNTClassName::String
{
  local attribute p :: ParserSpec;
  p = head(specs);
  p.compiledGrammars = grams;
  
  local attribute ast :: SyntaxRoot;
  ast = p.cstAst;

  ast.jPkgName = pkgName;
  ast.jParserName = parserName;
  ast.startNTClassName = startNTClassName;

  local attribute parserName :: String;
  parserName = makeParserName(p.fullName);

  local attribute copperFile :: String;
  copperFile = getIDEParserFile(p.sourceGrammar, parserName, silvergen);

  local attribute printio :: IO;
  printio = print("\t[" ++ p.fullName ++ "]\n", i);
  
  local attribute writeio :: IO;
  writeio = writeFile(copperFile, ast.nxmlCopper, printio);
  
  local attribute ideio :: IO;
  ideio = writeFile(getIDETempFolder() ++ "imp/coloring/" ++ parserName ++ "_TokenClassifier.java.template", 
                    getTokenClassifier(ast.fontList, ast.termFontPairList, parserName), 
	    writeFile(getIDETempFolder() ++ "imp/coloring/" ++ parserName ++ "_TextAttributeDecider.java.template", 
                      getTextAttributeDecider(ast.fontList, parserName), 
              mkdir(getIDETempFolder() ++ "imp/coloring", writeio).io
            ));

  local attribute ideio2 :: IO;
  ideio2 = writeFile(
            getIDETempFolder() ++ "copper/parser/" ++ parserName ++ "_ASTVisitorAdapter.java.template", 
            getASTVisitorAdapter(ast.ideSymbolInfos, parserName), 
            mkdir(getIDETempFolder() ++ "copper/parser", ideio).io);

  return if null(specs) then i
         else writeNCSSpec(ideio2, grams, silvergen, tail(specs), pkgName, startNTClassName);
}

-- class <pkgName>.imp.controller.ASTVisitorAdapter
function getASTVisitorAdapter
String ::= symInfos::[IDEParserSymbolInfo] parserName::String
{
return
  "package @PKG_NAME@.copper.parser;\n" ++
  "\n" ++
  "import edu.umn.cs.melt.ide.copper.IEnhancedParseTreeInnerNode;\n" ++
  "import edu.umn.cs.melt.ide.copper.IEnhancedParseTreeLeafNode;\n" ++
  "import @PKG_NAME@.copper.parser." ++ parserName ++".ASTVisitor;\n" ++
  "\n" ++
  "public class ASTVisitorAdapter implements ASTVisitor {\n" ++
  "\n" ++
    -- Generate code in format of
	-- public void visit_silver_definition_core_Root(IEnhancedParseTreeInnerNode node) { }
    generateVisitorDummyImplCode(symInfos) ++
  "}\n";
}

function generateVisitorDummyImplCode
String ::= list::[Pair<String Pair<Boolean Integer>>]
{
    return if null(list)
           then ""
           else generateVisitorDummyImplMethod(head(list)) ++ generateVisitorDummyImplCode(tail(list));
}
	
--@Override
--public void visit_silver_definition_core_Root(IEnhancedParseTreeInnerNode node) { }
function generateVisitorDummyImplMethod
String ::= name::Pair<String Pair<Boolean Integer>>
{
    return "\t@Override\n" ++
           "\tpublic void visit_" ++ name.fst ++ "(" ++
           (if (name.snd.fst) then "IEnhancedParseTreeLeafNode" else "IEnhancedParseTreeInnerNode") ++ 
           " node){ };\n\n";
}

-- class <pkgName>.imp.coloring.TokenClassifier
function getTokenClassifier
String ::= fontList::[Pair<String Font>] termFontPairList::[Pair<String String>] parserName::String
{
return
  "package @PKG_NAME@.imp.coloring;\n" ++
  "\n" ++
  "import java.util.HashMap;\n" ++
  "import java.util.Map;\n" ++
  "\n" ++
  "import edu.umn.cs.melt.ide.copper.coloring.ICopperTokenClassifier;\n" ++
  "\n" ++
  "public class " ++ parserName ++ "_TokenClassifier implements ICopperTokenClassifier {\n" ++
  "\tprivate static Map<String, Integer> map = new HashMap<String, Integer>();\n" ++	
  "\t\n" ++
  "\tpublic final static class TokenType {\n" ++
  "\t\t\n" ++ 
  "\t\tpublic static final int DEFAULT = 0;\n" ++ 
  "\t\t\n" ++ 
  getConstantDeclarations(1, fontList) ++
  "\t\t\n" ++ 
  "\t\tpublic static final int TOTAL = " ++ toString(length(fontList)+1) ++ ";\n" ++ 
  "\t}\n" ++	
  "\t\n" ++	
  "\tstatic{\n" ++	
  "\t\t" ++ getPutNameFontPairsIntoMap(termFontPairList) ++ "\n" ++ 
  "\t}\n" ++
  "\t\n" ++
  "\t@Override\n" ++
  "\tpublic int getKind(String symbolName) {\n" ++
  "\t\tif(symbolName==null || \"\".equals(symbolName)){\n" ++
  "\t\t\treturn TokenType.DEFAULT;\n" ++
  "\t\t}\n" ++
  "\t\t\n" ++		
  "\t\tInteger kind = map.get(symbolName);\n" ++
  "\t\t\n" ++			
  "\t\tif(kind==null){\n" ++
  "\t\t\treturn TokenType.DEFAULT;\n" ++
  "\t\t}\n" ++
  "\t\t\n" ++		
  "\t\treturn kind;\n" ++
  "\t}\n" ++
  "\t\n" ++			
  "\tprivate static " ++ parserName ++ "_TokenClassifier INSTANCE = new " ++ parserName ++ "_TokenClassifier();\n" ++
  "\t\n" ++
  "\tpublic static " ++ parserName ++ "_TokenClassifier getInstance(){\n" ++
  "\t\treturn INSTANCE;\n" ++
  "\t}\n" ++
  "\t\n" ++
  "\tprivate " ++ parserName ++ "_TokenClassifier(){\n" ++
  "\t\n" ++
  "\t}\n" ++
  "\n" ++
  "}\n";
}

function getPutNameFontPairsIntoMap
String ::= termFontPairList::[Pair<String String>]
{
return implode("\n\t\t\t", map(getPutNameFontPairIntoMap, termFontPairList));
}

function getPutNameFontPairIntoMap
String ::= tokenNameAndFontName::Pair<String String>
{
return "map.put(\"" ++ tokenNameAndFontName.fst ++ "\", " ++ "TokenType." ++ 
       (if tokenNameAndFontName.snd != ""
        then tokenNameAndFontName.snd
        else "DEFAULT") ++ ");"; 
}

function getConstantDeclarations
String ::= i::Integer fontList::[Pair<String Font>]
{
  return if (null(fontList)) 
         then "" 
         else ("\t\t\tpublic static final int " ++ 
              head(fontList).fst ++ 
              " = " ++ toString(i) ++ ";\n" ++ 
              getConstantDeclarations(i+1, tail(fontList)));
}

-- Inner class TextAttributeDecider
function getTextAttributeDecider
String ::= fontList::[Pair<String Font>] parserName::String
{
return
  "package @PKG_NAME@.imp.coloring;\n" ++
  "\n" ++
  "import org.eclipse.jface.text.TextAttribute;\n" ++
  "import org.eclipse.swt.widgets.Display;\n" ++
  "\n" ++
  "import edu.umn.cs.melt.ide.copper.coloring.CopperTextAttributeDecider;\n" ++
  "import edu.umn.cs.melt.ide.copper.coloring.TextAttributeProvider;\n" ++
  "\n" ++
  "public class " ++ parserName ++ "_TextAttributeDecider extends CopperTextAttributeDecider {\n" ++
  "\t\n" ++
  "\tprivate static " ++ parserName ++ "_TextAttributeDecider INSTANCE = new " ++ parserName ++ "_TextAttributeDecider();\n" ++
  "\t\n" ++
  "\tpublic static " ++ parserName ++ "_TextAttributeDecider getInstance(){\n" ++
  "\t\treturn INSTANCE;\n" ++
  "\t}\n" ++
  "\t\n" ++
  "\tprotected final TextAttribute[] attributes = new TextAttribute[" ++ parserName ++ "_TokenClassifier.TokenType.TOTAL];\n" ++
  "\t\n" ++
  "\tprivate " ++ parserName ++ "_TextAttributeDecider(){\n" ++
  "\t\tDisplay display = Display.getDefault();\n" ++
  "\t\t\n" ++
  "\t\t" ++ getAttributeInitializations(fontList, parserName) ++ "\n" ++
  "\t\t\n" ++
  "\t\t" ++ getPutAttributesIntoMap(fontList, parserName) ++ "\n" ++
  "\t}\n" ++
  "\n" ++
  "}\n";
}

function getAttributeInitializations
String ::= fontList::[Pair<String Font>] parserName::String
{
return 
  if(null(fontList))
  then ""
  else "\n\t\t\tattributes[" ++ parserName ++ "_" ++ getAttributeInitialization(head(fontList)) ++ getAttributeInitializations(tail(fontList), parserName);

--return implode("\n\t\t\t", map(getAttributeInitialization, fontList));
}

function getAttributeInitialization
String ::= namedFont::Pair<String Font>
{
--attributes[
return "TokenClassifier.TokenType." ++ namedFont.fst ++ "] = " ++ getAttributeInitializer(namedFont.snd) ++ ";";
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
String ::= fontList::[Pair<String Font>] parserName::String
{
return 
  if(null(fontList))
  then ""
  else "\n\t\t\taddTextAttribute(" ++ parserName ++ "_TokenClassifier.TokenType." ++ head(fontList).fst ++ 
       ", attributes[" ++ parserName ++ "_TokenClassifier.TokenType." ++ head(fontList).fst ++ "]);" ++ getPutAttributesIntoMap(tail(fontList), parserName);

--return implode("\n\t\t\t", map(getPutAttributeIntoMap, fontList));
}

function getPutAttributeIntoMap
String ::= namedFont::Pair<String Font>
{
return "addTextAttribute(TokenClassifier.TokenType." ++ namedFont.fst ++ ", attributes[TokenClassifier.TokenType." ++ namedFont.fst ++ "]);";
}

function getIDETempFolder
String ::=
{
  return "./ide_files/";
}

