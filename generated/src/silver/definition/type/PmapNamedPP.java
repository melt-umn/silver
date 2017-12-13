
package silver.definition.type;

public final class PmapNamedPP extends common.FunctionNode {

	public static final int i_tes = 0;
	public static final int i_bv = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_mapNamedPP;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PmapNamedPP(final Object c_tes, final Object c_bv) {
		this.child_tes = c_tes;
		this.child_bv = c_bv;

	}

	private Object child_tes;
	public final common.ConsCell getChild_tes() {
		return (common.ConsCell) (child_tes = common.Util.demand(child_tes));
	}

	private Object child_bv;
	public final common.ConsCell getChild_bv() {
		return (common.ConsCell) (child_bv = common.Util.demand(child_bv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tes: return getChild_tes();
			case i_bv: return getChild_bv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tes: return child_tes;
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
		return "silver:definition:type:mapNamedPP";
	}

	public static common.StringCatter invoke(final Object c_tes, final Object c_bv) {
		try {
		final common.DecoratedNode context = new PmapNamedPP(c_tes, c_bv).decorate();
		//if null(tes) then "" else fst.typepp ++ mapNamedPP(tail(tes), bv)
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.type.PmapNamedPP.i_tes))) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.definition.type.Init.fst__ON__silver_definition_type_mapNamedPP).synthesized(silver.definition.type.Init.silver_definition_type_typepp__ON__silver_definition_type_NamedArgType)), (common.StringCatter)((common.StringCatter)silver.definition.type.PmapNamedPP.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.type.PmapNamedPP.i_tes))); } }, context.childAsIsLazy(silver.definition.type.PmapNamedPP.i_bv))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:mapNamedPP", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmapNamedPP.invoke(children[0], children[1]);
		}
	};
}