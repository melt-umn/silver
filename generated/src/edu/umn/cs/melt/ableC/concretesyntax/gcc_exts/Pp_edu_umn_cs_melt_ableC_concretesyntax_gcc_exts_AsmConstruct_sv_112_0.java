
package edu.umn.cs.melt.ableC.concretesyntax.gcc_exts;

// top::AsmClobbers_c ::= asmC::AsmClobbers_c ',' s::StringConstant_t 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_112_0 extends edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmClobbers_c {

	public static final int i_asmC = 0;
	public static final int i__G_1 = 1;
	public static final int i_s = 2;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmClobbers_c.class,edu.umn.cs.melt.ableC.concretesyntax.TComma_t.class,edu.umn.cs.melt.ableC.concretesyntax.TStringConstant_t.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_112_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmClobbers_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmClobbers_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_asmC] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmClobbers_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_112_0(final Object c_asmC, final Object c__G_1, final Object c_s, final Object a_core_location) {
		super(a_core_location);
		this.child_asmC = c_asmC;
		this.child__G_1 = c__G_1;
		this.child_s = c_s;

	}

	private Object child_asmC;
	public final edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmClobbers_c getChild_asmC() {
		return (edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmClobbers_c) (child_asmC = common.Util.demand(child_asmC));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.ableC.concretesyntax.TComma_t getChild__G_1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_s;
	public final edu.umn.cs.melt.ableC.concretesyntax.TStringConstant_t getChild_s() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TStringConstant_t) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_asmC: return getChild_asmC();
			case i__G_1: return getChild__G_1();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_asmC: return child_asmC;
			case i__G_1: return child__G_1;
			case i_s: return child_s;

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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_112_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_112_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = ast:snocAsmClobbers(asmC.ast, s.lexeme,location=top.location)
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_112_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmClobbers_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmClobbers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PsnocAsmClobbers(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_112_0.i_asmC, edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmClobbers_c), ((common.StringCatter)((edu.umn.cs.melt.ableC.concretesyntax.TStringConstant_t)context.childAsIs(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_112_0.i_s)).lexeme), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmClobbers_c)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_112_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_112_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_112_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_112_0(children[0], children[1], children[2], annotations[0]);
		}
	};

}
