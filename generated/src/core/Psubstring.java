
package core;

public final class Psubstring extends common.FunctionNode {

	public static final int i_start = 0;
	public static final int i_endl = 1;
	public static final int i_str = 2;


	public static final Class<?> childTypes[] = { Integer.class,Integer.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__core_substring;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Psubstring(final Object c_start, final Object c_endl, final Object c_str) {
		this.child_start = c_start;
		this.child_endl = c_endl;
		this.child_str = c_str;

	}

	private Object child_start;
	public final Integer getChild_start() {
		return (Integer) (child_start = common.Util.demand(child_start));
	}

	private Object child_endl;
	public final Integer getChild_endl() {
		return (Integer) (child_endl = common.Util.demand(child_endl));
	}

	private Object child_str;
	public final common.StringCatter getChild_str() {
		return (common.StringCatter) (child_str = common.Util.demand(child_str));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_start: return getChild_start();
			case i_endl: return getChild_endl();
			case i_str: return getChild_str();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_start: return child_start;
			case i_endl: return child_endl;
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
		return "core:substring";
	}

	public static common.StringCatter invoke(final Object c_start, final Object c_endl, final Object c_str) {
		try {
		return (common.StringCatter)(new common.StringCatter(((common.StringCatter)common.Util.demand(c_str)).toString().substring(((Integer)common.Util.demand(c_start)), ((Integer)common.Util.demand(c_endl)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:substring", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Psubstring.invoke(children[0], children[1], children[2]);
		}
	};
}