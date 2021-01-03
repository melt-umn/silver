grammar silver:compiler:modification:impide:spec;

nonterminal IdeFunctions with package, visibleName, implang, bundle, pluginXml, pluginXmlActions, pluginXmlBuilders, markerFullName;

synthesized attribute pluginXmlBuilders :: String;
autocopy attribute markerFullName :: String;

abstract production consIdeFunction
top::IdeFunctions ::= h::IdeFunction  t::IdeFunctions
{
  top.pluginXml = h.pluginXml ++ t.pluginXml;
  top.pluginXmlActions = h.pluginXmlActions ++ t.pluginXmlActions;
  top.pluginXmlBuilders = h.pluginXmlBuilders ++ t.pluginXmlBuilders;
}

abstract production nilIdeFunction
top::IdeFunctions ::=
{
  top.pluginXml = "";
  top.pluginXmlActions = "";
  top.pluginXmlBuilders = "";
}

nonterminal IdeFunction with package, visibleName, implang, bundle, pluginXml, pluginXmlActions, pluginXmlBuilders, markerFullName;

aspect default production
top::IdeFunction ::=
{
  top.pluginXml = "";
  top.pluginXmlActions = "";
  top.pluginXmlBuilders = "";
}

abstract production builderFunction
top::IdeFunction ::= fName::String
{
  top.pluginXmlBuilders =
    s"""
      <parameter name="silver_build" value="${fName}" />""";
}

abstract production postbuilderFunction
top::IdeFunction ::= fName::String
{
  top.pluginXmlBuilders =
    s"""
      <parameter name="silver_postbuild" value="${fName}" />""";
}

abstract production exporterFunction
top::IdeFunction ::= fName::String
{
  top.pluginXmlActions = s"""
    <action
        label="Export as ${top.visibleName} target"
        tooltip="Export the project as ${top.visibleName} distributable"
        id="${top.bundle}.${extid_action_export}">
      <class class="edu.umn.cs.melt.ide.imp.builders.Exporter">
        <parameter name="name" value="${top.visibleName}" />
        <parameter name="markerName" value="${top.markerFullName}" />
        <parameter name="silver_export" value="${fName}" />
      </class>
    </action>
""";
}

abstract production folderFunction
top::IdeFunction ::= fName::String
{
  top.pluginXml = s"""
<extension point="org.eclipse.imp.runtime.foldingUpdater">
  <foldingUpdater
      class="edu.umn.cs.melt.ide.imp.services.FoldingProvider"
      language="${top.implang}">
    <silvercall function="${fName}" />
  </foldingUpdater>
</extension>
""";
}

