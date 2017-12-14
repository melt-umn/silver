
package silver.extension.treegen;

public final class PgenIds extends common.FunctionNode {

	public static final int i_side = 0;
	public static final int i_index = 1;
	public static final int i_total = 2;
	public static final int i_l = 3;


	public static final Class<?> childTypes[] = { common.StringCatter.class,Integer.class,Integer.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_genIds;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PgenIds(final Object c_side, final Object c_index, final Object c_total, final Object c_l) {
		this.child_side = c_side;
		this.child_index = c_index;
		this.child_total = c_total;
		this.child_l = c_l;

	}

	private Object child_side;
	public final common.StringCatter getChild_side() {
		return (common.StringCatter) (child_side = common.Util.demand(child_side));
	}

	private Object child_index;
	public final Integer getChild_index() {
		return (Integer) (child_index = common.Util.demand(child_index));
	}

	private Object child_total;
	public final Integer getChild_total() {
		return (Integer) (child_total = common.Util.demand(child_total));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_side: return getChild_side();
			case i_index: return getChild_index();
			case i_total: return getChild_total();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_side: return child_side;
			case i_index: return child_index;
			case i_total: return child_total;
			case i_l: return child_l;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return "silver:extension:treegen:genIds";
	}

	public static common.ConsCell invoke(final Object c_side, final Object c_index, final Object c_total, final Object c_l) {
		try {
		final common.DecoratedNode context = new PgenIds(c_side, c_index, c_total, c_l).decorate();
		//if index == total then [] else (name(side ++ toString(index), l) :: genIds(side, index + 1, total, l))
		return (common.ConsCell)((((Integer)context.childAsIs(silver.extension.treegen.PgenIds.i_index)).equals(((Integer)context.childAsIs(silver.extension.treegen.PgenIds.i_total))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)silver.definition.core.Pname.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.extension.treegen.PgenIds.i_side)), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childAsIs(silver.extension.treegen.PgenIds.i_index))))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenIds.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.treegen.PgenIds.invoke(context.childAsIsLazy(silver.extension.treegen.PgenIds.i_side), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.extension.treegen.PgenIds.i_index)) + Integer.valueOf((int)1)); } }, context.childAsIsLazy(silver.extension.treegen.PgenIds.i_total), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenIds.i_l)))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:genIds", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgenIds.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}