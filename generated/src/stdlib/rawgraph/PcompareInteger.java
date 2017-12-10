
package stdlib.rawgraph;

public final class PcompareInteger extends common.FunctionNode {

	public static final int i_a = 0;
	public static final int i_b = 1;


	public static final Class<?> childTypes[] = { Integer.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__stdlib_rawgraph_compareInteger;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcompareInteger(final Object c_a, final Object c_b) {
		this.child_a = c_a;
		this.child_b = c_b;

	}

	private Object child_a;
	public final Integer getChild_a() {
		return (Integer) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final Integer getChild_b() {
		return (Integer) (child_b = common.Util.demand(child_b));
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
		return "stdlib:rawgraph:compareInteger";
	}

	public static Integer invoke(final Object c_a, final Object c_b) {
		try {
		final common.DecoratedNode context = new PcompareInteger(c_a, c_b).decorate();
		//a - b
		return (Integer)(Integer.valueOf(((Integer)context.childAsIs(stdlib.rawgraph.PcompareInteger.i_a)) - ((Integer)context.childAsIs(stdlib.rawgraph.PcompareInteger.i_b))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function stdlib:rawgraph:compareInteger", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcompareInteger.invoke(children[0], children[1]);
		}
	};
}