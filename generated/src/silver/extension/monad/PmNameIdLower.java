
package silver.extension.monad;

// top::MName ::= id::IdLower_t 
public final class PmNameIdLower extends silver.extension.monad.NMName {

	public static final int i_id = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TIdLower_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_monad_mNameIdLower;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.monad.NMName.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.monad.NMName.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PmNameIdLower(final Object c_id, final Object a_core_location) {
		super(a_core_location);
		this.child_id = c_id;

	}

	private Object child_id;
	public final silver.definition.core.TIdLower_t getChild_id() {
		return (silver.definition.core.TIdLower_t) (child_id = common.Util.demand(child_id));
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:monad:mNameIdLower erroneously claimed to forward");
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
		return "silver:extension:monad:mNameIdLower";
	}

	static void initProductionAttributeDefinitions() {
		// top.name = id.lexeme
		silver.extension.monad.PmNameIdLower.synthesizedAttributes[silver.extension.monad.Init.silver_extension_monad_name__ON__silver_extension_monad_MName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.core.TIdLower_t)context.childAsIs(silver.extension.monad.PmNameIdLower.i_id)).lexeme); } };
		// top.pp = id.lexeme
		silver.extension.monad.PmNameIdLower.synthesizedAttributes[silver.extension.monad.Init.silver_definition_env_pp__ON__silver_extension_monad_MName] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.definition.core.TIdLower_t)context.childAsIs(silver.extension.monad.PmNameIdLower.i_id)).lexeme); } };

	}

	public static final common.NodeFactory<PmNameIdLower> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmNameIdLower> {

		@Override
		public PmNameIdLower invoke(final Object[] children, final Object[] annotations) {
			return new PmNameIdLower(children[0], annotations[0]);
		}
	};

}
