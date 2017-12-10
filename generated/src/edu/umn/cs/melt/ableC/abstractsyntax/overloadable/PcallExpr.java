
package edu.umn.cs.melt.ableC.abstractsyntax.overloadable;

// top::host:Expr ::= f::host:Expr a::host:Exprs 
public final class PcallExpr extends edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr {

	public static final int i_f = 0;
	public static final int i_a = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_f] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];
	childInheritedAttributes[i_a] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs.num_inh_attrs];

	}

	public PcallExpr(final Object c_f, final Object c_a, final Object a_core_location) {
		super(a_core_location);
		this.child_f = c_f;
		this.child_a = c_a;

	}

	private Object child_f;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr getChild_f() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr) (child_f = common.Util.demand(child_f));
	}

	private Object child_a;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs getChild_a() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs) (child_a = common.Util.demand(child_a));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_a: return getChild_a();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_a: return child_a;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return (((Boolean)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr).synthesized(core.Init.core_fromJust__ON__core_Maybe)) : (((Boolean)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option2__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr).synthesized(core.Init.core_isJust__ON__core_Maybe)) ? ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option2__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr).synthesized(core.Init.core_fromJust__ON__core_Maybe)) : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PcallExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.i_f)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.i_a)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } }))));
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:callExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = parens(ppConcat([ f.pp, parens(ppImplode(cat(comma(,), space(,),), a.host:pps,),) ],),)
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pparens.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.i_f, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pparens.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppImplode.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pcomma.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.i_a, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_pps__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Exprs))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };
		// a.env = addEnv(f.defs, f.env,)
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.i_a][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Exprs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PaddEnv.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.i_f, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.i_f).inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } })); } };
		// option1 = case f of memberExpr(l, d, r) -> applyMaybe5(getMemberCallOverload(l.host:typerep, top.env,), l, d, r, a, top.location,) | _ -> nothing(,) end
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3920___fail_3921 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr) { final common.Thunk<Object> __SV_LOCAL___pv3926___sv_pv_3927_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv3928___sv_pv_3929_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv3930___sv_pv_3925_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3931_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv3930___sv_pv_3925_r.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3932_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)(__SV_LOCAL___pv3928___sv_pv_3929_d.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3933_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv3926___sv_pv_3927_l.eval())); } };
return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe5.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMemberCallOverload.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)(__SV_LOCAL_3933_l.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))); } }, common.Thunk.transformUndecorate(__SV_LOCAL_3933_l), __SV_LOCAL_3932_d, common.Thunk.transformUndecorate(__SV_LOCAL_3931_r), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.i_a)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_3920___fail_3921.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.i_f)); } }).eval()); } };
		// option2 = applyMaybe3(getCallOverload(f.host:typerep, top.env,), f, a, top.location,)
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option2__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_callExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe3.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetCallOverload.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.i_f, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.i_f)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PcallExpr.i_a)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PcallExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcallExpr> {

		@Override
		public PcallExpr invoke(final Object[] children, final Object[] annotations) {
			return new PcallExpr(children[0], children[1], annotations[0]);
		}
	};

}
