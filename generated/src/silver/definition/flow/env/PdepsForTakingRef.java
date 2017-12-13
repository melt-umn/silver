
package silver.definition.flow.env;

public final class PdepsForTakingRef extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_nt = 1;
	public static final int i_flowEnv = 2;


	public static final Class<?> childTypes[] = { silver.definition.flow.ast.NVertexType.class,common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_env_depsForTakingRef;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_f] = new common.Lazy[silver.definition.flow.ast.NVertexType.num_inh_attrs];

	}

	public PdepsForTakingRef(final Object c_f, final Object c_nt, final Object c_flowEnv) {
		this.child_f = c_f;
		this.child_nt = c_nt;
		this.child_flowEnv = c_flowEnv;

	}

	private Object child_f;
	public final silver.definition.flow.ast.NVertexType getChild_f() {
		return (silver.definition.flow.ast.NVertexType) (child_f = common.Util.demand(child_f));
	}

	private Object child_nt;
	public final common.StringCatter getChild_nt() {
		return (common.StringCatter) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_flowEnv;
	public final common.DecoratedNode getChild_flowEnv() {
		return (common.DecoratedNode) (child_flowEnv = common.Util.demand(child_flowEnv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_nt: return getChild_nt();
			case i_flowEnv: return getChild_flowEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_nt: return child_nt;
			case i_flowEnv: return child_flowEnv;

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
		return "silver:definition:flow:env:depsForTakingRef";
	}

	public static common.ConsCell invoke(final Object c_f, final Object c_nt, final Object c_flowEnv) {
		try {
		final common.DecoratedNode context = new PdepsForTakingRef(c_f, c_nt, c_flowEnv).decorate();
		//map(f.inhVertex, inhsForTakingRef(nt, flowEnv))
		return (common.ConsCell)(((common.ConsCell)core.Pmap.invoke(context.childDecoratedSynthesizedLazy(silver.definition.flow.env.PdepsForTakingRef.i_f, silver.definition.flow.ast.Init.silver_definition_flow_ast_inhVertex__ON__silver_definition_flow_ast_VertexType), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.flow.env.PinhsForTakingRef.invoke(context.childAsIsLazy(silver.definition.flow.env.PdepsForTakingRef.i_nt), context.childAsIsLazy(silver.definition.flow.env.PdepsForTakingRef.i_flowEnv))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:env:depsForTakingRef", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PdepsForTakingRef.invoke(children[0], children[1], children[2]);
		}
	};
}