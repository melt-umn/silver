
package core;

public final class Preplicate extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_s = 1;


	public static final Class<?> childTypes[] = { Integer.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__core_replicate;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Preplicate(final Object c_n, final Object c_s) {
		this.child_n = c_n;
		this.child_s = c_s;

	}

	private Object child_n;
	public final Integer getChild_n() {
		return (Integer) (child_n = common.Util.demand(child_n));
	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
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
		return "core:replicate";
	}

	public static common.StringCatter invoke(final Object c_n, final Object c_s) {
		try {
		return (common.StringCatter)new common.StringCatter(new String(new char[((Integer)common.Util.demand(c_n)).intValue()]).replace("\0", ((common.StringCatter)common.Util.demand(c_s)).toString()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:replicate", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Preplicate.invoke(children[0], children[1]);
		}
	};
}