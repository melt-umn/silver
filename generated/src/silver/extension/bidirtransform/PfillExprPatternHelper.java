
package silver.extension.bidirtransform;

// top::Expr ::= toFill::Expr inputs::Pair<[Expr] [String]> 
public final class PfillExprPatternHelper extends silver.definition.core.NExpr {

	public static final int i_toFill = 0;
	public static final int i_inputs = 1;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,core.NPair.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_fillExprPatternHelper;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_inputs] = new common.Lazy[core.NPair.num_inh_attrs];

	}

	public PfillExprPatternHelper(final Object c_toFill, final Object c_inputs, final Object a_core_location) {
		super(a_core_location);
		this.child_toFill = c_toFill;
		this.child_inputs = c_inputs;

	}

	private Object child_toFill;
	public final silver.definition.core.NExpr getChild_toFill() {
		return (silver.definition.core.NExpr) (child_toFill = common.Util.demand(child_toFill));
	}

	private Object child_inputs;
	public final core.NPair getChild_inputs() {
		return (core.NPair) (child_inputs = common.Util.demand(child_inputs));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toFill: return getChild_toFill();
			case i_inputs: return getChild_inputs();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toFill: return child_toFill;
			case i_inputs: return child_inputs;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PfillExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PfillExprPatternHelper.i_toFill)), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PfillExprPatternHelper.i_inputs, core.Init.core_fst__ON__core_Pair), context.childDecoratedSynthesizedLazy(silver.extension.bidirtransform.PfillExprPatternHelper.i_inputs, core.Init.core_snd__ON__core_Pair), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PfillExprPatternHelper.i_toFill).undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:fillExprPatternHelper";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PfillExprPatternHelper> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfillExprPatternHelper> {

		@Override
		public PfillExprPatternHelper invoke(final Object[] children, final Object[] annotations) {
			return new PfillExprPatternHelper(children[0], children[1], annotations[0]);
		}
	};

}
