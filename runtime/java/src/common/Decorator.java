package common;

import java.util.*;

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
	@SuppressWarnings("unchecked")
	static protected void decorateAutoCopy(Class<?> production, String attribute) {
		Class<?> childTypes[];
		Map<Object, Map<String, common.Lazy>> inheritedAttributes;
		try {
			childTypes = (Class<?>[]) production.getField("childTypes").get(null);
			inheritedAttributes = (Map<Object, Map<String, common.Lazy>>)production.getField("inheritedAttributes").get(null);
		} catch (Throwable t) {
			throw new RuntimeException("Attempting to decorate a nonproduction?",t);
		}

		for(int i = 0; i < childTypes.length; i++) {
			Set<String> occurs;
			try {
				occurs = (Set<String>)childTypes[i].getField("occurs").get(null);
			} catch (NoSuchFieldException e) {
				// This is a non-error. We expect there to be things that aren't productions.
				continue;
			} catch (Throwable t) {
				throw new RuntimeException("Problem fetching class information through reflection.",t);
			}
			if(occurs.contains(attribute)) {
				// The nonterminal of this child contains the attribute in question
				if( ! inheritedAttributes.get(i).containsKey(attribute)) {
					// ... and this production does not define that attribute for this child!
					//System.out.println("Applying decorator to child # " + i + " in production " + production.getName() + " which is of type" + childTypes[i].getName());
					inheritedAttributes.get(i).put(attribute, new acLazy(attribute));
				}
			}
		}
	}
	
	private static class acLazy implements Lazy {
		private String attr;
		acLazy(String s) {
			attr = s;
		}
		public Object eval(common.DecoratedNode context) {
			return context.inherited(attr);
		}
	}
	
}
