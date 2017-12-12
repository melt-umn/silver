
package silver.driver;

public final class PgrammarContainsErrors extends common.FunctionNode {

	public static final int i_es = 0;
	public static final int i_werr = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Boolean.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_driver_grammarContainsErrors;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgrammarContainsErrors(final Object c_es, final Object c_werr) {
		this.child_es = c_es;
		this.child_werr = c_werr;

	}

	private Object child_es;
	public final common.ConsCell getChild_es() {
		return (common.ConsCell) (child_es = common.Util.demand(child_es));
	}

	private Object child_werr;
	public final Boolean getChild_werr() {
		return (Boolean) (child_werr = common.Util.demand(child_werr));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_es: return getChild_es();
			case i_werr: return getChild_werr();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_es: return child_es;
			case i_werr: return child_werr;

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
		return "silver:driver:grammarContainsErrors";
	}

	public static Boolean invoke(final Object c_es, final Object c_werr) {
		try {
		final common.DecoratedNode context = new PgrammarContainsErrors(c_es, c_werr).decorate();
		//if null(es) then false else containsErrors(head(es).snd, werr) || grammarContainsErrors(tail(es), werr)
		return (Boolean)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.driver.PgrammarContainsErrors.i_es))) ? false : (((Boolean)silver.definition.core.PcontainsErrors.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((core.NPair)core.Phead.invoke(context.childAsIsLazy(silver.driver.PgrammarContainsErrors.i_es))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_snd__ON__core_Pair)); } }, context.childAsIsLazy(silver.driver.PgrammarContainsErrors.i_werr))) || ((Boolean)silver.driver.PgrammarContainsErrors.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.driver.PgrammarContainsErrors.i_es))); } }, context.childAsIsLazy(silver.driver.PgrammarContainsErrors.i_werr))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:driver:grammarContainsErrors", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgrammarContainsErrors.invoke(children[0], children[1]);
		}
	};
}