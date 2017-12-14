
package silver.definition.env;

public final class PexplodeEnvItems extends common.FunctionNode {

	public static final int i_eis = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_explodeEnvItems;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PexplodeEnvItems(final Object c_eis) {
		this.child_eis = c_eis;

	}

	private Object child_eis;
	public final common.ConsCell getChild_eis() {
		return (common.ConsCell) (child_eis = common.Util.demand(child_eis));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_eis: return getChild_eis();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_eis: return child_eis;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:definition:env:explodeEnvItems";
	}

	public static common.ConsCell invoke(final Object c_eis) {
		try {
		final common.DecoratedNode context = new PexplodeEnvItems(c_eis).decorate();
		//if null(eis) then [] else head(eis).envContribs ++ explodeEnvItems(tail(eis))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.env.PexplodeEnvItems.i_eis))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((silver.definition.env.NEnvItem)core.Phead.invoke(context.childAsIsLazy(silver.definition.env.PexplodeEnvItems.i_eis))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.env.Init.silver_definition_env_envContribs__ON__silver_definition_env_EnvItem)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.env.PexplodeEnvItems.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.env.PexplodeEnvItems.i_eis))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:env:explodeEnvItems", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PexplodeEnvItems.invoke(children[0]);
		}
	};
}