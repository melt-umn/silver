
package silver.analysis.warnings.defs;

public final class PisEquationMissing extends common.FunctionNode {

	public static final int i_f = 0;
	public static final int i_attr = 1;


	public static final Class<?> childTypes[] = { common.NodeFactory.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_analysis_warnings_defs_isEquationMissing;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PisEquationMissing(final Object c_f, final Object c_attr) {
		this.child_f = c_f;
		this.child_attr = c_attr;

	}

	private Object child_f;
	public final common.NodeFactory<common.ConsCell> getChild_f() {
		return (common.NodeFactory<common.ConsCell>) (child_f = common.Util.demand(child_f));
	}

	private Object child_attr;
	public final common.StringCatter getChild_attr() {
		return (common.StringCatter) (child_attr = common.Util.demand(child_attr));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_attr: return getChild_attr();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_attr: return child_attr;

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
		return "silver:analysis:warnings:defs:isEquationMissing";
	}

	public static Boolean invoke(final Object c_f, final Object c_attr) {
		try {
		final common.DecoratedNode context = new PisEquationMissing(c_f, c_attr).decorate();
		//null(f(attr))
		return (Boolean)(((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.NodeFactory<common.ConsCell>)context.childAsIs(silver.analysis.warnings.defs.PisEquationMissing.i_f)).invoke(new Object[]{context.childAsIsLazy(silver.analysis.warnings.defs.PisEquationMissing.i_attr)}, null)); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:analysis:warnings:defs:isEquationMissing", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PisEquationMissing.invoke(children[0], children[1]);
		}
	};
}