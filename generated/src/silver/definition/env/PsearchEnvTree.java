
package silver.definition.env;

public final class PsearchEnvTree extends common.FunctionNode {

	public static final int i_search = 0;
	public static final int i_et = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_searchEnvTree;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PsearchEnvTree(final Object c_search, final Object c_et) {
		this.child_search = c_search;
		this.child_et = c_et;

	}

	private Object child_search;
	public final common.StringCatter getChild_search() {
		return (common.StringCatter) (child_search = common.Util.demand(child_search));
	}

	private Object child_et;
	public final Object getChild_et() {
		return (Object) (child_et = common.Util.demand(child_et));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_search: return getChild_search();
			case i_et: return getChild_et();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_search: return child_search;
			case i_et: return child_et;

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
		return "silver:definition:env:searchEnvTree";
	}

	public static common.ConsCell invoke(final Object c_search, final Object c_et) {
		try {
		final common.DecoratedNode context = new PsearchEnvTree(c_search, c_et).decorate();
		//rtm:lookup(search, et)
		return (common.ConsCell)(((common.ConsCell)silver.util.raw.treemap.Plookup.invoke(context.childAsIsLazy(silver.definition.env.PsearchEnvTree.i_search), context.childAsIsLazy(silver.definition.env.PsearchEnvTree.i_et))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:searchEnvTree", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PsearchEnvTree.invoke(children[0], children[1]);
		}
	};
}