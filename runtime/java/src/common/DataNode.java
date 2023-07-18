package common;

import common.exceptions.SilverInternalError;

public abstract class DataNode extends Node {
    // The DecoratedNode used as context for evaluating syn/production attributes, if ever demanded.
    // Since we have no inh attributes, syn attribute values are always the same and are cached here. 
    private DecoratedNode context;

	public DataNode() {
		super(false);
	}

	/**
	 * Obtain a synthesized attribute from this DataNode, creating the local context if needed.
	 * 
	 * @param attribute The index of the attribute.
	 * @return The value of the attribute.
	 */
	public <T> T synthesized(final int attribute) {
		if (context == null) {
            context = createContext();
        }
        return context.synthesized(attribute);
	}

	/**
	 * Obtain a decorated child with any supplied inherited attributes, from the local context created here.
	 * 
	 * @param child The number of the child to obtain.
	 * @return The decorated value of the child.
	 */
	public DecoratedNode childDecorated(final int child) {
		if (context == null) {
            context = createContext();
        }
        return context.childDecorated(child);
	}

	private DecoratedNode createContext() {
		return new DecoratedNode(
			getNumberOfChildren(), 0, getNumberOfSynAttrs(), getNumberOfLocalAttrs(),
			this, TopNode.singleton, null, null, null, false);
	}

	@Override
	public final boolean getLocalIsForward(final int index) {
		return false;
	}

	@Override
	public final boolean hasForward() {
        return false;
	}
	
	@Override
	public final int getNumberOfInhAttrs() {
		return 0;
	}

    // Overrides for methods that should never be consulted on data nonterminals:
	@Override
	public final DecoratedNode decorate(
            final DecoratedNode parent, final Lazy[] inhs, final Lazy[][] transInhs) {
        throw new SilverInternalError("Data nonterminals cannot be decorated");
    }

	@Override
	public final DecoratedNode decorate(
            final DecoratedNode parent, final Lazy[] inhs, final Lazy[][] transInhs,
            final DecoratedNode fwdParent, final boolean isProdForward) {
        throw new SilverInternalError("Data nonterminals cannot be decorated");
    }

	@Override
	public final Node evalUndecorate(final DecoratedNode context) {
		throw new SilverInternalError("Data nonterminals do not undecorate!");
	}

	@Override
	public final Node evalForward(final DecoratedNode context) {
		throw new SilverInternalError("Data nonterminals do not forward!");
	}

	@Override
	public final Lazy[] getForwardInheritedAttributes() {
		throw new SilverInternalError("Data nonterminals do not forward!");
	}

	@Override
	public final String getNameOfInhAttr(final int index) {
		throw new SilverInternalError("Data nonterminals do not possess inherited attributes! (Requested name of index " + index + ")");
	}
}
