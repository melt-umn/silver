
package silver.definition.env;

public final class PmapDefOnEnvItem extends common.FunctionNode {

	public static final int i_fn = 0;
	public static final int i_d = 1;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,silver.definition.env.NDef.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_mapDefOnEnvItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.env.NDef.num_inh_attrs];

	}

	public PmapDefOnEnvItem(final Object c_fn, final Object c_d) {
		this.child_fn = c_fn;
		this.child_d = c_d;

	}

	private Object child_fn;
	public final common.NodeFactory<silver.definition.env.NEnvItem> getChild_fn() {
		return (common.NodeFactory<silver.definition.env.NEnvItem>) (child_fn = common.Util.demand(child_fn));
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
		return "silver:definition:env:mapDefOnEnvItem";
	}

	public static silver.definition.env.NDef invoke(final Object c_fn, final Object c_d) {
		try {
		final common.DecoratedNode context = new PmapDefOnEnvItem(c_fn, c_d).decorate();
		//case d of valueDef(ei) -> valueDef(fn(ei)) | typeDef(ei) -> typeDef(fn(ei)) | attrDef(ei) -> attrDef(fn(ei)) | prodDclDef(ei) -> prodDclDef(fn(ei)) | _ -> d end
		return (silver.definition.env.NDef)(((silver.definition.env.NDef)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4026___fail_4027 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.env.PmapDefOnEnvItem.i_d).undecorate(); } };
return new common.PatternLazy<common.DecoratedNode, silver.definition.env.NDef>() { public final silver.definition.env.NDef eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof silver.definition.env.PattrDef) { final common.Thunk<Object> __SV_LOCAL___pv4031___sv_pv_4030_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.env.NDef)((silver.definition.env.NDef)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4032_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4031___sv_pv_4030_ei.eval())); } };
return ((silver.definition.env.NDef)new silver.definition.env.PattrDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NEnvItem)((common.NodeFactory<silver.definition.env.NEnvItem>)context.childAsIs(silver.definition.env.PmapDefOnEnvItem.i_fn)).invoke(new Object[]{common.Thunk.transformUndecorate(__SV_LOCAL_4032_ei)}, null)); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.env.PprodDclDef) { final common.Thunk<Object> __SV_LOCAL___pv4034___sv_pv_4033_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.env.NDef)((silver.definition.env.NDef)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4035_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4034___sv_pv_4033_ei.eval())); } };
return ((silver.definition.env.NDef)new silver.definition.env.PprodDclDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NEnvItem)((common.NodeFactory<silver.definition.env.NEnvItem>)context.childAsIs(silver.definition.env.PmapDefOnEnvItem.i_fn)).invoke(new Object[]{common.Thunk.transformUndecorate(__SV_LOCAL_4035_ei)}, null)); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.env.PtypeDef) { final common.Thunk<Object> __SV_LOCAL___pv4037___sv_pv_4036_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.env.NDef)((silver.definition.env.NDef)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4038_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4037___sv_pv_4036_ei.eval())); } };
return ((silver.definition.env.NDef)new silver.definition.env.PtypeDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NEnvItem)((common.NodeFactory<silver.definition.env.NEnvItem>)context.childAsIs(silver.definition.env.PmapDefOnEnvItem.i_fn)).invoke(new Object[]{common.Thunk.transformUndecorate(__SV_LOCAL_4038_ei)}, null)); } })); } }).eval()); }
else if(scrutineeNode instanceof silver.definition.env.PvalueDef) { final common.Thunk<Object> __SV_LOCAL___pv4040___sv_pv_4039_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
 return (silver.definition.env.NDef)((silver.definition.env.NDef)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4041_ei = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4040___sv_pv_4039_ei.eval())); } };
return ((silver.definition.env.NDef)new silver.definition.env.PvalueDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NEnvItem)((common.NodeFactory<silver.definition.env.NEnvItem>)context.childAsIs(silver.definition.env.PmapDefOnEnvItem.i_fn)).invoke(new Object[]{common.Thunk.transformUndecorate(__SV_LOCAL_4041_ei)}, null)); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((silver.definition.env.NDef)(__SV_LOCAL_4026___fail_4027.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(silver.definition.env.PmapDefOnEnvItem.i_d)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:mapDefOnEnvItem", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NDef> {
		@Override
		public silver.definition.env.NDef invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmapDefOnEnvItem.invoke(children[0], children[1]);
		}
	};
}