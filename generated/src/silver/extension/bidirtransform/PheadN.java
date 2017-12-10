
package silver.extension.bidirtransform;

public final class PheadN extends common.FunctionNode {

	public static final int i_input = 0;
	public static final int i_n = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Integer.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_headN;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PheadN(final Object c_input, final Object c_n) {
		this.child_input = c_input;
		this.child_n = c_n;

	}

	private Object child_input;
	public final common.ConsCell getChild_input() {
		return (common.ConsCell) (child_input = common.Util.demand(child_input));
	}

	private Object child_n;
	public final Integer getChild_n() {
		return (Integer) (child_n = common.Util.demand(child_n));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_input: return getChild_input();
			case i_n: return getChild_n();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_input: return child_input;
			case i_n: return child_n;

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
		return "silver:extension:bidirtransform:headN";
	}

	public static common.ConsCell invoke(final Object c_input, final Object c_n) {
		try {
		final common.DecoratedNode context = new PheadN(c_input, c_n).decorate();
		//if n == 0 then [] else if null(input) then [] else [ head(input) ] ++ headN(tail(input), n + 1)
		return (common.ConsCell)((((Integer)context.childAsIs(silver.extension.bidirtransform.PheadN.i_n)).equals(Integer.valueOf((int)0)) ? ((common.ConsCell)core.Pnil.invoke()) : (((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PheadN.i_input))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)core.Phead.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PheadN.i_input))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.extension.bidirtransform.PheadN.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.bidirtransform.PheadN.i_input))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.extension.bidirtransform.PheadN.i_n)) + Integer.valueOf((int)1)); } })); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:bidirtransform:headN", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PheadN.invoke(children[0], children[1]);
		}
	};
}