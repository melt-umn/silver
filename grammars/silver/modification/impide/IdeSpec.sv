grammar silver:modification:impide;

import silver:translation:java:core only makeClassName, makeParserName;

{-- IdeSpec --}

synthesized attribute ideExtension :: String;
synthesized attribute ideParserSpec :: ParserSpec;
--fst:the type of function, such as "builder"; snd: the full qualified name of function 
synthesized attribute funcDcls :: [Pair<String String>] with ++ ;
synthesized attribute ideFunctions :: [IdeFunction];
synthesized attribute propDcls :: [IdeProperty] with ++ ;
synthesized attribute wizards :: [IdeWizardDcl] with ++;
synthesized attribute svIdeInterface :: String;
synthesized attribute pluginXml :: String;
synthesized attribute pluginXmlActions :: String;
synthesized attribute pluginXmlWizards :: String;
synthesized attribute pluginParserClass :: String;
synthesized attribute pluginGrammar :: String;
synthesized attribute ideName :: String;
synthesized attribute ideVersion :: String;

nonterminal IdeSpec with ideExtension, ideParserSpec, funcDcls, propDcls, wizards, ideFunctions, svIdeInterface, pluginXml, pluginParserClass, pluginGrammar, ideName, ideVersion;


abstract production ideSpec
top::IdeSpec ::= 
    grammarName::String ideName::String ideVersion::String
    ext::String ideFuncDcls::[IdeFunction] idePropDcls::[IdeProperty] wizards::[IdeWizardDcl]
    pspec::ParserSpec
{
  top.ideName = ideName;
  top.ideVersion = ideVersion;
  top.pluginGrammar = grammarName;
  top.ideExtension = ext;
  top.ideParserSpec = pspec;
  top.funcDcls := foldr(append, [], map((.funcDcls), ideFuncDcls));
  top.ideFunctions = ideFuncDcls;
  top.propDcls := idePropDcls;
  top.wizards := wizards;
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

<extension id="@LANG_NAME@_IDE.parserWrapper" name="@LANG_NAME@ Parser Wrapper" point="org.eclipse.imp.runtime.parser">
  <parserWrapper class="edu.umn.cs.melt.ide.imp.services.ParseController" language="@LANG_NAME@">
  </parserWrapper>
</extension>

<extension id="@LANG_NAME@.imp.builder" name="@LANG_NAME@ builder" point="org.eclipse.core.resources.builders">
  <builder hasNature="true">
    <run class="edu.umn.cs.melt.ide.imp.builders.Builder">
    </run>
  </builder>
</extension>

<extension id="imp.nature" name="@LANG_NAME@ Nature" point="org.eclipse.core.resources.natures">
  <builder id="@LANG_NAME@_IDE.@LANG_NAME@.imp.builder" />
  <runtime>
    <run class="edu.umn.cs.melt.ide.imp.builders.Nature">
      <parameter name="builder" value="@LANG_NAME@_IDE.@LANG_NAME@.imp.builder" />
    </run>
  </runtime>
</extension>

<extension id="@LANG_NAME@.imp.builder.problem" name="@LANG_NAME@ Error" point="org.eclipse.core.resources.markers">
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

<extension id="@LANG_NAME@_IDE.wizards" name="@LANG_NAME@ Project Wizards" point="org.eclipse.ui.newWizards">
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

<extension
    point="org.eclipse.ui.perspectives">
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

</plugin>
""";
}

function newTabClass
String ::= tab::String
{
  return "new " ++ tab ++ "()";
}



{-- IdeProperty --}

nonterminal IdeProperty with propName, propType, optional, defaultVal, displayName, controlJavaTranslation, generatorJavaTranslation;

synthesized attribute propName :: String;
synthesized attribute propType :: String;
synthesized attribute optional :: Boolean;
synthesized attribute defaultVal :: String;
synthesized attribute displayName :: String;
synthesized attribute controlJavaTranslation :: String;
synthesized attribute generatorJavaTranslation :: String;

abstract production makeIdeProperty
top::IdeProperty ::= propName::String propType::String options::IdePropertyOptions
{
  top.propName = propName;
  top.propType = propType;
  top.optional = options.optional;
  top.defaultVal = options.defaultVal;
  top.displayName = if options.displayName == "" then propName else options.displayName;
  top.controlJavaTranslation =
    s"""    controls.add(new ${getConstructorByType(propType)}(panel, "${propName}", "${top.displayName}", "${top.defaultVal}", ${if top.optional then "false" else "true"}));
""";
  -- TODO: honestly, we ought to just build this whole string statically, and do escaping here, too.
  top.generatorJavaTranslation =
    s"""    sb.append("${propName}/${propType} = ");sb.append(escape("${top.defaultVal}"));sb.append("\n");
""";
}
-- TODO: remove and replace by an actualy PropType nonterminal, with this as a translation
function getConstructorByType
String ::= propType :: String
{
  return if propType == "string" then "TextPropertyControl"
    else if propType == "path" then "PathPropertyControl"
    else if propType == "url" then "URLPropertyControl"
    else -- propType == "integer" 
    "IntegerPropertyControl";
}

{-- Color --}

nonterminal Color with r, g, b;

synthesized attribute r :: Integer;
synthesized attribute g :: Integer;
synthesized attribute b :: Integer;

abstract production makeColor
top::Color ::= r::Integer g::Integer b::Integer
{
  top.r = r;
  top.g = g;
  top.b = b;
}

{-- Font --}

nonterminal Font with color, isBold, isItalic;

synthesized attribute color :: Color;
synthesized attribute isBold :: Boolean;
synthesized attribute isItalic :: Boolean;

abstract production font
top::Font ::= color::Color isBold::Boolean isItalic::Boolean
{
  top.color = color;
  top.isBold = isBold;
  top.isItalic = isItalic;
}


{-- IdeFunctions --}

nonterminal IdeFunction with funcDcls, svIdeInterface, pluginXml, pluginXmlActions;

abstract production builderFunction
top::IdeFunction ::= fName::String
{
  top.funcDcls := [pair("builder", fName)];
  top.svIdeInterface =
    s"""
	@Override
	public NIOVal build(ConsCell properties, NIdeEnv env, Object iotoken) {
		return (NIOVal)${makeClassName(fName)}.invoke(properties, env, iotoken);
	}
""";
  top.pluginXml = "";
  top.pluginXmlActions = "";
}

abstract production postbuilderFunction
top::IdeFunction ::= fName::String
{
  top.funcDcls := [pair("postbuilder", fName)];
  top.svIdeInterface =
    s"""
	@Override
	public NIOVal postbuild(ConsCell properties, NIdeEnv env, Object iotoken) {
		return (NIOVal)${makeClassName(fName)}.invoke(properties, env, iotoken);
	}
""";
  top.pluginXml = "";
  top.pluginXmlActions = "";
}

abstract production exporterFunction
top::IdeFunction ::= fName::String
{
  top.funcDcls := [pair("exporter", fName)];
  top.svIdeInterface = s"""
	@Override
	public NIOVal export(ConsCell properties, NIdeEnv env, Object iotoken) {
		return (NIOVal)${makeClassName(fName)}.invoke(properties, env, iotoken);
	}
""";
  
  top.pluginXmlActions = s"""
    <action
        label="Export as @LANG_NAME@ target"
        tooltip="Export the project as @LANG_NAME@ distributable"
        id="@LANG_NAME@.imp.actions.exportAction">
      <class class="edu.umn.cs.melt.ide.imp.builders.Exporter">
        <parameter name="name" value="@LANG_NAME@" />
      </class>
    </action>
""";
  top.pluginXml = "";
}

abstract production folderFunction
top::IdeFunction ::= fName::String
{
  top.funcDcls := [pair("folder", fName)];
  top.svIdeInterface = s"""
	@Override
	public ConsCell getFolds(Node root) {
		return (ConsCell)${makeClassName(fName)}.invoke(root);
	}
""";

  top.pluginXml = s"""
<extension point="org.eclipse.imp.runtime.foldingUpdater">
  <foldingUpdater
      class="edu.umn.cs.melt.ide.imp.services.FoldingProvider"
      language="@LANG_NAME@">
  </foldingUpdater>
</extension>
""";
  top.pluginXmlActions = "";
}


