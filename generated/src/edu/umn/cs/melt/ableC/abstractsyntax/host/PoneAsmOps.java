
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::AsmOperands ::= asmOp::AsmOperand 
public final class PoneAsmOps extends edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands {

	public static final int i_asmOp = 0;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperand.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_oneAsmOps;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_asmOp] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperand.num_inh_attrs];

	}

	public PoneAsmOps(final Object c_asmOp, final Object a_core_location) {
		super(a_core_location);
		this.child_asmOp = c_asmOp;

	}

	private Object child_asmOp;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperand getChild_asmOp() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperand) (child_asmOp = common.Util.demand(child_asmOp));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_asmOp: return getChild_asmOp();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_asmOp: return child_asmOp;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:oneAsmOps erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:oneAsmOps";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:oneAsmOps(, asmOp.host,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmOps.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands)new edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmOps(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmOps.i_asmOp, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperand), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands)context.undecorate()).getAnno_core_location()); } })); } };
		// top.lifted = edu:umn:cs:melt:ableC:abstractsyntax:host:oneAsmOps(, asmOp.lifted,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmOps.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands)new edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmOps(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmOps.i_asmOp, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperand), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands)context.undecorate()).getAnno_core_location()); } })); } };
		// top.pp = asmOp.pp
		edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmOps.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmOps.i_asmOp).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperand)); } };
		// top.exists = true
		edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmOps.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_exists__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// top.freeVariables = asmOp.freeVariables
		edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmOps.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(edu.umn.cs.melt.ableC.abstractsyntax.host.PoneAsmOps.i_asmOp).synthesized(edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperand)); } };

	}

	public static final common.NodeFactory<PoneAsmOps> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoneAsmOps> {

		@Override
		public PoneAsmOps invoke(final Object[] children, final Object[] annotations) {
			return new PoneAsmOps(children[0], annotations[0]);
		}
	};

}
