
package silver.driver;

public final class PrenderFileNames extends common.FunctionNode {

	public static final int i_files = 0;
	public static final int i_depth = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_renderFileNames;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PrenderFileNames(final Object c_files, final Object c_depth) {
		this.child_files = c_files;
		this.child_depth = c_depth;

	}

	private Object child_files;
	public final common.ConsCell getChild_files() {
		return (common.ConsCell) (child_files = common.Util.demand(child_files));
	}

	private Object child_depth;
	public final Integer getChild_depth() {
		return (Integer) (child_depth = common.Util.demand(child_depth));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_files: return getChild_files();
			case i_depth: return getChild_depth();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_files: return child_files;
			case i_depth: return child_depth;

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
		return "silver:driver:renderFileNames";
	}

	public static common.StringCatter invoke(final Object c_files, final Object c_depth) {
		try {
		final common.DecoratedNode context = new PrenderFileNames(c_files, c_depth).decorate();
		//if null(files) then "" else if depth >= 7 then "\n\t " ++ renderFileNames(files, 0) else head(files) ++ if null(tail(files)) then "" else " " ++ renderFileNames(tail(files), depth + 1)
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.PrenderFileNames.i_files))) ? (new common.StringCatter("")) : ((((Integer)context.childAsIs(silver.driver.PrenderFileNames.i_depth)) >= Integer.valueOf((int)7)) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("\n\t ")), (common.StringCatter)((common.StringCatter)silver.driver.PrenderFileNames.invoke(context.childAsIsLazy(silver.driver.PrenderFileNames.i_files), Integer.valueOf((int)0)))) : new common.StringCatter((common.StringCatter)((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.driver.PrenderFileNames.i_files))), (common.StringCatter)(((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.PrenderFileNames.i_files))); } })) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)silver.driver.PrenderFileNames.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.PrenderFileNames.i_files))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.driver.PrenderFileNames.i_depth)) + Integer.valueOf((int)1)); } }))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:renderFileNames", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrenderFileNames.invoke(children[0], children[1]);
		}
	};
}