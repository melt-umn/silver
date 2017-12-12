
package silver.definition.env.env_parser;

// top::ILocation ::= filename::IName ',' line::Num_t ',' column::Num_t ',' endline::Num_t ',' endcolumn::Num_t ',' index::Num_t ',' endindex::Num_t 
public final class PaLocationInfo extends silver.definition.env.env_parser.NILocation {

	public static final int i_filename = 0;
	public static final int i__G_11 = 1;
	public static final int i_line = 2;
	public static final int i__G_9 = 3;
	public static final int i_column = 4;
	public static final int i__G_7 = 5;
	public static final int i_endline = 6;
	public static final int i__G_5 = 7;
	public static final int i_endcolumn = 8;
	public static final int i__G_3 = 9;
	public static final int i_index = 10;
	public static final int i__G_1 = 11;
	public static final int i_endindex = 12;


	public static final Class<?> childTypes[] = {silver.definition.env.env_parser.NIName.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.TNum_t.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.TNum_t.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.TNum_t.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.TNum_t.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.TNum_t.class,silver.definition.env.env_parser.TComma_t.class,silver.definition.env.env_parser.TNum_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_env_parser_aLocationInfo;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.env_parser.NILocation.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.env_parser.NILocation.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[13][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_filename] = new common.Lazy[silver.definition.env.env_parser.NIName.num_inh_attrs];

	}

	public PaLocationInfo(final Object c_filename, final Object c__G_11, final Object c_line, final Object c__G_9, final Object c_column, final Object c__G_7, final Object c_endline, final Object c__G_5, final Object c_endcolumn, final Object c__G_3, final Object c_index, final Object c__G_1, final Object c_endindex) {
		super();
		this.child_filename = c_filename;
		this.child__G_11 = c__G_11;
		this.child_line = c_line;
		this.child__G_9 = c__G_9;
		this.child_column = c_column;
		this.child__G_7 = c__G_7;
		this.child_endline = c_endline;
		this.child__G_5 = c__G_5;
		this.child_endcolumn = c_endcolumn;
		this.child__G_3 = c__G_3;
		this.child_index = c_index;
		this.child__G_1 = c__G_1;
		this.child_endindex = c_endindex;

	}

	private Object child_filename;
	public final silver.definition.env.env_parser.NIName getChild_filename() {
		return (silver.definition.env.env_parser.NIName) (child_filename = common.Util.demand(child_filename));
	}

