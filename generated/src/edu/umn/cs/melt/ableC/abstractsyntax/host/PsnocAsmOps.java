
package edu.umn.cs.melt.ableC.abstractsyntax.host;

// top::AsmOperands ::= asmOps::AsmOperands asmOp::AsmOperand 
public final class PsnocAsmOps extends edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands {

	public static final int i_asmOps = 0;
	public static final int i_asmOp = 1;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands.class,edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperand.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_snocAsmOps;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_asmOps] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands.num_inh_attrs];
	childInheritedAttributes[i_asmOp] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperand.num_inh_attrs];

	}

	public PsnocAsmOps(final Object c_asmOps, final Object c_asmOp, final Object a_core_location) {
		super(a_core_location);
		this.child_asmOps = c_asmOps;
		this.child_asmOp = c_asmOp;

	}

	private Object child_asmOps;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands getChild_asmOps() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands) (child_asmOps = common.Util.demand(child_asmOps));
	}

	private Object child_asmOp;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperand getChild_asmOp() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperand) (child_asmOp = common.Util.demand(child_asmOp));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_asmOps: return getChild_asmOps();
			case i_asmOp: return getChild_asmOp();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_asmOps: return child_asmOps;
			case i_asmOp: return child_asmOp;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:abstractsyntax:host:snocAsmOps erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:abstractsyntax:host:snocAsmOps";
	}

	static void initProductionAttributeDefinitions() {
		// top.host = edu:umn:cs:melt:ableC:abstractsyntax:host:snocAsmOps(, asmOps.host, asmOp.host,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands)new edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.i_asmOps, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.i_asmOp, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_host__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperand), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands)context.undecorate()).getAnno_core_location()); } })); } };
		// top.lifted = edu:umn:cs:melt:ableC:abstractsyntax:host:snocAsmOps(, asmOps.lifted, asmOp.lifted,, location=top.location)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands)new edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.i_asmOps, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.i_asmOp, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_lifted__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperand), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands)context.undecorate()).getAnno_core_location()); } })); } };
		// top.pp = ppConcat([ asmOps.pp, text(", ",), asmOp.pp ],)
		edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.PppConcat.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.i_asmOps, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Ptext((new common.StringCatter(", ")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.i_asmOp, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.silver_langutil_pp__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperand), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } })); } };
		// top.exists = true
		edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_exists__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };
		// top.freeVariables = asmOp.freeVariables ++ asmOps.freeVariables
		edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.synthesizedAttributes[edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.i_asmOp, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperand), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps.i_asmOps, edu.umn.cs.melt.ableC.abstractsyntax.host.Init.edu_umn_cs_melt_ableC_abstractsyntax_host_freeVariables__ON__edu_umn_cs_melt_ableC_abstractsyntax_host_AsmOperands))); } };

	}

	public static final common.NodeFactory<PsnocAsmOps> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PsnocAsmOps> {

		@Override
		public PsnocAsmOps invoke(final Object[] children, final Object[] annotations) {
			return new PsnocAsmOps(children[0], children[1], annotations[0]);
		}
	};

}
