
package patt;

public final class Pbasic2 extends common.FunctionNode {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = { core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_basic2;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_s] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public Pbasic2(final Object c_s) {
		this.child_s = c_s;

	}

	private Object child_s;
	public final core.NPair getChild_s() {
		return (core.NPair) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		return "patt:basic2";
	}

	public static Boolean invoke(final Object c_s) {
		try {
		final common.DecoratedNode context = new Pbasic2(c_s).decorate();
		//case s of pair(just(bv), just(pair(sbv, ssv))) -> bv && sbv | pair(just(bv), nothing()) -> ! bv | pair(nothing(), _) -> false end
		return (Boolean)(new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Ppair) { final common.Thunk<Object> __SV_LOCAL___pv583___sv_tmp_pv_582 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (core.NMaybe)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv584___sv_tmp_pv_585 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (core.NMaybe)scrutinee.childAsIs(1); } };
 return (Boolean)new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv589___sv_pv_590_bv = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(0); } };
 return (Boolean)new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv593___sv_tmp_pv_592 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (core.NPair)scrutinee.childAsIs(0); } };
 return (Boolean)new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Ppair) { final common.Thunk<Object> __SV_LOCAL___pv596___sv_pv_597_sbv = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv598___sv_pv_595_ssv = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_599_ssv = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv598___sv_pv_595_ssv.eval())); } };
return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_600_sbv = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)(__SV_LOCAL___pv596___sv_pv_597_sbv.eval())); } };
return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_601_bv = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)(__SV_LOCAL___pv589___sv_pv_590_bv.eval())); } };
return (((Boolean)(__SV_LOCAL_601_bv.eval())) && ((Boolean)(__SV_LOCAL_600_sbv.eval()))); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Basics.sv', 19, 9, 23, 12, 398, 561\n"))));}}.eval(context, (common.DecoratedNode)((core.NPair)(__SV_LOCAL___pv593___sv_tmp_pv_592.eval())).decorate(context, (common.Lazy[])null)); }
else if(scrutineeNode instanceof core.Pnothing) {  return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_602_bv = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)(__SV_LOCAL___pv589___sv_pv_590_bv.eval())); } };
return (!((Boolean)(__SV_LOCAL_602_bv.eval()))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Basics.sv', 19, 9, 23, 12, 398, 561\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)(__SV_LOCAL___pv584___sv_tmp_pv_585.eval())).decorate(context, (common.Lazy[])null)); }
else if(scrutineeNode instanceof core.Pnothing) {  return (Boolean)false; }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Basics.sv', 19, 9, 23, 12, 398, 561\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)(__SV_LOCAL___pv583___sv_tmp_pv_582.eval())).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Basics.sv', 19, 9, 23, 12, 398, 561\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.Pbasic2.i_s)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:basic2", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pbasic2.invoke(children[0]);
		}
	};
}