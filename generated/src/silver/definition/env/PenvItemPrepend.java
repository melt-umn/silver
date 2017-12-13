
package silver.definition.env;

public final class PenvItemPrepend extends common.FunctionNode {

	public static final int i_ei = 0;
	public static final int i_pfx = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NEnvItem.class,common.StringCatter.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_envItemPrepend;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ei] = new common.Lazy[silver.definition.env.NEnvItem.num_inh_attrs];

	}

	public PenvItemPrepend(final Object c_ei, final Object c_pfx) {
		this.child_ei = c_ei;
		this.child_pfx = c_pfx;

	}

	private Object child_ei;
	public final silver.definition.env.NEnvItem getChild_ei() {
		return (silver.definition.env.NEnvItem) (child_ei = common.Util.demand(child_ei));
	}

	private Object child_pfx;
	public final common.StringCatter getChild_pfx() {
		return (common.StringCatter) (child_pfx = common.Util.demand(child_pfx));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ei: return getChild_ei();
			case i_pfx: return getChild_pfx();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ei: return child_ei;
			case i_pfx: return child_pfx;

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
		return "silver:definition:env:envItemPrepend";
	}

	public static silver.definition.env.NEnvItem invoke(final Object c_ei, final Object c_pfx) {
		try {
		final common.DecoratedNode context = new PenvItemPrepend(c_ei, c_pfx).decorate();
		//renamedEnvItem(pfx ++ ei.itemName, ei.dcl)
		return (silver.definition.env.NEnvItem)(((silver.definition.env.NEnvItem)new silver.definition.env.PrenamedEnvItem(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)context.childAsIs(silver.definition.env.PenvItemPrepend.i_pfx)), (common.StringCatter)((common.StringCatter)context.childDecorated(silver.definition.env.PenvItemPrepend.i_ei).synthesized(silver.definition.env.Init.silver_definition_env_itemName__ON__silver_definition_env_EnvItem))); } }, context.childDecoratedSynthesizedLazy(silver.definition.env.PenvItemPrepend.i_ei, silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_EnvItem))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:envItemPrepend", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NEnvItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NEnvItem> {
		@Override
		public silver.definition.env.NEnvItem invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PenvItemPrepend.invoke(children[0], children[1]);
		}
	};
}