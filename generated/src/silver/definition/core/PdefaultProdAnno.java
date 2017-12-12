
package silver.definition.core;

// top::ProductionStmt ::= 'default' 'annotation' qn::QName '=' aexpr::AppExpr ';' 
public final class PdefaultProdAnno extends silver.definition.core.NProductionStmt {

	public static final int i__G_5 = 0;
	public static final int i__G_4 = 1;
	public static final int i_qn = 2;
	public static final int i__G_2 = 3;
	public static final int i_aexpr = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.definition.core.TDefault_kwd.class,silver.definition.core.TAnnotation_kwd.class,silver.definition.core.NQName.class,silver.definition.core.TEqual_t.class,silver.definition.core.NAppExpr.class,silver.definition.core.TSemi_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_defaultProdAnno;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NProductionStmt.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_aexpr] = new common.Lazy[silver.definition.core.NAppExpr.num_inh_attrs];

	}

	public PdefaultProdAnno(final Object c__G_5, final Object c__G_4, final Object c_qn, final Object c__G_2, final Object c_aexpr, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child__G_5 = c__G_5;
		this.child__G_4 = c__G_4;
		this.child_qn = c_qn;
		this.child__G_2 = c__G_2;
		this.child_aexpr = c_aexpr;
		this.child__G_0 = c__G_0;

	}

	private Object child__G_5;
	public final silver.definition.core.TDefault_kwd getChild__G_5() {
		return (silver.definition.core.TDefault_kwd) (child__G_5 = common.Util.demand(child__G_5));
	}

	private Object child__G_4;
	public final silver.definition.core.TAnnotation_kwd getChild__G_4() {
		return (silver.definition.core.TAnnotation_kwd) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}

	private Object child__G_2;
	public final silver.definition.core.TEqual_t getChild__G_2() {
		return (silver.definition.core.TEqual_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_aexpr;
	public final silver.definition.core.NAppExpr getChild_aexpr() {
		return (silver.definition.core.NAppExpr) (child_aexpr = common.Util.demand(child_aexpr));
	}

	private Object child__G_0;
	public final silver.definition.core.TSemi_t getChild__G_0() {
		return (silver.definition.core.TSemi_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_5: return getChild__G_5();
			case i__G_4: return getChild__G_4();
			case i_qn: return getChild_qn();
			case i__G_2: return getChild__G_2();
			case i_aexpr: return getChild_aexpr();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_5: return child__G_5;
			case i__G_4: return child__G_4;
			case i_qn: return child_qn;
			case i__G_2: return child__G_2;
			case i_aexpr: return child_aexpr;
			case i__G_0: return child__G_0;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 6;
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
		return ((silver.definition.core.NProductionStmt)new silver.definition.core.PmkDefaultProdAnno(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PdefaultProdAnno.i_qn)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.core.PdefaultProdAnno.i_aexpr)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NProductionStmt)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:definition:core:defaultProdAnno";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PdefaultProdAnno> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PdefaultProdAnno> {

		@Override
		public PdefaultProdAnno invoke(final Object[] children, final Object[] annotations) {
			return new PdefaultProdAnno(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
