
package edu.umn.cs.melt.ableC.abstractsyntax.overloadable;

// top::host:Expr ::= lhs::host:Expr deref::Boolean rhs::host:Name 
public final class PmemberExpr extends edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr {

	public static final int i_lhs = 0;
	public static final int i_deref = 1;
	public static final int i_rhs = 2;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.class,Boolean.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NName.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_lhs] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr.num_inh_attrs];
	childInheritedAttributes[i_rhs] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NName.num_inh_attrs];

	}

	public PmemberExpr(final Object c_lhs, final Object c_deref, final Object c_rhs, final Object a_core_location) {
		super(a_core_location);
		this.child_lhs = c_lhs;
		this.child_deref = c_deref;
		this.child_rhs = c_rhs;

	}

	private Object child_lhs;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr getChild_lhs() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr) (child_lhs = common.Util.demand(child_lhs));
	}

	private Object child_deref;
	public final Boolean getChild_deref() {
		return (Boolean) (child_deref = common.Util.demand(child_deref));
	}

	private Object child_rhs;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NName getChild_rhs() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NName) (child_rhs = common.Util.demand(child_rhs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lhs: return getChild_lhs();
			case i_deref: return getChild_deref();
			case i_rhs: return getChild_rhs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lhs: return child_lhs;
			case i_deref: return child_deref;
			case i_rhs: return child_rhs;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)edu.umn.cs.melt.ableC.abstractsyntax.host.PwrapWarnExpr.invoke(context.localAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv4002___sv_pv_4001_prod = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)scrutinee.childAsIs(0); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4003_prod = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)(__SV_LOCAL___pv4002___sv_pv_4001_prod.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr>)(__SV_LOCAL_4003_prod.eval())).invoke(new Object[]{common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_lhs)), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_deref), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_rhs)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } }}, null)); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)new edu.umn.cs.melt.ableC.abstractsyntax.injectable.PmemberExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_lhs)), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_deref), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_rhs)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:overloadable 'Expr.sv', 111, 6, 114, 9, 4119, 4302\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PgetMemberOverload.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.ty__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr)), context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))).decorate(context, (common.Lazy[])null)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)context.undecorate()).getAnno_core_location()); } }));
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:overloadable:memberExpr";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = parens(ppConcat([ lhs.pp, text(if deref then "->" else ".",), rhs.pp ],),)
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pparens.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_lhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_deref)) ? (new common.StringCatter("->")) : (new common.StringCatter("."))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_rhs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } };
		// lerrors := case top.env, top.host:returnType of emptyEnv_i(), nothing() -> [] | _, _ -> [] end
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.lerrors__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3974___fail_3973 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.env.PemptyEnv_i) {  return (common.ConsCell)new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pnothing) {  return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_3974___fail_3973.eval()));}}.eval(context, (common.DecoratedNode)((core.NMaybe)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_returnType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)).decorate(context, (common.Lazy[])null)); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_3974___fail_3973.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))); } }).eval()); } });
		// ty = if deref then case lhs.host:typerep.host:withoutAttributes of host:pointerType(_, sub) -> sub | _ -> lhs.host:typerep end else lhs.host:typerep
		edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.localAttributes[edu.umn.cs.melt.ableC.abstractsyntax.overloadable.Init.ty__ON__edu_umn_cs_melt_ableC_abstractsyntax_overloadable_memberExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_deref)) ? ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3985___fail_3986 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_lhs).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType) { final common.Thunk<Object> __SV_LOCAL___pv3991___sv_tmp_pv_3992 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv3993___sv_pv_3990_sub = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_3994_sub = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv3993___sv_pv_3990_sub.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)__SV_LOCAL_3994_sub.eval()).undecorate()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_3985___fail_3986.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_lhs).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutAttributes__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)).decorate(context, (common.Lazy[])null)); } }).eval()) : ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.overloadable.PmemberExpr.i_lhs).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr))); } };

	}

	public static final common.NodeFactory<PmemberExpr> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmemberExpr> {

		@Override
		public PmemberExpr invoke(final Object[] children, final Object[] annotations) {
			return new PmemberExpr(children[0], children[1], children[2], annotations[0]);
		}
	};

}
