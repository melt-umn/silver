
package silver.util.deque;

public final class PdqInit extends common.FunctionNode {

	public static final int i_q = 0;


	public static final Class<?> childTypes[] = { silver.util.deque.NDeque.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_deque_dqInit;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_q] = new common.Lazy[silver.util.deque.NDeque.num_inh_attrs];

	}

	public PdqInit(final Object c_q) {
		this.child_q = c_q;

	}

	private Object child_q;
	public final silver.util.deque.NDeque getChild_q() {
		return (silver.util.deque.NDeque) (child_q = common.Util.demand(child_q));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;

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
		return "silver:util:deque:dqInit";
	}

	public static silver.util.deque.NDeque invoke(final Object c_q) {
		try {
		final common.DecoratedNode context = new PdqInit(c_q).decorate();
		//case q of deque(_, _::_, _, []) -> dqEmpty() | deque(ln, l, rn, rh::rt) -> dqCheck(ln, l, rn - 1, rt) end
		return (silver.util.deque.NDeque)(new common.PatternLazy<common.DecoratedNode, silver.util.deque.NDeque>() { public final silver.util.deque.NDeque eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.util.deque.Pdeque) { final common.Thunk<Object> __SV_LOCAL___pv268___sv_tmp_pv_269 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv270___sv_tmp_pv_271 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv272___sv_tmp_pv_273 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv274___sv_tmp_pv_267 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(3); } };
 return (silver.util.deque.NDeque)((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_284___fail_285 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.ConsCell, silver.util.deque.NDeque>() { public final silver.util.deque.NDeque eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_276___sv_pv_277_rh = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_278___sv_pv_275_rt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_279_rt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_278___sv_pv_275_rt.eval())); } };
return ((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_280_rh = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)(__SV_LOCAL_276___sv_pv_277_rh.eval())); } };
return ((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_281_rn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv272___sv_tmp_pv_273.eval())); } };
return ((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_282_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv270___sv_tmp_pv_271.eval())); } };
return ((silver.util.deque.NDeque)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_283_ln = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv268___sv_tmp_pv_269.eval())); } };
return ((silver.util.deque.NDeque)silver.util.deque.PdqCheck.invoke(__SV_LOCAL_283_ln, __SV_LOCAL_282_l, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)(__SV_LOCAL_281_rn.eval())) - Integer.valueOf((int)1)); } }, __SV_LOCAL_279_rt)); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }return ((silver.util.deque.NDeque)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:util:deque 'Deque.sv', 54, 9, 57, 5, 985, 1109\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL___pv274___sv_tmp_pv_267.eval()))); } };
return new common.PatternLazy<common.ConsCell, silver.util.deque.NDeque>() { public final silver.util.deque.NDeque eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_286___sv_tmp_pv_287 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_288___sv_tmp_pv_289 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.ConsCell, silver.util.deque.NDeque>() { public final silver.util.deque.NDeque eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(scrutinee.nil()) { return (silver.util.deque.NDeque)((silver.util.deque.NDeque)silver.util.deque.PdqEmpty.invoke()); }return ((silver.util.deque.NDeque)(__SV_LOCAL_284___fail_285.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL___pv274___sv_tmp_pv_267.eval()))); }return ((silver.util.deque.NDeque)(__SV_LOCAL_284___fail_285.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL___pv270___sv_tmp_pv_271.eval()))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.util.deque.NDeque)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:util:deque 'Deque.sv', 54, 9, 57, 5, 985, 1109\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.util.deque.PdqInit.i_q)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:deque:dqInit", t);
		}
	}

	public static final common.NodeFactory<silver.util.deque.NDeque> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.util.deque.NDeque> {
		@Override
		public silver.util.deque.NDeque invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdqInit.invoke(children[0]);
		}
	};
}