
package silver.langutil.pp;

public final class Penter extends common.FunctionNode {

	public static final int i_p = 0;
	public static final int i_q = 1;


	public static final Class<?> childTypes[] = { Integer.class,silver.util.deque.NDeque.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_langutil_pp_enter;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_q] = new common.Lazy[silver.util.deque.NDeque.num_inh_attrs];

	}

	public Penter(final Object c_p, final Object c_q) {
		this.child_p = c_p;
		this.child_q = c_q;

	}

	private Object child_p;
	public final Integer getChild_p() {
		return (Integer) (child_p = common.Util.demand(child_p));
	}

	private Object child_q;
	public final silver.util.deque.NDeque getChild_q() {
		return (silver.util.deque.NDeque) (child_q = common.Util.demand(child_q));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();
			case i_q: return getChild_q();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;
			case i_q: return child_q;

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
		return "silver:langutil:pp:enter";
	}

	public static silver.util.deque.NDeque invoke(final Object c_p, final Object c_q) {
		try {
		final common.DecoratedNode context = new Penter(c_p, c_q).decorate();
		//dqSnoc(q, pair(p, []))
		return (silver.util.deque.NDeque)(((silver.util.deque.NDeque)silver.util.deque.PdqSnoc.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.langutil.pp.Penter.i_q)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.langutil.pp.Penter.i_p), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:langutil:pp:enter", t);
		}
	}

	public static final common.NodeFactory<silver.util.deque.NDeque> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.util.deque.NDeque> {
		@Override
		public silver.util.deque.NDeque invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Penter.invoke(children[0], children[1]);
		}
	};
}