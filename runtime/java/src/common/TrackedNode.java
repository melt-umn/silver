package common;

import silver.core.NOriginInfo;

public abstract class TrackedNode extends Node {

	public final NOriginInfo origin;

	public TrackedNode (NOriginInfo origin) {
		this.origin = origin;
	}

	public abstract Object duplicate(Object redex, Object rule);

	public abstract Object duplicate(OriginContext oc);

	public abstract Object duplicateForForwarding(Object redex, String note);

	public abstract Object copy(Object redex, Object rule);
}