package common.javainterop;

import java.util.Arrays;

import common.*;

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
		String[] so = getOccursSynFor(dn.undecorate());
		
		return Arrays.asList(so).indexOf(syn);
	}

	public static int indexOfInhOn(String inh, DecoratedNode dn) {
		String[] io = getOccursInhFor(dn.undecorate());
		
		return Arrays.asList(io).indexOf(inh);
	}
}
