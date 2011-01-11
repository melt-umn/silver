package common;

import java.util.ArrayList;

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
	
	public Lazy getBase() {
		return base;
	}

	public void setBase(Lazy base) {
		this.base = base;
	}

	public ArrayList<Lazy> getPieces() {
		return pieces;
	}

	public void addPiece(Lazy attribute) {
		pieces.add(attribute);
	}

	public abstract Object eval(DecoratedNode context);
	
	private static class BaseDefault implements Lazy {
		private final int index;
		BaseDefault(final int index) {
			this.index=index;
		}
		@Override
		public Object eval(DecoratedNode context) {
			final DecoratedNode n = context.forward();
			if(n != null) {
				try {
					return n.synthesized(index);
				} catch(Throwable t) {
					final Node un = context.undecorate();
					throw new RuntimeException("Error evaluating base of collection attribute " + un.getNameOfSynAttr(index) + " via forward of " + un.getName(),t);
				}
			}	

			final Node un = context.undecorate();
			throw new RuntimeException("No base defined for collection attribute " + un.getNameOfSynAttr(index) + " in " + un.getName());
		}
		
	}
}
