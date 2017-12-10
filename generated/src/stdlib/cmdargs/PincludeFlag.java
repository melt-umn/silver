
package stdlib.cmdargs;

// top::CmdArgs ::= p::String rest::CmdArgs 
public final class PincludeFlag extends silver.util.cmdargs.NCmdArgs {

	public static final int i_p = 0;
	public static final int i_rest = 1;


	public static final Class<?> childTypes[] = {common.StringCatter.class,silver.util.cmdargs.NCmdArgs.class};

	public static final int num_local_attrs = Init.count_local__ON__stdlib_cmdargs_includeFlag;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_rest] = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_inh_attrs];

	}

	public PincludeFlag(final Object c_p, final Object c_rest) {
		super();
		this.child_p = c_p;
		this.child_rest = c_rest;

	}

	private Object child_p;
	public final common.StringCatter getChild_p() {
		return (common.StringCatter) (child_p = common.Util.demand(child_p));
	}

	private Object child_rest;
	public final silver.util.cmdargs.NCmdArgs getChild_rest() {
		return (silver.util.cmdargs.NCmdArgs) (child_rest = common.Util.demand(child_rest));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_p: return getChild_p();
			case i_rest: return getChild_rest();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_p: return child_p;
			case i_rest: return child_rest;

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
		return true;
	}

	@Override
	public common.Node evalForward(final common.DecoratedNode context) {
		return context.childDecorated(stdlib.cmdargs.PincludeFlag.i_rest).undecorate();
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
		return "stdlib:cmdargs:includeFlag";
	}

	static void initProductionAttributeDefinitions() {
		// top.includePaths = (p :: forward.includePaths)
		stdlib.cmdargs.PincludeFlag.synthesizedAttributes[stdlib.cmdargs.Init.stdlib_cmdargs_includePaths__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.ConsCell)core.Pcons.invoke(context.childAsIsLazy(stdlib.cmdargs.PincludeFlag.i_p), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((common.ConsCell)context.forward().synthesized(stdlib.cmdargs.Init.stdlib_cmdargs_includePaths__ON__silver_util_cmdargs_CmdArgs)); } })); } };

	}

	public static final common.NodeFactory<PincludeFlag> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PincludeFlag> {

		@Override
		public PincludeFlag invoke(final Object[] children, final Object[] annotations) {
			return new PincludeFlag(children[0], children[1]);
		}
	};

}
