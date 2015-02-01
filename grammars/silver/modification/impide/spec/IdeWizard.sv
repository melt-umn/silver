grammar silver:modification:impide:spec;

-- An IdeWizardDcl includes all the necessary information for generating a Wizard in IDE. 

synthesized attribute wizName :: String;
synthesized attribute wizProps :: [IdeProperty];

nonterminal IdeWizardDcl with wizName, wizProps, svIdeInterface, pluginXmlWizards;

{--
  func: the full name of stub generator, having signature String ::= [IdeProperty]
  props: a list of properties which the stub generator can access to
--}
abstract production newfileWizard
top::IdeWizardDcl ::= func::String props::[IdeProperty]
{
  top.wizName = "newfile"; -- newfile  display: new file
  top.wizProps = props;
  top.svIdeInterface = s"""
	@Override
	public IPropertyControlsProvider getNewFileProperties() {
		return new @PKG_NAME@.eclipse.wizard.${top.wizName}.PropertyControlsProvider();
	}
	@Override
	public StringCatter fileStub(ConsCell properties) {
		return (StringCatter)${makeClassName(func)}.invoke(properties);
	}
""";

  top.pluginXmlWizards = s"""
  <wizard
      category="@LANG_NAME@_IDE.wizards.category/"
      class="edu.umn.cs.melt.ide.wizard.NewSourceFileWizard"
      id="@LANG_NAME@.wizard.newSourceFile"
      name="New @LANG_NAME@ Source File">
  </wizard>
""";
}

