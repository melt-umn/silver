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
	 * @param transInhs A map from trans (syn) attribute indexes, to maps from inh attribute indexes to Lazys that define them. 
	 *   These Lazys will be supplied with 'parent' as their context for evaluation.
	 * @return A DecoratedNode with the attributes supplied.
	 */
	public DecoratedNode decorate(
		final DecoratedNode parent,
		final Lazy[] inhs,
		final Lazy[][] transInhs);

	/**
	 * Decorate this node with a forward parent.
	 * 
	 * @param parent The "true parent" of this node (same as the fwdParent's parent) 
	 * @param inhs Overrides for inherited attributes that should not be computed via forwarding.
	 *   These Lazys will be supplied with 'parent' as their context for evaluation.
	 * @param transInhs Overrides for inherited attributes on translation attributes that should not be computed via forwarding.
	 *   These Lazys will be supplied with 'parent' as their context for evaluation.
	 * @param fwdParent The DecoratedNode that forwards to the one we are about to create.
	 *   We will pass inherited attribute access requests to this node.
	 * @param fwdTrans Do translation attributes on this node have decoration sites in fwdParent?
	 * 	 (This is false for forward production attributes.)
	 * @return A DecoratedNode with the attributes supplied.
	 */
	public DecoratedNode decorate(
		final DecoratedNode parent,
		final Lazy[] inhs,
		final Lazy[][] transInhs,
		final DecoratedNode fwdParent,
		final boolean fwdTrans);
}
