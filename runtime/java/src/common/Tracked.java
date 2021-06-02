package common;

import silver.core.NOriginInfo;

public interface Tracked {
	public abstract NOriginInfo getOrigin();

	public abstract Object duplicate(Object redex, Object rule);

	public abstract Object duplicate(OriginContext oc);

	public abstract Object duplicateForForwarding(Object redex, String note);

	public abstract Object copy(Object redex, Object rule);
}