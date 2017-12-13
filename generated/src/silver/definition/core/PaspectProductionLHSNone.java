
package silver.definition.core;

// top::AspectProductionLHS ::= '_' 
public final class PaspectProductionLHSNone extends silver.definition.core.NAspectProductionLHS {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {silver.definition.core.TUnderScore_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_aspectProductionLHSNone;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAspectProductionLHS.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAspectProductionLHS.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PaspectProductionLHSNone(final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final silver.definition.core.TUnderScore_t getChild__G_0() {
		return (silver.definition.core.TUnderScore_t) (child__G_0 = common.Util.demand(child__G_0));
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NAspectProductionLHS)new silver.definition.core.PaspectProductionLHSId(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)silver.definition.core.Pname.invoke((new common.StringCatter("p_top")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAspectProductionLHS)context.undecorate()).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAspectProductionLHS)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:definition:core:aspectProductionLHSNone";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "_"
		silver.definition.core.PaspectProductionLHSNone.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AspectProductionLHS] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (new common.StringCatter("_")); } };

	}

	public static final common.NodeFactory<PaspectProductionLHSNone> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PaspectProductionLHSNone> {

		@Override
		public PaspectProductionLHSNone invoke(final Object[] children, final Object[] annotations) {
			return new PaspectProductionLHSNone(children[0], annotations[0]);
		}
	};

}
