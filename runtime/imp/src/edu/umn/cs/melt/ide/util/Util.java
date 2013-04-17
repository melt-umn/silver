package edu.umn.cs.melt.ide.util;

import edu.umn.cs.melt.ide.silver.misc.ConsoleLoggingStream;

public final class Util {

	private static ConsoleLoggingStream logger;
	
	public static void setConsoleLoggingStream(ConsoleLoggingStream cls){
		logger = cls;
	}
	
	public static Object info(String msg){
		if(logger!=null){
			logger.info(msg);
		}
		return null;
	}
	
	public static Object error(String msg){
		if(logger!=null){
			logger.error(msg);
		}
		return null;
	}
	
}
