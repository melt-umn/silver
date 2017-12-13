
package silver.definition.env;

public final class PgetProdsForNt extends common.FunctionNode {

	public static final int i_fnnt = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_getProdsForNt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgetProdsForNt(final Object c_fnnt, final Object c_e) {
		this.child_fnnt = c_fnnt;
		this.child_e = c_e;

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
			case i_fnnt: return getChild_fnnt();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fnnt: return child_fnnt;
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
		return "silver:definition:env:getProdsForNt";
	}

	public static common.ConsCell invoke(final Object c_fnnt, final Object c_e) {
		try {
		final common.DecoratedNode context = new PgetProdsForNt(c_fnnt, c_e).decorate();
		//searchEnvAll(fnnt, e.prodsForNtTree)
		return (common.ConsCell)(((common.ConsCell)silver.definition.env.PsearchEnvAll.invoke(context.childAsIsLazy(silver.definition.env.PgetProdsForNt.i_fnnt), context.childAsIsSynthesizedLazy(silver.definition.env.PgetProdsForNt.i_e, silver.definition.env.Init.silver_definition_env_prodsForNtTree__ON__silver_definition_env_Env))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:getProdsForNt", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetProdsForNt.invoke(children[0], children[1]);
		}
	};
}