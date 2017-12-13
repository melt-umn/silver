
package silver.extension.convenience;

public final class PmakeOccursDclsHelp extends common.FunctionNode {

	public static final int i_l = 0;
	public static final int i_at = 1;
	public static final int i_nts = 2;


	public static final Class<?> childTypes[] = { core.NLocation.class,silver.extension.convenience.NQNameWithTL.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_extension_convenience_makeOccursDclsHelp;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_l] = new common.Lazy[core.NLocation.num_inh_attrs];
	childInheritedAttributes[i_at] = new common.Lazy[silver.extension.convenience.NQNameWithTL.num_inh_attrs];

	}

	public PmakeOccursDclsHelp(final Object c_l, final Object c_at, final Object c_nts) {
		this.child_l = c_l;
		this.child_at = c_at;
		this.child_nts = c_nts;

	}

	private Object child_l;
	public final core.NLocation getChild_l() {
		return (core.NLocation) (child_l = common.Util.demand(child_l));
	}

	private Object child_at;
	public final silver.extension.convenience.NQNameWithTL getChild_at() {
		return (silver.extension.convenience.NQNameWithTL) (child_at = common.Util.demand(child_at));
	}

	private Object child_nts;
	public final common.ConsCell getChild_nts() {
		return (common.ConsCell) (child_nts = common.Util.demand(child_nts));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_l: return getChild_l();
			case i_at: return getChild_at();
			case i_nts: return getChild_nts();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_l: return child_l;
			case i_at: return child_at;
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
		return "silver:extension:convenience:makeOccursDclsHelp";
	}

	public static silver.definition.core.NAGDcl invoke(final Object c_l, final Object c_at, final Object c_nts) {
		try {
		final common.DecoratedNode context = new PmakeOccursDclsHelp(c_l, c_at, c_nts).decorate();
		//if null(nts) then emptyAGDcl(location=l) else appendAGDcl(attributionDcl('attribute', at.qnwtQN, at.qnwtTL, 'occurs', 'on', head(nts).qnwtQN, head(nts).qnwtTL, ';',location=l), makeOccursDclsHelp(l, at, tail(nts)),location=l)
		return (silver.definition.core.NAGDcl)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.extension.convenience.PmakeOccursDclsHelp.i_nts))) ? ((silver.definition.core.NAGDcl)new silver.definition.core.PemptyAGDcl(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PmakeOccursDclsHelp.i_l)))) : ((silver.definition.core.NAGDcl)new silver.definition.core.PappendAGDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)new silver.definition.core.PattributionDcl(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TAttribute_kwd((new common.StringCatter("attribute")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, context.childDecoratedSynthesizedLazy(silver.extension.convenience.PmakeOccursDclsHelp.i_at, silver.extension.convenience.Init.silver_extension_convenience_qnwtQN__ON__silver_extension_convenience_QNameWithTL), context.childDecoratedSynthesizedLazy(silver.extension.convenience.PmakeOccursDclsHelp.i_at, silver.extension.convenience.Init.silver_extension_convenience_qnwtTL__ON__silver_extension_convenience_QNameWithTL), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TOccurs_kwd((new common.StringCatter("occurs")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TOn_kwd((new common.StringCatter("on")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NQName)((silver.extension.convenience.NQNameWithTL)core.Phead.invoke(context.childAsIsLazy(silver.extension.convenience.PmakeOccursDclsHelp.i_nts))).decorate(context, (common.Lazy[])null).synthesized(silver.extension.convenience.Init.silver_extension_convenience_qnwtQN__ON__silver_extension_convenience_QNameWithTL)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.syntax.NBracketedOptTypeExprs)((silver.extension.convenience.NQNameWithTL)core.Phead.invoke(context.childAsIsLazy(silver.extension.convenience.PmakeOccursDclsHelp.i_nts))).decorate(context, (common.Lazy[])null).synthesized(silver.extension.convenience.Init.silver_extension_convenience_qnwtTL__ON__silver_extension_convenience_QNameWithTL)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.definition.core.TSemi_t((new common.StringCatter(";")), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PmakeOccursDclsHelp.i_l)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.core.NAGDcl)silver.extension.convenience.PmakeOccursDclsHelp.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PmakeOccursDclsHelp.i_l)), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PmakeOccursDclsHelp.i_at)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.extension.convenience.PmakeOccursDclsHelp.i_nts))); } })); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.extension.convenience.PmakeOccursDclsHelp.i_l))))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:extension:convenience:makeOccursDclsHelp", t);
		}
	}

	public static final common.NodeFactory<silver.definition.core.NAGDcl> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.definition.core.NAGDcl> {
		@Override
		public silver.definition.core.NAGDcl invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PmakeOccursDclsHelp.invoke(children[0], children[1], children[2]);
		}
	};
}