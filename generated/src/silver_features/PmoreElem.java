
package silver_features;

// top::ConcreteProductions ::= ss::ConcreteProductions 
public final class PmoreElem extends silver_features.NConcreteProductions {

	public static final int i_ss = 0;


	public static final Class<?> childTypes[] = {silver_features.NConcreteProductions.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_moreElem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NConcreteProductions.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NConcreteProductions.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_ss] = new common.Lazy[silver_features.NConcreteProductions.num_inh_attrs];

	}

	public PmoreElem(final Object c_ss) {
		super();
		this.child_ss = c_ss;

	}

	private Object child_ss;
	public final silver_features.NConcreteProductions getChild_ss() {
		return (silver_features.NConcreteProductions) (child_ss = common.Util.demand(child_ss));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_ss: return getChild_ss();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_ss: return child_ss;

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
		throw new common.exceptions.SilverInternalError("Production silver_features:moreElem erroneously claimed to forward");
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
		return "silver_features:moreElem";
	}

	static void initProductionAttributeDefinitions() {
		// top.fst = ss.fst
		silver_features.PmoreElem.synthesizedAttributes[silver_features.Init.core_fst__ON__silver_features_ConcreteProductions] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver_features.TATerm)context.childDecorated(silver_features.PmoreElem.i_ss).synthesized(silver_features.Init.core_fst__ON__silver_features_ConcreteProductions)); } };

	}

	public static final common.NodeFactory<PmoreElem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PmoreElem> {

		@Override
		public PmoreElem invoke(final Object[] children, final Object[] annotations) {
			return new PmoreElem(children[0]);
		}
	};

}
