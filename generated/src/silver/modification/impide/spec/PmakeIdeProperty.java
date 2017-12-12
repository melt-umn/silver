
package silver.modification.impide.spec;

// top::IdeProperty ::= propName::String propType::PropType optional::Boolean defaultVal::String displayName::String 
public final class PmakeIdeProperty extends silver.modification.impide.spec.NIdeProperty {

	public static final int i_propName = 0;
	public static final int i_propType = 1;
	public static final int i_optional = 2;
	public static final int i_defaultVal = 3;
	public static final int i_displayName = 4;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.modification.impide.spec.NPropType.class,Boolean.class,common.StringCatter.class,common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_makeIdeProperty;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.impide.spec.NIdeProperty.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.impide.spec.NIdeProperty.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_propType] = new common.Lazy[silver.modification.impide.spec.NPropType.num_inh_attrs];

	}

	public PmakeIdeProperty(final Object c_propName, final Object c_propType, final Object c_optional, final Object c_defaultVal, final Object c_displayName) {
		super();
		this.child_propName = c_propName;
		this.child_propType = c_propType;
		this.child_optional = c_optional;
		this.child_defaultVal = c_defaultVal;
		this.child_displayName = c_displayName;

	}

	private Object child_propName;
	public final common.StringCatter getChild_propName() {
		return (common.StringCatter) (child_propName = common.Util.demand(child_propName));
	}

	private Object child_propType;
	public final silver.modification.impide.spec.NPropType getChild_propType() {
		return (silver.modification.impide.spec.NPropType) (child_propType = common.Util.demand(child_propType));
	}

	private Object child_optional;
	public final Boolean getChild_optional() {
		return (Boolean) (child_optional = common.Util.demand(child_optional));
	}

	private Object child_defaultVal;
	public final common.StringCatter getChild_defaultVal() {
		return (common.StringCatter) (child_defaultVal = common.Util.demand(child_defaultVal));
	}

	private Object child_displayName;
	public final common.StringCatter getChild_displayName() {
		return (common.StringCatter) (child_displayName = common.Util.demand(child_displayName));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_propName: return getChild_propName();
			case i_propType: return getChild_propType();
			case i_optional: return getChild_optional();
			case i_defaultVal: return getChild_defaultVal();
			case i_displayName: return getChild_displayName();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_propName: return child_propName;
			case i_propType: return child_propType;
			case i_optional: return child_optional;
			case i_defaultVal: return child_defaultVal;
			case i_displayName: return child_displayName;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:impide:spec:makeIdeProperty erroneously claimed to forward");
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
		return "silver:modification:impide:spec:makeIdeProperty";
	}

	static void initProductionAttributeDefinitions() {
		// top.controlJavaTranslation = "    controls.add(new " ++ propType.propControl ++ "(panel, \"" ++ propName ++ "\", \"" ++ displayName ++ "\", \"" ++ defaultVal ++ "\", " ++ if optional then "false" else "true" ++ "));\n"
		silver.modification.impide.spec.PmakeIdeProperty.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_controlJavaTranslation__ON__silver_modification_impide_spec_IdeProperty] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("    controls.add(new ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PmakeIdeProperty.i_propType).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_propControl__ON__silver_modification_impide_spec_PropType)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("(panel, \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PmakeIdeProperty.i_propName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\", \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PmakeIdeProperty.i_displayName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\", \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PmakeIdeProperty.i_defaultVal)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\", ")), (common.StringCatter)new common.StringCatter((common.StringCatter)(((Boolean)context.childAsIs(silver.modification.impide.spec.PmakeIdeProperty.i_optional)) ? (new common.StringCatter("false")) : (new common.StringCatter("true"))), (common.StringCatter)(new common.StringCatter("));\n")))))))))))); } };
		// top.generatorJavaTranslation = "    sb.append(\"" ++ propName ++ "/" ++ propType.strPropType ++ " = \");sb.append(escape(\"" ++ defaultVal ++ "\"));sb.append(\"\\n\");\n"
		silver.modification.impide.spec.PmakeIdeProperty.synthesizedAttributes[silver.modification.impide.spec.Init.silver_modification_impide_spec_generatorJavaTranslation__ON__silver_modification_impide_spec_IdeProperty] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("    sb.append(\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PmakeIdeProperty.i_propName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.modification.impide.spec.PmakeIdeProperty.i_propType).synthesized(silver.modification.impide.spec.Init.silver_modification_impide_spec_strPropType__ON__silver_modification_impide_spec_PropType)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" = \");sb.append(escape(\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PmakeIdeProperty.i_defaultVal)), (common.StringCatter)(new common.StringCatter("\"));sb.append(\"\\n\");\n")))))))); } };

	}

	public static final common.NodeFactory<PmakeIdeProperty> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmakeIdeProperty> {

		@Override
		public PmakeIdeProperty invoke(final Object[] children, final Object[] annotations) {
			return new PmakeIdeProperty(children[0], children[1], children[2], children[3], children[4]);
		}
	};

}
