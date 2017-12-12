
package silver.definition.env;

public final class PaliasedChildDef extends common.FunctionNode {

	public static final int i_sg = 0;
	public static final int i_sl = 1;
	public static final int i_fn = 2;
	public static final int i_ty = 3;
	public static final int i_alias = 4;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NLocation.class,common.StringCatter.class,silver.definition.type.NType.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_aliasedChildDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PaliasedChildDef(final Object c_sg, final Object c_sl, final Object c_fn, final Object c_ty, final Object c_alias) {
		this.child_sg = c_sg;
		this.child_sl = c_sl;
		this.child_fn = c_fn;
		this.child_ty = c_ty;
		this.child_alias = c_alias;

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

	private Object child_alias;
	public final common.StringCatter getChild_alias() {
		return (common.StringCatter) (child_alias = common.Util.demand(child_alias));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sg: return getChild_sg();
			case i_sl: return getChild_sl();
			case i_fn: return getChild_fn();
			case i_ty: return getChild_ty();
			case i_alias: return getChild_alias();

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
			case i_alias: return child_alias;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return "silver:definition:env:aliasedChildDef";
	}

	public static silver.definition.env.NDef invoke(final Object c_sg, final Object c_sl, final Object c_fn, final Object c_ty, final Object c_alias) {
		try {
		final common.DecoratedNode context = new PaliasedChildDef(c_sg, c_sl, c_fn, c_ty, c_alias).decorate();
		//valueDef(onlyRenamedEnvItem(alias, childDcl(sg, sl, fn, ty)))
		return (silver.definition.env.NDef)(((silver.definition.env.NDef)new silver.definition.env.PvalueDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NEnvItem)new silver.definition.env.PonlyRenamedEnvItem(context.childAsIsLazy(silver.definition.env.PaliasedChildDef.i_alias), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)new silver.definition.env.PchildDcl(context.childAsIsLazy(silver.definition.env.PaliasedChildDef.i_sg), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PaliasedChildDef.i_sl)), context.childAsIsLazy(silver.definition.env.PaliasedChildDef.i_fn), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PaliasedChildDef.i_ty)))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:aliasedChildDef", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NDef> {
		@Override
		public silver.definition.env.NDef invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaliasedChildDef.invoke(children[0], children[1], children[2], children[3], children[4]);
		}
	};
}