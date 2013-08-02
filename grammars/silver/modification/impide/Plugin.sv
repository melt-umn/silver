grammar silver:modification:impide;

-- For structured declaration that can be translated to Eclipse plugin.xml file --

synthesized attribute xmlOutput :: String;

inherited attribute indentLevel :: Integer;

nonterminal PluginDefinition with xmlOutput;

nonterminal PluginElement with xmlOutput, indentLevel;

synthesized attribute hasExporter :: Boolean;
synthesized attribute hasSourceLinker :: Boolean;
synthesized attribute hasCodeFolder :: Boolean;
synthesized attribute propertyTabs :: [Pair<String String>];--Pair<"tab name" "class name">
nonterminal PluginConfig with hasExporter, hasSourceLinker, hasCodeFolder, propertyTabs;

abstract production pluginConfig
top::PluginConfig ::= hasExporter::Boolean hasSourceLinker::Boolean hasCodeFolder::Boolean propertyTabs :: [Pair<String String>]
{
    top.hasExporter = hasExporter;
    top.hasSourceLinker = hasSourceLinker;
    top.hasCodeFolder = hasCodeFolder;
    top.propertyTabs = propertyTabs;
}

abstract production pluginDefinition
top::PluginDefinition ::= header::String footer::String elements::[PluginElement]
{
    top.xmlOutput =
        header ++ "\n" ++
        outputXml(elements, 1) ++
        footer ++ "\n";
}

abstract production pluginStructuredElement
top::PluginElement ::= header::String footer::String content::String elements::[PluginElement]
{
    local nextIndentLevel::Integer = top.indentLevel+1;
    local leadingIndent::String = outputIndent(top.indentLevel);
    top.xmlOutput = 
        leadingIndent ++ header ++ "\n" ++
        outputXml(elements, nextIndentLevel) ++ 
        (if(content!="") then outputIndent(nextIndentLevel) ++ content ++ "\n" else "") ++ 
        leadingIndent ++ footer ++ "\n";
}

abstract production pluginUnstructuredElement
top::PluginElement ::= contents::[String]
{
    local leadingIndent::String = outputIndent(top.indentLevel);
    top.xmlOutput = outputContent(contents, leadingIndent);
}

function outputIndent
String ::= indent::Integer
{
    return 
      if indent>0
      then "\t" ++ outputIndent(indent-1)
      else "";
}

function outputContent
String ::= contents::[String] indent::String
{
    return 
      if !null(contents)
      then indent ++ head(contents) ++ "\n" ++ outputContent(tail(contents), indent)
      else "";
}

function outputXml
String ::= elements::[PluginElement] indent::Integer
{
    local hd::PluginElement = head(elements);
    hd.indentLevel = indent;
    return
      if !null(elements)
      then hd.xmlOutput ++ outputXml(tail(elements), indent)
      else "";
}

-- Plugin and nested extensions declaration --

function makePlugin
PluginDefinition ::= config::PluginConfig
{
    return pluginDefinition(
        --header
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"++
            "<?eclipse version=\"3.0\"?>\n"++
            "<!--\n"++
            "Variables used:\n"++
            "  PKG_NAME\n"++
            "  LANG_NAME\n"++
            "  SOURCE_EXT\n"++
            "-->\n"++
            "<plugin>",
        --footer
            "</plugin>",
        --nested elements
            makeExtensions(config)
        );
}

