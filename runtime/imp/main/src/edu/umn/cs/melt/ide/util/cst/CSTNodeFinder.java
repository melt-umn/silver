package edu.umn.cs.melt.ide.util.cst;

/**
 * A CSTNodeFinder is a specialized visitor used to find a node in the tree, 
 * meeting certain criteria, such as name and indices.
 */
public abstract class CSTNodeFinder implements CSTVisitor {

	/** The start index of target node */
	protected int index;
	
	/** The end index of target node */
	protected int endIndex;
	
	/** 
	 * The names of target node, how this name list is interpreted is up to the 
	 * implementation 
	 */
	protected String[] names;
	
	/**
	 * Create a CSTNodeFinder, the arguments provided to this constructor can
	 * be accessed by subclasses with same name.
	 * 
	 * @param names see field {@link CSTNodeFinder#names names}
	 * @param index see field {@link CSTNodeFinder#index index}
	 * @param endIndex see field {@link CSTNodeFinder#endIndex endIndex}
	 */
	public CSTNodeFinder(String[] names, int index, int endIndex){
		this.index = index;
		this.names = names;
		this.endIndex = endIndex;
	}
	
	public abstract Object getFound();

}
