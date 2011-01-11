package common;

import java.util.ArrayList;

import common.exceptions.MissingDefinitionException;
import common.exceptions.TraceException;

/**
 * This class is a specialized Lazy that allows additional elements to be inserted
 * during start-up initialization.  These elements are then used by the custom
 * eval method (note that it's abstract!) to produce the final value.
 * 
 * <p>The nice thing about this design is that using a collection attribute is no
 * different than any other. The difference is all in initialization.
 * 
 * @author tedinski, bodin
 */
public abstract class CollectionAttribute implements Lazy {
	
	private ArrayList<Lazy> pieces;
	private Lazy base;

	public CollectionAttribute() {
		this.pieces = new ArrayList<Lazy>();
	}
	
	public CollectionAttribute(final int index) {
		this();
		this.base = new BaseDefault(index);
	}
	
	public final Lazy getBase() {
		return base;
	}

	public final void setBase(final Lazy base) {
		this.base = base;
	}

	public final ArrayList<Lazy> getPieces() {
		return pieces;
	}

	public final void addPiece(final Lazy attribute) {
		pieces.add(attribute);
	}

	public abstract Object eval(final DecoratedNode context);
	
	private static class BaseDefault implements Lazy {
		private final int index;
		BaseDefault(final int index) {
			this.index=index;
		}
		@Override
		public Object eval(final DecoratedNode context) {
			final DecoratedNode n = context.forward();
			if(n != null) {
				try {
					return n.synthesized(index);
				} catch(Throwable t) {
					final Node un = context.undecorate();
					throw new TraceException("Error evaluating base of collection attribute " + un.getNameOfSynAttr(index) + " via forward of " + un.getName(),t);
				}
			}	

			final Node un = context.undecorate();
			throw new MissingDefinitionException("No base defined for collection attribute " + un.getNameOfSynAttr(index) + " in " + un.getName());
		}
		
	}
}
