package silver;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Map;

public class Compile {
	
	public static final String SILVERJAR = "silver.composed.Default.jar";
	public static final String SILVERRUNTIME = "SilverRuntime.jar";
	public static final String COPPERCOMPILER = "CopperCompiler.jar";
	public static final String COPPERRUNTIME = "CopperRuntime.jar";

	public static File find_home() {
		String result = System.getenv("SILVER_HOME");
		
		File jarsLocation;
		
		if(result == null || result.equals("")) {
			URI jarLocation;
			try {
				jarLocation = Compile.class.getProtectionDomain().getCodeSource().getLocation().toURI();
			} catch(Throwable t) {
				System.err.println("Could not find install location!\n");
				System.err.println("This is likely an installation problem." +
						"  If this error is expected due to an unusual environment setup," +
						" you may run silver.composed.Default.jar directly instead of going through RunSilver.jar\n");
				throw new RuntimeException("Failed to find install location.", t);
			}
			
			// Jars are the directory where this jar is located.
			jarsLocation = new File(jarLocation).getParentFile();
		} else {
			// Jars are under './jars/' from where SILVER_HOME is pointing
			jarsLocation = new File(new File(result), "jars");
		}
		
		File testFile = new File(jarsLocation, SILVERJAR);
		if(!testFile.exists()) {
			System.err.println("Could not find install location!\n");
			System.err.println("Tried " + jarsLocation.getPath() + " but couldn't find " + SILVERJAR);
			System.exit(1);
		}
		
		return jarsLocation;
	}
	
	/**
	 * Sets up the runtime environment as automatically as possible before
	 * actually executing silver.
	 * 
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException {
		String java_gen = System.getenv("SILVER_GEN");
		String grammar_path = System.getenv("GRAMMAR_PATH");
		
		// Step 1 : attempt to determine where we are
		File jarsLocation = find_home();
		File installRoot = jarsLocation.getParentFile();
		
		// Step 2 : create a new classloader with all the necessary jars
		
		URL urls[] = {
				new File(jarsLocation, COPPERCOMPILER).toURI().toURL(),
				new File(jarsLocation, COPPERRUNTIME).toURI().toURL(),
				new File(jarsLocation, SILVERRUNTIME).toURI().toURL(),
				new File(jarsLocation, SILVERJAR).toURI().toURL()
				};
		ClassLoader cl = Thread.currentThread().getContextClassLoader();
		cl = new URLClassLoader(urls, cl);
		
		
		// Step 3 : Check and set SILVER_JAVA
		
		if(java_gen == null || java_gen.equals("")) {
			File jgLocation = new File(installRoot, "generated");
			if(!jgLocation.canWrite()) {
				// TODO: We should check for any -J on the command line
				
				System.err.println("No SILVER_GEN location was supplied in the environment.");
				System.err.println("In addition, the path inferred (" + jgLocation + ") doesn't exist or is not writable by the user!\n");
				System.err.println("Either fix the install so the inferred location is writable," +
						" or specify a location using the SILVER_GEN environment variable.\n");
				System.exit(-1);
			}
			java_gen = jgLocation.getPath();
		}
		
		// Step 4 : Check and set GRAMMAR_PATH
		
		File gpLocation = new File(installRoot, "grammars");
		if(!gpLocation.canRead()) {
			System.err.println("Couldn't find the silver/grammars location to include in the GRAMMAR_PATH.");
			System.err.println("(Tried " + gpLocation + ")\n");
			System.err.println("If you actually intend to run silver without the default path," +
					" run silver.composed.Default.jar directly instead of RunSilver.jar\n");
			System.exit(-2);
		}
		
		// Step 5 : Find silver classes
		
		Method svmain;
		Map<String, String> svutil;
		try {
			svmain = Class.forName("silver.composed.Default.Main",true,cl).getMethod("main", String[].class);
			svutil = (Map<String, String>) Class.forName("common.Util",true,cl).getField("environment").get(null);
		} catch (Throwable t) {
			System.err.println("Failed to find the appropriate classes to start Silver.\n");
			throw new RuntimeException("Couldn't start silver!", t);
		}
		
		// Step 5.1: amend the environment. (we can't change the actual environment in java
		// for some reason, so we depend on altering an environment map in the runtime library)
		svutil.put("SILVER_HOME", installRoot.getPath());
		svutil.put("SILVER_GEN", java_gen);
		if(grammar_path == null || grammar_path.equals("")) {
			svutil.put("GRAMMAR_PATH", gpLocation.getPath() + ":./");
		} else {
			svutil.put("GRAMMAR_PATH", grammar_path + ":" + gpLocation.getPath() + ":./");
		}
		
		// Step 5.2: GO!
		try {
			svmain.invoke(null, (Object)args);
		} catch (InvocationTargetException e) {
			
			throw new RuntimeException("Silver runtime error", e);
			
		} catch (Throwable t) {
			System.err.println("Error invoking silver.  Installation problem?\n");
			throw new RuntimeException("Silver invocation error", t);
		}
		
	}

}
