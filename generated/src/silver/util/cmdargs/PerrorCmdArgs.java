
package silver.util.cmdargs;

// top::CmdArgs ::= errmsg::String 
public final class PerrorCmdArgs extends silver.util.cmdargs.NCmdArgs {

	public static final int i_errmsg = 0;


	public static final Class<?> childTypes[] = {common.StringCatter.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_util_cmdargs_errorCmdArgs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PerrorCmdArgs(final Object c_errmsg) {
		super();
		this.child_errmsg = c_errmsg;

	}

	private Object child_errmsg;
	public final common.StringCatter getChild_errmsg() {
		return (common.StringCatter) (child_errmsg = common.Util.demand(child_errmsg));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_errmsg: return getChild_errmsg();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_errmsg: return child_errmsg;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 1;
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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return ((silver.util.cmdargs.NCmdArgs)new silver.util.cmdargs.PendCmdArgs(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } }));
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
		return "silver:util:cmdargs:errorCmdArgs";
	}

	static void initProductionAttributeDefinitions() {
		// top.cmdRemaining = []
		silver.util.cmdargs.PerrorCmdArgs.synthesizedAttributes[silver.util.cmdargs.Init.silver_util_cmdargs_cmdRemaining__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pnil.invoke()); } };
		// top.cmdError = just(errmsg)
		silver.util.cmdargs.PerrorCmdArgs.synthesizedAttributes[silver.util.cmdargs.Init.silver_util_cmdargs_cmdError__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pjust(context.childAsIsLazy(silver.util.cmdargs.PerrorCmdArgs.i_errmsg))); } };

	}

	public static final common.NodeFactory<PerrorCmdArgs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PerrorCmdArgs> {

		@Override
		public PerrorCmdArgs invoke(final Object[] children, final Object[] annotations) {
			return new PerrorCmdArgs(children[0]);
		}
	};

}
