
package test.nonterm_b;

// top::B ::= Mangle_t 
public final class Pnonterm_b_cnc extends test.nonterm_b.NB {

	public static final int i__G_0 = 0;


	public static final Class<?> childTypes[] = {test.nonterm_b.TMangle_t.class};

	public static final int num_local_attrs = Init.count_local__ON__test_nonterm_b_nonterm_b_cnc;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[test.nonterm_b.NB.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[test.nonterm_b.NB.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pnonterm_b_cnc(final Object c__G_0) {
		super();
		this.child__G_0 = c__G_0;

	}

	private Object child__G_0;
	public final test.nonterm_b.TMangle_t getChild__G_0() {
		return (test.nonterm_b.TMangle_t) (child__G_0 = common.Util.demand(child__G_0));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i__G_0: return getChild__G_0();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i__G_0: return child__G_0;

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
		throw new common.exceptions.SilverInternalError("Production test:nonterm_b:nonterm_b_cnc erroneously claimed to forward");
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
		return "test:nonterm_b:nonterm_b_cnc";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<Pnonterm_b_cnc> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pnonterm_b_cnc> {

		@Override
		public Pnonterm_b_cnc invoke(final Object[] children, final Object[] annotations) {
			return new Pnonterm_b_cnc(children[0]);
		}
	};

}
