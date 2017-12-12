
package silver.extension.treegen;

public final class Pfoldmrl extends common.FunctionNode {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_foldmrl;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pfoldmrl(final Object c_l) {
		this.child_l = c_l;

	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return "silver:extension:treegen:foldmrl";
	}

	public static silver.extension.patternmatching.NMRuleList invoke(final Object c_l) {
		try {
		final common.DecoratedNode context = new Pfoldmrl(c_l).decorate();
		//if null(l) then error("doh") else if null(tail(l)) then mRuleList_one(head(l),location=head(l).location) else mRuleList_cons(head(l), '|', foldmrl(tail(l)),location=head(l).location)
		return (silver.extension.patternmatching.NMRuleList)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldmrl.i_l))) ? ((silver.extension.patternmatching.NMRuleList)core.Perror.invoke((new common.StringCatter("doh")))) : (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldmrl.i_l))); } })) ? ((silver.extension.patternmatching.NMRuleList)new silver.extension.patternmatching.PmRuleList_one(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NMatchRule)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldmrl.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NMatchRule)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldmrl.i_l))).getAnno_core_location()); } })) : ((silver.extension.patternmatching.NMRuleList)new silver.extension.patternmatching.PmRuleList_cons(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NMatchRule)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldmrl.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.patternmatching.TVbar_kwd((new common.StringCatter("|")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NMRuleList)silver.extension.treegen.Pfoldmrl.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldmrl.i_l))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NMatchRule)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldmrl.i_l))).getAnno_core_location()); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:foldmrl", t);
		}
	}

	public static final common.NodeFactory<silver.extension.patternmatching.NMRuleList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.extension.patternmatching.NMRuleList> {
		@Override
		public silver.extension.patternmatching.NMRuleList invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pfoldmrl.invoke(children[0]);
		}
	};
}