package edu.umn.cs.melt.ide.copper.coloring;

import org.eclipse.jface.text.TextAttribute;
import edu.umn.cs.melt.ide.copper.IToken;

public interface ICopperTokenClassifier {

	TextAttribute getColoring(IToken token);
	
}
