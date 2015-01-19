grammar silver:modification:impide;

import silver:translation:java:core only makeClassName;

{-- IdeSpec --}

synthesized attribute ideExtension :: String;
synthesized attribute ideParserSpec :: ParserSpec;
--fst:the type of function, such as "builder"; snd: the full qualified name of function 
synthesized attribute funcDcls :: [Pair<String String>] with ++ ;
synthesized attribute ideFunctions :: [IdeFunction];
synthesized attribute propDcls :: [IdeProperty] with ++ ;
synthesized attribute wizards :: [IdeWizardDcl] with ++;
synthesized attribute productInfo :: IdeProductInfo;
synthesized attribute pluginConfig :: PluginConfig;
synthesized attribute svIdeInterface :: String;

nonterminal IdeSpec with ideExtension, ideParserSpec, funcDcls, propDcls, wizards, productInfo, pluginConfig, ideFunctions, svIdeInterface;


abstract production ideSpec
top::IdeSpec ::= 
    ext::String ideFuncDcls::[IdeFunction] idePropDcls::[IdeProperty] wizards::[IdeWizardDcl]
    pspec::ParserSpec productInfo::IdeProductInfo pluginConfig::PluginConfig --TODO more?
{
  top.ideExtension = ext;
  top.ideParserSpec = pspec;
  top.funcDcls := foldr(append, [], map((.funcDcls), ideFuncDcls));
  top.ideFunctions = ideFuncDcls;
  top.propDcls := idePropDcls;
  top.wizards := wizards;
  top.productInfo = productInfo;
  top.pluginConfig = pluginConfig;
  top.svIdeInterface =
    template """
package @PKG_NAME@;

import ide.NIdeEnv;

import common.ConsCell;
import common.Node;
import common.StringCatter;

import core.NIOVal;
import core.Pioval;

import edu.umn.cs.melt.ide.eclipse.property.IPropertyPageTab;
import edu.umn.cs.melt.ide.silver.property.ui.IPropertyControlsProvider;
import edu.umn.cs.melt.ide.impl.SVDefault;

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
			${getTabClasses(pluginConfig.propertyTabs)}
		};
	}

${foldr(stringConcat, "", map((.svIdeInterface), ideFuncDcls))}
${foldr(stringConcat, "", map((.svIdeInterface), wizards))}
}
""";
}

{-- IdeProperty --}

nonterminal IdeProperty with propName, propType, optional, defaultVal, displayName;

synthesized attribute propName :: String;
synthesized attribute propType :: String;
synthesized attribute optional :: Boolean;
synthesized attribute defaultVal :: String;
synthesized attribute displayName :: String;

abstract production makeIdeProperty
top::IdeProperty ::= propName::String propType::String options::IdePropertyOptions
{
  top.propName = propName;
  top.propType = propType;
  top.optional = options.optional;
  top.defaultVal = options.defaultVal;
  top.displayName = if options.displayName == "" then propName else options.displayName;
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

nonterminal IdeFunction with funcDcls, svIdeInterface;

abstract production builderFunction
top::IdeFunction ::= fName::String
{
  top.funcDcls := [pair("builder", fName)];
  top.svIdeInterface =
    template """
	@Override
	public NIOVal build(ConsCell properties, NIdeEnv env, Object iotoken) {
		return (NIOVal)${makeClassName(fName)}.invoke(properties, env, iotoken);
	}
""";
}

abstract production postbuilderFunction
top::IdeFunction ::= fName::String
{
  top.funcDcls := [pair("postbuilder", fName)];
  top.svIdeInterface =
    template """
	@Override
	public NIOVal postbuild(ConsCell properties, NIdeEnv env, Object iotoken) {
		return (NIOVal)${makeClassName(fName)}.invoke(properties, env, iotoken);
	}
""";
}

abstract production exporterFunction
top::IdeFunction ::= fName::String
{
  top.funcDcls := [pair("exporter", fName)];
  top.svIdeInterface =
    template """
	@Override
	public NIOVal export(ConsCell properties, NIdeEnv env, Object iotoken) {
		return (NIOVal)${makeClassName(fName)}.invoke(properties, env, iotoken);
	}
""";
}

abstract production folderFunction
top::IdeFunction ::= fName::String
{
  top.funcDcls := [pair("folder", fName)];
  top.svIdeInterface =
    template """
	@Override
	public ConsCell getFolds(Node root) {
		return (ConsCell)${makeClassName(fName)}.invoke(root);
	}
""";
}


