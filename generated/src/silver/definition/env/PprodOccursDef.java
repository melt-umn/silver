
package silver.definition.env;

public final class PprodOccursDef extends common.FunctionNode {

	public static final int i_sg = 0;
	public static final int i_sl = 1;
	public static final int i_ns = 2;
	public static final int i_dcls = 3;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NLocation.class,silver.definition.env.NNamedSignature.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_prodOccursDef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_ns] = new common.Lazy[silver.definition.env.NNamedSignature.num_inh_attrs];

	}

	public PprodOccursDef(final Object c_sg, final Object c_sl, final Object c_ns, final Object c_dcls) {
		this.child_sg = c_sg;
		this.child_sl = c_sl;
		this.child_ns = c_ns;
		this.child_dcls = c_dcls;

	}

	private Object child_sg;
	public final common.StringCatter getChild_sg() {
		return (common.StringCatter) (child_sg = common.Util.demand(child_sg));
	}

	private Object child_sl;
	public final core.NLocation getChild_sl() {
		return (core.NLocation) (child_sl = common.Util.demand(child_sl));
	}

	private Object child_ns;
	public final silver.definition.env.NNamedSignature getChild_ns() {
		return (silver.definition.env.NNamedSignature) (child_ns = common.Util.demand(child_ns));
	}

	private Object child_dcls;
	public final common.ConsCell getChild_dcls() {
		return (common.ConsCell) (child_dcls = common.Util.demand(child_dcls));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_sg: return getChild_sg();
			case i_sl: return getChild_sl();
			case i_ns: return getChild_ns();
			case i_dcls: return getChild_dcls();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_sg: return child_sg;
			case i_sl: return child_sl;
			case i_ns: return child_ns;
			case i_dcls: return child_dcls;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:definition:env:prodOccursDef";
	}

	public static silver.definition.env.NDef invoke(final Object c_sg, final Object c_sl, final Object c_ns, final Object c_dcls) {
		try {
		final common.DecoratedNode context = new PprodOccursDef(c_sg, c_sl, c_ns, c_dcls).decorate();
		//paDef(paDcl(sg, sl, ns, dcls))
		return (silver.definition.env.NDef)(((silver.definition.env.NDef)new silver.definition.env.PpaDef(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDclInfo)new silver.definition.env.PpaDcl(context.childAsIsLazy(silver.definition.env.PprodOccursDef.i_sg), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PprodOccursDef.i_sl)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PprodOccursDef.i_ns)), context.childAsIsLazy(silver.definition.env.PprodOccursDef.i_dcls))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:prodOccursDef", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NDef> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NDef> {
		@Override
		public silver.definition.env.NDef invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PprodOccursDef.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}