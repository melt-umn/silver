
package edu.umn.cs.melt.ableC.abstractsyntax.overloadable;

// top::host:Expr ::= lhs::host:Expr rhs::host:Expr 
public final class PandEqExpr extends edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr {

	public static final int i_lhs = 0;
	public static final int i_rhs = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_lhs] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];
	childInheritedAttributes[i_rhs] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];

	}

	public PandEqExpr(final Object c_lhs, final Object c_rhs, final Object a_core_location) {
		super(a_core_location);
		this.child_lhs = c_lhs;
		this.child_rhs = c_rhs;

	}

	private Object child_lhs;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr getChild_lhs() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr) (child_lhs = common.Util.demand(child_lhs));
	}

	private Object child_rhs;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr getChild_rhs() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr) (child_rhs = common.Util.demand(child_rhs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lhs: return getChild_lhs();
			case i_rhs: return getChild_rhs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lhs: return child_lhs;
			case i_rhs: return child_rhs;

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
		return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)edu.umn.cs.melt.ableC.abstractsyntax.host.PwrapWarnExpr.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)edu.umn.cs.melt.ableC.abstractsyntax.host.PwrapQualifiedExpr.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv2248___sv_pv_2247_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)scrutinee.childAsIs(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2249_e = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)(__SV_LOCAL___pv2248___sv_pv_2247_e.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)(__SV_LOCAL_2249_e.eval())); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv2259___sv_pv_2258_prod = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)scrutinee.childAsIs(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2260_prod = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)(__SV_LOCAL___pv2259___sv_pv_2258_prod.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)(__SV_LOCAL_2260_prod.eval())).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr).synthesized(core.Init.core_snd__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } }}, null)); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv2271___sv_pv_2270_prod = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)scrutinee.childAsIs(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2272_prod = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)(__SV_LOCAL___pv2271___sv_pv_2270_prod.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PexpandAssign.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr).synthesized(core.Init.core_snd__ON__core_Pair)); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.i_lhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), __SV_LOCAL_2272_prod, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } })); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.injectable.PandEqExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr).synthesized(core.Init.core_snd__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:overloadable 'ExprBinOps.sv', 349, 8, 357, 11, 18695, 19387\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndOverload.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.i_lhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.i_rhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:overloadable 'ExprBinOps.sv', 349, 8, 357, 11, 18695, 19387\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetAndEqOverload.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.i_lhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.i_rhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:overloadable 'ExprBinOps.sv', 349, 8, 357, 11, 18695, 19387\n"))));}}.eval(context, (common.DecoratedNode)context.localDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:andEqExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = parens(ppConcat([ lhs.pp, space(,), text("&=",), space(,), rhs.pp ],),)
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pparens.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.i_lhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("&=")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.i_rhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } })); } };
		// lerrors := case top.env, top.host:returnType of emptyEnv_i(), nothing() -> [] | _, _ -> [] end
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2154___fail_2153 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.env.PemptyEnv_i) {  return (common.ConsCell)new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pnothing) {  return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_2154___fail_2153.eval()));}}.eval(context, (common.DecoratedNode)((core.NMaybe)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_returnType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_2154___fail_2153.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))); } }).eval()); } });
		// runtimeMods := case top.env, top.host:returnType of emptyEnv_i(), nothing() -> [] | _, _ -> [] end
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2168___fail_2167 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.env.PemptyEnv_i) {  return (common.ConsCell)new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pnothing) {  return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_2168___fail_2167.eval()));}}.eval(context, (common.DecoratedNode)((core.NMaybe)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_returnType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_2168___fail_2167.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))); } }).eval()); } });
		// modLhsRhs = inj:applyLhsRhsMods(runtimeMods, lhs, rhs,)
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.modLhsRhs__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)edu.umn.cs.melt.ableC.abstractsyntax.injectable.PapplyLhsRhsMods.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.runtimeMods__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr), context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.i_lhs), context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.i_rhs))); } };
		// injectedQualifiers := case top.env, top.host:returnType of emptyEnv_i(), nothing() -> [] | _, _ -> [] end
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2182___fail_2181 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.env.PemptyEnv_i) {  return (common.ConsCell)new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pnothing) {  return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_2182___fail_2181.eval()));}}.eval(context, (common.DecoratedNode)((core.NMaybe)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_returnType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_2182___fail_2181.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))); } }).eval()); } });
		// option1 = case lhs of arraySubscriptExpr(l, r) -> applyMaybe5(getSubscriptAssignOverload(l.host:typerep, top.env,), l, r, host:andEqExpr(_, _,location=_), rhs, top.location,) | memberExpr(l, d, r) -> applyMaybe6(getMemberAssignOverload(l.host:typerep, top.env,), l, d, r, host:andEqExpr(_, _,location=_), rhs, top.location,) | _ -> nothing(,) end
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.option1__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_andEqExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2193___fail_2194 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
return new common.PatternLazy<common.DecoratedNode, core.NMaybe>() { public final core.NMaybe eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.overloadable.ParraySubscriptExpr) { final common.Thunk<Object> __SV_LOCAL___pv2199___sv_pv_2200_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2201___sv_pv_2198_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2202_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv2201___sv_pv_2198_r.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2203_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv2199___sv_pv_2200_l.eval())); } };
return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe5.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetSubscriptAssignOverload.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)(__SV_LOCAL_2203_l.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))); } }, common.Thunk.transformUndecorate(__SV_LOCAL_2203_l), common.Thunk.transformUndecorate(__SV_LOCAL_2202_r), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return edu.umn.cs.melt.ableC.abstractsyntax.host.PandEqExpr.factory.invokeNamedPartial(new int[]{0}, null, null); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.i_rhs)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr) { final common.Thunk<Object> __SV_LOCAL___pv2221___sv_pv_2222_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv2223___sv_pv_2224_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Boolean)scrutinee.childAsIs(1); } };
final common.Thunk<Object> __SV_LOCAL___pv2225___sv_pv_2220_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
 return (core.NMaybe)((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2226_r = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv2225___sv_pv_2220_r.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2227_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Boolean)(__SV_LOCAL___pv2223___sv_pv_2224_d.eval())); } };
return ((core.NMaybe)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_2228_l = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv2221___sv_pv_2222_l.eval())); } };
return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PapplyMaybe6.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMemberAssignOverload.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)(__SV_LOCAL_2228_l.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } }, context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))); } }, common.Thunk.transformUndecorate(__SV_LOCAL_2228_l), __SV_LOCAL_2227_d, common.Thunk.transformUndecorate(__SV_LOCAL_2226_r), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return edu.umn.cs.melt.ableC.abstractsyntax.host.PandEqExpr.factory.invokeNamedPartial(new int[]{0}, null, null); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.i_rhs)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } })); } }).eval()); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NMaybe)(__SV_LOCAL_2193___fail_2194.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PandEqExpr.i_lhs)); } }).eval()); } };

	}

	public static final common.NodeFactory<PandEqExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PandEqExpr> {

		@Override
		public PandEqExpr invoke(final Object[] children, final Object[] annotations) {
			return new PandEqExpr(children[0], children[1], annotations[0]);
		}
	};

}
