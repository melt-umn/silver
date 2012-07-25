package edu.umn.cs.melt.ide.copper;

import edu.umn.cs.melt.copper.runtime.logging.CopperParserException;

public class EnhancedCopperParserException extends CopperParserException {

	private static final long serialVersionUID = -1944965587680442905L;
	
	public static final int UNKNOWN = -1;
	
	private int line = UNKNOWN;
	
	private int column = UNKNOWN;
	
	private long offset = UNKNOWN;
	
	private String fileName = null;

	public String toString(){
		return "(" + fileName + ") line=" + line + ";column=" + column + ";offset=" + offset;
	}
	
	public EnhancedCopperParserException(int line, int column, long offset, String fileName) {
		super("parsing error");
		setErrorInfo(line, column, offset, fileName);
	}
	
	public EnhancedCopperParserException(String message, Throwable cause, int line, int column, long offset, String fileName) {
		super(message, cause);
		setErrorInfo(line, column, offset, fileName);
	}

	public EnhancedCopperParserException(String message, int line, int column, long offset, String fileName) {
		super(message);
		setErrorInfo(line, column, offset, fileName);
	}

	public EnhancedCopperParserException(Throwable cause, int line, int column, long offset, String fileName) {
		super(cause);
		setErrorInfo(line, column, offset, fileName);
	}
	
	private void setErrorInfo(int line, int column, long offset, String fileName){
		this.line = line;
		this.column = column;
		this.offset = offset;
		this.fileName = fileName;
	}
	
	/**
	 * Line in the source file for the error.
	 * @return the line number; -1 if unknown
	 */
	public int getLine() {
		return line;
	}

	/**
	 * Column in the source file for the error.
	 * @return the column number; -1 if unknown
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Character offset in the source file for the error.
	 * @return the character offset; -1 if unknown
	 */
	public long getOffset() {
		return offset;
	}

	/**
	 * The name of the source file where error is raised.
	 * @return the file name; null if unknown
	 */
	public String getFileName() {
		return fileName;
	}

}
