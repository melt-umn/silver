package edu.umn.cs.melt.ide.impl;

import java.io.IOException;
import java.io.Reader;

import org.eclipse.core.resources.IProject;

import common.ConsCell;
import common.Node;
import common.StringCatter;
import core.NIOVal;

import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;
import edu.umn.cs.melt.ide.copper.coloring.ITokenClassifier;
import edu.umn.cs.melt.ide.eclipse.property.IPropertyPageTab;
import edu.umn.cs.melt.ide.imp.services.IdeParseResult;
import edu.umn.cs.melt.ide.silver.property.ui.IPropertyControlsProvider;

/**
 * This interface is implemented by two classes:
 *   1. The SVDefault class next door.
 *   2. The implementation for a language plugin generated by silver. 
 *
 * This is pretty much nothing but a collection of function pointers to silver functions.
 * They should appear in the ide plugin declaration in the silver source file.
 */
public interface SVInterface {
	/**
	 * Gets the language name, i.e. that used in the IMP language registry.
	 * 
	 * @see org.eclipse.imp.language.Language
	 * @see org.eclipse.imp.language.LanguageRegistry
	 */
	public String name();
	
	/**
	 * Obtain this plugin's id.
	 * 
	 * Should *NOT* be used to guess the ids of extension point contibutions
	 * (e.g. pluginId() + ".nature" or similar) becuase that's crap.
	 * 
	 * SHOULD be used for trying to open up resources from inside this bundle's jar.
	 */
	public String pluginId();
	
	/**
	 * Gets the name of the problem marker for this extension.
	 */
	public String markerErrorName();
	
	/**
	 * Obtain the name of the nature for this plugin.
	 */
	public String getNatureId();
	
	/**
	 * Get the file extension associated with this plugin. excluding the dot. e.g. "sv"
	 */
	public String fileExtension();
	
	/**
	 * IOVal<[IdeMessage]> ::= IdeProject [IdeProperty] IO
	 * 
	 * Run when a build action is requested. e.g. a file is saved, if auto-build is on.
	 * 
	 * @param project  The project being built
	 * @param properties  The IDE project properties.
	 * @param iotoken  An input IO token.
	 * @return An IO object that contains a list of error messages to raise.
	 */
	public NIOVal build(IProject project, ConsCell properties, Object iotoken); 

	/**
	 * IOVal<[IdeMessage]> ::= IdeProject [IdeProperty] IO
	 * 
	 * <p>Run when a build action *has succeeded without errors*.
	 * 
	 * <p>Why do we bother with this? In order to report errors to the user faster.
	 * 'build' can return empty list, the user's red-squigglies are updated, then this is run to
	 * actually accomplish longer running stuff in the build.
	 * 
	 * <p>An entirely valid implementation does nothing.
	 * 
	 * @param project  The project being built
	 * @param properties  The IDE project properties.
	 * @param iotoken  An input IO token.
	 * @return An IO object that contains a list of *additional* error messages to raise.
	 */
	public NIOVal postbuild(IProject project, ConsCell properties, Object iotoken);
	
	/**
	 * IOVal<[IdeMessage]> ::= IdeProject [IdeProperty] IO
	 * 
	 * Run when the user requests an export? (TODO: uh, figure some stuff out here)
	 * 
	 * @param project  The project being built
	 * @param properties  The IDE project properties.
	 * @param iotoken  An input IO token.
	 * @return  Any additional errors to raise (usually sys errors, rather than for files)
	 */
	public NIOVal export(IProject project, ConsCell properties, Object iotoken);
	
	/**
	 * [Location] ::= <<CST root's type>>
	 * 
	 * Given a tree from parsing, return a list of locations that can be folded.
	 * 
	 * @param root  The CST tree
	 * @return  A list of extents that should fold.
	 */
	public ConsCell getFolds(Node root);
	
	/**
	 * Obtains a list of properties to request in order to create a new file via wizard.
	 */
	public IPropertyControlsProvider getNewFileProperties();
	/**
	 * String ::= [IdeProperty]
	 * 
	 * Given the properties from {@link #getNewFileProperties()}, generate the file's contents.
	 * 
	 * @param properties  the requested properties
	 * @return the file's initial contents
	 */
	public StringCatter fileStub(ConsCell properties);

	/**
	 * Obtains a list of properties for the project's configuration.
	 */
	public IPropertyControlsProvider getProjectProperties();
	/**
	 * Generate the initial project properties configuration file contents.
	 */
	public String getInitialProjectProperties();
	/**
	 * Get a set of tabs for the project's properties page.
	 */
	public IPropertyPageTab[] getPropertyTabs();
	public ITokenClassifier getTokenClassifier();
	public IdeParseResult<Node> parse(Reader input, String filename) throws CopperParserException, IOException;
}
