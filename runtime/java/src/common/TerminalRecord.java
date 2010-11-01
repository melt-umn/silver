package common;

/**
 * The new terminal representation object. This replaces Terminal, which carried the 
 * full, expensive machinery of DecoratedNode along with it.  This was made possible
 * by the environment changes done in Summer '10, which let us treat terminals as
 * something in their own right, rather than having to pretend they're DNs.
 *
 * @author tedinski
 */
public final class TerminalRecord {
	final public StringCatter filename;
	final public StringCatter lexeme;
	final public Integer line;
	final public Integer column;

	public TerminalRecord(final String lx, final String fn, final Integer ln, final Integer cl) {
		lexeme = new StringCatter(lx);
		filename = new StringCatter(fn);
		line = ln;
		column = cl;
	}

	public TerminalRecord(final String lx, final Integer ln, final Integer cl) {
		lexeme = new StringCatter(lx);
		filename = new StringCatter("_NULL_");
		line = ln;
		column = cl;
	}

	public TerminalRecord(final String lx, final TerminalRecord old) {
		lexeme = new StringCatter(lx);
		filename = old.filename;
		line = old.line;
		column = old.column;
	}
}
