
package silver.definition.flow.driver;

public final class PmakeLabelDcls extends common.FunctionNode {

	public static final int i_nt = 0;
	public static final int i_attr = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_makeLabelDcls;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmakeLabelDcls(final Object c_nt, final Object c_attr) {
		this.child_nt = c_nt;
		this.child_attr = c_attr;

	}

	private Object child_nt;
	public final common.StringCatter getChild_nt() {
		return (common.StringCatter) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_attr;
	public final common.StringCatter getChild_attr() {
		return (common.StringCatter) (child_attr = common.Util.demand(child_attr));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nt: return getChild_nt();
			case i_attr: return getChild_attr();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nt: return child_nt;
			case i_attr: return child_attr;

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
		return "silver:definition:flow:driver:makeLabelDcls";
	}

	public static common.StringCatter invoke(final Object c_nt, final Object c_attr) {
		try {
		final common.DecoratedNode context = new PmakeLabelDcls(c_nt, c_attr).decorate();
		//"\"" ++ nt ++ "/" ++ attr ++ "\"[label=\"" ++ a ++ "\"];\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.driver.PmakeLabelDcls.i_nt)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.driver.PmakeLabelDcls.i_attr)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\"[label=\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.localAsIs(silver.definition.flow.driver.Init.a__ON__silver_definition_flow_driver_makeLabelDcls)), (common.StringCatter)(new common.StringCatter("\"];\n")))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:makeLabelDcls", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeLabelDcls.invoke(children[0], children[1]);
		}
	};
}