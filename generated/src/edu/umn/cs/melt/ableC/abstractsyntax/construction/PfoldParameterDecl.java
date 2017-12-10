
package edu.umn.cs.melt.ableC.abstractsyntax.construction;

public final class PfoldParameterDecl extends common.FunctionNode {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_construction_foldParameterDecl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfoldParameterDecl(final Object c_l) {
		this.child_l = c_l;

	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;

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
		return "edu:umn:cs:melt:ableC:abstractsyntax:construction:foldParameterDecl";
	}

	public static edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters invoke(final Object c_l) {
		try {
		final common.DecoratedNode context = new PfoldParameterDecl(c_l).decorate();
		//case l of [d] -> case decorate d with {env = emptyEnv(,); returnType = nothing(,);} of parameterDecl([], builtinTypeExpr(nilQualifier(), voidType()), baseTypeExpr(), nothingName(), nilAttribute()) -> nilParameters(,) | _ -> foldr(consParameters, nilParameters(,), l,) end | _ -> foldr(consParameters, nilParameters(,), l,) end
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9688___fail_9689 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)core.Pfoldr.invoke(edu.umn.cs.melt.ableC.abstractsyntax.host.PconsParameters.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilParameters()); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PfoldParameterDecl.i_l))); } };
return new common.PatternLazy<common.ConsCell, edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(!scrutineeIter.nil()) {final common.Thunk<Object> __SV_LOCAL_9691___sv_pv_9692_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.head(); } };
final common.Thunk<Object> __SV_LOCAL_9693___sv_tmp_pv_9690 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return scrutinee.tail(); } };
return new common.PatternLazy<common.ConsCell, edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(scrutinee.nil()) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9694_d = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameterDecl)(__SV_LOCAL_9691___sv_pv_9692_d.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9695___fail_9696 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)core.Pfoldr.invoke(edu.umn.cs.melt.ableC.abstractsyntax.host.PconsParameters.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilParameters()); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.construction.PfoldParameterDecl.i_l))); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl) { final common.Thunk<Object> __SV_LOCAL___pv9704___sv_tmp_pv_9703 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.ConsCell)scrutinee.childAsIs(0); } };
final common.Thunk<Object> __SV_LOCAL___pv9705___sv_tmp_pv_9706 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
final common.Thunk<Object> __SV_LOCAL___pv9707___sv_tmp_pv_9708 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(2); } };
final common.Thunk<Object> __SV_LOCAL___pv9709___sv_tmp_pv_9710 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(3); } };
final common.Thunk<Object> __SV_LOCAL___pv9711___sv_tmp_pv_9712 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(4); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)new common.PatternLazy<common.ConsCell, edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters eval(final common.DecoratedNode context, common.ConsCell scrutineeIter) {final common.ConsCell scrutinee = scrutineeIter; if(scrutinee.nil()) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PbuiltinTypeExpr) { final common.Thunk<Object> __SV_LOCAL___pv9720___sv_tmp_pv_9719 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv9721___sv_tmp_pv_9722 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PnilQualifier) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PvoidType) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PnothingName) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttribute) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilParameters()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(__SV_LOCAL_9695___fail_9696.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv9711___sv_tmp_pv_9712.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(__SV_LOCAL_9695___fail_9696.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv9709___sv_tmp_pv_9710.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(__SV_LOCAL_9695___fail_9696.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv9707___sv_tmp_pv_9708.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(__SV_LOCAL_9695___fail_9696.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv9721___sv_tmp_pv_9722.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(__SV_LOCAL_9695___fail_9696.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv9720___sv_tmp_pv_9719.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(__SV_LOCAL_9695___fail_9696.eval()));}}.eval(context, (common.DecoratedNode)((common.DecoratedNode)(__SV_LOCAL___pv9705___sv_tmp_pv_9706.eval()))); }return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(__SV_LOCAL_9695___fail_9696.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL___pv9704___sv_tmp_pv_9703.eval()))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(__SV_LOCAL_9695___fail_9696.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameterDecl)(__SV_LOCAL_9694_d.eval())).decorate(context, common.Util.populateInh(edu.umn.cs.melt.ableC.abstractsyntax.host.NParameterDecl.num_inh_attrs, new int[]{edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_returnType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl}, new common.Lazy[]{new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PemptyEnv.invoke()); } }, new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } }}))); } }).eval()); } }).eval()); }return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(__SV_LOCAL_9688___fail_9689.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)(__SV_LOCAL_9693___sv_tmp_pv_9690.eval()))); }return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters)(__SV_LOCAL_9688___fail_9689.eval()));}}.eval(context, (common.ConsCell)((common.ConsCell)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.construction.PfoldParameterDecl.i_l))); } }).eval()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:abstractsyntax:construction:foldParameterDecl", t);
		}
	}

	public static final common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters> factory = new Factory();

	public static final class Factory extends common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters> {
		@Override
		public edu.umn.cs.melt.ableC.abstractsyntax.host.NParameters invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfoldParameterDecl.invoke(children[0]);
		}
	};
}