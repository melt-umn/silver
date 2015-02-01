grammar silver:modification:impide;

import silver:driver;
import silver:translation:java;
import silver:util:cmdargs;

-- generate Copper Spec and other template files for IDE plugin
abstract production generateNCS
top::Unit ::= grams::EnvTree<Decorated RootSpec> silvergen::String ide::IdeSpec pkgName::String
{
  local io00::IO =
    print("[IDE plugin] Generating class templates.\n", top.ioIn);

  local io01::IO =
    writeFile(getIDETempFolder() ++ "eclipse/property/PropertyControlsProvider.java.template", getPropertyProvider(ide.propDcls, "property"),
      mkdir(getIDETempFolder() ++ "eclipse/property", io00).io);

  local io02::IO =
    writeFile(getIDETempFolder() ++ "eclipse/wizard/newproject/PropertyGenerator.java.template", getPropertyGenerator(ide.propDcls, "newproject"),
      mkdir(getIDETempFolder() ++ "eclipse/wizard/newproject", io01).io);

  local io04::IO = createWizardFiles(ide.wizards, io02);

  local io10::IO = print("[IDE plugin] Generating parsers.\n", io04);
  
  local io30::IO = writeNCSSpec(io10, grams, silvergen ++ "src/", ide.ideParserSpec, pkgName);

  local io40::IO = print("[IDE plugin] Generating plugin.xml template.\n", io30);

  local io50::IO = writeFile(getIDETempFolder() ++ "plugin.xml.template", ide.pluginXml, io40);
  
  local io60::IO = writeFile(getIDETempFolder() ++ "SVIdeInterface.java.template", ide.svIdeInterface, io50);

  top.io = io60;

  top.code = 0;
  top.order = 7;
}

function createWizardFiles
IO ::= wizards::[IdeWizardDcl] io::IO
{
  return
    if null(wizards)
    then io
    else createWizardFiles(tail(wizards), createFilesForOneWizard(head(wizards), io));
}

function createFilesForOneWizard
IO ::= wizardDcl::IdeWizardDcl io::IO
{
  -- property provider
  local io02 :: IO =
    writeFile(
      getIDETempFolder() ++ "eclipse/wizard/" ++ wizardDcl.wizName ++ "/PropertyControlsProvider.java.template", 
      getPropertyProvider(wizardDcl.wizProps, "wizard." ++ wizardDcl.wizName),
      mkdir(getIDETempFolder() ++ "eclipse/wizard/" ++ wizardDcl.wizName, io).io);

  return io02;
}

function getPropertyProvider 
String ::= propDcls :: [IdeProperty] pkgPart::String
{
  return s"""
package @PKG_NAME@.eclipse.${pkgPart};

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import edu.umn.cs.melt.ide.silver.property.ui.*;

public class PropertyControlsProvider implements IPropertyControlsProvider {

  private List<PropertyControl> controls;

  @Override
  public List<PropertyControl> getPropertyControls(Composite panel) {
    if(controls == null) {
      controls = new ArrayList<PropertyControl>();

${foldr(stringConcat, "", map((.controlJavaTranslation), propDcls))}
    }

    return controls;
  }

  @Override
  public boolean validateAll() {
    boolean valid = true;

    if(controls != null) {
      for(PropertyControl control : controls) {
        if(!control.validate()) {
          valid = false;
        }
      }
    }

    return valid;
  }
}
"""; -- TODO: for validation, we may someday want to expose a silver function where we can write how to validate a property
}

