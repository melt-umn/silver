
package silver.modification.primitivepattern;

// top::VarBinders ::= v::VarBinder ',' vs::VarBinders 
public final class PconsVarBinder extends silver.modification.primitivepattern.NVarBinders {

	public static final int i_v = 0;
	public static final int i__G_1 = 1;
	public static final int i_vs = 2;


	public static final Class<?> childTypes[] = {silver.modification.primitivepattern.NVarBinder.class,silver.definition.core.TComma_t.class,silver.modification.primitivepattern.NVarBinders.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_primitivepattern_consVarBinder;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.primitivepattern.NVarBinders.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.primitivepattern.NVarBinders.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_v] = new common.Lazy[silver.modification.primitivepattern.NVarBinder.num_inh_attrs];
	childInheritedAttributes[i_vs] = new common.Lazy[silver.modification.primitivepattern.NVarBinders.num_inh_attrs];

	}

	public PconsVarBinder(final Object c_v, final Object c__G_1, final Object c_vs, final Object a_core_location) {
		super(a_core_location);
		this.child_v = c_v;
		this.child__G_1 = c__G_1;
		this.child_vs = c_vs;

	}

	private Object child_v;
	public final silver.modification.primitivepattern.NVarBinder getChild_v() {
		return (silver.modification.primitivepattern.NVarBinder) (child_v = common.Util.demand(child_v));
	}

	private Object child__G_1;
	public final silver.definition.core.TComma_t getChild__G_1() {
		return (silver.definition.core.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_vs;
	public final silver.modification.primitivepattern.NVarBinders getChild_vs() {
		return (silver.modification.primitivepattern.NVarBinders) (child_vs = common.Util.demand(child_vs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_v: return getChild_v();
			case i__G_1: return getChild__G_1();
			case i_vs: return getChild_vs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_v: return child_v;
			case i__G_1: return child__G_1;
			case i_vs: return child_vs;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:primitivepattern:consVarBinder erroneously claimed to forward");
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
		return "silver:modification:primitivepattern:consVarBinder";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = v.pp ++ ", " ++ vs.pp
		silver.modification.primitivepattern.PconsVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconsVarBinder.i_v).synthesized(silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_VarBinder)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconsVarBinder.i_vs).synthesized(silver.modification.primitivepattern.Init.silver_definition_env_pp__ON__silver_modification_primitivepattern_VarBinders)))); } };
		// top.defs = v.defs ++ vs.defs
		silver.modification.primitivepattern.PconsVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_env_defs__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PconsVarBinder.i_v, silver.modification.primitivepattern.Init.silver_definition_env_defs__ON__silver_modification_primitivepattern_VarBinder), context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PconsVarBinder.i_vs, silver.modification.primitivepattern.Init.silver_definition_env_defs__ON__silver_modification_primitivepattern_VarBinders))); } };
		// top.errors := v.errors ++ vs.errors
		if(silver.modification.primitivepattern.PconsVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders] == null)
			silver.modification.primitivepattern.PconsVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders] = new silver.definition.core.CAerrors(silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders);
		((common.CollectionAttribute)silver.modification.primitivepattern.PconsVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PconsVarBinder.i_v, silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinder), context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PconsVarBinder.i_vs, silver.modification.primitivepattern.Init.silver_definition_core_errors__ON__silver_modification_primitivepattern_VarBinders))); } });
		// top.translation = v.translation ++ vs.translation
		silver.modification.primitivepattern.PconsVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconsVarBinder.i_v).synthesized(silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_VarBinder)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.primitivepattern.PconsVarBinder.i_vs).synthesized(silver.modification.primitivepattern.Init.silver_translation_java_core_translation__ON__silver_modification_primitivepattern_VarBinders))); } };
		// top.varBinderCount = 1 + vs.varBinderCount
		silver.modification.primitivepattern.PconsVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_modification_primitivepattern_varBinderCount__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf((int)1) + ((Integer)context.childDecorated(silver.modification.primitivepattern.PconsVarBinder.i_vs).synthesized(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_varBinderCount__ON__silver_modification_primitivepattern_VarBinders))); } };
		// top.flowProjections = v.flowProjections ++ vs.flowProjections
		silver.modification.primitivepattern.PconsVarBinder.synthesizedAttributes[silver.modification.primitivepattern.Init.silver_modification_primitivepattern_flowProjections__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PconsVarBinder.i_v, silver.modification.primitivepattern.Init.silver_modification_primitivepattern_flowProjections__ON__silver_modification_primitivepattern_VarBinder), context.childDecoratedSynthesizedLazy(silver.modification.primitivepattern.PconsVarBinder.i_vs, silver.modification.primitivepattern.Init.silver_modification_primitivepattern_flowProjections__ON__silver_modification_primitivepattern_VarBinders))); } };
		// v.bindingIndex = top.bindingIndex
		silver.modification.primitivepattern.PconsVarBinder.childInheritedAttributes[silver.modification.primitivepattern.PconsVarBinder.i_v][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingIndex__ON__silver_modification_primitivepattern_VarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.inherited(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingIndex__ON__silver_modification_primitivepattern_VarBinders)); } };
		// vs.bindingIndex = top.bindingIndex + 1
		silver.modification.primitivepattern.PconsVarBinder.childInheritedAttributes[silver.modification.primitivepattern.PconsVarBinder.i_vs][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingIndex__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.inherited(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingIndex__ON__silver_modification_primitivepattern_VarBinders)) + Integer.valueOf((int)1)); } };
		// v.bindingType = if null(top.bindingTypes) then errorType() else head(top.bindingTypes)
		silver.modification.primitivepattern.PconsVarBinder.childInheritedAttributes[silver.modification.primitivepattern.PconsVarBinder.i_v][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingType__ON__silver_modification_primitivepattern_VarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingTypes__ON__silver_modification_primitivepattern_VarBinders))) ? ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke()) : ((silver.definition.type.NType)core.Phead.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingTypes__ON__silver_modification_primitivepattern_VarBinders)))); } };
		// vs.bindingTypes = if null(top.bindingTypes) then [] else tail(top.bindingTypes)
		silver.modification.primitivepattern.PconsVarBinder.childInheritedAttributes[silver.modification.primitivepattern.PconsVarBinder.i_vs][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingTypes__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingTypes__ON__silver_modification_primitivepattern_VarBinders))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Ptail.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingTypes__ON__silver_modification_primitivepattern_VarBinders)))); } };
		// v.bindingName = if null(top.bindingNames) then "__NONAME" else head(top.bindingNames)
		silver.modification.primitivepattern.PconsVarBinder.childInheritedAttributes[silver.modification.primitivepattern.PconsVarBinder.i_v][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingName__ON__silver_modification_primitivepattern_VarBinder] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingNames__ON__silver_modification_primitivepattern_VarBinders))) ? (new common.StringCatter("__NONAME")) : ((common.StringCatter)core.Phead.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingNames__ON__silver_modification_primitivepattern_VarBinders)))); } };
		// vs.bindingNames = if null(top.bindingNames) then [] else tail(top.bindingNames)
		silver.modification.primitivepattern.PconsVarBinder.childInheritedAttributes[silver.modification.primitivepattern.PconsVarBinder.i_vs][silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingNames__ON__silver_modification_primitivepattern_VarBinders] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingNames__ON__silver_modification_primitivepattern_VarBinders))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Ptail.invoke(context.contextInheritedLazy(silver.modification.primitivepattern.Init.silver_modification_primitivepattern_bindingNames__ON__silver_modification_primitivepattern_VarBinders)))); } };

	}

	public static final common.NodeFactory<PconsVarBinder> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsVarBinder> {

		@Override
		public PconsVarBinder invoke(final Object[] children, final Object[] annotations) {
			return new PconsVarBinder(children[0], children[1], children[2], annotations[0]);
		}
	};

}
