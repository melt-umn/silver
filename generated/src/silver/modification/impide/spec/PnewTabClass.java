
package silver.modification.impide.spec;

public final class PnewTabClass extends common.FunctionNode {

	public static final int i_tab = 0;


	public static final Class<?> childTypes[] = { common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_newTabClass;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PnewTabClass(final Object c_tab) {
		this.child_tab = c_tab;

	}

	private Object child_tab;
	public final common.StringCatter getChild_tab() {
		return (common.StringCatter) (child_tab = common.Util.demand(child_tab));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tab: return getChild_tab();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tab: return child_tab;

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
		return "silver:modification:impide:spec:newTabClass";
	}

	public static common.StringCatter invoke(final Object c_tab) {
		try {
		final common.DecoratedNode context = new PnewTabClass(c_tab).decorate();
		//"new " ++ tab ++ "()"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("new ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.modification.impide.spec.PnewTabClass.i_tab)), (common.StringCatter)(new common.StringCatter("()")))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:spec:newTabClass", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnewTabClass.invoke(children[0]);
		}
	};
}