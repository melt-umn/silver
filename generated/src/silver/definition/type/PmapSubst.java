
package silver.definition.type;

public final class PmapSubst extends common.FunctionNode {

	public static final int i_tes = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,silver.definition.type.NSubstitution.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_mapSubst;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_s] = new common.Lazy[silver.definition.type.NSubstitution.num_inh_attrs];

	}

	public PmapSubst(final Object c_tes, final Object c_s) {
		this.child_tes = c_tes;
		this.child_s = c_s;

	}

	private Object child_tes;
	public final common.ConsCell getChild_tes() {
		return (common.ConsCell) (child_tes = common.Util.demand(child_tes));
	}

	private Object child_s;
	public final silver.definition.type.NSubstitution getChild_s() {
		return (silver.definition.type.NSubstitution) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tes: return getChild_tes();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tes: return child_tes;
			case i_s: return child_s;

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
		return "silver:definition:type:mapSubst";
	}

	public static common.ConsCell invoke(final Object c_tes, final Object c_s) {
		try {
		final common.DecoratedNode context = new PmapSubst(c_tes, c_s).decorate();
		//if null(tes) then [] else (performSubstitution(head(tes), s) :: mapSubst(tail(tes), s))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.type.PmapSubst.i_tes))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)core.Phead.invoke(context.childAsIsLazy(silver.definition.type.PmapSubst.i_tes))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PmapSubst.i_s)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PmapSubst.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.type.PmapSubst.i_tes))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PmapSubst.i_s)))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:mapSubst", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmapSubst.invoke(children[0], children[1]);
		}
	};
}