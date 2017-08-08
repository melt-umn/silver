package edu.umn.cs.melt.ide.imp.services;

import java.util.HashMap;

import edu.umn.cs.melt.ide.copper.coloring.ITokenClassifier;
import edu.umn.cs.melt.ide.copper.coloring.TextAttributeProvider;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.swt.widgets.Display;

import common.Terminal;

public class JasonTokenClassifier implements ITokenClassifier {
	
	static Display display = Display.getDefault();

	// if the word is 'jason', gives a color
	public TextAttribute getColoring(Terminal token) {
		
		String lex = token.lexeme.toString();

		if (lex != "jason") {
			return TextAttributeProvider.getAttribute(display, 123, 0, 82, true, false);
		}

		return null;
	}
}
