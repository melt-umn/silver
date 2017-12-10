
package silver_features;

// top::FuncManipNT ::= a::Integer b::Integer 
public final class Pfmadd extends silver_features.NFuncManipNT {

	public static final int i_a = 0;
	public static final int i_b = 1;


	public static final Class<?> childTypes[] = {Integer.class,Integer.class};

	public static final int num_local_attrs = Init.count_local__ON__silver_features_fmadd;
	public static final String[] occurs_local = new String[num_local_attrs];

	public static final common.Lazy[] forwardInheritedAttributes = new common.Lazy[silver_features.NFuncManipNT.num_inh_attrs];

	public static final common.Lazy[] synthesizedAttributes = new common.Lazy[silver_features.NFuncManipNT.num_syn_attrs];
	public static final common.Lazy[][] childInheritedAttributes = new common.Lazy[2][];

	public static final common.Lazy[] localAttributes = new common.Lazy[num_local_attrs];
	public static final common.Lazy[][] localInheritedAttributes = new common.Lazy[num_local_attrs][];

	static {

	}

	public Pfmadd(final Object c_a, final Object c_b) {
		super();
		this.child_a = c_a;
		this.child_b = c_b;

	}

	private Object child_a;
	public final Integer getChild_a() {
		return (Integer) (child_a = common.Util.demand(child_a));
	}

	private Object child_b;
	public final Integer getChild_b() {
		return (Integer) (child_b = common.Util.demand(child_b));
	}



	@Override
	public Object getChild(final int index) {
		switch(index) {
			case i_a: return getChild_a();
			case i_b: return getChild_b();

			default: return null;
		}
	}

	@Override
	public Object getChildLazy(final int index) {
		switch(index) {
			case i_a: return child_a;
			case i_b: return child_b;

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
		throw new common.exceptions.SilverInternalError("Production silver_features:fmadd erroneously claimed to forward");
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
		return "silver_features:fmadd";
	}

	static void initProductionAttributeDefinitions() {
		// top.fmeval = a + b
		silver_features.Pfmadd.synthesizedAttributes[silver_features.Init.silver_features_fmeval__ON__silver_features_FuncManipNT] = new common.Lazy() { public final Object eval(final common.DecoratedNode context) { return Integer.valueOf(((Integer)context.childAsIs(silver_features.Pfmadd.i_a)) + ((Integer)context.childAsIs(silver_features.Pfmadd.i_b))); } };

	}

	public static final common.NodeFactory<Pfmadd> factory = new Factory();

	public static final class Factory extends common.NodeFactory<Pfmadd> {

		@Override
		public Pfmadd invoke(final Object[] children, final Object[] annotations) {
			return new Pfmadd(children[0], children[1]);
		}
	};

}
