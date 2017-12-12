
package silver.definition.flow.driver;

public final class PunList extends common.FunctionNode {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_unList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PunList(final Object c_l) {
		this.child_l = c_l;

	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:definition:flow:driver:unList";
	}

	public static common.ConsCell invoke(final Object c_l) {
		try {
		final common.DecoratedNode context = new PunList(c_l).decorate();
		//if null(l) then [] else if ! null(recurse) && head(recurse).fst == head(l).fst then (pair(head(l).fst, (head(l).snd :: head(recurse).snd)) :: tail(recurse)) else (pair(head(l).fst, [ head(l).snd ]) :: recurse)
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.flow.driver.PunList.i_l))) ? ((common.ConsCell)core.Pnil.invoke()) : (((!((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.flow.driver.Init.recurse__ON__silver_definition_flow_driver_unList)))) && ((common.StringCatter)((core.NPair)core.Phead.invoke(context.localAsIsLazy(silver.definition.flow.driver.Init.recurse__ON__silver_definition_flow_driver_unList))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)).equals(((common.StringCatter)((core.NPair)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PunList.i_l))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NPair)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PunList.i_l))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NPair)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PunList.i_l))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)core.Phead.invoke(context.localAsIsLazy(silver.definition.flow.driver.Init.recurse__ON__silver_definition_flow_driver_unList))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.localAsIsLazy(silver.definition.flow.driver.Init.recurse__ON__silver_definition_flow_driver_unList))); } })) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((core.NPair)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PunList.i_l))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NPair)core.Phead.invoke(context.childAsIsLazy(silver.definition.flow.driver.PunList.i_l))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }, context.localAsIsLazy(silver.definition.flow.driver.Init.recurse__ON__silver_definition_flow_driver_unList))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:unList", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunList.invoke(children[0]);
		}
	};
}