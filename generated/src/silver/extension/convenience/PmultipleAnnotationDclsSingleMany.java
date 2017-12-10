
package silver.extension.convenience;

// top::AGDcl ::= 'annotation' a::QName tl::BracketedOptTypeExprs 'occurs' 'on' nts::QNames2 ';' 
public final class PmultipleAnnotationDclsSingleMany extends silver.definition.core.NAGDcl {

	public static final int i__G_6 = 0;
	public static final int i_a = 1;
	public static final int i_tl = 2;
	public static final int i__G_3 = 3;
	public static final int i__G_2 = 4;
	public static final int i_nts = 5;
	public static final int i__G_0 = 6;


	public static final Class<?> childTypes[] = {silver.definition.core.TAnnotation_kwd.class,silver.definition.core.NQName.class,silver.definition.type.syntax.NBracketedOptTypeExprs.class,silver.definition.core.TOccurs_kwd.class,silver.definition.core.TOn_kwd.class,silver.extension.convenience.NQNames2.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_multipleAnnotationDclsSingleMany;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NAGDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[7][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_a] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_tl] = new common.Lazy[silver.definition.type.syntax.NBracketedOptTypeExprs.num_inh_attrs];
	childInheritedAttributes[i_nts] = new common.Lazy[silver.extension.convenience.NQNames2.num_inh_attrs];

	}

	public PmultipleAnnotationDclsSingleMany(final Object c__G_6, final Object c_a, final Object c_tl, final Object c__G_3, final Object c__G_2, final Object c_nts, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_6 = c__G_6;
		this.child_a = c_a;
		this.child_tl = c_tl;
		this.child__G_3 = c__G_3;
		this.child__G_2 = c__G_2;
		this.child_nts = c_nts;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_6;
	public final silver.definition.core.TAnnotation_kwd getChild__G_6() {
		return (silver.definition.core.TAnnotation_kwd) (child__G_6 = common.Util.demand(child__G_6));
	}

	private Object child_a;
	public final silver.definition.core.NQName getChild_a() {
		return (silver.definition.core.NQName) (child_a = common.Util.demand(child_a));
	}

	private Object child_tl;
	public final silver.definition.type.syntax.NBracketedOptTypeExprs getChild_tl() {
		return (silver.definition.type.syntax.NBracketedOptTypeExprs) (child_tl = common.Util.demand(child_tl));
	}

	private Object child__G_3;
	public final silver.definition.core.TOccurs_kwd getChild__G_3() {
		return (silver.definition.core.TOccurs_kwd) (child__G_3 = common.Util.demand(child__G_3));
	}

	private Object child__G_2;
	public final silver.definition.core.TOn_kwd getChild__G_2() {
		return (silver.definition.core.TOn_kwd) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_nts;
	public final silver.extension.convenience.NQNames2 getChild_nts() {
		return (silver.extension.convenience.NQNames2) (child_nts = common.Util.demand(child_nts));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_6: return getChild__G_6();
			case i_a: return getChild_a();
			case i_tl: return getChild_tl();
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
			case i__G_6: return child__G_6;
			case i_a: return child_a;
			case i_tl: return child_tl;
			case i__G_3: return child__G_3;
			case i__G_2: return child__G_2;
			case i_nts: return child_nts;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 7;
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
		return ((silver.definition.core.NAGDcl)silver.extension.convenience.PmakeOccursDcls.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NAGDcl)context.undecorate()).getAnno_core_location()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.convenience.NQNameWithTL)new silver.extension.convenience.PqNameWithTL(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PmultipleAnnotationDclsSingleMany.i_a)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PmultipleAnnotationDclsSingleMany.i_tl)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childDecoratedSynthesizedLazy(silver.extension.convenience.PmultipleAnnotationDclsSingleMany.i_nts, silver.extension.convenience.Init.silver_extension_convenience_qnames__ON__silver_extension_convenience_QNames2)));
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
		return "silver:extension:convenience:multipleAnnotationDclsSingleMany";
	}

	static void initProductionAttributeDefinitions() {
		// top.pp = "annotation " ++ a.pp ++ " occurs on " ++ nts.pp ++ " ;"
		silver.extension.convenience.PmultipleAnnotationDclsSingleMany.synthesizedAttributes[silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_AGDcl] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("annotation ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PmultipleAnnotationDclsSingleMany.i_a).synthesized(silver.definition.core.Init.silver_definition_env_pp__ON__silver_definition_core_QName)), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" occurs on ")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)context.childDecorated(silver.extension.convenience.PmultipleAnnotationDclsSingleMany.i_nts).synthesized(silver.extension.convenience.Init.silver_definition_env_pp__ON__silver_extension_convenience_QNames2)), (common.StringCatter)(new common.StringCatter(" ;")))))); } };

	}

	public static final common.NodeFactory<PmultipleAnnotationDclsSingleMany> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmultipleAnnotationDclsSingleMany> {

		@Override
		public PmultipleAnnotationDclsSingleMany invoke(final Object[] children, final Object[] annotations) {
			return new PmultipleAnnotationDclsSingleMany(children[0], children[1], children[2], children[3], children[4], children[5], children[6], annotations[0]);
		}
	};

}
