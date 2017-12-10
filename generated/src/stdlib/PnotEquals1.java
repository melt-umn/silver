
package stdlib;

public final class PnotEquals1 extends common.FunctionNode {

	public static final int i_x = 0;


	public static final Class<?> childTypes[] = { Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__stdlib_notEquals1;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PnotEquals1(final Object c_x) {
		this.child_x = c_x;

	}

	private Object child_x;
	public final Integer getChild_x() {
		return (Integer) (child_x = common.Util.demand(child_x));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_x: return getChild_x();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_x: return child_x;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "stdlib:notEquals1";
	}

	public static Boolean invoke(final Object c_x) {
		try {
		final common.DecoratedNode context = new PnotEquals1(c_x).decorate();
		//x != 1
		return (Boolean)(!((Integer)context.childAsIs(stdlib.PnotEquals1.i_x)).equals(Integer.valueOf((int)1)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function stdlib:notEquals1", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnotEquals1.invoke(children[0]);
		}
	};
}