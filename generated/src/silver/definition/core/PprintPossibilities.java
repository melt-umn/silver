
package silver.definition.core;

public final class PprintPossibilities extends common.FunctionNode {

	public static final int i_lst = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_printPossibilities;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PprintPossibilities(final Object c_lst) {
		this.child_lst = c_lst;

	}

	private Object child_lst;
	public final common.ConsCell getChild_lst() {
		return (common.ConsCell) (child_lst = common.Util.demand(child_lst));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_lst: return getChild_lst();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_lst: return child_lst;

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
		return "silver:definition:core:printPossibilities";
	}

	public static common.StringCatter invoke(final Object c_lst) {
		try {
		final common.DecoratedNode context = new PprintPossibilities(c_lst).decorate();
		//implode("\n", map(dclinfo2possibility, lst))
		return (common.StringCatter)(((common.StringCatter)core.Pimplode.invoke((new common.StringCatter("\n")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(silver.definition.core.Pdclinfo2possibility.factory, context.childAsIsLazy(silver.definition.core.PprintPossibilities.i_lst))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:printPossibilities", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PprintPossibilities.invoke(children[0]);
		}
	};
}