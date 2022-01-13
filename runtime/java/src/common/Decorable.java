package common;

/**
 * Things that can be decorated.
 * @see Node
 * @see DecoratedNode
 * 
 * @author lucas
 */
public interface Decorable {
	/**
	 * Decorate this node with additional inherited attributes.
	 * 
	 * @param parent The DecoratedNode creating this one. (Whether this is a child or a local (or other) of that node.)
	 * @param inhs A map from attribute names to Lazys that define them.  These Lazys will be supplied with 'parent' as their context for evaluation.
	 * @return A DecoratedNode with the attributes supplied.
	 */
	public DecoratedNode decorate(final DecoratedNode parent, final Lazy[] inhs);
}
