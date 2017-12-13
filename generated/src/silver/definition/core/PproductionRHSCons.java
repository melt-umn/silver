
package silver.definition.core;

// top::ProductionRHS ::= h::ProductionRHSElem t::ProductionRHS 
public final class PproductionRHSCons extends silver.definition.core.NProductionRHS {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NProductionRHSElem.class,silver.definition.core.NProductionRHS.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_productionRHSCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionRHS.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionRHS.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver.definition.core.NProductionRHSElem.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.core.NProductionRHS.num_inh_attrs];

	}

	public PproductionRHSCons(final Object c_h, final Object c_t, final Object a_core_location) {
		super(a_core_location);
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver.definition.core.NProductionRHSElem getChild_h() {
		return (silver.definition.core.NProductionRHSElem) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver.definition.core.NProductionRHS getChild_t() {
		return (silver.definition.core.NProductionRHS) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:productionRHSCons erroneously claimed to forward");
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
		return "silver:definition:core:productionRHSCons";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = h.pp ++ " " ++ t.pp
		silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PproductionRHSCons.i_h).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionRHSElem)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.core.PproductionRHSCons.i_t).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_ProductionRHS)))); } };
		// top.defs = h.defs ++ t.defs
		silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_h, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionRHSElem), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_t, silver.definition.core.Init.silver_definition_env_defs__ON__silver_definition_core_ProductionRHS))); } };
		// top.errors := h.errors ++ t.errors
		if(silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionRHS] == null)
			silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionRHS] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionRHS);
		((common.CollectionAttribute)silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionRHS]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_h, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionRHSElem), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_t, silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_ProductionRHS))); } });
		// top.inputElements = h.inputElements ++ t.inputElements
		silver.definition.core.PproductionRHSCons.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_inputElements__ON__silver_definition_core_ProductionRHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_h, silver.definition.core.Init.silver_definition_env_inputElements__ON__silver_definition_core_ProductionRHSElem), context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_t, silver.definition.core.Init.silver_definition_env_inputElements__ON__silver_definition_core_ProductionRHS))); } };
		// h.deterministicCount = length(t.inputElements)
		silver.definition.core.PproductionRHSCons.childInheritedAttributes[silver.definition.core.PproductionRHSCons.i_h][silver.definition.core.Init.silver_definition_core_deterministicCount__ON__silver_definition_core_ProductionRHSElem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)core.PlistLength.invoke(context.childDecoratedSynthesizedLazy(silver.definition.core.PproductionRHSCons.i_t, silver.definition.core.Init.silver_definition_env_inputElements__ON__silver_definition_core_ProductionRHS))); } };

	}

	public static final common.NodeFactory<PproductionRHSCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionRHSCons> {

		@Override
		public PproductionRHSCons invoke(final Object[] children, final Object[] annotations) {
			return new PproductionRHSCons(children[0], children[1], annotations[0]);
		}
	};

}
