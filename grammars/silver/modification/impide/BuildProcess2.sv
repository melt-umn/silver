grammar silver:modification:impide;

import silver:modification:impide:cstast;
import silver:modification:copper;
import silver:definition:concrete_syntax;
import silver:definition:concrete_syntax:ast;
import silver:driver;
import silver:translation:java;
--import silver:translation:java:core only makeIdName, makeNTClassName;
--import silver:translation:java:driver;
import silver:util:cmdargs;

aspect production compilation
top::Compilation ::= g::Grammars _ buildGrammar::String silverHome::String silverGen::String
{
  -- The RootSpec representing the grammar actually being built (specified on the command line)
  production builtGrammar :: [Decorated RootSpec] = searchEnvTree(buildGrammar, g.compiledGrammars);
  
  -- Empty if no ide decl in that grammar, otherwise has at least one spec... note that
  -- we're going to go with assuming there's just one IDE declaration...
  production isIde :: Boolean = !null(builtGrammar) && !null(head(builtGrammar).ideSpecs);

  top.postOps <- if !isIde then [] else [generateNCS(g.compiledGrammars, allParsers, silverGen, ide)];

  extraTopLevelDecls <- if !isIde then [] else [
    "<property name='start.nonterminal.class' value='" ++ makeNTClassName(head(allParsers).cstAst.startNT) ++ "'/>"]; 
  -- FIXME? we now only track the first parser.
}

abstract production generateNCS
top::Unit ::= grams::EnvTree<Decorated RootSpec> specs::[ParserSpec] silvergen::String ide::IdeSpec
{
  local attribute pr::IO;
  pr = print("Generating Parsers and Scanners for IMP-based IDE.\n", top.ioIn);
  
  top.io = writeNCSSpec(
		writeFile("ide_files/eclipse/wizard/PropertyGenerator.java.template", getPropertyGenerator(ide.propDcls),
		mkdir("ide_files/eclipse/wizard", pr).io), 
		grams, silvergen ++ "src/", specs);

  --top.io = writeNCSSpec(pr, grams, silvergen ++ "src/", specs);
  top.code = 0;
  top.order = 7;
}

function getPropertyGenerator 
String ::= propDcls :: [IdeProperty]
{
  return 
	"package @PKG_NAME@.eclipse.wizard;\n" ++
	"\n" ++
	"public class PropertyGenerator {\n" ++
	"    \n" ++
	"    private static String properties = null;\n" ++
	"    \n" ++	
	"    public static String getAll() {\n" ++
	"        if(properties==null){\n" ++
	"            StringBuilder sb = new StringBuilder();\n" ++
	"    \n" ++				

	--should prevent duplicate definition of silver_home by user
	"            sb.append(\"silver_home\");sb.append(\"/\");sb.append(\"path\");sb.append(\"=\\n\");\n" ++

	getProperties(propDcls) ++

	"    \n" ++			
	"            properties = sb.toString();\n" ++
	"        }\n" ++
	"    \n" ++		
	"        return properties;\n" ++
	"    }\n" ++
	"\n" ++	
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
  return "		sb.append(\"" ++ propDcl.propName ++ "\");sb.append(\"/\");sb.append(\"" ++ propDcl.propType ++ "\");sb.append(\"=\\n\");\n";
}

function writeNCSSpec
IO ::= i::IO grams::EnvTree<Decorated RootSpec> silvergen::String specs::[ParserSpec]
{
  local attribute p :: ParserSpec;
  p = head(specs);
  p.compiledGrammars = grams;
  
  local attribute ast :: SyntaxRoot;
  ast = p.cstAst;
  
  local attribute parserName :: String;
  parserName = makeParserName(p.fullName);

  local attribute copperFile :: String;
  copperFile = silvergen ++ grammarToPath(p.sourceGrammar) ++ parserName ++ "_ide.copper";

  local attribute printio :: IO;
  printio = print("\t[" ++ p.fullName ++ "]\n", i);
  
  local attribute writeio :: IO;
  writeio = writeFile(copperFile, ast.nxmlCopper, printio);
  
  return if null(specs) then i
         else writeNCSSpec(writeio, grams, silvergen, tail(specs));
}

