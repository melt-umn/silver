
package silver.extension.patternmatching;

// top::AbstractMatchRule ::= pl::[Decorated Pattern] e::Expr 
public final class PmatchRule extends silver.extension.patternmatching.NAbstractMatchRule {

	public static final int i_pl = 0;
	public static final int i_e = 1;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class,silver.definition.core.NExpr.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_matchRule;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.extension.patternmatching.NAbstractMatchRule.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.extension.patternmatching.NAbstractMatchRule.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_e] = new common.Lazy[silver.definition.core.NExpr.num_inh_attrs];

	}

	public PmatchRule(final Object c_pl, final Object c_e, final Object a_core_location) {
		super(a_core_location);
		this.child_pl = c_pl;
		this.child_e = c_e;

	}

	private Object child_pl;
	public final common.ConsCell getChild_pl() {
		return (common.ConsCell) (child_pl = common.Util.demand(child_pl));
	}

	private Object child_e;
	public final silver.definition.core.NExpr getChild_e() {
		return (silver.definition.core.NExpr) (child_e = common.Util.demand(child_e));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_pl: return getChild_pl();
			case i_e: return getChild_e();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_pl: return child_pl;
			case i_e: return child_e;

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
		throw new common.exceptions.SilverInternalError("Production silver:extension:patternmatching:matchRule erroneously claimed to forward");
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
		return "silver:extension:patternmatching:matchRule";
	}

	static void initProductionAttributeDefinitions() {
		// top.headPattern = head(pl)
		silver.extension.patternmatching.PmatchRule.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_headPattern__ON__silver_extension_patternmatching_AbstractMatchRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PmatchRule.i_pl))); } };
		// top.isVarMatchRule = null(pl) || head(pl).patternIsVariable
		silver.extension.patternmatching.PmatchRule.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_isVarMatchRule__ON__silver_extension_patternmatching_AbstractMatchRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return (((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.patternmatching.PmatchRule.i_pl))) || ((Boolean)((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PmatchRule.i_pl))).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_patternIsVariable__ON__silver_extension_patternmatching_Pattern))); } };
		// top.expandHeadPattern = matchRule(head(pl).patternSubPatternList ++ tail(pl), e,location=top.location)
		silver.extension.patternmatching.PmatchRule.synthesizedAttributes[silver.extension.patternmatching.Init.silver_extension_patternmatching_expandHeadPattern__ON__silver_extension_patternmatching_AbstractMatchRule] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NAbstractMatchRule)new silver.extension.patternmatching.PmatchRule(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pappend.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)((common.DecoratedNode)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PmatchRule.i_pl))).synthesized(silver.extension.patternmatching.Init.silver_extension_patternmatching_patternSubPatternList__ON__silver_extension_patternmatching_Pattern)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.patternmatching.PmatchRule.i_pl))); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PmatchRule.i_e)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NAbstractMatchRule)context.undecorate()).getAnno_core_location()); } })); } };

	}

	public static final common.NodeFactory<PmatchRule> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmatchRule> {

		@Override
		public PmatchRule invoke(final Object[] children, final Object[] annotations) {
			return new PmatchRule(children[0], children[1], annotations[0]);
		}
	};

}
