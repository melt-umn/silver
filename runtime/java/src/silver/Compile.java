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
		
		// Step 3 : We *no longer* set SILVER_GEN. Silver defaults automatically.
		
		// Step 4 : We *no longer* set GRAMMAR_PATH. Silver defaults automatically.
		
		// Step 5 : Find silver classes using custom classloader
		
		Method svmain;
		Map<String, String> svutil;
		try {
			svmain = Class.forName("silver.composed.Default.Main",true,cl).getMethod("main", String[].class);
			svutil = (Map<String, String>) Class.forName("common.Util",true,cl).getField("environment").get(null);
		} catch (Throwable t) {
			System.err.println("Failed to find the appropriate classes to start Silver.\n");
			throw new RuntimeException("Couldn't start silver!", t);
		}
		
		// Step 5.1: Amend environment so silver knows where it is located.
		svutil.put("SILVER_HOME", installRoot.getPath());
		
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
