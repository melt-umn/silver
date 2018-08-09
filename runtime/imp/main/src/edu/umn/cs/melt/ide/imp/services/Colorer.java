package edu.umn.cs.melt.ide.imp.services;

import java.util.HashMap;

import org.eclipse.imp.parser.IParseController;
import org.eclipse.imp.services.ITokenColorer;
import org.eclipse.imp.services.base.TokenColorerBase;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.swt.widgets.Display;

import edu.umn.cs.melt.ide.copper.coloring.TextAttributeProvider;

import common.Terminal;

public class Colorer extends TokenColorerBase implements ITokenColorer, IExecutableExtension {

	private TextAttribute[] attributes;
	private HashMap<String, Integer> classColorings;
	
	public Colorer() {
		super();
	}

	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		// <font name="n" r="1" g="1" b="1" bold="false" italic="true" />
		IConfigurationElement fonts[] = config.getChildren("font");
		this.attributes = new TextAttribute[fonts.length];
		HashMap<String, Integer> fontIndexes = new HashMap<String, Integer>();
		Display display = Display.getDefault();
		
		for(int i = 0; i < fonts.length; i++) {
			IConfigurationElement elem = fonts[i];
			String name = elem.getAttribute("name");
			int r = Integer.parseInt(elem.getAttribute("r"));
			int g = Integer.parseInt(elem.getAttribute("g"));
			int b = Integer.parseInt(elem.getAttribute("b"));
			boolean bold = Boolean.parseBoolean(elem.getAttribute("bold"));
			boolean italic = Boolean.parseBoolean(elem.getAttribute("italic"));
			
			this.attributes[i] = TextAttributeProvider.getAttribute(display, r, g, b, bold, italic);
			fontIndexes.put(name, i);
			System.out.println("Font " + name + " " + i);
		}
		
		classColorings = new HashMap<String, Integer>();
		
		// <coloring lexerclass="sv:name" font="fontname" />
		for(IConfigurationElement elem : config.getChildren("coloring")) {
			String lexerclass = elem.getAttribute("lexerclass");
			String font = elem.getAttribute("font");
			int attribute_index = fontIndexes.get(font);
			
			classColorings.put(lexerclass, attribute_index);
			System.out.println("Coloring " + lexerclass + " as " + font + " is " + attribute_index);
		}
	}

	public TextAttribute getColoring(IParseController controller, Object o) {
		if (o == null)
			return null;
		
		Terminal token = (Terminal) o;
		
		for(String lc : token.getLexerClasses()) {
			Integer i = this.classColorings.get(lc);
			if(i != null) {
				return this.attributes[i];
			}
		}
		
		return super.getColoring(controller, token);

	}

	public IRegion calculateDamageExtent(IRegion seed) {
		return seed;
	}
}
