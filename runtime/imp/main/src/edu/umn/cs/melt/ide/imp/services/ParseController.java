package edu.umn.cs.melt.ide.imp.services;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.parser.ParseControllerBase;
import org.eclipse.imp.parser.SimpleAnnotationTypeInfo;
import org.eclipse.imp.services.IAnnotationTypeInfo;
import org.eclipse.imp.services.ILanguageSyntaxProperties;

import common.Node;

import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;
import edu.umn.cs.melt.copper.runtime.logging.CopperSyntaxError;
import edu.umn.cs.melt.ide.copper.AdaptiveEnhancedParseTreeInnerNode;
import edu.umn.cs.melt.ide.copper.IEnhancedParseTreeNode;
import edu.umn.cs.melt.ide.copper.IToken;
import edu.umn.cs.melt.ide.copper.SourcePositionLocator;
import edu.umn.cs.melt.ide.impl.SVRegistry;

/**
 * Extends ParseControllerBase to use the default implementation of several
 * interface methods. The following variables are inherited:<br>
 * 	 protected ISourceProject fProject;<br>
 * 	 protected IPath fFilePath;<br>
 * 	 protected IMessageHandler handler;<br>
 * 	 protected Object fCurrentAst;<br>
 */
public class ParseController extends ParseControllerBase {
	
	private static DateFormat DATE_FORMAT = new SimpleDateFormat();
	
	/** Construct an instance of the parser. */
//	private @PARSER_NAME@ parser = new @PARSER_NAME@();

	private final SimpleAnnotationTypeInfo fSimpleAnnotationTypeInfo
		= new SimpleAnnotationTypeInfo();

	private SourcePositionLocator<IEnhancedParseTreeNode, IToken> locator;
	
	public ParseController() {
		super(SVRegistry.get().name());
		locator = new SourcePositionLocator<IEnhancedParseTreeNode, IToken>(this);
	}
	
	/**
	 * Return an instance of SimpleAnnotationTypeInfo
	 */
	@Override
	public IAnnotationTypeInfo getAnnotationTypeInfo() {
		return fSimpleAnnotationTypeInfo;
	}
	
	@Override
	public ISourcePositionLocator getSourcePositionLocator() {
		return locator;
	}

	@Override
	public ILanguageSyntaxProperties getSyntaxProperties() {
		return null;//TODO
	}
	
	@Override
	public Object parse(String input, IProgressMonitor monitor) {
		AdaptiveEnhancedParseTreeInnerNode<Node> result = null;
		boolean parsed = false;
		handler.clearMessages();
		
		try {
			Reader reader = new StringReader(input);
			result = SVRegistry.get().parse(reader, getPath().toFile().getName());
		    parsed = (result==null)?false:true;
		    if(!parsed)
		    	System.out.println("\nI didn't think parse result could be null?! \n");
		} catch (CopperSyntaxError e) {
			// We have a point, not an extent, so repeat start/end positions.
			handler.handleSimpleMessage(
				e.getMessage(), (int)e.getRealCharIndex(), (int)e.getRealCharIndex(),
				e.getVirtualColumn(), e.getVirtualColumn(),
				e.getVirtualLine(), e.getVirtualLine());
		} catch (CopperParserException e) {
			// Not sure how else to error here?
			handler.handleSimpleMessage(e.getMessage(), 0, 0, 0, 0, 1, 1);
		} catch (IOException e) {
			// TODO: this is a bit stupid
			e.printStackTrace();
		}
		
		String date = DATE_FORMAT.format(new Date()).toString();
		if(parsed){
			System.out.println(date + ": Parsed correctly.");
		} else {
			System.err.println(date + ": Parsing failed.");
		}
		
		fCurrentAst = result;
		return fCurrentAst;
	}

	//Delegate to auto-generated (enhanced) parser
	@Override
	public Iterator getTokenIterator(org.eclipse.jface.text.IRegion region) {
		return SVRegistry.get().getTokensForLastParse(region);
	}
	
}
