
package silver.definition.env;

public final class PenvItemExclude extends common.FunctionNode {

	public static final int i_ei = 0;
	public static final int i_exclude = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NEnvItem.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_envItemExclude;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ei] = new common.Lazy[silver.definition.env.NEnvItem.num_inh_attrs];

	}

	public PenvItemExclude(final Object c_ei, final Object c_exclude) {
		this.child_ei = c_ei;
		this.child_exclude = c_exclude;

	}

	private Object child_ei;
	public final silver.definition.env.NEnvItem getChild_ei() {
		return (silver.definition.env.NEnvItem) (child_ei = common.Util.demand(child_ei));
	}

	private Object child_exclude;
	public final common.ConsCell getChild_exclude() {
		return (common.ConsCell) (child_exclude = common.Util.demand(child_exclude));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ei: return getChild_ei();
			case i_exclude: return getChild_exclude();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ei: return child_ei;
			case i_exclude: return child_exclude;

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
		return "silver:definition:env:envItemExclude";
	}

	public static Boolean invoke(final Object c_ei, final Object c_exclude) {
		try {
		final common.DecoratedNode context = new PenvItemExclude(c_ei, c_exclude).decorate();
		//! contains(ei.itemName, exclude)
		return (Boolean)((!((Boolean)silver.util.Pcontains.invoke(context.childDecoratedSynthesizedLazy(silver.definition.env.PenvItemExclude.i_ei, silver.definition.env.Init.silver_definition_env_itemName__ON__silver_definition_env_EnvItem), context.childAsIsLazy(silver.definition.env.PenvItemExclude.i_exclude)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:envItemExclude", t);
		}
	}

	public static final common.NodeFactory<Boolean> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Boolean> {
		@Override
		public Boolean invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PenvItemExclude.invoke(children[0], children[1]);
		}
	};
}