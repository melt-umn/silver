
package silver.testing;

// t::TestSuite ::= ts::[Test] 
public final class Ptests extends silver.testing.NTestSuite {

	public static final int i_ts = 0;


	public static final Class<?> childTypes[] = {common.DecoratedNode.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_testing_tests;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver.testing.NTestSuite.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver.testing.NTestSuite.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Ptests(final Object c_ts) {
		super();
		this.child_ts = c_ts;

	}

	private Object child_ts;
	public final common.ConsCell getChild_ts() {
		return (common.ConsCell) (child_ts = common.Util.demand(child_ts));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ts: return getChild_ts();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ts: return child_ts;

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
		return ((silver.testing.NTestSuite)silver.testing.PtestsAsNT.invoke(context.childAsIsLazy(silver.testing.Ptests.i_ts)));
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
		return "silver:testing:tests";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<Ptests> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Ptests> {

		@Override
		public Ptests invoke(final Object[] children, final Object[] annotations) {
			return new Ptests(children[0]);
		}
	};

}
