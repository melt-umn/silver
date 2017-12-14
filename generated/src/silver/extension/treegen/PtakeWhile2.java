
package silver.extension.treegen;

public final class PtakeWhile2 extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_l = 1;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_takeWhile2;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtakeWhile2(final Object c_f, final Object c_l) {
		this.child_f = c_f;
		this.child_l = c_l;

	}

	private Object child_f;
	public final common.NodeFactory<Boolean> getChild_f() {
		return (common.NodeFactory<Boolean>) (child_f = common.Util.demand(child_f));
	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_l: return child_l;

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
		return "silver:extension:treegen:takeWhile2";
	}

	public static common.ConsCell invoke(final Object c_f, final Object c_l) {
		try {
		final common.DecoratedNode context = new PtakeWhile2(c_f, c_l).decorate();
		//if null(l) then [] else if null(tail(l)) then l else if f(head(l), head(tail(l))) then (head(l) :: takeWhile2(f, tail(l))) else [ head(l) ]
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.treegen.PtakeWhile2.i_l))) ? ((common.ConsCell)core.Pnil.invoke()) : (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.PtakeWhile2.i_l))); } })) ? ((common.ConsCell)context.childAsIs(silver.extension.treegen.PtakeWhile2.i_l)) : (((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(silver.extension.treegen.PtakeWhile2.i_f)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.PtakeWhile2.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.PtakeWhile2.i_l))); } })); } }}, null)) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.PtakeWhile2.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.treegen.PtakeWhile2.invoke(context.childAsIsLazy(silver.extension.treegen.PtakeWhile2.i_f), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.PtakeWhile2.i_l))); } })); } })) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.PtakeWhile2.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:takeWhile2", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtakeWhile2.invoke(children[0], children[1]);
		}
	};
}