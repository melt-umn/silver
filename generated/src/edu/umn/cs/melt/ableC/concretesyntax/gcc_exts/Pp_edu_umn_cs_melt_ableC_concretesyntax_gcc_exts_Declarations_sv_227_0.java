
package edu.umn.cs.melt.ableC.concretesyntax.gcc_exts;

// top::StructOrUnionSpecifier_c ::= su::StructOrUnion_c aa::Attributes_c id::Identifier_t '{' ss::StructDeclarationList_c '}' 
public final class Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0 extends edu.umn.cs.melt.ableC.concretesyntax.NStructOrUnionSpecifier_c {

	public static final int i_su = 0;
	public static final int i_aa = 1;
	public static final int i_id = 2;
	public static final int i__G_2 = 3;
	public static final int i_ss = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {edu.umn.cs.melt.ableC.concretesyntax.NStructOrUnion_c.class,edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAttributes_c.class,edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t.class,edu.umn.cs.melt.ableC.concretesyntax.TLCurly_t.class,edu.umn.cs.melt.ableC.concretesyntax.NStructDeclarationList_c.class,edu.umn.cs.melt.ableC.concretesyntax.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NStructOrUnionSpecifier_c.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NStructOrUnionSpecifier_c.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_su] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NStructOrUnion_c.num_inh_attrs];
	childInheritedAttributes[i_aa] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAttributes_c.num_inh_attrs];
	childInheritedAttributes[i_ss] = new common.Lazy[edu.umn.cs.melt.ableC.concretesyntax.NStructDeclarationList_c.num_inh_attrs];

	}

	public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0(final Object c_su, final Object c_aa, final Object c_id, final Object c__G_2, final Object c_ss, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_su = c_su;
		this.child_aa = c_aa;
		this.child_id = c_id;
		this.child__G_2 = c__G_2;
		this.child_ss = c_ss;
		this.child__G_0 = c__G_0;

	}

	private Object child_su;
	public final edu.umn.cs.melt.ableC.concretesyntax.NStructOrUnion_c getChild_su() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NStructOrUnion_c) (child_su = common.Util.demand(child_su));
	}

	private Object child_aa;
	public final edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAttributes_c getChild_aa() {
		return (edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.NAttributes_c) (child_aa = common.Util.demand(child_aa));
	}

	private Object child_id;
	public final edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t getChild_id() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TIdentifier_t) (child_id = common.Util.demand(child_id));
	}

	private Object child__G_2;
	public final edu.umn.cs.melt.ableC.concretesyntax.TLCurly_t getChild__G_2() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_ss;
	public final edu.umn.cs.melt.ableC.concretesyntax.NStructDeclarationList_c getChild_ss() {
		return (edu.umn.cs.melt.ableC.concretesyntax.NStructDeclarationList_c) (child_ss = common.Util.demand(child_ss));
	}

	private Object child__G_0;
	public final edu.umn.cs.melt.ableC.concretesyntax.TRCurly_t getChild__G_0() {
		return (edu.umn.cs.melt.ableC.concretesyntax.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_su: return getChild_su();
			case i_aa: return getChild_aa();
			case i_id: return getChild_id();
			case i__G_2: return getChild__G_2();
			case i_ss: return getChild_ss();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_su: return child_su;
			case i_aa: return child_aa;
			case i_id: return child_id;
			case i__G_2: return child__G_2;
			case i_ss: return child_ss;
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
		throw new common.exceptions.SilverInternalError("Production edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0 erroneously claimed to forward");
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
		return "edu:umn:cs:melt:ableC:concretesyntax:gcc_exts:p_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0";
	}

	static void initProductionAttributeDefinitions() {
		// top.realTypeSpecifiers = case su of struct_c(_) -> [ ast:structTypeExpr(top.givenQualifiers, ast:structDecl(aa.ast, ast:justName(ast:fromId(id,),), ast:foldStructItem(ss.ast,),location=top.location),) ] | union_c(_) -> [ ast:unionTypeExpr(top.givenQualifiers, ast:unionDecl(aa.ast, ast:justName(ast:fromId(id,),), ast:foldStructItem(ss.ast,),location=top.location),) ] end
		edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0.synthesizedAttributes[edu.umn.cs.melt.ableC.concretesyntax.Init.edu_umn_cs_melt_ableC_concretesyntax_realTypeSpecifiers__ON__edu_umn_cs_melt_ableC_concretesyntax_StructOrUnionSpecifier_c] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.PatternLazy<common.DecoratedNode, common.ConsCell>() { public final common.ConsCell eval(final common.DecoratedNode context, common.DecoratedNode scrutineeIter) {while(true) {final common.DecoratedNode scrutinee = scrutineeIter; final common.Node scrutineeNode = scrutinee.undecorate(); if(scrutineeNode instanceof edu.umn.cs.melt.ableC.concretesyntax.Pstruct_c) { final common.Thunk<Object> __SV_LOCAL___pv4177___sv_tmp_pv_4178 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (edu.umn.cs.melt.ableC.concretesyntax.TSTRUCT)scrutinee.childAsIs(0); } };
 return (common.ConsCell)((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstructTypeExpr(context.contextInheritedLazy(edu.umn.cs.melt.ableC.concretesyntax.Init.edu_umn_cs_melt_ableC_concretesyntax_givenQualifiers__ON__edu_umn_cs_melt_ableC_concretesyntax_StructOrUnionSpecifier_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructDecl)new edu.umn.cs.melt.ableC.abstractsyntax.host.PstructDecl(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0.i_aa, edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Attributes_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName)new edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)edu.umn.cs.melt.ableC.abstractsyntax.construction.PfromId.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0.i_id))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList)edu.umn.cs.melt.ableC.abstractsyntax.construction.PfoldStructItem.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0.i_ss, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_StructDeclarationList_c))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.concretesyntax.NStructOrUnionSpecifier_c)context.undecorate()).getAnno_core_location()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }
