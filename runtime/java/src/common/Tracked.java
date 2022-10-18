package common;

import silver.core.NOriginInfo;

public interface Tracked {
	public abstract NOriginInfo getOrigin();

	public abstract Object duplicate(Node redex, ConsCell notes);

	public default Object duplicate(OriginContext oc) {
		return this.duplicate(oc.lhs, oc.rulesAsSilverList());
	}

	public abstract Object duplicateForForwarding(Node redex, String note);

	public abstract Object copy(Node redex, ConsCell redexNotes);
}