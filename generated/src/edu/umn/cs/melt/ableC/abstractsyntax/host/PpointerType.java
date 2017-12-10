
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::Type ::= q::Qualifiers target::Type 
public final class PpointerType extends edu.umn.cs.melt.ableC.abstractsyntax.host.NType {

	public static final int i_q = 0;
	public static final int i_target = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_pointerType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_q] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers.num_inh_attrs];
	childInheritedAttributes[i_target] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PpointerType(final Object c_q, final Object c_target) {
		super();
		this.child_q = c_q;
		this.child_target = c_target;

	}

	private Object child_q;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers getChild_q() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers) (child_q = common.Util.demand(child_q));
	}

	private Object child_target;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_target() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_target = common.Util.demand(child_target));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();
			case i_target: return getChild_target();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;
			case i_target: return child_target;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:pointerType erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:pointerType";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:pointerType(, q.host, target.host,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_q, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))); } };
		// top.lpp = ppConcat([ target.lpp, space(,), text("*",), space(,), ppImplode(space(,), q.pps,) ],)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("*")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppImplode.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_q, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_pps__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } })); } })); } };
		// top.rpp = target.rpp
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.baseTypeExpr = target.baseTypeExpr
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.typeModifierExpr = pointerTypeExpr(q, target.typeModifierExpr,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeModifierExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerTypeExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_q)), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeModifierExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))); } };
		// top.mangledName = q.mangledName ++ "_pointer_" ++ target.mangledName ++ "_"
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_q).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("_pointer_")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)), (common.StringCatter)(new common.StringCatter("_"))))); } };
		// top.integerPromotions = top
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerPromotions__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// top.defaultArgumentPromotions = top
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultArgumentPromotions__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// top.defaultLvalueConversion = pointerType(nilQualifier(,), target,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultLvalueConversion__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilQualifier()); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target)))); } };
		// top.defaultFunctionArrayLvalueConversion = top
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultFunctionArrayLvalueConversion__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// top.withoutTypeQualifiers = pointerType(nilQualifier(,), target,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutTypeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilQualifier()); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target)))); } };
		// top.withoutExtensionQualifiers = pointerType(filterExtensionQualifiers(q,), target.withoutExtensionQualifiers,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutExtensionQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)edu.umn.cs.melt.ableC.abstractsyntax.host.PfilterExtensionQualifiers.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_q)))); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutExtensionQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))); } };
		// top.withTypeQualifiers = pointerType(foldQualifier(top.addedTypeQualifiers ++ q.qualifiers,), target,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withTypeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)edu.umn.cs.melt.ableC.abstractsyntax.construction.PfoldQualifier.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.contextInheritedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_addedTypeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_q, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers))); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target)))); } };
		// top.mergeQualifiers = \ t2::Type  -> case t2 of pointerType(q2, target2) -> pointerType(unionQualifiers(top.qualifiers, q2.qualifiers,), target.mergeQualifiers(target2,),) | _ -> pointerType(q, target,) end
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mergeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<Object>() {
  public final Object invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_4507_t2 = args[0];

    return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4508___fail_4509 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_q)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target)))); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType) { final common.Thunk<Object> __SV_LOCAL___pv4511___sv_pv_4512_q2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv4513___sv_pv_4510_target2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4514_target2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4513___sv_pv_4510_target2.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4515_q2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4511___sv_pv_4512_q2.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)edu.umn.cs.melt.ableC.abstractsyntax.host.PunionQualifiers.invoke(context.contextSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)(__SV_LOCAL_4515_q2.eval())).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers)); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType>)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mergeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)).invoke(new Object[]{common.Thunk.transformUndecorate(__SV_LOCAL_4514_target2)}, null)); } })); } }).eval()); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_4508___fail_4509.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)common.Util.demand(__SV_LAMBDA_PARAM_4507_t2)).decorate(context, (common.Lazy[])null)); } }).eval());
  }
}); } };
		// top.qualifiers = q.qualifiers
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_q).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers)); } };
		// top.errors := q.errors ++ target.errors
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_q, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_target, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))); } });
		// top.isScalarType = true
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isScalarType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// q.typeToQualify = top
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType.i_q][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeToQualify__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };

	}

	public static final common.NodeFactory<PpointerType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpointerType> {

		@Override
		public PpointerType invoke(final Object[] children, final Object[] annotations) {
			return new PpointerType(children[0], children[1]);
		}
	};

}
