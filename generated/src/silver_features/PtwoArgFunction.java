
package silver_features;

public final class PtwoArgFunction extends common.FunctionNode {

	public static final int i_s = 0;
	public static final int i_i = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_features_twoArgFunction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtwoArgFunction(final Object c_s, final Object c_i) {
		this.child_s = c_s;
		this.child_i = c_i;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}

	private Object child_i;
	public final Integer getChild_i() {
		return (Integer) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
			case i_i: return child_i;

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
		return "silver_features:twoArgFunction";
	}

	public static Integer invoke(final Object c_s, final Object c_i) {
		try {
		final common.DecoratedNode context = new PtwoArgFunction(c_s, c_i).decorate();
		//i
		return (Integer)(((Integer)context.childAsIs(silver_features.PtwoArgFunction.i_i)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver_features:twoArgFunction", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtwoArgFunction.invoke(children[0], children[1]);
		}
	};
}