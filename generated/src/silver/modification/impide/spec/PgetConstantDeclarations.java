
package silver.modification.impide.spec;

public final class PgetConstantDeclarations extends common.FunctionNode {

	public static final int i_i = 0;
	public static final int i_fontList = 1;


	public static final Class<?> childTypes[] = { Integer.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_spec_getConstantDeclarations;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgetConstantDeclarations(final Object c_i, final Object c_fontList) {
		this.child_i = c_i;
		this.child_fontList = c_fontList;

	}

	private Object child_i;
	public final Integer getChild_i() {
		return (Integer) (child_i = common.Util.demand(child_i));
	}

	private Object child_fontList;
	public final common.ConsCell getChild_fontList() {
		return (common.ConsCell) (child_fontList = common.Util.demand(child_fontList));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();
			case i_fontList: return getChild_fontList();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;
			case i_fontList: return child_fontList;

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
		return "silver:modification:impide:spec:getConstantDeclarations";
	}

	public static common.StringCatter invoke(final Object c_i, final Object c_fontList) {
		try {
		final common.DecoratedNode context = new PgetConstantDeclarations(c_i, c_fontList).decorate();
		//if null(fontList) then "" else "\t\tpublic static final int " ++ head(fontList).fst ++ " = " ++ toString(i) ++ ";\n" ++ getConstantDeclarations(i + 1, tail(fontList))
		return (common.StringCatter)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.modification.impide.spec.PgetConstantDeclarations.i_fontList))) ? (new common.StringCatter("")) : new common.StringCatter((common.StringCatter)(new common.StringCatter("\t\tpublic static final int ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((core.NPair)core.Phead.invoke(context.childAsIsLazy(silver.modification.impide.spec.PgetConstantDeclarations.i_fontList))).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_fst__ON__core_Pair)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" = ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childAsIs(silver.modification.impide.spec.PgetConstantDeclarations.i_i)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(";\n")), (common.StringCatter)((common.StringCatter)silver.modification.impide.spec.PgetConstantDeclarations.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver.modification.impide.spec.PgetConstantDeclarations.i_i)) + Integer.valueOf((int)1)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.modification.impide.spec.PgetConstantDeclarations.i_fontList))); } })))))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:spec:getConstantDeclarations", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetConstantDeclarations.invoke(children[0], children[1]);
		}
	};
}