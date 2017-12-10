
package silver.util.fixedmap;

public final class PinternalBuildHelp extends common.FunctionNode {

	public static final int i_collected = 0;
	public static final int i_upTo = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_fixedmap_internalBuildHelp;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PinternalBuildHelp(final Object c_collected, final Object c_upTo) {
		this.child_collected = c_collected;
		this.child_upTo = c_upTo;

	}

	private Object child_collected;
	public final common.ConsCell getChild_collected() {
		return (common.ConsCell) (child_collected = common.Util.demand(child_collected));
	}

	private Object child_upTo;
	public final Integer getChild_upTo() {
		return (Integer) (child_upTo = common.Util.demand(child_upTo));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_collected: return getChild_collected();
			case i_upTo: return getChild_upTo();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_collected: return child_collected;
			case i_upTo: return child_upTo;

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
		return "silver:util:fixedmap:internalBuildHelp";
	}

	public static silver.util.fixedmap.NMap invoke(final Object c_collected, final Object c_upTo) {
		try {
		final common.DecoratedNode context = new PinternalBuildHelp(c_collected, c_upTo).decorate();
		//if upTo == 0 then empty(,) else if upTo == 1 then node(head(head(collected,),).fst, map(getSnd, head(collected,),), empty(,), empty(,),) else node(head(head(right_list,),).fst, map(getSnd, head(right_list,),), ltree, rtree,)
		return (silver.util.fixedmap.NMap)((((Integer)context.childAsIs(silver.util.fixedmap.PinternalBuildHelp.i_upTo)).equals(Integer.valueOf((int)0)) ? ((silver.util.fixedmap.NMap)new silver.util.fixedmap.Pempty()) : (((Integer)context.childAsIs(silver.util.fixedmap.PinternalBuildHelp.i_upTo)).equals(Integer.valueOf((int)1)) ? ((silver.util.fixedmap.NMap)new silver.util.fixedmap.Pnode(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NPair)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.childAsIsLazy(silver.util.fixedmap.PinternalBuildHelp.i_collected))); } })).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.util.fixedmap.PgetSnd.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.childAsIsLazy(silver.util.fixedmap.PinternalBuildHelp.i_collected))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.fixedmap.NMap)new silver.util.fixedmap.Pempty()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.fixedmap.NMap)new silver.util.fixedmap.Pempty()); } })) : ((silver.util.fixedmap.NMap)new silver.util.fixedmap.Pnode(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NPair)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.localAsIsLazy(silver.util.fixedmap.Init.right_list__ON__silver_util_fixedmap_internalBuildHelp))); } })).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.util.fixedmap.PgetSnd.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.localAsIsLazy(silver.util.fixedmap.Init.right_list__ON__silver_util_fixedmap_internalBuildHelp))); } })); } }, common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.util.fixedmap.Init.ltree__ON__silver_util_fixedmap_internalBuildHelp)), common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.util.fixedmap.Init.rtree__ON__silver_util_fixedmap_internalBuildHelp)))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:fixedmap:internalBuildHelp", t);
		}
	}

	public static final common.NodeFactory<silver.util.fixedmap.NMap> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.util.fixedmap.NMap> {
		@Override
		public silver.util.fixedmap.NMap invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PinternalBuildHelp.invoke(children[0], children[1]);
		}
	};
}