
package silver.definition.env;

public final class PannoInstanceDef extends common.FunctionNode {

	public static final int i_sg = 0;
	public static final int i_sl = 1;
	public static final int i_fnnt = 2;
	public static final int i_fnat = 3;
	public static final int i_ntty = 4;
	public static final int i_atty = 5;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NLocation.class,common.StringCatter.class,common.StringCatter.class,silver.definition.type.NType.class,silver.definition.type.NType.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_annoInstanceDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_ntty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];
	childInheritedAttributes[i_atty] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PannoInstanceDef(final Object c_sg, final Object c_sl, final Object c_fnnt, final Object c_fnat, final Object c_ntty, final Object c_atty) {
		this.child_sg = c_sg;
		this.child_sl = c_sl;
		this.child_fnnt = c_fnnt;
		this.child_fnat = c_fnat;
		this.child_ntty = c_ntty;
		this.child_atty = c_atty;

	}

	private Object child_sg;
	public final common.StringCatter getChild_sg() {
		return (common.StringCatter) (child_sg = common.Util.demand(child_sg));
	}

	private Object child_sl;
	public final core.NLocation getChild_sl() {
		return (core.NLocation) (child_sl = common.Util.demand(child_sl));
	}

	private Object child_fnnt;
	public final common.StringCatter getChild_fnnt() {
		return (common.StringCatter) (child_fnnt = common.Util.demand(child_fnnt));
	}

	private Object child_fnat;
	public final common.StringCatter getChild_fnat() {
		return (common.StringCatter) (child_fnat = common.Util.demand(child_fnat));
	}

	private Object child_ntty;
	public final silver.definition.type.NType getChild_ntty() {
		return (silver.definition.type.NType) (child_ntty = common.Util.demand(child_ntty));
	}

	private Object child_atty;
	public final silver.definition.type.NType getChild_atty() {
		return (silver.definition.type.NType) (child_atty = common.Util.demand(child_atty));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sg: return getChild_sg();
			case i_sl: return getChild_sl();
			case i_fnnt: return getChild_fnnt();
			case i_fnat: return getChild_fnat();
			case i_ntty: return getChild_ntty();
			case i_atty: return getChild_atty();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sg: return child_sg;
			case i_sl: return child_sl;
			case i_fnnt: return child_fnnt;
			case i_fnat: return child_fnat;
			case i_ntty: return child_ntty;
			case i_atty: return child_atty;

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
		return "silver:definition:env:annoInstanceDef";
	}

	public static silver.definition.env.NDef invoke(final Object c_sg, final Object c_sl, final Object c_fnnt, final Object c_fnat, final Object c_ntty, final Object c_atty) {
		try {
		final common.DecoratedNode context = new PannoInstanceDef(c_sg, c_sl, c_fnnt, c_fnat, c_ntty, c_atty).decorate();
		//oDef(annoInstanceDcl(sg, sl, fnnt, fnat, ntty, atty))
		return (silver.definition.env.NDef)(((silver.definition.env.NDef)new silver.definition.env.PoDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)new silver.definition.env.PannoInstanceDcl(context.childAsIsLazy(silver.definition.env.PannoInstanceDef.i_sg), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PannoInstanceDef.i_sl)), context.childAsIsLazy(silver.definition.env.PannoInstanceDef.i_fnnt), context.childAsIsLazy(silver.definition.env.PannoInstanceDef.i_fnat), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PannoInstanceDef.i_ntty)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PannoInstanceDef.i_atty)))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:annoInstanceDef", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NDef> {
		@Override
		public silver.definition.env.NDef invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PannoInstanceDef.invoke(children[0], children[1], children[2], children[3], children[4], children[5]);
		}
	};
}