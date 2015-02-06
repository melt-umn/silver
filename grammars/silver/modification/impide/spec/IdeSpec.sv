grammar silver:modification:impide:spec;

synthesized attribute ideParserSpec :: ParserSpec;
synthesized attribute propDcls :: [IdeProperty];
synthesized attribute wizards :: [IdeWizardDcl];
synthesized attribute svIdeInterface :: String;
synthesized attribute pluginXml :: String;
synthesized attribute pluginXmlActions :: String;
synthesized attribute pluginXmlWizards :: String;
synthesized attribute pluginParserClass :: String;
synthesized attribute pluginGrammar :: String; -- TODO: replace with sourceGrammar?
synthesized attribute ideName :: String;
synthesized attribute ideVersion :: String;

autocopy attribute bundle :: String;
autocopy attribute implang :: String;
autocopy attribute visibleName :: String;
autocopy attribute package :: String;

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

nonterminal IdeSpec with ideParserSpec, propDcls, wizards, svIdeInterface, pluginXml, pluginParserClass, pluginGrammar, ideName, ideVersion;

abstract production ideSpec
top::IdeSpec ::= 
    grammarName::String ideName::String ideVersion::String
    ext::String ideFuncDcls::[IdeFunction] idePropDcls::[IdeProperty] wizards::[IdeWizardDcl]
    pspec::ParserSpec
{
  top.ideName = ideName;
  top.ideVersion = ideVersion;
  top.pluginGrammar = grammarName;
  top.ideParserSpec = pspec;
  top.propDcls = idePropDcls;
  top.wizards = wizards;
  top.pluginParserClass = makeParserName(pspec.fullName);
  
  -- NOTE: currently, implang = ideName, but we may want to change this
  -- always use implang as the imp registry language name, and ideName as the user-visible.
  local implang :: String = ideName;
  local package :: String = makeName(grammarName);
  local bundle :: String = s"${implang}_IDE";

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

  local tabs::[String] = 
    if null(idePropDcls) then [] else ["edu.umn.cs.melt.ide.eclipse.property.TabCommons"];

  
  top.svIdeInterface = s"""
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
""";
  
  top.pluginXml = s"""<?xml version="1.0" encoding="UTF-8"?>
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
""";
}

function newTabClass
String ::= tab::String
{
  return "new " ++ tab ++ "()";
}

