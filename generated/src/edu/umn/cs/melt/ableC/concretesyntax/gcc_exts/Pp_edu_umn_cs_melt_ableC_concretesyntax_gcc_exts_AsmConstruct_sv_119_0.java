
package edu.umn.cs.melt.ableC.concretesyntax.gcc_exts;

// top::AsmOperands_c ::= asmOps::AsmOperands_c ',' asmOp::AsmOperand_c 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_119_0 extends edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c {

	public static final int i_asmOps = 0;
	public static final int i__G_1 = 1;
	public static final int i_asmOp = 2;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c.class,edu.umn.cs.melt.ableC.concretesyntax.TComma_t.class,edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperand_c.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_119_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_asmOps] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c.num_inh_attrs];
	childInheritedAttributes[i_asmOp] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperand_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_119_0(final Object c_asmOps, final Object c__G_1, final Object c_asmOp, final Object a_core_location) {
		super(a_core_location);
		this.child_asmOps = c_asmOps;
		this.child__G_1 = c__G_1;
		this.child_asmOp = c_asmOp;

	}

	private Object child_asmOps;
	public final edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c getChild_asmOps() {
		return (edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c) (child_asmOps = common.Util.demand(child_asmOps));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.ableC.concretesyntax.TComma_t getChild__G_1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_asmOp;
	public final edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperand_c getChild_asmOp() {
		return (edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperand_c) (child_asmOp = common.Util.demand(child_asmOp));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_asmOps: return getChild_asmOps();
			case i__G_1: return getChild__G_1();
			case i_asmOp: return getChild_asmOp();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_asmOps: return child_asmOps;
			case i__G_1: return child__G_1;
			case i_asmOp: return child_asmOp;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_119_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_119_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = ast:snocAsmOps(asmOps.ast, asmOp.ast,location=top.location)
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_119_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmOperands_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmOperands)new edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmOps(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_119_0.i_asmOps, edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmOperands_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_119_0.i_asmOp, edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmOperand_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_119_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_119_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_119_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_119_0(children[0], children[1], children[2], annotations[0]);
		}
	};

}
