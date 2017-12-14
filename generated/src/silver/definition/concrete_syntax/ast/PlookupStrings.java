
package silver.definition.concrete_syntax.ast;

public final class PlookupStrings extends common.FunctionNode {

	public static final int i_t = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,Object.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_concrete_syntax_ast_lookupStrings;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PlookupStrings(final Object c_t, final Object c_e) {
		this.child_t = c_t;
		this.child_e = c_e;

	}

	private Object child_t;
	public final common.ConsCell getChild_t() {
		return (common.ConsCell) (child_t = common.Util.demand(child_t));
	}

	private Object child_e;
	public final Object getChild_e() {
		return (Object) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i_e: return child_e;

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
		return "silver:definition:concrete_syntax:ast:lookupStrings";
	}

	public static common.ConsCell invoke(final Object c_t, final Object c_e) {
		try {
		final common.DecoratedNode context = new PlookupStrings(c_t, c_e).decorate();
		//map(searchEnvTree(_, e), t)
		return (common.ConsCell)(((common.ConsCell)core.Pmap.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return silver.definition.env.PsearchEnvTree.factory.invokePartial(new int[]{1}, new Object[]{context.childAsIsLazy(silver.definition.concrete_syntax.ast.PlookupStrings.i_e)}); } }, context.childAsIsLazy(silver.definition.concrete_syntax.ast.PlookupStrings.i_t))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:concrete_syntax:ast:lookupStrings", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PlookupStrings.invoke(children[0], children[1]);
		}
	};
}