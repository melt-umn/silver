
package silver.definition.flow.env;

public final class PisAffectable extends common.FunctionNode {

	public static final int i_prodgram = 0;
	public static final int i_ntgram = 1;
	public static final int i_cg = 2;
	public static final int i_d = 3;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,Object.class,silver.definition.env.NDclInfo.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_isAffectable;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_d] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public PisAffectable(final Object c_prodgram, final Object c_ntgram, final Object c_cg, final Object c_d) {
		this.child_prodgram = c_prodgram;
		this.child_ntgram = c_ntgram;
		this.child_cg = c_cg;
		this.child_d = c_d;

	}

	private Object child_prodgram;
	public final common.StringCatter getChild_prodgram() {
		return (common.StringCatter) (child_prodgram = common.Util.demand(child_prodgram));
	}

	private Object child_ntgram;
	public final common.StringCatter getChild_ntgram() {
		return (common.StringCatter) (child_ntgram = common.Util.demand(child_ntgram));
	}

	private Object child_cg;
	public final Object getChild_cg() {
		return (Object) (child_cg = common.Util.demand(child_cg));
	}

	private Object child_d;
	public final silver.definition.env.NDclInfo getChild_d() {
		return (silver.definition.env.NDclInfo) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prodgram: return getChild_prodgram();
			case i_ntgram: return getChild_ntgram();
			case i_cg: return getChild_cg();
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prodgram: return child_prodgram;
			case i_ntgram: return child_ntgram;
			case i_cg: return child_cg;
			case i_d: return child_d;

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
		return "silver:definition:flow:env:isAffectable";
	}

	public static Boolean invoke(final Object c_prodgram, final Object c_ntgram, final Object c_cg, final Object c_d) {
		try {
		final common.DecoratedNode context = new PisAffectable(c_prodgram, c_ntgram, c_cg, c_d).decorate();
		//isExportedBy(prodgram, [ ntgram, d.sourceGrammar ], cg)
		return (Boolean)(((Boolean)silver.driver.util.PisExportedBy.invoke(context.childAsIsLazy(silver.definition.flow.env.PisAffectable.i_prodgram), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(silver.definition.flow.env.PisAffectable.i_ntgram), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.env.PisAffectable.i_d, silver.definition.env.Init.silver_definition_env_sourceGrammar__ON__silver_definition_env_DclInfo), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } }, context.childAsIsLazy(silver.definition.flow.env.PisAffectable.i_cg))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:env:isAffectable", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisAffectable.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}