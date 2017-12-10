
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::Type ::= bt::Type bytes::Integer 
public final class PvectorType extends edu.umn.cs.melt.ableC.abstractsyntax.host.NType {

	public static final int i_bt = 0;
	public static final int i_bytes = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NType.class,Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_vectorType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_bt] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NType.num_inh_attrs];

	}

	public PvectorType(final Object c_bt, final Object c_bytes) {
		super();
		this.child_bt = c_bt;
		this.child_bytes = c_bytes;

	}

	private Object child_bt;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType getChild_bt() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType) (child_bt = common.Util.demand(child_bt));
	}

	private Object child_bytes;
	public final Integer getChild_bytes() {
		return (Integer) (child_bytes = common.Util.demand(child_bytes));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_bt: return getChild_bt();
			case i_bytes: return getChild_bytes();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_bt: return child_bt;
			case i_bytes: return child_bytes;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:vectorType erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:vectorType";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:vectorType(, bt.host, bytes,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bytes))); } };
		// top.withoutExtensionQualifiers = edu:umn:cs:melt:ableC:abstractsyntax:host:vectorType(, bt.withoutExtensionQualifiers, bytes,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutExtensionQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withoutExtensionQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bytes))); } };
		// top.lpp = ppConcat([ text("__attribute__((__vector_size__(" ++ toString(bytes) ++ "))) ",), bt.lpp ],)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("__attribute__((__vector_size__(")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bytes)))), (common.StringCatter)(new common.StringCatter("))) ")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };
		// top.rpp = bt.rpp
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_rpp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.mangledName = "vector_" ++ bt.mangledName ++ "_" ++ toString(bytes) ++ "_"
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("vector_")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("_")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childAsIs(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bytes)))), (common.StringCatter)(new common.StringCatter("_")))))); } };
		// top.baseTypeExpr = attributedTypeExpr(consAttribute(gccAttribute(consAttrib(appliedAttrib(attribName(name("__vector_size__",location=builtinLoc("host",)),), consExpr(mkIntConst(bytes, builtinLoc("host",),), nilExpr(,),),), nilAttrib(,),),), nilAttribute(,),), bt.baseTypeExpr,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattributedTypeExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttribute(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribute)new edu.umn.cs.melt.ableC.abstractsyntax.host.PgccAttribute(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsAttrib(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttrib)new edu.umn.cs.melt.ableC.abstractsyntax.host.PappliedAttrib(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribName)new edu.umn.cs.melt.ableC.abstractsyntax.host.PattribName(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)new edu.umn.cs.melt.ableC.abstractsyntax.host.Pname((new common.StringCatter("__vector_size__")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)core.PbuiltinLoc.invoke((new common.StringCatter("host")))); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs)new edu.umn.cs.melt.ableC.abstractsyntax.host.PconsExpr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExpr)edu.umn.cs.melt.ableC.abstractsyntax.construction.PmkIntConst.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bytes), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)core.PbuiltinLoc.invoke((new common.StringCatter("host")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NExprs)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilExpr()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttribs)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttrib()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAttributes)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnilAttribute()); } })); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_baseTypeExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type))); } };
		// top.typeModifierExpr = baseTypeExpr(,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeModifierExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NTypeModifierExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PbaseTypeExpr()); } };
		// top.integerPromotions = top
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerPromotions__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// top.defaultArgumentPromotions = top
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultArgumentPromotions__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// top.defaultLvalueConversion = top
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultLvalueConversion__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// top.defaultFunctionArrayLvalueConversion = top
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultFunctionArrayLvalueConversion__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// top.withTypeQualifiers = top
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_withTypeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// top.mergeQualifiers = \ t2::Type  -> case t2 of vectorType(bt2, _) -> vectorType(bt.mergeQualifiers(bt2,), bytes,) | _ -> vectorType(bt, bytes,) end
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mergeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<Object>() {
  public final Object invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_4802_t2 = args[0];

    return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4803___fail_4804 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bt)), context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bytes))); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType) { final common.Thunk<Object> __SV_LOCAL___pv4806___sv_pv_4805_bt2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv4807___sv_tmp_pv_4808 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (Integer)scrutinee.childAsIs(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4809_bt2 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4806___sv_pv_4805_bt2.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.NodeFactory<edu.umn.cs.melt.ableC.abstractsyntax.host.NType>)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mergeQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)).invoke(new Object[]{common.Thunk.transformUndecorate(__SV_LOCAL_4809_bt2)}, null)); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bytes))); } }).eval()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)(__SV_LOCAL_4803___fail_4804.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)common.Util.demand(__SV_LAMBDA_PARAM_4802_t2)).decorate(context, (common.Lazy[])null)); } }).eval());
  }
}); } };
		// top.qualifiers = []
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.isIntegerType = false
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isIntegerType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.isScalarType = false
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isScalarType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.isArithmeticType = false
		edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isArithmeticType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.errors := bt.errors
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PvectorType.i_bt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } });

	}

	public static final common.NodeFactory<PvectorType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PvectorType> {

		@Override
		public PvectorType invoke(final Object[] children, final Object[] annotations) {
			return new PvectorType(children[0], children[1]);
		}
	};

}
