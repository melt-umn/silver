package edu.umn.cs.melt.ide.imp.services;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.parser.ParseControllerBase;
import org.eclipse.imp.parser.SimpleAnnotationTypeInfo;
import org.eclipse.imp.services.IAnnotationTypeInfo;
import org.eclipse.imp.services.ILanguageSyntaxProperties;
import org.eclipse.jface.text.IRegion;

import common.Node;

import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;
import edu.umn.cs.melt.copper.runtime.logging.CopperSyntaxError;
import edu.umn.cs.melt.ide.copper.CopperToken;
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
	
	private SimpleAnnotationTypeInfo fSimpleAnnotationTypeInfo;
	private SourcePositionLocator<Node, IToken> locator;
	private IdeParseResult<Node, CopperToken> lastSuccess;
	
	public ParseController() {
		super(SVRegistry.get().name());
		fSimpleAnnotationTypeInfo = new SimpleAnnotationTypeInfo();
		locator = new SourcePositionLocator<Node, IToken>(this);
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
		try {
			handler.clearMessages();
			Reader reader = new StringReader(input);
			IdeParseResult<Node, CopperToken> result = SVRegistry.get().parse(reader, getPath().toFile().getName());
			lastSuccess = result;
			return fCurrentAst = result.getTree();
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
		
		// Failure-only case
		return null;
	}

	//Delegate to auto-generated (enhanced) parser
	@Override
	public Iterator getTokenIterator(IRegion region) {
		if(lastSuccess == null)
			return null;
		return lastSuccess.getTokenIterator(region);
	}
	
}
