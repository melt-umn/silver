package common.exceptions;

/**
 * Thrown instead of calling System.exit, in order to allow composition
 * of Silver code with outside systems in a slightly nicer fashion.
 * 
 * @author tedinski
 */
@SuppressWarnings("serial")
public class SilverExit extends SilverException {

	private int exitCode;
	
	public SilverExit(int exitCode) {
		super("Exit code: " + exitCode);
		this.exitCode = exitCode;
	}

	public int getExitCode() {
		return exitCode;
	}

}
