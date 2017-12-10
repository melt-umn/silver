
package silver_features;

// top::FunctorTestNT ::= h::FunctorTestNT t::FunctorTestNT 
public final class PconsFTNT extends silver_features.NFunctorTestNT {

	public static final int i_h = 0;
	public static final int i_t = 1;


	public static final Class<?> childTypes[] = {silver_features.NFunctorTestNT.class,silver_features.NFunctorTestNT.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_consFTNT;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NFunctorTestNT.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NFunctorTestNT.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_h] = new common.Lazy[silver_features.NFunctorTestNT.num_inh_attrs];
	childInheritedAttributes[i_t] = new common.Lazy[silver_features.NFunctorTestNT.num_inh_attrs];

	}

	public PconsFTNT(final Object c_h, final Object c_t, final Object a_silver_features_functorTestAnno) {
		super(a_silver_features_functorTestAnno);
		this.child_h = c_h;
		this.child_t = c_t;

	}

	private Object child_h;
	public final silver_features.NFunctorTestNT getChild_h() {
		return (silver_features.NFunctorTestNT) (child_h = common.Util.demand(child_h));
	}

	private Object child_t;
	public final silver_features.NFunctorTestNT getChild_t() {
		return (silver_features.NFunctorTestNT) (child_t = common.Util.demand(child_t));
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
		throw new common.exceptions.SilverInternalError("Production silver_features:consFTNT erroneously claimed to forward");
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
		return "silver_features:consFTNT";
	}

	static void initProductionAttributeDefinitions() {
		// top.functorSyn = silver_features:consFTNT(, h.functorSyn, t.functorSyn,, functorTestAnno=top.functorTestAnno)
		silver_features.PconsFTNT.synthesizedAttributes[silver_features.Init.silver_features_functorSyn__ON__silver_features_FunctorTestNT] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver_features.NFunctorTestNT)new silver_features.PconsFTNT(context.childDecoratedSynthesizedLazy(silver_features.PconsFTNT.i_h, silver_features.Init.silver_features_functorSyn__ON__silver_features_FunctorTestNT), context.childDecoratedSynthesizedLazy(silver_features.PconsFTNT.i_t, silver_features.Init.silver_features_functorSyn__ON__silver_features_FunctorTestNT), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Integer)((silver_features.NFunctorTestNT)context.undecorate()).getAnno_silver_features_functorTestAnno()); } })); } };
		// top.functorTestAnnoSum = h.functorTestAnnoSum + t.functorTestAnnoSum + top.functorTestAnno
		silver_features.PconsFTNT.synthesizedAttributes[silver_features.Init.silver_features_functorTestAnnoSum__ON__silver_features_FunctorTestNT] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(Integer.valueOf(((Integer)context.childDecorated(silver_features.PconsFTNT.i_h).synthesized(silver_features.Init.silver_features_functorTestAnnoSum__ON__silver_features_FunctorTestNT)) + ((Integer)context.childDecorated(silver_features.PconsFTNT.i_t).synthesized(silver_features.Init.silver_features_functorTestAnnoSum__ON__silver_features_FunctorTestNT))) + ((Integer)((silver_features.NFunctorTestNT)context.undecorate()).getAnno_silver_features_functorTestAnno())); } };

	}

	public static final common.NodeFactory<PconsFTNT> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PconsFTNT> {

		@Override
		public PconsFTNT invoke(final Object[] children, final Object[] annotations) {
			return new PconsFTNT(children[0], children[1], annotations[0]);
		}
	};

}
