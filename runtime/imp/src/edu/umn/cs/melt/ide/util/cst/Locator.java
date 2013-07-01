package edu.umn.cs.melt.ide.util.cst;

/**
 * This class implements the locating service for Silver language.
 * <p>
 * The IDE, upon loaded, will call {@link #setDefinitionFinder(IDefinitionFinder)}
 * to initialize. This rather twisted approach is mainly due to where those Silver 
 * core classes (core.NLocation, for example) are placed: they are now compiled to
 * be part of Silver target jar. Thus IDE runtime cannot get access to them.
 */
public final class Locator {

	private static INodeFinder FINDER;

	/**
	 * To be called by IDE plug-in as part of startup initialization 
	 */
	public static void setDefinitionFinder(INodeFinder f) {
		FINDER = f;
	}
	
	/**
	 * The main interface to be called by Silver from foreign code block.
	 * 
	 * @param root	The root node of CST
	 * @param nodes	a list of names of target nodes to look up
	 * @param index	the start index of target node
	 * @param endIndex	the end index of target node
	 * 
	 * @return a Maybe that contains either the node in CST whose scope covers 
	 * index and whose name matches any of nodes, or nil if no such node is 
	 * found. 
	 */
	public static common.Node find(Object root, String[] nodes, int index, int endIndex){
		return FINDER.find(root, nodes, index, endIndex);
	}
	
}
