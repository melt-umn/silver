
package silver.modification.ffi.util;

public final class PsubstituteAll extends common.FunctionNode {

	public static final int i_s = 0;
	public static final int i_names = 1;
	public static final int i_results = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_ffi_util_substituteAll;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsubstituteAll(final Object c_s, final Object c_names, final Object c_results) {
		this.child_s = c_s;
		this.child_names = c_names;
		this.child_results = c_results;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_names;
	public final common.ConsCell getChild_names() {
		return (common.ConsCell) (child_names = common.Util.demand(child_names));
	}

	private Object child_results;
	public final common.ConsCell getChild_results() {
		return (common.ConsCell) (child_results = common.Util.demand(child_results));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_names: return getChild_names();
			case i_results: return getChild_results();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_names: return child_names;
			case i_results: return child_results;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:modification:ffi:util:substituteAll";
	}

	public static common.StringCatter invoke(final Object c_s, final Object c_names, final Object c_results) {
		try {
		final common.DecoratedNode context = new PsubstituteAll(c_s, c_names, c_results).decorate();
		//if null(names) then s else substituteAll(substitute(head(names), head(results), s), tail(names), tail(results))
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.modification.ffi.util.PsubstituteAll.i_names))) ? ((common.StringCatter)context.childAsIs(silver.modification.ffi.util.PsubstituteAll.i_s)) : ((common.StringCatter)silver.modification.ffi.util.PsubstituteAll.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstitute.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.modification.ffi.util.PsubstituteAll.i_names))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.modification.ffi.util.PsubstituteAll.i_results))); } }, context.childAsIsLazy(silver.modification.ffi.util.PsubstituteAll.i_s))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.modification.ffi.util.PsubstituteAll.i_names))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.modification.ffi.util.PsubstituteAll.i_results))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:ffi:util:substituteAll", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsubstituteAll.invoke(children[0], children[1], children[2]);
		}
	};
}