package edu.umn.cs.melt.ide.silver.problem;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;

/**
 * A wrapper of problem message returned by compiler.
 */
public class Problem {
	
	public static final int INFO = IMarker.SEVERITY_INFO;
	
	public static final int WARNING = IMarker.SEVERITY_WARNING;
	
	public static final int ERROR = IMarker.SEVERITY_ERROR;

	public static final String[] LEVELS = new String[]{"info", "warning", "error"};
	
	public IFile getFile() {
		return file;
	}

	public int getColumn() {
		return column;
	}

	public int getLine() {
		return line;
	}

	public String getMessage() {
		return message;
	}

	public int getLevel() {
		return level;
	}

	private IFile file;
	
	private int column;
	
	private int line;
	
	private String message;
	
	private int level;

	private Problem(IFile file, int column, int line, String message, int level) {
		this.file = file;
		this.column = column;
		this.line = line;
		this.message = message;
		this.level = level;
	}
	
	/**
	 * Create a Problem from Silver message, which has the format:<br>
	 * <pre>
	 *  GRAMMAR_NAME#FILE_NAME:LINE:COLUMN:LEVEL:MESSAGE
	 * </pre>
	 * Example:
	 * <pre>
	 *  sample:grammar:name#AbstractSyntax.sv:48:5: error: Undeclared attribute 'undeca'.
	 * </pre>
	 * @param silverMessage
	 * @return null if parsing is failed.
	 */
	public static Problem createProblem(IProject project, String silverMessage){
		int sep = silverMessage.indexOf('#');
		if(sep==-1){
			//System.err.println("No '#' found!");
			return null;
		}
		String name = silverMessage.substring(0, sep);
		name = name.replace(':', '/');

		String rest = silverMessage.substring(sep+1, silverMessage.length());
		String[] parts = rest.split(":");
		if(parts.length < 5){
			//System.err.println("No enough ':'s!");
			return null;	
		}
		
		name = name + '/' + parts[0];
		IFile file = project.getFile(name);
		
		int line = 0;
		int column = 0;
		try {
			line = Integer.parseInt(parts[1]);
			column = Integer.parseInt(parts[2]);
		} catch (NumberFormatException e) {
			//System.err.println("Should be a number for line and column.");
			//e.printStackTrace();
			return null;
		}
		
		int level = INFO;
		String levelStr = parts[3].trim().toLowerCase();	
		if("warning".equals(levelStr)){
			level = WARNING;
		} else if ("error".equals(levelStr)) {
			level = ERROR;
		}
		
		int len = parts[0].length() + parts[1].length() + parts[2].length() + parts[3].length() + 4;
		String message = rest.substring(len).trim();
			
		return new Problem(file, column, line, message, level);
	}

	@Override
	public String toString() {
		return 
			"Problem [file=" + file 
			+ ", line=" + line
			+ ", column=" + column 
			+ ", level=" + level 
			+ ", message=" + message + "]";
	}
	
}
