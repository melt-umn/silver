package common.javainterop;

import java.util.Arrays;

import common.*;

/**
 * Accomplish certain common operations (from Java code) on Silver data types.
 * 
 * <p>Many operations can be found directly on Node, DecoratedNode, etc. However,
 * these only include those operations that are actually used by the Silver runtime.
 * The operations included here are for more general use of arbitrary Java code that
 * needs to work with Silver values and types.
 */
public final class Util {

	public static String productionName(DecoratedNode n) {
		return n.undecorate().getName();
	}
	
	public static int numberOfChildren(DecoratedNode n) {
		return n.undecorate().getNumberOfChildren();
	}
	
	private static String[] getOccursInhFor(Node n) {
		try {
			return (String[])n.getClass().getField("occurs_inh").get(null);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String[] inhAttributesOccurring(DecoratedNode dn) {
		return getOccursInhFor(dn.undecorate());
	}

	private static String[] getOccursSynFor(Node n) {
		try {
			return (String[])n.getClass().getField("occurs_syn").get(null);
		} catch (Exception e) {
			return null;
		}
	}

	public static String[] synAttributesOccurring(DecoratedNode dn) {
		return getOccursSynFor(dn.undecorate());
	}

	public static int indexOfSynOn(String syn, DecoratedNode dn) {
		return indexOfSynOn(syn, dn.undecorate());
	}
	public static int indexOfSynOn(String syn, Node n) {
		String[] so = getOccursSynFor(n);
		
		return Arrays.asList(so).indexOf(syn);
	}

	public static int indexOfInhOn(String inh, DecoratedNode dn) {
		return indexOfInhOn(inh, dn.undecorate());
	}
	public static int indexOfInhOn(String inh, Node n) {
		String[] io = getOccursInhFor(n);
		
		return Arrays.asList(io).indexOf(inh);
	}
}
