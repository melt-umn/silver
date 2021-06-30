package common;

import java.util.*;

/**
 * Collects maps between fully-qualified silver names (some:package:NT) and singletons for terminals,
 * nonterminals, and productions. The singletons represent the terminal/nonterminal/production itself,
 * not instances thereof. So functions on the singletons grant access to static fields/methods on the
 * terminal/nonterminal/production. These are meant to be usable for all the same functions we
 * previously did via java reflection. So a Terminalton<T> is akin to a Class<T extends Terminal>, a
 * Nonterminalton<T> to a Class<T extends Node>, and Prodleton<T> to a Class<T extends N extends Node>
 * where .getNonterminalton() -> Nonterminalton<N>. These are registered into these lookup tables at
 * init time.
 * 
 * This is used for the following:
 *  - silver reflection
 *  - decorators (read: implementing autocopy)
 * 
 * @author louisg
 */
public final class RTTIManager {
	private static final Map<String, Prodleton<?>> productionsByName = new HashMap<String, Prodleton<?>>(4096, 0.5f); // guesstimates from silver
	private static final Map<String, Nonterminalton<?>> nonterminalsByName = new HashMap<String, Nonterminalton<?>>(512, 0.5f);
	private static final Map<String, Terminalton<?>> terminalsByName = new HashMap<String, Terminalton<?>>(512, 0.5f);

	public static void registerNonterminal(Nonterminalton<?> nton) {
		nonterminalsByName.put(nton.getName(), nton);
	}

	public static Nonterminalton<?> getNonterminalton(String name) {
		return nonterminalsByName.get(name);
	}

	public static void registerProduction(Prodleton<?> pton) {
		productionsByName.put(pton.getName(), pton);
	}

	public static Prodleton<?> getProdleton(String name) {
		return productionsByName.get(name);
	}

	public static Terminalton<?> getTerminalton(String name) {
		return terminalsByName.get(name);
	}

	public static void registerTerminal(Terminalton<?> tton) {
		terminalsByName.put(tton.getName(), tton);
	}

	// Represents a production (not an instance of the production, but the production itself.)
	// So implements reify and access to static fields (childtypes, childinheritedattributes) on
	//  the nonterminal.
	// T = the production type (PSomething)
	public static abstract class Prodleton<T> {
		public abstract T reify(
			final silver.core.NAST origAST,
			final common.ConsCell rules,
			final common.TypeRep resultType,
			final silver.core.NAST[] childASTs,
			final String[] annotationNames,
			final silver.core.NAST[] annotationASTs);

		public abstract String getName();
		public abstract Nonterminalton<?> getNonterminalton();
		
		public abstract String[] getChildTypes();
		public abstract Lazy[][] getChildInheritedAttributes();
	}

	// Represents a terminal (not an instance of the terminal, but the terminal itself.)
	// T = the terminal type (TSomething)
	public static abstract class Terminalton<T> {
		public abstract T construct(final StringCatter lexeme, final silver.core.NLocation location);
		public abstract String getName();
	}

	// Represents a nonterminal (not a production or instance, but the nonterminal type itself.)
	// T = the nonterminal type (NSomething)
	public static abstract class Nonterminalton<T> {
		public abstract String[] getOccursInh();
		public abstract String getName();
	}

}
