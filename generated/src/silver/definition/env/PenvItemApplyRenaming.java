
package silver.definition.env;

public final class PenvItemApplyRenaming extends common.FunctionNode {

	public static final int i_ei = 0;
	public static final int i_renames = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NEnvItem.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_envItemApplyRenaming;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_ei] = new common.Lazy[silver.definition.env.NEnvItem.num_inh_attrs];

	}

	public PenvItemApplyRenaming(final Object c_ei, final Object c_renames) {
		this.child_ei = c_ei;
		this.child_renames = c_renames;

	}

	private Object child_ei;
	public final silver.definition.env.NEnvItem getChild_ei() {
		return (silver.definition.env.NEnvItem) (child_ei = common.Util.demand(child_ei));
	}

	private Object child_renames;
	public final common.ConsCell getChild_renames() {
		return (common.ConsCell) (child_renames = common.Util.demand(child_renames));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ei: return getChild_ei();
			case i_renames: return getChild_renames();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ei: return child_ei;
			case i_renames: return child_renames;

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
		return "silver:definition:env:envItemApplyRenaming";
	}

	public static silver.definition.env.NEnvItem invoke(final Object c_ei, final Object c_renames) {
		try {
		final common.DecoratedNode context = new PenvItemApplyRenaming(c_ei, c_renames).decorate();
		//if ! result.isJust then ei else renamedEnvItem(result.fromJust, ei.dcl)
		return (silver.definition.env.NEnvItem)(((!((Boolean)context.localDecorated(silver.definition.env.Init.result__ON__silver_definition_env_envItemApplyRenaming).synthesized(core.Init.core_isJust__ON__core_Maybe))) ? context.childDecorated(silver.definition.env.PenvItemApplyRenaming.i_ei).undecorate() : ((silver.definition.env.NEnvItem)new silver.definition.env.PrenamedEnvItem(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)context.localDecorated(silver.definition.env.Init.result__ON__silver_definition_env_envItemApplyRenaming).synthesized(core.Init.core_fromJust__ON__core_Maybe)); } }, context.childDecoratedSynthesizedLazy(silver.definition.env.PenvItemApplyRenaming.i_ei, silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_EnvItem)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:envItemApplyRenaming", t);
		}
	}

	public static final common.NodeFactory<silver.definition.env.NEnvItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.env.NEnvItem> {
		@Override
		public silver.definition.env.NEnvItem invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PenvItemApplyRenaming.invoke(children[0], children[1]);
		}
	};
}