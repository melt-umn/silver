package edu.umn.cs.melt.ide.util;

import org.eclipse.ant.core.AntRunner;
import org.eclipse.core.runtime.CoreException;

import edu.umn.cs.melt.ide.silver.misc.ConsoleLoggingStream;

/**
 * This utility class is an API collection used mainly by Silver code (through
 * foreign code block). 
 */
public final class Util {

	/* IDE Console */
	
	private static ConsoleLoggingStream CONSOLE_LOGGER;
	
	public static void setConsoleLoggingStream(ConsoleLoggingStream cls){
		CONSOLE_LOGGER = cls;
	}
	
	public static Object info(String msg){
		if(CONSOLE_LOGGER!=null){
			CONSOLE_LOGGER.info(msg);
		}
		return null;
	}
	
	public static Object error(String msg){
		if(CONSOLE_LOGGER!=null){
			CONSOLE_LOGGER.error(msg);
		}
		return null;
	}
	
	/* Apache Ant tool */
	
	public static Object ant(String buildFile, String arguments, String target){
		
		ANT_RUNNER.ant(buildFile, arguments, target);

		/*
		AntRunner runner = new AntRunner();
		runner.setBuildFileLocation(buildFile);
		runner.setArguments(arguments);
		if(target != null && !"".equals(target.trim())){
			runner.setExecutionTargets(new String[]{target});
		}
		try {
			runner.run();
		} catch (CoreException e) {
			e.printStackTrace();
		}
		*/
		
		return null;
	}
	
	private static IAntRunnable ANT_RUNNER;
	
	public static void setAntRunnable(IAntRunnable iat){
		ANT_RUNNER = iat;
	}
	
	public static interface IAntRunnable {
		void ant(String buildFile, String arguments, String target);
	}
}
