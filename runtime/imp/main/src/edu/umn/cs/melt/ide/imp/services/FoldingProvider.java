package edu.umn.cs.melt.ide.imp.services;

import java.util.HashMap;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.imp.services.base.FolderBase;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;

import common.ConsCell;
import common.DecoratedNode;
import common.Node;
import common.javainterop.ConsCellCollection;
import core.NLocation;

import edu.umn.cs.melt.ide.copper.AdaptiveEnhancedParseTreeInnerNode;
import edu.umn.cs.melt.ide.impl.SVInterface;
import edu.umn.cs.melt.ide.impl.SVRegistry;

public class FoldingProvider extends FolderBase implements IExecutableExtension {

	private String language;
	
	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		// This is deliberately not used yet, but I wanted to show how we can get info
		// out of plugin.xml about what language this is.
		// Right now, SVRegistry doesn't need a language, but perhaps in the future it should.
		language = config.getAttribute("language");
	}

	@Override
	protected void sendVisitorToAST(
			HashMap<Annotation, Position> newAnnotations,
			List<Annotation> annotations, Object _ast) {
		
		SVInterface sv = SVRegistry.get();
		
		// Unfortunately...
		AdaptiveEnhancedParseTreeInnerNode<Node> ast = 
				(AdaptiveEnhancedParseTreeInnerNode<Node>) _ast;

		ConsCell folds = sv.getFolds(ast.getLangSpecNode());
		
		for(NLocation loc : new ConsCellCollection<NLocation>(folds)) {
			DecoratedNode dloc = loc.decorate();
			
        	int startInd = (Integer)dloc.synthesized(core.Init.core_index__ON__core_Location);
            int endInd = (Integer)dloc.synthesized(core.Init.core_endIndex__ON__core_Location);

            makeAnnotation(startInd, endInd - startInd);
		}
	}

}
