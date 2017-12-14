
package silver.modification.copper;

public final class PpluckTermDef extends common.FunctionNode {

	public static final int i_sg = 0;
	public static final int i_sl = 1;
	public static final int i_fn = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NLocation.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_pluckTermDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PpluckTermDef(final Object c_sg, final Object c_sl, final Object c_fn) {
		this.child_sg = c_sg;
		this.child_sl = c_sl;
		this.child_fn = c_fn;

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



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sg: return getChild_sg();
			case i_sl: return getChild_sl();
			case i_fn: return getChild_fn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sg: return child_sg;
			case i_sl: return child_sl;
			case i_fn: return child_fn;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:modification:copper:pluckTermDef";
	}

	public static silver.definition.env.NDef invoke(final Object c_sg, final Object c_sl, final Object c_fn) {
		try {
		final common.DecoratedNode context = new PpluckTermDef(c_sg, c_sl, c_fn).decorate();
		//valueDef(defaultEnvItem(pluckTermDcl(sg, sl, fn)))
		return (silver.definition.env.NDef)(((silver.definition.env.NDef)new silver.definition.env.PvalueDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NEnvItem)silver.definition.env.PdefaultEnvItem.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)new silver.modification.copper.PpluckTermDcl(context.childAsIsLazy(silver.modification.copper.PpluckTermDef.i_sg), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PpluckTermDef.i_sl)), context.childAsIsLazy(silver.modification.copper.PpluckTermDef.i_fn))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:copper:pluckTermDef", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NDef> {
		@Override
		public silver.definition.env.NDef invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PpluckTermDef.invoke(children[0], children[1], children[2]);
		}
	};
}