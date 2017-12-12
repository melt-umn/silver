
package core;

// top::Location ::= filename::String line::Integer column::Integer endLine::Integer endColumn::Integer index::Integer endIndex::Integer 
public final class Ploc extends core.NLocation {

	public static final int i_filename = 0;
	public static final int i_line = 1;
	public static final int i_column = 2;
	public static final int i_endLine = 3;
	public static final int i_endColumn = 4;
	public static final int i_index = 5;
	public static final int i_endIndex = 6;


	public static final Class<?> childTypes[] = {common.StringCatter.class,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class,Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__core_loc;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[core.NLocation.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[core.NLocation.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Ploc(final Object c_filename, final Object c_line, final Object c_column, final Object c_endLine, final Object c_endColumn, final Object c_index, final Object c_endIndex) {
		super();
		this.child_filename = c_filename;
		this.child_line = c_line;
		this.child_column = c_column;
		this.child_endLine = c_endLine;
		this.child_endColumn = c_endColumn;
		this.child_index = c_index;
		this.child_endIndex = c_endIndex;

	}

	private Object child_filename;
	public final common.StringCatter getChild_filename() {
		return (common.StringCatter) (child_filename = common.Util.demand(child_filename));
	}

	private Object child_line;
	public final Integer getChild_line() {
		return (Integer) (child_line = common.Util.demand(child_line));
	}

	private Object child_column;
	public final Integer getChild_column() {
		return (Integer) (child_column = common.Util.demand(child_column));
	}

	private Object child_endLine;
	public final Integer getChild_endLine() {
		return (Integer) (child_endLine = common.Util.demand(child_endLine));
	}

	private Object child_endColumn;
	public final Integer getChild_endColumn() {
		return (Integer) (child_endColumn = common.Util.demand(child_endColumn));
	}

	private Object child_index;
	public final Integer getChild_index() {
		return (Integer) (child_index = common.Util.demand(child_index));
	}

	private Object child_endIndex;
	public final Integer getChild_endIndex() {
		return (Integer) (child_endIndex = common.Util.demand(child_endIndex));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_filename: return getChild_filename();
			case i_line: return getChild_line();
			case i_column: return getChild_column();
			case i_endLine: return getChild_endLine();
			case i_endColumn: return getChild_endColumn();
			case i_index: return getChild_index();
			case i_endIndex: return getChild_endIndex();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_filename: return child_filename;
			case i_line: return child_line;
			case i_column: return child_column;
			case i_endLine: return child_endLine;
			case i_endColumn: return child_endColumn;
			case i_index: return child_index;
			case i_endIndex: return child_endIndex;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
	}

	@Override
	public common.Lazy[] getLocalInheritedAttributes(final int key) {
		return localInheritedAttributes[key];
	}

	@Override
	public common.Lazy[] getChildInheritedAttributes(final int key) {
		return childInheritedAttributes[key];
	}

	@Override
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production core:loc erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
	}

	@Override
	public common.Lazy getLocal(final int key) {
		return localAttributes[key];
	}

	@Override
	public final int getNumberOfLocalAttrs() {
		return num_local_attrs;
	}

	@Override
	public final String getNameOfLocalAttr(final int index) {
		return occurs_local[index];
	}

	@Override
	public String getName() {
		return "core:loc";
	}

	static void initProductionAttributeDefinitions() {
		// top.filename = filename
		core.Ploc.synthesizedAttributes[core.Init.core_filename__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(core.Ploc.i_filename)); } };
		// top.line = line
		core.Ploc.synthesizedAttributes[core.Init.core_line__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(core.Ploc.i_line)); } };
		// top.column = column
		core.Ploc.synthesizedAttributes[core.Init.core_column__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(core.Ploc.i_column)); } };
		// top.endLine = endLine
		core.Ploc.synthesizedAttributes[core.Init.core_endLine__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(core.Ploc.i_endLine)); } };
		// top.endColumn = endColumn
		core.Ploc.synthesizedAttributes[core.Init.core_endColumn__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(core.Ploc.i_endColumn)); } };
		// top.index = index
		core.Ploc.synthesizedAttributes[core.Init.core_index__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(core.Ploc.i_index)); } };
		// top.endIndex = endIndex
		core.Ploc.synthesizedAttributes[core.Init.core_endIndex__ON__core_Location] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(core.Ploc.i_endIndex)); } };

	}

	public static final common.NodeFactory<Ploc> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Ploc> {

		@Override
		public Ploc invoke(final Object[] children, final Object[] annotations) {
			return new Ploc(children[0], children[1], children[2], children[3], children[4], children[5], children[6]);
		}
	};

}
