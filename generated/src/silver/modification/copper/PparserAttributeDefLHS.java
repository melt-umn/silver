
package silver.modification.copper;

// top::DefLHS ::= q::Decorated QName 
public final class PparserAttributeDefLHS extends silver.definition.core.NDefLHS {

	public static final int i_q = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_parserAttributeDefLHS;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NDefLHS.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NDefLHS.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PparserAttributeDefLHS(final Object c_q, final Object a_core_location) {
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:copper:parserAttributeDefLHS erroneously claimed to forward");
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
		return "silver:modification:copper:parserAttributeDefLHS";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = q.pp
		silver.modification.copper.PparserAttributeDefLHS.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_DefLHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PparserAttributeDefLHS.i_q)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)); } };
		// top.errors := if ! top.frame.permitActions then [ err(q.location, "Parser attributes can only be used in action blocks") ] else [ err(q.location, "Parser action blocks are imperative, not declarative. You cannot modify the attributes of " ++ q.name ++ ". If you are trying to set inherited attributes, you should use 'decorate ... with { ... }' when you create it.") ]
		if(silver.modification.copper.PparserAttributeDefLHS.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_DefLHS] == null)
			silver.modification.copper.PparserAttributeDefLHS.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_DefLHS] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_DefLHS);
		((common.CollectionAttribute)silver.modification.copper.PparserAttributeDefLHS.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_DefLHS]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((!((Boolean)((silver.definition.core.NBlockContext)context.inherited(silver.definition.core.Init.silver_definition_core_frame__ON__silver_definition_core_DefLHS)).decorate(context, (common.Lazy[])null).synthesized(silver.modification.copper.Init.silver_modification_copper_permitActions__ON__silver_definition_core_BlockContext))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PparserAttributeDefLHS.i_q)).undecorate()).getAnno_core_location()); } }, (new common.StringCatter("Parser attributes can only be used in action blocks")))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NMessage)new silver.definition.core.Perr(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NQName)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PparserAttributeDefLHS.i_q)).undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Parser action blocks are imperative, not declarative. You cannot modify the attributes of ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PparserAttributeDefLHS.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName)), (common.StringCatter)(new common.StringCatter(". If you are trying to set inherited attributes, you should use 'decorate ... with { ... }' when you create it.")))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }))); } });
		// top.translation = error("Internal compiler error: translation not defined in the presence of errors")
		silver.modification.copper.PparserAttributeDefLHS.synthesizedAttributes[silver.translation.java.core.Init.silver_translation_java_core_translation__ON__silver_definition_core_DefLHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Internal compiler error: translation not defined in the presence of errors")))); } };
		// top.typerep = q.lookupValue.typerep
		silver.modification.copper.PparserAttributeDefLHS.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_DefLHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.copper.PparserAttributeDefLHS.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)); } };

	}

	public static final common.NodeFactory<PparserAttributeDefLHS> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PparserAttributeDefLHS> {

		@Override
		public PparserAttributeDefLHS invoke(final Object[] children, final Object[] annotations) {
			return new PparserAttributeDefLHS(children[0], annotations[0]);
		}
	};

}
