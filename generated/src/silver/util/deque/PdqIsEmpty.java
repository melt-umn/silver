
package silver.util.deque;

public final class PdqIsEmpty extends common.FunctionNode {

	public static final int i_q = 0;


	public static final Class<?> childTypes[] = { silver.util.deque.NDeque.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_deque_dqIsEmpty;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_q] = new common.Lazy[silver.util.deque.NDeque.num_inh_attrs];

	}

	public PdqIsEmpty(final Object c_q) {
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
		return "silver:util:deque:dqIsEmpty";
	}

	public static Boolean invoke(final Object c_q) {
		try {
		final common.DecoratedNode context = new PdqIsEmpty(c_q).decorate();
		//case q of deque(ln, _, rn, _) -> ln == 0 && rn == 0 end
		return (Boolean)(new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.util.deque.Pdeque) { final common.Thunk<Object> __SV_LOCAL___pv307___sv_pv_308_ln = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv309___sv_tmp_pv_310 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv311___sv_pv_306_rn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(2); } };
final common.Thunk<Object> __SV_LOCAL___pv312___sv_tmp_pv_313 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(3); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_314_rn = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv311___sv_pv_306_rn.eval())); } };
return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_315_ln = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv307___sv_pv_308_ln.eval())); } };
return (((Integer)(__SV_LOCAL_315_ln.eval())).equals(Integer.valueOf((int)0)) && ((Integer)(__SV_LOCAL_314_rn.eval())).equals(Integer.valueOf((int)0))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:util:deque 'Deque.sv', 62, 9, 62, 61, 1167, 1219\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.util.deque.PdqIsEmpty.i_q)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:deque:dqIsEmpty", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdqIsEmpty.invoke(children[0]);
		}
	};
}