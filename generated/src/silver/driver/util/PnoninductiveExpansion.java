
package silver.driver.util;

public final class PnoninductiveExpansion extends common.FunctionNode {

	public static final int i_initial = 0;
	public static final int i_rules = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_noninductiveExpansion;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PnoninductiveExpansion(final Object c_initial, final Object c_rules) {
		this.child_initial = c_initial;
		this.child_rules = c_rules;

	}

	private Object child_initial;
	public final common.ConsCell getChild_initial() {
		return (common.ConsCell) (child_initial = common.Util.demand(child_initial));
	}

	private Object child_rules;
	public final common.ConsCell getChild_rules() {
		return (common.ConsCell) (child_rules = common.Util.demand(child_rules));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_initial: return getChild_initial();
			case i_rules: return getChild_rules();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_initial: return child_initial;
			case i_rules: return child_rules;

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
		return "silver:driver:util:noninductiveExpansion";
	}

	public static common.ConsCell invoke(final Object c_initial, final Object c_rules) {
		try {
		final common.DecoratedNode context = new PnoninductiveExpansion(c_initial, c_rules).decorate();
		//if null(rules) then [] else if containsAny(tail(head(rules)), initial) && ! contains(head(head(rules)), initial) then (head(head(rules)) :: noninductiveExpansion(initial, tail(rules))) else noninductiveExpansion(initial, tail(rules))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.util.PnoninductiveExpansion.i_rules))) ? ((common.ConsCell)core.Pnil.invoke()) : ((((Boolean)silver.util.PcontainsAny.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PnoninductiveExpansion.i_rules))); } })); } }, context.childAsIsLazy(silver.driver.util.PnoninductiveExpansion.i_initial))) && (!((Boolean)silver.util.Pcontains.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PnoninductiveExpansion.i_rules))); } })); } }, context.childAsIsLazy(silver.driver.util.PnoninductiveExpansion.i_initial))))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PnoninductiveExpansion.i_rules))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PnoninductiveExpansion.invoke(context.childAsIsLazy(silver.driver.util.PnoninductiveExpansion.i_initial), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.util.PnoninductiveExpansion.i_rules))); } })); } })) : ((common.ConsCell)silver.driver.util.PnoninductiveExpansion.invoke(context.childAsIsLazy(silver.driver.util.PnoninductiveExpansion.i_initial), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.util.PnoninductiveExpansion.i_rules))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:noninductiveExpansion", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PnoninductiveExpansion.invoke(children[0], children[1]);
		}
	};
}