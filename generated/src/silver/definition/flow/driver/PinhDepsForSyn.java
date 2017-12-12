
package silver.definition.flow.driver;

public final class PinhDepsForSyn extends common.FunctionNode {

	public static final int i_syn = 0;
	public static final int i_nt = 1;
	public static final int i_flow = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_inhDepsForSyn;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PinhDepsForSyn(final Object c_syn, final Object c_nt, final Object c_flow) {
		this.child_syn = c_syn;
		this.child_nt = c_nt;
		this.child_flow = c_flow;

	}

	private Object child_syn;
	public final common.StringCatter getChild_syn() {
		return (common.StringCatter) (child_syn = common.Util.demand(child_syn));
	}

	private Object child_nt;
	public final common.StringCatter getChild_nt() {
		return (common.StringCatter) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_flow;
	public final Object getChild_flow() {
		return (Object) (child_flow = common.Util.demand(child_flow));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_syn: return getChild_syn();
			case i_nt: return getChild_nt();
			case i_flow: return getChild_flow();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_syn: return child_syn;
			case i_nt: return child_nt;
			case i_flow: return child_flow;

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
		return "silver:definition:flow:driver:inhDepsForSyn";
	}

	public static Object invoke(final Object c_syn, final Object c_nt, final Object c_flow) {
		try {
		final common.DecoratedNode context = new PinhDepsForSyn(c_syn, c_nt, c_flow).decorate();
		//g:edgesFrom(syn, findFlowType(nt, flow))
		return (Object)(((Object)silver.util.raw.graph.PedgesFrom.invoke(context.childAsIsLazy(silver.definition.flow.driver.PinhDepsForSyn.i_syn), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)silver.definition.flow.driver.PfindFlowType.invoke(context.childAsIsLazy(silver.definition.flow.driver.PinhDepsForSyn.i_nt), context.childAsIsLazy(silver.definition.flow.driver.PinhDepsForSyn.i_flow))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:inhDepsForSyn", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PinhDepsForSyn.invoke(children[0], children[1], children[2]);
		}
	};
}