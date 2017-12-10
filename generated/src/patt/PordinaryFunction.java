
package patt;

public final class PordinaryFunction extends common.FunctionNode {

	public static final int i_undecoratedFirst = 0;
	public static final int i_decoratedSecond = 1;


	public static final Class<?> childTypes[] = { patt.NOrdinaryNonterminal.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_ordinaryFunction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_undecoratedFirst] = new common.Lazy[patt.NOrdinaryNonterminal.num_inh_attrs];

	}

	public PordinaryFunction(final Object c_undecoratedFirst, final Object c_decoratedSecond) {
		this.child_undecoratedFirst = c_undecoratedFirst;
		this.child_decoratedSecond = c_decoratedSecond;

	}

	private Object child_undecoratedFirst;
	public final patt.NOrdinaryNonterminal getChild_undecoratedFirst() {
		return (patt.NOrdinaryNonterminal) (child_undecoratedFirst = common.Util.demand(child_undecoratedFirst));
	}

	private Object child_decoratedSecond;
	public final common.DecoratedNode getChild_decoratedSecond() {
		return (common.DecoratedNode) (child_decoratedSecond = common.Util.demand(child_decoratedSecond));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_undecoratedFirst: return getChild_undecoratedFirst();
			case i_decoratedSecond: return getChild_decoratedSecond();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_undecoratedFirst: return child_undecoratedFirst;
			case i_decoratedSecond: return child_decoratedSecond;

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
		return "patt:ordinaryFunction";
	}

	public static common.StringCatter invoke(final Object c_undecoratedFirst, final Object c_decoratedSecond) {
		try {
		final common.DecoratedNode context = new PordinaryFunction(c_undecoratedFirst, c_decoratedSecond).decorate();
		//""
		return (common.StringCatter)((new common.StringCatter("")));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:ordinaryFunction", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PordinaryFunction.invoke(children[0], children[1]);
		}
	};
}