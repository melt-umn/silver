
package silver.definition.core;

public final class PreorderedAnnoAppExprs extends common.FunctionNode {

	public static final int i_d = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_reorderedAnnoAppExprs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PreorderedAnnoAppExprs(final Object c_d) {
		this.child_d = c_d;

	}

	private Object child_d;
	public final common.DecoratedNode getChild_d() {
		return (common.DecoratedNode) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;

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
		return "silver:definition:core:reorderedAnnoAppExprs";
	}

	public static common.ConsCell invoke(final Object c_d) {
		try {
		final common.DecoratedNode context = new PreorderedAnnoAppExprs(c_d).decorate();
		//map(reorderedGetSnd, sortBy(reorderedLte, zipWith(pair, d.annoIndexSupplied, d.exprs)))
		return (common.ConsCell)(((common.ConsCell)core.Pmap.invoke(silver.definition.core.PreorderedGetSnd.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PsortBy.invoke(silver.definition.core.PreorderedLte.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PzipWith.invoke(core.Ppair.factory, context.childAsIsSynthesizedLazy(silver.definition.core.PreorderedAnnoAppExprs.i_d, silver.definition.core.Init.silver_definition_core_annoIndexSupplied__ON__silver_definition_core_AnnoAppExprs), context.childAsIsSynthesizedLazy(silver.definition.core.PreorderedAnnoAppExprs.i_d, silver.definition.core.Init.silver_definition_core_exprs__ON__silver_definition_core_AnnoAppExprs))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:reorderedAnnoAppExprs", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PreorderedAnnoAppExprs.invoke(children[0]);
		}
	};
}