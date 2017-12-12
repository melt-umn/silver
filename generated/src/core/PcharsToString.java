
package core;

public final class PcharsToString extends common.FunctionNode {

	public static final int i_chars = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__core_charsToString;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PcharsToString(final Object c_chars) {
		this.child_chars = c_chars;

	}

	private Object child_chars;
	public final common.ConsCell getChild_chars() {
		return (common.ConsCell) (child_chars = common.Util.demand(child_chars));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_chars: return getChild_chars();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_chars: return child_chars;

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
		return "core:charsToString";
	}

	public static common.StringCatter invoke(final Object c_chars) {
		try {
		return (common.StringCatter)common.StringCatter.fromChars(((common.ConsCell)common.Util.demand(c_chars)));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function core:charsToString", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PcharsToString.invoke(children[0]);
		}
	};
}