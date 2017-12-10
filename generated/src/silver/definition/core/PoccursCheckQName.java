
package silver.definition.core;

// top::OccursCheck ::= at::Decorated QName ntty::Type 
public final class PoccursCheckQName extends silver.definition.core.NOccursCheck {

	public static final int i_at = 0;
	public static final int i_ntty = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_occursCheckQName;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NOccursCheck.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NOccursCheck.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ntty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PoccursCheckQName(final Object c_at, final Object c_ntty) {
		super();
		this.child_at = c_at;
		this.child_ntty = c_ntty;

	}

	private Object child_at;
	public final common.DecoratedNode getChild_at() {
		return (common.DecoratedNode) (child_at = common.Util.demand(child_at));
	}

	private Object child_ntty;
	public final silver.definition.type.NType getChild_ntty() {
		return (silver.definition.type.NType) (child_ntty = common.Util.demand(child_ntty));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_at: return getChild_at();
			case i_ntty: return getChild_ntty();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_at: return child_at;
			case i_ntty: return child_ntty;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:occursCheckQName erroneously claimed to forward");
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
		return "silver:definition:core:occursCheckQName";
	}

	static void initProductionAttributeDefinitions() {
		// occursCheck = getOccursDcl(at.lookupAttribute.fullName, ntty.typeName, at.env)
		silver.definition.core.PoccursCheckQName.localAttributes[silver.definition.core.Init.occursCheck__ON__silver_definition_core_occursCheckQName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetOccursDcl.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.definition.core.PoccursCheckQName.i_at)).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_fullName__ON__silver_definition_core_QNameLookup)); } }, context.childDecoratedSynthesizedLazy(silver.definition.core.PoccursCheckQName.i_ntty, silver.definition.env.Init.silver_definition_env_typeName__ON__silver_definition_type_Type), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.definition.core.PoccursCheckQName.i_at)).inherited(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_QName)); } })); } };
		// top.errors := if null(at.lookupAttribute.errors) && null(occursCheck) then [ err(at.location, "Attribute '" ++ at.name ++ "' does not occur on '" ++ prettyType(ntty) ++ "'") ] else []
		if(silver.definition.core.PoccursCheckQName.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_OccursCheck] == null)
			silver.definition.core.PoccursCheckQName.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_OccursCheck] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_OccursCheck);
		((common.CollectionAttribute)silver.definition.core.PoccursCheckQName.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_OccursCheck]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.definition.core.PoccursCheckQName.i_at)).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } })) && ((Boolean)core.Pnull.invoke(context.localAsIsLazy(silver.definition.core.Init.occursCheck__ON__silver_definition_core_occursCheckQName)))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)((common.DecoratedNode)context.childAsIs(silver.definition.core.PoccursCheckQName.i_at)).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Attribute '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.definition.core.PoccursCheckQName.i_at)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("' does not occur on '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)silver.definition.type.PprettyType.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PoccursCheckQName.i_ntty)))), (common.StringCatter)(new common.StringCatter("'")))))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } });
		// top.typerep = if null(at.lookupAttribute.errors) && null(top.errors) then determineAttributeType(head(occursCheck), ntty) else errorType()
		silver.definition.core.PoccursCheckQName.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_OccursCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.definition.core.PoccursCheckQName.i_at)).synthesized(silver.definition.core.Init.silver_definition_core_lookupAttribute__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_QNameLookup)); } })) && ((Boolean)core.Pnull.invoke(context.contextSynthesizedLazy(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_OccursCheck)))) ? ((silver.definition.type.NType)silver.definition.env.PdetermineAttributeType.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)core.Phead.invoke(context.localAsIsLazy(silver.definition.core.Init.occursCheck__ON__silver_definition_core_occursCheckQName))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PoccursCheckQName.i_ntty)))) : ((silver.definition.type.NType)silver.definition.type.PerrorType.invoke())); } };
		// top.dcl = head(occursCheck)
		silver.definition.core.PoccursCheckQName.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_dcl__ON__silver_definition_core_OccursCheck] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)core.Phead.invoke(context.localAsIsLazy(silver.definition.core.Init.occursCheck__ON__silver_definition_core_occursCheckQName))); } };

	}

	public static final common.NodeFactory<PoccursCheckQName> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoccursCheckQName> {

		@Override
		public PoccursCheckQName invoke(final Object[] children, final Object[] annotations) {
			return new PoccursCheckQName(children[0], children[1]);
		}
	};

}
