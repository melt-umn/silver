
package silver.definition.env;

public final class PannotationsForNonterminal extends common.FunctionNode {

	public static final int i_nt = 0;
	public static final int i_env = 1;


	public static final Class<?> childTypes[] = { silver.definition.type.NType.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_annotationsForNonterminal;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_nt] = new common.Lazy[silver.definition.type.NType.num_inh_attrs];

	}

	public PannotationsForNonterminal(final Object c_nt, final Object c_env) {
		this.child_nt = c_nt;
		this.child_env = c_env;

	}

	private Object child_nt;
	public final silver.definition.type.NType getChild_nt() {
		return (silver.definition.type.NType) (child_nt = common.Util.demand(child_nt));
	}

	private Object child_env;
	public final common.DecoratedNode getChild_env() {
		return (common.DecoratedNode) (child_env = common.Util.demand(child_env));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nt: return getChild_nt();
			case i_env: return getChild_env();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nt: return child_nt;
			case i_env: return child_env;

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
		return "silver:definition:env:annotationsForNonterminal";
	}

	public static common.ConsCell invoke(final Object c_nt, final Object c_env) {
		try {
		final common.DecoratedNode context = new PannotationsForNonterminal(c_nt, c_env).decorate();
		//sortBy(namedSignatureElementLte, map(annoInstanceToNamed(nt, _), annos))
		return (common.ConsCell)(((common.ConsCell)core.PsortBy.invoke(silver.definition.env.PnamedSignatureElementLte.factory, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PannoInstanceToNamed.factory.invokePartial(new int[]{0}, new Object[]{common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PannotationsForNonterminal.i_nt))}); } }, context.localAsIsLazy(silver.definition.env.Init.annos__ON__silver_definition_env_annotationsForNonterminal))); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:annotationsForNonterminal", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PannotationsForNonterminal.invoke(children[0], children[1]);
		}
	};
}