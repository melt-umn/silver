
package silver_features;

// s::IntNT ::= l::IntNT r::IntNT 
public final class PintAdd extends silver_features.NIntNT {

	public static final int i_l = 0;
	public static final int i_r = 1;


	public static final Class<?> childTypes[] = {silver_features.NIntNT.class,silver_features.NIntNT.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_intAdd;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NIntNT.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NIntNT.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_l] = new common.Lazy[silver_features.NIntNT.num_inh_attrs];
	childInheritedAttributes[i_r] = new common.Lazy[silver_features.NIntNT.num_inh_attrs];

	}

	public PintAdd(final Object c_l, final Object c_r) {
		super();
		this.child_l = c_l;
		this.child_r = c_r;

	}

	private Object child_l;
	public final silver_features.NIntNT getChild_l() {
		return (silver_features.NIntNT) (child_l = common.Util.demand(child_l));
	}

	private Object child_r;
	public final silver_features.NIntNT getChild_r() {
		return (silver_features.NIntNT) (child_r = common.Util.demand(child_r));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_r: return getChild_r();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_r: return child_r;

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
		throw new common.exceptions.SilverInternalError("Production silver_features:intAdd erroneously claimed to forward");
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
		return "silver_features:intAdd";
	}

	static void initProductionAttributeDefinitions() {
		// s.intValue = l.intValue + r.intValue
		silver_features.PintAdd.synthesizedAttributes[silver_features.Init.silver_features_intValue__ON__silver_features_IntNT] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childDecorated(silver_features.PintAdd.i_l).synthesized(silver_features.Init.silver_features_intValue__ON__silver_features_IntNT)) + ((Integer)context.childDecorated(silver_features.PintAdd.i_r).synthesized(silver_features.Init.silver_features_intValue__ON__silver_features_IntNT))); } };

	}

	public static final common.NodeFactory<PintAdd> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PintAdd> {

		@Override
		public PintAdd invoke(final Object[] children, final Object[] annotations) {
			return new PintAdd(children[0], children[1]);
		}
	};

}
