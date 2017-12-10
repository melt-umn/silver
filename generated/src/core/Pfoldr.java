
package core;

public final class Pfoldr extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_i = 1;
	public static final int i_l = 2;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,Object.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_foldr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pfoldr(final Object c_f, final Object c_i, final Object c_l) {
		this.child_f = c_f;
		this.child_i = c_i;
		this.child_l = c_l;

	}

	private Object child_f;
	public final common.NodeFactory<Object> getChild_f() {
		return (common.NodeFactory<Object>) (child_f = common.Util.demand(child_f));
	}

	private Object child_i;
	public final Object getChild_i() {
		return (Object) (child_i = common.Util.demand(child_i));
	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_i: return getChild_i();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_i: return child_i;
			case i_l: return child_l;

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
		return "core:foldr";
	}

	public static Object invoke(final Object c_f, final Object c_i, final Object c_l) {
		try {
		final common.DecoratedNode context = new Pfoldr(c_f, c_i, c_l).decorate();
		//if null(l) then i else f(head(l), foldr(f, i, tail(l)))
		return (Object)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.Pfoldr.i_l))) ? ((Object)context.childAsIs(core.Pfoldr.i_i)) : ((Object)((common.NodeFactory<Object>)context.childAsIs(core.Pfoldr.i_f)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.Pfoldr.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Pfoldr.invoke(context.childAsIsLazy(core.Pfoldr.i_f), context.childAsIsLazy(core.Pfoldr.i_i), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.Pfoldr.i_l))); } })); } }}, null))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:foldr", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pfoldr.invoke(children[0], children[1], children[2]);
		}
	};
}