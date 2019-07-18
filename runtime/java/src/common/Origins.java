package common;


import core.*;

/**
 * Implementation of helper functions on Nonterminals used by Origin-tracking code
 * 
 * @author louisg
 */
public final class Origins {
	public static Object debug(final Object arg) {
		System.out.println("\n-----  OTX DEBUG  -----\n");
		System.out.println("arg = " + arg + "\n");
		System.out.println("----- / OTX DEBUG -----\n\n");
		return arg;
	}

	public static Node duplicate(final String rule, final Node arg) {
		System.out.println("\n-----  OTX DUPLICATE  -----\n");
		System.out.println("rule = " + rule + "\n");
		System.out.println("arg = " + arg + "\n");
		System.out.println("----- / OTX DUPLICATE -----\n\n");
		return arg;
	}
}
