
package silver.util;

public final class Pequals extends common.FunctionNode {

	public static final int i_s1 = 0;
	public static final int i_s2 = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_equals;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pequals(final Object c_s1, final Object c_s2) {
		this.child_s1 = c_s1;
		this.child_s2 = c_s2;

	}

	private Object child_s1;
	public final common.ConsCell getChild_s1() {
		return (common.ConsCell) (child_s1 = common.Util.demand(child_s1));
	}

	private Object child_s2;
	public final common.ConsCell getChild_s2() {
		return (common.ConsCell) (child_s2 = common.Util.demand(child_s2));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s1: return getChild_s1();
			case i_s2: return getChild_s2();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s1: return child_s1;
			case i_s2: return child_s2;

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
		return "silver:util:equals";
	}

	public static Boolean invoke(final Object c_s1, final Object c_s2) {
		try {
		final common.DecoratedNode context = new Pequals(c_s1, c_s2).decorate();
		//length(s1) == length(s2) && containsAll(s1, s2)
		return (Boolean)((((Integer)core.PlistLength.invoke(context.childAsIsLazy(silver.util.Pequals.i_s1))).equals(((Integer)core.PlistLength.invoke(context.childAsIsLazy(silver.util.Pequals.i_s2)))) && ((Boolean)silver.util.PcontainsAll.invoke(context.childAsIsLazy(silver.util.Pequals.i_s1), context.childAsIsLazy(silver.util.Pequals.i_s2)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:equals", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pequals.invoke(children[0], children[1]);
		}
	};
}