
package silver.analysis.typechecking.core;

// top::TypeCheck ::= l::Type r::Type 
public final class Pcheck extends silver.analysis.typechecking.core.NTypeCheck {

	public static final int i_l = 0;
	public static final int i_r = 1;


	public static final Class<?> childTypes[] = {silver.definition.type.NType.class,silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_typechecking_core_check;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.analysis.typechecking.core.NTypeCheck.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public Pcheck(final Object c_l, final Object c_r) {
		super();
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child_l;
	public final silver.definition.type.NType getChild_l() {
		return (silver.definition.type.NType) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final silver.definition.type.NType getChild_r() {
		return (silver.definition.type.NType) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_r: return child_r;

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
		throw new common.exceptions.SilverInternalError("Production silver:analysis:typechecking:core:check erroneously claimed to forward");
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
		return "silver:analysis:typechecking:core:check";
	}

	static void initProductionAttributeDefinitions() {
		// top.upSubst = unifyCheck(l, r, top.downSubst)
		silver.analysis.typechecking.core.Pcheck.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)silver.definition.type.PunifyCheck.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.typechecking.core.Pcheck.i_l)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.typechecking.core.Pcheck.i_r)), context.contextInheritedLazy(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_analysis_typechecking_core_TypeCheck))); } };
		// top.typeerror = top.upSubst.failure
		silver.analysis.typechecking.core.Pcheck.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_typeerror__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Boolean)((silver.definition.type.NSubstitution)context.synthesized(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_analysis_typechecking_core_TypeCheck)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_failure__ON__silver_definition_type_Substitution)); } };
		// finleft = performSubstitution(l, top.finalSubst)
		silver.analysis.typechecking.core.Pcheck.localAttributes[silver.analysis.typechecking.core.Init.finleft__ON__silver_analysis_typechecking_core_check] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.typechecking.core.Pcheck.i_l)), context.contextInheritedLazy(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck))); } };
		// finright = performSubstitution(r, top.finalSubst)
		silver.analysis.typechecking.core.Pcheck.localAttributes[silver.analysis.typechecking.core.Init.finright__ON__silver_analysis_typechecking_core_check] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.analysis.typechecking.core.Pcheck.i_r)), context.contextInheritedLazy(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_analysis_typechecking_core_TypeCheck))); } };
		// fv = setUnionTyVars(finleft.freeVariables, finright.freeVariables)
		silver.analysis.typechecking.core.Pcheck.localAttributes[silver.analysis.typechecking.core.Init.fv__ON__silver_analysis_typechecking_core_check] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PsetUnionTyVars.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.analysis.typechecking.core.Init.finleft__ON__silver_analysis_typechecking_core_check).synthesized(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.analysis.typechecking.core.Init.finright__ON__silver_analysis_typechecking_core_check).synthesized(silver.definition.type.Init.silver_definition_type_freeVariables__ON__silver_definition_type_Type)); } })); } };
		// top.leftpp = prettyTypeWith(finleft, fv)
		silver.analysis.typechecking.core.Pcheck.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_leftpp__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.type.PprettyTypeWith.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.analysis.typechecking.core.Init.finleft__ON__silver_analysis_typechecking_core_check)), context.localAsIsLazy(silver.analysis.typechecking.core.Init.fv__ON__silver_analysis_typechecking_core_check))); } };
		// top.rightpp = prettyTypeWith(finright, fv)
		silver.analysis.typechecking.core.Pcheck.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_rightpp__ON__silver_analysis_typechecking_core_TypeCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)silver.definition.type.PprettyTypeWith.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.analysis.typechecking.core.Init.finright__ON__silver_analysis_typechecking_core_check)), context.localAsIsLazy(silver.analysis.typechecking.core.Init.fv__ON__silver_analysis_typechecking_core_check))); } };

	}

	public static final common.NodeFactory<Pcheck> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pcheck> {

		@Override
		public Pcheck invoke(final Object[] children, final Object[] annotations) {
			return new Pcheck(children[0], children[1]);
		}
	};

}
