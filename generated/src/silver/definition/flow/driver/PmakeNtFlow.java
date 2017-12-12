
package silver.definition.flow.driver;

public final class PmakeNtFlow extends common.FunctionNode {

	public static final int i_nt = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_makeNtFlow;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_e] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PmakeNtFlow(final Object c_nt, final Object c_e) {
		this.child_nt = c_nt;
		this.child_e = c_e;

	}

	private Object child_nt;
	public final common.StringCatter getChild_nt() {
		return (common.StringCatter) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_e;
	public final core.NPair getChild_e() {
		return (core.NPair) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nt: return getChild_nt();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nt: return child_nt;
			case i_e: return child_e;

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
		return "silver:definition:flow:driver:makeNtFlow";
	}

	public static common.StringCatter invoke(final Object c_nt, final Object c_e) {
		try {
		final common.DecoratedNode context = new PmakeNtFlow(c_nt, c_e).decorate();
		//"\"" ++ nt ++ "/" ++ e.fst ++ "\" -> \"" ++ nt ++ "/" ++ e.snd ++ "\";\n"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.driver.PmakeNtFlow.i_nt)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.driver.PmakeNtFlow.i_e).synthesized(core.Init.core_fst__ON__core_Pair)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("\" -> \"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.flow.driver.PmakeNtFlow.i_nt)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter("/")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.flow.driver.PmakeNtFlow.i_e).synthesized(core.Init.core_snd__ON__core_Pair)), (common.StringCatter)(new common.StringCatter("\";\n")))))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:makeNtFlow", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeNtFlow.invoke(children[0], children[1]);
		}
	};
}