
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::Type ::= attrs::Attributes bt::Type 
public final class PattributedType extends edu.umn.cs.melt.ableC.abstractsyntax.host.NType {

	public static final int i_attrs = 0;
	public static final int i_bt = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_attributedType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_attrs] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes.num_inh_attrs];
	childInheritedAttributes[i_bt] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PattributedType(final Object c_attrs, final Object c_bt) {
		super();
		this.child_attrs = c_attrs;
		this.child_bt = c_bt;

	}

	private Object child_attrs;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes getChild_attrs() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes) (child_attrs = common.Util.demand(child_attrs));
	}

	private Object child_bt;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_bt() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_bt = common.Util.demand(child_bt));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_attrs: return getChild_attrs();
			case i_bt: return getChild_bt();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_attrs: return child_attrs;
			case i_bt: return child_bt;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:attributedType erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:attributedType";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:attributedType(, attrs.host, bt.host,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_attrs, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attributes), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))); } };
		// top.withoutExtensionQualifiers = edu:umn:cs:melt:ableC:abstractsyntax:host:attributedType(, attrs, bt.withoutExtensionQualifiers,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutExtensionQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_attrs)), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutExtensionQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))); } };
		// top.lpp = ppConcat([ ppAttributes(attrs,), space(,), bt.lpp ],)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)edu.umn.cs.melt.ableC.abstractsyntax.host.PppAttributes.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_attrs)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pspace.invoke()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };
		// top.rpp = bt.rpp
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.mangledName = bt.mangledName
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.baseTypeExpr = attributedTypeExpr(attrs, bt.baseTypeExpr,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedTypeExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_attrs)), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))); } };
		// top.typeModifierExpr = baseTypeExpr(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeModifierExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr()); } };
		// top.integerPromotions = attributedType(attrs, bt.integerPromotions,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerPromotions__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_attrs)), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerPromotions__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))); } };
		// top.defaultArgumentPromotions = attributedType(attrs, bt.defaultArgumentPromotions,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultArgumentPromotions__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_attrs)), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultArgumentPromotions__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))); } };
		// top.defaultLvalueConversion = bt.defaultLvalueConversion
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultLvalueConversion__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultLvalueConversion__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.defaultFunctionArrayLvalueConversion = bt.defaultFunctionArrayLvalueConversion
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultFunctionArrayLvalueConversion__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultFunctionArrayLvalueConversion__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.withoutAttributes = bt.withoutAttributes
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutAttributes__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutAttributes__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.withoutTypeQualifiers = attributedType(attrs, bt.withoutTypeQualifiers,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutTypeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_attrs)), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutTypeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))); } };
		// top.withTypeQualifiers = attributedType(attrs, bt.withTypeQualifiers,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withTypeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_attrs)), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withTypeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))); } };
		// bt.addedTypeQualifiers = top.addedTypeQualifiers
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_addedTypeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_addedTypeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.mergeQualifiers = \ t2::Type  -> case t2 of attributedType(_, bt2) -> attributedType(attrs, bt.mergeQualifiers(bt2,),) | _ -> attributedType(attrs, bt,) end
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mergeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<Object>() {
  public final Object invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_4785_t2 = args[0];

    return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4786___fail_4787 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_attrs)), common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt)))); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType) { final common.Thunk<Object> __SV_LOCAL___pv4789___sv_tmp_pv_4790 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv4791___sv_pv_4788_bt2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4792_bt2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4791___sv_pv_4788_bt2.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_attrs)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType>)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mergeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)).invoke(new Object[]{common.Thunk.transformUndecorate(__SV_LOCAL_4792_bt2)}, null)); } })); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_4786___fail_4787.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)common.Util.demand(__SV_LAMBDA_PARAM_4785_t2)).decorate(context, (common.Lazy[])null)); } }).eval());
  }
}); } };
		// top.qualifiers = bt.qualifiers
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.isIntegerType = bt.isIntegerType
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isIntegerType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isIntegerType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.isScalarType = bt.isScalarType
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isScalarType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isScalarType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.isArithmeticType = bt.isArithmeticType
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isArithmeticType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isArithmeticType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.errors := bt.errors
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } });
		// attrs.env = emptyEnv(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_attrs][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_env_env__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)edu.umn.cs.melt.ableC.abstractsyntax.env.PemptyEnv.invoke()); } };
		// attrs.returnType = nothing(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.childInheritedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedType.i_attrs][edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_returnType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Attributes] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };

	}

	public static final common.NodeFactory<PattributedType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PattributedType> {

		@Override
		public PattributedType invoke(final Object[] children, final Object[] annotations) {
			return new PattributedType(children[0], children[1]);
		}
	};

}
