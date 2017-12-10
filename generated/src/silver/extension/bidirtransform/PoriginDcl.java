
package silver.extension.bidirtransform;

// top::AGDcl ::= 'apply' 'origins' 'on' nts::NonterminalList ';' 
public final class PoriginDcl extends silver.definition.core.NAGDcl {

	public static final int i__G_4 = 0;
	public static final int i__G_3 = 1;
	public static final int i__G_2 = 2;
	public static final int i_nts = 3;
	public static final int i__G_0 = 4;


	public static final Class<?> childTypes[] = {silver.extension.bidirtransform.TApply_kwd.class,silver.extension.bidirtransform.TOrigins_kwd.class,silver.definition.core.TOn_kwd.class,silver.extension.bidirtransform.NNonterminalList.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_originDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[5][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_nts] = new common.Lazy[silver.extension.bidirtransform.NNonterminalList.num_inh_attrs];

	}

	public PoriginDcl(final Object c__G_4, final Object c__G_3, final Object c__G_2, final Object c_nts, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_4 = c__G_4;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_nts = c_nts;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_4;
	public final silver.extension.bidirtransform.TApply_kwd getChild__G_4() {
		return (silver.extension.bidirtransform.TApply_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child__G_3;
	public final silver.extension.bidirtransform.TOrigins_kwd getChild__G_3() {
		return (silver.extension.bidirtransform.TOrigins_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.core.TOn_kwd getChild__G_2() {
		return (silver.definition.core.TOn_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_nts;
	public final silver.extension.bidirtransform.NNonterminalList getChild_nts() {
		return (silver.extension.bidirtransform.NNonterminalList) (child_nts = common.Util.demand(child_nts));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_4: return getChild__G_4();
			case i__G_3: return getChild__G_3();
			case i__G_2: return getChild__G_2();
			case i_nts: return getChild_nts();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_4: return child__G_4;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_nts: return child_nts;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 5;
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
		return ((silver.definition.core.NAGDcl)new silver.extension.bidirtransform.PapplyOrigins(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PoriginDcl.i_nts, silver.extension.bidirtransform.Init.silver_extension_bidirtransform_ntList__ON__silver_extension_bidirtransform_NonterminalList), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:originDcl";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PoriginDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoriginDcl> {

		@Override
		public PoriginDcl invoke(final Object[] children, final Object[] annotations) {
			return new PoriginDcl(children[0], children[1], children[2], children[3], children[4], annotations[0]);
		}
	};

}
