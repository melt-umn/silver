package common;

/**
 * Node represents undecorated nodes.  That is, we have children, but no inherited attributes, yet.
 * 
 * <p>This is subclassed by nonterminals (which are then subclassed by productions, which implement
 * the abstract methods).  Generally speaking, nothing is used on this class externally except the
 * various decorate methods, and by the DecoratedNode class.
 * 
 * @author tedinski
 * @see DecoratedNode
 */
public abstract class Node {
	
	private final Object[] children;
	
	protected Node(final Object[] children) {
		this.children = children;

		// STATS: Uncomment to enable statistics
		//Statistics.nSpawn(this.getClass());
	}
	
	// STATS: Uncomment to enable statistics
//	@Override
//	protected void finalize() throws Throwable {
//		Statistics.nFinal(this.getClass());
//	}

	/**
	 * Decorates a Node, without a parent or inherited attributes.
	 * Generally only used for function calls. (and empty decorate exprs) (note: generated code for fun calls, too)
	 * 
	 * @return A "decorated" form of this Node 
	 */
	public final DecoratedNode decorate() {
		return decorate(TopNode.singleton);
	}

	/**
	 * Used by the translation, whenever there are no inherited attributes to be supplied.
	 * 
	 * @param parent The DecoratedNode creating this one. (Whether this is a child or a local (or other) of that node.)
	 * @return A "decorated" form of this Node
	 * @deprecated
	 */
	public final DecoratedNode decorate(final DecoratedNode parent) {
		return decorate(parent, (Lazy[])null);
	}

	/**
	 * The normal way of decorating a node. 
	 * (child and local)
	 * 
	 * @param parent The DecoratedNode creating this one. (Whether this is a child or a local (or other) of that node.)
	 * @param inhs A map from attribute names to Lazys that define them.  These Lazys will be supplied with 'parent' as their context for evaluation.
	 * @return A "decorated" form of this Node
	 */
	public final DecoratedNode decorate(final DecoratedNode parent, final Lazy[] inhs) {
		return new DecoratedNode(this, parent, inhs);
	}

	/**
	 * The way of decorating a forward node.
	 * (fwd only)
	 * 
	 * @param parent The "true parent" of this node (same as the fwdParent's parent) 
	 * @param fwdParent The DecoratedNode that forwards to the one we are about to create. We will pass inherited attribute access requests to this node.
	 * @return A "decorated" form of this Node 
	 */
	public final DecoratedNode decorate(final DecoratedNode parent, final DecoratedNode fwdParent) {
		return new DecoratedNode(this, parent, fwdParent);
	}

	/**
	 * Access a (raw) child of this Node.
	 * 
	 * @param child A number in the range <code>0 - getNumberofChildren()</code>
	 * @return The child object. (Thunk already evaluated)
	 * @see Node#getNumberOfChildren()
	 */
	public final Object getChild(final int child) {
		Object o = children[child];
		if(o instanceof Closure) {
			// We're doing thunk evaluation on the Node level, rather
			// than DecoratedNode.  This is more efficient, as there
			// may be more than one DecoratedNode per Node.
			// AND this eliminates all references to this thunk,
			// eliminating all references to that DecoratedNode and so on.
			o = ((Closure)o).eval();
			children[child] = o;
		}
		return o;
	}
	
	/**
	 * Returns the number of children, same way array length does.
	 * 
	 * <p>For example, a Node with 2 children allows you to request child 0 and 1.
	 * 
	 * @return The number of children this Node has.
	 */
	public final int getNumberOfChildren() {
		if(children == null)
			return 0;
		return children.length;
	}
	
	/**
	 * Used to create arrays of appropriate size in DecoratedNode.
	 * 
	 * @return The number of inherited attributes that occur on this nonterminal type.
	 */
	public abstract int getNumberOfInhAttrs();
	/**
	 * Used to create arrays of appropriate size in DecoratedNode.
	 * 
	 * @return The number of synthesized attributes that occur on this nonterminal type.
	 */
	public abstract int getNumberOfSynAttrs();
	/**
	 * Used for debugging, and pattern matching.
	 * 
	 * @return The full name of this Node. (e.g. "silver:definition:core:baseExpr")
	 */
	public abstract String getName();
	/**
	 * Used for debugging, stack traces especially.
	 * 
	 * @param index The index of the inherited attribute to return the name of
	 * @return The name of the inherited attribute at this index
	 */
	public abstract String getNameOfInhAttr(final int index);
	/**
	 * Used for debugging, stack traces especially.
	 * 
	 * @param index The index of the synthesized attribute to return the name of
	 * @return The name of the synthesized attribute at this index
	 */
	public abstract String getNameOfSynAttr(final int index);

	/**
	 * @return A Lazy to evaluate on a decorated form of this Node, to get a Node that this one forwards to.
	 */
	public abstract Lazy getForward();

	/**
	 * Get any overridden attributes for this node's forward.  (e.g. forwarding with { inh = foo; })
	 * 
	 * @param index The inherited attribute requested by a forwarded-to Node. 
	 * @return A Lazy to evaluate on a decorated form of this Node, to get the value of this attribute provided to the forward.
	 */
	public abstract Lazy getForwardInheritedAttributes(final int index);

	/**
	 * @param index Any synthesized attribute on this Node
	 * @return A Lazy to evaluate on a decorated form of this Node, to get the value of the attribute
	 */
	public abstract Lazy getSynthesized(final int index);

	/**
	 * @param key The name of the local to retrieve inherited attributes for.
	 * @return An array containing the inherited attributes supplied to that local 
	 */
	public abstract Lazy[] getLocalInheritedAttributes(final String key);
	
	/**
	 * @param key The child index to look up the inherited attributes.
	 * @return An array containing the inherited attributes supplied to that child 
	 */
	public abstract Lazy[] getChildInheritedAttributes(final int key);
	
	/**
	 * @param name Any local attribute on this Node
	 * @return A Lazy to evaluate on a decorated form of this Node, to get the value of the attribute
	 */
	public abstract Lazy getLocal(final String name);
}
