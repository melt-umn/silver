
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::UnaryOp ::= 
public final class PpreIncOp extends edu.umn.cs.melt.ableC.abstractsyntax.host.NUnaryOp {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_preIncOp;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NUnaryOp.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NUnaryOp.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PpreIncOp(final Object a_core_location) {
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:preIncOp erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:preIncOp";
	}

	static void initProductionAttributeDefinitions() {
		// top.opName = "pre++"
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_opName__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("pre++")); } };
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:preIncOp(,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NUnaryOp)new edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NUnaryOp)context.undecorate()).getAnno_core_location()); } })); } };
		// top.lifted = edu:umn:cs:melt:ableC:abstractsyntax:host:preIncOp(,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NUnaryOp)new edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NUnaryOp)context.undecorate()).getAnno_core_location()); } })); } };
		// top.pp = text("++",)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter("++")))); } };
		// top.preExpr = true
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_preExpr__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// top.noLvalueConversion = true
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_noLvalueConversion__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// top.typerep = top.op.typerep.defaultLvalueConversion.integerPromotions
		edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((edu.umn.cs.melt.ableC.abstractsyntax.host.NType)((common.DecoratedNode)context.inherited(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_op__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp)).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_typerep__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Expr)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_defaultLvalueConversion__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)).decorate(context, (common.Lazy[])null).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_integerPromotions__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_Type)); } };
		// top.injectedQualifiers := []
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new edu.umn.cs.melt.ableC.abstractsyntax.host.CAinjectedQualifiers(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_injectedQualifiers__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.errors := []
		if(edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] == null)
			edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp] = new silver.langutil.CAerrors(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp);
		((common.CollectionAttribute)edu.umn.cs.melt.ableC.abstractsyntax.host.PpreIncOp.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_errors__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_UnaryOp]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });

	}

	public static final common.NodeFactory<PpreIncOp> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PpreIncOp> {

		@Override
		public PpreIncOp invoke(final Object[] children, final Object[] annotations) {
			return new PpreIncOp(annotations[0]);
		}
	};

}
