
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::ParameterDecl ::= storage::[StorageClass] bty::BaseTypeExpr mty::TypeModifierExpr name::MaybeName attrs::Attributes 
public final class PparameterDecl extends edu.umn.cs.melt.ableC.abstractsyntax.host.NParameterDecl {

	public static final int i_storage = 0;
	public static final int i_bty = 1;
	public static final int i_mty = 2;
	public static final int i_name = 3;
	public static final int i_attrs = 4;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_parameterDecl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NParameterDecl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NParameterDecl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_bty] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_mty] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr.num_inh_attrs];
	childInheritedAttributes[i_name] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName.num_inh_attrs];
	childInheritedAttributes[i_attrs] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.num_inh_attrs];

	}

	public PparameterDecl(final Object c_storage, final Object c_bty, final Object c_mty, final Object c_name, final Object c_attrs) {
		super();
		this.child_storage = c_storage;
		this.child_bty = c_bty;
		this.child_mty = c_mty;
		this.child_name = c_name;
		this.child_attrs = c_attrs;

	}

	private Object child_storage;
	public final common.ConsCell getChild_storage() {
		return (common.ConsCell) (child_storage = common.Util.demand(child_storage));
	}

	private Object child_bty;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr getChild_bty() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr) (child_bty = common.Util.demand(child_bty));
	}

	private Object child_mty;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr getChild_mty() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr) (child_mty = common.Util.demand(child_mty));
	}

	private Object child_name;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName getChild_name() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName) (child_name = common.Util.demand(child_name));
	}

	private Object child_attrs;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes getChild_attrs() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes) (child_attrs = common.Util.demand(child_attrs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_storage: return getChild_storage();
			case i_bty: return getChild_bty();
			case i_mty: return getChild_mty();
			case i_name: return getChild_name();
			case i_attrs: return getChild_attrs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_storage: return child_storage;
			case i_bty: return child_bty;
			case i_mty: return child_mty;
			case i_name: return child_name;
			case i_attrs: return child_attrs;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:parameterDecl erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:parameterDecl";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:parameterDecl(, storage, bty.host, mty.host, name.host, attrs.host,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameterDecl)new edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_storage), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_bty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_mty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_name, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_attrs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attributes))); } };
		// top.lifted = edu:umn:cs:melt:ableC:abstractsyntax:host:parameterDecl(, storage, bty.lifted, mty.lifted, name.lifted, attrs.lifted,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NParameterDecl)new edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_storage), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_bty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_mty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_name, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_attrs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attributes))); } };
		// top.pp = ppConcat([ terminate(space(,), map((.pp), storage,),), bty.pp, space(,), mty.lpp, space(,), name.pp, mty.rpp, ppAttributesRHS(attrs,) ],)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pterminate.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_StorageClass, context), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_storage))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_bty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_mty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_name, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_mty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)edu.umn.cs.melt.ableC.abstractsyntax.host.PppAttributesRHS.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_attrs)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } })); } })); } })); } };
		// top.paramname = name.maybename
		edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_paramname__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_name).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_maybename__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName)); } };
		// top.typerep = mty.typerep
		edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_mty).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr)); } };
		// top.sourceLocation = case name.maybename of just(n) -> n.location | nothing() -> loc("??", -1, -1, -1, -1, -1, -1,) end
		edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_sourceLocation__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, core.NLocation>() { public final core.NLocation eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv9440___sv_pv_9439_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NName)scrutinee.childAsIs(0); } };
 return (core.NLocation)((core.NLocation)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9441_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)(__SV_LOCAL___pv9440___sv_pv_9439_n.eval())); } };
return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)(__SV_LOCAL_9441_n.eval())).getAnno_core_location()); } }).eval()); }
else if(scrutineeNode instanceof core.Pnothing) {  return (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((core.NLocation)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:abstractsyntax:host 'Decls.sv', 487, 4, 490, 7, 16783, 16958\n"))));}}.eval(context, (common.DecoratedNode)((core.NMaybe)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_name).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_maybename__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName)).decorate(context, (common.Lazy[])null)); } };
		// top.errors := bty.errors ++ mty.errors
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_bty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_mty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr))); } });
		// top.globalDecls := bty.globalDecls ++ mty.globalDecls
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] = new edu.umn.cs.melt.ableC.abstractsyntax.host.CAglobalDecls(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_bty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_mty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_globalDecls__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr))); } });
		// top.defs := bty.defs ++ case name.maybename of just(n) -> [ valueDef(n.name, parameterValueItem(top,),) ] | _ -> [] end
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] = new edu.umn.cs.melt.ableC.abstractsyntax.env.CAdefs(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_bty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_defs__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9449___fail_9450 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof core.Pjust) { final common.Thunk<Object> __SV_LOCAL___pv9456___sv_pv_9455_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (edu.umn.cs.melt.ableC.abstractsyntax.host.NName)scrutinee.childAsIs(0); } };
 return (common.ConsCell)((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_9457_n = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)(__SV_LOCAL___pv9456___sv_pv_9455_n.eval())); } };
return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NDef)new edu.umn.cs.melt.ableC.abstractsyntax.env.PvalueDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)(__SV_LOCAL_9457_n.eval())).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_name__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Name)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.env.NValueItem)new edu.umn.cs.melt.ableC.abstractsyntax.env.PparameterValueItem(context)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_9449___fail_9450.eval()));}}.eval(context, (common.DecoratedNode)((core.NMaybe)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_name).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_maybename__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName)).decorate(context, (common.Lazy[])null)); } })); } });
		// top.freeVariables = bty.freeVariables ++ mty.freeVariables
		edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_bty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_mty, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr))); } };
		// bty.givenRefId = nothing(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_bty][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_givenRefId__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };
		// mty.baseType = bty.typerep
		edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_mty][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_baseType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_bty).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr)); } };
		// mty.typeModifiersIn = bty.typeModifiers
		edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_mty][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeModifiersIn__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_TypeModifierExpr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_bty).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeModifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BaseTypeExpr)); } };
		// top.errors <- name.valueRedeclarationCheckNoCompatible
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_ParameterDecl]).addPiece(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PparameterDecl.i_name).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_valueRedeclarationCheckNoCompatible__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_MaybeName)); } });

	}

	public static final common.NodeFactory<PparameterDecl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PparameterDecl> {

		@Override
		public PparameterDecl invoke(final Object[] children, final Object[] annotations) {
			return new PparameterDecl(children[0], children[1], children[2], children[3], children[4]);
		}
	};

}
