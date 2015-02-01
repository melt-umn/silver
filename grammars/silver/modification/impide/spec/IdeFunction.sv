grammar silver:modification:impide:spec;

nonterminal IdeFunction with svIdeInterface, pluginXml, pluginXmlActions;

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
  top.pluginXml = "";
  top.pluginXmlActions = "";
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
  top.pluginXml = "";
  top.pluginXmlActions = "";
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

