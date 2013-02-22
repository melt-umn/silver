package common;

import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;
import core.NLocation;
import core.Ploc;

/**
 * The terminal representation object, containing a lexeme and a location.
 *
 * @author tedinski
 */
public final class TerminalRecord {
	final public StringCatter lexeme;
	final public NLocation location;
	
	/**
	 * Construct a terminal from a Copper VirtualLocation, used during parse tree construction.
	 */
	public TerminalRecord(final String lexeme, final VirtualLocation vl, final int index, final int endIndex){
		this.lexeme = new StringCatter(lexeme);
		final int line = vl.getLine();
		final int column = vl.getColumn();
		vl.defaultUpdate(lexeme);
		this.location = new Ploc(
				new StringCatter(vl.getFileName()),
				line,
				column,
				vl.getLine(),
				vl.getColumn(),
				index,
				endIndex);
	}
			
	/**
	 * The standard manual constructor for a terminal object.
	 */
	public TerminalRecord(final StringCatter lexeme, final NLocation location) {
		this.lexeme = lexeme;
		this.location = location;
	}
	
	// It'd be nice to just directly access its children, but we don't actually know
	// that our NLocation is Ploc. :(
	private Object getFromLoc(int syn) {
		DecoratedNode d = location.decorate(TopNode.singleton, (Lazy[])null);
		return d.synthesized(syn);
	}
	public Integer getLine() {
		return (Integer)getFromLoc(core.Init.core_line__ON__core_Location);
	}
	public Integer getColumn() {
		return (Integer)getFromLoc(core.Init.core_column__ON__core_Location);
	}
	public StringCatter getFilename() {
		return (StringCatter)getFromLoc(core.Init.core_filename__ON__core_Location);
	}
}
