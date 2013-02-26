package edu.umn.cs.melt.ide.dev;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class solely for development. Will create log files under "ECLIPSE_HOME/syslog/".
 * 
 * @author Ming
 */
final public class SystemLogger {
	
	//Enabling switch
	private static boolean ON = false;

	//Flag indicating whether the logger has been start()ed
	private static boolean STARTED = false;
	
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	
	private static final String LOG_FOLDER = "syslog";
	
	private static final String LOG_FILE_PREFIX= "log";
	
	private static final String LOG_FILE_SUFFIX= "txt";
	
	private static PrintStream STANDARD_OUT;
	
	private static PrintStream STANDARD_ERR;
	
	private SystemLogger(){
		
	}
	
	public synchronized static void start(){
		if(!ON || STARTED){
			return;
		}
		File root = new File("./"+LOG_FOLDER);
		try {
			//System.out.println(root.getCanonicalPath());
			boolean hasFolder = root.exists();
			if(!hasFolder){
				hasFolder = root.mkdir();
			}
			if(!hasFolder){
				throw new IOException("cannot create system log folder.");
			}
			
			String logFileName = 
				LOG_FILE_PREFIX + "_" + 
				DATE_FORMAT.format(new Date()).toString() + 
				"." + LOG_FILE_SUFFIX;
			
			File logFile = new File(root.getCanonicalPath() + "/" + logFileName);
			
			if(!logFile.createNewFile()){
				throw new IOException("cannot create log file.");
			}
			
			PrintStream filePrintStream = new PrintStream(new FileOutputStream(logFile));
			STANDARD_OUT = System.out;
			STANDARD_ERR = System.err;
			System.setOut(filePrintStream);
			System.setErr(filePrintStream);
			
			STARTED = true;
		} catch (IOException e) {
			e.printStackTrace();
			STARTED = false;
		}
	}
	
	public synchronized static void stop(){
		if(!ON || !STARTED){
			return;
		}
		System.out.close();
		System.err.close();
		System.setOut(STANDARD_OUT);
		System.setErr(STANDARD_ERR);
		STARTED = false;
	}

	public synchronized static void setEnabled(boolean b) {
		if(!STARTED){
			ON = b;		
		}
	}
	
}
