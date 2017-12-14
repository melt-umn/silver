
package core;

public final class PexplodeNormal extends common.FunctionNode {

	public static final int i_sep = 0;
	public static final int i_str = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__core_explodeNormal;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PexplodeNormal(final Object c_sep, final Object c_str) {
		this.child_sep = c_sep;
		this.child_str = c_str;

	}

	private Object child_sep;
	public final common.StringCatter getChild_sep() {
		return (common.StringCatter) (child_sep = common.Util.demand(child_sep));
	}

	private Object child_str;
	public final common.StringCatter getChild_str() {
		return (common.StringCatter) (child_str = common.Util.demand(child_str));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sep: return getChild_sep();
			case i_str: return getChild_str();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sep: return child_sep;
			case i_str: return child_str;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "core:explodeNormal";
	}

	public static common.ConsCell invoke(final Object c_sep, final Object c_str) {
		try {
		final common.DecoratedNode context = new PexplodeNormal(c_sep, c_str).decorate();
		//if i == -1 then [ str ] else (substring(0, i, str) :: explodeNormal(sep, substring(i + length(sep), length(str), str)))
		return (common.ConsCell)((((Integer)context.localAsIs(core.Init.i__ON__core_explodeNormal)).equals(Integer.valueOf((int)-1)) ? ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(core.PexplodeNormal.i_str), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), context.localAsIsLazy(core.Init.i__ON__core_explodeNormal), context.childAsIsLazy(core.PexplodeNormal.i_str))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PexplodeNormal.invoke(context.childAsIsLazy(core.PexplodeNormal.i_sep), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.localAsIs(core.Init.i__ON__core_explodeNormal)) + Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(core.PexplodeNormal.i_sep))).length())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(core.PexplodeNormal.i_str))).length()); } }, context.childAsIsLazy(core.PexplodeNormal.i_str))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:explodeNormal", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PexplodeNormal.invoke(children[0], children[1]);
		}
	};
}