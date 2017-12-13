
package silver.definition.core;

public final class PtriggeredGrammars extends common.FunctionNode {

	public static final int i_grammarDependencies = 0;
	public static final int i_trig = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_core_triggeredGrammars;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PtriggeredGrammars(final Object c_grammarDependencies, final Object c_trig) {
		this.child_grammarDependencies = c_grammarDependencies;
		this.child_trig = c_trig;

	}

	private Object child_grammarDependencies;
	public final common.ConsCell getChild_grammarDependencies() {
		return (common.ConsCell) (child_grammarDependencies = common.Util.demand(child_grammarDependencies));
	}

	private Object child_trig;
	public final common.ConsCell getChild_trig() {
		return (common.ConsCell) (child_trig = common.Util.demand(child_trig));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_grammarDependencies: return getChild_grammarDependencies();
			case i_trig: return getChild_trig();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_grammarDependencies: return child_grammarDependencies;
			case i_trig: return child_trig;

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
		return "silver:definition:core:triggeredGrammars";
	}

	public static common.ConsCell invoke(final Object c_grammarDependencies, final Object c_trig) {
		try {
		final common.DecoratedNode context = new PtriggeredGrammars(c_grammarDependencies, c_trig).decorate();
		//if null(trig) then [] else if contains(head(tail(head(trig))), grammarDependencies) then (head(head(trig)) :: triggeredGrammars(grammarDependencies, tail(trig))) else triggeredGrammars(grammarDependencies, tail(trig))
		return (common.ConsCell)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.core.PtriggeredGrammars.i_trig))) ? ((common.ConsCell)core.Pnil.invoke()) : (((Boolean)silver.util.Pcontains.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.childAsIsLazy(silver.definition.core.PtriggeredGrammars.i_trig))); } })); } })); } }, context.childAsIsLazy(silver.definition.core.PtriggeredGrammars.i_grammarDependencies))) ? ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Phead.invoke(context.childAsIsLazy(silver.definition.core.PtriggeredGrammars.i_trig))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)silver.definition.core.PtriggeredGrammars.invoke(context.childAsIsLazy(silver.definition.core.PtriggeredGrammars.i_grammarDependencies), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.core.PtriggeredGrammars.i_trig))); } })); } })) : ((common.ConsCell)silver.definition.core.PtriggeredGrammars.invoke(context.childAsIsLazy(silver.definition.core.PtriggeredGrammars.i_grammarDependencies), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.core.PtriggeredGrammars.i_trig))); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:core:triggeredGrammars", t);
		}
	}

	public static final common.NodeFactory<common.ConsCell> factory = new Factory();

	public static final class Factory extends common.NodeFactory<common.ConsCell> {
		@Override
		public common.ConsCell invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PtriggeredGrammars.invoke(children[0], children[1]);
		}
	};
}