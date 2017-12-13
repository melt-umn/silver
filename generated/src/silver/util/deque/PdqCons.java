
package silver.util.deque;

public final class PdqCons extends common.FunctionNode {

	public static final int i_e = 0;
	public static final int i_q = 1;


	public static final Class<?> childTypes[] = { Object.class,silver.util.deque.NDeque.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_deque_dqCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_q] = new common.Lazy[silver.util.deque.NDeque.num_inh_attrs];

	}

	public PdqCons(final Object c_e, final Object c_q) {
		this.child_e = c_e;
		this.child_q = c_q;

	}

	private Object child_e;
	public final Object getChild_e() {
		return (Object) (child_e = common.Util.demand(child_e));
	}

	private Object child_q;
	public final silver.util.deque.NDeque getChild_q() {
		return (silver.util.deque.NDeque) (child_q = common.Util.demand(child_q));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();
			case i_q: return getChild_q();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;
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
		return "silver:util:deque:dqCons";
	}

	public static silver.util.deque.NDeque invoke(final Object c_e, final Object c_q) {
		try {
		final common.DecoratedNode context = new PdqCons(c_e, c_q).decorate();
		//case q of deque(ln, l, rn, r) -> dqCheck(ln + 1, (e :: l), rn, r) end
		return (silver.util.deque.NDeque)(new common.PatternLazy<common.DecoratedNode, silver.util.deque.NDeque>() { public final silver.util.deque.NDeque eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.util.deque.Pdeque) { final common.Thunk<Object> __SV_LOCAL___pv119___sv_pv_120_ln = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv121___sv_pv_122_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv123___sv_pv_124_rn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv125___sv_pv_118_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(3); } };
 return (silver.util.deque.NDeque)((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_126_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv125___sv_pv_118_r.eval())); } };
return ((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_127_rn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv123___sv_pv_124_rn.eval())); } };
return ((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_128_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv121___sv_pv_122_l.eval())); } };
return ((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_129_ln = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv119___sv_pv_120_ln.eval())); } };
return ((silver.util.deque.NDeque)silver.util.deque.PdqCheck.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)(__SV_LOCAL_129_ln.eval())) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.util.deque.PdqCons.i_e), __SV_LOCAL_128_l)); } }, __SV_LOCAL_127_rn, __SV_LOCAL_126_r)); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.util.deque.NDeque)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:util:deque 'Deque.sv', 20, 9, 20, 66, 298, 355\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.util.deque.PdqCons.i_q)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:deque:dqCons", t);
		}
	}

	public static final common.NodeFactory<silver.util.deque.NDeque> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.util.deque.NDeque> {
		@Override
		public silver.util.deque.NDeque invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdqCons.invoke(children[0], children[1]);
		}
	};
}