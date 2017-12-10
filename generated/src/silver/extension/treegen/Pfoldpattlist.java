
package silver.extension.treegen;

public final class Pfoldpattlist extends common.FunctionNode {

	public static final int i_l = 0;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_treegen_foldpattlist;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public Pfoldpattlist(final Object c_l) {
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
		return "silver:extension:treegen:foldpattlist";
	}

	public static silver.extension.patternmatching.NPatternList invoke(final Object c_l) {
		try {
		final common.DecoratedNode context = new Pfoldpattlist(c_l).decorate();
		//if null(l) then patternList_nil(location=loc("generated", -1, -1, -1, -1, -1, -1)) else if null(tail(l)) then patternList_one(head(l),location=head(l).location) else patternList_more(head(l), ',', foldpattlist(tail(l)),location=head(l).location)
		return (silver.extension.patternmatching.NPatternList)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldpattlist.i_l))) ? ((silver.extension.patternmatching.NPatternList)new silver.extension.patternmatching.PpatternList_nil(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)new core.Ploc((new common.StringCatter("generated")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1))); } })) : (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldpattlist.i_l))); } })) ? ((silver.extension.patternmatching.NPatternList)new silver.extension.patternmatching.PpatternList_one(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPattern)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldpattlist.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPattern)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldpattlist.i_l))).getAnno_core_location()); } })) : ((silver.extension.patternmatching.NPatternList)new silver.extension.patternmatching.PpatternList_more(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPattern)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldpattlist.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.patternmatching.NPatternList)silver.extension.treegen.Pfoldpattlist.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldpattlist.i_l))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.extension.patternmatching.NPattern)core.Phead.invoke(context.childAsIsLazy(silver.extension.treegen.Pfoldpattlist.i_l))).getAnno_core_location()); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:treegen:foldpattlist", t);
		}
	}

	public static final common.NodeFactory<silver.extension.patternmatching.NPatternList> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.extension.patternmatching.NPatternList> {
		@Override
		public silver.extension.patternmatching.NPatternList invoke(final Object[] children, final Object[] namedNotApplicable) {
			return Pfoldpattlist.invoke(children[0]);
		}
	};
}