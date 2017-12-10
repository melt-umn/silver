
package silver.definition.env;

// top::Defs ::= 
public final class PnilDefs extends silver.definition.env.NDefs {



	public static final Class<?> childTypes[] = {};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_nilDefs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NDefs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NDefs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[0][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilDefs() {
		super();

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:nilDefs erroneously claimed to forward");
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
		return "silver:definition:env:nilDefs";
	}

	static void initProductionAttributeDefinitions() {
		// top.typeList = []
		silver.definition.env.PnilDefs.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typeList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.valueList = []
		silver.definition.env.PnilDefs.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_valueList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.attrList = []
		silver.definition.env.PnilDefs.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_attrList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.prodOccursList = []
		silver.definition.env.PnilDefs.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodOccursList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.occursList = []
		silver.definition.env.PnilDefs.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_occursList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.prodDclList = []
		silver.definition.env.PnilDefs.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodDclList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };

	}

	public static final common.NodeFactory<PnilDefs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilDefs> {

		@Override
		public PnilDefs invoke(final Object[] children, final Object[] annotations) {
			return new PnilDefs();
		}
	};

}
