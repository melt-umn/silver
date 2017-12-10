
package silver.testing.bin;

// tr::TestingResults ::= nf::Integer 
public final class PtestingResults extends silver.testing.bin.NTestingResults {

	public static final int i_nf = 0;


	public static final Class<?> childTypes[] = {Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_bin_testingResults;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.bin.NTestingResults.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.bin.NTestingResults.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtestingResults(final Object c_nf) {
		super();
		this.child_nf = c_nf;

	}

	private Object child_nf;
	public final Integer getChild_nf() {
		return (Integer) (child_nf = common.Util.demand(child_nf));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_nf: return getChild_nf();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_nf: return child_nf;

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
		throw new common.exceptions.SilverInternalError("Production silver:testing:bin:testingResults erroneously claimed to forward");
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
		return "silver:testing:bin:testingResults";
	}

	static void initProductionAttributeDefinitions() {
		// tr.numFailed = nf
		silver.testing.bin.PtestingResults.synthesizedAttributes[silver.testing.bin.Init.silver_testing_numFailed__ON__silver_testing_bin_TestingResults] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((Integer)context.childAsIs(silver.testing.bin.PtestingResults.i_nf)); } };

	}

	public static final common.NodeFactory<PtestingResults> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtestingResults> {

		@Override
		public PtestingResults invoke(final Object[] children, final Object[] annotations) {
			return new PtestingResults(children[0]);
		}
	};

}
