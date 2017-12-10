
package silver.testing;

public final class PrepeatTestTimes extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_times = 1;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_repeatTestTimes;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PrepeatTestTimes(final Object c_f, final Object c_times) {
		this.child_f = c_f;
		this.child_times = c_times;

	}

	private Object child_f;
	public final common.NodeFactory<Boolean> getChild_f() {
		return (common.NodeFactory<Boolean>) (child_f = common.Util.demand(child_f));
	}

	private Object child_times;
	public final Integer getChild_times() {
		return (Integer) (child_times = common.Util.demand(child_times));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_times: return getChild_times();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_times: return child_times;

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
		return "silver:testing:repeatTestTimes";
	}

	public static Boolean invoke(final Object c_f, final Object c_times) {
		try {
		final common.DecoratedNode context = new PrepeatTestTimes(c_f, c_times).decorate();
		//if times <= 0 then true else f(,) && repeatTestTimes(f, times - 1,)
		return (Boolean)(((((Integer)context.childAsIs(silver.testing.PrepeatTestTimes.i_times)) <= Integer.valueOf((int)0)) ? true : (((Boolean)((common.NodeFactory<Boolean>)context.childAsIs(silver.testing.PrepeatTestTimes.i_f)).invoke(new Object[]{}, null)) && ((Boolean)silver.testing.PrepeatTestTimes.invoke(context.childAsIsLazy(silver.testing.PrepeatTestTimes.i_f), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.testing.PrepeatTestTimes.i_times)) - Integer.valueOf((int)1)); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:testing:repeatTestTimes", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PrepeatTestTimes.invoke(children[0], children[1]);
		}
	};
}