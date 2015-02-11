grammar silver:modification:impide:spec;

synthesized attribute ideResources :: [Pair<String String>];

synthesized attribute pluginParserClass :: String;
synthesized attribute pluginGrammar :: String; -- TODO: replace with sourceGrammar?
synthesized attribute ideName :: String;
synthesized attribute ideVersion :: String;
synthesized attribute pluginFiles :: [Pair<String String>];

-- These are for our spec's children:
synthesized attribute svIdeInterface :: String;
synthesized attribute pluginXml :: String;
synthesized attribute pluginXmlActions :: String;
synthesized attribute pluginXmlWizards :: String;

autocopy attribute bundle :: String;
autocopy attribute implang :: String;
autocopy attribute visibleName :: String;
autocopy attribute package :: String;
autocopy attribute pluginPkgPath :: String;

{--
 - Eclipse extensions have unique IDs.
 - When these IDs are supplied to an <extension> tag, they are
 - automatically prefixed with the bundle name.
 - e.g. "SILVER_IDE.builder" for <extension id="builder"...>
 - 
 - BUT, when the IDs are given to sub-tags underneath <extension>, you must
 - evidently give the fully qualified name right off the bat. *sigh* eclipse.
 -}
global extid_nature :: String = "nature";
global extid_builder :: String = "builder";
global extid_problem :: String = "builder.problem";
global extid_perspective :: String = "perspective";

global extid_projectmenu :: String = "actions.projectmenu";
global extid_action_nature :: String = "actions.nature";
global extid_action_export :: String = "actions.export";

global extid_wizard_category :: String = "wizards.category";
global extid_wizard_newproject :: String = "wizards.newproject";
global extid_wizard_newfile :: String = "wizards.newfile";

global extid_properties :: String = "properties";

nonterminal IdeSpec with compiledGrammars, pluginParserClass, pluginGrammar, ideName, ideVersion, pluginFiles, ideResources;

