
package patt;

public final class Pbeta extends common.FunctionNode {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = { patt.NLCE.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_beta;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_e] = new common.Lazy[patt.NLCE.num_inh_attrs];

	}

	public Pbeta(final Object c_e) {
		this.child_e = c_e;

	}

	private Object child_e;
	public final patt.NLCE getChild_e() {
		return (patt.NLCE) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;

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
		return "patt:beta";
	}

	public static patt.NLCE invoke(final Object c_e) {
		try {
		final common.DecoratedNode context = new Pbeta(c_e).decorate();
		//case e of lcUnit() -> e | lcAbs(v, e1) -> e | lcApp(lcAbs(v, e1), e2) -> betaContrived(e1,) | lcApp(e1, e2) -> beta(lcApp(beta(e1,), e2,),) end
		return (patt.NLCE)(new common.PatternLazy<common.DecoratedNode, patt.NLCE>() { public final patt.NLCE eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.PlcAbs) { final common.Thunk<Object> __SV_LOCAL___pv506___sv_pv_507_v = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv508___sv_pv_505_e1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (patt.NLCE)((patt.NLCE)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_509_e1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv508___sv_pv_505_e1.eval())); } };
return ((patt.NLCE)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_510_v = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv506___sv_pv_507_v.eval())); } };
return context.childDecorated(patt.Pbeta.i_e).undecorate(); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof patt.PlcApp) { final common.Thunk<Object> __SV_LOCAL___pv512___sv_tmp_pv_513 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv514___sv_pv_511_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (patt.NLCE)((patt.NLCE)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_517___fail_518 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NLCE)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_515_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv514___sv_pv_511_e2.eval())); } };
return ((patt.NLCE)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_516_e1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv512___sv_tmp_pv_513.eval())); } };
return ((patt.NLCE)patt.Pbeta.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NLCE)new patt.PlcApp(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NLCE)patt.Pbeta.invoke(common.Thunk.transformUndecorate(__SV_LOCAL_516_e1))); } }, common.Thunk.transformUndecorate(__SV_LOCAL_515_e2))); } })); } }).eval()); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, patt.NLCE>() { public final patt.NLCE eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof patt.PlcAbs) { final common.Thunk<Object> __SV_LOCAL___pv532___sv_pv_533_v = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv534___sv_pv_535_e1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (patt.NLCE)((patt.NLCE)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_536_e2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv514___sv_pv_511_e2.eval())); } };
return ((patt.NLCE)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_537_e1 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv534___sv_pv_535_e1.eval())); } };
return ((patt.NLCE)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_538_v = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv532___sv_pv_533_v.eval())); } };
return ((patt.NLCE)patt.PbetaContrived.invoke(__SV_LOCAL_537_e1)); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((patt.NLCE)(__SV_LOCAL_517___fail_518.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv512___sv_tmp_pv_513.eval()))); } }).eval()); }
else if(scrutineeNode instanceof patt.PlcUnit) {  return (patt.NLCE)context.childDecorated(patt.Pbeta.i_e).undecorate(); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((patt.NLCE)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at patt 'Decoration.sv', 28, 9, 33, 12, 342, 587\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(patt.Pbeta.i_e)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:beta", t);
		}
	}

	public static final common.NodeFactory<patt.NLCE> factory = new Factory();

	public static final class Factory extends common.NodeFactory<patt.NLCE> {
		@Override
		public patt.NLCE invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pbeta.invoke(children[0]);
		}
	};
}