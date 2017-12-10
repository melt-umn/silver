
package silver_features;

// top::ConcreteProductions ::= s::ATerm 
public final class PoneElem extends silver_features.NConcreteProductions {

	public static final int i_s = 0;


	public static final Class<?> childTypes[] = {silver_features.TATerm.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_oneElem;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NConcreteProductions.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NConcreteProductions.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public PoneElem(final Object c_s) {
		super();
		this.child_s = c_s;

	}

	private Object child_s;
	public final silver_features.TATerm getChild_s() {
		return (silver_features.TATerm) (child_s = common.Util.demand(child_s));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_s: return getChild_s();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_s: return child_s;

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
		throw new common.exceptions.SilverInternalError("Production silver_features:oneElem erroneously claimed to forward");
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
		return "silver_features:oneElem";
	}

	static void initProductionAttributeDefinitions() {
		// top.fst = s
		silver_features.PoneElem.synthesizedAttributes[silver_features.Init.core_fst__ON__silver_features_ConcreteProductions] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((silver_features.TATerm)context.childAsIs(silver_features.PoneElem.i_s)); } };

	}

	public static final common.NodeFactory<PoneElem> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PoneElem> {

		@Override
		public PoneElem invoke(final Object[] children, final Object[] annotations) {
			return new PoneElem(children[0]);
		}
	};

}
