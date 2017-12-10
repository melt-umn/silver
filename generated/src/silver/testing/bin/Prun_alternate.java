
package silver.testing.bin;

// r::Run ::= f::OptionalFail run_kwd::'run' ':' rest::CommandAlt_t 
public final class Prun_alternate extends silver.testing.bin.NRun {

	public static final int i_f = 0;
	public static final int i_run_kwd = 1;
	public static final int i__G_1 = 2;
	public static final int i_rest = 3;


	public static final Class<?> childTypes[] = {silver.testing.bin.NOptionalFail.class,silver.testing.bin.TRun_t.class,silver.testing.bin.TColon_t.class,silver.testing.bin.TCommandAlt_t.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_run_alternate;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.bin.NRun.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.bin.NRun.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[4][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_f] = new common.Lazy[silver.testing.bin.NOptionalFail.num_inh_attrs];

	}

	public Prun_alternate(final Object c_f, final Object c_run_kwd, final Object c__G_1, final Object c_rest) {
		super();
		this.child_f = c_f;
		this.child_run_kwd = c_run_kwd;
		this.child__G_1 = c__G_1;
		this.child_rest = c_rest;

	}

	private Object child_f;
	public final silver.testing.bin.NOptionalFail getChild_f() {
		return (silver.testing.bin.NOptionalFail) (child_f = common.Util.demand(child_f));
	}

	private Object child_run_kwd;
	public final silver.testing.bin.TRun_t getChild_run_kwd() {
		return (silver.testing.bin.TRun_t) (child_run_kwd = common.Util.demand(child_run_kwd));
	}

	private Object child__G_1;
	public final silver.testing.bin.TColon_t getChild__G_1() {
		return (silver.testing.bin.TColon_t) (child__G_1 = common.Util.demand(child__G_1));
	}

	private Object child_rest;
	public final silver.testing.bin.TCommandAlt_t getChild_rest() {
		return (silver.testing.bin.TCommandAlt_t) (child_rest = common.Util.demand(child_rest));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_f: return getChild_f();
			case i_run_kwd: return getChild_run_kwd();
			case i__G_1: return getChild__G_1();
			case i_rest: return getChild_rest();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_f: return child_f;
			case i_run_kwd: return child_run_kwd;
			case i__G_1: return child__G_1;
			case i_rest: return child_rest;

			default: return null;
		}
	}

	@Override
	public final int getNumberOfChildren() {
		return 4;
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
		return ((silver.testing.bin.NRun)new silver.testing.bin.Prun(common.Thunk.transformUndecorate(context.childDecoratedLazy(silver.testing.bin.Prun_alternate.i_f)), context.childAsIsLazy(silver.testing.bin.Prun_alternate.i_run_kwd), new common.Thunk<Object>(context) { public final Object doEval(final common.DecoratedNode context) { return new silver.testing.bin.TCommand_t(new common.StringCatter((common.StringCatter)(new common.StringCatter("\"")), (common.StringCatter)new common.StringCatter((common.StringCatter)((common.StringCatter)((silver.testing.bin.TCommandAlt_t)context.childAsIs(silver.testing.bin.Prun_alternate.i_rest)).lexeme), (common.StringCatter)(new common.StringCatter("\"")))), (core.NLocation)((core.NLocation)new core.Ploc((new common.StringCatter("??")), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1), Integer.valueOf((int)-1)))); } }));
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
		return "silver:testing:bin:run_alternate";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<Prun_alternate> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Prun_alternate> {

		@Override
		public Prun_alternate invoke(final Object[] children, final Object[] annotations) {
			return new Prun_alternate(children[0], children[1], children[2], children[3]);
		}
	};

}
