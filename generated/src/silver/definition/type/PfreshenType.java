
package silver.definition.type;

public final class PfreshenType extends common.FunctionNode {

	public static final int i_te = 0;
	public static final int i_tvs = 1;


	public static final Class<?> childTypes[] = { silver.definition.type.NType.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_freshenType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_te] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PfreshenType(final Object c_te, final Object c_tvs) {
		this.child_te = c_te;
		this.child_tvs = c_tvs;

	}

	private Object child_te;
	public final silver.definition.type.NType getChild_te() {
		return (silver.definition.type.NType) (child_te = common.Util.demand(child_te));
	}

	private Object child_tvs;
	public final common.ConsCell getChild_tvs() {
		return (common.ConsCell) (child_tvs = common.Util.demand(child_tvs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_te: return getChild_te();
			case i_tvs: return getChild_tvs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_te: return child_te;
			case i_tvs: return child_tvs;

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
		return "silver:definition:type:freshenType";
	}

	public static silver.definition.type.NType invoke(final Object c_te, final Object c_tvs) {
		try {
		final common.DecoratedNode context = new PfreshenType(c_te, c_tvs).decorate();
		//freshenTypeWith(te, tvs, freshTyVars(length(tvs)))
		return (silver.definition.type.NType)(((silver.definition.type.NType)silver.definition.type.PfreshenTypeWith.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PfreshenType.i_te)), context.childAsIsLazy(silver.definition.type.PfreshenType.i_tvs), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PfreshTyVars.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)core.PlistLength.invoke(context.childAsIsLazy(silver.definition.type.PfreshenType.i_tvs))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:freshenType", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NType> {
		@Override
		public silver.definition.type.NType invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfreshenType.invoke(children[0], children[1]);
		}
	};
}