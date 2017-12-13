
package silver.definition.type;

public final class PfreshenTypeWith extends common.FunctionNode {

	public static final int i_te = 0;
	public static final int i_tvs = 1;
	public static final int i_ntvs = 2;


	public static final Class<?> childTypes[] = { silver.definition.type.NType.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_freshenTypeWith;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PfreshenTypeWith(final Object c_te, final Object c_tvs, final Object c_ntvs) {
		this.child_te = c_te;
		this.child_tvs = c_tvs;
		this.child_ntvs = c_ntvs;

	}

	private Object child_te;
	public final silver.definition.type.NType getChild_te() {
		return (silver.definition.type.NType) (child_te = common.Util.demand(child_te));
	}

	private Object child_tvs;
	public final common.ConsCell getChild_tvs() {
		return (common.ConsCell) (child_tvs = common.Util.demand(child_tvs));
	}

	private Object child_ntvs;
	public final common.ConsCell getChild_ntvs() {
		return (common.ConsCell) (child_ntvs = common.Util.demand(child_ntvs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_te: return getChild_te();
			case i_tvs: return getChild_tvs();
			case i_ntvs: return getChild_ntvs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_te: return child_te;
			case i_tvs: return child_tvs;
			case i_ntvs: return child_ntvs;

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
		return "silver:definition:type:freshenTypeWith";
	}

	public static silver.definition.type.NType invoke(final Object c_te, final Object c_tvs, final Object c_ntvs) {
		try {
		final common.DecoratedNode context = new PfreshenTypeWith(c_te, c_tvs, c_ntvs).decorate();
		//performSubstitution(te, zipVarsIntoSubstitution(tvs, ntvs))
		return (silver.definition.type.NType)(((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PfreshenTypeWith.i_te)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PzipVarsIntoSubstitution.invoke(context.childAsIsLazy(silver.definition.type.PfreshenTypeWith.i_tvs), context.childAsIsLazy(silver.definition.type.PfreshenTypeWith.i_ntvs))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:freshenTypeWith", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NType> {
		@Override
		public silver.definition.type.NType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfreshenTypeWith.invoke(children[0], children[1], children[2]);
		}
	};
}