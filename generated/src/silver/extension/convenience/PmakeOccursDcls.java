
package silver.extension.convenience;

public final class PmakeOccursDcls extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_ats = 1;
	public static final int i_nts = 2;


	public static final Class<?> childTypes[] = { core.NLocation.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_makeOccursDcls;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PmakeOccursDcls(final Object c_l, final Object c_ats, final Object c_nts) {
		this.child_l = c_l;
		this.child_ats = c_ats;
		this.child_nts = c_nts;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_ats;
	public final common.ConsCell getChild_ats() {
		return (common.ConsCell) (child_ats = common.Util.demand(child_ats));
	}

	private Object child_nts;
	public final common.ConsCell getChild_nts() {
		return (common.ConsCell) (child_nts = common.Util.demand(child_nts));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_ats: return getChild_ats();
			case i_nts: return getChild_nts();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_ats: return child_ats;
			case i_nts: return child_nts;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 3;
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
		return "silver:extension:convenience:makeOccursDcls";
	}

	public static silver.definition.core.NAGDcl invoke(final Object c_l, final Object c_ats, final Object c_nts) {
		try {
		final common.DecoratedNode context = new PmakeOccursDcls(c_l, c_ats, c_nts).decorate();
		//if null(ats) then emptyAGDcl(location=l) else appendAGDcl(makeOccursDclsHelp(l, head(ats), nts), makeOccursDcls(l, tail(ats), nts),location=l)
		return (silver.definition.core.NAGDcl)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.convenience.PmakeOccursDcls.i_ats))) ? ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PmakeOccursDcls.i_l)))) : ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)silver.extension.convenience.PmakeOccursDclsHelp.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PmakeOccursDcls.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.extension.convenience.NQNameWithTL)core.Phead.invoke(context.childAsIsLazy(silver.extension.convenience.PmakeOccursDcls.i_ats))); } }, context.childAsIsLazy(silver.extension.convenience.PmakeOccursDcls.i_nts))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)silver.extension.convenience.PmakeOccursDcls.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PmakeOccursDcls.i_l)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.convenience.PmakeOccursDcls.i_ats))); } }, context.childAsIsLazy(silver.extension.convenience.PmakeOccursDcls.i_nts))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PmakeOccursDcls.i_l))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:convenience:makeOccursDcls", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NAGDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NAGDcl> {
		@Override
		public silver.definition.core.NAGDcl invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeOccursDcls.invoke(children[0], children[1], children[2]);
		}
	};
}