
package core;

public final class PbuiltinLoc extends common.FunctionNode {

	public static final int i_module = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__core_builtinLoc;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PbuiltinLoc(final Object c_module) {
		this.child_module = c_module;

	}

	private Object child_module;
	public final common.StringCatter getChild_module() {
		return (common.StringCatter) (child_module = common.Util.demand(child_module));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_module: return getChild_module();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_module: return child_module;

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
		return "core:builtinLoc";
	}

	public static core.NLocation invoke(final Object c_module) {
		try {
		final common.DecoratedNode context = new PbuiltinLoc(c_module).decorate();
		//txtLoc("Built in from " ++ module)
		return (core.NLocation)(((core.NLocation)new core.PtxtLoc(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Built in from ")), (common.StringCatter)((common.StringCatter)context.childAsIs(core.PbuiltinLoc.i_module))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:builtinLoc", t);
		}
	}

	public static final common.NodeFactory<core.NLocation> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NLocation> {
		@Override
		public core.NLocation invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PbuiltinLoc.invoke(children[0]);
		}
	};
}