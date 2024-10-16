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
	 * @param inhs A map from inh attribute indexes to Lazys that define them.
	 *   These Lazys will be supplied with 'parent' as their context for evaluation.
	 * @param decSite An accessor for where this DecoratedNode will be supplied with additional inherited attributes.
	 * @return A DecoratedNode with the attributes supplied.
	 */
	public DecoratedNode decorate(final DecoratedNode parent, final Lazy[] inhs, final Lazy decSite);

	/**
	 * Decorate this node with a forward parent.
	 * 
	 * @param parent The "true parent" of this node (same as the fwdParent's parent) 
	 * @param inhs Overrides for inherited attributes that should not be computed via forwarding.
	 *   These Lazys will be supplied with 'parent' as their context for evaluation.
	 * @param fwdParent The DecoratedNode that forwards to the one we are about to create.
	 *   We will pass inherited attribute access requests to this node.
	 * @param prodFwrd Is this the forward for fwdParent's prod?  False for forward prod attributes.
	 * @return A DecoratedNode with the attributes supplied.
	 */
	public DecoratedNode decorate(
		final DecoratedNode parent,
		final Lazy[] inhs,
		final DecoratedNode fwdParent,
		final boolean prodFwrd);

	/**
	 * A convenience method unused by generate Silver code, but useful when working with
	 * the Silver runtime from Java.
	 * 
	 * @return  A node decorated with no inherited attributes, without a parent.
	 */
	public default DecoratedNode decorate() {
		return decorate(TopNode.singleton, null, null);
	}
}
