package common;


import core.*;

/**
 * Implementation of helper functions on Nonterminals used by Origin-tracking code
 * 
 * @author louisg
 */
public final class Origins {
	public static Node copy(final Object redex, final Node tree) {
		System.out.println("Look ma, a side effect!\n");
		return tree;
	}
}
