package common;

/**
 * TopNode is a DecoratedNode that is empty. It is used largely to give better error
 * messages than "null pointer exception" if there is a compiler bug.
 * 
 * <p>It's used as the parent of the very first thing, as the context for evaluating
 * global values, and is the parent of all function nodes.
 * 
 * @author tedinski, bodin
 * @see DecoratedNode
 */
public class TopNode extends DecoratedNode{		

	public static final TopNode singleton = new TopNode();
	
	private TopNode() {
		super(null,null,null,null);
	}
	
	@Override
	public Object inherited(final int attribute) {		
		throw new RuntimeException("No inherited attributes given to TopNode.");
	}

	@Override
	protected Object inheritedForwarded(final int attribute) {
		throw new RuntimeException("TopNode does not provided inherited attributes.");
	}

	@Override
	public Lazy synthesized(final int attribute) {		
		throw new RuntimeException("No synthesized attributes defined on TopNode.");
	}
	
	@Override
	public Object childAsIs(final int s) {
		throw new RuntimeException("No Children defined on TopNode.");
	}

	@Override
	public DecoratedNode childDecorated(final int s) {
		throw new RuntimeException("No Children defined on TopNode.");
	}

	@Override
	public Object localAsIs(final String attribute) {
		throw new RuntimeException("No local attributes defined on TopNode.");
	}

	@Override
	public DecoratedNode localDecorated(final String attribute) {
		throw new RuntimeException("No local attributes defined on TopNode.");
	}

	@Override
	public Node undecorate() {
		throw new RuntimeException("Cannot undecorate TopNode.");
	}

	@Override
	public DecoratedNode forward() {
		throw new RuntimeException("TopNode does not forward.");
	}
	
}
