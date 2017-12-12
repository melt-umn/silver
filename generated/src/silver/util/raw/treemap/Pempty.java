
package silver.util.raw.treemap;

public final class Pempty extends common.FunctionNode {

	public static final int i_comparator = 0;


	public static final Class<?> childTypes[] = { common.NodeFactory.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_raw_treemap_empty;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pempty(final Object c_comparator) {
		this.child_comparator = c_comparator;

	}

	private Object child_comparator;
	public final common.NodeFactory<Integer> getChild_comparator() {
		return (common.NodeFactory<Integer>) (child_comparator = common.Util.demand(child_comparator));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_comparator: return getChild_comparator();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_comparator: return child_comparator;

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
		return "silver:util:raw:treemap:empty";
	}

	public static Object invoke(final Object c_comparator) {
		try {
		return (Object)common.rawlib.RawTreeMap.empty(((common.NodeFactory<Integer>)common.Util.demand(c_comparator)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:raw:treemap:empty", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pempty.invoke(children[0]);
		}
	};
}