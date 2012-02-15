package common;

/**
 * Takes a NodeFactory, and transforms it into a NodeFactory with a few
 * argument positions filled in already. (i.e. partial function application)
 * 
 * @author tedinski
 *
 * @param <T> The return type of the function
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
	private final NodeFactory<T> ref;
	
	public PartialNodeFactory(final int[] indices, final Object[] args, final NodeFactory<T> ref) {
		this.indices = indices;
		this.args = args;
		this.ref = ref;
	}
	
	@Override
	public T invoke(final Object[] restargs) {
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
		return ref.invoke(fullargs);
	}

}
