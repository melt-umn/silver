
package silver.definition.env;

public final class PenvItemInclude extends common.FunctionNode {

	public static final int i_ei = 0;
	public static final int i_include = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NEnvItem.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_envItemInclude;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ei] = new common.Lazy[silver.definition.env.NEnvItem.num_inh_attrs];

	}

	public PenvItemInclude(final Object c_ei, final Object c_include) {
		this.child_ei = c_ei;
		this.child_include = c_include;

	}

	private Object child_ei;
	public final silver.definition.env.NEnvItem getChild_ei() {
		return (silver.definition.env.NEnvItem) (child_ei = common.Util.demand(child_ei));
	}

	private Object child_include;
	public final common.ConsCell getChild_include() {
		return (common.ConsCell) (child_include = common.Util.demand(child_include));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ei: return getChild_ei();
			case i_include: return getChild_include();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ei: return child_ei;
			case i_include: return child_include;

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
		return "silver:definition:env:envItemInclude";
	}

	public static Boolean invoke(final Object c_ei, final Object c_include) {
		try {
		final common.DecoratedNode context = new PenvItemInclude(c_ei, c_include).decorate();
		//contains(ei.itemName, include)
		return (Boolean)(((Boolean)silver.util.Pcontains.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PenvItemInclude.i_ei, silver.definition.env.Init.silver_definition_env_itemName__ON__silver_definition_env_EnvItem), context.childAsIsLazy(silver.definition.env.PenvItemInclude.i_include))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:envItemInclude", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PenvItemInclude.invoke(children[0], children[1]);
		}
	};
}