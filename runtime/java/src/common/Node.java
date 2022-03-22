package common;

import common.exceptions.TraceException;

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
public abstract class Node implements Decorable, Typed {
	// Common manipulators of Node objects.
	
	/**
	 * The normal way of decorating a node. 
	 * (child and local)
	 * 
	 * @param parent The DecoratedNode creating this one. (Whether this is a child or a local (or other) of that node.)
	 * @param inhs A map from attribute names to Lazys that define them.  These Lazys will be supplied with 'parent' as their context for evaluation.
	 * @return A "decorated" form of this Node
	 */
	@Override
	public final DecoratedNode decorate(final DecoratedNode parent, final Lazy[] inhs) {
		return new DecoratedNode(getNumberOfChildren(),
				                 getNumberOfInhAttrs(),
				                 getNumberOfSynAttrs(),
				                 getNumberOfLocalAttrs(),
				                 this, parent, inhs, null);
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
		return new DecoratedNode(getNumberOfChildren(),
                                 getNumberOfInhAttrs(),
                                 getNumberOfSynAttrs(),
                                 getNumberOfLocalAttrs(),
                                 this, parent, null, fwdParent);
	}

	/**
	 * A convenience method unused by generate Silver code, but useful when working with
	 * the Silver runtime from Java.
	 * 
	 * @return  A node decorated with no inherited attributes, without a parent.
	 */
	public DecoratedNode decorate() {
		return decorate(TopNode.singleton, (Lazy[])null);
	}
	
	private Node undecoratedValue = null;
	public final Node undecorate(final DecoratedNode context) {
		if (undecoratedValue != null) {
			return undecoratedValue;
		} else {
			try {
				undecoratedValue = evalUndecorate(context);
			} catch(Throwable t) {
				throw new TraceException("While undecorating " + context.getDebugID(), t);
			}
			return undecoratedValue;
		}
	}

	// These methods are to be provided by the *nonterminal*
	
	/**
	 * Used to create arrays of appropriate size in DecoratedNode.
	 * 
	 * @return The number of synthesized attributes that occur on this nonterminal type.
	 */
	public abstract int getNumberOfSynAttrs();

	/**
	 * Used for debugging, stack traces especially.
	 * 
	 * @param index The index of the synthesized attribute to return the name of
	 * @return The name of the synthesized attribute at this index
	 */
	public abstract String getNameOfSynAttr(final int index);
	
	/**
	 * When a production does not forward and lacks an equation for a synthesized attribute, this is consulted instead.
	 * 
	 * @param index Any synthesized attribute on this Node
	 * @return A Lazy to evaluate on a decorated form of this Node, to get the DEFAULT value of the attribute.
	 */
	public abstract Lazy getDefaultSynthesized(final int index);
	
	/**
	 * Used to create arrays of appropriate size in DecoratedNode.
	 * 
	 * @return The number of inherited attributes that occur on this nonterminal type.
	 */
	public abstract int getNumberOfInhAttrs();

	/**
	 * Used for debugging, stack traces especially.
	 * 
	 * @param index The index of the inherited attribute to return the name of
	 * @return The name of the inherited attribute at this index
	 */
	public abstract String getNameOfInhAttr(final int index);
	
	/**
	 * Used for reflection.
	 * 
	 * @return An array of the names of all annotations.
	 */
	public abstract String[] getAnnoNames();

	/**
	 * Used for reflection.
	 * 
	 * @param name The name of any annotation returned by <code>getAnnoNames()</code>
	 * @return The annotation object. (Thunk already evaluated)
	 */
	public abstract Object getAnno(final String name);
	
	
	// These methods are to be provided by the *production*
	
	/**
	 * Used for debugging, and pattern matching.
	 * 
	 * @return The full name of this Node. (e.g. "silver:definition:core:baseExpr")
	 */
	public abstract String getName();

	/**
	 * Returns the number of children, same way array length does.
	 * 
	 * <p>For example, a Node with 2 children allows you to request child 0 and 1.
	 * 
	 * @return The number of children this Node has.
	 */
	public abstract int getNumberOfChildren();

	/**
	 * Access a (raw) child of this Node.
	 * 
	 * @param child A number in the range <code>0 - getNumberofChildren()</code>
	 * @return The child object. (Thunk already evaluated)
	 * @see Node#getNumberOfChildren()
	 */
	public abstract Object getChild(final int child);
	
	/**
	 * @param child A number in the range <code>0 - getNumberofChildren()</code>
	 * @return The child object, WITHOUT forcing the Thunk, if any.
	 */
	public abstract Object getChildLazy(final int child);
	
	/**
	 * @param key The child index to look up the inherited attributes.
	 * @return An array containing the inherited attributes supplied to that child 
	 */
	public abstract Lazy[] getChildInheritedAttributes(final int index);

	/**
	 * Used to create arrays of appropriate size in DecoratedNode.
	 * 
	 * @return The number of local and production attributes that occur on this <b>production</b>
	 */
	public abstract int getNumberOfLocalAttrs();
	
	/**
	 * Used for debugging, stack traces especially.
	 * 
	 * @param index The index of the local attribute to return the name of
	 * @return The name of the local attribute at this index
	 */
	public abstract String getNameOfLocalAttr(final int index);

	/**
	 * @param name The index of a local or production attribute on this Node
	 * @return A Lazy to evaluate on a decorated form of this Node, to get the value of the attribute
	 */
	public abstract Lazy getLocal(final int index);

	/**
	 * @param key The index for a local, to retrieve inherited attributes for.
	 * @return An array containing the inherited attributes supplied to that local 
	 */
	public abstract Lazy[] getLocalInheritedAttributes(final int index);
	
	/**
	 * Reports whether or not this production forwards.
	 * 
	 * @return true if {@link #evalForward} can be called, false if that immediately throws.
	 */
	public abstract boolean hasForward();
	
	/**
	 * It may help conceptually to imagine this returns a Lazy that the DN then calls with itself as context.
	 * We've simply merged this into this method to avoid an unnecessary Lazy.
	 *
	 * @param context The DN of this node, to use to evaluate the forward equation.
	 * @return The Node that context forwards to.
	 */
	public abstract Node evalForward(final DecoratedNode context);
	
	/**
	 * Compute the Node that this Node undecorates to.
	 *
	 * @param context The DN of this node, to use to evaluate the undecorate equation.
	 * @return The Node that context undecorates to.
	 */
	public abstract Node evalUndecorate(final DecoratedNode context);

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

	public abstract RTTIManager.Prodleton<? extends Node> getProdleton();
}
