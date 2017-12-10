
package silver.testing.bin;

// r::Run ::= 'skip' skiprun::Run 
public final class PskipRun extends silver.testing.bin.NRun {

	public static final int i__G_1 = 0;
	public static final int i_skiprun = 1;


	public static final Class<?> childTypes[] = {silver.testing.bin.TSkip_t.class,silver.testing.bin.NRun.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_skipRun;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.bin.NRun.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.bin.NRun.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_skiprun] = new common.Lazy[silver.testing.bin.NRun.num_inh_attrs];

	}

	public PskipRun(final Object c__G_1, final Object c_skiprun) {
		super();
		this.child__G_1 = c__G_1;
		this.child_skiprun = c_skiprun;

	}

	private Object child__G_1;
	public final silver.testing.bin.TSkip_t getChild__G_1() {
		return (silver.testing.bin.TSkip_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_skiprun;
	public final silver.testing.bin.NRun getChild_skiprun() {
		return (silver.testing.bin.NRun) (child_skiprun = common.Util.demand(child_skiprun));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_1: return getChild__G_1();
			case i_skiprun: return getChild_skiprun();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_1: return child__G_1;
			case i_skiprun: return child_skiprun;

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
		throw new common.exceptions.SilverInternalError("Production silver:testing:bin:skipRun erroneously claimed to forward");
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
		return "silver:testing:bin:skipRun";
	}

	static void initProductionAttributeDefinitions() {
		// r.ioResult = ioval(r.ioInput.io, 0,)
		silver.testing.bin.PskipRun.synthesizedAttributes[silver.testing.bin.Init.silver_testing_bin_ioResult__ON__silver_testing_bin_Run] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((core.NIOVal)new core.Pioval(new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return ((Object)((core.NIOVal)context.inherited(silver.testing.bin.Init.silver_testing_bin_ioInput__ON__silver_testing_bin_Run)).decorate(context, (common.Lazy[])null).synthesized(core.Init.core_io__ON__core_IOVal)); } }, Integer.valueOf((int)0))); } };

	}

	public static final common.NodeFactory<PskipRun> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PskipRun> {

		@Override
		public PskipRun invoke(final Object[] children, final Object[] annotations) {
			return new PskipRun(children[0], children[1]);
		}
	};

}
