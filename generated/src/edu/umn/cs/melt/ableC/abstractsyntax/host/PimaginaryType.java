
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::BuiltinType ::= rt::RealType 
public final class PimaginaryType extends edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType {

	public static final int i_rt = 0;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_imaginaryType;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_rt] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType.num_inh_attrs];

	}

	public PimaginaryType(final Object c_rt) {
		super();
		this.child_rt = c_rt;

	}

	private Object child_rt;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType getChild_rt() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType) (child_rt = common.Util.demand(child_rt));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rt: return getChild_rt();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rt: return child_rt;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:imaginaryType erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:imaginaryType";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:imaginaryType(, rt.host,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType.i_rt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_RealType))); } };
		// top.pp = ppConcat([ text("_Imaginary ",), rt.pp ],)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("_Imaginary ")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType.i_rt, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_RealType), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } };
		// top.mangledName = "imaginary_" ++ rt.mangledName
		edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("imaginary_")), (common.StringCatter)((common.StringCatter)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType.i_rt).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_RealType))); } };
		// top.integerPromotionsBuiltin = top
		edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerPromotionsBuiltin__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.undecorate(); } };
		// top.defaultArgumentPromotionsBuiltin = imaginaryType(case rt of floatType() -> doubleType(,) | _ -> rt end,)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultArgumentPromotionsBuiltin__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBuiltinType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_6311___fail_6312 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType.i_rt).undecorate(); } };
return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PfloatType) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)new edu.umn.cs.melt.ableC.abstractsyntax.host.PdoubleType()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NRealType)(__SV_LOCAL_6311___fail_6312.eval()));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType.i_rt)); } })); } };
		// top.isIntegerType = false
		edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isIntegerType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.isArithmeticType = true
		edu.umn.cs.melt.ableC.abstractsyntax.host.PimaginaryType.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_isArithmeticType__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_BuiltinType] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };

	}

	public static final common.NodeFactory<PimaginaryType> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PimaginaryType> {

		@Override
		public PimaginaryType invoke(final Object[] children, final Object[] annotations) {
			return new PimaginaryType(children[0]);
		}
	};

}
