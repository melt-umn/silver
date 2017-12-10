
package silver.util.cmdargs;

public final class PinterpretCmdArgs extends common.FunctionNode {

	public static final int i_flags = 0;
	public static final int i_input = 1;


	public static final Class<?> childTypes[] = { common.DecoratedNode.class,common.DecoratedNode.class };

	public static final int num_local_attrs = Init.count_local__ON__silver_util_cmdargs_interpretCmdArgs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static{

	}

	public PinterpretCmdArgs(final Object c_flags, final Object c_input) {
		this.child_flags = c_flags;
		this.child_input = c_input;

	}

	private Object child_flags;
	public final common.ConsCell getChild_flags() {
		return (common.ConsCell) (child_flags = common.Util.demand(child_flags));
	}

	private Object child_input;
	public final common.ConsCell getChild_input() {
		return (common.ConsCell) (child_input = common.Util.demand(child_input));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_flags: return getChild_flags();
			case i_input: return getChild_input();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_flags: return child_flags;
			case i_input: return child_input;

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
		return "silver:util:cmdargs:interpretCmdArgs";
	}

	public static silver.util.cmdargs.NCmdArgs invoke(final Object c_flags, final Object c_input) {
		try {
		final common.DecoratedNode context = new PinterpretCmdArgs(c_flags, c_input).decorate();
		//if null(input) then endCmdArgs([]) else if ! l.isJust then if startsWith("-", head(input)) then errorCmdArgs("Unrecognized flag: " ++ head(input)) else endCmdArgs(input) else here.flagModified
		return (silver.util.cmdargs.NCmdArgs)((((Boolean)core.Pnull.invoke(context.childAsIsLazy(silver.util.cmdargs.PinterpretCmdArgs.i_input))) ? ((silver.util.cmdargs.NCmdArgs)new silver.util.cmdargs.PendCmdArgs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } })) : ((!((Boolean)context.localDecorated(silver.util.cmdargs.Init.l__ON__silver_util_cmdargs_interpretCmdArgs).synthesized(core.Init.core_isJust__ON__core_Maybe))) ? (((Boolean)core.PstartsWith.invoke((new common.StringCatter("-")), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.util.cmdargs.PinterpretCmdArgs.i_input))); } })) ? ((silver.util.cmdargs.NCmdArgs)new silver.util.cmdargs.PerrorCmdArgs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)(new common.StringCatter("Unrecognized flag: ")), (common.StringCatter)((common.StringCatter)core.Phead.invoke(context.childAsIsLazy(silver.util.cmdargs.PinterpretCmdArgs.i_input)))); } })) : ((silver.util.cmdargs.NCmdArgs)new silver.util.cmdargs.PendCmdArgs(context.childAsIsLazy(silver.util.cmdargs.PinterpretCmdArgs.i_input)))) : ((silver.util.cmdargs.NCmdArgs)context.localDecorated(silver.util.cmdargs.Init.here__ON__silver_util_cmdargs_interpretCmdArgs).synthesized(silver.util.cmdargs.Init.silver_util_cmdargs_flagModified__ON__silver_util_cmdargs_Flag)))));

		} catch(Throwable t) {
			throw new common.exceptions.TraceException("Error while evaluating function silver:util:cmdargs:interpretCmdArgs", t);
		}
	}

	public static final common.NodeFactory<silver.util.cmdargs.NCmdArgs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<silver.util.cmdargs.NCmdArgs> {
		@Override
		public silver.util.cmdargs.NCmdArgs invoke(final Object[] children, final Object[] namedNotApplicable) {
			return PinterpretCmdArgs.invoke(children[0], children[1]);
		}
	};
}