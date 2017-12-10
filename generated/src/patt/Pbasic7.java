
package patt;

public final class Pbasic7 extends common.FunctionNode {

	public static final int i_p = 0;


	public static final Class<?> childTypes[] = { patt.NMyTriple.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_basic7;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_p] = new common.Lazy[patt.NMyTriple.num_inh_attrs];

	}

	public Pbasic7(final Object c_p) {
		this.child_p = c_p;

	}

	private Object child_p;
	public final patt.NMyTriple getChild_p() {
		return (patt.NMyTriple) (child_p = common.Util.demand(child_p));
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
		return "patt:basic7";
	}

	public static Integer invoke(final Object c_p) {
		try {
		final common.DecoratedNode context = new Pbasic7(c_p).decorate();
		//case p of mytriple(aa, bb, just(cc)) -> aa + cc | mytriple(bb, just(aa), cc) -> aa + bb end
		return (Integer)(new common.PatternLazy<common.DecoratedNode, Integer>() { public final Integer eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.Pmytriple) { final common.Thunk<Object> __SV_LOCAL___pv705___sv_pv_706_aa = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv707___sv_pv_708_bb = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (core.NMaybe)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv709___sv_tmp_pv_704 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (core.NMaybe)scrutinee.childAsIs(2); } };
 return (Integer)((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_719___fail_720 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, Integer>() { public final Integer eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv715___sv_pv_714_cc = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(0); } };
 return (Integer)((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_716_cc = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv715___sv_pv_714_cc.eval())); } };
return ((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_717_bb = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)(__SV_LOCAL___pv707___sv_pv_708_bb.eval())); } };
return ((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_718_aa = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv705___sv_pv_706_aa.eval())); } };
return Integer.valueOf(((Integer)(__SV_LOCAL_718_aa.eval())) + ((Integer)(__SV_LOCAL_716_cc.eval()))); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Integer)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Basics.sv', 113, 7, 116, 3, 3991, 4084\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)(__SV_LOCAL___pv709___sv_tmp_pv_704.eval())).decorate(context, (common.Lazy[])null)); } };
return new common.PatternLazy<common.DecoratedNode, Integer>() { public final Integer eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv727___sv_pv_728_aa = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(0); } };
 return (Integer)((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_729_cc = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)(__SV_LOCAL___pv709___sv_tmp_pv_704.eval())); } };
return ((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_730_aa = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv727___sv_pv_728_aa.eval())); } };
return ((Integer)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_731_bb = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)(__SV_LOCAL___pv705___sv_pv_706_aa.eval())); } };
return Integer.valueOf(((Integer)(__SV_LOCAL_730_aa.eval())) + ((Integer)(__SV_LOCAL_731_bb.eval()))); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Integer)(__SV_LOCAL_719___fail_720.eval()));}}.eval(context, (common.DecoratedNode)((core.NMaybe)(__SV_LOCAL___pv707___sv_pv_708_bb.eval())).decorate(context, (common.Lazy[])null)); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Integer)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Basics.sv', 113, 7, 116, 3, 3991, 4084\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.Pbasic7.i_p)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:basic7", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pbasic7.invoke(children[0]);
		}
	};
}