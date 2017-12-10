
package stdlib;

public final class Psub extends common.FunctionNode {

	public static final int i_x = 0;
	public static final int i_y = 1;


	public static final Class<?> childTypes[] = { Integer.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__stdlib_sub;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Psub(final Object c_x, final Object c_y) {
		this.child_x = c_x;
		this.child_y = c_y;

	}

	private Object child_x;
	public final Integer getChild_x() {
		return (Integer) (child_x = common.Util.demand(child_x));
	}

	private Object child_y;
	public final Integer getChild_y() {
		return (Integer) (child_y = common.Util.demand(child_y));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_x: return getChild_x();
			case i_y: return getChild_y();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_x: return child_x;
			case i_y: return child_y;

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
		return "stdlib:sub";
	}

	public static Integer invoke(final Object c_x, final Object c_y) {
		try {
		final common.DecoratedNode context = new Psub(c_x, c_y).decorate();
		//x - y
		return (Integer)(Integer.valueOf(((Integer)context.childAsIs(stdlib.Psub.i_x)) - ((Integer)context.childAsIs(stdlib.Psub.i_y))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function stdlib:sub", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Psub.invoke(children[0], children[1]);
		}
	};
}