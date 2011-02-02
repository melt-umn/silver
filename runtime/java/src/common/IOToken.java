package common;

/**
 * This class is pointless, except to ensure that the translation gets it right
 * w.r.t. to types. NOT CURRENTLY IN USE.
 * 
 * @author tedinski
 */
public final class IOToken {
	public static final IOToken singleton = new IOToken();
	
	private IOToken() {}
}
