grammar silver:modification:impide;

-- An IdeWizardDcl includes all the necessary information for generating a Wizard in IDE. 

synthesized attribute wizName :: String;
synthesized attribute wizDisplay :: String;
synthesized attribute wizFunc :: String;
synthesized attribute wizProps :: [IdeProperty];

nonterminal IdeWizardDcl with wizName, wizDisplay, wizFunc, wizProps;

{--
  name: name of this wizard. For package/class.
  display: name of this wizard. For display.
  func: the full name of stub generator, having signature String ::= [IdeProperty]
  props: a list of properties which the stub generator can access to
--}
abstract production makeNewWizardDcl
top::IdeWizardDcl ::= name::String display::String func::String props::[IdeProperty]
{
  top.wizName = name;
  top.wizDisplay = display;
  top.wizFunc = func;
  top.wizProps = props;
}
