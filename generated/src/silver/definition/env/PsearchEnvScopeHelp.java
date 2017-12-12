
package silver.definition.env;

public final class PsearchEnvScopeHelp extends common.FunctionNode {

	public static final int i_search = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_searchEnvScopeHelp;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsearchEnvScopeHelp(final Object c_search, final Object c_e) {
		this.child_search = c_search;
		this.child_e = c_e;

	}

	private Object child_search;
	public final common.StringCatter getChild_search() {
		return (common.StringCatter) (child_search = common.Util.demand(child_search));
	}

	private Object child_e;
	public final common.ConsCell getChild_e() {
		return (common.ConsCell) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_search: return getChild_search();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_search: return child_search;
			case i_e: return child_e;

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
		return "silver:definition:env:searchEnvScopeHelp";
	}

	public static common.ConsCell invoke(final Object c_search, final Object c_e) {
		try {
		final common.DecoratedNode context = new PsearchEnvScopeHelp(c_search, c_e).decorate();
		//if null(e) then [] else searchEnvTree(search, head(e)) ++ searchEnvScopeHelp(search, tail(e))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.env.PsearchEnvScopeHelp.i_e))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvTree.invoke(context.childAsIsLazy(silver.definition.env.PsearchEnvScopeHelp.i_search), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(silver.definition.env.PsearchEnvScopeHelp.i_e))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvScopeHelp.invoke(context.childAsIsLazy(silver.definition.env.PsearchEnvScopeHelp.i_search), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.env.PsearchEnvScopeHelp.i_e))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:searchEnvScopeHelp", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsearchEnvScopeHelp.invoke(children[0], children[1]);
		}
	};
}