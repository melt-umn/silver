package common;

import java.util.*;

import common.exceptions.SilverInternalError;

/**
 * Right now this is an ugly hack for getting autocopy to work properly.
 * 
 * <p>In the future, this may be extended to support more general kinds of "decorators".
 * 
 * See "Decorated attribute grammars: Attribute evaluation meets strategic programming"
 * by Kats, Sloane, Visser  to get an idea of what this might look like.
 * 
 * @author tedinski
 */
abstract public class Decorator {
	abstract public void decorate(Prodleton production);
	
	public static void applyDecorators(List<Decorator> nonterminal, Prodleton production) {
		for( Decorator decorator : nonterminal ) {
			decorator.decorate(production);
		}
		
		// STATS: Uncomment to enable statistics
		// TODO: OH GOD THIS IS A HACK
		//Statistics.hook();
	}
	
	// Default actions for autocopy
	static protected void decorateAutoCopy(final Prodleton production, final String attribute) {
		
		// Find the index of the inh attribute on this production's NT
		int attrindex;
		String[] oi = production.getOccursInh();
		attrindex = Arrays.asList(oi).indexOf(attribute);
		if(attrindex == -1)
			throw new SilverInternalError("Attribute doesn't occur on NT it is supposed to?");
		
		// Create the lazy that we'll be putting on children.
		Lazy eq = new acLazy(attrindex);
		
		// Get information about the children
		String childTypes[];
		Lazy[][] inheritedAttributes;
		childTypes = production.getChildTypes();
		inheritedAttributes = production.getChildInheritedAttributes();

		for(int i = 0; i < childTypes.length; i++) {
			if (childTypes[i] == null) continue;

			String[] occurs;
			occurs = RTTI.getProdletonsForNonterminal(childTypes[i]).get(0).getOccursInh();

			int loc = Arrays.asList(occurs).indexOf(attribute);
			if(loc != -1) {
				// The nonterminal of this child contains the attribute in question
				if(inheritedAttributes[i][loc] == null) {
					// ... and this production does not define that attribute for this child!
					//System.out.println("Applying decorator to child # " + i + " in production " + production.getName() + " which is of type" + childTypes[i].getName());
					inheritedAttributes[i][loc] = eq;
				}
			}
		}
	}
	
	private static class acLazy implements Lazy {
		private final int attr;
		acLazy(final int s) {
			attr = s;
		}
		public Object eval(common.DecoratedNode context) {
			return context.inherited(attr);
		}
	}
	
}
