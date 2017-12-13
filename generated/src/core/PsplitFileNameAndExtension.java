
package core;

public final class PsplitFileNameAndExtension extends common.FunctionNode {

	public static final int i_filePath = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__core_splitFileNameAndExtension;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsplitFileNameAndExtension(final Object c_filePath) {
		this.child_filePath = c_filePath;

	}

	private Object child_filePath;
	public final common.StringCatter getChild_filePath() {
		return (common.StringCatter) (child_filePath = common.Util.demand(child_filePath));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_filePath: return getChild_filePath();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_filePath: return child_filePath;

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
		return "core:splitFileNameAndExtension";
	}

	public static core.NPair invoke(final Object c_filePath) {
		try {
		final common.DecoratedNode context = new PsplitFileNameAndExtension(c_filePath).decorate();
		//if indexOfLastDot == -1 then pair(filePath, "") else pair(substring(0, indexOfLastDot, filePath), substring(indexOfLastDot + 1, length(filePath), filePath))
		return (core.NPair)((((Integer)context.localAsIs(core.Init.indexOfLastDot__ON__core_splitFileNameAndExtension)).equals(Integer.valueOf((int)-1)) ? ((core.NPair)new core.Ppair(context.childAsIsLazy(core.PsplitFileNameAndExtension.i_filePath), (new common.StringCatter("")))) : ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), context.localAsIsLazy(core.Init.indexOfLastDot__ON__core_splitFileNameAndExtension), context.childAsIsLazy(core.PsplitFileNameAndExtension.i_filePath))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.localAsIs(core.Init.indexOfLastDot__ON__core_splitFileNameAndExtension)) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(core.PsplitFileNameAndExtension.i_filePath))).length()); } }, context.childAsIsLazy(core.PsplitFileNameAndExtension.i_filePath))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:splitFileNameAndExtension", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsplitFileNameAndExtension.invoke(children[0]);
		}
	};
}