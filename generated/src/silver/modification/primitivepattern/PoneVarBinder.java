
package silver.modification.primitivepattern;

// top::VarBinders ::= v::VarBinder 
public final class PoneVarBinder extends silver.modification.primitivepattern.NVarBinders {

	public static final int i_v = 0;


	public static final Class<?> childTypes[] = {silver.modification.primitivepattern.NVarBinder.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_oneVarBinder;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.primitivepattern.NVarBinders.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.primitivepattern.NVarBinders.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_v] = new common.Lazy[silver.modification.primitivepattern.NVarBinder.num_inh_attrs];

	}

	public PoneVarBinder(final Object c_v, final Object a_core_location) {
		super(a_core_location);
		this.child_v = c_v;

	}

	private Object child_v;
	public final silver.modification.primitivepattern.NVarBinder getChild_v() {
		return (silver.modification.primitivepattern.NVarBinder) (child_v = common.Util.demand(child_v));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_v: return getChild_v();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_v: return child_v;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:primitivepattern:oneVarBinder erroneously claimed to forward");
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
		return "silver:modification:primitivepattern:oneVarBinder";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = v.pp
		silver.modification.primitivepattern.PoneVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PoneVarBinder.i_v).synthesized(silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_VarBinder)); } };
		// top.defs = v.defs
		silver.modification.primitivepattern.PoneVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_defs__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.primitivepattern.PoneVarBinder.i_v).synthesized(silver.modification.primitivepattern.Init.silver_definition_env_defs__ON__silver_modification_primitivepattern_VarBinder)); } };
		// top.errors := v.errors
		if(silver.modification.primitivepattern.PoneVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders] == null)
			silver.modification.primitivepattern.PoneVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders);
		((common.CollectionAttribute)silver.modification.primitivepattern.PoneVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.primitivepattern.PoneVarBinder.i_v).synthesized(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinder)); } });
		// top.translation = v.translation
		silver.modification.primitivepattern.PoneVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PoneVarBinder.i_v).synthesized(silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_VarBinder)); } };
		// top.varBinderCount = 1
		silver.modification.primitivepattern.PoneVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_modification_primitivepattern_varBinderCount__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf((int)1); } };
		// top.flowProjections = v.flowProjections
		silver.modification.primitivepattern.PoneVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_modification_primitivepattern_flowProjections__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.modification.primitivepattern.PoneVarBinder.i_v).synthesized(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_flowProjections__ON__silver_modification_primitivepattern_VarBinder)); } };
		// v.bindingIndex = top.bindingIndex
		silver.modification.primitivepattern.PoneVarBinder.childInheritedAttributes[silver.modification.primitivepattern.PoneVarBinder.i_v][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingIndex__ON__silver_modification_primitivepattern_VarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.inherited(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingIndex__ON__silver_modification_primitivepattern_VarBinders)); } };
		// v.bindingType = if null(top.bindingTypes) then errorType() else head(top.bindingTypes)
		silver.modification.primitivepattern.PoneVarBinder.childInheritedAttributes[silver.modification.primitivepattern.PoneVarBinder.i_v][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingType__ON__silver_modification_primitivepattern_VarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingTypes__ON__silver_modification_primitivepattern_VarBinders))) ? ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke()) : ((silver.definition.type.NType)core.Phead.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingTypes__ON__silver_modification_primitivepattern_VarBinders)))); } };
		// v.bindingName = if null(top.bindingNames) then "__NONAME" else head(top.bindingNames)
		silver.modification.primitivepattern.PoneVarBinder.childInheritedAttributes[silver.modification.primitivepattern.PoneVarBinder.i_v][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingName__ON__silver_modification_primitivepattern_VarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingNames__ON__silver_modification_primitivepattern_VarBinders))) ? (new common.StringCatter("__NONAME")) : ((common.StringCatter)core.Phead.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingNames__ON__silver_modification_primitivepattern_VarBinders)))); } };

	}

	public static final common.NodeFactory<PoneVarBinder> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoneVarBinder> {

		@Override
		public PoneVarBinder invoke(final Object[] children, final Object[] annotations) {
			return new PoneVarBinder(children[0], annotations[0]);
		}
	};

}
