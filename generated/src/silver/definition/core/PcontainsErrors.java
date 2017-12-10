
package silver.definition.core;

public final class PcontainsErrors extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_wError = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Boolean.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_containsErrors;
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
		return "silver:definition:core:containsErrors";
	}

	public static Boolean invoke(final Object c_l, final Object c_wError) {
		try {
		final common.DecoratedNode context = new PcontainsErrors(c_l, c_wError).decorate();
		//case l of [] -> false | err(_, _)::_ -> true | wrn(_, _)::t -> if wError then true else containsErrors(t, false) | _::t -> containsErrors(t, wError) end
		return (Boolean)(new common.PatternLazy<common.ConsCell, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_3617___sv_tmp_pv_3618 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_3619___sv_tmp_pv_3616 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3621___fail_3622 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3620_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_3619___sv_tmp_pv_3616.eval())); } };
return ((Boolean)silver.definition.core.PcontainsErrors.invoke(__SV_LOCAL_3620_t, context.childAsIsLazy(silver.definition.core.PcontainsErrors.i_wError))); } }).eval()); } };
return new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.core.Perr) { final common.Thunk<Object> __SV_LOCAL___pv21087___sv_tmp_pv_16071 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv21151___sv_tmp_pv_16072 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (Boolean)true; }
else if(scrutineeNode instanceof silver.definition.core.Pwrn) { final common.Thunk<Object> __SV_LOCAL___pv3626___sv_tmp_pv_3627 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv3628___sv_tmp_pv_3629 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3630_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_3619___sv_tmp_pv_3616.eval())); } };
return (((Boolean)context.childAsIs(silver.definition.core.PcontainsErrors.i_wError)) ? true : ((Boolean)silver.definition.core.PcontainsErrors.invoke(__SV_LOCAL_3630_t, false))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)(__SV_LOCAL_3621___fail_3622.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.core.NMessage)(__SV_LOCAL_3617___sv_tmp_pv_3618.eval())).decorate(context, (common.Lazy[])null)); } }).eval()); }
else if(scrutinee.nil()) { return (Boolean)false; }return ((Boolean)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at silver:definition:core 'Message.sv', 45, 9, 50, 12, 1075, 1278\n"))));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.definition.core.PcontainsErrors.i_l))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:containsErrors", t);
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