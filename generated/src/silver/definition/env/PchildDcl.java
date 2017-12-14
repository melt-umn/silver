
package silver.definition.env;

// top::DclInfo ::= sg::String sl::Location fn::String ty::Type 
public final class PchildDcl extends silver.definition.env.NDclInfo {

	public static final int i_sg = 0;
	public static final int i_sl = 1;
	public static final int i_fn = 2;
	public static final int i_ty = 3;


	public static final Class<?> childTypes[] = {common.StringCatter.class,core.NLocation.class,common.StringCatter.class,silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_childDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NDclInfo.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PchildDcl(final Object c_sg, final Object c_sl, final Object c_fn, final Object c_ty) {
		super();
		this.child_sg = c_sg;
		this.child_sl = c_sl;
		this.child_fn = c_fn;
		this.child_ty = c_ty;

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



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sg: return getChild_sg();
			case i_sl: return getChild_sl();
			case i_fn: return getChild_fn();
			case i_ty: return getChild_ty();

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

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:childDcl erroneously claimed to forward");
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
		return "silver:definition:env:childDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.sourceGrammar = sg
		silver.definition.env.PchildDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.env.PchildDcl.i_sg)); } };
		// top.sourceLocation = sl
		silver.definition.env.PchildDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_sourceLocation__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.env.PchildDcl.i_sl).undecorate(); } };
		// top.fullName = fn
		silver.definition.env.PchildDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.env.PchildDcl.i_fn)); } };
		// top.unparse = error("Internal compiler error: locally scoped declaration that should never appear in interface files")
		silver.definition.env.PchildDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Perror.invoke((new common.StringCatter("Internal compiler error: locally scoped declaration that should never appear in interface files")))); } };
		// top.typerep = ty
		silver.definition.env.PchildDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.env.PchildDcl.i_ty).undecorate(); } };

	}

	public static final common.NodeFactory<PchildDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PchildDcl> {

		@Override
		public PchildDcl invoke(final Object[] children, final Object[] annotations) {
			return new PchildDcl(children[0], children[1], children[2], children[3]);
		}
	};

}
