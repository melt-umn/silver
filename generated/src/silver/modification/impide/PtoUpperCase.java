
package silver.modification.impide;

public final class PtoUpperCase extends common.FunctionNode {

	public static final int i_original = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_toUpperCase;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtoUpperCase(final Object c_original) {
		this.child_original = c_original;

	}

	private Object child_original;
	public final common.StringCatter getChild_original() {
		return (common.StringCatter) (child_original = common.Util.demand(child_original));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_original: return getChild_original();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_original: return child_original;

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
		return "silver:modification:impide:toUpperCase";
	}

	public static common.StringCatter invoke(final Object c_original) {
		try {
		return (common.StringCatter)(new common.StringCatter(((common.StringCatter)common.Util.demand(c_original)).toString().toUpperCase()));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:toUpperCase", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoUpperCase.invoke(children[0]);
		}
	};
}