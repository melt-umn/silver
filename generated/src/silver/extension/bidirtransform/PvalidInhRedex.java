
package silver.extension.bidirtransform;

public final class PvalidInhRedex extends common.FunctionNode {

	public static final int i_test = 0;
	public static final int i_def = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_validInhRedex;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PvalidInhRedex(final Object c_test, final Object c_def) {
		this.child_test = c_test;
		this.child_def = c_def;

	}

	private Object child_test;
	public final common.StringCatter getChild_test() {
		return (common.StringCatter) (child_test = common.Util.demand(child_test));
	}

	private Object child_def;
	public final common.StringCatter getChild_def() {
		return (common.StringCatter) (child_def = common.Util.demand(child_def));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_test: return getChild_test();
			case i_def: return getChild_def();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_test: return child_test;
			case i_def: return child_def;

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
		return "silver:extension:bidirtransform:validInhRedex";
	}

	public static common.StringCatter invoke(final Object c_test, final Object c_def) {
		try {
		final common.DecoratedNode context = new PvalidInhRedex(c_test, c_def).decorate();
		//if test == "Integer" then def else if test == "String" then def else test
		return (common.StringCatter)((((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PvalidInhRedex.i_test)).equals((new common.StringCatter("Integer"))) ? ((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PvalidInhRedex.i_def)) : (((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PvalidInhRedex.i_test)).equals((new common.StringCatter("String"))) ? ((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PvalidInhRedex.i_def)) : ((common.StringCatter)context.childAsIs(silver.extension.bidirtransform.PvalidInhRedex.i_test)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:validInhRedex", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PvalidInhRedex.invoke(children[0], children[1]);
		}
	};
}