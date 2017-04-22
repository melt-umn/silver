package edu.umn.cs.melt.ide.copper.coloring;

import org.eclipse.jface.text.TextAttribute;
import common.Terminal;

public interface ITokenClassifier {

	TextAttribute getColoring(Terminal token);
	
}
