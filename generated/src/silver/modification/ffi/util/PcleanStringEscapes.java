
package silver.modification.ffi.util;

public final class PcleanStringEscapes extends common.FunctionNode {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_ffi_util_cleanStringEscapes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcleanStringEscapes(final Object c_s) {
		this.child_s = c_s;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:modification:ffi:util:cleanStringEscapes";
	}

	public static common.StringCatter invoke(final Object c_s) {
		try {
		final common.DecoratedNode context = new PcleanStringEscapes(c_s).decorate();
		//if i == -1 then s else substring(0, i, s) ++ (if c == "n" then "\n" else if c == "t" then "\t" else c) ++ cleanStringEscapes(substring(i + 2, length(s), s))
		return (common.StringCatter)((((Integer)context.localAsIs(silver.modification.ffi.util.Init.i__ON__silver_modification_ffi_util_cleanStringEscapes)).equals(Integer.valueOf((int)-1)) ? ((common.StringCatter)context.childAsIs(silver.modification.ffi.util.PcleanStringEscapes.i_s)) : new common.StringCatter((common.StringCatter)((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), context.localAsIsLazy(silver.modification.ffi.util.Init.i__ON__silver_modification_ffi_util_cleanStringEscapes), context.childAsIsLazy(silver.modification.ffi.util.PcleanStringEscapes.i_s))), (common.StringCatter)new common.StringCatter((common.StringCatter)(((common.StringCatter)context.localAsIs(silver.modification.ffi.util.Init.c__ON__silver_modification_ffi_util_cleanStringEscapes)).equals((new common.StringCatter("n"))) ? (new common.StringCatter("\n")) : (((common.StringCatter)context.localAsIs(silver.modification.ffi.util.Init.c__ON__silver_modification_ffi_util_cleanStringEscapes)).equals((new common.StringCatter("t"))) ? (new common.StringCatter("\t")) : ((common.StringCatter)context.localAsIs(silver.modification.ffi.util.Init.c__ON__silver_modification_ffi_util_cleanStringEscapes)))), (common.StringCatter)((common.StringCatter)silver.modification.ffi.util.PcleanStringEscapes.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.localAsIs(silver.modification.ffi.util.Init.i__ON__silver_modification_ffi_util_cleanStringEscapes)) + Integer.valueOf((int)2)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.ffi.util.PcleanStringEscapes.i_s))).length()); } }, context.childAsIsLazy(silver.modification.ffi.util.PcleanStringEscapes.i_s))); } }))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:ffi:util:cleanStringEscapes", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcleanStringEscapes.invoke(children[0]);
		}
	};
}