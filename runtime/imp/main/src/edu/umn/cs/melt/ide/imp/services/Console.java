package edu.umn.cs.melt.ide.imp.services;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

import edu.umn.cs.melt.ide.impl.SVInterface;
import edu.umn.cs.melt.ide.impl.SVRegistry;
import edu.umn.cs.melt.ide.silver.misc.ConsoleLoggingStream;


public class Console {

	private static ConsoleLoggingStream clsCache = null;
	
	/**
	 * Get the {@link ConsoleLoggingStream} associated with this IDE.
	 * 
	 * @return
	 */
	public static ConsoleLoggingStream getConsoleLoggingStream() {
		SVInterface sv = SVRegistry.get(); // perhaps in the future we'll add a parameter to this function?
		String id = sv.name() + " Console";
		if(clsCache == null) {
			clsCache = ConsoleLoggingStream.getStream(getConsoleStream(id), getConsoleStream(id));
		}
    	
		return clsCache;
    }
    
	/**
	 * Get a raw {@link MessageConsoleStream}. {@link #getConsoleLoggingStream()} provides
	 * a more convenient wrapper for this stream.
	 * 
	 * @return
	 */
	private static MessageConsoleStream getConsoleStream(String consoleID) {
    	MessageConsole console = findConsole(consoleID);
    	ConsolePlugin.getDefault().getConsoleManager().showConsoleView(console);
    	return console.newMessageStream();
    }
	
    private static MessageConsole findConsole(String consoleName) {
        IConsoleManager consoleManager = ConsolePlugin.getDefault().getConsoleManager();
        IConsole[] consoles = consoleManager.getConsoles();
        
        for(int i = 0; i < consoles.length; i++) {
            if(consoles[i].getName().equals(consoleName))
                return (MessageConsole)consoles[i];
        }

        MessageConsole myConsole = new MessageConsole(consoleName, null);
        consoleManager.addConsoles(new IConsole[] { myConsole });
        return myConsole;
    }
    
}
