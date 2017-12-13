
package silver.definition.flow.driver;

public final class PgetInhsForNtForPatternVars extends common.FunctionNode {

	public static final int i_nt = 0;
	public static final int i_realEnv = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_getInhsForNtForPatternVars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgetInhsForNtForPatternVars(final Object c_nt, final Object c_realEnv) {
		this.child_nt = c_nt;
		this.child_realEnv = c_realEnv;

	}

	private Object child_nt;
	public final common.StringCatter getChild_nt() {
		return (common.StringCatter) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_realEnv;
	public final common.DecoratedNode getChild_realEnv() {
		return (common.DecoratedNode) (child_realEnv = common.Util.demand(child_realEnv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nt: return getChild_nt();
			case i_realEnv: return getChild_realEnv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nt: return child_nt;
			case i_realEnv: return child_realEnv;

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
		return "silver:definition:flow:driver:getInhsForNtForPatternVars";
	}

	public static common.ConsCell invoke(final Object c_nt, final Object c_realEnv) {
		try {
		final common.DecoratedNode context = new PgetInhsForNtForPatternVars(c_nt, c_realEnv).decorate();
		//map((.attrOccurring), filter(isOccursInherited(_, realEnv), getAttrsOn(nt, realEnv)))
		return (common.ConsCell)(((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_attrOccurring__ON__silver_definition_env_DclInfo, context), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pfilter.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.flow.driver.PisOccursInherited.factory.invokePartial(new int[]{1}, new Object[]{context.childAsIsLazy(silver.definition.flow.driver.PgetInhsForNtForPatternVars.i_realEnv)}); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PgetAttrsOn.invoke(context.childAsIsLazy(silver.definition.flow.driver.PgetInhsForNtForPatternVars.i_nt), context.childAsIsLazy(silver.definition.flow.driver.PgetInhsForNtForPatternVars.i_realEnv))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:getInhsForNtForPatternVars", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetInhsForNtForPatternVars.invoke(children[0], children[1]);
		}
	};
}