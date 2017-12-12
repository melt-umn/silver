
package silver.definition.env;

public final class PgetOccursDcl extends common.FunctionNode {

	public static final int i_fnat = 0;
	public static final int i_fnnt = 1;
	public static final int i_e = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_getOccursDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgetOccursDcl(final Object c_fnat, final Object c_fnnt, final Object c_e) {
		this.child_fnat = c_fnat;
		this.child_fnnt = c_fnnt;
		this.child_e = c_e;

	}

	private Object child_fnat;
	public final common.StringCatter getChild_fnat() {
		return (common.StringCatter) (child_fnat = common.Util.demand(child_fnat));
	}

	private Object child_fnnt;
	public final common.StringCatter getChild_fnnt() {
		return (common.StringCatter) (child_fnnt = common.Util.demand(child_fnnt));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fnat: return getChild_fnat();
			case i_fnnt: return getChild_fnnt();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fnat: return child_fnat;
			case i_fnnt: return child_fnnt;
			case i_e: return child_e;

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
		return "silver:definition:env:getOccursDcl";
	}

	public static common.ConsCell invoke(final Object c_fnat, final Object c_fnnt, final Object c_e) {
		try {
		final common.DecoratedNode context = new PgetOccursDcl(c_fnat, c_fnnt, c_e).decorate();
		//occursOnHelp(searchEnvScope(fnnt, e.occursTree), fnat)
		return (common.ConsCell)(((common.ConsCell)silver.definition.env.PoccursOnHelp.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PsearchEnvScope.invoke(context.childAsIsLazy(silver.definition.env.PgetOccursDcl.i_fnnt), context.childAsIsSynthesizedLazy(silver.definition.env.PgetOccursDcl.i_e, silver.definition.env.Init.silver_definition_env_occursTree__ON__silver_definition_env_Env))); } }, context.childAsIsLazy(silver.definition.env.PgetOccursDcl.i_fnat))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:getOccursDcl", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetOccursDcl.invoke(children[0], children[1], children[2]);
		}
	};
}