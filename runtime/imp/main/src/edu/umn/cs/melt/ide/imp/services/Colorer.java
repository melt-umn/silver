package edu.umn.cs.melt.ide.imp.services;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;

import edu.umn.cs.melt.ide.copper.CopperToken;
import edu.umn.cs.melt.ide.copper.IToken;
import edu.umn.cs.melt.ide.copper.coloring.CopperTextAttributeDecider;
import edu.umn.cs.melt.ide.impl.SVRegistry;

public class Colorer extends TokenColorerBase implements ITokenColorer {

	private CopperTextAttributeDecider decider = SVRegistry.get().getColorDecider();
	
	public Colorer() {
		super();
	}

	public TextAttribute getColoring(IParseController controller, Object o) {
		if (o == null)
			return null;
		
		IToken token = (CopperToken) o;
		
		TextAttribute attr = decider.getAddTextAttribute(token);
		
		return (attr!=null)?attr:super.getColoring(controller, token);

	}

	public IRegion calculateDamageExtent(IRegion seed) {
		return seed;
	}
}
