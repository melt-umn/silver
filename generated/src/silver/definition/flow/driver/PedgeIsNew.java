
package silver.definition.flow.driver;

public final class PedgeIsNew extends common.FunctionNode {

	public static final int i_edge = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { core.NPair.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_edgeIsNew;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_edge] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PedgeIsNew(final Object c_edge, final Object c_e) {
		this.child_edge = c_edge;
		this.child_e = c_e;

	}

	private Object child_edge;
	public final core.NPair getChild_edge() {
		return (core.NPair) (child_edge = common.Util.demand(child_edge));
	}

	private Object child_e;
	public final Object getChild_e() {
		return (Object) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_edge: return getChild_edge();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_edge: return child_edge;
			case i_e: return child_e;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:definition:flow:driver:edgeIsNew";
	}

	public static Boolean invoke(final Object c_edge, final Object c_e) {
		try {
		final common.DecoratedNode context = new PedgeIsNew(c_edge, c_e).decorate();
		//! g:contains(edge, e)
		return (Boolean)((!((Boolean)silver.util.raw.graph.Pcontains.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.flow.driver.PedgeIsNew.i_edge)), context.childAsIsLazy(silver.definition.flow.driver.PedgeIsNew.i_e)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:edgeIsNew", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PedgeIsNew.invoke(children[0], children[1]);
		}
	};
}