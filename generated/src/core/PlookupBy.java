
package core;

public final class PlookupBy extends common.FunctionNode {

	public static final int i_eqf = 0;
	public static final int i_elem = 1;
	public static final int i_lst = 2;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,Object.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_lookupBy;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PlookupBy(final Object c_eqf, final Object c_elem, final Object c_lst) {
		this.child_eqf = c_eqf;
		this.child_elem = c_elem;
		this.child_lst = c_lst;

	}

	private Object child_eqf;
	public final common.NodeFactory<Boolean> getChild_eqf() {
		return (common.NodeFactory<Boolean>) (child_eqf = common.Util.demand(child_eqf));
	}

	private Object child_elem;
	public final Object getChild_elem() {
		return (Object) (child_elem = common.Util.demand(child_elem));
	}

	private Object child_lst;
	public final common.ConsCell getChild_lst() {
		return (common.ConsCell) (child_lst = common.Util.demand(child_lst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_eqf: return getChild_eqf();
			case i_elem: return getChild_elem();
			case i_lst: return getChild_lst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_eqf: return child_eqf;
			case i_elem: return child_elem;
			case i_lst: return child_lst;

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
		return "core:lookupBy";
	}

	public static core.NMaybe invoke(final Object c_eqf, final Object c_elem, final Object c_lst) {
		try {
		final common.DecoratedNode context = new PlookupBy(c_eqf, c_elem, c_lst).decorate();
		//if null(lst) then nothing() else if eqf(elem, head(lst).fst) then just(head(lst).snd) else lookupBy(eqf, elem, tail(lst))
		return (core.NMaybe)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(core.PlookupBy.i_lst))) ? ((core.NMaybe)new core.Pnothing()) : (((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(core.PlookupBy.i_eqf)).invoke(new Object[]{context.childAsIsLazy(core.PlookupBy.i_elem), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NPair)core.Phead.invoke(context.childAsIsLazy(core.PlookupBy.i_lst))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)); } }}, null)) ? ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NPair)core.Phead.invoke(context.childAsIsLazy(core.PlookupBy.i_lst))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } })) : ((core.NMaybe)core.PlookupBy.invoke(context.childAsIsLazy(core.PlookupBy.i_eqf), context.childAsIsLazy(core.PlookupBy.i_elem), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(core.PlookupBy.i_lst))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:lookupBy", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PlookupBy.invoke(children[0], children[1], children[2]);
		}
	};
}