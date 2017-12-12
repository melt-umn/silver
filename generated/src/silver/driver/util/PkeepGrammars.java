
package silver.driver.util;

public final class PkeepGrammars extends common.FunctionNode {

	public static final int i_keep = 0;
	public static final int i_d = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_util_keepGrammars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PkeepGrammars(final Object c_keep, final Object c_d) {
		this.child_keep = c_keep;
		this.child_d = c_d;

	}

	private Object child_keep;
	public final common.ConsCell getChild_keep() {
		return (common.ConsCell) (child_keep = common.Util.demand(child_keep));
	}

	private Object child_d;
	public final common.ConsCell getChild_d() {
		return (common.ConsCell) (child_d = common.Util.demand(child_d));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_keep: return getChild_keep();
			case i_d: return getChild_d();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_keep: return child_keep;
			case i_d: return child_d;

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
		return "silver:driver:util:keepGrammars";
	}

	public static common.ConsCell invoke(final Object c_keep, final Object c_d) {
		try {
		final common.DecoratedNode context = new PkeepGrammars(c_keep, c_d).decorate();
		//if null(d) then [] else (if contains(head(d).declaredName, keep) then [ head(d) ] else []) ++ keepGrammars(keep, tail(d))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.util.PkeepGrammars.i_d))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return (((Boolean)silver.util.Pcontains.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PkeepGrammars.i_d))).synthesized(silver.driver.util.Init.silver_definition_env_declaredName__ON__silver_driver_util_RootSpec)); } }, context.childAsIsLazy(silver.driver.util.PkeepGrammars.i_keep))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.driver.util.PkeepGrammars.i_d))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((common.ConsCell)core.Pnil.invoke())); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.util.PkeepGrammars.invoke(context.childAsIsLazy(silver.driver.util.PkeepGrammars.i_keep), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.util.PkeepGrammars.i_d))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:util:keepGrammars", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PkeepGrammars.invoke(children[0], children[1]);
		}
	};
}