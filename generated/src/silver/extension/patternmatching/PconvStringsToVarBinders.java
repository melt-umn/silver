
package silver.extension.patternmatching;

public final class PconvStringsToVarBinders extends common.FunctionNode {

	public static final int i_s = 0;
	public static final int i_l = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,core.NLocation.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_patternmatching_convStringsToVarBinders;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PconvStringsToVarBinders(final Object c_s, final Object c_l) {
		this.child_s = c_s;
		this.child_l = c_l;

	}

	private Object child_s;
	public final common.ConsCell getChild_s() {
		return (common.ConsCell) (child_s = common.Util.demand(child_s));
	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;
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
		return "silver:extension:patternmatching:convStringsToVarBinders";
	}

	public static silver.modification.primitivepattern.NVarBinders invoke(final Object c_s, final Object c_l) {
		try {
		final common.DecoratedNode context = new PconvStringsToVarBinders(c_s, c_l).decorate();
		//if null(s) then nilVarBinder(location=l) else if null(tail(s)) then oneVarBinder(varVarBinder(head(s),location=head(s).location),location=l) else consVarBinder(varVarBinder(head(s),location=head(s).location), ',', convStringsToVarBinders(tail(s), l),location=l)
		return (silver.modification.primitivepattern.NVarBinders)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.patternmatching.PconvStringsToVarBinders.i_s))) ? ((silver.modification.primitivepattern.NVarBinders)new silver.modification.primitivepattern.PnilVarBinder(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PconvStringsToVarBinders.i_l)))) : (((Boolean)core.Pnull.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.patternmatching.PconvStringsToVarBinders.i_s))); } })) ? ((silver.modification.primitivepattern.NVarBinders)new silver.modification.primitivepattern.PoneVarBinder(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.primitivepattern.NVarBinder)new silver.modification.primitivepattern.PvarVarBinder(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PconvStringsToVarBinders.i_s))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PconvStringsToVarBinders.i_s))).getAnno_core_location()); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PconvStringsToVarBinders.i_l)))) : ((silver.modification.primitivepattern.NVarBinders)new silver.modification.primitivepattern.PconsVarBinder(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.primitivepattern.NVarBinder)new silver.modification.primitivepattern.PvarVarBinder(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NName)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PconvStringsToVarBinders.i_s))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NLocation)((silver.definition.core.NName)core.Phead.invoke(context.childAsIsLazy(silver.extension.patternmatching.PconvStringsToVarBinders.i_s))).getAnno_core_location()); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TComma_t((new common.StringCatter(",")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.modification.primitivepattern.NVarBinders)silver.extension.patternmatching.PconvStringsToVarBinders.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.patternmatching.PconvStringsToVarBinders.i_s))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PconvStringsToVarBinders.i_l)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.patternmatching.PconvStringsToVarBinders.i_l)))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:patternmatching:convStringsToVarBinders", t);
		}
	}

	public static final common.NodeFactory<silver.modification.primitivepattern.NVarBinders> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.modification.primitivepattern.NVarBinders> {
		@Override
		public silver.modification.primitivepattern.NVarBinders invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PconvStringsToVarBinders.invoke(children[0], children[1]);
		}
	};
}