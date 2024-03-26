package common;

/**
 * Take a NodeFactory for a dispatch implementation production,
 * and partially apply it to fill in the extra arguments.
 * 
 * @author krame505
 */

public class DispatchNodeFactory<T> extends NodeFactory<NodeFactory<T>> {
    private final NodeFactory<T> prod;
    private int dispatchArity;

    public DispatchNodeFactory(final NodeFactory<T> prod, final int dispatchArity) {
        this.prod = prod;
        this.dispatchArity = dispatchArity;
    }

    @Override
    public NodeFactory<T> invoke(OriginContext originCtx, Object[] args, Object[] namedArgs) {
        int[] indices = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            indices[i] = i + dispatchArity;
        }
        return new PartialNodeFactory<>(indices, args, prod);
    }

    @Override
    public TypeRep getType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getType'");
    }
    
}
