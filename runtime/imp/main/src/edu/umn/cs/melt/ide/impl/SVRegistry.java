package edu.umn.cs.melt.ide.impl;

/**
 * The silver-generated ide plugin will register itself with us as a result of startup.
 * 
 * <p>NOTE: Currently, we assume that this package is EMBEDDED WITHIN the generated plugin.
 * i.e. if there are multiple silver plugins loaded into eclipse, there are multiple
 * private silver ide runtimes, each private to its plugin. As a result, we assume there's
 * just one implementation to care about.
 * 
 * <p>This is a poor design, and should be fixed eventually, but I'm not sure what the best
 * way to do it is. So for now I have not even tried to do so.
 * 
 * <p>Okay: here's a better design. When we use the runtime's classes from plugin.xml, we can
 * usually pass properties to it. I.e. we could say "use the silver-imp runtime builder class
 * and pass it parameter 'plugin-name-to-look-up-in-registry = whatever' to use to look things
 * up here.
 */
public class SVRegistry {
	private static SVInterface plugin = null;
	
	public static SVInterface get() {
		if(plugin == null) {
			throw new UnsupportedOperationException("No plugin has been registered yet!");
		}
		return plugin;
	}
	
	public static void register(SVInterface impl) {
		if(plugin != null) {
			throw new UnsupportedOperationException("A plugin has already been registered!");
		}
		plugin = impl;
	}
}
