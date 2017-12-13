
package silver.definition.core;

// top::ProductionSignature ::= lhs::ProductionLHS '::=' rhs::ProductionRHS 
public final class PproductionSignature extends silver.definition.core.NProductionSignature {

	public static final int i_lhs = 0;
	public static final int i__G_1 = 1;
	public static final int i_rhs = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NProductionLHS.class,silver.definition.core.TCCEQ_t.class,silver.definition.core.NProductionRHS.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_productionSignature;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionSignature.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionSignature.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_lhs] = new common.Lazy[silver.definition.core.NProductionLHS.num_inh_attrs];
	childInheritedAttributes[i_rhs] = new common.Lazy[silver.definition.core.NProductionRHS.num_inh_attrs];

	}

	public PproductionSignature(final Object c_lhs, final Object c__G_1, final Object c_rhs, final Object a_core_location) {
		super(a_core_location);
		this.child_lhs = c_lhs;
		this.child__G_1 = c__G_1;
		this.child_rhs = c_rhs;

	}

	private Object child_lhs;
	public final silver.definition.core.NProductionLHS getChild_lhs() {
		return (silver.definition.core.NProductionLHS) (child_lhs = common.Util.demand(child_lhs));
	}

	private Object child__G_1;
	public final silver.definition.core.TCCEQ_t getChild__G_1() {
		return (silver.definition.core.TCCEQ_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_rhs;
	public final silver.definition.core.NProductionRHS getChild_rhs() {
		return (silver.definition.core.NProductionRHS) (child_rhs = common.Util.demand(child_rhs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lhs: return getChild_lhs();
			case i__G_1: return getChild__G_1();
			case i_rhs: return getChild_rhs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lhs: return child_lhs;
			case i__G_1: return child__G_1;
			case i_rhs: return child_rhs;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:productionSignature erroneously claimed to forward");
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
		return "silver:definition:core:productionSignature";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = lhs.pp ++ " ::= " ++ rhs.pp
		silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PproductionSignature.i_lhs).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionLHS)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ::= ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PproductionSignature.i_rhs).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionRHS)))); } };
		// top.defs = lhs.defs ++ rhs.defs
		silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionSignature.i_lhs, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionLHS), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionSignature.i_rhs, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionRHS))); } };
		// top.errors := lhs.errors ++ rhs.errors
		if(silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionSignature] == null)
			silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionSignature] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionSignature);
		((common.CollectionAttribute)silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionSignature]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionSignature.i_lhs, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionLHS), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionSignature.i_rhs, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionRHS))); } });
		// top.namedSignature = namedSignature(top.signatureName, rhs.inputElements, lhs.outputElement, annotationsForNonterminal(lhs.outputElement.typerep, top.env))
		silver.definition.core.PproductionSignature.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_namedSignature__ON__silver_definition_core_ProductionSignature] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NNamedSignature)new silver.definition.env.PnamedSignature(context.contextInheritedLazy(silver.definition.core.Init.silver_definition_core_signatureName__ON__silver_definition_core_ProductionSignature), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionSignature.i_rhs, silver.definition.core.Init.silver_definition_env_inputElements__ON__silver_definition_core_ProductionRHS), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionSignature.i_lhs, silver.definition.core.Init.silver_definition_env_outputElement__ON__silver_definition_core_ProductionLHS), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PannotationsForNonterminal.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((silver.definition.env.NNamedSignatureElement)context.childDecorated(silver.definition.core.PproductionSignature.i_lhs).synthesized(silver.definition.core.Init.silver_definition_env_outputElement__ON__silver_definition_core_ProductionLHS)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_NamedSignatureElement)); } }, context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_ProductionSignature))); } })); } };

	}

	public static final common.NodeFactory<PproductionSignature> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionSignature> {

		@Override
		public PproductionSignature invoke(final Object[] children, final Object[] annotations) {
			return new PproductionSignature(children[0], children[1], children[2], annotations[0]);
		}
	};

}
