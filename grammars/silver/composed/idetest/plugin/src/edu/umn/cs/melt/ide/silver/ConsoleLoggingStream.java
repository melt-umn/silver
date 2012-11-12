package edu.umn.cs.melt.ide.silver;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.MessageConsoleStream;

public class ConsoleLoggingStream {

	private MessageConsoleStream infoStream;
	
	private MessageConsoleStream errorStream;
	
	final static Display DISPLAY = Display.getDefault();
	
	private final static int SCREEN_WIDTH = 80;
	
	private static Color RED;
	
	private ConsoleLoggingStream(
			MessageConsoleStream infoMCStream,
			MessageConsoleStream errorMCStream) {
		infoStream = infoMCStream;
		errorStream = errorMCStream;
		DISPLAY.syncExec(new Runnable() {
            public void run() {
            	errorStream.setColor(RED);
            }
        });
	}
	
	public void info(String message){
		println(infoStream, message);
	}
	
	public void error(String message){
		println(errorStream, message);
	}
	
	private void println(MessageConsoleStream stream, String message) {
		String[] strings = splitString(message);
		for(String s:strings){
			stream.println(s);
		}
	}

	private String[] splitString(String message) {
		int length = message.length();
		int block = length / SCREEN_WIDTH;
		int totalMinusRemainder = block * SCREEN_WIDTH;
		int remainderLength = length - totalMinusRemainder;
		char[] chars = message.toCharArray();
		
		String[] strings = new String[block+1];
		int offset = 0;
		for(int i = 0; i < block; i++, offset += SCREEN_WIDTH){
			strings[i] = String.copyValueOf(chars, offset, SCREEN_WIDTH);
		}
		strings[block] = String.copyValueOf(chars, offset, remainderLength);
			
		return strings;
	}
	
	private static void init(){
		DISPLAY.syncExec(new Runnable() {
            public void run() {
            	RED = DISPLAY.getSystemColor(SWT.COLOR_RED);
            }
        });
	}


	private static ConsoleLoggingStream SINGLETON = null;
	
	public static ConsoleLoggingStream getStream(
			MessageConsoleStream infoMCStream,
			MessageConsoleStream errorMCStream) {
		
		if(SINGLETON == null){
			init();
			SINGLETON = new ConsoleLoggingStream(infoMCStream, errorMCStream);
		}
		
		return SINGLETON;
	}

}
