
package silver.extension.treegen;

public final class PgenerateCheckEqMRuleList extends common.FunctionNode {

	public static final int i_prod = 0;
	public static final int i_l = 1;


	public static final Class<?> childTypes[] = { silver.definition.env.NDclInfo.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_generateCheckEqMRuleList;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_prod] = new common.Lazy[silver.definition.env.NDclInfo.num_inh_attrs];
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PgenerateCheckEqMRuleList(final Object c_prod, final Object c_l) {
		this.child_prod = c_prod;
		this.child_l = c_l;

	}

	private Object child_prod;
	public final silver.definition.env.NDclInfo getChild_prod() {
		return (silver.definition.env.NDclInfo) (child_prod = common.Util.demand(child_prod));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_prod: return getChild_prod();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_prod: return child_prod;
			case i_l: return child_l;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
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
		return "silver:extension:treegen:generateCheckEqMRuleList";
	}

	public static silver.extension.patternmatching.NMatchRule invoke(final Object c_prod, final Object c_l) {
		try {
		final common.DecoratedNode context = new PgenerateCheckEqMRuleList(c_prod, c_l).decorate();
		//matchRule_c(foldpattlist([ lpatt, rpatt ]), '->', zipCheckEqCalls(children, lchildren, rchildren),location=l)
		return (silver.extension.patternmatching.NMatchRule)(((silver.extension.patternmatching.NMatchRule)new silver.extension.patternmatching.PmatchRule_c(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPatternList)silver.extension.treegen.Pfoldpattlist.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.lpatt__ON__silver_extension_treegen_generateCheckEqMRuleList)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.localDecoratedLazy(silver.extension.treegen.Init.rpatt__ON__silver_extension_treegen_generateCheckEqMRuleList)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.patternmatching.TArrow_kwd((new common.StringCatter("->")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NExpr)silver.extension.treegen.PzipCheckEqCalls.invoke(context.localAsIsLazy(silver.extension.treegen.Init.children__ON__silver_extension_treegen_generateCheckEqMRuleList), context.localAsIsLazy(silver.extension.treegen.Init.lchildren__ON__silver_extension_treegen_generateCheckEqMRuleList), context.localAsIsLazy(silver.extension.treegen.Init.rchildren__ON__silver_extension_treegen_generateCheckEqMRuleList))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.treegen.PgenerateCheckEqMRuleList.i_l)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:generateCheckEqMRuleList", t);
		}
	}

	public static final common.NodeFactory<silver.extension.patternmatching.NMatchRule> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.extension.patternmatching.NMatchRule> {
		@Override
		public silver.extension.patternmatching.NMatchRule invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PgenerateCheckEqMRuleList.invoke(children[0], children[1]);
		}
	};
}