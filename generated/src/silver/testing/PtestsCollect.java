
package silver.testing;

// t::TestSuite ::= tss::[TestSuite] 
public final class PtestsCollect extends silver.testing.NTestSuite {

	public static final int i_tss = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_testsCollect;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTestSuite.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTestSuite.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PtestsCollect(final Object c_tss) {
		super();
		this.child_tss = c_tss;

	}

	private Object child_tss;
	public final common.ConsCell getChild_tss() {
		return (common.ConsCell) (child_tss = common.Util.demand(child_tss));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_tss: return getChild_tss();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_tss: return child_tss;

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
		return ((silver.testing.NTestSuite)silver.testing.PconsolidateTestSuite.invoke(context.childAsIsLazy(silver.testing.PtestsCollect.i_tss)));
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
		return "silver:testing:testsCollect";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PtestsCollect> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PtestsCollect> {

		@Override
		public PtestsCollect invoke(final Object[] children, final Object[] annotations) {
			return new PtestsCollect(children[0]);
		}
	};

}
