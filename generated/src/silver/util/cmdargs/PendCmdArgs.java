
package silver.util.cmdargs;

// top::CmdArgs ::= remaining::[String] 
public final class PendCmdArgs extends silver.util.cmdargs.NCmdArgs {

	public static final int i_remaining = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_util_cmdargs_endCmdArgs;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PendCmdArgs(final Object c_remaining) {
		super();
		this.child_remaining = c_remaining;

	}

	private Object child_remaining;
	public final common.ConsCell getChild_remaining() {
		return (common.ConsCell) (child_remaining = common.Util.demand(child_remaining));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_remaining: return getChild_remaining();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_remaining: return child_remaining;

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
		return false;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		throw new common.exceptions.SilverInternalError("Production silver:util:cmdargs:endCmdArgs erroneously claimed to forward");
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
		return "silver:util:cmdargs:endCmdArgs";
	}

	static void initProductionAttributeDefinitions() {
		// top.cmdRemaining = remaining
		silver.util.cmdargs.PendCmdArgs.synthesizedAttributes[silver.util.cmdargs.Init.silver_util_cmdargs_cmdRemaining__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)context.childAsIs(silver.util.cmdargs.PendCmdArgs.i_remaining)); } };
		// top.cmdError = nothing()
		silver.util.cmdargs.PendCmdArgs.synthesizedAttributes[silver.util.cmdargs.Init.silver_util_cmdargs_cmdError__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NMaybe)new core.Pnothing()); } };

	}

	public static final common.NodeFactory<PendCmdArgs> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PendCmdArgs> {

		@Override
		public PendCmdArgs invoke(final Object[] children, final Object[] annotations) {
			return new PendCmdArgs(children[0]);
		}
	};

}
