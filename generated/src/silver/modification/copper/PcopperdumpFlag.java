
package silver.modification.copper;

// top::CmdArgs ::= rest::CmdArgs 
public final class PcopperdumpFlag extends silver.util.cmdargs.NCmdArgs {

	public static final int i_rest = 0;


	public static final Class<?> childTypes[] = {silver.util.cmdargs.NCmdArgs.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_modification_copper_copperdumpFlag;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_rest] = new common.Lazy[silver.util.cmdargs.NCmdArgs.num_inh_attrs];

	}

	public PcopperdumpFlag(final Object c_rest) {
		super();
		this.child_rest = c_rest;

	}

	private Object child_rest;
	public final silver.util.cmdargs.NCmdArgs getChild_rest() {
		return (silver.util.cmdargs.NCmdArgs) (child_rest = common.Util.demand(child_rest));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_rest: return getChild_rest();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_rest: return child_rest;

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
		return context.childDecorated(silver.modification.copper.PcopperdumpFlag.i_rest).undecorate();
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
		return "silver:modification:copper:copperdumpFlag";
	}

	static void initProductionAttributeDefinitions() {
		// top.forceCopperDump = true
		silver.modification.copper.PcopperdumpFlag.synthesizedAttributes[silver.modification.copper.Init.silver_modification_copper_forceCopperDump__ON__silver_util_cmdargs_CmdArgs] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return true; } };

	}

	public static final common.NodeFactory<PcopperdumpFlag> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PcopperdumpFlag> {

		@Override
		public PcopperdumpFlag invoke(final Object[] children, final Object[] annotations) {
			return new PcopperdumpFlag(children[0]);
		}
	};

}
