
package silver.modification.primitivepattern;

// top::VarBinders ::= 
public final class PnilVarBinder extends silver.modification.primitivepattern.NVarBinders {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_nilVarBinder;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.primitivepattern.NVarBinders.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.primitivepattern.NVarBinders.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilVarBinder(final Object a_core_location) {
		super(a_core_location);

	}



	@Override
	public Object getChild(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 0;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:primitivepattern:nilVarBinder erroneously claimed to forward");
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
		return "silver:modification:primitivepattern:nilVarBinder";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = ""
		silver.modification.primitivepattern.PnilVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.defs = []
		silver.modification.primitivepattern.PnilVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_defs__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.errors := []
		if(silver.modification.primitivepattern.PnilVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders] == null)
			silver.modification.primitivepattern.PnilVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders);
		((common.CollectionAttribute)silver.modification.primitivepattern.PnilVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.translation = ""
		silver.modification.primitivepattern.PnilVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("")); } };
		// top.varBinderCount = 0
		silver.modification.primitivepattern.PnilVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_modification_primitivepattern_varBinderCount__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)0); } };
		// top.flowProjections = []
		silver.modification.primitivepattern.PnilVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_modification_primitivepattern_flowProjections__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PnilVarBinder> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilVarBinder> {

		@Override
		public PnilVarBinder invoke(final Object[] children, final Object[] annotations) {
			return new PnilVarBinder(annotations[0]);
		}
	};

}
