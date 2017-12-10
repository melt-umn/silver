
package silver.definition.env;

// top::Defs ::= e1::Def e2::Defs 
public final class PconsDefs extends silver.definition.env.NDefs {

	public static final int i_e1 = 0;
	public static final int i_e2 = 1;


	public static final Class<?> childTypes[] = {silver.definition.env.NDef.class,silver.definition.env.NDefs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_consDefs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NDefs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NDefs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e1] = new common.Lazy[silver.definition.env.NDef.num_inh_attrs];
	childInheritedAttributes[i_e2] = new common.Lazy[silver.definition.env.NDefs.num_inh_attrs];

	}

	public PconsDefs(final Object c_e1, final Object c_e2) {
		super();
		this.child_e1 = c_e1;
		this.child_e2 = c_e2;

	}

	private Object child_e1;
	public final silver.definition.env.NDef getChild_e1() {
		return (silver.definition.env.NDef) (child_e1 = common.Util.demand(child_e1));
	}

	private Object child_e2;
	public final silver.definition.env.NDefs getChild_e2() {
		return (silver.definition.env.NDefs) (child_e2 = common.Util.demand(child_e2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e1: return getChild_e1();
			case i_e2: return getChild_e2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e1: return child_e1;
			case i_e2: return child_e2;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:consDefs erroneously claimed to forward");
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
		return "silver:definition:env:consDefs";
	}

	static void initProductionAttributeDefinitions() {
		// top.typeList = e1.typeList ++ e2.typeList
		silver.definition.env.PconsDefs.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typeList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e1, silver.definition.env.Init.silver_definition_env_typeList__ON__silver_definition_env_Def), context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e2, silver.definition.env.Init.silver_definition_env_typeList__ON__silver_definition_env_Defs))); } };
		// top.valueList = e1.valueList ++ e2.valueList
		silver.definition.env.PconsDefs.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_valueList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e1, silver.definition.env.Init.silver_definition_env_valueList__ON__silver_definition_env_Def), context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e2, silver.definition.env.Init.silver_definition_env_valueList__ON__silver_definition_env_Defs))); } };
		// top.attrList = e1.attrList ++ e2.attrList
		silver.definition.env.PconsDefs.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_attrList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e1, silver.definition.env.Init.silver_definition_env_attrList__ON__silver_definition_env_Def), context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e2, silver.definition.env.Init.silver_definition_env_attrList__ON__silver_definition_env_Defs))); } };
		// top.prodOccursList = e1.prodOccursList ++ e2.prodOccursList
		silver.definition.env.PconsDefs.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodOccursList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e1, silver.definition.env.Init.silver_definition_env_prodOccursList__ON__silver_definition_env_Def), context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e2, silver.definition.env.Init.silver_definition_env_prodOccursList__ON__silver_definition_env_Defs))); } };
		// top.occursList = e1.occursList ++ e2.occursList
		silver.definition.env.PconsDefs.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_occursList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e1, silver.definition.env.Init.silver_definition_env_occursList__ON__silver_definition_env_Def), context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e2, silver.definition.env.Init.silver_definition_env_occursList__ON__silver_definition_env_Defs))); } };
		// top.prodDclList = e1.prodDclList ++ e2.prodDclList
		silver.definition.env.PconsDefs.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_prodDclList__ON__silver_definition_env_Defs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e1, silver.definition.env.Init.silver_definition_env_prodDclList__ON__silver_definition_env_Def), context.childDecoratedSynthesizedLazy(silver.definition.env.PconsDefs.i_e2, silver.definition.env.Init.silver_definition_env_prodDclList__ON__silver_definition_env_Defs))); } };

	}

	public static final common.NodeFactory<PconsDefs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsDefs> {

		@Override
		public PconsDefs invoke(final Object[] children, final Object[] annotations) {
			return new PconsDefs(children[0], children[1]);
		}
	};

}
