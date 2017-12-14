
package silver.langutil.pp;

public final class Pgroupnestlines extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_inner = 1;


	public static final Class<?> childTypes[] = { Integer.class,silver.langutil.pp.NDocument.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_langutil_pp_groupnestlines;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_inner] = new common.Lazy[silver.langutil.pp.NDocument.num_inh_attrs];

	}

	public Pgroupnestlines(final Object c_n, final Object c_inner) {
		this.child_n = c_n;
		this.child_inner = c_inner;

	}

	private Object child_n;
	public final Integer getChild_n() {
		return (Integer) (child_n = common.Util.demand(child_n));
	}

	private Object child_inner;
	public final silver.langutil.pp.NDocument getChild_inner() {
		return (silver.langutil.pp.NDocument) (child_inner = common.Util.demand(child_inner));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_inner: return getChild_inner();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_inner: return child_inner;

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
		return "silver:langutil:pp:groupnestlines";
	}

	public static silver.langutil.pp.NDocument invoke(final Object c_n, final Object c_inner) {
		try {
		final common.DecoratedNode context = new Pgroupnestlines(c_n, c_inner).decorate();
		//cat(groupnest(n, cat(line(), inner)), line())
		return (silver.langutil.pp.NDocument)(((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)silver.langutil.pp.Pgroupnest.invoke(context.childAsIsLazy(silver.langutil.pp.Pgroupnestlines.i_n), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pcat(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pline()); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.langutil.pp.Pgroupnestlines.i_inner)))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.langutil.pp.NDocument)new silver.langutil.pp.Pline()); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:langutil:pp:groupnestlines", t);
		}
	}

	public static final common.NodeFactory<silver.langutil.pp.NDocument> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.langutil.pp.NDocument> {
		@Override
		public silver.langutil.pp.NDocument invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pgroupnestlines.invoke(children[0], children[1]);
		}
	};
}