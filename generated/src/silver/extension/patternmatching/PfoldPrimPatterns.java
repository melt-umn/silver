
package silver.extension.patternmatching;

public final class PfoldPrimPatterns extends common.FunctionNode {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_foldPrimPatterns;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PfoldPrimPatterns(final Object c_l) {
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
		return "silver:extension:patternmatching:foldPrimPatterns";
	}

	public static silver.modification.primitivepattern.NPrimPatterns invoke(final Object c_l) {
		try {
		final common.DecoratedNode context = new PfoldPrimPatterns(c_l).decorate();
		//if null(tail(l)) then onePattern(head(l),location=head(l).location) else consPattern(head(l), '|', foldPrimPatterns(tail(l)),location=head(l).location)
		return (silver.modification.primitivepattern.NPrimPatterns)((((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.patternmatching.PfoldPrimPatterns.i_l))); } })) ? ((silver.modification.primitivepattern.NPrimPatterns)new silver.modification.primitivepattern.PonePattern(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.primitivepattern.NPrimPattern)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PfoldPrimPatterns.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.primitivepattern.NPrimPattern)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PfoldPrimPatterns.i_l))).getAnno_core_location()); } })) : ((silver.modification.primitivepattern.NPrimPatterns)new silver.modification.primitivepattern.PconsPattern(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.primitivepattern.NPrimPattern)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PfoldPrimPatterns.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.extension.patternmatching.TVbar_kwd((new common.StringCatter("|")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.primitivepattern.NPrimPatterns)silver.extension.patternmatching.PfoldPrimPatterns.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.patternmatching.PfoldPrimPatterns.i_l))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.modification.primitivepattern.NPrimPattern)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PfoldPrimPatterns.i_l))).getAnno_core_location()); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:patternmatching:foldPrimPatterns", t);
		}
	}

	public static final common.NodeFactory<silver.modification.primitivepattern.NPrimPatterns> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.modification.primitivepattern.NPrimPatterns> {
		@Override
		public silver.modification.primitivepattern.NPrimPatterns invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfoldPrimPatterns.invoke(children[0]);
		}
	};
}