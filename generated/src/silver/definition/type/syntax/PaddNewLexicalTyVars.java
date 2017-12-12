
package silver.definition.type.syntax;

public final class PaddNewLexicalTyVars extends common.FunctionNode {

	public static final int i_gn = 0;
	public static final int i_sl = 1;
	public static final int i_l = 2;


	public static final Class<?> childTypes[] = { common.StringCatter.class,core.NLocation.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_syntax_addNewLexicalTyVars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_sl] = new common.Lazy[core.NLocation.num_inh_attrs];

	}

	public PaddNewLexicalTyVars(final Object c_gn, final Object c_sl, final Object c_l) {
		this.child_gn = c_gn;
		this.child_sl = c_sl;
		this.child_l = c_l;

	}

	private Object child_gn;
	public final common.StringCatter getChild_gn() {
		return (common.StringCatter) (child_gn = common.Util.demand(child_gn));
	}

	private Object child_sl;
	public final core.NLocation getChild_sl() {
		return (core.NLocation) (child_sl = common.Util.demand(child_sl));
	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_gn: return getChild_gn();
			case i_sl: return getChild_sl();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_gn: return child_gn;
			case i_sl: return child_sl;
			case i_l: return child_l;

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
		return "silver:definition:type:syntax:addNewLexicalTyVars";
	}

	public static common.ConsCell invoke(final Object c_gn, final Object c_sl, final Object c_l) {
		try {
		final common.DecoratedNode context = new PaddNewLexicalTyVars(c_gn, c_sl, c_l).decorate();
		//if null(l) then [] else (lexTyVarDef(gn, sl, head(l), skolemType(freshTyVar())) :: addNewLexicalTyVars(gn, sl, tail(l)))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.type.syntax.PaddNewLexicalTyVars.i_l))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.env.NDef)silver.definition.env.PlexTyVarDef.invoke(context.childAsIsLazy(silver.definition.type.syntax.PaddNewLexicalTyVars.i_gn), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.syntax.PaddNewLexicalTyVars.i_sl)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.definition.type.syntax.PaddNewLexicalTyVars.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NType)new silver.definition.type.PskolemType(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NTyVar)silver.definition.type.PfreshTyVar.invoke()); } })); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.syntax.PaddNewLexicalTyVars.invoke(context.childAsIsLazy(silver.definition.type.syntax.PaddNewLexicalTyVars.i_gn), common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.definition.type.syntax.PaddNewLexicalTyVars.i_sl)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.type.syntax.PaddNewLexicalTyVars.i_l))); } })); } }))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:syntax:addNewLexicalTyVars", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PaddNewLexicalTyVars.invoke(children[0], children[1], children[2]);
		}
	};
}