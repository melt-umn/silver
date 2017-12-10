
package silver.definition.flow.driver;

public final class PtoFlatEdges extends common.FunctionNode {

	public static final int i_x = 0;


	public static final Class<?> childTypes[] = { core.NPair.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_flow_driver_toFlatEdges;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_x] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PtoFlatEdges(final Object c_x) {
		this.child_x = c_x;

	}

	private Object child_x;
	public final core.NPair getChild_x() {
		return (core.NPair) (child_x = common.Util.demand(child_x));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_x: return getChild_x();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_x: return child_x;

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
		return "silver:definition:flow:driver:toFlatEdges";
	}

	public static common.ConsCell invoke(final Object c_x) {
		try {
		final common.DecoratedNode context = new PtoFlatEdges(c_x).decorate();
		//map(pair(x.fst, _), x.snd)
		return (common.ConsCell)(((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return core.Ppair.factory.invokePartial(new int[]{0}, new Object[]{context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PtoFlatEdges.i_x, core.Init.core_fst__ON__core_Pair)}); } }, context.childDecoratedSynthesizedLazy(silver.definition.flow.driver.PtoFlatEdges.i_x, core.Init.core_snd__ON__core_Pair))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:flow:driver:toFlatEdges", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtoFlatEdges.invoke(children[0]);
		}
	};
}