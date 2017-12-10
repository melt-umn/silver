
package silver.definition.env;

public final class PunparseSignatureElementsHelp extends common.FunctionNode {

	public static final int i_s = 0;
	public static final int i_bv = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_unparseSignatureElementsHelp;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PunparseSignatureElementsHelp(final Object c_s, final Object c_bv) {
		this.child_s = c_s;
		this.child_bv = c_bv;

	}

	private Object child_s;
	public final common.ConsCell getChild_s() {
		return (common.ConsCell) (child_s = common.Util.demand(child_s));
	}

	private Object child_bv;
	public final common.ConsCell getChild_bv() {
		return (common.ConsCell) (child_bv = common.Util.demand(child_bv));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_bv: return getChild_bv();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
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
		return "silver:definition:env:unparseSignatureElementsHelp";
	}

	public static common.StringCatter invoke(final Object c_s, final Object c_bv) {
		try {
		final common.DecoratedNode context = new PunparseSignatureElementsHelp(c_s, c_bv).decorate();
		//if null(s) then "" else h.unparse ++ (if null(tail(s)) then "" else (", " ++ unparseSignatureElementsHelp(tail(s), bv)))
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.env.PunparseSignatureElementsHelp.i_s))) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)((common.StringCatter)context.localDecorated(silver.definition.env.Init.h__ON__silver_definition_env_unparseSignatureElementsHelp).synthesized(silver.definition.env.Init.silver_definition_env_unparse__ON__silver_definition_env_NamedSignatureElement)), (common.StringCatter)(((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.env.PunparseSignatureElementsHelp.i_s))); } })) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)(new common.StringCatter(", ")), (common.StringCatter)((common.StringCatter)silver.definition.env.PunparseSignatureElementsHelp.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.env.PunparseSignatureElementsHelp.i_s))); } }, context.childAsIsLazy(silver.definition.env.PunparseSignatureElementsHelp.i_bv))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:unparseSignatureElementsHelp", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PunparseSignatureElementsHelp.invoke(children[0], children[1]);
		}
	};
}