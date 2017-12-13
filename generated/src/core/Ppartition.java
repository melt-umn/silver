
package core;

public final class Ppartition extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_lst = 1;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_partition;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Ppartition(final Object c_f, final Object c_lst) {
		this.child_f = c_f;
		this.child_lst = c_lst;

	}

	private Object child_f;
	public final common.NodeFactory<Boolean> getChild_f() {
		return (common.NodeFactory<Boolean>) (child_f = common.Util.demand(child_f));
	}

	private Object child_lst;
	public final common.ConsCell getChild_lst() {
		return (common.ConsCell) (child_lst = common.Util.demand(child_lst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_lst: return getChild_lst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_lst: return child_lst;

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
		return "core:partition";
	}

	public static core.NPair invoke(final Object c_f, final Object c_lst) {
		try {
		final common.DecoratedNode context = new Ppartition(c_f, c_lst).decorate();
		//if null(lst) then pair([], []) else if f(head(lst)) then pair((head(lst) :: recurse.fst), recurse.snd) else pair(recurse.fst, (head(lst) :: recurse.snd))
		return (core.NPair)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.Ppartition.i_lst))) ? ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : (((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(core.Ppartition.i_f)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.Ppartition.i_lst))); } }}, null)) ? ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.Ppartition.i_lst))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(core.Init.recurse__ON__core_partition).synthesized(core.Init.core_fst__ON__core_Pair)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(core.Init.recurse__ON__core_partition).synthesized(core.Init.core_snd__ON__core_Pair)); } })) : ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(core.Init.recurse__ON__core_partition).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(core.Ppartition.i_lst))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(core.Init.recurse__ON__core_partition).synthesized(core.Init.core_snd__ON__core_Pair)); } })); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:partition", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Ppartition.invoke(children[0], children[1]);
		}
	};
}