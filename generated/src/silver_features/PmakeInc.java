
package silver_features;

public final class PmakeInc extends common.FunctionNode {

	public static final int i_i = 0;


	public static final Class<?> childTypes[] = { Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_features_makeInc;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmakeInc(final Object c_i) {
		this.child_i = c_i;

	}

	private Object child_i;
	public final Integer getChild_i() {
		return (Integer) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;

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
		return "silver_features:makeInc";
	}

	public static common.NodeFactory<Integer> invoke(final Object c_i) {
		try {
		final common.DecoratedNode context = new PmakeInc(c_i).decorate();
		//\ x::Integer  -> x + i
		return (common.NodeFactory<Integer>)((new common.NodeFactory<Integer>() {
  public final Integer invoke(final Object[] args, final Object[] namedArgs) {
    final Object __SV_LAMBDA_PARAM_150_x = args[0];

    return Integer.valueOf(((Integer)common.Util.demand(__SV_LAMBDA_PARAM_150_x)) + ((Integer)context.childAsIs(silver_features.PmakeInc.i_i)));
  }
}));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver_features:makeInc", t);
		}
	}

	public static final common.NodeFactory<common.NodeFactory<Integer>> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.NodeFactory<Integer>> {
		@Override
		public common.NodeFactory<Integer> invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeInc.invoke(children[0]);
		}
	};
}