else if(scrutineeNode instanceof edu.umn.cs.melt.ableC.concretesyntax.Punion_c) { final common.Thunk<Object> __SV_LOCAL___pv4184___sv_tmp_pv_4185 = new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (edu.umn.cs.melt.ableC.concretesyntax.TUNION)scrutinee.childAsIs(0); } };
 return (common.ConsCell)((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NBaseTypeExpr)new edu.umn.cs.melt.ableC.abstractsyntax.host.PunionTypeExpr(context.contextInheritedLazy(edu.umn.cs.melt.ableC.concretesyntax.Init.edu_umn_cs_melt_ableC_concretesyntax_givenQualifiers__ON__edu_umn_cs_melt_ableC_concretesyntax_StructOrUnionSpecifier_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NUnionDecl)new edu.umn.cs.melt.ableC.abstractsyntax.host.PunionDecl(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0.i_aa, edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Attributes_c), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NMaybeName)new edu.umn.cs.melt.ableC.abstractsyntax.host.PjustName(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NName)edu.umn.cs.melt.ableC.abstractsyntax.construction.PfromId.invoke(context.childAsIsLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0.i_id))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((edu.umn.cs.melt.ableC.abstractsyntax.host.NStructItemList)edu.umn.cs.melt.ableC.abstractsyntax.construction.PfoldStructItem.invoke(context.childDecoratedSynthesizedLazy(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0.i_ss, edu.umn.cs.melt.ableC.concretesyntax.Init.silver_langutil_ast__ON__edu_umn_cs_melt_ableC_concretesyntax_StructDeclarationList_c))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((edu.umn.cs.melt.ableC.concretesyntax.NStructOrUnionSpecifier_c)context.undecorate()).getAnno_core_location()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); }if(!scrutineeIter.undecorate().hasForward()) break;scrutineeIter = scrutineeIter.forward();}return ((common.ConsCell)core.Perror.invoke((new common.StringCatter("Error: pattern match failed at edu:umn:cs:melt:ableC:concretesyntax:gcc_exts 'Declarations.sv', 229, 8, 232, 11, 9841, 10204\n"))));}}.eval(context, (common.DecoratedNode)context.childDecorated(edu.umn.cs.melt.ableC.concretesyntax.gcc_exts.Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0.i_su)); } };

	}

	public static final common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0> {

		@Override
		public Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0 invoke(final Object[] children, final Object[] annotations) {
			return new Pp_edu_umn_cs_melt_ableC_concretesyntax_gcc_exts_Declarations_sv_227_0(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
