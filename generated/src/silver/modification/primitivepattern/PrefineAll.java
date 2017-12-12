
package silver.modification.primitivepattern;

public final class PrefineAll extends common.FunctionNode {

	public static final int i_te1 = 0;
	public static final int i_te2 = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_refineAll;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PrefineAll(final Object c_te1, final Object c_te2) {
		this.child_te1 = c_te1;
		this.child_te2 = c_te2;

	}

	private Object child_te1;
	public final common.ConsCell getChild_te1() {
		return (common.ConsCell) (child_te1 = common.Util.demand(child_te1));
	}

	private Object child_te2;
	public final common.ConsCell getChild_te2() {
		return (common.ConsCell) (child_te2 = common.Util.demand(child_te2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_te1: return getChild_te1();
			case i_te2: return getChild_te2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_te1: return child_te1;
			case i_te2: return child_te2;

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
		return "silver:modification:primitivepattern:refineAll";
	}

	public static silver.definition.type.NSubstitution invoke(final Object c_te1, final Object c_te2) {
		try {
		final common.DecoratedNode context = new PrefineAll(c_te1, c_te2).decorate();
		//if null(te1) && null(te2) then emptySubst() else if null(te1) || null(te2) then errorSubst("Internal error: refineing mismatching numbers") else composeSubst(first, refineAll(mapSubst(tail(te1), first), mapSubst(tail(te2), first)))
		return (silver.definition.type.NSubstitution)(((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.modification.primitivepattern.PrefineAll.i_te1))) && ((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.modification.primitivepattern.PrefineAll.i_te2)))) ? ((silver.definition.type.NSubstitution)silver.definition.type.PemptySubst.invoke()) : ((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.modification.primitivepattern.PrefineAll.i_te1))) || ((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.modification.primitivepattern.PrefineAll.i_te2)))) ? ((silver.definition.type.NSubstitution)silver.definition.type.PerrorSubst.invoke((new common.StringCatter("Internal error: refineing mismatching numbers")))) : ((silver.definition.type.NSubstitution)silver.definition.type.PcomposeSubst.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.primitivepattern.Init.first__ON__silver_modification_primitivepattern_refineAll)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.modification.primitivepattern.PrefineAll.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PmapSubst.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.modification.primitivepattern.PrefineAll.i_te1))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.primitivepattern.Init.first__ON__silver_modification_primitivepattern_refineAll)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PmapSubst.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.modification.primitivepattern.PrefineAll.i_te2))); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.modification.primitivepattern.Init.first__ON__silver_modification_primitivepattern_refineAll)))); } })); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:primitivepattern:refineAll", t);
		}
	}

	public static final common.NodeFactory<silver.definition.type.NSubstitution> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.type.NSubstitution> {
		@Override
		public silver.definition.type.NSubstitution invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrefineAll.invoke(children[0], children[1]);
		}
	};
}