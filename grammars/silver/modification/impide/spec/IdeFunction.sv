grammar silver:modification:impide:spec;

nonterminal IdeFunctions with package, visibleName, implang, bundle, svIdeInterface, pluginXml, pluginXmlActions;

abstract production consIdeFunction
top::IdeFunctions ::= h::IdeFunction  t::IdeFunctions
{
  top.svIdeInterface = h.svIdeInterface ++ t.svIdeInterface;
  top.pluginXml = h.pluginXml ++ t.pluginXml;
  top.pluginXmlActions = h.pluginXmlActions ++ t.pluginXmlActions;
}

abstract production nilIdeFunction
top::IdeFunctions ::=
{
  top.svIdeInterface = "";
  top.pluginXml = "";
  top.pluginXmlActions = "";
}

nonterminal IdeFunction with package, visibleName, implang, bundle, svIdeInterface, pluginXml, pluginXmlActions;

aspect default production
top::IdeFunction ::=
{
  top.svIdeInterface = "";
  top.pluginXml = "";
  top.pluginXmlActions = "";
}

abstract production builderFunction
top::IdeFunction ::= fName::String
{
  top.svIdeInterface =
    s"""
	@Override
	public NIOVal build(ConsCell properties, NIdeEnv env, Object iotoken) {
		return (NIOVal)${makeClassName(fName)}.invoke(properties, env, iotoken);
	}
""";
}

abstract production postbuilderFunction
top::IdeFunction ::= fName::String
{
  top.svIdeInterface =
    s"""
	@Override
	public NIOVal postbuild(ConsCell properties, NIdeEnv env, Object iotoken) {
		return (NIOVal)${makeClassName(fName)}.invoke(properties, env, iotoken);
	}
""";
}

abstract production exporterFunction
top::IdeFunction ::= fName::String
{
  top.svIdeInterface = s"""
	@Override
	public NIOVal export(ConsCell properties, NIdeEnv env, Object iotoken) {
		return (NIOVal)${makeClassName(fName)}.invoke(properties, env, iotoken);
	}
""";
  
  top.pluginXmlActions = s"""
    <action
        label="Export as ${top.visibleName} target"
        tooltip="Export the project as ${top.visibleName} distributable"
        id="${top.bundle}.${extid_action_export}">
      <class class="edu.umn.cs.melt.ide.imp.builders.Exporter">
        <parameter name="name" value="${top.visibleName}" />
      </class>
    </action>
""";
}

abstract production folderFunction
top::IdeFunction ::= fName::String
{
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
      language="${top.implang}">
  </foldingUpdater>
</extension>
""";
}

