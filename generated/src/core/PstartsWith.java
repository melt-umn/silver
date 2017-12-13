
package core;

public final class PstartsWith extends common.FunctionNode {

	public static final int i_pre = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__core_startsWith;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PstartsWith(final Object c_pre, final Object c_s) {
		this.child_pre = c_pre;
		this.child_s = c_s;

	}

	private Object child_pre;
	public final common.StringCatter getChild_pre() {
		return (common.StringCatter) (child_pre = common.Util.demand(child_pre));
	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pre: return getChild_pre();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pre: return child_pre;
			case i_s: return child_s;

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
		return "core:startsWith";
	}

	public static Boolean invoke(final Object c_pre, final Object c_s) {
		try {
		return (Boolean)Boolean.valueOf(((common.StringCatter)common.Util.demand(c_s)).toString().startsWith(((common.StringCatter)common.Util.demand(c_pre)).toString()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:startsWith", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PstartsWith.invoke(children[0], children[1]);
		}
	};
}