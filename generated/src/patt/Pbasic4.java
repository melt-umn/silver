
package patt;

public final class Pbasic4 extends common.FunctionNode {

	public static final int i_p = 0;


	public static final Class<?> childTypes[] = { core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_basic4;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_p] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public Pbasic4(final Object c_p) {
		this.child_p = c_p;

	}

	private Object child_p;
	public final core.NPair getChild_p() {
		return (core.NPair) (child_p = common.Util.demand(child_p));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;

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
		return "patt:basic4";
	}

	public static Integer invoke(final Object c_p) {
		try {
		final common.DecoratedNode context = new Pbasic4(c_p).decorate();
		//case p of pair(1, nothing()) -> 1 | pair(1, just(_)) -> 2 | pair(2, nothing()) -> 3 | pair(_, _) -> 4 end
		return (Integer)(new common.PatternLazy<common.DecoratedNode, Integer>() { public final Integer eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Ppair) { final common.Thunk<Object> __SV_LOCAL___pv660___sv_tmp_pv_657 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv661___sv_tmp_pv_662 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (core.NMaybe)scrutinee.childAsIs(1); } };
 return (Integer)((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_658___fail_659 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf((int)4); } };
return new common.PatternLazy<Integer, Integer>() { public final Integer eval(final common.DecoratedNode context, Integer scrutineeIter) {final Integer scrutinee = scrutineeIter; if(scrutinee == 1) { return (Integer)new common.PatternLazy<common.DecoratedNode, Integer>() { public final Integer eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv2411___sv_tmp_pv_1583 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(0); } };
 return (Integer)Integer.valueOf((int)2); }
else if(scrutineeNode instanceof core.Pnothing) {  return (Integer)Integer.valueOf((int)1); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Integer)(__SV_LOCAL_658___fail_659.eval()));}}.eval(context, (common.DecoratedNode)((core.NMaybe)(__SV_LOCAL___pv661___sv_tmp_pv_662.eval())).decorate(context, (common.Lazy[])null)); }
else if(scrutinee == 2) { return (Integer)new common.PatternLazy<common.DecoratedNode, Integer>() { public final Integer eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pnothing) {  return (Integer)Integer.valueOf((int)3); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Integer)(__SV_LOCAL_658___fail_659.eval()));}}.eval(context, (common.DecoratedNode)((core.NMaybe)(__SV_LOCAL___pv661___sv_tmp_pv_662.eval())).decorate(context, (common.Lazy[])null)); }return ((Integer)(__SV_LOCAL_658___fail_659.eval()));}}.eval(context, (Integer)((Integer)(__SV_LOCAL___pv660___sv_tmp_pv_657.eval()))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Integer)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Basics.sv', 57, 9, 62, 12, 2061, 2213\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.Pbasic4.i_p)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:basic4", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pbasic4.invoke(children[0]);
		}
	};
}