
package silver.modification.copper;

// top::Expr ::= q::Decorated QName 
public final class PactionChildReference extends silver.definition.core.NExpr {

	public static final int i_q = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_actionChildReference;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PactionChildReference(final Object c_q, final Object a_core_location) {
		super(a_core_location);
		this.child_q = c_q;

	}

	private Object child_q;
	public final common.DecoratedNode getChild_q() {
		return (common.DecoratedNode) (child_q = common.Util.demand(child_q));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:actionChildReference erroneously claimed to forward");
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
		return "silver:modification:copper:actionChildReference";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = q.pp
		silver.modification.copper.PactionChildReference.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PactionChildReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)); } };
		// top.errors := []
		if(silver.modification.copper.PactionChildReference.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.modification.copper.PactionChildReference.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.modification.copper.PactionChildReference.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.typerep = q.lookupValue.typerep
		silver.modification.copper.PactionChildReference.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PactionChildReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } };
		// top.translation = "((" ++ q.lookupValue.typerep.transType ++ ")((common.Node)RESULT).getChild(" ++ top.frame.className ++ ".i_" ++ q.lookupValue.fullName ++ "))"
		silver.modification.copper.PactionChildReference.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("((")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.type.NType)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PactionChildReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.type.Init.silver_translation_java_type_transType__ON__silver_definition_type_Type)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(")((common.Node)RESULT).getChild(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.definition.core.NBlockContext)context.inherited(silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_Expr)).decorate(context, (common.Lazy[])null).synthesized(silver.translation.java.core.Init.silver_translation_java_core_className__ON__silver_definition_core_BlockContext)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(".i_")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PactionChildReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)), (common.StringCatter)(new common.StringCatter("))")))))))); } };
		// top.lazyTranslation = top.translation
		silver.modification.copper.PactionChildReference.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_lazyTranslation__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.synthesized(silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_Expr)); } };
		// top.upSubst = top.downSubst
		silver.modification.copper.PactionChildReference.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr)); } };

	}

	public static final common.NodeFactory<PactionChildReference> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PactionChildReference> {

		@Override
		public PactionChildReference invoke(final Object[] children, final Object[] annotations) {
			return new PactionChildReference(children[0], annotations[0]);
		}
	};

}
