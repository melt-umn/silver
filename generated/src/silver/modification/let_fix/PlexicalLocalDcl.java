
package silver.modification.let_fix;

// top::DclInfo ::= sg::String sl::Location fn::String ty::Type fi::ExprVertexInfo fd::[FlowVertex] 
public final class PlexicalLocalDcl extends silver.definition.env.NDclInfo {

	public static final int i_sg = 0;
	public static final int i_sl = 1;
	public static final int i_fn = 2;
	public static final int i_ty = 3;
	public static final int i_fi = 4;
	public static final int i_fd = 5;


	public static final Class<?> childTypes[] = {common.StringCatter.class,core.NLocation.class,common.StringCatter.class,silver.definition.type.NType.class,silver.definition.flow.ast.NExprVertexInfo.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_let_fix_lexicalLocalDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NDclInfo.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_fi] = new common.Lazy[silver.definition.flow.ast.NExprVertexInfo.num_inh_attrs];

	}

	public PlexicalLocalDcl(final Object c_sg, final Object c_sl, final Object c_fn, final Object c_ty, final Object c_fi, final Object c_fd) {
		super();
		this.child_sg = c_sg;
		this.child_sl = c_sl;
		this.child_fn = c_fn;
		this.child_ty = c_ty;
		this.child_fi = c_fi;
		this.child_fd = c_fd;

	}

	private Object child_sg;
	public final common.StringCatter getChild_sg() {
		return (common.StringCatter) (child_sg = common.Util.demand(child_sg));
	}

	private Object child_sl;
	public final core.NLocation getChild_sl() {
		return (core.NLocation) (child_sl = common.Util.demand(child_sl));
	}

	private Object child_fn;
	public final common.StringCatter getChild_fn() {
		return (common.StringCatter) (child_fn = common.Util.demand(child_fn));
	}

	private Object child_ty;
	public final silver.definition.type.NType getChild_ty() {
		return (silver.definition.type.NType) (child_ty = common.Util.demand(child_ty));
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
			case i_sg: return getChild_sg();
			case i_sl: return getChild_sl();
			case i_fn: return getChild_fn();
			case i_ty: return getChild_ty();
			case i_fi: return getChild_fi();
			case i_fd: return getChild_fd();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sg: return child_sg;
			case i_sl: return child_sl;
			case i_fn: return child_fn;
			case i_ty: return child_ty;
			case i_fi: return child_fi;
			case i_fd: return child_fd;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		throw new common.exceptions.SilverInternalError("Production silver:modification:let_fix:lexicalLocalDcl erroneously claimed to forward");
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
		return "silver:modification:let_fix:lexicalLocalDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.sourceGrammar = sg
		silver.modification.let_fix.PlexicalLocalDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.modification.let_fix.PlexicalLocalDcl.i_sg)); } };
		// top.sourceLocation = sl
		silver.modification.let_fix.PlexicalLocalDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_sourceLocation__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.modification.let_fix.PlexicalLocalDcl.i_sl).undecorate(); } };
		// top.fullName = fn
		silver.modification.let_fix.PlexicalLocalDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.modification.let_fix.PlexicalLocalDcl.i_fn)); } };
		// top.unparse = error("Internal compiler error: locally scoped declaration that should never appear in interface files")
		silver.modification.let_fix.PlexicalLocalDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Internal compiler error: locally scoped declaration that should never appear in interface files")))); } };
		// top.typerep = ty
		silver.modification.let_fix.PlexicalLocalDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.modification.let_fix.PlexicalLocalDcl.i_ty).undecorate(); } };
		// top.refDispatcher = lexicalLocalReference(_, fi, fd,location=_)
		silver.modification.let_fix.PlexicalLocalDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_refDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.modification.let_fix.PlexicalLocalReference.factory.invokePartial(new int[]{1, 2}, new Object[]{common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.let_fix.PlexicalLocalDcl.i_fi)), context.childAsIsLazy(silver.modification.let_fix.PlexicalLocalDcl.i_fd)}).invokeNamedPartial(new int[]{0}, null, null); } };
		// top.defDispatcher = errorValueDef(_, _,location=_)
		silver.modification.let_fix.PlexicalLocalDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_defDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.core.PerrorValueDef.factory.invokeNamedPartial(new int[]{0}, null, null); } };
		// top.defLHSDispatcher = errorDefLHS(_,location=_)
		silver.modification.let_fix.PlexicalLocalDcl.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_defLHSDispatcher__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.core.PerrorDefLHS.factory.invokeNamedPartial(new int[]{0}, null, null); } };

	}

	public static final common.NodeFactory<PlexicalLocalDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlexicalLocalDcl> {

		@Override
		public PlexicalLocalDcl invoke(final Object[] children, final Object[] annotations) {
			return new PlexicalLocalDcl(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};

}
