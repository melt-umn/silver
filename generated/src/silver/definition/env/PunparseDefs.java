
package silver.definition.env;

public final class PunparseDefs extends common.FunctionNode {

	public static final int i_d = 0;
	public static final int i_bv = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_unparseDefs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PunparseDefs(final Object c_d, final Object c_bv) {
		this.child_d = c_d;
		this.child_bv = c_bv;

	}

	private Object child_d;
	public final common.ConsCell getChild_d() {
		return (common.ConsCell) (child_d = common.Util.demand(child_d));
	}

	private Object child_bv;
	public final common.ConsCell getChild_bv() {
		return (common.ConsCell) (child_bv = common.Util.demand(child_bv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_d: return getChild_d();
			case i_bv: return getChild_bv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_d: return child_d;
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
		return "silver:definition:env:unparseDefs";
	}

	public static common.StringCatter invoke(final Object c_d, final Object c_bv) {
		try {
		final common.DecoratedNode context = new PunparseDefs(c_d, c_bv).decorate();
		//"[\n " ++ implode(",\n ", mapUnparseDcls(map((.dcl), d), bv)) ++ "]"
		return (common.StringCatter)(new common.StringCatter((common.StringCatter)(new common.StringCatter("[\n ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)core.Pimplode.invoke((new common.StringCatter(",\n ")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PmapUnparseDcls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.AttributeSection.Undecorated(silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_Def, context), context.childAsIsLazy(silver.definition.env.PunparseDefs.i_d))); } }, context.childAsIsLazy(silver.definition.env.PunparseDefs.i_bv))); } })), (common.StringCatter)(new common.StringCatter("]")))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:unparseDefs", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunparseDefs.invoke(children[0], children[1]);
		}
	};
}