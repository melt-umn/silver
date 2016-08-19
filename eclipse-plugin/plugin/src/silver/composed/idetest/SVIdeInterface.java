
package silver.composed.idetest;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import common.ConsCell;
import common.Node;
import common.StringCatter;
import core.NIOVal;
import core.Pioval;

import org.eclipse.jface.text.IRegion;
import org.eclipse.core.resources.IProject;

import edu.umn.cs.melt.ide.eclipse.property.IPropertyPageTab;
import edu.umn.cs.melt.ide.silver.property.ui.IPropertyControlsProvider;
import edu.umn.cs.melt.ide.impl.SVDefault;
import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;
import edu.umn.cs.melt.ide.copper.coloring.ITokenClassifier;
import edu.umn.cs.melt.ide.imp.services.IdeParseResult;

public class SVIdeInterface extends SVDefault {

	public SVIdeInterface() {}
@Override
	public String name() { return "Silver"; }
	@Override
	public String pluginId() { return "silver.composed.idetest"; }
	@Override
	public String markerErrorName() { return "silver.composed.idetest.builder.problem"; }
	@Override
	public String getNatureId() { return "silver.composed.idetest.nature"; }
	@Override
	public String fileExtension() { return "sv"; }
	@Override
	public IPropertyControlsProvider getProjectProperties() {
		return new silver.composed.idetest.eclipse.property.PropertyControlsProvider();
	}
	@Override
	public String getInitialProjectProperties() {
		return silver.composed.idetest.eclipse.wizard.newproject.PropertyGenerator.getAll();
	}
	@Override
	public IPropertyPageTab[] getPropertyTabs() {
		return new IPropertyPageTab[] {
			new edu.umn.cs.melt.ide.eclipse.property.TabCommons()
		};
	}
	@Override
	public ITokenClassifier getTokenClassifier() {
		return new silver.composed.idetest.imp.coloring.Parser_silver_composed_Default_svParse_TokenClassifier();
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
	public NIOVal build(IProject project, ConsCell properties, common.IOToken iotoken) {
		return (NIOVal)silver.composed.idetest.Panalyze.invoke(project, properties, iotoken);
	}

	@Override
	public NIOVal postbuild(IProject project, ConsCell properties, common.IOToken iotoken) {
		return (NIOVal)silver.composed.idetest.Pgenerate.invoke(project, properties, iotoken);
	}

	@Override
	public NIOVal export(IProject project, ConsCell properties, common.IOToken iotoken) {
		return (NIOVal)silver.composed.idetest.Pexport.invoke(project, properties, iotoken);
	}

	@Override
	public ConsCell getFolds(Node root) {
		return (ConsCell)silver.composed.idetest.Pfold.invoke(root);
	}


	@Override
	public IPropertyControlsProvider getNewFileProperties() {
		return new silver.composed.idetest.eclipse.wizard.newfile.PropertyControlsProvider();
	}
	@Override
	public StringCatter fileStub(ConsCell properties) {
		return (StringCatter)silver.composed.idetest.PgetStubForNewFile.invoke(properties);
	}

}
