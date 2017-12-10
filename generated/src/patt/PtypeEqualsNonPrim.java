
package patt;

public final class PtypeEqualsNonPrim extends common.FunctionNode {

	public static final int i_ta = 0;
	public static final int i_tb = 1;


	public static final Class<?> childTypes[] = { patt.NType.class,patt.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_typeEqualsNonPrim;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ta] = new common.Lazy[patt.NType.num_inh_attrs];
	childInheritedAttributes[i_tb] = new common.Lazy[patt.NType.num_inh_attrs];

	}

	public PtypeEqualsNonPrim(final Object c_ta, final Object c_tb) {
		this.child_ta = c_ta;
		this.child_tb = c_tb;

	}

	private Object child_ta;
	public final patt.NType getChild_ta() {
		return (patt.NType) (child_ta = common.Util.demand(child_ta));
	}

	private Object child_tb;
	public final patt.NType getChild_tb() {
		return (patt.NType) (child_tb = common.Util.demand(child_tb));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ta: return getChild_ta();
			case i_tb: return getChild_tb();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ta: return child_ta;
			case i_tb: return child_tb;

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
		return "patt:typeEqualsNonPrim";
	}

	public static core.NMaybe invoke(final Object c_ta, final Object c_tb) {
		try {
		final common.DecoratedNode context = new PtypeEqualsNonPrim(c_ta, c_tb).decorate();
		//case ta, tb of unitT(), unitT() -> just(eq(,),) | arrow(aa, ab), arrow(ba, bb) -> case typeEqualsNonPrim(aa, ba,), typeEqualsNonPrim(ab, bb,) of just(eq()), just(eq()) -> just(eq(,),) | _, _ -> nothing(,) end | _, _ -> nothing(,) end
		return (core.NMaybe)(((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_91___fail_86 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.Parrow) { final common.Thunk<Object> __SV_LOCAL___pv96___sv_pv_94_aa = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv97___sv_pv_95_ab = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (core.NMaybe)new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.Parrow) { final common.Thunk<Object> __SV_LOCAL___pv111___sv_pv_109_ba = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv112___sv_pv_110_bb = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_117_bb = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv112___sv_pv_110_bb.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_120_ba = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv111___sv_pv_109_ba.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_123_ab = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv97___sv_pv_95_ab.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_126_aa = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv96___sv_pv_94_aa.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_130___fail_127 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv137___sv_tmp_pv_136 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (patt.NEq)scrutinee.childAsIs(0); } };
 return (core.NMaybe)new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.Peq) {  return (core.NMaybe)new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv146___sv_tmp_pv_145 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (patt.NEq)scrutinee.childAsIs(0); } };
 return (core.NMaybe)new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.Peq) {  return (core.NMaybe)((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NEq)new patt.Peq()); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_130___fail_127.eval()));}}.eval(context, (common.DecoratedNode)((patt.NEq)(__SV_LOCAL___pv146___sv_tmp_pv_145.eval())).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_130___fail_127.eval()));}}.eval(context, (common.DecoratedNode)((core.NMaybe)patt.PtypeEqualsNonPrim.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_123_ab), common.Thunk.transformUndecorate(__SV_LOCAL_117_bb))).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_130___fail_127.eval()));}}.eval(context, (common.DecoratedNode)((patt.NEq)(__SV_LOCAL___pv137___sv_tmp_pv_136.eval())).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_130___fail_127.eval()));}}.eval(context, (common.DecoratedNode)((core.NMaybe)patt.PtypeEqualsNonPrim.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_126_aa), common.Thunk.transformUndecorate(__SV_LOCAL_120_ba))).decorate(context, (common.Lazy[])null)); } }).eval()); } }).eval()); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_91___fail_86.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.PtypeEqualsNonPrim.i_tb)); }
else if(scrutineeNode instanceof patt.PunitT) {  return (core.NMaybe)new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.PunitT) {  return (core.NMaybe)((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NEq)new patt.Peq()); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_91___fail_86.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.PtypeEqualsNonPrim.i_tb)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_91___fail_86.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.PtypeEqualsNonPrim.i_ta)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:typeEqualsNonPrim", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtypeEqualsNonPrim.invoke(children[0], children[1]);
		}
	};
}