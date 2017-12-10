
package silver.extension.convenience;

// top::QNames ::= id::QNameWithTL 
public final class PqNamesSingle extends silver.extension.convenience.NQNames {

	public static final int i_id = 0;


	public static final Class<?> childTypes[] = {silver.extension.convenience.NQNameWithTL.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_qNamesSingle;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.convenience.NQNames.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.convenience.NQNames.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_id] = new common.Lazy[silver.extension.convenience.NQNameWithTL.num_inh_attrs];

	}

	public PqNamesSingle(final Object c_id) {
		super();
		this.child_id = c_id;

	}

	private Object child_id;
	public final silver.extension.convenience.NQNameWithTL getChild_id() {
		return (silver.extension.convenience.NQNameWithTL) (child_id = common.Util.demand(child_id));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_id: return getChild_id();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_id: return child_id;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:convenience:qNamesSingle erroneously claimed to forward");
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
		return "silver:extension:convenience:qNamesSingle";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = id.pp
		silver.extension.convenience.PqNamesSingle.synthesizedAttributes[silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_QNames] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver.extension.convenience.PqNamesSingle.i_id).synthesized(silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_QNameWithTL)); } };
		// top.qnames = [ id ]
		silver.extension.convenience.PqNamesSingle.synthesizedAttributes[silver.extension.convenience.Init.silver_extension_convenience_qnames__ON__silver_extension_convenience_QNames] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PqNamesSingle.i_id)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } };

	}

	public static final common.NodeFactory<PqNamesSingle> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PqNamesSingle> {

		@Override
		public PqNamesSingle invoke(final Object[] children, final Object[] annotations) {
			return new PqNamesSingle(children[0]);
		}
	};

}
