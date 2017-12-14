
package silver.definition.type;

public final class PzipVarsIntoSkolemizedSubstitution extends common.FunctionNode {

	public static final int i_original = 0;
	public static final int i_sub = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_zipVarsIntoSkolemizedSubstitution;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PzipVarsIntoSkolemizedSubstitution(final Object c_original, final Object c_sub) {
		this.child_original = c_original;
		this.child_sub = c_sub;

	}

	private Object child_original;
	public final common.ConsCell getChild_original() {
		return (common.ConsCell) (child_original = common.Util.demand(child_original));
	}

	private Object child_sub;
	public final common.ConsCell getChild_sub() {
		return (common.ConsCell) (child_sub = common.Util.demand(child_sub));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_original: return getChild_original();
			case i_sub: return getChild_sub();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_original: return child_original;
			case i_sub: return child_sub;

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
		return "silver:definition:type:zipVarsIntoSkolemizedSubstitution";
	}

	public static silver.definition.type.NSubstitution invoke(final Object c_original, final Object c_sub) {
		try {
		final common.DecoratedNode context = new PzipVarsIntoSkolemizedSubstitution(c_original, c_sub).decorate();
		//if null(original) || null(sub) then emptySubst() else composeSubst(subst(head(original), skolemType(head(sub))), zipVarsIntoSkolemizedSubstitution(tail(original), tail(sub)))
		return (silver.definition.type.NSubstitution)(((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.type.PzipVarsIntoSkolemizedSubstitution.i_original))) || ((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.type.PzipVarsIntoSkolemizedSubstitution.i_sub)))) ? ((silver.definition.type.NSubstitution)silver.definition.type.PemptySubst.invoke()) : ((silver.definition.type.NSubstitution)silver.definition.type.PcomposeSubst.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.Psubst.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NTyVar)core.Phead.invoke(context.childAsIsLazy(silver.definition.type.PzipVarsIntoSkolemizedSubstitution.i_original))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PskolemType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NTyVar)core.Phead.invoke(context.childAsIsLazy(silver.definition.type.PzipVarsIntoSkolemizedSubstitution.i_sub))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PzipVarsIntoSkolemizedSubstitution.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.type.PzipVarsIntoSkolemizedSubstitution.i_original))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.type.PzipVarsIntoSkolemizedSubstitution.i_sub))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:zipVarsIntoSkolemizedSubstitution", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NSubstitution> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NSubstitution> {
		@Override
		public silver.definition.type.NSubstitution invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PzipVarsIntoSkolemizedSubstitution.invoke(children[0], children[1]);
		}
	};
}