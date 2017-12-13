
package silver.extension.deprecation;

// top::NameTickTick ::= id::IdTickTick_t 
public final class PnameIdTickTick extends silver.extension.deprecation.NNameTickTick {

	public static final int i_id = 0;


	public static final Class<?> childTypes[] = {silver.extension.deprecation.TIdTickTick_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_deprecation_nameIdTickTick;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.deprecation.NNameTickTick.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.deprecation.NNameTickTick.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnameIdTickTick(final Object c_id, final Object a_core_location) {
		super(a_core_location);
		this.child_id = c_id;

	}

	private Object child_id;
	public final silver.extension.deprecation.TIdTickTick_t getChild_id() {
		return (silver.extension.deprecation.TIdTickTick_t) (child_id = common.Util.demand(child_id));
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
		throw new common.exceptions.SilverInternalError("Production silver:extension:deprecation:nameIdTickTick erroneously claimed to forward");
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
		return "silver:extension:deprecation:nameIdTickTick";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = id.lexeme
		silver.extension.deprecation.PnameIdTickTick.synthesizedAttributes[silver.extension.deprecation.Init.silver_definition_env_pp__ON__silver_extension_deprecation_NameTickTick] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)((silver.extension.deprecation.TIdTickTick_t)context.childAsIs(silver.extension.deprecation.PnameIdTickTick.i_id)).lexeme); } };
		// top.name = substring(0, length(id.lexeme) - 2, id.lexeme)
		silver.extension.deprecation.PnameIdTickTick.synthesizedAttributes[silver.extension.deprecation.Init.silver_definition_core_name__ON__silver_extension_deprecation_NameTickTick] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)core.Psubstring.invoke(Integer.valueOf((int)0), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((common.StringCatter)((common.StringCatter)((silver.extension.deprecation.TIdTickTick_t)context.childAsIs(silver.extension.deprecation.PnameIdTickTick.i_id)).lexeme)).length()) - Integer.valueOf((int)2)); } }, ((common.StringCatter)((silver.extension.deprecation.TIdTickTick_t)context.childAsIs(silver.extension.deprecation.PnameIdTickTick.i_id)).lexeme))); } };

	}

	public static final common.NodeFactory<PnameIdTickTick> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnameIdTickTick> {

		@Override
		public PnameIdTickTick invoke(final Object[] children, final Object[] annotations) {
			return new PnameIdTickTick(children[0], annotations[0]);
		}
	};

}
