
package silver.modification.primitivepattern;

public final class PisOnlyTyVars extends common.FunctionNode {

	public static final int i_ls = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_isOnlyTyVars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PisOnlyTyVars(final Object c_ls) {
		this.child_ls = c_ls;

	}

	private Object child_ls;
	public final common.ConsCell getChild_ls() {
		return (common.ConsCell) (child_ls = common.Util.demand(child_ls));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ls: return getChild_ls();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ls: return child_ls;

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
		return "silver:modification:primitivepattern:isOnlyTyVars";
	}

	public static Boolean invoke(final Object c_ls) {
		try {
		final common.DecoratedNode context = new PisOnlyTyVars(c_ls).decorate();
		//case ls of [] -> true | varType(_)::t -> isOnlyTyVars(t) | skolemType(_)::t -> isOnlyTyVars(t) | _ -> false end
		return (Boolean)(((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7613___fail_7614 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return false; } };
return new common.PatternLazy<common.ConsCell, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_7616___sv_tmp_pv_7615 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_7617___sv_pv_7618_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.type.PskolemType) { final common.Thunk<Object> __SV_LOCAL___pv7621___sv_tmp_pv_7622 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7623_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_7617___sv_pv_7618_t.eval())); } };
return ((Boolean)silver.modification.primitivepattern.PisOnlyTyVars.invoke(__SV_LOCAL_7623_t)); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.type.PvarType) { final common.Thunk<Object> __SV_LOCAL___pv7624___sv_tmp_pv_7625 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7626_t = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)(__SV_LOCAL_7617___sv_pv_7618_t.eval())); } };
return ((Boolean)silver.modification.primitivepattern.PisOnlyTyVars.invoke(__SV_LOCAL_7626_t)); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)(__SV_LOCAL_7613___fail_7614.eval()));}}.eval(context, (common.DecoratedNode)((silver.definition.type.NType)(__SV_LOCAL_7616___sv_tmp_pv_7615.eval())).decorate(context, (common.Lazy[])null)); }
else if(scrutinee.nil()) { return (Boolean)true; }return ((Boolean)(__SV_LOCAL_7613___fail_7614.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(silver.modification.primitivepattern.PisOnlyTyVars.i_ls))); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:primitivepattern:isOnlyTyVars", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisOnlyTyVars.invoke(children[0]);
		}
	};
}