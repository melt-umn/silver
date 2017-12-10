
package silver.definition.env;

public final class PfilterDefOnEnvItem extends common.FunctionNode {

	public static final int i_fn = 0;
	public static final int i_d = 1;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,silver.definition.env.NDef.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_filterDefOnEnvItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.env.NDef.num_inh_attrs];

	}

	public PfilterDefOnEnvItem(final Object c_fn, final Object c_d) {
		this.child_fn = c_fn;
		this.child_d = c_d;

	}

	private Object child_fn;
	public final common.NodeFactory<Boolean> getChild_fn() {
		return (common.NodeFactory<Boolean>) (child_fn = common.Util.demand(child_fn));
	}

	private Object child_d;
	public final silver.definition.env.NDef getChild_d() {
		return (silver.definition.env.NDef) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_fn: return getChild_fn();
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_fn: return child_fn;
			case i_d: return child_d;

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
		return "silver:definition:env:filterDefOnEnvItem";
	}

	public static Boolean invoke(final Object c_fn, final Object c_d) {
		try {
		final common.DecoratedNode context = new PfilterDefOnEnvItem(c_fn, c_d).decorate();
		//case d of valueDef(ei) -> fn(ei) | typeDef(ei) -> fn(ei) | attrDef(ei) -> fn(ei) | prodDclDef(ei) -> fn(ei) | _ -> true end
		return (Boolean)(((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4000___fail_4001 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return true; } };
return new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.env.PattrDef) { final common.Thunk<Object> __SV_LOCAL___pv4005___sv_pv_4004_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4006_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4005___sv_pv_4004_ei.eval())); } };
return ((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(silver.definition.env.PfilterDefOnEnvItem.i_fn)).invoke(new Object[]{common.Thunk.transformUndecorate(__SV_LOCAL_4006_ei)}, null)); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.env.PprodDclDef) { final common.Thunk<Object> __SV_LOCAL___pv4008___sv_pv_4007_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4009_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4008___sv_pv_4007_ei.eval())); } };
return ((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(silver.definition.env.PfilterDefOnEnvItem.i_fn)).invoke(new Object[]{common.Thunk.transformUndecorate(__SV_LOCAL_4009_ei)}, null)); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.env.PtypeDef) { final common.Thunk<Object> __SV_LOCAL___pv4011___sv_pv_4010_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4012_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4011___sv_pv_4010_ei.eval())); } };
return ((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(silver.definition.env.PfilterDefOnEnvItem.i_fn)).invoke(new Object[]{common.Thunk.transformUndecorate(__SV_LOCAL_4012_ei)}, null)); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.env.PvalueDef) { final common.Thunk<Object> __SV_LOCAL___pv4014___sv_pv_4013_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (Boolean)((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4015_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4014___sv_pv_4013_ei.eval())); } };
return ((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(silver.definition.env.PfilterDefOnEnvItem.i_fn)).invoke(new Object[]{common.Thunk.transformUndecorate(__SV_LOCAL_4015_ei)}, null)); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)(__SV_LOCAL_4000___fail_4001.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.env.PfilterDefOnEnvItem.i_d)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:filterDefOnEnvItem", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfilterDefOnEnvItem.invoke(children[0], children[1]);
		}
	};
}