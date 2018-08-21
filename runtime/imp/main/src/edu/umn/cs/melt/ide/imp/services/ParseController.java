package edu.umn.cs.melt.ide.imp.services;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.IRegion;

import org.eclipse.imp.parser.ISourcePositionLocator;
import org.eclipse.imp.parser.ParseControllerBase;
import org.eclipse.imp.parser.SimpleAnnotationTypeInfo;
import org.eclipse.imp.services.IAnnotationTypeInfo;
import org.eclipse.imp.services.ILanguageSyntaxProperties;
import org.eclipse.imp.language.LanguageRegistry;

import common.Node;
import common.Terminal;

import edu.umn.cs.melt.copper.runtime.engines.CopperParser;
import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;
import edu.umn.cs.melt.copper.runtime.logging.CopperSyntaxError;

import edu.umn.cs.melt.ide.copper.SourcePositionLocator;
import edu.umn.cs.melt.ide.util.ReflectedCall;

// https://github.com/impulse-org/imp.runtime/blob/master/src/org/eclipse/imp/parser/IParseController.java
// https://github.com/impulse-org/imp.runtime/blob/master/src/org/eclipse/imp/parser/ParseControllerBase.java

/**
 * Extends ParseControllerBase to use the default implementation of several
 * interface methods. The following variables are inherited:<br>
 * 	 protected ISourceProject fProject;<br>
 * 	 protected IPath fFilePath;<br>
 * 	 protected IMessageHandler handler;<br>
 * 	 protected Object fCurrentAst;<br>
 */
public class ParseController extends ParseControllerBase implements IExecutableExtension {
	
	private SimpleAnnotationTypeInfo fSimpleAnnotationTypeInfo;
	private SourcePositionLocator<Node> locator;
	private IdeParseResult<Node> lastSuccess;
	
	private CopperParser<? extends Node, CopperParserException> parser;
	// These are silver-generated functions on the parser...
	// For the time being, we'll get this be reflection, but we should consider
	// adding an interface or something? Can copper parsers have custom interfaces?
	private ReflectedCall<List<Terminal>> getTokens;
	private ReflectedCall<Object> reset;
	
	
	@Override
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		String language = config.getAttribute("language");
		
		for(IConfigurationElement elem : config.getChildren("copper")) {
			String parser_class_name = elem.getAttribute("class");
			parser = (CopperParser<? extends Node, CopperParserException>)
				ReflectedCall.newInstance(parser_class_name);
			getTokens = new ReflectedCall<List<Terminal>>(parser_class_name, "getTokens", new Class[0]);
			reset = new ReflectedCall<Object>(parser_class_name, "reset", new Class[0]);
			// Do something smarter with error handling later...
			break;
		}
		
		// Normally this is done by ParseControllerBase's non-default constructor
		// but we want to do it HERE instead of in the constructor, so whatevs:
		fLanguage = LanguageRegistry.findLanguage(language);
		
		// We might as well just do all our initialization here.
		// After all, this stuff might be influenced by plugin.xml in the future, too...
		fSimpleAnnotationTypeInfo = new SimpleAnnotationTypeInfo();
		locator = new SourcePositionLocator<Node>(this);
	}

	public ParseController() {
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
			lastSuccess = null;
			handler.clearMessages();
			Reader reader = new StringReader(input);
			IdeParseResult<Node> result;
			synchronized(parser) {
				reset.invokeOn(parser, null);
				result = new IdeParseResult<Node>(parser.parse(reader, getPath().toFile().getName()), getTokens.invokeOn(parser, null));
			}
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
