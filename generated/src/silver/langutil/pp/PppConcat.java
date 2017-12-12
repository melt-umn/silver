
package silver.langutil.pp;

public final class PppConcat extends common.FunctionNode {

	public static final int i_ds = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_langutil_pp_ppConcat;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PppConcat(final Object c_ds) {
		this.child_ds = c_ds;

	}

	private Object child_ds;
	public final common.ConsCell getChild_ds() {
		return (common.ConsCell) (child_ds = common.Util.demand(child_ds));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ds: return getChild_ds();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ds: return child_ds;

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
		return "silver:langutil:pp:ppConcat";
	}

	public static silver.langutil.pp.NDocument invoke(final Object c_ds) {
		try {
		final common.DecoratedNode context = new PppConcat(c_ds).decorate();
		//if null(ds) then notext() else foldl(cat, head(ds), tail(ds))
		return (silver.langutil.pp.NDocument)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.langutil.pp.PppConcat.i_ds))) ? ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pnotext()) : ((silver.langutil.pp.NDocument)core.Pfoldl.invoke(silver.langutil.pp.Pcat.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)core.Phead.invoke(context.childAsIsLazy(silver.langutil.pp.PppConcat.i_ds))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.langutil.pp.PppConcat.i_ds))); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:langutil:pp:ppConcat", t);
		}
	}

	public static final common.NodeFactory<silver.langutil.pp.NDocument> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.langutil.pp.NDocument> {
		@Override
		public silver.langutil.pp.NDocument invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PppConcat.invoke(children[0]);
		}
	};
}