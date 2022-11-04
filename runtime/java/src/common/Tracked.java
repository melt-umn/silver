package common;

import silver.core.NOriginInfo;

public interface Tracked {
	public abstract NOriginInfo getOrigin();

	public abstract Tracked duplicate(Node redex, ConsCell notes);

	public default Tracked duplicate(OriginContext oc) {
		return this.duplicate(oc.lhs, oc.rulesAsSilverList());
	}

	public abstract Tracked duplicateForForwarding(Node redex, String note);

	public abstract Tracked copy(Node redex, ConsCell redexNotes);
}