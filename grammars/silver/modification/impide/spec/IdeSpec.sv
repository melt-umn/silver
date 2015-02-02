grammar silver:modification:impide:spec;

synthesized attribute ideParserSpec :: ParserSpec;
synthesized attribute propDcls :: [IdeProperty];
synthesized attribute wizards :: [IdeWizardDcl];
synthesized attribute svIdeInterface :: String;
synthesized attribute pluginXml :: String;
synthesized attribute pluginXmlActions :: String;
synthesized attribute pluginXmlWizards :: String;
synthesized attribute pluginParserClass :: String;
synthesized attribute pluginGrammar :: String;
synthesized attribute ideName :: String;
synthesized attribute ideVersion :: String;

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
  
  local tabs::[String] = 
    if null(idePropDcls) then [] else ["edu.umn.cs.melt.ide.eclipse.property.TabCommons"];

  
  top.svIdeInterface = s"""
package @PKG_NAME@;

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
	public String name() { return "@LANG_NAME@"; }
	@Override
	public String pluginId() { return "@LANG_NAME@_IDE"; }
	@Override
	public String markerErrorName() { return "@LANG_NAME@_IDE.@LANG_NAME@.imp.builder.problem"; }
	@Override
	public String getNatureId() { return "@LANG_NAME@_IDE.imp.nature"; }
	@Override
	public String fileExtension() { return "${ext}"; }
	@Override
	public IPropertyControlsProvider getProjectProperties() {
		return new @PKG_NAME@.eclipse.property.PropertyControlsProvider();
	}
	@Override
	public String getInitialProjectProperties() {
		return @PKG_NAME@.eclipse.wizard.newproject.PropertyGenerator.getAll();
	}
	@Override
	public IPropertyPageTab[] getPropertyTabs() {
		return new IPropertyPageTab[] {
			${implode(", ", map(newTabClass, tabs))}
		};
	}
	@Override
	public ICopperTokenClassifier getTokenClassifier() {
		return new @PKG_NAME@.imp.coloring.${top.pluginParserClass}_TokenClassifier();
	}
	private @PKG_NAME@.copper.parser.${top.pluginParserClass} parser = new @PKG_NAME@.copper.parser.${top.pluginParserClass}();
	@Override
	public IdeParseResult<Node, CopperToken> parse(Reader input, String filename) throws CopperParserException, IOException {
		// In the long run, maybe we should have a getParser() rather than parse() so things could be concurrent... TODO
		synchronized(parser) {
			parser.reset();
			return new IdeParseResult<Node, CopperToken>((Node)parser.parse(input, filename), parser.getTokens());
		}
	}



${foldr(stringConcat, "", map((.svIdeInterface), ideFuncDcls))}
${foldr(stringConcat, "", map((.svIdeInterface), wizards))}
}
""";
  
  top.pluginXml = s"""<?xml version="1.0" encoding="UTF-8"?>
<?eclipse version="3.0"?>
<plugin>

<extension point="org.eclipse.imp.runtime.languageDescription">
  <language extensions="${ext}" description="nothing here" natureID="@LANG_NAME@_IDE.imp.nature" language="@LANG_NAME@">
  </language>
</extension>

<extension point="org.eclipse.imp.runtime.parser">
  <parserWrapper class="edu.umn.cs.melt.ide.imp.services.ParseController" language="@LANG_NAME@">
  </parserWrapper>
</extension>

<extension point="org.eclipse.core.resources.builders" id="@LANG_NAME@.imp.builder" name="@LANG_NAME@ builder">
  <builder hasNature="true">
    <run class="edu.umn.cs.melt.ide.imp.builders.Builder">
    </run>
  </builder>
</extension>

<extension point="org.eclipse.core.resources.natures" id="imp.nature" name="@LANG_NAME@ Nature">
  <builder id="@LANG_NAME@_IDE.@LANG_NAME@.imp.builder" />
  <runtime>
    <run class="edu.umn.cs.melt.ide.imp.builders.Nature">
      <parameter name="builder" value="@LANG_NAME@_IDE.@LANG_NAME@.imp.builder" />
    </run>
  </runtime>
</extension>

<extension point="org.eclipse.core.resources.markers" id="@LANG_NAME@.imp.builder.problem" name="@LANG_NAME@ Error">
  <super type="org.eclipse.core.resources.problemmarker" />
  <persistent value="true" />
</extension>

<extension point="org.eclipse.ui.popupMenus">
  <objectContribution objectClass="org.eclipse.core.resources.IProject" adaptable="true" nameFilter="*" id="@LANG_NAME@.imp.projectContextMenu">

    <action
        label="Enable @LANG_NAME@ Builder"
        tooltip="Enable the @LANG_NAME@ builder for this project"
        id="@LANG_NAME@.imp.actions.enableNatureAction">
      <class class="edu.umn.cs.melt.ide.imp.builders.EnableNature">
        <parameter name="nature" value="@LANG_NAME@_IDE.imp.nature" />
      </class>
    </action>

${foldr(stringConcat, "", map((.pluginXmlActions), ideFuncDcls))}

  </objectContribution>
</extension>

<extension point="org.eclipse.imp.runtime.tokenColorer">
  <tokenColorer class="edu.umn.cs.melt.ide.imp.services.Colorer" language="@LANG_NAME@">
  </tokenColorer>
</extension>

<extension point="org.eclipse.ui.newWizards" id="@LANG_NAME@_IDE.wizards" name="@LANG_NAME@ Project Wizards">
  <wizard
      category="@LANG_NAME@_IDE.wizards.category/"
      class="edu.umn.cs.melt.ide.wizard.NewProjectWizard"
      id="@LANG_NAME@_IDE.wizard.newProject"
      name="New @LANG_NAME@ Project"
      finalPerspective="@LANG_NAME@_IDE.perspective"
      project="true">
  </wizard>
  
${foldr(stringConcat, "", map((.pluginXmlWizards), wizards))}

  <category
      id="@LANG_NAME@_IDE.wizards.category"
      name="@LANG_NAME@">
  </category>
</extension>

<extension point="org.eclipse.ui.perspectives">
  <perspective
      class="edu.umn.cs.melt.ide.eclipse.Perspective"
      id="@LANG_NAME@_IDE.perspective"
      name="@LANG_NAME@">
  </perspective>
</extension>

<extension point="org.eclipse.ui.propertyPages">
  <page
      class="edu.umn.cs.melt.ide.eclipse.property.MultiTabPropertyPage"
      id="@LANG_NAME@_IDE.buildConfig.propertyPage"
      name="@LANG_NAME@">
    <enabledWhen>
      <and>
        <instanceof value="org.eclipse.core.resources.IProject"/>
        <adapt type="org.eclipse.core.resources.IResource">
          <test property="org.eclipse.core.resources.projectNature"
                value="@LANG_NAME@_IDE.imp.nature">
          </test>
        </adapt>
      </and>
    </enabledWhen>
  </page>
</extension>

${foldr(stringConcat, "", map((.pluginXml), ideFuncDcls))}

</plugin>
""";
}

function newTabClass
String ::= tab::String
{
  return "new " ++ tab ++ "()";
}

