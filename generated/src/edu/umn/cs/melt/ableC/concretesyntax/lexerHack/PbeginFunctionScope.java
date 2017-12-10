
package edu.umn.cs.melt.ableC.concretesyntax.lexerHack;

public final class PbeginFunctionScope extends common.FunctionNode {

	public static final int i_funName = 0;
	public static final int i_paramNames = 1;
	public static final int i_context = 2;


	public static final Class<?> childTypes[] = { edu.umn.cs.melt.ableC.abstractsyntax.host.NName.class,core.NMaybe.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__edu_umn_cs_melt_ableC_concretesyntax_lexerHack_beginFunctionScope;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[3][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{
	childInheritedAttributes[i_funName] = new common.Lazy[edu.umn.cs.melt.ableC.abstractsyntax.host.NName.num_inh_attrs];
	childInheritedAttributes[i_paramNames] = new common.Lazy[core.NMaybe.num_inh_attrs];

	}

	public PbeginFunctionScope(final Object c_funName, final Object c_paramNames, final Object c_context) {
		this.child_funName = c_funName;
		this.child_paramNames = c_paramNames;
		this.child_context = c_context;

	}

	private Object child_funName;
	public final edu.umn.cs.melt.ableC.abstractsyntax.host.NName getChild_funName() {
		return (edu.umn.cs.melt.ableC.abstractsyntax.host.NName) (child_funName = common.Util.demand(child_funName));
	}

	private Object child_paramNames;
	public final core.NMaybe getChild_paramNames() {
		return (core.NMaybe) (child_paramNames = common.Util.demand(child_paramNames));
	}

	private Object child_context;
	public final common.ConsCell getChild_context() {
		return (common.ConsCell) (child_context = common.Util.demand(child_context));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_funName: return getChild_funName();
			case i_paramNames: return getChild_paramNames();
			case i_context: return getChild_context();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_funName: return child_funName;
			case i_paramNames: return child_paramNames;
			case i_context: return child_context;

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
		return "edu:umn:cs:melt:ableC:concretesyntax:lexerHack:beginFunctionScope";
	}

	public static common.ConsCell invoke(final Object c_funName, final Object c_paramNames, final Object c_context) {
		try {
		final common.DecoratedNode context = new PbeginFunctionScope(c_funName, c_paramNames, c_context).decorate();
		//addIdentsToScope(fromMaybe([], paramNames,), openScope(addIdentsToScope([ funName ], context,),),)
		return (common.ConsCell)(((common.ConsCell)edu.umn.cs.melt.ableC.concretesyntax.lexerHack.PaddIdentsToScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.PfromMaybe.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }, common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.concretesyntax.lexerHack.PbeginFunctionScope.i_paramNames)))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.concretesyntax.lexerHack.PopenScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)edu.umn.cs.melt.ableC.concretesyntax.lexerHack.PaddIdentsToScope.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(common.Thunk.transformUndecorate(context.childDecoratedLazy(edu.umn.cs.melt.ableC.concretesyntax.lexerHack.PbeginFunctionScope.i_funName)), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })); } }, context.childAsIsLazy(edu.umn.cs.melt.ableC.concretesyntax.lexerHack.PbeginFunctionScope.i_context))); } })); } })));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function edu:umn:cs:melt:ableC:concretesyntax:lexerHack:beginFunctionScope", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PbeginFunctionScope.invoke(children[0], children[1], children[2]);
		}
	};
}