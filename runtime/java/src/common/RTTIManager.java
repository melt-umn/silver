package common;

import java.util.*;

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

	public static abstract class Terminalton<T> {
		public abstract T construct(final StringCatter lexeme, final silver.core.NLocation location);
		public abstract String getName();
	}

	public static abstract class Nonterminalton<T> {
		public abstract String[] getOccursInh();
		public abstract String getName();
	}

}
