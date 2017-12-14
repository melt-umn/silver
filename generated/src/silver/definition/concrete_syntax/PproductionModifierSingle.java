
package silver.definition.concrete_syntax;

// top::ProductionModifierList ::= pm::ProductionModifier 
public final class PproductionModifierSingle extends silver.definition.concrete_syntax.NProductionModifierList {

	public static final int i_pm = 0;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.NProductionModifier.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_productionModifierSingle;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.NProductionModifierList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.NProductionModifierList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_pm] = new common.Lazy[silver.definition.concrete_syntax.NProductionModifier.num_inh_attrs];

	}

	public PproductionModifierSingle(final Object c_pm, final Object a_core_location) {
		super(a_core_location);
		this.child_pm = c_pm;

	}

	private Object child_pm;
	public final silver.definition.concrete_syntax.NProductionModifier getChild_pm() {
		return (silver.definition.concrete_syntax.NProductionModifier) (child_pm = common.Util.demand(child_pm));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pm: return getChild_pm();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pm: return child_pm;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:productionModifierSingle erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:productionModifierSingle";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = pm.pp
		silver.definition.concrete_syntax.PproductionModifierSingle.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifierList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PproductionModifierSingle.i_pm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifier)); } };
		// top.productionModifiers = pm.productionModifiers
		silver.definition.concrete_syntax.PproductionModifierSingle.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifierList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.PproductionModifierSingle.i_pm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifier)); } };
		// top.errors := pm.errors
		if(silver.definition.concrete_syntax.PproductionModifierSingle.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifierList] == null)
			silver.definition.concrete_syntax.PproductionModifierSingle.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifierList] = new silver.definition.core.CAerrors(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifierList);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PproductionModifierSingle.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifierList]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.PproductionModifierSingle.i_pm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifier)); } });

	}

	public static final common.NodeFactory<PproductionModifierSingle> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionModifierSingle> {

		@Override
		public PproductionModifierSingle invoke(final Object[] children, final Object[] annotations) {
			return new PproductionModifierSingle(children[0], annotations[0]);
		}
	};

}
