
package silver.util.cmdargs;

// top::Flag ::= ast::(CmdArgs ::= CmdArgs) 
public final class Pflag extends silver.util.cmdargs.NFlag {

	public static final int i_ast = 0;


	public static final Class<?> childTypes[] = {common.NodeFactory.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_util_cmdargs_flag;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.util.cmdargs.NFlag.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.util.cmdargs.NFlag.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pflag(final Object c_ast) {
		super();
		this.child_ast = c_ast;

	}

	private Object child_ast;
	public final common.NodeFactory<silver.util.cmdargs.NCmdArgs> getChild_ast() {
		return (common.NodeFactory<silver.util.cmdargs.NCmdArgs>) (child_ast = common.Util.demand(child_ast));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ast: return getChild_ast();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ast: return child_ast;

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
		throw new common.exceptions.SilverInternalError("Production silver:util:cmdargs:flag erroneously claimed to forward");
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
		return "silver:util:cmdargs:flag";
	}

	static void initProductionAttributeDefinitions() {
		// top.flagOutput = tail(top.flagInput)
		silver.util.cmdargs.Pflag.synthesizedAttributes[silver.util.cmdargs.Init.silver_util_cmdargs_flagOutput__ON__silver_util_cmdargs_Flag] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Ptail.invoke(context.contextInheritedLazy(silver.util.cmdargs.Init.silver_util_cmdargs_flagInput__ON__silver_util_cmdargs_Flag))); } };
		// top.flagModified = ast(top.flagOriginal)
		silver.util.cmdargs.Pflag.synthesizedAttributes[silver.util.cmdargs.Init.silver_util_cmdargs_flagModified__ON__silver_util_cmdargs_Flag] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver.util.cmdargs.NCmdArgs)((common.NodeFactory<silver.util.cmdargs.NCmdArgs>)context.childAsIs(silver.util.cmdargs.Pflag.i_ast)).invoke(new Object[]{context.contextInheritedLazy(silver.util.cmdargs.Init.silver_util_cmdargs_flagOriginal__ON__silver_util_cmdargs_Flag)}, null)); } };

	}

	public static final common.NodeFactory<Pflag> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pflag> {

		@Override
		public Pflag invoke(final Object[] children, final Object[] annotations) {
			return new Pflag(children[0]);
		}
	};

}
