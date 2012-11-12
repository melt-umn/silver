package edu.umn.cs.melt.ide.silver;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.imp.preferences.IPreferencesService;

import edu.umn.cs.melt.ide.silver.preferences.SilverConstants;

import silver.composed.idetest.imp.SILVERPlugin;

/**
 * This invoker simply runs the following command on OS and redirect 
 * whatever the process outputs to Eclipse console:
 * <p>
 * <code>java -jar $SILVER_HOME/RunSilver.jar --clean -I $SOURCE_FOLDER $GRAMMAR_TO_BUILD</code>
 * <p>
 * @author Ming Zhou, mzhou@umn.edu
 */
public class SilverCompilerInvoker {
	
	public static final String RUNSILVERJAR = "RunSilver.jar";
	
	private static final int BUILD_CANCELLED = -99;
    
	/**
	 * Build the Silver project
	 * 
	 * @param args
	 * @param projectPath
	 * @param clstream
	 * 
	 * @result true if build was successful; false if failed.
	 */
	public static boolean build(
		String[] args, String projectPath, ConsoleLoggingStream clstream, IProgressMonitor monitor) {
		//try { Thread.sleep(5000);} catch (InterruptedException e2) {}
		IPreferencesService prefService = SILVERPlugin.getInstance().getPreferencesService();
		String silverHome = prefService.getStringPreference(SilverConstants.P_SILVERHOME);
		
		//Sanity check
		String silverJar = silverHome + "/jars/" + RUNSILVERJAR;
		File file = new File(silverHome);
		if(!file.exists()){
			clstream.error("BUILD FAILED: the Silver home is not set properly. " +
				"(Please check the consistency between the setting of Silver " +
				"preferences and the installed location of Silver in the OS) ");
			return false;
		}		
		
		List<String> argList = new ArrayList<String>();
		argList.add("java");
		argList.add("-jar");
		argList.add(silverJar);
		argList.add("--clean");
		for(String arg:args){
			argList.add(arg);
		}
		ProcessBuilder pb = new ProcessBuilder(argList);
		pb.directory(new File(projectPath));
		
	    Map<String, String> env = pb.environment();
	    env.put("SILVER_HOME", silverHome);
	    
	    pb.redirectErrorStream(true);
	    Process process = null;
		try {
			process = pb.start();
		} catch (IOException e) {
			clstream.error("BUILD FAILED: failed to start Silver compiler. " +
				"(Please check if Silver has been properly installed) ");
			clstream.error("[Cause of failure: " + e.toString() + "]");
			return false;
		}
	    InputStream is = process.getInputStream();
	    InputStreamReader isr = new InputStreamReader(is);
	    BufferedReader br = new BufferedReader(isr);

	    String line;
	    try {
			while ((line = br.readLine()) != null) {
			  System.out.println(line);
			}
		} catch (IOException e) {
			//Ignore
		}
		
		int result = BUILD_CANCELLED;
		//Poll if the task is cancelled by user
		while(!monitor.isCanceled()){
			try {
				result = process.exitValue();
				break;
			} catch (IllegalThreadStateException e) {
				//The process is not finished yet, wait for a while.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					//Ignore
				}
			}		
		}

		if(monitor.isCanceled() && result == BUILD_CANCELLED){
			process.destroy();
			clstream.info("BUILD CANCELLED\n");
			return false;
		} 
		
		if(result==0){
			clstream.info("BUILD SUCCESSFUL\n");
			return true;
		} else {
			clstream.error("BUILD FAILED. Error code = " + result + "\n");
			return false;
		}
		
		/*
		try {
			int result = process.waitFor();
			if(result==0){
				clstream.info("BUILD SUCCESSFUL\n");
				return true;
			} else {
				clstream.error("BUILD FAILED. Error code = " + result + "\n");
				return false;
			}
		} catch (InterruptedException e) {
			clstream.error("BUILD FAILURE: an error is encountered while running " +
				"Silver compiler. (Please check if Silver has been properly installed) ");
			clstream.error("[Cause of failure: " + e.toString() + "]");
			return false;
		}
		*/

	}

}