
package silver.definition.concrete_syntax;

// top::ProductionModifiers ::= pm::ProductionModifierList 
public final class PproductionModifierSome extends silver.definition.concrete_syntax.NProductionModifiers {

	public static final int i_pm = 0;


	public static final Class<?> childTypes[] = {silver.definition.concrete_syntax.NProductionModifierList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_productionModifierSome;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.concrete_syntax.NProductionModifiers.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.concrete_syntax.NProductionModifiers.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_pm] = new common.Lazy[silver.definition.concrete_syntax.NProductionModifierList.num_inh_attrs];

	}

	public PproductionModifierSome(final Object c_pm, final Object a_core_location) {
		super(a_core_location);
		this.child_pm = c_pm;

	}

	private Object child_pm;
	public final silver.definition.concrete_syntax.NProductionModifierList getChild_pm() {
		return (silver.definition.concrete_syntax.NProductionModifierList) (child_pm = common.Util.demand(child_pm));
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:concrete_syntax:productionModifierSome erroneously claimed to forward");
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
		return "silver:definition:concrete_syntax:productionModifierSome";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = pm.pp
		silver.definition.concrete_syntax.PproductionModifierSome.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.definition.concrete_syntax.PproductionModifierSome.i_pm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_env_pp__ON__silver_definition_concrete_syntax_ProductionModifierList)); } };
		// top.productionModifiers = pm.productionModifiers
		silver.definition.concrete_syntax.PproductionModifierSome.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifiers] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.PproductionModifierSome.i_pm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_concrete_syntax_productionModifiers__ON__silver_definition_concrete_syntax_ProductionModifierList)); } };
		// top.errors := pm.errors
		if(silver.definition.concrete_syntax.PproductionModifierSome.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifiers] == null)
			silver.definition.concrete_syntax.PproductionModifierSome.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifiers] = new silver.definition.core.CAerrors(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifiers);
		((common.CollectionAttribute)silver.definition.concrete_syntax.PproductionModifierSome.synthesizedAttributes[silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifiers]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.definition.concrete_syntax.PproductionModifierSome.i_pm).synthesized(silver.definition.concrete_syntax.Init.silver_definition_core_errors__ON__silver_definition_concrete_syntax_ProductionModifierList)); } });

	}

	public static final common.NodeFactory<PproductionModifierSome> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PproductionModifierSome> {

		@Override
		public PproductionModifierSome invoke(final Object[] children, final Object[] annotations) {
			return new PproductionModifierSome(children[0], annotations[0]);
		}
	};

}
