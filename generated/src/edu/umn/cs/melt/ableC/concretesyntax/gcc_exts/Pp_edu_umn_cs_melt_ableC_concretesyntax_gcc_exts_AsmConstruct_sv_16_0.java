
package edu.umn.cs.melt.ableC.concretesyntax.gcc_exts;

// top::Asm_Statement_c ::= Asm_Starter_c ds::TypeQualifier_c '(' arg::AsmArgument_c ')' ';' 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_16_0 extends edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsm_Statement_c {

	public static final int i__G_5 = 0;
	public static final int i_ds = 1;
	public static final int i__G_3 = 2;
	public static final int i_arg = 3;
	public static final int i__G_1 = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsm_Starter_c.class,edu.umn.cs.melt.ableC.concretesyntax.NTypeQualifier_c.class,edu.umn.cs.melt.ableC.concretesyntax.TLParen_t.class,edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmArgument_c.class,edu.umn.cs.melt.ableC.concretesyntax.TRParen_t.class,edu.umn.cs.melt.ableC.concretesyntax.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_16_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsm_Statement_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsm_Statement_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i__G_5] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsm_Starter_c.num_inh_attrs];
	childInheritedAttributes[i_ds] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NTypeQualifier_c.num_inh_attrs];
	childInheritedAttributes[i_arg] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmArgument_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_16_0(final Object c__G_5, final Object c_ds, final Object c__G_3, final Object c_arg, final Object c__G_1, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child_ds = c_ds;
		this.child__G_3 = c__G_3;
		this.child_arg = c_arg;
		this.child__G_1 = c__G_1;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsm_Starter_c getChild__G_5() {
		return (edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsm_Starter_c) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child_ds;
	public final edu.umn.cs.melt.ableC.concretesyntax.NTypeQualifier_c getChild_ds() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NTypeQualifier_c) (child_ds = common.Util.demand(child_ds));
	}

	private Object child__G_3;
	public final edu.umn.cs.melt.ableC.concretesyntax.TLParen_t getChild__G_3() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TLParen_t) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child_arg;
	public final edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmArgument_c getChild_arg() {
		return (edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsmArgument_c) (child_arg = common.Util.demand(child_arg));
	}

	private Object child__G_1;
	public final edu.umn.cs.melt.ableC.concretesyntax.TRParen_t getChild__G_1() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TRParen_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child__G_0;
	public final edu.umn.cs.melt.ableC.concretesyntax.TSemi_t getChild__G_0() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i_ds: return getChild_ds();
			case i__G_3: return getChild__G_3();
			case i_arg: return getChild_arg();
			case i__G_1: return getChild__G_1();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i_ds: return child_ds;
			case i__G_3: return child__G_3;
			case i_arg: return child_arg;
			case i__G_1: return child__G_1;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_16_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_16_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.ast = ast:asmStatementTypeQual((case ds.typeQualifiers of ast:nilQualifier() -> error("Some TypeQualifier_c has empty typeQualifiers attribute.",) | ast:consQualifier(h, _) -> h end), arg.ast,location=top.location)
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_16_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Asm_Statement_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NAsmStatement)new edu.umn.cs.melt.ableC.abstractsyntax.host.PasmStatementTypeQual(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier>() { public final edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PconsQualifier) { final common.Thunk<Object> __SV_LOCAL___pv4351___sv_pv_4350_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(0); } };
final common.Thunk<Object> __SV_LOCAL___pv4352___sv_tmp_pv_4353 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (common.DecoratedNode)scrutinee.childDecorated(1); } };
 return (edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier)((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier)(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { final common.Thunk<Object> __SV_LOCAL_4354_h = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)(__SV_LOCAL___pv4351___sv_pv_4350_h.eval())); } };
return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier)((common.DecoratedNode)__SV_LOCAL_4354_h.eval()).undecorate()); } }).eval()); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.abstractsyntax.host.PnilQualifier) {  return (edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier)((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier)core.Perror.invoke((new common.StringCatter("Some TypeQualifier_c has empty typeQualifiers attribute.")))); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifier)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:concretesyntax:gcc_exts 'AsmConstruct.sv', 18, 19, 22, 22, 682, 912\n"))));}}.eval(context, (common.DecoratedNode)((edu.umn.cs.melt.ableC.abstractsyntax.host.NQualifiers)context.childDecorated(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_16_0.i_ds).synthesized(edu.umn.cs.melt.ableC.concretesyntax.Init.edu_umn_cs_melt_ableC_concretesyntax_typeQualifiers__ON__edu_umn_cs_melt_ableC_concretesyntax_TypeQualifier_c)).decorate(context, (common.Lazy[])null)); } }, context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_16_0.i_arg, edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmArgument_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAsm_Statement_c)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_16_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_16_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_16_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_AsmConstruct_sv_16_0(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
