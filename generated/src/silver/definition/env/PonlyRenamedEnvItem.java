
package silver.definition.env;

// ei::EnvItem ::= newname::String di::DclInfo 
public final class PonlyRenamedEnvItem extends silver.definition.env.NEnvItem {

	public static final int i_newname = 0;
	public static final int i_di = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.definition.env.NDclInfo.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_env_onlyRenamedEnvItem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.env.NEnvItem.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.env.NEnvItem.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_di] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];

	}

	public PonlyRenamedEnvItem(final Object c_newname, final Object c_di) {
		super();
		this.child_newname = c_newname;
		this.child_di = c_di;

	}

	private Object child_newname;
	public final common.StringCatter getChild_newname() {
		return (common.StringCatter) (child_newname = common.Util.demand(child_newname));
	}

	private Object child_di;
	public final silver.definition.env.NDclInfo getChild_di() {
		return (silver.definition.env.NDclInfo) (child_di = common.Util.demand(child_di));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_newname: return getChild_newname();
			case i_di: return getChild_di();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_newname: return child_newname;
			case i_di: return child_di;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:definition:env:onlyRenamedEnvItem erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "silver:definition:env:onlyRenamedEnvItem";
	}

	static void initProductionAttributeDefinitions() {
		// ei.itemName = newname
		silver.definition.env.PonlyRenamedEnvItem.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_itemName__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childAsIs(silver.definition.env.PonlyRenamedEnvItem.i_newname)); } };
		// ei.dcl = di
		silver.definition.env.PonlyRenamedEnvItem.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_dcl__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return context.childDecorated(silver.definition.env.PonlyRenamedEnvItem.i_di).undecorate(); } };
		// ei.envContribs = [ pair(newname, di) ]
		silver.definition.env.PonlyRenamedEnvItem.synthesizedAttributes[silver.definition.env.Init.silver_definition_env_envContribs__ON__silver_definition_env_EnvItem] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NPair)new core.Ppair(context.childAsIsLazy(silver.definition.env.PonlyRenamedEnvItem.i_newname), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.env.PonlyRenamedEnvItem.i_di)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PonlyRenamedEnvItem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PonlyRenamedEnvItem> {

		@Override
		public PonlyRenamedEnvItem invoke(final Object[] children, final Object[] annotations) {
			return new PonlyRenamedEnvItem(children[0], children[1]);
		}
	};

}
