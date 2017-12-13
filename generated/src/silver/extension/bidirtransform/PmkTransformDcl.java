
package silver.extension.bidirtransform;

// top::TransformDcl ::= s::String transType::TypeExpr trRules::TransformRuleList 
public final class PmkTransformDcl extends silver.extension.bidirtransform.NTransformDcl {

	public static final int i_s = 0;
	public static final int i_transType = 1;
	public static final int i_trRules = 2;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.type.syntax.NTypeExpr.class,silver.extension.bidirtransform.NTransformRuleList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_mkTransformDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_transType] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_trRules] = new common.Lazy[silver.extension.bidirtransform.NTransformRuleList.num_inh_attrs];

	}

	public PmkTransformDcl(final Object c_s, final Object c_transType, final Object c_trRules, final Object a_core_location) {
		super(a_core_location);
		this.child_s = c_s;
		this.child_transType = c_transType;
		this.child_trRules = c_trRules;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_transType;
	public final silver.definition.type.syntax.NTypeExpr getChild_transType() {
		return (silver.definition.type.syntax.NTypeExpr) (child_transType = common.Util.demand(child_transType));
	}

	private Object child_trRules;
	public final silver.extension.bidirtransform.NTransformRuleList getChild_trRules() {
		return (silver.extension.bidirtransform.NTransformRuleList) (child_trRules = common.Util.demand(child_trRules));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_transType: return getChild_transType();
			case i_trRules: return getChild_trRules();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_transType: return child_transType;
			case i_trRules: return child_trRules;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:bidirtransform:mkTransformDcl erroneously claimed to forward");
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
		return "silver:extension:bidirtransform:mkTransformDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = s ++ "::" ++ transType.pp ++ "{" ++ trRules.pp ++ "}"
		silver.extension.bidirtransform.PmkTransformDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PmkTransformDcl.i_s)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("::")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PmkTransformDcl.i_transType).synthesized(silver.definition.type.syntax.Init.silver_definition_env_pp__ON__silver_definition_type_syntax_TypeExpr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("{")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.bidirtransform.PmkTransformDcl.i_trRules).synthesized(silver.extension.bidirtransform.Init.silver_definition_env_pp__ON__silver_extension_bidirtransform_TransformRuleList)), (common.StringCatter)(new common.StringCatter("}"))))))); } };
		// top.errors := trRules.errors
		if(silver.extension.bidirtransform.PmkTransformDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformDcl] == null)
			silver.extension.bidirtransform.PmkTransformDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformDcl] = new silver.definition.core.CAerrors(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformDcl);
		((common.CollectionAttribute)silver.extension.bidirtransform.PmkTransformDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformDcl]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.bidirtransform.PmkTransformDcl.i_trRules).synthesized(silver.extension.bidirtransform.Init.silver_definition_core_errors__ON__silver_extension_bidirtransform_TransformRuleList)); } });
		// top.transformRules = trRules.transformRules
		silver.extension.bidirtransform.PmkTransformDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transformRules__ON__silver_extension_bidirtransform_TransformDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childDecorated(silver.extension.bidirtransform.PmkTransformDcl.i_trRules).synthesized(silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transformRules__ON__silver_extension_bidirtransform_TransformRuleList)); } };
		// top.transType = transType
		silver.extension.bidirtransform.PmkTransformDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_extension_bidirtransform_transType__ON__silver_extension_bidirtransform_TransformDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.extension.bidirtransform.PmkTransformDcl.i_transType).undecorate(); } };
		// top.name = s
		silver.extension.bidirtransform.PmkTransformDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_core_name__ON__silver_extension_bidirtransform_TransformDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PmkTransformDcl.i_s)); } };
		// top.typeName = transType.typerep.typeName
		silver.extension.bidirtransform.PmkTransformDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_definition_env_typeName__ON__silver_extension_bidirtransform_TransformDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.type.NType)context.childDecorated(silver.extension.bidirtransform.PmkTransformDcl.i_transType).synthesized(silver.definition.type.syntax.Init.silver_definition_env_typerep__ON__silver_definition_type_syntax_TypeExpr)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type)); } };
		// trRules.downSubst = top.downSubst
		silver.extension.bidirtransform.PmkTransformDcl.childInheritedAttributes[silver.extension.bidirtransform.PmkTransformDcl.i_trRules][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_downSubst__ON__silver_extension_bidirtransform_TransformDcl)); } };
		// top.upSubst = trRules.upSubst
		silver.extension.bidirtransform.PmkTransformDcl.synthesizedAttributes[silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.childDecorated(silver.extension.bidirtransform.PmkTransformDcl.i_trRules).synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformRuleList)); } };
		// trRules.finalSubst = top.upSubst
		silver.extension.bidirtransform.PmkTransformDcl.childInheritedAttributes[silver.extension.bidirtransform.PmkTransformDcl.i_trRules][silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_finalSubst__ON__silver_extension_bidirtransform_TransformRuleList] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.synthesized(silver.extension.bidirtransform.Init.silver_analysis_typechecking_core_upSubst__ON__silver_extension_bidirtransform_TransformDcl)); } };

	}

	public static final common.NodeFactory<PmkTransformDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmkTransformDcl> {

		@Override
		public PmkTransformDcl invoke(final Object[] children, final Object[] annotations) {
			return new PmkTransformDcl(children[0], children[1], children[2], annotations[0]);
		}
	};

}
