
package silver.modification.impide.spec;

public final class PboolToString extends common.FunctionNode {

	public static final int i_b = 0;


	public static final Class<?> childTypes[] = { Boolean.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_boolToString;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PboolToString(final Object c_b) {
		this.child_b = c_b;

	}

	private Object child_b;
	public final Boolean getChild_b() {
		return (Boolean) (child_b = common.Util.demand(child_b));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_b: return getChild_b();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_b: return child_b;

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
		return "silver:modification:impide:spec:boolToString";
	}

	public static common.StringCatter invoke(final Object c_b) {
		try {
		final common.DecoratedNode context = new PboolToString(c_b).decorate();
		//if b then "true" else "false"
		return (common.StringCatter)((((Boolean)context.childAsIs(silver.modification.impide.spec.PboolToString.i_b)) ? (new common.StringCatter("true")) : (new common.StringCatter("false"))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:spec:boolToString", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PboolToString.invoke(children[0]);
		}
	};
}