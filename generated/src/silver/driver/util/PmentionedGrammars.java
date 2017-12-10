
package silver.driver.util;

public final class PmentionedGrammars extends common.FunctionNode {

	public static final int i_r = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_mentionedGrammars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmentionedGrammars(final Object c_r) {
		this.child_r = c_r;

	}

	private Object child_r;
	public final common.DecoratedNode getChild_r() {
		return (common.DecoratedNode) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_r: return child_r;

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
		return "silver:driver:util:mentionedGrammars";
	}

	public static common.ConsCell invoke(final Object c_r) {
		try {
		final common.DecoratedNode context = new PmentionedGrammars(c_r).decorate();
		//makeSet(r.moduleNames ++ concat(r.condBuild) ++ r.optionalGrammars)
		return (common.ConsCell)(((common.ConsCell)silver.util.PmakeSet.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childAsIsSynthesizedLazy(silver.driver.util.PmentionedGrammars.i_r, silver.driver.util.Init.silver_definition_env_moduleNames__ON__silver_driver_util_RootSpec), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pconcat.invoke(context.childAsIsSynthesizedLazy(silver.driver.util.PmentionedGrammars.i_r, silver.driver.util.Init.silver_definition_env_condBuild__ON__silver_driver_util_RootSpec))); } }, context.childAsIsSynthesizedLazy(silver.driver.util.PmentionedGrammars.i_r, silver.driver.util.Init.silver_definition_env_optionalGrammars__ON__silver_driver_util_RootSpec))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:mentionedGrammars", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmentionedGrammars.invoke(children[0]);
		}
	};
}