package edu.umn.cs.melt.ide.util.cst;

public interface INodeFinder {

	/**
	 * Find a node in CST with certain characteristics.
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
	public common.Node find(Object root, String[] nodes, int index, int endIndex);
	
}
