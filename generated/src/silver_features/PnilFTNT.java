
package silver_features;

// top::FunctorTestNT ::= i::Integer 
public final class PnilFTNT extends silver_features.NFunctorTestNT {

	public static final int i_i = 0;


	public static final Class<?> childTypes[] = {Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_nilFTNT;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NFunctorTestNT.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NFunctorTestNT.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilFTNT(final Object c_i, final Object a_silver_features_functorTestAnno) {
		super(a_silver_features_functorTestAnno);
		this.child_i = c_i;

	}

	private Object child_i;
	public final Integer getChild_i() {
		return (Integer) (child_i = common.Util.demand(child_i));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_i: return getChild_i();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_i: return child_i;

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
		throw new common.exceptions.SilverInternalError("Production silver_features:nilFTNT erroneously claimed to forward");
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
		return "silver_features:nilFTNT";
	}

	static void initProductionAttributeDefinitions() {
		// top.functorSyn = nilFTNT(10,functorTestAnno=123)
		silver_features.PnilFTNT.synthesizedAttributes[silver_features.Init.silver_features_functorSyn__ON__silver_features_FunctorTestNT] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT)new silver_features.PnilFTNT(Integer.valueOf((int)10), Integer.valueOf((int)123))); } };
		// top.functorTestAnnoSum = top.functorTestAnno
		silver_features.PnilFTNT.synthesizedAttributes[silver_features.Init.silver_features_functorTestAnnoSum__ON__silver_features_FunctorTestNT] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)((silver_features.NFunctorTestNT)context.undecorate()).getAnno_silver_features_functorTestAnno()); } };

	}

	public static final common.NodeFactory<PnilFTNT> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilFTNT> {

		@Override
		public PnilFTNT invoke(final Object[] children, final Object[] annotations) {
			return new PnilFTNT(children[0], annotations[0]);
		}
	};

}
