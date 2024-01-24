package common;

import java.util.*;

import common.exceptions.SilverError;

/**
 * Collects maps between fully-qualified silver names (some:package:NT) and singletons for terminals,
 * nonterminals, and productions. The singletons represent the terminal/nonterminal/production itself,
 * not instances thereof. So functions on the singletons grant access to static fields/methods on the
 * terminal/nonterminal/production. These are meant to be usable for all the same functions we
 * previously did via java reflection. So a Terminalton<T> is akin to a Class<T extends Terminal>, a
 * Nonterminalton<T> to a Class<T extends Node>, and Prodleton<T> to a Class<T extends N extends Node>
 * where .getNonterminalton() -> Nonterminalton<N super T>. These are registered into these lookup
 * tables at silver init time.
 * 
 * This is used for the following:
 *  - silver reflection (and transitively, reflective de/serialization, rewriting stuff, etc)
 *  - native de/serialization (see Reflection.java:nativeSerialize)
 * 
 * @author louisg
 */
public final class RTTIManager {
	private static final int estimateProductionCount = 4096; // guesstimates from the silver compiler
	private static final int estimateNonterminalCount = 512;
	private static final int estimateTerminalCount = 512;

	private static final Map<String, Prodleton<?>> productionsByName = new HashMap<String, Prodleton<?>>(estimateProductionCount, 0.5f);
	private static final Map<String, Nonterminalton<?>> nonterminalsByName = new HashMap<String, Nonterminalton<?>>(estimateNonterminalCount, 0.5f);
	private static final Map<String, Terminalton<?>> terminalsByName = new HashMap<String, Terminalton<?>>(estimateTerminalCount, 0.5f);


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


	public static void registerTerminal(Terminalton<?> tton) {
		terminalsByName.put(tton.getName(), tton);
	}

	public static Terminalton<?> getTerminalton(String name) {
		return terminalsByName.get(name);
	}


	public static void registerOccurs(String nt, String attr, boolean isInherited, int index) {
		Nonterminalton<?> ntton = getNonterminalton(nt);
		if (isInherited) {
			ntton.inhOccursIndices.put(attr, index);
		} else {
			ntton.synOccursIndices.put(attr, index);
		}
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

		public abstract T constructDirect(
			final Object[] children,
			final Object[] annos); // NO reify or other checking, used by native[De]Serialize

		public abstract String getName();
		public abstract Nonterminalton<? super T> getNonterminalton();
		
		public abstract String getTypeUnparse(); // Nominally opaque representation of the type
		                                         //  for making sure that something nativeSerialized
		                                         //  is still valid.
		public abstract int getChildCount();
		public abstract int getAnnoCount();
		
		public abstract String[] getChildTypes();
		public abstract Lazy[][] getChildInheritedAttributes(); // Originally for autocopy, not currently used
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
		public abstract String getName();

		private final Map<String, Integer> inhOccursIndices = new HashMap<String, Integer>(16, 0.5f);
		private final Map<String, Integer> synOccursIndices = new HashMap<String, Integer>(16, 0.5f);
		public final boolean hasInh(String attrName) {
			return inhOccursIndices.containsKey(attrName);
		}
		public final boolean hasSyn(String attrName) {
			return synOccursIndices.containsKey(attrName);
		}
		public final boolean hasAttribute(String attrName) {
			return synOccursIndices.containsKey(attrName) || inhOccursIndices.containsKey(attrName);
		}
		public final int getInhOccursIndex(String attrName) {
			if (!hasInh(attrName)) {
				throw new SilverError("Attribute " + attrName + " does not occur on " + getName() + ".");
			}
			return inhOccursIndices.get(attrName);
		}
		public final int getSynOccursIndex(String attrName) {
			if (!hasSyn(attrName)) {
				throw new SilverError("Attribute " + attrName + " does not occur on " + getName() + ".");
			}
			return synOccursIndices.get(attrName);
		}
		// write a getAllInh() and getAllSyn() that return the keySet of each of these maps
		public Set<String> getAllInh() {
			return inhOccursIndices.keySet();
		}
		
		public Set<String> getAllSynth() {
			return synOccursIndices.keySet();
		}

		public List<String> alhpabeticalAttributes() {
			Set<String> allAttributesSet = new HashSet<>();
			allAttributesSet.addAll(inhOccursIndices.keySet());
			allAttributesSet.addAll(synOccursIndices.keySet());
			List<String> allAttributesList = new ArrayList<>();
			allAttributesList.addAll(allAttributesSet);
			allAttributesList.sort(null);
			return allAttributesList;

		} 

		public Map<String, Integer> getSynOccursIndices() {
			return synOccursIndices;
		}

		public Map<String, Integer> getInhOccursIndices() {
			return inhOccursIndices;
		}
	}

}