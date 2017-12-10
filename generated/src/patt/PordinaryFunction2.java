
package patt;

public final class PordinaryFunction2 extends common.FunctionNode {

	public static final int i_undecoratedFirst = 0;
	public static final int i_attrSecond = 1;


	public static final Class<?> childTypes[] = { patt.NOrdinaryNonterminal.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_ordinaryFunction2;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_undecoratedFirst] = new common.Lazy[patt.NOrdinaryNonterminal.num_inh_attrs];

	}

	public PordinaryFunction2(final Object c_undecoratedFirst, final Object c_attrSecond) {
		this.child_undecoratedFirst = c_undecoratedFirst;
		this.child_attrSecond = c_attrSecond;

	}

	private Object child_undecoratedFirst;
	public final patt.NOrdinaryNonterminal getChild_undecoratedFirst() {
		return (patt.NOrdinaryNonterminal) (child_undecoratedFirst = common.Util.demand(child_undecoratedFirst));
	}

	private Object child_attrSecond;
	public final common.StringCatter getChild_attrSecond() {
		return (common.StringCatter) (child_attrSecond = common.Util.demand(child_attrSecond));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_undecoratedFirst: return getChild_undecoratedFirst();
			case i_attrSecond: return getChild_attrSecond();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_undecoratedFirst: return child_undecoratedFirst;
			case i_attrSecond: return child_attrSecond;

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
		return "patt:ordinaryFunction2";
	}

	public static common.StringCatter invoke(final Object c_undecoratedFirst, final Object c_attrSecond) {
		try {
		final common.DecoratedNode context = new PordinaryFunction2(c_undecoratedFirst, c_attrSecond).decorate();
		//""
		return (common.StringCatter)((new common.StringCatter("")));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:ordinaryFunction2", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PordinaryFunction2.invoke(children[0], children[1]);
		}
	};
}