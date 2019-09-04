package common;

/**
 * Takes a NodeFactory, and transforms it into a NodeFactory with a few
 * argument positions filled in already. (i.e. partial function application)
 * 
 * e.g.
 * foo(x,_,y,_) -> {0, 2} {x,y} foo
 * bar(_,_,x,y) -> {2, 3} {x,y} bar
 * 
 * @param <T> The return type of the function
 * @see PartialNameNodeFactory
 * @author tedinski
 */
public class PartialNodeFactory<T> extends NodeFactory<T> {

	/**
	 * Position indexes corresponding to the given arguments.
	 * ALWAYS in increasing order, starting from 0. 
	 */
	private final int[] indices;
	/**
	 * The argument to fill in to the corresponding position indicies.
	 */
	private final Object[] args;
	/**
	 * The function to transform.
	 */
	private final NodeFactory<? extends T> ref;
	
	/**
	 * Creates a NodeFactory from another, with partially applied arguments.
	 * 
	 * @param indices  The indicies we're supplying values for here, in increasing order.
	 * @param args  The values corresponding to said indicies.
	 * @param ref  The NodeFactory we're wrapping.
	 */
	public PartialNodeFactory(final int[] indices, final Object[] args, final NodeFactory<? extends T> ref) {
		this.indices = indices;
		this.args = args;
		this.ref = ref;
	}
	
	@Override
	public T invoke(final Object[] restargs, final Object[] namedArgs) {
		final int fullsize = args.length + restargs.length;
		final Object[] fullargs = new Object[fullsize];
		
		int argsindex = 0;
		int restargsindex = 0;
		for(int i = 0; i < fullsize; i++) {
			if(argsindex < args.length && indices[argsindex] == i) {
				// The we're at that argument, fill it in
				fullargs[i] = args[argsindex++];
			} else {
				fullargs[i] = restargs[restargsindex++];
			}
		}
		// We pass through namedArgs unchanged here.
		return ref.invoke(fullargs, namedArgs);
	}

}
