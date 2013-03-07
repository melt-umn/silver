package edu.umn.cs.melt.ide.silver.misc;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;

/**
 * A wrapper of problem message returned by compiler.
 * <p>
 * There are two types of problems, based on its associativity with either file
 * or project. For a file problem, the user should provide project, file, and 
 * location information, in addition to the severity and message. 
 * <p>
 * For a project problem, the user only needs to provide severity and message.
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
	 * Create a file-related problem from given argument set.
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
	public static Problem createFileProblem(
		IProject project, 
		String filePath, String fileName, 
		int line, int column, int startInd, int endInd, 
		int severity, String message){
		
		String name = filePath + '/' + fileName;
		IFile file = project.getFile(name);
		
		return new Problem(file, column, line, message, severity, startInd, endInd, false);
	}
	
	/**
	 * Create a project-level problem.
	 * <p>
	 * A project problem is not associated with any particular file.
	 * 
	 * @param severity	such as {@link #ERROR} and {@link #WARNING}
	 * @param message	a human-oriented message that will be shown in IDE.
	 * @return
	 */
	public static Problem createProjectProblem(int severity, String message){
		return new Problem(null, UNKNOWN, UNKNOWN, message, severity, UNKNOWN, UNKNOWN, true);
	}
	
	/**
	 * Create file problem with unknown indices.
	 */
	private Problem(IFile file, int column, int line, String message, int level) {
		this.file = file;
		this.column = column;
		this.line = line;
		this.message = message;
		this.level = level;
	}
	
	/**
	 * The most primitive constructor.
	 */
	private Problem(IFile file, int column, int line, String message, int level, 
		int startInd, int endInd, boolean projMsg) {
		this(file, column, line, message, level);
		this.startInd = startInd;
		this.endInd = endInd;
		this.projMsg = projMsg;
	}
	
	@Override
	public String toString() {
		return 
			(projMsg) ?
				"Project problem [level=" + ((level>=0&&level<LEVELS.length)?LEVELS[level]:"UNKNOWN") + 
				", message=" + message + "]"
			:	
				"Problem [file=" + file + 
				", line=" + line + ", column=" + column + 
				", startInd=" + ((startInd!=UNKNOWN)?startInd:"UNKNOWN") +
				", endInd=" + ((endInd!=UNKNOWN)?endInd:"UNKNOWN") +
				", level=" + ((level>=0&&level<LEVELS.length)?LEVELS[level]:"UNKNOWN") + 
				", message=" + message + "]";
	}
	
	/**
	 * @return	null if it's a project message.
	 */
	public IFile getFile() {
		return file;
	}

	/**
	 * 1-based column number.
	 * 
	 * @return	{@link #UNKNOWN} if it's a project message.
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * 1-based line number.
	 * 
	 * @return	{@link #UNKNOWN} if it's a project message.
	 */
	public int getLine() {
		return line;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * @return {@link #INFO}|{@link #WARNING}|{@link #ERROR}
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Whether this is a project problem (true) or file problem (false)
	 * 
	 * @return true if it's a project problem.
	 */
	public boolean isProjectProblem(){
		return projMsg;
	}
	
	/**
	 * The starting index of the problem; relative to file start.
	 * 
	 * @return	can be {@link #UNKNOWN}.
	 */
	public int getStartInd() {
		return startInd;
	}
	
	/**
	 * The ending index of the problem; relative to file start.
	 * 
	 * @return	can be {@link #UNKNOWN}.
	 */
	public int getEndInd() {
		return endInd;
	}
	
	private IFile file;
	
	private int column;
	
	private int line;
	
	private int startInd = UNKNOWN;
	
	private int endInd = UNKNOWN;
	
	private String message;
	
	private int level;
	
	private boolean projMsg = false;
	
}
