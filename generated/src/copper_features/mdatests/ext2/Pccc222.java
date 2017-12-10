
package copper_features.mdatests.ext2;

// top::Root ::= 'c' Root 
public final class Pccc222 extends copper_features.mdatests.host.NRoot {

	public static final int i__G_1 = 0;
	public static final int i__G_0 = 1;


	public static final Class<?> childTypes[] = {copper_features.mdatests.ext2.TC.class,copper_features.mdatests.host.NRoot.class};

	public static final int num_local_attrs = Init.count_local__ON__copper_features_mdatests_ext2_ccc222;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[copper_features.mdatests.host.NRoot.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[copper_features.mdatests.host.NRoot.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i__G_0] = new common.Lazy[copper_features.mdatests.host.NRoot.num_inh_attrs];

	}

	public Pccc222(final Object c__G_1, final Object c__G_0) {
		super();
		this.child__G_1 = c__G_1;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_1;
	public final copper_features.mdatests.ext2.TC getChild__G_1() {
		return (copper_features.mdatests.ext2.TC) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child__G_0;
	public final copper_features.mdatests.host.NRoot getChild__G_0() {
		return (copper_features.mdatests.host.NRoot) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_1: return getChild__G_1();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_1: return child__G_1;
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production copper_features:mdatests:ext2:ccc222 erroneously claimed to forward");
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
		return "copper_features:mdatests:ext2:ccc222";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<Pccc222> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pccc222> {

		@Override
		public Pccc222 invoke(final Object[] children, final Object[] annotations) {
			return new Pccc222(children[0], children[1]);
		}
	};

}
