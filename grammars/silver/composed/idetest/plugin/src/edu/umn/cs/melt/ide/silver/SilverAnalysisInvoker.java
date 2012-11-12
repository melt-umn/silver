package edu.umn.cs.melt.ide.silver;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.preferences.IPreferencesService;

import silver.composed.idetest.imp.SILVERPlugin;
import edu.umn.cs.melt.ide.silver.preferences.SilverConstants;

/**
 * A helper class used to invoke the method Analyze(String[]) in the language implementation jar.
 */
public class SilverAnalysisInvoker {
	
	public static final String SILVERJAR = "silver.composed.idetest.jar";
	public static final String SILVERRUNTIME = "SilverRuntime.jar";
	public static final String COPPERCOMPILER = "CopperCompiler.jar";
	public static final String COPPERRUNTIME = "CopperRuntime.jar";

	private static File findHome() {
		IPreferencesService prefService = SILVERPlugin.getInstance().getPreferencesService();
		String silverHome = prefService.getStringPreference(SilverConstants.P_SILVERHOME);
		
		File jarsLocation = null;

		//Jars are under './jars/' from where SILVER_HOME is pointing
		jarsLocation = new File(new File(silverHome), "jars");
		
		//Sanity check
		File testFile = new File(jarsLocation, SILVERJAR);
		if(!testFile.exists()) {
			preparingError = "BUILD FAILED: the Silver home is not set properly. " +
					"(Please check the consistency between the setting of Silver " +
					"preferences and the installed location of Silver in the OS) ";
			return null;
		}
		
		return jarsLocation;
	}
	
	@SuppressWarnings("unchecked")
	static boolean prepare(){
		if(prepared){
			return true;
		}
		
		// Step 1 : attempt to determine where we are
		File jarsLocation = findHome();
		if(jarsLocation==null){
			return false;
		}
		
		File installRoot = jarsLocation.getParentFile();
		
		// Step 2 : create a new classloader with all the necessary jars
		URL[] urls = null;
		try {
		urls = new URL[]{
				new File(jarsLocation, COPPERCOMPILER).toURI().toURL(),
				new File(jarsLocation, COPPERRUNTIME).toURI().toURL(),
				new File(jarsLocation, SILVERRUNTIME).toURI().toURL(),
				new File(jarsLocation, SILVERJAR).toURI().toURL()
			};
		} catch (MalformedURLException e){
			preparingError = "BUILD FAILED: incomplete components for running Silver." +
				"(Please check the integrity of Silver installation. Re-install Silver if necessary.)";
			return false;
		}
		ClassLoader cl = new URLClassLoader(urls, null);
		
		// Step 3 : Check and set SILVER_GEN
		String java_gen = null;
		File jgLocation = new File(installRoot, "generated");
		if(!jgLocation.canWrite()) {
			preparingError = "BUILD FAILED: the location for code egenration (" +
				jgLocation.getAbsolutePath() + ") doesn't exist or is not writable by the user. " +
				"(Please check the folder to make sure it's existent and writable)";
			return false;
		}
		java_gen = jgLocation.getPath();
		
		// Step 4 : Check and set GRAMMAR_PATH
		gpLocation = new File(installRoot, "grammars");
		if(!gpLocation.canRead()) {
			preparingError = "BUILD FAILED: the folder of core grammars (" +
				gpLocation.getAbsolutePath() + ") doesn't exist or is not readable by the user. " +
				"(Please check the folder to make sure it's existent and readable)";
			return false;
		}
		
		// Step 5 : Find silver classes
		try {
			svmain = Class.forName("silver.composed.idetest.Analyze",true,cl).getMethod("analyze", String[].class);
			svutil = (Map<String, String>) Class.forName("common.Util",true,cl).getField("environment").get(null);
		} catch (Throwable t) {
			preparingError = "BUILD FAILED: failed to find the appropriate class to start Silver. " +
				"(Please check the integrity of Silver installation. Re-install Silver if necessary.)";
			t.printStackTrace();
			return false;
		}
		
		// Step 5.1: amend the environment. (we can't change the actual environment in java
		// for some reason, so we depend on altering an environment map in the runtime library)
		svutil.put("SILVER_HOME", installRoot.getPath());
		svutil.put("SILVER_GEN", java_gen);

		return prepared = true;
	}
	
	private static String preparingError;
	
	@SuppressWarnings("unchecked")
	public static boolean build(
			String[] args, String projectPath, ConsoleLoggingStream clstream, IProgressMonitor monitor,
			AnalysisHandler handler) {
		
		if(!prepare()){
			clstream.error(preparingError);
			return false;
		}
		
		svutil.put("GRAMMAR_PATH", gpLocation.getPath() + ":" + projectPath);	
		
		// Step 5.2: GO!
		try {
			List<String> list = (List<String>)svmain.invoke(null, (Object)args);
			return handler.handle(list);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			clstream.error("BUILD FAILED: Silver encountered a runtime error. ");
			return false;
		} catch (Throwable t) {
			clstream.error("BUILD FAILED: failed to invoke Silver. " +
				"(Please check the integrity of Silver installation. Re-install Silver if necessary.)");
			return false;
		}
		
	}
	
	private static Method svmain;
	
	private static Map<String, String> svutil;
	
	private static File gpLocation;
	
	private static boolean prepared = false;
	
	public static interface AnalysisHandler {
		/**
		 * Handle the message list returned by analyzer.
		 * 
		 * @param list the message list. 
		 * @return true if the build is considered successfull; false otherwise
		 */
		boolean handle(List<String> list);
	}

}
