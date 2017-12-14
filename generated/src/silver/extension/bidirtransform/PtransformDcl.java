
package silver.extension.bidirtransform;

// top::TransformDcl ::= qn::QName '::' transType::TypeExpr '{' trRules::TransformRuleList '}' 
public final class PtransformDcl extends silver.extension.bidirtransform.NTransformDcl {

	public static final int i_qn = 0;
	public static final int i__G_4 = 1;
	public static final int i_transType = 2;
	public static final int i__G_2 = 3;
	public static final int i_trRules = 4;
	public static final int i__G_0 = 5;


	public static final Class<?> childTypes[] = {silver.definition.core.NQName.class,silver.definition.core.TColonColon_t.class,silver.definition.type.syntax.NTypeExpr.class,silver.definition.core.TLCurly_t.class,silver.extension.bidirtransform.NTransformRuleList.class,silver.definition.core.TRCurly_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_transformDcl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformDcl.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.bidirtransform.NTransformDcl.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[6][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_qn] = new common.Lazy[silver.definition.core.NQName.num_inh_attrs];
	childInheritedAttributes[i_transType] = new common.Lazy[silver.definition.type.syntax.NTypeExpr.num_inh_attrs];
	childInheritedAttributes[i_trRules] = new common.Lazy[silver.extension.bidirtransform.NTransformRuleList.num_inh_attrs];

	}

	public PtransformDcl(final Object c_qn, final Object c__G_4, final Object c_transType, final Object c__G_2, final Object c_trRules, final Object c__G_0, final Object a_core_location) {
		super(a_core_location);
		this.child_qn = c_qn;
		this.child__G_4 = c__G_4;
		this.child_transType = c_transType;
		this.child__G_2 = c__G_2;
		this.child_trRules = c_trRules;
		this.child__G_0 = c__G_0;

	}

	private Object child_qn;
	public final silver.definition.core.NQName getChild_qn() {
		return (silver.definition.core.NQName) (child_qn = common.Util.demand(child_qn));
	}

	private Object child__G_4;
	public final silver.definition.core.TColonColon_t getChild__G_4() {
		return (silver.definition.core.TColonColon_t) (child__G_4 = common.Util.demand(child__G_4));
	}

	private Object child_transType;
	public final silver.definition.type.syntax.NTypeExpr getChild_transType() {
		return (silver.definition.type.syntax.NTypeExpr) (child_transType = common.Util.demand(child_transType));
	}

	private Object child__G_2;
	public final silver.definition.core.TLCurly_t getChild__G_2() {
		return (silver.definition.core.TLCurly_t) (child__G_2 = common.Util.demand(child__G_2));
	}

	private Object child_trRules;
	public final silver.extension.bidirtransform.NTransformRuleList getChild_trRules() {
		return (silver.extension.bidirtransform.NTransformRuleList) (child_trRules = common.Util.demand(child_trRules));
	}

	private Object child__G_0;
	public final silver.definition.core.TRCurly_t getChild__G_0() {
		return (silver.definition.core.TRCurly_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_qn: return getChild_qn();
			case i__G_4: return getChild__G_4();
			case i_transType: return getChild_transType();
			case i__G_2: return getChild__G_2();
			case i_trRules: return getChild_trRules();
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_qn: return child_qn;
			case i__G_4: return child__G_4;
			case i_transType: return child_transType;
			case i__G_2: return child__G_2;
			case i_trRules: return child_trRules;
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
		return ((silver.extension.bidirtransform.NTransformDcl)new silver.extension.bidirtransform.PmkTransformDcl(context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PtransformDcl.i_qn, silver.definition.core.Init.silver_definition_core_name__ON__silver_definition_core_QName), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PtransformDcl.i_transType)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PtransformDcl.i_trRules)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.bidirtransform.NTransformDcl)context.undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:transformDcl";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PtransformDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtransformDcl> {

		@Override
		public PtransformDcl invoke(final Object[] children, final Object[] annotations) {
			return new PtransformDcl(children[0], children[1], children[2], children[3], children[4], children[5], annotations[0]);
		}
	};

}
