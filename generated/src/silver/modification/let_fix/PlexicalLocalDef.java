
package silver.modification.let_fix;

public final class PlexicalLocalDef extends common.FunctionNode {

	public static final int i_sg = 0;
	public static final int i_sl = 1;
	public static final int i_fn = 2;
	public static final int i_ty = 3;
	public static final int i_fi = 4;
	public static final int i_fd = 5;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NLocation.class,common.StringCatter.class,silver.definition.type.NType.class,silver.definition.flow.ast.NExprVertexInfo.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_let_fix_lexicalLocalDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_ty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_fi] = new common.Lazy[silver.definition.flow.ast.NExprVertexInfo.num_inh_attrs];

	}

	public PlexicalLocalDef(final Object c_sg, final Object c_sl, final Object c_fn, final Object c_ty, final Object c_fi, final Object c_fd) {
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
		return "silver:modification:let_fix:lexicalLocalDef";
	}

	public static silver.definition.env.NDef invoke(final Object c_sg, final Object c_sl, final Object c_fn, final Object c_ty, final Object c_fi, final Object c_fd) {
		try {
		final common.DecoratedNode context = new PlexicalLocalDef(c_sg, c_sl, c_fn, c_ty, c_fi, c_fd).decorate();
		//valueDef(defaultEnvItem(lexicalLocalDcl(sg, sl, fn, ty, fi, fd)))
		return (silver.definition.env.NDef)(((silver.definition.env.NDef)new silver.definition.env.PvalueDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NEnvItem)silver.definition.env.PdefaultEnvItem.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)new silver.modification.let_fix.PlexicalLocalDcl(context.childAsIsLazy(silver.modification.let_fix.PlexicalLocalDef.i_sg), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.let_fix.PlexicalLocalDef.i_sl)), context.childAsIsLazy(silver.modification.let_fix.PlexicalLocalDef.i_fn), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.let_fix.PlexicalLocalDef.i_ty)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.let_fix.PlexicalLocalDef.i_fi)), context.childAsIsLazy(silver.modification.let_fix.PlexicalLocalDef.i_fd))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:let_fix:lexicalLocalDef", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NDef> {
		@Override
		public silver.definition.env.NDef invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PlexicalLocalDef.invoke(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};
}