
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::Qualifier ::= 
public final class PrestrictQualifier extends edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_restrictQualifier;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PrestrictQualifier(final Object a_core_location) {
		super(a_core_location);

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:restrictQualifier erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:restrictQualifier";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = text("restrict",)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PrestrictQualifier.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("restrict")))); } };
		// top.mangledName = "restrict"
		edu.umn.cs.melt.ableC.abstractsyntax.host.PrestrictQualifier.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_mangledName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("restrict")); } };
		// top.qualIsPositive = true
		edu.umn.cs.melt.ableC.abstractsyntax.host.PrestrictQualifier.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualIsPositive__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// top.qualIsNegative = false
		edu.umn.cs.melt.ableC.abstractsyntax.host.PrestrictQualifier.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualIsNegative__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.qualAppliesWithinRef = false
		edu.umn.cs.melt.ableC.abstractsyntax.host.PrestrictQualifier.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualAppliesWithinRef__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return false; } };
		// top.qualCompat = \ qualToCompare::Qualifier  -> case qualToCompare of restrictQualifier() -> true | uuRestrictQualifier() -> true | _ -> false end
		edu.umn.cs.melt.ableC.abstractsyntax.host.PrestrictQualifier.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualCompat__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.NodeFactory<Object>() {
  public final Object invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_7867_qualToCompare = args[0];

    return ((Boolean)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7865___fail_7866 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return false; } };
return new common.PatternLazy<common.DecoratedNode, Boolean>() { public final Boolean eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PrestrictQualifier) {  return (Boolean)true; }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PuuRestrictQualifier) {  return (Boolean)true; }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((Boolean)(__SV_LOCAL_7865___fail_7866.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier)common.Util.demand(__SV_LAMBDA_PARAM_7867_qualToCompare)).decorate(context, (common.Lazy[])null)); } }).eval());
  }
}); } };
		// top.qualIsHost = true
		edu.umn.cs.melt.ableC.abstractsyntax.host.PrestrictQualifier.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_qualIsHost__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// top.errors := case top.typeToQualify.defaultFunctionArrayLvalueConversion of pointerType(_, _) -> [] | _ -> [ err(top.location, "invalid use of `restrict'",) ] end
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PrestrictQualifier.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PrestrictQualifier.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PrestrictQualifier.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_7868___fail_7869 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.NMessage)new silver.langutil.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier)context.undecorate()).getAnno_core_location()); } }, (new common.StringCatter("invalid use of `restrict'")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };
return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PpointerType) { final common.Thunk<Object> __SV_LOCAL___pv7875___sv_tmp_pv_7876 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv7877___sv_tmp_pv_7878 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (common.ConsCell)((common.ConsCell)core.Pnil.invoke()); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)(__SV_LOCAL_7868___fail_7869.eval()));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typeToQualify__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Qualifier)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultFunctionArrayLvalueConversion__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)).decorate(context, (common.Lazy[])null)); } }).eval()); } });

	}

	public static final common.NodeFactory<PrestrictQualifier> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PrestrictQualifier> {

		@Override
		public PrestrictQualifier invoke(final Object[] children, final Object[] annotations) {
			return new PrestrictQualifier(annotations[0]);
		}
	};

}
