
package core;

public final class PtoIntSafe extends common.FunctionNode {

	public static final int i_str = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__core_toIntSafe;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtoIntSafe(final Object c_str) {
		this.child_str = c_str;

	}

	private Object child_str;
	public final common.StringCatter getChild_str() {
		return (common.StringCatter) (child_str = common.Util.demand(child_str));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_str: return getChild_str();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_str: return child_str;

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
		return "core:toIntSafe";
	}

	public static core.NMaybe invoke(final Object c_str) {
		try {
		return (core.NMaybe)common.Util.safetoInt(((common.StringCatter)common.Util.demand(c_str)).toString());

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:toIntSafe", t);
		}
	}

	public static final common.NodeFactory<core.NMaybe> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NMaybe> {
		@Override
		public core.NMaybe invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoIntSafe.invoke(children[0]);
		}
	};
}