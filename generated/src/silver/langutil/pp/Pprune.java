
package silver.langutil.pp;

public final class Pprune extends common.FunctionNode {

	public static final int i_p = 0;
	public static final int i_q = 1;


	public static final Class<?> childTypes[] = { Integer.class,silver.util.deque.NDeque.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_langutil_pp_prune;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_q] = new common.Lazy[silver.util.deque.NDeque.num_inh_attrs];

	}

	public Pprune(final Object c_p, final Object c_q) {
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
		return "silver:langutil:pp:prune";
	}

	public static core.NPair invoke(final Object c_p, final Object c_q) {
		try {
		final common.DecoratedNode context = new Pprune(c_p, c_q).decorate();
		//if dqIsEmpty(q) then pair(q, []) else let h :: Pair<Integer [Boolean]> = dqHead(q) in if p <= h.fst then pair(q, []) else let recur :: Pair<Deque<Pair<Integer [Boolean]>> [Boolean]> = prune(p, dqTail(q)) in pair(recur.fst, (false :: (h.snd ++ recur.snd))) end end
		return (core.NPair)((((Boolean)silver.util.deque.PdqIsEmpty.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.langutil.pp.Pprune.i_q)))) ? ((core.NPair)new core.Ppair(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.langutil.pp.Pprune.i_q)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5515_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)silver.util.deque.PdqHead.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.langutil.pp.Pprune.i_q)))); } };
return ((((Integer)context.childAsIs(silver.langutil.pp.Pprune.i_p)) <= ((Integer)((core.NPair)(__SV_LOCAL_5515_h.eval())).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair))) ? ((core.NPair)new core.Ppair(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.langutil.pp.Pprune.i_q)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((core.NPair)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_5516_recur = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)silver.langutil.pp.Pprune.invoke(context.childAsIsLazy(silver.langutil.pp.Pprune.i_p), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)silver.util.deque.PdqTail.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.langutil.pp.Pprune.i_q)))); } })); } };
return ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.util.deque.NDeque)((core.NPair)(__SV_LOCAL_5516_recur.eval())).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(false, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)(__SV_LOCAL_5515_h.eval())).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)(__SV_LOCAL_5516_recur.eval())).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } })); } })); } })); } }).eval())); } }).eval())));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:langutil:pp:prune", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pprune.invoke(children[0], children[1]);
		}
	};
}