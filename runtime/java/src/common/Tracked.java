package common;

import silver.core.NOriginInfo;
import silver.core.PoriginOriginInfo;
import silver.core.PoriginAndRedexOriginInfo;
import silver.core.PoriginDbgNote;

public interface Tracked {
	public abstract NOriginInfo getOrigin();

	/**
	 * Deep copy, setting the redex at the root and the notes everywhere
	 */
	public abstract Tracked duplicate(Node redex, ConsCell notes);

	public default Tracked duplicate(OriginContext oc) {
		return this.duplicate(oc.lhs, oc.rulesAsSilverList());
	}

	/**
	 * Shallow copy, updating the origin info.
	 */
	public abstract Tracked updateOriginInfo(NOriginInfo oi);

    public default Tracked copy(Node redex, ConsCell redexNotes) {
        Object origin;
        ConsCell originNotes;
        Boolean newlyConstructed;
        NOriginInfo roi = getOrigin();
        if (roi instanceof PoriginOriginInfo) {
            PoriginOriginInfo oi = (PoriginOriginInfo)roi;
            origin = oi.getChild_origin();
            originNotes = oi.getChild_originNotes();
            newlyConstructed = oi.getChild_newlyConstructed();
        } else if (roi instanceof PoriginAndRedexOriginInfo) {
            PoriginAndRedexOriginInfo oi = (PoriginAndRedexOriginInfo)roi;
            origin = oi.getChild_origin();
            originNotes = oi.getChild_originNotes();
            newlyConstructed = oi.getChild_newlyConstructed();
        } else {
            return this;
        }

        return updateOriginInfo(
			new PoriginAndRedexOriginInfo(
				OriginsUtil.SET_AT_ACCESS_OIT,
				origin, originNotes, redex, redexNotes, newlyConstructed));
    }

	public default Tracked duplicateForForwarding(Node redex, String note) {
        return updateOriginInfo(
            new PoriginOriginInfo(
				OriginsUtil.SET_AT_FORWARDING_OIT,
				this, new ConsCell(new PoriginDbgNote(new StringCatter(note)), ConsCell.nil), true));
    }
}