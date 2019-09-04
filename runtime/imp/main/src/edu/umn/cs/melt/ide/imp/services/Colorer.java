package edu.umn.cs.melt.ide.imp.services;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;

import edu.umn.cs.melt.ide.copper.coloring.ITokenClassifier;
import edu.umn.cs.melt.ide.impl.SVRegistry;

import common.Terminal;

public class Colorer extends TokenColorerBase implements ITokenColorer {

	private ITokenClassifier decider = SVRegistry.get().getTokenClassifier();
	
	public Colorer() {
		super();
	}

	public TextAttribute getColoring(IParseController controller, Object o) {
		if (o == null)
			return null;
		
		Terminal token = (Terminal) o;
		
		TextAttribute attr = decider.getColoring(token);
		
		return (attr!=null)?attr:super.getColoring(controller, token);

	}

	public IRegion calculateDamageExtent(IRegion seed) {
		return seed;
	}
}
