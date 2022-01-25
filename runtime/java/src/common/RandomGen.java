package common;

import java.util.*;

import common.exceptions.*;
import silver.core.*;

public final class RandomGen {
	public static Object runRandomGen(final OriginContext ctx, final int seed, final NRandomGen r) {
		return runRandomGen(ctx, new Random(seed), r);
	}

	public static Object runRandomGen(final OriginContext ctx, final NRandomGen r) {
		return runRandomGen(ctx, new Random(), r);
	}

	// "Interpret" a RandomGen computation using the specified Random generator
	@SuppressWarnings("unchecked")
	public static Object runRandomGen(final OriginContext ctx, final Random rng, final NRandomGen r) {
		if (r instanceof PmapRandomGen) {
			final Object val = runRandomGen(ctx, rng, (NRandomGen)r.getChild(1));
			return ((NodeFactory<?>)r.getChild(0)).invoke(ctx, new Object[] {val}, null);
		} else if (r instanceof PapRandomGen) {
			final NodeFactory<?> fn = (NodeFactory<?>)runRandomGen(ctx, rng, (NRandomGen)r.getChild(0));
			final Object val = runRandomGen(ctx, rng, (NRandomGen)r.getChild(1));
			return fn.invoke(ctx, new Object[] {val}, null);
		} else if (r instanceof PpureRandomGen) {
			return r.getChild(0);
  		} else if (r instanceof PbindRandomGen) {
  			final Object val = runRandomGen(ctx, rng, (NRandomGen)r.getChild(0));
  			return runRandomGen(ctx, rng, ((NodeFactory<NRandomGen>)r.getChild(1)).invoke(ctx, new Object[] {val}, null));
  		} else if (r instanceof PrandomInteger) {
  			return rng.nextInt();
  		} else if (r instanceof PrandomRangeInteger) {
  			return randomRangeInteger((Integer)r.getChild(0), (Integer)r.getChild(1), rng);
  		} else if (r instanceof PrandomFloat) {
  			return rng.nextFloat();
  		} else if (r instanceof PrandomBoolean) {
  			return rng.nextBoolean();
  		} else if (r instanceof PrandomToken_) {
  			return new Random(rng.nextLong());
  		} else {
  			throw new SilverError("Unsupported RandomGen constructor: " + r.getName());  // Not SilverInternalError, someone could define a new RandomGen production
  		}
	}
	
	public static int randomRangeInteger(final int lower, final int upper, final Random rng) {
		if (lower > upper) {
			throw new SilverError("Empty Integer range [" + lower + ", " + upper + "]");
		}
		return rng.nextInt(upper - lower + 1) + lower;  // TODO: I think this could be more robust against signed overflow.
	}
}