function getPropertyGenerator 
String ::= propDcls::[IdeProperty] pkgName::String
{
  local pkgPart :: String = if pkgName == "" then "" else "." ++ pkgName;

  return s"""
package @PKG_NAME@.eclipse.wizard${pkgPart};

import java.util.ArrayList;
import java.util.List;

public class PropertyGenerator {
    
    private static String properties = null;
    
    public static String getAll() {
        if(properties==null) {
            StringBuilder sb = new StringBuilder();
    
${foldr(stringConcat, "", map((.generatorJavaTranslation), propDcls))}
    
            properties = sb.toString();
        }
    
        return properties;
    }
    

    private static String escape(String str) {
        char[] orig = str.toCharArray();
        List<Character> list = new ArrayList<Character>();
        for(char c : orig) {
            if(c == '=' || c == '#' || c == '\\' || c == ':') {
               list.add('\\');
            }
            list.add(c);
        }
        
        //Convert to a char array
        char[] mod = new char[list.size()];
        for(int i = 0; i < mod.length; i++) {
            mod[i] = list.get(i);
        }
            
        return new String(mod);
    }
}
""";
}

function writeNCSSpec
IO ::= i::IO grams::EnvTree<Decorated RootSpec> silvergen::String p::ParserSpec pkgName::String 
{
  p.compiledGrammars = grams;
  
  local ast :: SyntaxRoot = p.cstAst;

  ast.jPkgName = pkgName;
  ast.jParserName = parserName;

  local parserName :: String = makeParserName(p.fullName);

  local copperFile :: String = getIDEParserFile(p.sourceGrammar, parserName, silvergen);

  local printio :: IO = print("\t[" ++ p.fullName ++ "]\n", i);
  
  local writeio :: IO = writeFile(copperFile, ast.nxmlCopper, printio);
  
  local ideio :: IO =
    writeFile(
      getIDETempFolder() ++ "imp/coloring/" ++ parserName ++ "_TokenClassifier.java.template", 
      getTokenClassifier(ast.fontList, ast.termFontPairList, parserName), 
      mkdir(getIDETempFolder() ++ "imp/coloring", writeio).io);

  return ideio;
}

-- class <pkgName>.imp.coloring.TokenClassifier
function getTokenClassifier
String ::= fontList::[Pair<String Font>] termFontPairList::[Pair<String String>] parserName::String
{
return s"""
package @PKG_NAME@.imp.coloring;

import java.util.HashMap;

import edu.umn.cs.melt.ide.copper.IToken;
import edu.umn.cs.melt.ide.copper.coloring.ICopperTokenClassifier;
import edu.umn.cs.melt.ide.copper.coloring.TextAttributeProvider;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.widgets.Display;

public class ${parserName}_TokenClassifier implements ICopperTokenClassifier {
	private static final HashMap<String, Integer> map = new HashMap<String, Integer>();

	public final static class TokenType {
		public static final int DEFAULT = 0; 
${getConstantDeclarations(1, fontList)}
		public static final int TOTAL = ${toString(length(fontList)+1)}; 
	}

	static {
		${getPutNameFontPairsIntoMap(termFontPairList)}
	}

	public static int getKind(String symbolName) {
		if(symbolName == null || "".equals(symbolName)) {
			return TokenType.DEFAULT;
		}

		Integer kind = map.get(symbolName);

		if(kind == null) {
			return TokenType.DEFAULT;
		}

		return kind;
	}

	private static final TextAttribute[] attributes = new TextAttribute[TokenType.TOTAL];
	
	static {
		Display display = Display.getDefault();
		${implode("\n\t\t", map(getTextAttributeInit, fontList))}
	}
	
	@Override
	public TextAttribute getColoring(IToken token) {
		return attributes[token.getKind()];
	}
}
""";
}

function getPutNameFontPairsIntoMap
String ::= termFontPairList::[Pair<String String>]
{
return implode("\n\t\t", map(getPutNameFontPairIntoMap, termFontPairList));
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
  return if null(fontList)
         then ""
         else "\t\tpublic static final int " ++ head(fontList).fst ++ " = " ++ toString(i) ++ ";\n" ++ 
              getConstantDeclarations(i+1, tail(fontList));
}

function getTextAttributeInit
String ::= f::Pair<String Font>
{
  return s"""attributes[TokenType.${f.fst}] = ${f.snd.getTextAttribute};""";
}

function getIDETempFolder
String ::=
{
  return "./ide_files/";
}

