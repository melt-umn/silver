
package silver.definition.env;

public final class PunparseTyVars extends common.FunctionNode {

	public static final int i_utvs = 0;
	public static final int i_bv = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_unparseTyVars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PunparseTyVars(final Object c_utvs, final Object c_bv) {
		this.child_utvs = c_utvs;
		this.child_bv = c_bv;

	}

	private Object child_utvs;
	public final common.ConsCell getChild_utvs() {
		return (common.ConsCell) (child_utvs = common.Util.demand(child_utvs));
	}

	private Object child_bv;
	public final common.ConsCell getChild_bv() {
		return (common.ConsCell) (child_bv = common.Util.demand(child_bv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_utvs: return getChild_utvs();
			case i_bv: return getChild_bv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_utvs: return child_utvs;
			case i_bv: return child_bv;

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
		return "silver:definition:env:unparseTyVars";
	}

	public static common.StringCatter invoke(final Object c_utvs, final Object c_bv) {
		try {
		final common.DecoratedNode context = new PunparseTyVars(c_utvs, c_bv).decorate();
		//"[" ++ implode(", ", mapUnparseTyVars(utvs, bv)) ++ "]"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("[")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(", ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PmapUnparseTyVars.invoke(context.childAsIsLazy(silver.definition.env.PunparseTyVars.i_utvs), context.childAsIsLazy(silver.definition.env.PunparseTyVars.i_bv))); } })), (common.StringCatter)(new common.StringCatter("]")))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:unparseTyVars", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunparseTyVars.invoke(children[0], children[1]);
		}
	};
}