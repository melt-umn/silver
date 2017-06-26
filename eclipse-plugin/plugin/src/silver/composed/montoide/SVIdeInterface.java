
package silver.composed.montoide;

import java.io.IOException;
import java.io.File;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

import common.ConsCell;
import common.IOToken;
import common.Node;
import common.StringCatter;
import core.NIOVal;
import core.Pioval;

import org.eclipse.jface.text.IRegion;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.CoreException;

import edu.umn.cs.melt.ide.IdeMessage;
import edu.umn.cs.melt.ide.eclipse.property.IPropertyPageTab;
import edu.umn.cs.melt.ide.silver.property.ProjectProperties;
import edu.umn.cs.melt.ide.silver.property.Property;
import edu.umn.cs.melt.ide.silver.property.ui.IPropertyControlsProvider;
import edu.umn.cs.melt.ide.impl.SVDefault;
import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;
import edu.umn.cs.melt.ide.copper.coloring.ITokenClassifier;
import edu.umn.cs.melt.ide.imp.services.IdeParseResult;

import static common.javainterop.Util.indexOfSynOn;
import static common.Util.copyFile;
import static edu.umn.cs.melt.ide.util.Util.ant;

public class SVIdeInterface extends SVDefault {

	public SVIdeInterface() {}
@Override
	public String name() { return "Monto-Deciphered Language"; }
	@Override
	public String pluginId() { return "silver.composed.montoide"; }
	@Override
	public String markerErrorName() { return "silver.composed.montoide.builder.problem"; }
	@Override
	public String getNatureId() { return "silver.composed.montoide.nature"; }
	@Override
	public String fileExtension() { return "jmg"; } //TODO: THIS NEEDS TO BE FIXED RIGHT AWAY
												 //         NEEDS INFORMATION FROM MONTO
	@Override
	public IPropertyControlsProvider getProjectProperties() {
		return new silver.composed.montoide.eclipse.property.PropertyControlsProvider();
	}
	@Override
	public String getInitialProjectProperties() {
		return silver.composed.montoide.eclipse.wizard.newproject.PropertyGenerator.getAll();
	}
	@Override
	public IPropertyPageTab[] getPropertyTabs() {
		return new IPropertyPageTab[] {
			new edu.umn.cs.melt.ide.eclipse.property.TabCommons()
		};
	}
	@Override
	public ITokenClassifier getTokenClassifier() {
		return new silver.composed.montoide.imp.coloring.Parser_silver_composed_Default_svParse_TokenClassifier();
	}
	private silver.composed.Default.Parser_silver_composed_Default_svParse parser = new silver.composed.Default.Parser_silver_composed_Default_svParse();
	@Override
	public IdeParseResult<Node> parse(Reader input, String filename) throws CopperParserException, IOException {
		// In the long run, maybe we should have a getParser() rather than parse() so things could be concurrent... TODO
		synchronized(parser) {
			parser.reset();
			return new IdeParseResult<Node>((Node)parser.parse(input, filename), parser.getTokens());
		}
	}

	@Override
	public NIOVal build(IProject project, ConsCell properties, IOToken iotoken) {
		return (NIOVal)silver.composed.montoide.Panalyze.invoke(project, properties, iotoken);
	}

	@Override
	public NIOVal postbuild(IProject project, ConsCell properties, IOToken iotoken) {
		return (NIOVal)silver.composed.montoide.Pgenerate.invoke(project, properties, iotoken);
	}

	@Override
	public IdeMessage export(IProject project, ProjectProperties properties) {
		final String projPath = project.getLocation().toOSString();
		final String genPath = projPath + IPath.SEPARATOR + "bin";

		final String pkgName = getGrammarToCompile(properties).replace(":", ".");
		final String buildFile = genPath + "/build.xml";
		final String jarFile = genPath + "/" + pkgName + ".jar";
		final String targetFile = projPath + "/" + pkgName + ".jar";

		final boolean fileExists = new File(buildFile).isFile();

		if (!fileExists) {
			return new IdeMessage(IdeMessage.Severity.IDE_MSG_LV_ERROR,
						"build.xml doesn't exist. Has the project been successfully built before?");
		}

		ant(buildFile, "", "");
		final boolean jarExists = new File(jarFile).isFile();

		if (!jarExists) {
			return new IdeMessage(IdeMessage.Severity.IDE_MSG_LV_ERROR,
						"Ant failed to generate the jar.");
		}

		copyFile(jarFile, targetFile);
		try {
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (CoreException e) {
			// who knows
			e.printStackTrace();
		}

		IdeMessage error = null;
		return error;
	}

	@Override
	public ConsCell getFolds(Node root) {
		return (ConsCell)root.decorate().synthesized(indexOfSynOn("foldableRanges", root));
	}

	@Override
	public IPropertyControlsProvider getNewFileProperties() {
		return new silver.composed.montoide.eclipse.wizard.newfile.PropertyControlsProvider();
	}

	@Override
	public String fileStub(Map<String, String> properties) {
		String declared_grammar = properties.get("declared_grammar");
		return (declared_grammar == null) ? ""
			: "grammar " + declared_grammar + ";\n\n";
	}

	private String getGrammarToCompile(ProjectProperties properties)
	{
		Property grammar_to_compile = properties.get("grammar_to_compile");
		return grammar_to_compile != null
			? grammar_to_compile.getSValue()
			: "";
	}
}