	private Object child__G_11;
	public final silver.definition.env.env_parser.TComma_t getChild__G_11() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_11 = common.Util.demand(child__G_11));
	}

	private Object child_line;
	public final silver.definition.env.env_parser.TNum_t getChild_line() {
		return (silver.definition.env.env_parser.TNum_t) (child_line = common.Util.demand(child_line));
	}

	private Object child__G_9;
	public final silver.definition.env.env_parser.TComma_t getChild__G_9() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_9 = common.Util.demand(child__G_9));
	}

	private Object child_column;
	public final silver.definition.env.env_parser.TNum_t getChild_column() {
		return (silver.definition.env.env_parser.TNum_t) (child_column = common.Util.demand(child_column));
	}

	private Object child__G_7;
	public final silver.definition.env.env_parser.TComma_t getChild__G_7() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_7 = common.Util.demand(child__G_7));
	}

	private Object child_endline;
	public final silver.definition.env.env_parser.TNum_t getChild_endline() {
		return (silver.definition.env.env_parser.TNum_t) (child_endline = common.Util.demand(child_endline));
	}

	private Object child__G_5;
	public final silver.definition.env.env_parser.TComma_t getChild__G_5() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_endcolumn;
	public final silver.definition.env.env_parser.TNum_t getChild_endcolumn() {
		return (silver.definition.env.env_parser.TNum_t) (child_endcolumn = common.Util.demand(child_endcolumn));
	}

	private Object child__G_3;
	public final silver.definition.env.env_parser.TComma_t getChild__G_3() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_index;
	public final silver.definition.env.env_parser.TNum_t getChild_index() {
		return (silver.definition.env.env_parser.TNum_t) (child_index = common.Util.demand(child_index));
	}

	private Object child__G_1;
	public final silver.definition.env.env_parser.TComma_t getChild__G_1() {
		return (silver.definition.env.env_parser.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_endindex;
	public final silver.definition.env.env_parser.TNum_t getChild_endindex() {
		return (silver.definition.env.env_parser.TNum_t) (child_endindex = common.Util.demand(child_endindex));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_filename: return getChild_filename();
			case i__G_11: return getChild__G_11();
			case i_line: return getChild_line();
			case i__G_9: return getChild__G_9();
			case i_column: return getChild_column();
			case i__G_7: return getChild__G_7();
			case i_endline: return getChild_endline();
			case i__G_5: return getChild__G_5();
			case i_endcolumn: return getChild_endcolumn();
			case i__G_3: return getChild__G_3();
			case i_index: return getChild_index();
			case i__G_1: return getChild__G_1();
			case i_endindex: return getChild_endindex();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_filename: return child_filename;
			case i__G_11: return child__G_11;
			case i_line: return child_line;
			case i__G_9: return child__G_9;
			case i_column: return child_column;
			case i__G_7: return child__G_7;
			case i_endline: return child_endline;
			case i__G_5: return child__G_5;
			case i_endcolumn: return child_endcolumn;
			case i__G_3: return child__G_3;
			case i_index: return child_index;
			case i__G_1: return child__G_1;
			case i_endindex: return child_endindex;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 13;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:env_parser:aLocationInfo erroneously claimed to forward");
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
		return "silver:definition:env:env_parser:aLocationInfo";
	}

	static void initProductionAttributeDefinitions() {
		// top.alocation = loc(filename.aname, toInt(line.lexeme), toInt(column.lexeme), toInt(endline.lexeme), toInt(endcolumn.lexeme), toInt(index.lexeme), toInt(endindex.lexeme))
		silver.definition.env.env_parser.PaLocationInfo.synthesizedAttributes[silver.definition.env.env_parser.Init.silver_definition_env_env_parser_alocation__ON__silver_definition_env_env_parser_ILocation] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NLocation)new core.Ploc(context.childDecoratedSynthesizedLazy(silver.definition.env.env_parser.PaLocationInfo.i_filename, silver.definition.env.env_parser.Init.silver_definition_env_env_parser_aname__ON__silver_definition_env_env_parser_IName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((silver.definition.env.env_parser.TNum_t)context.childAsIs(silver.definition.env.env_parser.PaLocationInfo.i_line)).lexeme).toString()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((silver.definition.env.env_parser.TNum_t)context.childAsIs(silver.definition.env.env_parser.PaLocationInfo.i_column)).lexeme).toString()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((silver.definition.env.env_parser.TNum_t)context.childAsIs(silver.definition.env.env_parser.PaLocationInfo.i_endline)).lexeme).toString()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((silver.definition.env.env_parser.TNum_t)context.childAsIs(silver.definition.env.env_parser.PaLocationInfo.i_endcolumn)).lexeme).toString()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((silver.definition.env.env_parser.TNum_t)context.childAsIs(silver.definition.env.env_parser.PaLocationInfo.i_index)).lexeme).toString()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((silver.definition.env.env_parser.TNum_t)context.childAsIs(silver.definition.env.env_parser.PaLocationInfo.i_endindex)).lexeme).toString()); } })); } };

	}

	public static final common.NodeFactory<PaLocationInfo> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaLocationInfo> {

		@Override
		public PaLocationInfo invoke(final Object[] children, final Object[] annotations) {
			return new PaLocationInfo(children[0], children[1], children[2], children[3], children[4], children[5], children[6], children[7], children[8], children[9], children[10], children[11], children[12]);
		}
	};

}
