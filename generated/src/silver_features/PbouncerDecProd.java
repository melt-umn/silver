
package silver_features;

// top::ADecoratedValue ::= v::ADecoratedValue 
public final class PbouncerDecProd extends silver_features.NADecoratedValue {

	public static final int i_v = 0;


	public static final Class<?> childTypes[] = {silver_features.NADecoratedValue.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_bouncerDecProd;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NADecoratedValue.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NADecoratedValue.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[1][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {
	childInheritedAttributes[i_v] = new common.Lazy[silver_features.NADecoratedValue.num_inh_attrs];

	}

	public PbouncerDecProd(final Object c_v) {
		super();
		this.child_v = c_v;

	}

	private Object child_v;
	public final silver_features.NADecoratedValue getChild_v() {
		return (silver_features.NADecoratedValue) (child_v = common.Util.demand(child_v));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_v: return getChild_v();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_v: return child_v;

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
		throw new common.exceptions.SilverInternalError("Production silver_features:bouncerDecProd erroneously claimed to forward");
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
		return "silver_features:bouncerDecProd";
	}

	static void initProductionAttributeDefinitions() {
		// top.aDecVal = v.aDecVal
		silver_features.PbouncerDecProd.synthesizedAttributes[silver_features.Init.silver_features_aDecVal__ON__silver_features_ADecoratedValue] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return ((common.StringCatter)context.childDecorated(silver_features.PbouncerDecProd.i_v).synthesized(silver_features.Init.silver_features_aDecVal__ON__silver_features_ADecoratedValue)); } };

	}

	public static final common.NodeFactory<PbouncerDecProd> factory = new Factory();

	public static final class Factory extends common.NodeFactory<PbouncerDecProd> {

		@Override
		public PbouncerDecProd invoke(final Object[] children, final Object[] annotations) {
			return new PbouncerDecProd(children[0]);
		}
	};

}
