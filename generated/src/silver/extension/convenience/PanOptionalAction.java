
package silver.extension.convenience;

// opta::OptionalAction ::= 'action' acode::ActionCode_c 
public final class PanOptionalAction extends silver.extension.convenience.NOptionalAction {

	public static final int i__G_1 = 0;
	public static final int i_acode = 1;


	public static final Class<?> childTypes[] = {silver.modification.copper.TAction_kwd.class,silver.modification.copper.NActionCode_c.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_anOptionalAction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.convenience.NOptionalAction.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.convenience.NOptionalAction.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_acode] = new common.Lazy[silver.modification.copper.NActionCode_c.num_inh_attrs];

	}

	public PanOptionalAction(final Object c__G_1, final Object c_acode, final Object a_core_location) {
		super(a_core_location);
		this.child__G_1 = c__G_1;
		this.child_acode = c_acode;

	}

	private Object child__G_1;
	public final silver.modification.copper.TAction_kwd getChild__G_1() {
		return (silver.modification.copper.TAction_kwd) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_acode;
	public final silver.modification.copper.NActionCode_c getChild_acode() {
		return (silver.modification.copper.NActionCode_c) (child_acode = common.Util.demand(child_acode));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_1: return getChild__G_1();
			case i_acode: return getChild_acode();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_1: return child__G_1;
			case i_acode: return child_acode;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:convenience:anOptionalAction erroneously claimed to forward");
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
		return "silver:extension:convenience:anOptionalAction";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PanOptionalAction> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PanOptionalAction> {

		@Override
		public PanOptionalAction invoke(final Object[] children, final Object[] annotations) {
			return new PanOptionalAction(children[0], children[1], annotations[0]);
		}
	};

}
