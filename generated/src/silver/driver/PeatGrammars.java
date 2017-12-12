
package silver.driver;

public final class PeatGrammars extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_sofar = 1;
	public static final int i_rootStream = 2;
	public static final int i_grammars = 3;


	public static final Class<?> childTypes[] = { Integer.class,common.DecoratedNode.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_eatGrammars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PeatGrammars(final Object c_n, final Object c_sofar, final Object c_rootStream, final Object c_grammars) {
		this.child_n = c_n;
		this.child_sofar = c_sofar;
		this.child_rootStream = c_rootStream;
		this.child_grammars = c_grammars;

	}

	private Object child_n;
	public final Integer getChild_n() {
		return (Integer) (child_n = common.Util.demand(child_n));
	}

	private Object child_sofar;
	public final common.ConsCell getChild_sofar() {
		return (common.ConsCell) (child_sofar = common.Util.demand(child_sofar));
	}

	private Object child_rootStream;
	public final common.ConsCell getChild_rootStream() {
		return (common.ConsCell) (child_rootStream = common.Util.demand(child_rootStream));
	}

	private Object child_grammars;
	public final common.ConsCell getChild_grammars() {
		return (common.ConsCell) (child_grammars = common.Util.demand(child_grammars));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_sofar: return getChild_sofar();
			case i_rootStream: return getChild_rootStream();
			case i_grammars: return getChild_grammars();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_sofar: return child_sofar;
			case i_rootStream: return child_rootStream;
			case i_grammars: return child_grammars;

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
		return "silver:driver:eatGrammars";
	}

	public static common.ConsCell invoke(final Object c_n, final Object c_sofar, final Object c_rootStream, final Object c_grammars) {
		try {
		final common.DecoratedNode context = new PeatGrammars(c_n, c_sofar, c_rootStream, c_grammars).decorate();
		//if n == 0 then [] else if ! head(rootStream).isJust then eatGrammars(n - 1, sofar, tail(rootStream), grammars) else newDeps ++ eatGrammars(n - 1 + length(newDeps), newDeps ++ sofar, tail(rootStream), tail(grammars))
		return (common.ConsCell)((((Integer)context.childAsIs(silver.driver.PeatGrammars.i_n)).equals(Integer.valueOf((int)0)) ? ((common.ConsCell)core.Pnil.invoke()) : ((!((Boolean)((core.NMaybe)core.Phead.invoke(context.childAsIsLazy(silver.driver.PeatGrammars.i_rootStream))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_isJust__ON__core_Maybe))) ? ((common.ConsCell)silver.driver.PeatGrammars.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.driver.PeatGrammars.i_n)) - Integer.valueOf((int)1)); } }, context.childAsIsLazy(silver.driver.PeatGrammars.i_sofar), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.PeatGrammars.i_rootStream))); } }, context.childAsIsLazy(silver.driver.PeatGrammars.i_grammars))) : ((common.ConsCell)core.Pappend.invoke(context.localAsIsLazy(silver.driver.Init.newDeps__ON__silver_driver_eatGrammars), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.driver.PeatGrammars.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((Integer)context.childAsIs(silver.driver.PeatGrammars.i_n)) - Integer.valueOf((int)1)) + ((Integer)core.PlistLength.invoke(context.localAsIsLazy(silver.driver.Init.newDeps__ON__silver_driver_eatGrammars)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(context.localAsIsLazy(silver.driver.Init.newDeps__ON__silver_driver_eatGrammars), context.childAsIsLazy(silver.driver.PeatGrammars.i_sofar))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.PeatGrammars.i_rootStream))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.PeatGrammars.i_grammars))); } })); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:eatGrammars", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PeatGrammars.invoke(children[0], children[1], children[2], children[3]);
		}
	};
}