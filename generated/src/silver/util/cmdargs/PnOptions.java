
package silver.util.cmdargs;

// top::Flag ::= n::Integer ast::(CmdArgs ::= [String] CmdArgs) 
public final class PnOptions extends silver.util.cmdargs.NFlag {

	public static final int i_n = 0;
	public static final int i_ast = 1;


	public static final Class<?> childTypes[] = {Integer.class,common.NodeFactory.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_util_cmdargs_nOptions;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.util.cmdargs.NFlag.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.util.cmdargs.NFlag.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PnOptions(final Object c_n, final Object c_ast) {
		super();
		this.child_n = c_n;
		this.child_ast = c_ast;

	}

	private Object child_n;
	public final Integer getChild_n() {
		return (Integer) (child_n = common.Util.demand(child_n));
	}

	private Object child_ast;
	public final common.NodeFactory<silver.util.cmdargs.NCmdArgs> getChild_ast() {
		return (common.NodeFactory<silver.util.cmdargs.NCmdArgs>) (child_ast = common.Util.demand(child_ast));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_n: return getChild_n();
			case i_ast: return getChild_ast();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_n: return child_n;
			case i_ast: return child_ast;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 2;
	}

	@Override
	public common.Lazy getSynthesized(final int index) {
		return synthesizedAttributes[index];
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
	public boolean hasForward() {
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:util:cmdargs:nOptions erroneously claimed to forward");
	}

	@Override
	public common.Lazy getForwardInheritedAttributes(final int index) {
		return forwardInheritedAttributes[index];
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
		return "silver:util:cmdargs:nOptions";
	}

	static void initProductionAttributeDefinitions() {
		// top.flagOutput = if length(top.flagInput) <= n then [] else drop(n, top.flagInput)
		silver.util.cmdargs.PnOptions.synthesizedAttributes[silver.util.cmdargs.Init.silver_util_cmdargs_flagOutput__ON__silver_util_cmdargs_Flag] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(context.contextInheritedLazy(silver.util.cmdargs.Init.silver_util_cmdargs_flagInput__ON__silver_util_cmdargs_Flag))) <= ((Integer)context.childAsIs(silver.util.cmdargs.PnOptions.i_n))) ? ((common.ConsCell)core.Pnil.invoke()) : ((common.ConsCell)core.Pdrop.invoke(context.childAsIsLazy(silver.util.cmdargs.PnOptions.i_n), context.contextInheritedLazy(silver.util.cmdargs.Init.silver_util_cmdargs_flagInput__ON__silver_util_cmdargs_Flag)))); } };
		// top.flagModified = if length(top.flagInput) <= n then errorCmdArgs(head(top.flagInput) ++ " only has " ++ toString(length(top.flagInput) - 1) ++ " parameters, when it should have " ++ toString(n)) else ast(take(n, top.flagInput), top.flagOriginal)
		silver.util.cmdargs.PnOptions.synthesizedAttributes[silver.util.cmdargs.Init.silver_util_cmdargs_flagModified__ON__silver_util_cmdargs_Flag] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((((Integer)core.PlistLength.invoke(context.contextInheritedLazy(silver.util.cmdargs.Init.silver_util_cmdargs_flagInput__ON__silver_util_cmdargs_Flag))) <= ((Integer)context.childAsIs(silver.util.cmdargs.PnOptions.i_n))) ? ((silver.util.cmdargs.NCmdArgs)new silver.util.cmdargs.PerrorCmdArgs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new common.StringCatter((common.StringCatter)((common.StringCatter)core.Phead.invoke(context.contextInheritedLazy(silver.util.cmdargs.Init.silver_util_cmdargs_flagInput__ON__silver_util_cmdargs_Flag))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" only has ")), (common.StringCatter)new common.StringCatter((common.StringCatter)new common.StringCatter(String.valueOf(Integer.valueOf(((Integer)core.PlistLength.invoke(context.contextInheritedLazy(silver.util.cmdargs.Init.silver_util_cmdargs_flagInput__ON__silver_util_cmdargs_Flag))) - Integer.valueOf((int)1)))), (common.StringCatter)new common.StringCatter((common.StringCatter)(new common.StringCatter(" parameters, when it should have ")), (common.StringCatter)new common.StringCatter(String.valueOf(((Integer)context.childAsIs(silver.util.cmdargs.PnOptions.i_n)))))))); } })) : ((silver.util.cmdargs.NCmdArgs)((common.NodeFactory<silver.util.cmdargs.NCmdArgs>)context.childAsIs(silver.util.cmdargs.PnOptions.i_ast)).invoke(new Object[]{new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptake.invoke(context.childAsIsLazy(silver.util.cmdargs.PnOptions.i_n), context.contextInheritedLazy(silver.util.cmdargs.Init.silver_util_cmdargs_flagInput__ON__silver_util_cmdargs_Flag))); } }, context.contextInheritedLazy(silver.util.cmdargs.Init.silver_util_cmdargs_flagOriginal__ON__silver_util_cmdargs_Flag)}, null))); } };

	}

	public static final common.NodeFactory<PnOptions> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PnOptions> {

		@Override
		public PnOptions invoke(final Object[] children, final Object[] annotations) {
			return new PnOptions(children[0], children[1]);
		}
	};

}
