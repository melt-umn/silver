package common;

import edu.umn.cs.melt.copper.runtime.engines.semantics.VirtualLocation;
import silver.core.NLocation;
import silver.core.Ploc;
import silver.core.Alocation;

/**
 * The terminal representation object, containing a lexeme and a location.
 *
 * @author tedinski
 */
public abstract class Terminal implements Typed {
	final public StringCatter lexeme;
	final public NLocation location;
	
	/**
	 * Construct a terminal from a Copper VirtualLocation, used during parse tree construction.
	 */
	public Terminal(final String lexeme, final VirtualLocation vl, final int index, final int endIndex){
		this.lexeme = new StringCatter(lexeme);
		final int line = vl.getLine();
		final int column = vl.getColumn();
		vl.defaultUpdate(lexeme);
		this.location = new Ploc(new StringCatter(vl.getFileName()),
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
	public Terminal(final StringCatter lexeme, final NLocation location) {
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
		return (Integer)getFromLoc(silver.core.Init.silver_core_line__ON__silver_core_Location);
	}
	public Integer getColumn() {
		return (Integer)getFromLoc(silver.core.Init.silver_core_column__ON__silver_core_Location);
	}
	public StringCatter getFilename() {
		return (StringCatter)getFromLoc(silver.core.Init.silver_core_filename__ON__silver_core_Location);
	}
	public Integer getStartOffset() {
		return (Integer)getFromLoc(silver.core.Init.silver_core_index__ON__silver_core_Location);
	}
	public Integer getEndOffset() {
		return (Integer)getFromLoc(silver.core.Init.silver_core_endIndex__ON__silver_core_Location);
	}
	
	// This is a utility that I put here because why not. Perhaps it should be moved?
	public static NLocation span(final NLocation a, final NLocation b) {
		final DecoratedNode x = a.decorate(TopNode.singleton, (Lazy[])null);
		final DecoratedNode y = b.decorate(TopNode.singleton, (Lazy[])null);
		
		return new Ploc(x.synthesized(silver.core.Init.silver_core_filename__ON__silver_core_Location),
				x.synthesized(silver.core.Init.silver_core_line__ON__silver_core_Location),
				x.synthesized(silver.core.Init.silver_core_column__ON__silver_core_Location),
				y.synthesized(silver.core.Init.silver_core_endLine__ON__silver_core_Location),
				y.synthesized(silver.core.Init.silver_core_endColumn__ON__silver_core_Location),
				x.synthesized(silver.core.Init.silver_core_index__ON__silver_core_Location),
				y.synthesized(silver.core.Init.silver_core_endIndex__ON__silver_core_Location));
	}
	// Ditto
	public static NLocation createSpan(final Object[] children, VirtualLocation l, int index) {
		if(children.length == 0) {
			return new Ploc(new StringCatter(l.getFileName()), l.getLine(), l.getColumn(), l.getLine(), l.getColumn(), index, index);
		} else if(children.length == 1) {
			return extractLocation(children[0]);
		} else {
			return span(extractLocation(children[0]), extractLocation(children[children.length-1]));
		}
	}
	public static NLocation extractLocation(Object o) {
		if(o instanceof Terminal) {
			return ((Terminal)o).location;
		} else if(o instanceof Alocation) {
			return (NLocation) ((Alocation)o).getAnno_silver_core_location();
		} else if(o instanceof Tracked) {
			silver.core.NOriginInfo oi = ((Tracked)o).getOrigin();
			if (oi!=null && oi instanceof silver.core.PparsedOriginInfo) {
				return ((silver.core.PparsedOriginInfo)oi).getChild_source();
			}
		}
		// TODO: a better error, maybe? Eh, it should never happen.
		throw new RuntimeException("Attempting to extract location from locationless object: "+o.toString());
	}

	/**
	 * Obtains the type of this terminal.
	 * @returns The full name of this terminal type (e.g.
	 *   "silver:definition:core:Id_t")
	 */
	public abstract String getName();

	/**
	 * Obtains the lexer classes this terminal belongs to.
	 * @returns The classes, as an array of interned strings in sorted
	 *   order.
	 */
	public abstract String[] getLexerClasses();
	
	@Override
	public final BaseTypeRep getType() {
		return new BaseTypeRep(getName());
	}
}
