
package silver.modification.copper;

// terms::TermPrecList ::= t::QName ',' terms_tail::TermPrecList 
public final class PtermPrecListCons extends silver.modification.copper.NTermPrecList {

	public static final int i_t = 0;
	public static final int i__G_1 = 1;
	public static final int i_terms_tail = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.TComma_t.class,silver.modification.copper.NTermPrecList.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_termPrecListCons;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.modification.copper.NTermPrecList.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.modification.copper.NTermPrecList.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_t] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_terms_tail] = new common.Lazy[silver.modification.copper.NTermPrecList.num_inh_attrs];

	}

	public PtermPrecListCons(final Object c_t, final Object c__G_1, final Object c_terms_tail, final Object a_core_location) {
		super(a_core_location);
		this.child_t = c_t;
		this.child__G_1 = c__G_1;
		this.child_terms_tail = c_terms_tail;

	}

	private Object child_t;
	public final silver.definition.core.NQName getChild_t() {
		return (silver.definition.core.NQName) (child_t = common.Util.demand(child_t));
	}

	private Object child__G_1;
	public final silver.definition.core.TComma_t getChild__G_1() {
		return (silver.definition.core.TComma_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_terms_tail;
	public final silver.modification.copper.NTermPrecList getChild_terms_tail() {
		return (silver.modification.copper.NTermPrecList) (child_terms_tail = common.Util.demand(child_terms_tail));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_t: return getChild_t();
			case i__G_1: return getChild__G_1();
			case i_terms_tail: return getChild_terms_tail();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_t: return child_t;
			case i__G_1: return child__G_1;
			case i_terms_tail: return child_terms_tail;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return ((silver.modification.copper.NTermPrecList)new silver.modification.copper.PtermPrecList(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PtermPrecListCons.i_t)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.modification.copper.PtermPrecListCons.i_terms_tail)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.copper.NTermPrecList)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:modification:copper:termPrecListCons";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PtermPrecListCons> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtermPrecListCons> {

		@Override
		public PtermPrecListCons invoke(final Object[] children, final Object[] annotations) {
			return new PtermPrecListCons(children[0], children[1], children[2], annotations[0]);
		}
	};

}
