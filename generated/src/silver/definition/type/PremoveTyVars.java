
package silver.definition.type;

public final class PremoveTyVars extends common.FunctionNode {

	public static final int i_startswith = 0;
	public static final int i_toremove = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_removeTyVars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PremoveTyVars(final Object c_startswith, final Object c_toremove) {
		this.child_startswith = c_startswith;
		this.child_toremove = c_toremove;

	}

	private Object child_startswith;
	public final common.ConsCell getChild_startswith() {
		return (common.ConsCell) (child_startswith = common.Util.demand(child_startswith));
	}

	private Object child_toremove;
	public final common.ConsCell getChild_toremove() {
		return (common.ConsCell) (child_toremove = common.Util.demand(child_toremove));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_startswith: return getChild_startswith();
			case i_toremove: return getChild_toremove();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_startswith: return child_startswith;
			case i_toremove: return child_toremove;

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
		return "silver:definition:type:removeTyVars";
	}

	public static common.ConsCell invoke(final Object c_startswith, final Object c_toremove) {
		try {
		final common.DecoratedNode context = new PremoveTyVars(c_startswith, c_toremove).decorate();
		//if null(startswith) then [] else if containsTyVar(head(startswith), toremove) then removeTyVars(tail(startswith), toremove) else cons(head(startswith), removeTyVars(tail(startswith), toremove))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.type.PremoveTyVars.i_startswith))) ? ((common.ConsCell)core.Pnil.invoke()) : (((Boolean)silver.definition.type.PcontainsTyVar.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NTyVar)core.Phead.invoke(context.childAsIsLazy(silver.definition.type.PremoveTyVars.i_startswith))); } }, context.childAsIsLazy(silver.definition.type.PremoveTyVars.i_toremove))) ? ((common.ConsCell)silver.definition.type.PremoveTyVars.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.type.PremoveTyVars.i_startswith))); } }, context.childAsIsLazy(silver.definition.type.PremoveTyVars.i_toremove))) : ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NTyVar)core.Phead.invoke(context.childAsIsLazy(silver.definition.type.PremoveTyVars.i_startswith))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.type.PremoveTyVars.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.type.PremoveTyVars.i_startswith))); } }, context.childAsIsLazy(silver.definition.type.PremoveTyVars.i_toremove))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:removeTyVars", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PremoveTyVars.invoke(children[0], children[1]);
		}
	};
}