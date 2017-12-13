
package silver.util.raw.treeset;

public final class Padd extends common.FunctionNode {

	public static final int i_lst = 0;
	public static final int i_set = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_raw_treeset_add;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Padd(final Object c_lst, final Object c_set) {
		this.child_lst = c_lst;
		this.child_set = c_set;

	}

	private Object child_lst;
	public final common.ConsCell getChild_lst() {
		return (common.ConsCell) (child_lst = common.Util.demand(child_lst));
	}

	private Object child_set;
	public final Object getChild_set() {
		return (Object) (child_set = common.Util.demand(child_set));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lst: return getChild_lst();
			case i_set: return getChild_set();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lst: return child_lst;
			case i_set: return child_set;

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
		return "silver:util:raw:treeset:add";
	}

	public static Object invoke(final Object c_lst, final Object c_set) {
		try {
		return (Object)common.rawlib.RawTreeSet.addList(((common.ConsCell)common.Util.demand(c_lst)), (java.util.TreeSet<Object>)((Object)common.Util.demand(c_set)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:raw:treeset:add", t);
		}
	}

	public static final common.NodeFactory<Object> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Object> {
		@Override
		public Object invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Padd.invoke(children[0], children[1]);
		}
	};
}