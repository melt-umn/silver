
package silver.langutil;

public final class PcontainsErrors extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_wError = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Boolean.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_langutil_containsErrors;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcontainsErrors(final Object c_l, final Object c_wError) {
		this.child_l = c_l;
		this.child_wError = c_wError;

	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}

	private Object child_wError;
	public final Boolean getChild_wError() {
		return (Boolean) (child_wError = common.Util.demand(child_wError));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_wError: return getChild_wError();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_wError: return child_wError;

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
		return "silver:langutil:containsErrors";
	}

	public static Boolean invoke(final Object c_l, final Object c_wError) {
		try {
		final common.DecoratedNode context = new PcontainsErrors(c_l, c_wError).decorate();
		//case l of [] -> false | err(_, _)::_ -> true | wrn(_, _)::t -> if wError then true else containsErrors(t, false) | nested(_, _, e)::t -> containsErrors(e, wError) || containsErrors(t, wError) | _::t -> containsErrors(t, wError) end
		return (Boolean)(new common.PatternLazy<common.ConsCell, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_2463___sv_tmp_pv_2464 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_2465___sv_tmp_pv_2462 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2467___fail_2468 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2466_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_2465___sv_tmp_pv_2462.eval())); } };
return ((Boolean)silver.langutil.PcontainsErrors.invoke(__SV_LOCAL_2466_t, context.childAsIsLazy(silver.langutil.PcontainsErrors.i_wError))); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.langutil.Perr) { final common.Thunk<Object> __SV_LOCAL___pv20722___sv_tmp_pv_15351 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv20724___sv_tmp_pv_15352 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (Boolean)true; }
else if(scrutineeNode instanceof silver.langutil.Pnested) { final common.Thunk<Object> __SV_LOCAL___pv2472___sv_tmp_pv_2473 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2474___sv_tmp_pv_2475 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv2476___sv_pv_2477_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(2); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2478_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_2465___sv_tmp_pv_2462.eval())); } };
return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2479_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL___pv2476___sv_pv_2477_e.eval())); } };
return (((Boolean)silver.langutil.PcontainsErrors.invoke(__SV_LOCAL_2479_e, context.childAsIsLazy(silver.langutil.PcontainsErrors.i_wError))) || ((Boolean)silver.langutil.PcontainsErrors.invoke(__SV_LOCAL_2478_t, context.childAsIsLazy(silver.langutil.PcontainsErrors.i_wError)))); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof silver.langutil.Pwrn) { final common.Thunk<Object> __SV_LOCAL___pv2480___sv_tmp_pv_2481 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2482___sv_tmp_pv_2483 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2484_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_2465___sv_tmp_pv_2462.eval())); } };
return (((Boolean)context.childAsIs(silver.langutil.PcontainsErrors.i_wError)) ? true : ((Boolean)silver.langutil.PcontainsErrors.invoke(__SV_LOCAL_2484_t, false))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)(__SV_LOCAL_2467___fail_2468.eval()));}}.eval(context, (common.DecoratedNode)((silver.langutil.NMessage)(__SV_LOCAL_2463___sv_tmp_pv_2464.eval())).decorate(context, (common.Lazy[])null)); } }).eval()); }
else if(scrutinee.nil()) { return (Boolean)false; }return ((Boolean)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:langutil 'Message.sv', 100, 9, 106, 12, 2507, 2800\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.langutil.PcontainsErrors.i_l))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:langutil:containsErrors", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcontainsErrors.invoke(children[0], children[1]);
		}
	};
}