
package silver.definition.flow.driver;

public final class PntListEq extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_b = 1;


	public static final Class<?> childTypes[] = { core.NPair.class,core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_ntListEq;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_a] = new common.Lazy[core.NPair.num_inh_attrs];
	childInheritedAttributes[i_b] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PntListEq(final Object c_a, final Object c_b) {
		this.child_a = c_a;
		this.child_b = c_b;

	}

	private Object child_a;
	public final core.NPair getChild_a() {
		return (core.NPair) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final core.NPair getChild_b() {
		return (core.NPair) (child_b = common.Util.demand(child_b));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_b: return getChild_b();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_b: return child_b;

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
		return "silver:definition:flow:driver:ntListEq";
	}

	public static Boolean invoke(final Object c_a, final Object c_b) {
		try {
		final common.DecoratedNode context = new PntListEq(c_a, c_b).decorate();
		//a.fst == b.fst
		return (Boolean)(((common.StringCatter)context.childDecorated(silver.definition.flow.driver.PntListEq.i_a).synthesized(core.Init.core_fst__ON__core_Pair)).equals(((common.StringCatter)context.childDecorated(silver.definition.flow.driver.PntListEq.i_b).synthesized(core.Init.core_fst__ON__core_Pair))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:ntListEq", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PntListEq.invoke(children[0], children[1]);
		}
	};
}