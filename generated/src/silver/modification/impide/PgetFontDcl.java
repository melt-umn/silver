
package silver.modification.impide;

public final class PgetFontDcl extends common.FunctionNode {

	public static final int i_search = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_impide_getFontDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PgetFontDcl(final Object c_search, final Object c_e) {
		this.child_search = c_search;
		this.child_e = c_e;

	}

	private Object child_search;
	public final common.StringCatter getChild_search() {
		return (common.StringCatter) (child_search = common.Util.demand(child_search));
	}

	private Object child_e;
	public final common.DecoratedNode getChild_e() {
		return (common.DecoratedNode) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_search: return getChild_search();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_search: return child_search;
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
		return "silver:modification:impide:getFontDcl";
	}

	public static common.ConsCell invoke(final Object c_search, final Object c_e) {
		try {
		final common.DecoratedNode context = new PgetFontDcl(c_search, c_e).decorate();
		//searchEnvScope(search, e.fontDefTree)
		return (common.ConsCell)(((common.ConsCell)silver.definition.env.PsearchEnvScope.invoke(context.childAsIsLazy(silver.modification.impide.PgetFontDcl.i_search), context.childAsIsSynthesizedLazy(silver.modification.impide.PgetFontDcl.i_e, silver.modification.impide.Init.silver_modification_impide_fontDefTree__ON__silver_definition_env_Env))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:modification:impide:getFontDcl", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgetFontDcl.invoke(children[0], children[1]);
		}
	};
}