package common;

/**
 * Stores information about the auxiliary attributes used by a translation attribute, for debugging purposes.
 */
public class TransOccursInfo {
    public final int inhsAttribute;
    public final int decSiteAttribute;

    public TransOccursInfo(final int inhsAttribute, final int decSiteAttribute) {
        this.inhsAttribute = inhsAttribute;
        this.decSiteAttribute = decSiteAttribute;
    }
}
