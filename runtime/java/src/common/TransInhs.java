package common;

import common.exceptions.SilverInternalError;

/**
 * Represents the inherited attributes supplied to a translation attribute.
 * 
 * <p>This class pretends to be a Lazy, so that it can hide in the inherited
 * attribute array passed for decoration. But it should never be evaluated!
 * 
 * @author krame505
 */
public class TransInhs implements Lazy {
    public final Lazy[] inhs;

    public TransInhs(final int ni) {
        inhs = new Lazy[ni];
    }

    @Override
    public Object eval(DecoratedNode context) {
        throw new SilverInternalError("TransInhs should never be evaluated!");
    }
    
    @Override
    public TransInhs withContext(final DecoratedNode context) {
        TransInhs result = new TransInhs(inhs.length);
        for(int i = 0; i < inhs.length; i++) {
            result.inhs[i] = inhs[i].withContext(context);
        }
        return result;
    }
}