function makeExtensions
[PluginElement] ::= config::PluginConfig
{
    return 
    [
        pluginUnstructuredElement(
            [
                "<extension point=\"org.eclipse.imp.runtime.languageDescription\">",
                "  <language extensions=\"@SOURCE_EXT@\" description=\"nothing here\" natureID=\"@LANG_NAME@_IDE.imp.nature\" language=\"@LANG_NAME@\">",
                "  </language>",
                "</extension>"
            ]
        ),
        pluginUnstructuredElement(
            [
                "<extension id=\"@LANG_NAME@_IDE.parserWrapper\" name=\"@LANG_NAME@ Parser Wrapper\" point=\"org.eclipse.imp.runtime.parser\">",
                "  <parserWrapper class=\"@PKG_NAME@.imp.controller.@LANG_NAME@ParseController\" language=\"@LANG_NAME@\">",
                "  </parserWrapper>",
                "</extension>"
            ]
        ),
        pluginUnstructuredElement(
            [
               "<extension id=\"@LANG_NAME@.imp.builder\" name=\"@LANG_NAME@ builder\" point=\"org.eclipse.core.resources.builders\">",
               "  <builder hasNature=\"true\">",
               "    <run class=\"@PKG_NAME@.imp.builders.@LANG_NAME@Builder\">",
               "      <parameter name=\"foo\" value=\"bar\">",
               "      </parameter>",
               "    </run>",
               "  </builder>",
               "</extension>"
            ]
        ),
        pluginUnstructuredElement(
            [
               "<extension id=\"imp.nature\" name=\"@LANG_NAME@ Nature\" point=\"org.eclipse.core.resources.natures\">",
               "  <builder id=\"@LANG_NAME@_IDE.@LANG_NAME@.imp.builder\"></builder>",
               "  <runtime>",
               "     <run class=\"@PKG_NAME@.imp.builders.@LANG_NAME@Nature\"></run>",
               "  </runtime>",
               "</extension>"
            ]
        ),    
        pluginUnstructuredElement(
            [
               "<extension id=\"@LANG_NAME@.imp.builder.problem\" name=\"@LANG_NAME@ Error\" point=\"org.eclipse.core.resources.markers\">",
               "   <super type=\"org.eclipse.core.resources.problemmarker\"></super>",
               "   <persistent value=\"true\"></persistent>",
               "</extension>"
            ]
        ), 
        pluginUnstructuredElement(
            [
               "<extension id=\"@LANG_NAME@.imp.builder.problem\" name=\"@LANG_NAME@ Error\" point=\"org.eclipse.core.resources.markers\">",
               "   <super type=\"org.eclipse.core.resources.problemmarker\"></super>",
               "   <persistent value=\"true\"></persistent>",
               "</extension>"
            ]
        ), 
        pluginStructuredElement(
            "<extension point=\"org.eclipse.ui.popupMenus\">",
            "</extension>",
            "",
            [
                pluginStructuredElement(
                    "<objectContribution objectClass=\"org.eclipse.core.resources.IProject\" adaptable=\"true\" nameFilter=\"*\" id=\"@LANG_NAME@.imp.projectContextMenu\">",
                    "</objectContribution>",
                    "",
                    [
                        pluginUnstructuredElement(
                            [
                                "<action",
                                "      label=\"Enable @LANG_NAME@ Builder\"",
                                "      tooltip=\"Enable the @LANG_NAME@ builder for this project\"",
                                "      class=\"@PKG_NAME@.imp.actions.Enable@LANG_NAME@Nature\"",
                                "      id=\"@LANG_NAME@.imp.actions.enableNatureAction\">",
                                "</action>"
                            ]
                        )
                    ]
                    ++
                    if(config.hasExporter) then [
                        pluginUnstructuredElement(
                            [
                                "<action",
                                "      label=\"Export as @LANG_NAME@ target\"",
                                "      tooltip=\"Export the project as @LANG_NAME@ distributable\"",
                                "      class=\"@PKG_NAME@.imp.actions.Export@LANG_NAME@\"",
                                "      id=\"@LANG_NAME@.imp.actions.exportAction\">",
                                "</action>"
                            ]
                        )
                    ] else []
                )
            ]
        ),
        pluginUnstructuredElement(
            [
               "<extension point=\"org.eclipse.imp.runtime.tokenColorer\">",
               "   <tokenColorer class=\"@PKG_NAME@.imp.coloring.Colorer\" language=\"@LANG_NAME@\">",
               "   </tokenColorer>",
               "</extension>"
            ]
        ), 
        pluginUnstructuredElement(
            [
               "<extension",
               "   id=\"@LANG_NAME@_IDE.wizards\"",
               "   name=\"@LANG_NAME@ Project Wizards\"",
               "   point=\"org.eclipse.ui.newWizards\">",
               "   <wizard",
               "      category=\"@LANG_NAME@_IDE.wizards.category/\"",
               "      class=\"@PKG_NAME@.eclipse.wizard.NewProjectWizard\"",
               "      id=\"@LANG_NAME@_IDE.wizard.newProject\"",
               "      name=\"New @LANG_NAME@ Project\"",
               "      finalPerspective=\"@LANG_NAME@_IDE.perspective\"",
               "      project=\"true\">",
               "   </wizard>",
               "   <category",
               "      id=\"@LANG_NAME@_IDE.wizards.category\"",
               "      name=\"@LANG_NAME@\">",
               "   </category>",
               "</extension>"
            ]
        ), 
        pluginUnstructuredElement(
            [
               "<extension",
               "   point=\"org.eclipse.ui.startup\">",
               "   <startup",
               "       class=\"@PKG_NAME@.StartupHook\">",
               "   </startup>",
               "</extension>"
            ]
        ), 
        pluginUnstructuredElement(
            [
               "<extension",
               "   point=\"org.eclipse.ui.perspectives\">",
               "   <perspective",
               "      class=\"@PKG_NAME@.eclipse.perspective.@LANG_NAME@Perspective\"",
               "      id=\"@LANG_NAME@_IDE.perspective\"",
               "      name=\"@LANG_NAME@\">",
               "   </perspective>",
               "</extension>"
            ]
        )
    ]

    ++
    if(!null(config.propertyTabs)) then [
        pluginUnstructuredElement(
            [
                "<extension point=\"org.eclipse.ui.propertyPages\">",
                "  <page",
                "    class=\"@PKG_NAME@.eclipse.property.MultiTabPropertyPage\"",
                "    id=\"@LANG_NAME@_IDE.buildConfig.propertyPage\"",
                "    name=\"@LANG_NAME@\">",
                "    <enabledWhen>",
                "      <and>",
                "        <instanceof value=\"org.eclipse.core.resources.IProject\"/>",
                "	     <adapt type=\"org.eclipse.core.resources.IResource\">",
                "	       <test property=\"org.eclipse.core.resources.projectNature\"",
                "	         value=\"@LANG_NAME@_IDE.imp.nature\">",
                "	       </test>",
                "	     </adapt>",
                "	   </and>", 
                "    </enabledWhen>",
                "  </page>",
                "</extension>"
            ]
        )
    ] else []

    ++
    if(config.hasCodeFolder) then [
        pluginUnstructuredElement(
            [
                "<extension",
                "   point=\"org.eclipse.imp.runtime.foldingUpdater\">",
                "   <foldingUpdater",
                "      class=\"@PKG_NAME@.imp.folding.@LANG_NAME@FoldingUpdater\"",
                "      language=\"@LANG_NAME@\">",
                "   </foldingUpdater>",
                "</extension>"
            ]
        )
    ] else []

    ;
}

