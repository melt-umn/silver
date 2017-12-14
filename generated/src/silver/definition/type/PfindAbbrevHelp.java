
package silver.definition.type;

public final class PfindAbbrevHelp extends common.FunctionNode {

	public static final int i_tv = 0;
	public static final int i_bv = 1;
	public static final int i_vn = 2;


	public static final Class<?> childTypes[] = { silver.definition.type.NTyVar.class,common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_findAbbrevHelp;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_tv] = new common.Lazy[silver.definition.type.NTyVar.num_inh_attrs];

	}

	public PfindAbbrevHelp(final Object c_tv, final Object c_bv, final Object c_vn) {
		this.child_tv = c_tv;
		this.child_bv = c_bv;
		this.child_vn = c_vn;

	}

	private Object child_tv;
	public final silver.definition.type.NTyVar getChild_tv() {
		return (silver.definition.type.NTyVar) (child_tv = common.Util.demand(child_tv));
	}

	private Object child_bv;
	public final common.ConsCell getChild_bv() {
		return (common.ConsCell) (child_bv = common.Util.demand(child_bv));
	}

	private Object child_vn;
	public final common.ConsCell getChild_vn() {
		return (common.ConsCell) (child_vn = common.Util.demand(child_vn));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tv: return getChild_tv();
			case i_bv: return getChild_bv();
			case i_vn: return getChild_vn();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tv: return child_tv;
			case i_bv: return child_bv;
			case i_vn: return child_vn;

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
		return "silver:definition:type:findAbbrevHelp";
	}

	public static common.StringCatter invoke(final Object c_tv, final Object c_bv, final Object c_vn) {
		try {
		final common.DecoratedNode context = new PfindAbbrevHelp(c_tv, c_bv, c_vn).decorate();
		//if null(vn) || null(bv) then "V_" ++ toString(tvi) else if tyVarEqual(tv, head(bv)) then head(vn) else findAbbrevHelp(tv, tail(bv), tail(vn))
		return (common.StringCatter)(((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.type.PfindAbbrevHelp.i_vn))) || ((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.type.PfindAbbrevHelp.i_bv)))) ? new common.StringCatter((common.StringCatter)(new common.StringCatter("V_")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.localAsIs(silver.definition.type.Init.tvi__ON__silver_definition_type_findAbbrevHelp))))) : (((Boolean)silver.definition.type.PtyVarEqual.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PfindAbbrevHelp.i_tv)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NTyVar)core.Phead.invoke(context.childAsIsLazy(silver.definition.type.PfindAbbrevHelp.i_bv))); } })) ? ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.definition.type.PfindAbbrevHelp.i_vn))) : ((common.StringCatter)silver.definition.type.PfindAbbrevHelp.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.PfindAbbrevHelp.i_tv)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.type.PfindAbbrevHelp.i_bv))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.type.PfindAbbrevHelp.i_vn))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:findAbbrevHelp", t);
		}
	}

	public static final common.NodeFactory<common.StringCatter> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.StringCatter> {
		@Override
		public common.StringCatter invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PfindAbbrevHelp.invoke(children[0], children[1], children[2]);
		}
	};
}