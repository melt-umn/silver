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
import common.OriginContext;
import common.javainterop.ConsCellCollection;
import silver.core.NLocation;

import edu.umn.cs.melt.ide.util.ReflectedCall;

/**
 * Computes a set of foldable regions in the IDE.
 * Example configuration:
    <extension point="org.eclipse.imp.runtime.foldingUpdater">
      <foldingUpdater
          class="edu.umn.cs.melt.ide.imp.services.FoldingProvider"
          language="Silver">
        <silvercall function="silver:composed:idetest:fold" />
      </foldingUpdater>
    </extension>
 *
 * The function (above `silver:composed:idetest:fold`) should have
 * Silver type `[Location] ::= ASTRoot`.
 */
public class FoldingProvider extends FolderBase implements IExecutableExtension {

	private String language;
	private ReflectedCall<ConsCell> silvercall;
	
	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		// This is deliberately not used yet, but I wanted to show how we can get info
		// out of plugin.xml about what language this is.
		// Right now, SVRegistry doesn't need a language, but perhaps in the future it should.
		language = config.getAttribute("language");
		
		for(IConfigurationElement elem : config.getChildren("silvercall")) {
			silvercall = new ReflectedCall<ConsCell>(elem.getAttribute("function"), 1);
			// Do something smarter with error handling later...
			break;
		}
	}

	@Override
	protected void sendVisitorToAST(
			HashMap<Annotation, Position> newAnnotations,
			List<Annotation> annotations, Object _ast) {
		
               ConsCell folds = silvercall.invoke(new Object[]{OriginContext.FFI_CONTEXT, _ast});
		
		for(NLocation loc : new ConsCellCollection<NLocation>(folds)) {
			DecoratedNode dloc = loc.decorate();
			
			int startInd = (Integer)dloc.synthesized(silver.core.Init.core_index__ON__core_Location);
			int endInd = (Integer)dloc.synthesized(silver.core.Init.core_endIndex__ON__core_Location);

			makeAnnotation(startInd, endInd - startInd);
		}
	}

}
