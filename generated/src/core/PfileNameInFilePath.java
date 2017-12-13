
package core;

public final class PfileNameInFilePath extends common.FunctionNode {

	public static final int i_filePath = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__core_fileNameInFilePath;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfileNameInFilePath(final Object c_filePath) {
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
		return "core:fileNameInFilePath";
	}

	public static common.StringCatter invoke(final Object c_filePath) {
		try {
		final common.DecoratedNode context = new PfileNameInFilePath(c_filePath).decorate();
		//if indexOfLastSlash == -1 then filePath else substring(indexOfLastSlash + 1, length(filePath), filePath)
		return (common.StringCatter)((((Integer)context.localAsIs(core.Init.indexOfLastSlash__ON__core_fileNameInFilePath)).equals(Integer.valueOf((int)-1)) ? ((common.StringCatter)context.childAsIs(core.PfileNameInFilePath.i_filePath)) : ((common.StringCatter)core.Psubstring.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.localAsIs(core.Init.indexOfLastSlash__ON__core_fileNameInFilePath)) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((common.StringCatter)((common.StringCatter)context.childAsIs(core.PfileNameInFilePath.i_filePath))).length()); } }, context.childAsIsLazy(core.PfileNameInFilePath.i_filePath)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:fileNameInFilePath", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfileNameInFilePath.invoke(children[0]);
		}
	};
}