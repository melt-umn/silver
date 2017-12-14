
package core;

public final class PlastIndexOf extends common.FunctionNode {

	public static final int i_needle = 0;
	public static final int i_haystack = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__core_lastIndexOf;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PlastIndexOf(final Object c_needle, final Object c_haystack) {
		this.child_needle = c_needle;
		this.child_haystack = c_haystack;

	}

	private Object child_needle;
	public final common.StringCatter getChild_needle() {
		return (common.StringCatter) (child_needle = common.Util.demand(child_needle));
	}

	private Object child_haystack;
	public final common.StringCatter getChild_haystack() {
		return (common.StringCatter) (child_haystack = common.Util.demand(child_haystack));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_needle: return getChild_needle();
			case i_haystack: return getChild_haystack();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_needle: return child_needle;
			case i_haystack: return child_haystack;

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
		return "core:lastIndexOf";
	}

	public static Integer invoke(final Object c_needle, final Object c_haystack) {
		try {
		return (Integer)Integer.valueOf(((common.StringCatter)common.Util.demand(c_haystack)).toString().lastIndexOf(((common.StringCatter)common.Util.demand(c_needle)).toString()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:lastIndexOf", t);
		}
	}

	public static final common.NodeFactory<Integer> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Integer> {
		@Override
		public Integer invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PlastIndexOf.invoke(children[0], children[1]);
		}
	};
}