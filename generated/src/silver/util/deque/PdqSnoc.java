
package silver.util.deque;

public final class PdqSnoc extends common.FunctionNode {

	public static final int i_q = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { silver.util.deque.NDeque.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_deque_dqSnoc;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_q] = new common.Lazy[silver.util.deque.NDeque.num_inh_attrs];

	}

	public PdqSnoc(final Object c_q, final Object c_e) {
		this.child_q = c_q;
		this.child_e = c_e;

	}

	private Object child_q;
	public final silver.util.deque.NDeque getChild_q() {
		return (silver.util.deque.NDeque) (child_q = common.Util.demand(child_q));
	}

	private Object child_e;
	public final Object getChild_e() {
		return (Object) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;
			case i_e: return child_e;

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
		return "silver:util:deque:dqSnoc";
	}

	public static silver.util.deque.NDeque invoke(final Object c_q, final Object c_e) {
		try {
		final common.DecoratedNode context = new PdqSnoc(c_q, c_e).decorate();
		//case q of deque(ln, l, rn, r) -> dqCheck(ln, l, rn + 1, (e :: r)) end
		return (silver.util.deque.NDeque)(new common.PatternLazy<common.DecoratedNode, silver.util.deque.NDeque>() { public final silver.util.deque.NDeque eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.util.deque.Pdeque) { final common.Thunk<Object> __SV_LOCAL___pv214___sv_pv_215_ln = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv216___sv_pv_217_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv218___sv_pv_219_rn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv220___sv_pv_213_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(3); } };
 return (silver.util.deque.NDeque)((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_221_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv220___sv_pv_213_r.eval())); } };
return ((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_222_rn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv218___sv_pv_219_rn.eval())); } };
return ((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_223_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv216___sv_pv_217_l.eval())); } };
return ((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_224_ln = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv214___sv_pv_215_ln.eval())); } };
return ((silver.util.deque.NDeque)silver.util.deque.PdqCheck.invoke(__SV_LOCAL_224_ln, __SV_LOCAL_223_l, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)(__SV_LOCAL_222_rn.eval())) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.util.deque.PdqSnoc.i_e), __SV_LOCAL_221_r)); } })); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.util.deque.NDeque)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:util:deque 'Deque.sv', 41, 9, 41, 66, 734, 791\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.util.deque.PdqSnoc.i_q)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:deque:dqSnoc", t);
		}
	}

	public static final common.NodeFactory<silver.util.deque.NDeque> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.util.deque.NDeque> {
		@Override
		public silver.util.deque.NDeque invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdqSnoc.invoke(children[0], children[1]);
		}
	};
}