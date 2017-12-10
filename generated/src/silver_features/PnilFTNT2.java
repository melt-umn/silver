
package silver_features;

// top::FunctorTestNT2 ::= s::String 
public final class PnilFTNT2 extends silver_features.NFunctorTestNT2 {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_nilFTNT2;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NFunctorTestNT2.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NFunctorTestNT2.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnilFTNT2(final Object c_s, final Object a_silver_features_functorTestAnno) {
		super(a_silver_features_functorTestAnno);
		this.child_s = c_s;

	}

	private Object child_s;
	public final common.StringCatter getChild_s() {
		return (common.StringCatter) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		throw new common.exceptions.SilverInternalError("Production silver_features:nilFTNT2 erroneously claimed to forward");
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
		return "silver_features:nilFTNT2";
	}

	static void initProductionAttributeDefinitions() {
		// top.functorSyn = silver_features:nilFTNT2(, s,, functorTestAnno=top.functorTestAnno)
		silver_features.PnilFTNT2.synthesizedAttributes[silver_features.Init.silver_features_functorSyn__ON__silver_features_FunctorTestNT2] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT2)new silver_features.PnilFTNT2(context.childAsIsLazy(silver_features.PnilFTNT2.i_s), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)((silver_features.NFunctorTestNT2)context.undecorate()).getAnno_silver_features_functorTestAnno()); } })); } };
		// top.functorTestAnnoSum = top.functorTestAnno
		silver_features.PnilFTNT2.synthesizedAttributes[silver_features.Init.silver_features_functorTestAnnoSum__ON__silver_features_FunctorTestNT2] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)((silver_features.NFunctorTestNT2)context.undecorate()).getAnno_silver_features_functorTestAnno()); } };

	}

	public static final common.NodeFactory<PnilFTNT2> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnilFTNT2> {

		@Override
		public PnilFTNT2 invoke(final Object[] children, final Object[] annotations) {
			return new PnilFTNT2(children[0], annotations[0]);
		}
	};

}
