
package patt;

public final class PbetaContrived extends common.FunctionNode {

	public static final int i_e = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__patt_betaContrived;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PbetaContrived(final Object c_e) {
		this.child_e = c_e;

	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_e: return child_e;

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
		return "patt:betaContrived";
	}

	public static patt.NLCE invoke(final Object c_e) {
		try {
		final common.DecoratedNode context = new PbetaContrived(c_e).decorate();
		//beta(new(e),)
		return (patt.NLCE)(((patt.NLCE)patt.Pbeta.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((patt.NLCE)((common.DecoratedNode)context.childAsIs(patt.PbetaContrived.i_e)).undecorate()); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function patt:betaContrived", t);
		}
	}

	public static final common.NodeFactory<patt.NLCE> factory = new Factory();

	public static final class Factory extends common.NodeFactory<patt.NLCE> {
		@Override
		public patt.NLCE invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PbetaContrived.invoke(children[0]);
		}
	};
}