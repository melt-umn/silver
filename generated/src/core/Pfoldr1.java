
package core;

public final class Pfoldr1 extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_l = 1;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_foldr1;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pfoldr1(final Object c_f, final Object c_l) {
		this.child_f = c_f;
		this.child_l = c_l;

	}

	private Object child_f;
	public final common.NodeFactory<Object> getChild_f() {
		return (common.NodeFactory<Object>) (child_f = common.Util.demand(child_f));
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
		return "core:foldr1";
	}

	public static Object invoke(final Object c_f, final Object c_l) {
		try {
		final common.DecoratedNode context = new Pfoldr1(c_f, c_l).decorate();
		//if null(l) then error("Applying foldr1 to empty list.") else if null(tail(l)) then head(l) else f(head(l), foldr1(f, tail(l)))
		return (Object)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.Pfoldr1.i_l))) ? ((Object)core.Perror.invoke((new common.StringCatter("Applying foldr1 to empty list.")))) : (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.Pfoldr1.i_l))); } })) ? ((Object)core.Phead.invoke(context.childAsIsLazy(core.Pfoldr1.i_l))) : ((Object)((common.NodeFactory<Object>)context.childAsIs(core.Pfoldr1.i_f)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.Pfoldr1.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pfoldr1.invoke(context.childAsIsLazy(core.Pfoldr1.i_f), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.Pfoldr1.i_l))); } })); } }}, null)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:foldr1", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pfoldr1.invoke(children[0], children[1]);
		}
	};
}