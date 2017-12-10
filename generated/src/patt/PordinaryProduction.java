
package patt;

// top::OrdinaryNonterminal ::= ordinaryUndecorated::OrdinaryNonterminal 
public final class PordinaryProduction extends patt.NOrdinaryNonterminal {

	public static final int i_ordinaryUndecorated = 0;


	public static final Class<?> childTypes[] = {patt.NOrdinaryNonterminal.class};

	public static final int num_local_attrs = Init.count_local__ON__patt_ordinaryProduction;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[patt.NOrdinaryNonterminal.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[patt.NOrdinaryNonterminal.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ordinaryUndecorated] = new common.Lazy[patt.NOrdinaryNonterminal.num_inh_attrs];

	}

	public PordinaryProduction(final Object c_ordinaryUndecorated) {
		super();
		this.child_ordinaryUndecorated = c_ordinaryUndecorated;

	}

	private Object child_ordinaryUndecorated;
	public final patt.NOrdinaryNonterminal getChild_ordinaryUndecorated() {
		return (patt.NOrdinaryNonterminal) (child_ordinaryUndecorated = common.Util.demand(child_ordinaryUndecorated));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ordinaryUndecorated: return getChild_ordinaryUndecorated();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ordinaryUndecorated: return child_ordinaryUndecorated;

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
		throw new common.exceptions.SilverInternalError("Production patt:ordinaryProduction erroneously claimed to forward");
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
		return "patt:ordinaryProduction";
	}

	static void initProductionAttributeDefinitions() {

	}

	public static final common.NodeFactory<PordinaryProduction> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PordinaryProduction> {

		@Override
		public PordinaryProduction invoke(final Object[] children, final Object[] annotations) {
			return new PordinaryProduction(children[0]);
		}
	};

}