abstract production ideSpec
top::IdeSpec ::= 
    grammarName::String ideName::String ideVersion::String
    ext::String ideFuncDcls::[IdeFunction] idePropDcls::[IdeProperty] wizards::[IdeWizardDcl]
    pspec::ParserSpec ideResources::[Pair<String String>]
{
  top.ideName = ideName;
  top.ideVersion = ideVersion;
  top.pluginGrammar = grammarName;
  top.pluginParserClass = makeParserName(pspec.fullName);
  top.ideResources = ideResources;
  
  -- NOTE: currently, implang = ideName, but we may want to change this
  -- always use implang as the imp registry language name, and ideName as the user-visible.
  local implang :: String = ideName;
  local package :: String = makeName(grammarName);
  local bundle :: String = package; -- same for now
  
  local pluginPkgPath :: String = s"src/${grammarToPath(grammarName)}";

  local ast :: SyntaxRoot = pspec.cstAst;
  ast.jPkgName = package;
  ast.jParserName = top.pluginParserClass;

  local funcs :: IdeFunctions = foldr(consIdeFunction, nilIdeFunction(), ideFuncDcls);
  funcs.bundle = bundle;
  funcs.implang = implang;
  funcs.visibleName = ideName;
  funcs.package = package;
  
  local wizs :: IdeWizards = foldr(consIdeWizard, nilIdeWizard(), wizards);
  wizs.bundle = bundle;
  wizs.implang = implang;
  wizs.visibleName = ideName;
  wizs.package = package;
  wizs.pluginPkgPath = pluginPkgPath;

  local tabs::[String] = 
    if null(idePropDcls) then [] else ["edu.umn.cs.melt.ide.eclipse.property.TabCommons"];

  
  top.pluginFiles =
    [pair(s"${pluginPkgPath}SVIdeInterface.java", s"""
package ${package};

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import common.ConsCell;
import common.Node;
import common.StringCatter;
import core.NIOVal;
import core.Pioval;

import ide.NIdeEnv;

import org.eclipse.jface.text.IRegion;

import edu.umn.cs.melt.ide.eclipse.property.IPropertyPageTab;
import edu.umn.cs.melt.ide.silver.property.ui.IPropertyControlsProvider;
import edu.umn.cs.melt.ide.impl.SVDefault;
import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;
import edu.umn.cs.melt.ide.copper.coloring.ICopperTokenClassifier;
import edu.umn.cs.melt.ide.imp.services.IdeParseResult;
import edu.umn.cs.melt.ide.copper.CopperToken;

public class SVIdeInterface extends SVDefault {

	public SVIdeInterface() {}

	@Override
	public String name() { return "${implang}"; }
	@Override
	public String pluginId() { return "${bundle}"; }
	@Override
	public String markerErrorName() { return "${bundle}.${extid_problem}"; }
	@Override
	public String getNatureId() { return "${bundle}.${extid_nature}"; }
	@Override
	public String fileExtension() { return "${ext}"; }
	@Override
	public IPropertyControlsProvider getProjectProperties() {
		return new ${package}.eclipse.property.PropertyControlsProvider();
	}
	@Override
	public String getInitialProjectProperties() {
		return ${package}.eclipse.wizard.newproject.PropertyGenerator.getAll();
	}
	@Override
	public IPropertyPageTab[] getPropertyTabs() {
		return new IPropertyPageTab[] {
			${implode(", ", map(newTabClass, tabs))}
		};
	}
	@Override
	public ICopperTokenClassifier getTokenClassifier() {
		return new ${package}.imp.coloring.${top.pluginParserClass}_TokenClassifier();
	}
	private ${package}.copper.parser.${top.pluginParserClass} parser = new ${package}.copper.parser.${top.pluginParserClass}();
	@Override
	public IdeParseResult<Node, CopperToken> parse(Reader input, String filename) throws CopperParserException, IOException {
		// In the long run, maybe we should have a getParser() rather than parse() so things could be concurrent... TODO
		synchronized(parser) {
			parser.reset();
			return new IdeParseResult<Node, CopperToken>((Node)parser.parse(input, filename), parser.getTokens());
		}
	}



${funcs.svIdeInterface}
${wizs.svIdeInterface}
}
"""),
    pair("plugin.xml", s"""<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.0"?>
<plugin>

<extension point="org.eclipse.imp.runtime.languageDescription">
  <language extensions="${ext}" description="nothing here" natureID="${bundle}.${extid_nature}" language="${implang}">
  </language>
</extension>

<extension point="org.eclipse.imp.runtime.parser">
  <parserWrapper class="edu.umn.cs.melt.ide.imp.services.ParseController" language="${implang}">
  </parserWrapper>
</extension>

<extension point="org.eclipse.core.resources.builders" id="${extid_builder}" name="${ideName} builder">
  <builder hasNature="true">
    <run class="edu.umn.cs.melt.ide.imp.builders.Builder">
    </run>
  </builder>
</extension>

<extension point="org.eclipse.core.resources.natures" id="${extid_nature}" name="${ideName} Nature">
  <builder id="${bundle}.${extid_builder}" />
  <runtime>
    <run class="edu.umn.cs.melt.ide.imp.builders.Nature">
      <parameter name="builder" value="${bundle}.${extid_builder}" />
    </run>
  </runtime>
</extension>

<extension point="org.eclipse.ui.perspectives">
  <perspective
      class="edu.umn.cs.melt.ide.eclipse.Perspective"
      id="${bundle}.${extid_perspective}"
      name="${ideName}">
  </perspective>
</extension>

<extension point="org.eclipse.core.resources.markers" id="${extid_problem}" name="${ideName} Error">
  <super type="org.eclipse.core.resources.problemmarker" />
  <persistent value="true" />
</extension>

<extension point="org.eclipse.imp.runtime.tokenColorer">
  <tokenColorer class="edu.umn.cs.melt.ide.imp.services.Colorer" language="${implang}">
  </tokenColorer>
</extension>

<extension point="org.eclipse.ui.popupMenus">
  <objectContribution objectClass="org.eclipse.core.resources.IProject" adaptable="true" nameFilter="*" id="${bundle}.${extid_projectmenu}">

    <action
        label="Enable ${ideName} Builder"
        tooltip="Enable the ${ideName} builder for this project"
        id="${bundle}.${extid_action_nature}">
      <class class="edu.umn.cs.melt.ide.imp.builders.EnableNature">
        <parameter name="nature" value="${bundle}.${extid_nature}" />
      </class>
    </action>

${funcs.pluginXmlActions}

  </objectContribution>
</extension>

<extension point="org.eclipse.ui.newWizards">
  <category
      id="${bundle}.${extid_wizard_category}"
      name="${ideName}">
  </category>
  <wizard
      category="${bundle}.${extid_wizard_category}"
      class="edu.umn.cs.melt.ide.wizard.NewProjectWizard"
      id="${bundle}.${extid_wizard_newproject}"
      name="New ${ideName} Project"
      finalPerspective="${bundle}.${extid_perspective}"
      project="true">
  </wizard>
  
${wizs.pluginXmlWizards}

</extension>

<extension point="org.eclipse.ui.propertyPages">
  <page
      class="edu.umn.cs.melt.ide.eclipse.property.MultiTabPropertyPage"
      id="${bundle}.${extid_properties}"
      name="${ideName}">
    <enabledWhen>
      <and>
        <instanceof value="org.eclipse.core.resources.IProject"/>
        <adapt type="org.eclipse.core.resources.IResource">
          <test property="org.eclipse.core.resources.projectNature"
                value="${bundle}.${extid_nature}">
          </test>
        </adapt>
      </and>
    </enabledWhen>
  </page>
</extension>

${funcs.pluginXml}

</plugin>
"""),
  pair(s"${pluginPkgPath}Plugin.java",
    s"""
package ${package};

import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleActivator;

import edu.umn.cs.melt.ide.impl.SVRegistry;

public class Plugin implements BundleActivator {

    public void start(BundleContext context) throws Exception {

        Init.initAllStatics();
        Init.init();
        Init.postInit();

        SVRegistry.register(new SVIdeInterface());
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }
}
"""),
  pair(s"${pluginPkgPath}eclipse/property/PropertyControlsProvider.java",
    getPropertyProvider(package, idePropDcls, "property")),
  pair(s"${pluginPkgPath}eclipse/wizard/newproject/PropertyGenerator.java",
    getPropertyGenerator(package, idePropDcls, "newproject")),
  pair(s"${pluginPkgPath}copper/parser/${top.pluginParserClass}.copper",
    ast.nxmlCopper),
  pair(s"${pluginPkgPath}imp/coloring/${top.pluginParserClass}_TokenClassifier.java",
    getTokenClassifier(package, ast.fontList, ast.termFontPairList, top.pluginParserClass))
  ] ++
  wizs.pluginFiles;
}

function newTabClass
String ::= tab::String
{
  return "new " ++ tab ++ "()";
}


function getPropertyProvider 
String ::= pkgName::String propDcls :: [IdeProperty] pkgPart::String
{
  return s"""
package ${pkgName}.eclipse.${pkgPart};

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
String ::= pkgName::String propDcls::[IdeProperty] pkgFinalPart::String
{
  local pkgPart :: String = if pkgFinalPart == "" then "" else "." ++ pkgFinalPart;

  return s"""
package ${pkgName}.eclipse.wizard${pkgPart};

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

-- class <pkgName>.imp.coloring.TokenClassifier
function getTokenClassifier
String ::= pkgName::String fontList::[Pair<String Font>] termFontPairList::[Pair<String String>] parserName::String
{
return s"""
package ${pkgName}.imp.coloring;

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
		${implode("\n\t\t", map(getPutNameFontPairIntoMap, termFontPairList))}
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

