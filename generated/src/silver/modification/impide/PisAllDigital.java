
package silver.modification.impide;

public final class PisAllDigital extends common.FunctionNode {

	public static final int i_parts = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_isAllDigital;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PisAllDigital(final Object c_parts) {
		this.child_parts = c_parts;

	}

	private Object child_parts;
	public final common.ConsCell getChild_parts() {
		return (common.ConsCell) (child_parts = common.Util.demand(child_parts));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_parts: return getChild_parts();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_parts: return child_parts;

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
		return "silver:modification:impide:isAllDigital";
	}

	public static Boolean invoke(final Object c_parts) {
		try {
		final common.DecoratedNode context = new PisAllDigital(c_parts).decorate();
		//null(parts) || isDigit(head(parts)) && isAllDigital(tail(parts))
		return (Boolean)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.modification.impide.PisAllDigital.i_parts))) || (((Boolean)core.PisDigit.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.modification.impide.PisAllDigital.i_parts))); } })) && ((Boolean)silver.modification.impide.PisAllDigital.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.modification.impide.PisAllDigital.i_parts))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:isAllDigital", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisAllDigital.invoke(children[0]);
		}
	};
}