
package silver.definition.env;

// top::DclInfo ::= sg::String sl::Location fn::String ty::Type 
public final class PlocalDcl extends silver.definition.env.NDclInfo {

	public static final int i_sg = 0;
	public static final int i_sl = 1;
	public static final int i_fn = 2;
	public static final int i_ty = 3;


	public static final Class<?> childTypes[] = {common.StringCatter.class,core.NLocation.class,common.StringCatter.class,silver.definition.type.NType.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_localDcl;
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

	public PlocalDcl(final Object c_sg, final Object c_sl, final Object c_fn, final Object c_ty) {
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
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:localDcl erroneously claimed to forward");
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
		return "silver:definition:env:localDcl";
	}

	static void initProductionAttributeDefinitions() {
		// top.sourceGrammar = sg
		silver.definition.env.PlocalDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.env.PlocalDcl.i_sg)); } };
		// top.sourceLocation = sl
		silver.definition.env.PlocalDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_sourceLocation__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.env.PlocalDcl.i_sl).undecorate(); } };
		// top.fullName = fn
		silver.definition.env.PlocalDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_fullName__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.env.PlocalDcl.i_fn)); } };
		// ty.boundVariables = top.boundVariables
		silver.definition.env.PlocalDcl.childInheritedAttributes[silver.definition.env.PlocalDcl.i_ty][silver.definition.type.Init.silver_definition_type_boundVariables__ON__silver_definition_type_Type] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.inherited(silver.definition.env.Init.silver_definition_type_boundVariables__ON__silver_definition_env_DclInfo)); } };
		// top.unparse = "loc(" ++ sl.unparse ++ ", '" ++ fn ++ "', " ++ ty.unparse ++ ")"
		silver.definition.env.PlocalDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("loc(")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PlocalDcl.i_sl).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__core_Location)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(", '")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PlocalDcl.i_fn)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("', ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PlocalDcl.i_ty).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_type_Type)), (common.StringCatter)(new common.StringCatter(")")))))))); } };
		// top.typerep = ty
		silver.definition.env.PlocalDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_typerep__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.env.PlocalDcl.i_ty).undecorate(); } };
		// top.substitutedDclInfo = localDcl(sg, sl, fn, performSubstitution(ty, top.givenSubstitution))
		silver.definition.env.PlocalDcl.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_substitutedDclInfo__ON__silver_definition_env_DclInfo] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)new silver.definition.env.PlocalDcl(context.childAsIsLazy(silver.definition.env.PlocalDcl.i_sg), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PlocalDcl.i_sl)), context.childAsIsLazy(silver.definition.env.PlocalDcl.i_fn), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)silver.definition.type.PperformSubstitution.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PlocalDcl.i_ty)), context.contextInheritedLazy(silver.definition.env.Init.silver_definition_env_givenSubstitution__ON__silver_definition_env_DclInfo))); } })); } };

	}

	public static final common.NodeFactory<PlocalDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PlocalDcl> {

		@Override
		public PlocalDcl invoke(final Object[] children, final Object[] annotations) {
			return new PlocalDcl(children[0], children[1], children[2], children[3]);
		}
	};

}
