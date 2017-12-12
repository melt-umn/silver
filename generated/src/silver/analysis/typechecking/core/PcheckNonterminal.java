
package silver.analysis.typechecking.core;

// top::TypeCheck ::= l::Type 
public final class PcheckNonterminal extends silver.analysis.typechecking.core.NTypeCheck {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = {silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_typechecking_core_checkNonterminal;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PcheckNonterminal(final Object c_l) {
		super();
		this.child_l = c_l;

	}

	private Object child_l;
	public final silver.definition.type.NType getChild_l() {
		return (silver.definition.type.NType) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;

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
		throw new common.exceptions.SilverInternalError("Production silver:analysis:typechecking:core:checkNonterminal erroneously claimed to forward");
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
		return "silver:analysis:typechecking:core:checkNonterminal";
	}

	static void initProductionAttributeDefinitions() {
		// top.upSubst = composeSubst(ignoreFailure(top.downSubst), performSubstitution(l, top.downSubst).unifyInstanceNonterminal)
		silver.analysis.typechecking.core.PcheckNonterminal.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PcomposeSubst.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PignoreFailure.invoke(context.contextInheritedLazy(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.typechecking.core.PcheckNonterminal.i_l)), context.contextInheritedLazy(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_unifyInstanceNonterminal__ON__silver_definition_type_Type)); } })); } };
		// top.typeerror = top.upSubst.failure
		silver.analysis.typechecking.core.PcheckNonterminal.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)((silver.definition.type.NSubstitution)context.synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_failure__ON__silver_definition_type_Substitution)); } };
		// top.leftpp = prettyType(performSubstitution(l, top.finalSubst))
		silver.analysis.typechecking.core.PcheckNonterminal.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.type.PprettyType.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.typechecking.core.PcheckNonterminal.i_l)), context.contextInheritedLazy(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck))); } })); } };
		// top.rightpp = "a nonterminal"
		silver.analysis.typechecking.core.PcheckNonterminal.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("a nonterminal")); } };

	}

	public static final common.NodeFactory<PcheckNonterminal> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcheckNonterminal> {

		@Override
		public PcheckNonterminal invoke(final Object[] children, final Object[] annotations) {
			return new PcheckNonterminal(children[0]);
		}
	};

}
