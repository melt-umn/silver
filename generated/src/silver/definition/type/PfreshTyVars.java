
package silver.definition.type;

public final class PfreshTyVars extends common.FunctionNode {

	public static final int i_n = 0;


	public static final Class<?> childTypes[] = { Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_freshTyVars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfreshTyVars(final Object c_n) {
		this.child_n = c_n;

	}

	private Object child_n;
	public final Integer getChild_n() {
		return (Integer) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;

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
		return "silver:definition:type:freshTyVars";
	}

	public static common.ConsCell invoke(final Object c_n) {
		try {
		final common.DecoratedNode context = new PfreshTyVars(c_n).decorate();
		//if n > 0 then (freshTyVar() :: freshTyVars(n - 1)) else []
		return (common.ConsCell)(((((Integer)context.childAsIs(silver.definition.type.PfreshTyVars.i_n)) > Integer.valueOf((int)0)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NTyVar)silver.definition.type.PfreshTyVar.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PfreshTyVars.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.definition.type.PfreshTyVars.i_n)) - Integer.valueOf((int)1)); } })); } })) : ((common.ConsCell)core.Pnil.invoke())));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:freshTyVars", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfreshTyVars.invoke(children[0]);
		}
	};
}