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
	abstract public void decorate(Class<?> production);
	
	public static void applyDecorators(List<Decorator> nonterminal, Class<?> production) {
		for( Decorator decorator : nonterminal ) {
			decorator.decorate(production);
		}
		
		// STATS: Uncomment to enable statistics
		// TODO: OH GOD THIS IS A HACK
		//Statistics.hook();
	}
	
	// Default actions for autocopy
	static protected void decorateAutoCopy(final Class<?> production, final String attribute) {
		
		// Find the index of the inh attribute on this production's NT
		int attrindex;
		try {
			String[] oi = (String[])production.getField("occurs_inh").get(null);
			attrindex = Arrays.asList(oi).indexOf(attribute);
			if(attrindex == -1)
				throw new SilverInternalError("Attribute doesn't occur on NT it is supposed to?");
		} catch(Throwable t) {
			throw new SilverInternalError("Error while applying autocopy decorators.", t);
		}
		
		// Create the lazy that we'll be putting on children.
		Lazy eq = new acLazy(attrindex);
		
		// Get information about the children
		Class<?> childTypes[];
		Lazy[][] inheritedAttributes;
		try {
			childTypes = (Class<?>[]) production.getField("childTypes").get(null);
			inheritedAttributes = (Lazy[][])production.getField("childInheritedAttributes").get(null);
		} catch (Throwable t) {
			throw new SilverInternalError("Attempting to decorate a nonproduction?",t);
		}

		for(int i = 0; i < childTypes.length; i++) {
			String[] occurs;
			try {
				occurs = (String[])childTypes[i].getField("occurs_inh").get(null);
			} catch (NoSuchFieldException e) {
				// This is a non-error. We expect there to be children that aren't NTs
				continue;
			} catch (Throwable t) {
				throw new SilverInternalError("Problem fetching class information through reflection.",t);
			}
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
