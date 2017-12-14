
package silver.definition.core;

// top::ClosedOrNot ::= 'closed' 
public final class PclosedNt extends silver.definition.core.NClosedOrNot {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TClosed_kwd.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_closedNt;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NClosedOrNot.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NClosedOrNot.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PclosedNt(final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.definition.core.TClosed_kwd getChild__G_0() {
		return (silver.definition.core.TClosed_kwd) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production silver:definition:core:closedNt erroneously claimed to forward");
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
		return "silver:definition:core:closedNt";
	}

	static void initProductionAttributeDefinitions() {
		// top.whichDcl = closedNtDef
		silver.definition.core.PclosedNt.synthesizedAttributes[silver.definition.core.Init.silver_definition_core_whichDcl__ON__silver_definition_core_ClosedOrNot] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return silver.definition.env.PclosedNtDef.factory; } };

	}

	public static final common.NodeFactory<PclosedNt> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PclosedNt> {

		@Override
		public PclosedNt invoke(final Object[] children, final Object[] annotations) {
			return new PclosedNt(children[0], annotations[0]);
		}
	};

}
