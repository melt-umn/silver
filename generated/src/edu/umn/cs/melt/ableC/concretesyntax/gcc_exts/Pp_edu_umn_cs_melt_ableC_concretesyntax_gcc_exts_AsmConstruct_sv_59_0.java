
package edu.umn.cs.melt.ableC.concretesyntax.gcc_exts;

// top::AsmArgument_c ::= s::StringConstant_t ':' asmOps1::AsmOperands_c ':' asmOps2::AsmOperands_c 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0 extends edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmArgument_c {

	public static final int i_s = 0;
	public static final int i__G_3 = 1;
	public static final int i_asmOps1 = 2;
	public static final int i__G_1 = 3;
	public static final int i_asmOps2 = 4;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.TStringConstant_t.class,edu.umn.cs.melt.ableC.concretesyntax.TColon_t.class,edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c.class,edu.umn.cs.melt.ableC.concretesyntax.TColon_t.class,edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmArgument_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmArgument_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_asmOps1] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c.num_inh_attrs];
	childInheritedAttributes[i_asmOps2] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0(final Object c_s, final Object c__G_3, final Object c_asmOps1, final Object c__G_1, final Object c_asmOps2, final Object a_core_location) {
		super(a_core_location);
		this.child_s = c_s;
		this.child__G_3 = c__G_3;
		this.child_asmOps1 = c_asmOps1;
		this.child__G_1 = c__G_1;
		this.child_asmOps2 = c_asmOps2;

	}

	private Object child_s;
	public final edu.umn.cs.melt.ableC.concretesyntax.TStringConstant_t getChild_s() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TStringConstant_t) (child_s = common.Util.demand(child_s));
	}

	private Object child__G_3;
	public final edu.umn.cs.melt.ableC.concretesyntax.TColon_t getChild__G_3() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TColon_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_asmOps1;
	public final edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c getChild_asmOps1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c) (child_asmOps1 = common.Util.demand(child_asmOps1));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.ableC.concretesyntax.TColon_t getChild__G_1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TColon_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_asmOps2;
	public final edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c getChild_asmOps2() {
		return (edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmOperands_c) (child_asmOps2 = common.Util.demand(child_asmOps2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i__G_3: return getChild__G_3();
			case i_asmOps1: return getChild_asmOps1();
			case i__G_1: return getChild__G_1();
			case i_asmOps2: return getChild_asmOps2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i__G_3: return child__G_3;
			case i_asmOps1: return child_asmOps1;
			case i__G_1: return child__G_1;
			case i_asmOps2: return child_asmOps2;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = ast:asmArgument(s.lexeme, asmOps1.ast, asmOps2.ast, ast:noneAsmClobbers(,location=top.location),location=top.location)
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmArgument_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmArgument)new edu.umn.cs.melt.ableC.abstractsyntax.host.PasmArgument(((common.StringCatter)((edu.umn.cs.melt.ableC.concretesyntax.TStringConstant_t)context.childAsIs(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0.i_s)).lexeme), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0.i_asmOps1, edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmOperands_c), context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0.i_asmOps2, edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmOperands_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmClobbers)new edu.umn.cs.melt.ableC.abstractsyntax.host.PnoneAsmClobbers(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmArgument_c)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmArgument_c)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_59_0(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
