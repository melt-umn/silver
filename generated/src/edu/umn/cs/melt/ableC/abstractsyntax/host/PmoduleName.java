
package edu.umn.cs.melt.ableC.abstractsyntax.host;

public final class PmoduleName extends common.FunctionNode {

	public static final int i_env = 0;
	public static final int i_a = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_moduleName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_a] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PmoduleName(final Object c_env, final Object c_a) {
		this.child_env = c_env;
		this.child_a = c_a;

	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}

	private Object child_a;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_a() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_a = common.Util.demand(child_a));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_env: return getChild_env();
			case i_a: return getChild_a();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_env: return child_env;
			case i_a: return child_a;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:moduleName";
	}

	public static core.NMaybe invoke(final Object c_env, final Object c_a) {
		try {
		final common.DecoratedNode context = new PmoduleName(c_env, c_a).decorate();
		//case a of tagType(_, refIdTagType(_, _, refId)) -> case lookupRefId(refId, env,) of item::_ -> item.moduleName | _ -> nothing(,) end | attributedType(attrs, bt) -> orElse(attrs.moduleName, moduleName(env, bt,),) | _ -> nothing(,) end
		return (core.NMaybe)(((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9027___fail_9028 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType) { final common.Thunk<Object> __SV_LOCAL___pv9033___sv_pv_9034_attrs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv9035___sv_pv_9032_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9036_bt = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv9035___sv_pv_9032_bt.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9037_attrs = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv9033___sv_pv_9034_attrs.eval())); } };
return ((core.NMaybe)core.PorElse.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)((common.DecoratedNode)(__SV_LOCAL_9037_attrs.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_moduleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attributes)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.host.PmoduleName.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PmoduleName.i_env), common.Thunk.transformUndecorate(__SV_LOCAL_9036_bt))); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PtagType) { final common.Thunk<Object> __SV_LOCAL___pv9044___sv_tmp_pv_9045 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv9046___sv_tmp_pv_9043 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (core.NMaybe)new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PrefIdTagType) { final common.Thunk<Object> __SV_LOCAL___pv9051___sv_tmp_pv_9052 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv9053___sv_tmp_pv_9054 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv9055___sv_pv_9050_refId = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.StringCatter)scrutinee.childAsIs(2); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9056_refId = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)(__SV_LOCAL___pv9055___sv_pv_9050_refId.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9057___fail_9058 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.ConsCell, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_9060___sv_pv_9059_item = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_9061___sv_tmp_pv_9062 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9063_item = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem)(__SV_LOCAL_9060___sv_pv_9059_item.eval())); } };
return ((core.NMaybe)((edu.umn.cs.melt.ableC.abstractsyntax.env.NRefIdItem)(__SV_LOCAL_9063_item.eval())).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.env.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_moduleName__ON__edu_umn_cs_melt_ableC_abstractsyntax_env_RefIdItem)); } }).eval()); }return ((core.NMaybe)(__SV_LOCAL_9057___fail_9058.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)edu.umn.cs.melt.ableC.abstractsyntax.env.PlookupRefId.invoke(__SV_LOCAL_9056_refId, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PmoduleName.i_env)))); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_9027___fail_9028.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv9046___sv_tmp_pv_9043.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_9027___fail_9028.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PmoduleName.i_a)); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:host:moduleName", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmoduleName.invoke(children[0], children[1]);
		}
	};
}