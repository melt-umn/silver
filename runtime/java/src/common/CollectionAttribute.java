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

	public Lazy getBase() {
		return base;
	}

	public void setBase(Lazy base) {
		this.base = base;
	}

	public CollectionAttribute() {
		this.pieces = new ArrayList<Lazy>();
	}
	public CollectionAttribute(String name) {
		this();
		this.base = new BaseDefault(name);
	}

	public ArrayList<Lazy> getPieces() {
		return pieces;
	}

	public void addPiece(Lazy attribute) {
		pieces.add(attribute);
	}

	public abstract Object eval(DecoratedNode context);
	
	private static class BaseDefault implements Lazy {
		private String name;
		BaseDefault(String name) {
			this.name=name;
		}
		@Override
		public Object eval(DecoratedNode context) {
			DecoratedNode n = context.forward();
			if(n != null)
				try {
					return n.synthesized(name);
				} catch(Throwable t) {
					throw new RuntimeException("Error evaluating base of collection attribute " + name + " via forward node",t);
				}
				
			throw new RuntimeException("No base defined for collection attribute " + name);
		}
		
	}
}
