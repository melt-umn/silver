
package silver.modification.let_fix;

// top::Expr ::= q::Decorated QName fi::ExprVertexInfo fd::[FlowVertex] 
public final class PlexicalLocalReference extends silver.definition.core.NExpr {

	public static final int i_q = 0;
	public static final int i_fi = 1;
	public static final int i_fd = 2;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,silver.definition.flow.ast.NExprVertexInfo.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_let_fix_lexicalLocalReference;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_fi] = new common.Lazy[silver.definition.flow.ast.NExprVertexInfo.num_inh_attrs];

	}

	public PlexicalLocalReference(final Object c_q, final Object c_fi, final Object c_fd, final Object a_core_location) {
		super(a_core_location);
		this.child_q = c_q;
		this.child_fi = c_fi;
		this.child_fd = c_fd;

	}

	private Object child_q;
	public final common.DecoratedNode getChild_q() {
		return (common.DecoratedNode) (child_q = common.Util.demand(child_q));
	}

	private Object child_fi;
	public final silver.definition.flow.ast.NExprVertexInfo getChild_fi() {
		return (silver.definition.flow.ast.NExprVertexInfo) (child_fi = common.Util.demand(child_fi));
	}

	private Object child_fd;
	public final common.ConsCell getChild_fd() {
		return (common.ConsCell) (child_fd = common.Util.demand(child_fd));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_q: return getChild_q();
			case i_fi: return getChild_fi();
			case i_fd: return getChild_fd();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_q: return child_q;
			case i_fi: return child_fi;
			case i_fd: return child_fd;

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
		throw new common.exceptions.SilverInternalError("Production silver:modification:let_fix:lexicalLocalReference erroneously claimed to forward");
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
		return "silver:modification:let_fix:lexicalLocalReference";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = q.pp
		silver.modification.let_fix.PlexicalLocalReference.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)context.childAsIs(silver.modification.let_fix.PlexicalLocalReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)); } };
		// top.errors := []
		if(silver.modification.let_fix.PlexicalLocalReference.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] == null)
			silver.modification.let_fix.PlexicalLocalReference.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr] = new silver.definition.core.CAerrors(silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr);
		((common.CollectionAttribute)silver.modification.let_fix.PlexicalLocalReference.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_errors__ON__silver_definition_core_Expr]).setBase(new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } });
		// top.typerep = if q.lookupValue.typerep.isDecorated then ntOrDecType(q.lookupValue.typerep.decoratedType, freshType()) else q.lookupValue.typerep
		silver.modification.let_fix.PlexicalLocalReference.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)((silver.definition.type.NType)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.let_fix.PlexicalLocalReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_isDecorated__ON__silver_definition_type_Type)) ? ((silver.definition.type.NType)new silver.definition.type.PntOrDecType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)((silver.definition.type.NType)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.let_fix.PlexicalLocalReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup)).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_decoratedType__ON__silver_definition_type_Type)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PfreshType.invoke()); } })) : ((silver.definition.type.NType)((common.DecoratedNode)((common.DecoratedNode)context.childAsIs(silver.modification.let_fix.PlexicalLocalReference.i_q)).synthesized(silver.definition.core.Init.silver_definition_core_lookupValue__ON__silver_definition_core_QName)).synthesized(silver.definition.core.Init.silver_definition_env_typerep__ON__silver_definition_core_QNameLookup))); } };
		// top.upSubst = top.downSubst
		silver.modification.let_fix.PlexicalLocalReference.synthesizedAttributes[silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_upSubst__ON__silver_definition_core_Expr] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.type.NSubstitution)context.inherited(silver.analysis.typechecking.core.Init.silver_analysis_typechecking_core_downSubst__ON__silver_definition_core_Expr)); } };

	}

	public static final common.NodeFactory<PlexicalLocalReference> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlexicalLocalReference> {

		@Override
		public PlexicalLocalReference invoke(final Object[] children, final Object[] annotations) {
			return new PlexicalLocalReference(children[0], children[1], children[2], annotations[0]);
		}
	};

}
