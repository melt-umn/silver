
package silver.definition.type;

public final class PextractNamedArg extends common.FunctionNode {

	public static final int i_n = 0;
	public static final int i_l = 1;


	public static final Class<?> childTypes[] = { common.StringCatter.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_definition_type_extractNamedArg;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PextractNamedArg(final Object c_n, final Object c_l) {
		this.child_n = c_n;
		this.child_l = c_l;

	}

	private Object child_n;
	public final common.StringCatter getChild_n() {
		return (common.StringCatter) (child_n = common.Util.demand(child_n));
	}

	private Object child_l;
	public final common.ConsCell getChild_l() {
		return (common.ConsCell) (child_l = common.Util.demand(child_l));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_l: return getChild_l();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
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
		return "silver:definition:type:extractNamedArg";
	}

	public static core.NPair invoke(final Object c_n, final Object c_l) {
		try {
		final common.DecoratedNode context = new PextractNamedArg(c_n, c_l).decorate();
		//if null(l) then pair(nothing(), []) else if head(l).argName == n then pair(just(head(l)), tail(l)) else pair(recurse.fst, (head(l) :: recurse.snd))
		return (core.NPair)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.definition.type.PextractNamedArg.i_l))) ? ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : (((common.StringCatter)((silver.definition.type.NNamedArgType)core.Phead.invoke(context.childAsIsLazy(silver.definition.type.PextractNamedArg.i_l))).decorate(context, (common.Lazy[])null).synthesized(silver.definition.type.Init.silver_definition_type_argName__ON__silver_definition_type_NamedArgType)).equals(((common.StringCatter)context.childAsIs(silver.definition.type.PextractNamedArg.i_n))) ? ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NNamedArgType)core.Phead.invoke(context.childAsIsLazy(silver.definition.type.PextractNamedArg.i_l))); } })); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.childAsIsLazy(silver.definition.type.PextractNamedArg.i_l))); } })) : ((core.NPair)new core.Ppair(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((core.NMaybe)context.localDecorated(silver.definition.type.Init.recurse__ON__silver_definition_type_extractNamedArg).synthesized(core.Init.core_fst__ON__core_Pair)); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((silver.definition.type.NNamedArgType)core.Phead.invoke(context.childAsIsLazy(silver.definition.type.PextractNamedArg.i_l))); } }, new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.localDecorated(silver.definition.type.Init.recurse__ON__silver_definition_type_extractNamedArg).synthesized(core.Init.core_snd__ON__core_Pair)); } })); } })))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:definition:type:extractNamedArg", t);
		}
	}

	public static final common.NodeFactory<core.NPair> factory = new Factory();

	public static final class Factory extends common.NodeFactory<core.NPair> {
		@Override
		public core.NPair invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PextractNamedArg.invoke(children[0], children[1]);
		}
	};
}