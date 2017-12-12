
package core;

public final class Psubstitute extends common.FunctionNode {

	public static final int i_search = 0;
	public static final int i_replace = 1;
	public static final int i_str = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__core_substitute;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Psubstitute(final Object c_search, final Object c_replace, final Object c_str) {
		this.child_search = c_search;
		this.child_replace = c_replace;
		this.child_str = c_str;

	}

	private Object child_search;
	public final common.StringCatter getChild_search() {
		return (common.StringCatter) (child_search = common.Util.demand(child_search));
	}

	private Object child_replace;
	public final common.StringCatter getChild_replace() {
		return (common.StringCatter) (child_replace = common.Util.demand(child_replace));
	}

	private Object child_str;
	public final common.StringCatter getChild_str() {
		return (common.StringCatter) (child_str = common.Util.demand(child_str));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_search: return getChild_search();
			case i_replace: return getChild_replace();
			case i_str: return getChild_str();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_search: return child_search;
			case i_replace: return child_replace;
			case i_str: return child_str;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "core:substitute";
	}

	public static common.StringCatter invoke(final Object c_search, final Object c_replace, final Object c_str) {
		try {
		return (common.StringCatter)new common.StringCatter(((common.StringCatter)common.Util.demand(c_str)).toString().replace((CharSequence)((common.StringCatter)common.Util.demand(c_search)).toString(),(CharSequence)((common.StringCatter)common.Util.demand(c_replace)).toString()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:substitute", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Psubstitute.invoke(children[0], children[1], children[2]);
		}
	};
}