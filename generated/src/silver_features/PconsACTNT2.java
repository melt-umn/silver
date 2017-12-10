
package silver_features;

// top::AutoCopyTestNT ::= h::AutoCopyTestNT2 t::AutoCopyTestNT 
public final class PconsACTNT2 extends silver_features.NAutoCopyTestNT {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver_features.NAutoCopyTestNT2.class,silver_features.NAutoCopyTestNT.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_consACTNT2;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NAutoCopyTestNT.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NAutoCopyTestNT.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver_features.NAutoCopyTestNT2.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver_features.NAutoCopyTestNT.num_inh_attrs];

	}

	public PconsACTNT2(final Object c_h, final Object c_t) {
		super();
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver_features.NAutoCopyTestNT2 getChild_h() {
		return (silver_features.NAutoCopyTestNT2) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver_features.NAutoCopyTestNT getChild_t() {
		return (silver_features.NAutoCopyTestNT) (child_t = common.Util.demand(child_t));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_h: return getChild_h();
			case i_t: return getChild_t();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_h: return child_h;
			case i_t: return child_t;

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
		throw new common.exceptions.SilverInternalError("Production silver_features:consACTNT2 erroneously claimed to forward");
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
		return "silver_features:consACTNT2";
	}

	static void initProductionAttributeDefinitions() {
		// top.autoSyn = h.autoSyn + t.autoSyn
		silver_features.PconsACTNT2.synthesizedAttributes[silver_features.Init.silver_features_autoSyn__ON__silver_features_AutoCopyTestNT] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childDecorated(silver_features.PconsACTNT2.i_h).synthesized(silver_features.Init.silver_features_autoSyn__ON__silver_features_AutoCopyTestNT2)) + ((Integer)context.childDecorated(silver_features.PconsACTNT2.i_t).synthesized(silver_features.Init.silver_features_autoSyn__ON__silver_features_AutoCopyTestNT))); } };

	}

	public static final common.NodeFactory<PconsACTNT2> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsACTNT2> {

		@Override
		public PconsACTNT2 invoke(final Object[] children, final Object[] annotations) {
			return new PconsACTNT2(children[0], children[1]);
		}
	};

}
