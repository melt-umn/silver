package edu.umn.cs.melt.ide.silver.misc;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;

/**
 * A wrapper of problem message returned by compiler.
 */
public class Problem {
	
	/**
	 * If an integer value is unknown, it has this value, which shouldn't be 
	 * interpreted literally. 
	 */
	public static final int UNKNOWN = -1;
		
	public static final int INFO = IMarker.SEVERITY_INFO;
	
	public static final int WARNING = IMarker.SEVERITY_WARNING;
	
	public static final int ERROR = IMarker.SEVERITY_ERROR;

	public static final String[] LEVELS = new String[]{"info", "warning", "error"};
	
	/**
	 * Create a Problem from a composite message, which has the format:<br>
	 * <pre>
	 *  GRAMMAR_NAME#FILE_NAME:LINE:COLUMN:LEVEL:MESSAGE
	 * </pre>
	 * Example:
	 * <pre>
	 *  sample:grammar:name#AbstractSyntax.sv:48:5: error: Undeclared attribute 'abc'.
	 * </pre>
	 * @param silverMessage
	 * @deprecated this is historically for Silver language only. Developers should always use 
	 * {@link #createProblem(IProject, String, String, int, int, int, String)}.
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

	/**
	 * Create a problem from given argument set.
	 * 
	 * @param project	the project under which the resource declName+fileName 
	 * 	is found.
	 * @param filePath	the file's path in format "a/b/c", relative to project root
	 * @param fileName	the file's full name, including extension. Note the 
	 * resource can be located under project root using {filePath + "/" + fileName}
	 * @param line
	 * @param column
	 * @param startInd	starting index relative to start of file. Use {@link #UNKNOWN} 
	 * if not available.
	 * @param endInd	ending index relative to start of file. Use {@link #UNKNOWN} 
	 * if not available.
	 * @param severity	such as {@link #ERROR} and {@link #WARNING}
	 * @param message	a human-oriented message that will be shown in IDE.
	 * @return
	 */
	public static Problem createProblem(
		IProject project, 
		String filePath, String fileName, 
		int line, int column, int startInd, int endInd, 
		int severity, String message){
		
		String name = filePath + '/' + fileName;
		IFile file = project.getFile(name);
		
		return new Problem(file, column, line, message, severity, startInd, endInd);
	}

	@Override
	public String toString() {
		return 
			"Problem [file=" + file + 
			", line=" + line + ", column=" + column + 
			", startInd=" + ((startInd!=UNKNOWN)?startInd:"UNKNOWN") +
			", endInd=" + ((endInd!=UNKNOWN)?endInd:"UNKNOWN") +
			", level=" + ((level>=0&&level<LEVELS.length)?LEVELS[level]:"UNKNOWN") + 
			", message=" + message + "]";
	}
	
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
	
	/**
	 * The starting index of the problem; relative to file start.
	 * 
	 * @return
	 */
	public int getStartInd() {
		return startInd;
	}
	
	/**
	 * The ending index of the problem; relative to file start.
	 * 
	 * @return
	 */
	public int getEndInd() {
		return endInd;
	}

	private Problem(IFile file, int column, int line, String message, int level) {
		this.file = file;
		this.column = column;
		this.line = line;
		this.message = message;
		this.level = level;
	}
	
	private Problem(IFile file, int column, int line, String message, int level, int startInd, int endInd) {
		this(file, column, line, message, level);
		this.startInd = startInd;
		this.endInd = endInd;
	}
	
	private IFile file;
	
	private int column;
	
	private int line;
	
	private int startInd = UNKNOWN;
	
	private int endInd = UNKNOWN;
	
	private String message;
	
	private int level;
	
}
