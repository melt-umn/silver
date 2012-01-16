package common;

import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;

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
	final public Integer endLine;
	final public Integer endColumn;

	public TerminalRecord(final String lx, final VirtualLocation vl){
		lexeme = new StringCatter(lx);
		filename = new StringCatter(vl.getFileName());
		line = vl.getLine();
		column = vl.getColumn();
		vl.defaultUpdate(lx);
		endLine = vl.getLine();
		endColumn = vl.getColumn();
	}
			
	public TerminalRecord(final String lx, final String fn, final Integer ln, final Integer cl) {
		lexeme = new StringCatter(lx);
		filename = new StringCatter(fn);
		line = ln;
		column = cl;
		endLine = line;
		endColumn = column;
	}

	public TerminalRecord(final String lx, final Integer ln, final Integer cl) {
		this(lx, "_NULL_", ln, cl);
	}

	public TerminalRecord(final String lx, final TerminalRecord old) {
		this(lx, old.filename.toString(), old.line, old.column);
	}
}
