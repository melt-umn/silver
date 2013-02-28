package common;

import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;
import core.NLocation;
import core.Ploc;
import silver.langutil.location.Alocation;

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
	
	// This is a utility that I put here because why not. Perhaps it should be moved?
	public static NLocation span(final NLocation a, final NLocation b) {
		final DecoratedNode x = a.decorate(TopNode.singleton, (Lazy[])null);
		final DecoratedNode y = b.decorate(TopNode.singleton, (Lazy[])null);
		
		return new Ploc(
				x.synthesized(core.Init.core_filename__ON__core_Location),
				x.synthesized(core.Init.core_line__ON__core_Location),
				x.synthesized(core.Init.core_column__ON__core_Location),
				y.synthesized(core.Init.core_endLine__ON__core_Location),
				y.synthesized(core.Init.core_endColumn__ON__core_Location),
				x.synthesized(core.Init.core_index__ON__core_Location),
				y.synthesized(core.Init.core_endIndex__ON__core_Location));
	}
	// Ditto
	public static NLocation createSpan(final Object[] children, VirtualLocation l, int index) {
		if(children.length == 0) {
			return new Ploc(l.getFileName(), l.getLine(), l.getColumn(), l.getLine(), l.getColumn(), index, index);
		} else if(children.length == 1) {
			return extractLocation(children[0]);
		} else {
			return span(extractLocation(children[0]), extractLocation(children[children.length-1]));
		}
	}
	public static NLocation extractLocation(Object o) {
		if(o instanceof TerminalRecord) {
			return ((TerminalRecord)o).location;
		} else if(o instanceof Alocation) {
			return (NLocation) ((Alocation)o).getAnno_silver_langutil_location_location();
		}
		// TODO: a better error, maybe? Eh, it should never happen.
		throw new RuntimeException("Attempting to extract location from locationless object");
	}
}
