
package silver.extension.bidirtransform;

// top::Expr ::= toFill::Expr appexps::AppExprs pattern::[Pattern] 
public final class PfillExprPattern extends silver.definition.core.NExpr {

	public static final int i_toFill = 0;
	public static final int i_appexps = 1;
	public static final int i_pattern = 2;


	public static final Class<?> childTypes[] = {silver.definition.core.NExpr.class,silver.definition.core.NAppExprs.class,common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_bidirtransform_fillExprPattern;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.definition.core.NExpr.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_toFill] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];
	childInheritedAttributes[i_appexps] = new common.Lazy[silver.definition.core.NAppExprs.num_inh_attrs];

	}

	public PfillExprPattern(final Object c_toFill, final Object c_appexps, final Object c_pattern, final Object a_core_location) {
		super(a_core_location);
		this.child_toFill = c_toFill;
		this.child_appexps = c_appexps;
		this.child_pattern = c_pattern;

	}

	private Object child_toFill;
	public final silver.definition.core.NExpr getChild_toFill() {
		return (silver.definition.core.NExpr) (child_toFill = common.Util.demand(child_toFill));
	}

	private Object child_appexps;
	public final silver.definition.core.NAppExprs getChild_appexps() {
		return (silver.definition.core.NAppExprs) (child_appexps = common.Util.demand(child_appexps));
	}

	private Object child_pattern;
	public final common.ConsCell getChild_pattern() {
		return (common.ConsCell) (child_pattern = common.Util.demand(child_pattern));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_toFill: return getChild_toFill();
			case i_appexps: return getChild_appexps();
			case i_pattern: return getChild_pattern();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_toFill: return child_toFill;
			case i_appexps: return child_appexps;
			case i_pattern: return child_pattern;

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
		return ((silver.definition.core.NExpr)new silver.extension.bidirtransform.PfillExpr(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PfillExprPattern.i_toFill)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.bidirtransform.Init.inputs__ON__silver_extension_bidirtransform_fillExprPattern).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.extension.bidirtransform.Init.inputs__ON__silver_extension_bidirtransform_fillExprPattern).synthesized(core.Init.core_snd__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NExpr)context.childDecorated(silver.extension.bidirtransform.PfillExprPattern.i_toFill).undecorate()).getAnno_core_location()); } }));
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
		return "silver:extension:bidirtransform:fillExprPattern";
	}

	static void initProductionAttributeDefinitions() {
		// inputs = matchAppExpsToPattern(appexps, pattern, top.env)
		silver.extension.bidirtransform.PfillExprPattern.localAttributes[silver.extension.bidirtransform.Init.inputs__ON__silver_extension_bidirtransform_fillExprPattern] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NPair)silver.extension.bidirtransform.PmatchAppExpsToPattern.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.bidirtransform.PfillExprPattern.i_appexps)), context.childAsIsLazy(silver.extension.bidirtransform.PfillExprPattern.i_pattern), context.contextInheritedLazy(silver.definition.core.Init.silver_definition_env_env__ON__silver_definition_core_Expr))); } };

	}

	public static final common.NodeFactory<PfillExprPattern> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PfillExprPattern> {

		@Override
		public PfillExprPattern invoke(final Object[] children, final Object[] annotations) {
			return new PfillExprPattern(children[0], children[1], children[2], annotations[0]);
		}
	};

}
