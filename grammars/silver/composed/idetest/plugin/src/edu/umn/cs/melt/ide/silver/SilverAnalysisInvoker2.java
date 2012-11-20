package edu.umn.cs.melt.ide.silver;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.preferences.IPreferencesService;

import silver.composed.idetest.imp.SILVERPlugin;
import edu.umn.cs.melt.ide.silver.SilverAnalysisInvoker.AnalysisHandler;
import edu.umn.cs.melt.ide.silver.preferences.SilverConstants;

/**
 * A helper class used to invoke the method Analyze(String[]) in the language 
 * implementation jar.
 * <p>
 * Used for all-in-one plugin.
 */
public class SilverAnalysisInvoker2 {

	private static File findHome() {
		IPreferencesService prefService = SILVERPlugin.getInstance().getPreferencesService();
		String silverHome = prefService.getStringPreference(SilverConstants.P_SILVERHOME);
		
		File home = new File(silverHome);
		
		//Sanity check
		//TODO
		
		return home;
	}
	
	public static boolean prepare(){
		if(prepared){
			return true;
		}
		
		// Step 1 : Get the Silver Home
		File home = findHome();
		if(home==null){
			return false;
		}
		
		// Step 3 : Check and set SILVER_GEN
		String java_gen = null;
		File jgLocation = new File(home, "generated");
		if(!jgLocation.canWrite()) {
			preparingError = "BUILD FAILED: the location for code egenration (" +
				jgLocation.getAbsolutePath() + ") doesn't exist or is not writable by the user. " +
				"(Please check the folder to make sure it's existent and writable)";
			return false;
		}
		java_gen = jgLocation.getPath();
		
		// Step 4 : Check and set GRAMMAR_PATH
		gpLocation = new File(home, "grammars");
		if(!gpLocation.canRead()) {
			preparingError = "BUILD FAILED: the folder of core grammars (" +
				gpLocation.getAbsolutePath() + ") doesn't exist or is not readable by the user. " +
				"(Please check the folder to make sure it's existent and readable)";
			return false;
		}
		
		// Step 5.1: amend the environment. (we can't change the actual environment in java
		// for some reason, so we depend on altering an environment map in the runtime library)
		common.Util.environment.put("SILVER_HOME", home.getPath());
		common.Util.environment.put("SILVER_GEN", java_gen);

		return prepared = true;
	}
	
	private static String preparingError;
	
	public static boolean build(
			String[] args, String projectPath, ConsoleLoggingStream clstream, IProgressMonitor monitor,
			AnalysisHandler handler) {
		
		if(!prepare()){
			clstream.error(preparingError);
			return false;
		}
		
		common.Util.environment.put("GRAMMAR_PATH", gpLocation.getPath() + ":" + projectPath);
		
		// Step 5.2: GO!
		try {
			List<String> list = silver.composed.idetest.Analyze.analyze(args);
			return handler.handle(list);
		} catch (Exception t) {
			t.printStackTrace();
			clstream.error("BUILD FAILED: failed to invoke Silver. " +
				"(Please check the integrity of Silver installation. Re-install Silver if necessary.)");
			return false;
		}
		
	}
	
	private static File gpLocation;
	
	private static boolean prepared = false;

}
